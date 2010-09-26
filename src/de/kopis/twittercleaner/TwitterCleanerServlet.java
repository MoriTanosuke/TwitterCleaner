package de.kopis.twittercleaner;

import java.io.IOException;
import javax.servlet.http.*;

public class TwitterCleanerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws IOException {
		String redirect = "/error.jsp";
		if(req.getSession().getAttribute("twitter") != null) {
			redirect = "/timeline.jsp";
		}
		resp.sendRedirect(redirect);
	}
}
