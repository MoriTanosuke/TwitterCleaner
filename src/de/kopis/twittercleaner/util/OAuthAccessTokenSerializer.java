package de.kopis.twittercleaner.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

public class OAuthAccessTokenSerializer {
    public static String serializeOAuthAccessToken(Twitter twitter) throws TwitterException, IOException {
        AccessToken token = twitter.getOAuthAccessToken();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        ObjectOutputStream oout = new ObjectOutputStream(out);
        oout.writeBytes(token.getToken());
        oout.writeBytes(token.getTokenSecret());
        oout.close();

        return new String(out.toByteArray());
    }

    public static AccessToken deserializeOAuthAccessToken(byte[] buf) throws TwitterException, IOException {
        String token = "";
        String tokenSecret = "";

        ObjectInputStream oin = new ObjectInputStream(new ByteArrayInputStream(buf));
        token = oin.readUTF();
        tokenSecret = oin.readUTF();
        oin.close();

        return new AccessToken(token, tokenSecret);
    }
}