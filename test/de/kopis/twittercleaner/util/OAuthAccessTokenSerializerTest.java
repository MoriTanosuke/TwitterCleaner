package de.kopis.twittercleaner.util;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.Test;

import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;

public class OAuthAccessTokenSerializerTest {

    private AccessToken TOKEN_REAL;
    private String TOKEN_SERIALIZED;

    @Test
    public void testSerialize() throws TwitterException, IOException {
        assertEquals(TOKEN_SERIALIZED, OAuthAccessTokenSerializer.serializeOAuthAccessToken(TOKEN_REAL));
    }

    @Test
    public void testDeserialize() throws Exception {
        assertEquals(TOKEN_REAL, OAuthAccessTokenSerializer.deserializeOAuthAccessToken(TOKEN_SERIALIZED.getBytes()));
    }

    @Before
    public void setUp() {
        TOKEN_REAL = new AccessToken("1-token", "bar");
        TOKEN_SERIALIZED = "1-token|bar";
    }
}
