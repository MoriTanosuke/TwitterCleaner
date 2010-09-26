package de.kopis.twittercleaner;

import static org.junit.Assert.*;

import org.junit.Test;


public class HtmlStatusWriterTest {
	@Test
	public void testNullToString() {
		final String expected = "";
		final String actual = new HtmlStatusWriter(null).toString();
		assertEquals(expected, actual);
	}
}
