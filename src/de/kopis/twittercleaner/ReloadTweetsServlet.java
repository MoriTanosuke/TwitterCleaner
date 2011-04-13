package de.kopis.twittercleaner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.RateLimitStatus;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class ReloadTweetsServlet extends HttpServlet {
    private static final Logger log = Logger.getLogger(ReloadTweetsServlet.class.getName());

    private void doReload(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        log.info("Reloading statuses from twitter");
        final Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");

        List<Status> statuses = new ArrayList<Status>();
        try {
            RateLimitStatus rateLimitStatus = twitter.getRateLimitStatus();
            req.getSession().setAttribute("rateLimitStatus",
                    rateLimitStatus.getRemainingHits() + ", next reset " + rateLimitStatus.getResetTime());

            ListIterator<Status> userTimeline = twitter.getUserTimeline().listIterator();
            while (userTimeline.hasNext()) {
                Status s = userTimeline.next();
                statuses.add(s);
            }
        } catch (TwitterException e1) {
            log.throwing(getClass().getName(), "doGet", e1);
            resp.getWriter().append("Error while reloading statuses from twitter: ").append(e1.getMessage());
        }
        req.getSession().setAttribute("statuses", statuses);

        String redirect = "/timeline.jsp";
        log.finest("Redirecting to " + redirect);
        resp.sendRedirect(redirect);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReload(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doReload(req, resp);
    }

    private static final long serialVersionUID = 1L;

}
