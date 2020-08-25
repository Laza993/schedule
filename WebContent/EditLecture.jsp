<%@page import="Schedule.model.Lecture;" %>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% Lecture lecture = (Lecture) request.getAttribute("lecture"); %>

    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Edit Lecture</title>
        <link rel="icon" href="data:,">
        <style>
            tr th {text-align: right;
                    width: 150px;}
            tr, td {width: 200px;}
            tr td input, select {box-sizing:content-box;
                                width: 100%;
                                }
            .radio {width: 20px;}
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
        <script src="js/AddLecture.js"></script>
    </head>
    <body>
        <div>
            <button onclick="return backward()">back</button>
        </div>
        <div>
            <form method="post" action="EditLectureServlet" onsubmit="return checkValues()">
            <table>
                <caption>Edit Lecture</caption>
                <tr>
                    <th>Day</th>
                    <td>
                        <select name="day" id="day">
                            <option value="Monday">Monday</option>
                            <option value="Tuesday">Tuesday</option>
                            <option value="Wednesday">Wednesday</option>
                            <option value="Thursday">Thursday</option>
                            <option value="Friday">Friday</option>
                            <option value="Saturday">Saturday</option>
                          </select>
                    </td>
                </tr>
                <tr>
                    <th>Group</th>
                    <td>
                        <input type="text" id="group" name="group" required maxlength="3" value="<%= lecture.getGroup() %>">
                    </td>
                </tr>
                <tr>
                    <th>From</th>
                    <td>
                        <input type="time" id="from" name="from" required value="<%= lecture.getFrom() %>">
                    </td>
                </tr>
                <tr>
                    <th>To</th>
                    <td>
                        <input type="time" id="to" name="to" required value="<%= lecture.getTo() %>">
                    </td>
                </tr>
                <tr>
                    <th>Classroom</th>
                    <td><input type="text" id="clasroom" name="clasroom" required value="<%= lecture.getClassroom() %>"></td>
                </tr>
                <tr>
                    <th>Teaching</th>
                    <td>
                        <table>
                            <tr>
                                <td>
                                    <input class="radio"  type="radio" id="theory" name="teaching" value="theory" checked>
                                    <label for="theory">Theory</label><br>
                                </td>
                                <td>
                                    <input class="radio" type="radio" id="practice" name="teaching" value="practice">
                                    <label for="practice">Practice</label><br>
                                </td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <th>Subject</th>
                    <td><input type="text" id="subject" name="subject" required value="<%= lecture.getSubject() %>"></td>
                </tr>
                <tr>
                    <th>Teacher</th>
                    <td><input type="text" name="teacher" required value="<%= lecture.getTeacher() %>"></td>
                </tr>
                <tr>
                    <td><input type="hidden" name="ediTid" value="<%= lecture.getId() %>"></td>
                    <td><input type="submit" class="submit" value="save changes"></td>
                </tr>
            </table>
            </form>
        </div>
        <div>
        	<a href="LogoutServlet">Logout</a>
        </div>
    
    </body>
    </html>