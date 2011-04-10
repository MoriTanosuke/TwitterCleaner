package de.kopis.twittercleaner.util;

import java.io.IOException;
import java.io.Writer;

import twitter4j.ResponseList;
import twitter4j.Status;

public class ResponsePrinter {
	private Writer writer;
	
	public ResponsePrinter(final Writer writer) {
		this.writer = writer;
	}

	public void printResponses(final ResponseList<Status> responses) throws IOException {
		println("<h1>Your timeline</h1>");
		println("<ul>");
		for (Status status : responses) {
			println("<li><strong>" + status.getUser().getScreenName() + "</strong> " + status.getText() + "<br />" +
					"at <a href=\"http://twitter.com/" + status.getUser().getScreenName() + "/status/" + status.getId() + "\">" + status.getCreatedAt() + "</a><br />" +
					"from <em>" + status.getSource() + "</em></li>");
		}
		println("</ul>");
	}

	private void println(final String s) throws IOException {
		writer.write(s + "\n");
	}
}
