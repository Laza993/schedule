<%@page import="Schedule.model.User"%>
<%@page import="Schedule.model.Role"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
	User loggedUser = (User) session.getAttribute("loggedUser");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Schedule</title>
    <style>
        a:link, a:visited {
            background-color: #4085f5;
            color: white;
            padding: 10px 15px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            border-radius: 10px;
        }

        a:hover, a:active {
            background-color: blue;
        }
    </style>
</head>
<body>
    <h1>
    <% if (loggedUser != null) { %>
    <%= loggedUser.getUserName() %> Welcome to School Schedule
    <% } %>
    </h1>
<% if (loggedUser == null) { %>
    <a href="Login.html">Please sign in</a>
<% } else if (loggedUser.getRole().equals(Role.student)){ %>
    <a href="AllLecturesServlet">Preview schedule</a>
    <a href="LogoutServlet">Logout</a>
<% } else { %>
    <a href="AllLecturesServlet">Preview schedule</a>
    <a href="ViewAddLectureServlet">Add a new Lecture</a>
    <a href="LogoutServlet">Logout</a>
<% } %>
</body>
</html>