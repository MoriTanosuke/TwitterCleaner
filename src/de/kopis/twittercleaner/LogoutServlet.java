package de.kopis.twittercleaner;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.http.*;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final Logger log = Logger.getLogger(LogoutServlet.class.getName());

	public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		String redirect = "/index.jsp";

		req.getSession().removeAttribute("twitter");

		log.finest("Redirecting to " + redirect);
		resp.sendRedirect(redirect);
	}
}
