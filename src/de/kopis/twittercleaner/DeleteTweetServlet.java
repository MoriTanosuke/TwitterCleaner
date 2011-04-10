package de.kopis.twittercleaner;

import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withMethod;
import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withParam;
import static com.google.appengine.api.taskqueue.TaskOptions.Builder.withUrl;

import java.io.IOException;
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
import com.google.appengine.api.taskqueue.TaskOptions;
import com.google.appengine.api.taskqueue.TaskOptions.Method;

import de.kopis.twittercleaner.util.OAuthAccessTokenSerializer;

public class DeleteTweetServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(DeleteTweetServlet.class.getName());

    @Override
    public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
        final Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
        try {
            // TODO use a Task Queue for deletion
            Queue queue = QueueFactory.getQueue("delete-queue");
            ResponseList<Status> timeline;
            log.finest("Adding next bunch of statuses to delete queue.");
            timeline = twitter.getUserTimeline();
            log.finest("the timeline: " + timeline);
            Iterator<Status> it = timeline.iterator();
            while (it.hasNext()) {
                Status s = it.next();
                log.finest("Creating task to delete status " + s.getId());
                List<TaskOptions> options = new ArrayList<TaskOptions>();
                options.add(withUrl("/deletestatusworker"));
                options.add(withParam("id", Long.toString(s.getId())));
                options.add(withParam("oauthtoken", OAuthAccessTokenSerializer.serializeOAuthAccessToken(twitter)));
                options.add(withMethod(Method.GET));
                queue.add(options);
            }
            log.finest("Added " + timeline.size() + " statuses to delete queue.");
            // TODO respect API limit!
            // TODO add user input for API calls per minute or such
        } catch (final TwitterException e) {
            log.throwing(getClass().getName(), "doGet", e);
            resp.getWriter().append("Error while adding statuses to delete queue: ").append(e.getMessage());
        }
        resp.sendRedirect("/timeline.jsp");
    }
}
