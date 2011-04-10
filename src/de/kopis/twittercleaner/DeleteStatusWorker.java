package de.kopis.twittercleaner;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import de.kopis.twittercleaner.util.OAuthAccessTokenSerializer;

public class DeleteStatusWorker extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteStatusWorker.class.getName());

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Deleting a status now.");
        try {
            String id = req.getParameter("id");
            AccessToken accessToken = OAuthAccessTokenSerializer.deserializeOAuthAccessToken(req.getParameter(
                    "oauthtoken").getBytes());
            log.finest("Trying to destroy status '" + id + "' with access token for username '"
                    + accessToken.getScreenName() + "'");
        } catch (TwitterException e) {
            throw new ServletException("Can not deserialize access token.", e);
        }

    }

}
