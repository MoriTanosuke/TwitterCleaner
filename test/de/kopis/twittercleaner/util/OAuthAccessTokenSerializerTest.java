package de.kopis.twittercleaner.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import org.junit.Before;
import org.junit.Test;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

public class OAuthAccessTokenSerializerTest {

    private AccessToken TOKEN_REAL;
    private String TOKEN_SERIALIZED;

    @Test
    public void testSerialize() throws TwitterException, IOException {
        assertEquals(TOKEN_SERIALIZED, new OAuthAccessTokenSerializer("|").serializeOAuthAccessToken(TOKEN_REAL));
    }

    @Test
    public void testDeserialize() throws Exception {
        assertEquals(TOKEN_REAL,
                new OAuthAccessTokenSerializer("|").deserializeOAuthAccessToken(TOKEN_SERIALIZED.getBytes()));
    }

    @Test
    public void testEncodeSeparator() throws UnsupportedEncodingException {
        assertEquals("%7C", URLEncoder.encode("|", "UTF-8"));
        assertEquals("_", URLEncoder.encode("_", "UTF-8"));
        assertEquals("%7C", URLEncoder.encode("|", "ISO-8859-1"));
    }

    @Before
    public void setUp() throws UnsupportedEncodingException {
        TOKEN_REAL = new AccessToken("1-token", "bar");
        TOKEN_SERIALIZED = URLEncoder.encode("1-token|bar", "UTF-8");
    }
}
