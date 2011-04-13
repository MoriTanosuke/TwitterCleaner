<?xml version="1.0" encoding="ISO-8859-1" ?>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml"
	xmlns:f="http://java.sun.com/jsf/core"
	xmlns:h="http://java.sun.com/jsf/html">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1" />
<link rel="stylesheet" type="text/css" href="/styles.css" />
<title>TwitterCleaner</title>
<jsp:include page="analytics.jsp" />
</head>
<body>
<h1>TwitterCleaner</h1>
<p><font color="green">This application uses <a
	href="https://code.google.com/intl/de/appengine/docs/java/taskqueue/overview.html">Googles
Task Queue</a> now. There is a limit of <em>20 deletes per second</em>. If
you got any issues, please leave a comment at <a
	href="https://github.com/MoriTanosuke/TwitterCleaner/issues">my bug
tracker</a>.</font></p>
<h2>I want to delete my tweets!</h2>
<p><a href="./signin">Sign in and start.</a></p>
<p>You will be taken to <a href="https://twitter.com/">Twitter</a>
to authorize this application.</p>

<h2>FAQ</h2>
<ul>
	<li><strong>What is this?</strong> <span> This application
	lets you wipe the last bunch of <a href="https://twitter.com">tweets
	from your timeline</a>. It uses <a
		href="http://dev.twitter.com/pages/oauth_faq">OAuth</a> to log in to
	twitter and stores <strong>NOTHING</strong>.</span></li>
	<li><strong>Why does this look so ugly?</strong> <span>I'm
	a developer first. And I don't really care about glossy buttons.</span></li>
	<li><strong>I want to delete <em>all</em> my tweets and I
	don't want to click that damn button all day!</strong> <span>Me too. <em>"wipe
	all"</em> will be the next thing I add to this application.</span></li>
	<li><strong>Who is responsible for this thing?</strong> <span><a
		href="http://www.kopis.de">I am. Come over and get in contact.</a></span></li>
</ul>

<h2>What's next?</h2>
<ul>
	<li>Maybe add an email input field to support <em>run in
	background</em> with notifications?</li>
	<li>Add input field for user to specify API calls per minute (or
	something like that)</li>
	<li>Respect the <a
		href="http://dev.twitter.com/pages/rate-limiting">Twitter API
	limits</a></li>
</ul>

<address>&copy; 2010 <a href="http://www.kopis.de/">Carsten
Ringe</a></address>

</body>
</html>
