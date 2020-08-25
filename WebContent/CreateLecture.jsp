<%@page import="Schedule.model.User" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<% User user = (User) session.getAttribute("loggedUser"); %>


    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="data:,">
        <title>ScheduleAddLecture</title>
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
            <form method="post" action="AddLectureServlet" onsubmit="return checkValues()">
            <table>
                <caption>Add Lectures</caption>
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
                        <input type="text" id="group" name="group" required maxlength="3" value="">
                    </td>
                </tr>
                <tr>
                    <th>From</th>
                    <td>
                        <input type="time" id="from" name="from" required value="">
                    </td>
                </tr>
                <tr>
                    <th>To</th>
                    <td>
                        <input type="time" id="to" name="to" required value="">
                    </td>
                </tr>
                <tr>
                    <th>Clasroom</th>
                    <td><input type="text" id="classroom" name="clasroom" required value=""></td>
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
                    <td><input type="text" id="subject" name="subject" required value=""></td>
                </tr>
                <tr>
                    <th>Teacher</th>
                    <td><input type="text" id="teacher" name="teacher" required value="<%= user.getUserName() %>"></td>
                </tr>
                <tr>
                    <th>&nbsp;</th>
                    <td><input type="submit" class="submit" value="add"></td>
                </tr>
            </table>
            </form>
        </div>
        <div>
        	<a href="LogoutServlet">Logout</a>
        </div>
    
    </body>
    </html>