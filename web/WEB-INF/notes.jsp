<%-- 
    Document   : notes
    Created on : Apr 4, 2020, 2:27:59 PM
    Author     : 792807
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Notes!</h1>
        <table>
            <tr>
                <th>Date Created</th>
                <th>Title</th>
                <th></th>
            </tr>
            <c:forEach var="notes" items="${notesList}">
                <tr>
                    <td>${notes.datecreated}</td>
                    <td>${notes.title}</td>
                    <td></td>
                </tr>
            </c:forEach>
        </table>
    </body>
</html>
