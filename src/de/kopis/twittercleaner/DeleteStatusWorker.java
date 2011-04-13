package de.kopis.twittercleaner;

import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import de.kopis.twittercleaner.cmd.DeleteStatusCommand;
import de.kopis.twittercleaner.util.OAuthAccessTokenSerializer;

public class DeleteStatusWorker extends HttpServlet {
    private static final Logger log = Logger.getLogger(DeleteStatusWorker.class.getName());

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doProcess(req, resp);
    }

    private void doProcess(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("Deleting a status now.");
        String id = req.getParameter("id");
        String token = req.getParameter("oauthtoken");
        log.info("Using " + id + " and oauthtoken " + token);
        try {
            AccessToken accessToken = new OAuthAccessTokenSerializer().deserializeOAuthAccessToken(token.getBytes());
            log.finest("Trying to destroy status '" + id + "' with access token for username '"
                    + accessToken.getScreenName() + "'");
            // XXX delete status
            new DeleteStatusCommand(Long.valueOf(id)).execute(accessToken);
            log.info("Status " + id + " deleted.");
        } catch (NumberFormatException e) {
            log.severe("Can not delete status, because status ID is not a number: " + id);
        } catch (TwitterException e) {
            throw new ServletException("Can not deserialize access token.", e);
        }

    }

}
