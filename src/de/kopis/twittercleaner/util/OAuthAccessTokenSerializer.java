package de.kopis.twittercleaner.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

public class OAuthAccessTokenSerializer {
    private static final String SEPARATOR = "|";

    public static String serializeOAuthAccessToken(Twitter twitter) throws TwitterException, IOException {
        return serializeOAuthAccessToken(twitter.getOAuthAccessToken());
    }

    public static String serializeOAuthAccessToken(AccessToken token) throws TwitterException, IOException {
        return token.getToken() + SEPARATOR + token.getTokenSecret();
    }

    public static AccessToken deserializeOAuthAccessToken(byte[] serialized) throws TwitterException, IOException {
        Reader r = new StringReader(new String(serialized));
        int c = -1;
        StringBuilder buf = new StringBuilder();
        while ((c = r.read()) != -1) {
            buf.append((char) c);
        }
        String[] data = buf.toString().split("\\" + SEPARATOR);
        AccessToken token = new AccessToken(data[0], data[1]);
        return token;
    }
}