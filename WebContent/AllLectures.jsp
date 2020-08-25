<%@page import="Schedule.model.Lecture"%>
<%@page import="Schedule.model.User"%>
<%@page import="Schedule.model.Role"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<%
    User loggedUser = (User) session.getAttribute("loggedUser");
	Integer practiceCounter = (Integer) request.getAttribute("practiceCounter");
	Integer theoryCounter = (Integer) request.getAttribute("theoryCounter");
	@SuppressWarnings("unchecked")
	List<Lecture> lectures = (List<Lecture>) request.getAttribute("lectures");
%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="icon" href="data:,">
    <title>All Lectures</title>
    <style>
        .mainTable {border-collapse: collapse;
                    border-spacing: 0px;
                  }
        .mainTable tr:nth-child(even){background-color: #f2f2f2;}
        .mainTable tr th {color: aliceblue;
        background-color: blue;
        padding: 10px;}
        .mainTable tr td {
            padding: 10px;
        }
        a:link, a:visited, button, .submit {
            background-color: #4085f5;
            color: white;
            padding: 8px 12px;
            text-align: center;
            text-decoration: none;
            display: inline-block;
            border-radius: 10px;
        }

        a:hover, a:active {
            background-color: blue;
        }
    </style>
    <script src="js/AllLectures.js"></script>
</head>
<body>
    <div>
        <table>
            <form action="SearchByDayServlet" method="post">
                <tr>              
                    <td>
                        <select name="day" id="day">
                            <option value="All">All</option>
                            <option value="Monday">Monday</option>
                            <option value="Tuesday">Tuesday</option>
                            <option value="Wednesday">Wednesday</option>
                            <option value="Thursday">Thursday</option>
                            <option value="Friday">Friday</option>
                            <option value="Saturday">Saturday</option>
                          </select>
                    </td>
                    <td>
                        <input type="submit" class="submit" value="search">
                    </td>
                    <% if(loggedUser.getRole().equals(Role.teacher)){ %>
                    <td><a href="ViewAddLectureServlet">Create new lecture</a></td>
                    <% } %>
                    <td><a href="LogoutServlet">Logout</a></td>
                </tr>
            </form>
        </table>
    </div>
    <div>
        <table class="mainTable" border="1">
            <caption><h1>Schedule</h1></caption>
            
            <tr>
                <th>Day</th>
                <th>Group</th>
                <th>From</th>
                <th>To</th>
                <th>Classroom</th>
                <th>Teaching</th>
                <th>Subject</th>
                <th>Teacher</th>
            <% if(loggedUser.getRole().equals(Role.teacher)){ %>
                <th colspan="2">Action</th>
            <% } %>
            </tr>
            <% for (Lecture itLecture: lectures) { %>
            <tr>
                <td><%= itLecture.getDay() %></td>
                <td><%= itLecture.getGroup() %></td>
                <td><%= itLecture.getFrom() %></td>
                <td><%= itLecture.getTo() %></td>
                <td><%= itLecture.getClassroom() %></td>
                <td><%= itLecture.getTeaching().getName() %></td>
                <td><%= itLecture.getSubject() %></td>
                <td><%= itLecture.getTeacher() %></td>

            	<% if(loggedUser.getRole().equals(Role.teacher)){ %>
                <td>
                    <a href="DeleteLectureServlet?delId=<%= itLecture.getId() %>" onclick="return confirmDeletion()">delete</a>
                </td>
                <td>
                    <a href="ViewLectureServlet?viewId=<%= itLecture.getId() %>">edit</a>
                </td>
            	<% } %>
            </tr>
			<% } %>
        </table>
    </div>
    <div>
        <table>
            <tr>
                <th>number of theory classes</th>
                <td><%= theoryCounter %></td>
            </tr>
            <tr>
                <th>number of practice classes</th>
                <td><%= practiceCounter %></td>
            </tr>
        </table>
    </div>
</body>
</html>