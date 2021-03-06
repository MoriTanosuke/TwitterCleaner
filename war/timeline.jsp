<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@page import="java.util.List"%>
<%@page import="de.kopis.twittercleaner.util.OAuthAccessTokenSerializer"%>
<%@page import="de.kopis.twittercleaner.HtmlStatusWriter"%>
<%@page import="java.text.DateFormat"%>
<%@page import="twitter4j.Status"%>
<%@page import="twitter4j.ResponseList"%>
<%@page import="twitter4j.Twitter"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
    //TODO add taglib for jsf html/core
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<title>TwitterCleaner :: Timeline</title>
<jsp:include page="analytics.jsp" />
</head>
<body>

<%
if(request.getSession().getAttribute("statuses") != null) {
    final List<Status> timeline = (List<Status>)request.getSession().getAttribute("statuses");
    out.append("<ul>");
    for (final Status status : timeline) {
        out.append("<li>").append(new HtmlStatusWriter(status).withSource().toString())
                .append(" <a href=\"/delete?id=" + status.getId() + "\">delete</a>").append("</li>");
    }
    out.append("</ul>");
}

%>API rate limit status: <% out.println(request.getSession().getAttribute("rateLimitStatus"));
%>

<ul>
	<li><a href="/reload">reload from twitter</a></li>
	<li><a href="/delete">wipe this bunch</a></li>
<%--
	<li><a href="/delete?all=true">wipe all! (and yes, I know what I'm doing)</a></li>
 --%>
	<li><a href="/logout">log out</a></li>
</ul>

</body>
</html>