package de.kopis.twittercleaner;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

public class TwitterCleanerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = Logger.getLogger(TwitterCleanerServlet.class.getName());

	public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		String redirect = "/index.jsp";
		if(req.getSession().getAttribute("twitter") != null) {
			redirect = "/timeline.jsp";
		}
		log.finest("Redirecting to " + redirect);
		resp.sendRedirect(redirect);
	}
}
