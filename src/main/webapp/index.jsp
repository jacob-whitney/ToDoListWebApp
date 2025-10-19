<%@ page import="java.util.*, com.example.todolistwebapp.ToDoItem" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>To Do List</title>
</head>
<body>
    <h1><%= "To Do List" %></h1>
    <table>
        <tr>
            <th>ID</th>
            <th>Description</th>
        </tr>

        <%
            List<ToDoItem> list = (List<ToDoItem>) request.getAttribute("list");
            if (list != null) {
                for (ToDoItem item : list) {
        %>
        <tr>
            <td><%= item.getId() %></td>
            <td><%= item.getDescription() %></td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="2">No items found.</td></tr>
        <%
            }
        %>


    </table>

</body>
</html>