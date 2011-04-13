package de.kopis.twittercleaner.cmd;

import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class DeleteStatusCommand {
    private final long statusId;

    public DeleteStatusCommand(long statusId) {
        this.statusId = statusId;
    }

    public Status execute(AccessToken t) throws TwitterException {
        return execute(new TwitterFactory().getInstance(t));
    }

    public Status execute(Twitter t) throws TwitterException {
        return t.destroyStatus(statusId);
    }
}