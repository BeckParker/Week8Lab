<%-- 
    Document   : notes
    Created on : Oct 29, 2017, 2:02:57 PM
    Author     : 679810
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
   <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Manage Users</title>
    </head>
    <body>
        <h1>Manage Notes</h1>
        <h2>Notes</h2>
        <p>${errorMessage}</p>
        <form action="notes" method ="POST">
            Contents: <input type="text" name="contents">
            <input type="submit" value="Create Note">
            <input type="hidden" value="addNote" name="action">
        </form>

        <br>
        
        <c:if test="${selectedNote != null}">
            <form action="notes" method="POST">
                <table border="1">
                        <thead>
                            <td>ID</td>
                            <td>Date Created</td>
                            <td>Content</td>
                        </thead>
                        <tr>
                            <td>${note.noteId}</td>
                            <td>${note.dateCreated}</td>
                            <td><input type="text" name="contents" value="${note.contents}"></td>
                            <input type="hidden" name="id" value="${note.noteId}">
                        </tr>
                </table>
                <input type="hidden" name="action" value="updateNote">
                <input type="submit" value="Update">
            </form>
        </c:if>
        <br>
        <table border="1">
        <thead>
            <td>ID</td>
            <td>Date Created</td>
            <td>Content</td>
        </thead>
            <c:forEach var="note" items="${notes}">
                <form action="notes" method="POST">
                <tr>
                    <td><a href="notes?noteSelection=${note.noteId}">${note.noteId}</a></td>
                    <td>${note.dateCreated}</td>
                    <td>${note.contents}</td>
                    <input type="hidden" name="id" value="${note.noteId}">
                    <td><input type="submit" value="Delete"></td>
                    <input type="hidden" name="action" value="deleteNote">
                </tr>
                </form>
            </c:forEach>
        </table>
    </body>
</html>
