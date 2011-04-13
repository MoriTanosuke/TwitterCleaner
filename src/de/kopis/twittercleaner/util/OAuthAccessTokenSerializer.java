package de.kopis.twittercleaner.util;

import java.io.IOException;
import java.io.Reader;
import java.io.StringReader;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.logging.Logger;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

public class OAuthAccessTokenSerializer {
    private static final Logger log = Logger.getLogger(OAuthAccessTokenSerializer.class.getName());
    private final String separator;

    public OAuthAccessTokenSerializer() throws UnsupportedEncodingException {
        this("__");
    }

    public OAuthAccessTokenSerializer(String separator) throws UnsupportedEncodingException {
        this.separator = URLEncoder.encode(separator, "UTF-8"); // "%257C";
    }

    public String serializeOAuthAccessToken(Twitter twitter) throws TwitterException, IOException {
        return serializeOAuthAccessToken(twitter.getOAuthAccessToken());
    }

    public String serializeOAuthAccessToken(AccessToken token) throws TwitterException, IOException {
        log.info("Serializing access token");
        return token.getToken() + separator + token.getTokenSecret();
    }

    public AccessToken deserializeOAuthAccessToken(byte[] serialized) throws TwitterException, IOException {
        log.info("Deserializing access token");
        Reader r = new StringReader(new String(serialized));
        int c = -1;
        StringBuilder buf = new StringBuilder();
        while ((c = r.read()) != -1) {
            buf.append((char) c);
        }
        log.info("Deserialized token: " + buf);
        String[] data = buf.toString().split("\\" + separator);
        AccessToken token = new AccessToken(data[0], data[1]);
        return token;
    }
}