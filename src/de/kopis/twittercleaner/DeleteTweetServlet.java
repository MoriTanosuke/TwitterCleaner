package de.kopis.twittercleaner;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import com.google.appengine.api.taskqueue.Queue;
import com.google.appengine.api.taskqueue.QueueFactory;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

import de.kopis.twittercleaner.util.OAuthAccessTokenSerializer;

public class DeleteTweetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(DeleteTweetServlet.class.getName());

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final List<String> statusIds = new ArrayList<String>();

        final Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
        try {
            String accessToken = new OAuthAccessTokenSerializer().serializeOAuthAccessToken(twitter);

            String id = req.getParameter("id");
            if (id != null && !"".equals(id)) {
                log.info("Deleting single status " + id);
                try {
                    statusIds.add(id);
                } catch (NumberFormatException e) {
                    log.throwing(getClass().getName(), "doGet", e);
                    resp.getWriter().append("Can not parse status ID: " + id).append(e.getMessage());
                }
            } else {
                log.info("Deleting lots of statuses");
                try {
                    ResponseList<Status> timeline;
                    log.finest("Adding next bunch of statuses to delete queue.");
                    timeline = twitter.getUserTimeline();
                    log.finest("the timeline: " + timeline);
                    Iterator<Status> it = timeline.iterator();
                    while (it.hasNext()) {
                        Status s = it.next();
                        log.finest("Creating task to delete status " + s.getId());
                        statusIds.add(Long.toString(s.getId()));
                    }
                    // TODO respect API limit!
                    // TODO add user input for API calls per minute or such
                } catch (final TwitterException e) {
                    log.throwing(getClass().getName(), "doGet", e);
                    resp.getWriter().append("Error while adding statuses to delete queue: ").append(e.getMessage());
                }
            }

            log.finest("About to add " + statusIds.size() + " statuses to delete queue.");
            Queue queue = QueueFactory.getQueue("delete-queue");
            Iterator<String> it = statusIds.iterator();
            while (it.hasNext()) {
                String s = it.next();
                log.finest("Creating task to delete status " + s);
                queue.add(withUrl("/deletestatusworker").method(Method.GET).param("id", s)
                        .param("oauthtoken", URLEncoder.encode(accessToken)));
            }
        } catch (TwitterException e1) {
            log.throwing(getClass().getName(), "doGet", e1);
            resp.getWriter().append("Error while adding statuses to delete queue: ").append(e1.getMessage());
        }
        resp.sendRedirect("/reload");
    }
}
