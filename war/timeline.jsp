<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="twitter4j.Status"%>
<%@page import="twitter4j.ResponseList"%>
<%@page import="twitter4j.Twitter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>TwitterCleaner :: Timeline</title>
</head>
<body>

<%
	final Twitter twitter = (Twitter) request.getSession().getAttribute("twitter");
	final ResponseList<Status> timeline = twitter.getUserTimeline();
	out.append("<ul>");
	for (final Status status : timeline) {
		out.append("<li>")
		.append(status.getUser().getName()).append(" ").append(status.getText())
		.append("</li>");
	}
	out.append("</ul>");
%>
<a href="/delete">wipe them!</a>
<a href="/logout">log out</a>

</body>
</html>