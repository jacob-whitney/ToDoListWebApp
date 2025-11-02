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
            <th>Action</th>
        </tr>

        <%
            List<ToDoItem> list = (List<ToDoItem>) request.getAttribute("list");
            if (list != null && !list.isEmpty()) {
                for (ToDoItem item : list) {
        %>
        <tr>
            <td><%= item.getId() %></td>
            <td><%= item.getDescription() %></td>
            <td>
                <form action="todo" method="post" style="display:inline;">
                    <input type="hidden" name="action" value="delete">
                    <input type="hidden" name="id" value="<%= item.getId() %>">
                    <input type="submit" value="Delete">
                </form>
            </td>
        </tr>
        <%
            }
        } else {
        %>
        <tr><td colspan="3">No items found.</td></tr>
        <%
            }
        %>
    </table>

    <form action="todo" method="post">
        <input type="hidden" name="action" value="add">
        <input type="text" name="description" placeholder="New item description" required>
        <input type="submit" value="Add">
    </form>

</body>
</html>