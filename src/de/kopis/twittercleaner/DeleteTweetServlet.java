package de.kopis.twittercleaner;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.ResponseList;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class DeleteTweetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(DeleteTweetServlet.class.getName());

	public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		final Twitter twitter = (Twitter) req.getSession().getAttribute("twitter");
		try {
			final ResponseList<Status> timeline = twitter.getUserTimeline();
			// loop over timeline until no more tweets are available
//			while (timeline != null && timeline.size() > 0) {
				for (final Status status : timeline) {
					final long id = status.getId();
					twitter.destroyStatus(id);
					log.finest("Destroying status: " + id);
				}
//			}
		} catch (final TwitterException e) {
			resp.getWriter()
				.append("Error while destroying statuses: ")
				.append(e.getMessage());
		}
		resp.sendRedirect("/timeline.jsp");
	}
}
