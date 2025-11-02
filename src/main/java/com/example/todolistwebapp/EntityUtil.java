package com.example.todolistwebapp;

import jakarta.persistence.*;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Software Development II
 * October 19, 2025
 * EntityUtil.java
 * @author Jacob Whitney
 */
@WebServlet("/todo")
public class EntityUtil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<ToDoItem> list = getToDoItemsFromDb();
        request.setAttribute("list", list);
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("add".equals(action)) {
            String description = request.getParameter("description");
            ToDoItem item = new ToDoItem(description);
            addItemToDb(item);

        } else if ("delete".equals(action)) {
            long id = Integer.parseInt(request.getParameter("id"));
            deleteItemFromDb(id);
        }

        // After performing the action, redirect back to the list
        response.sendRedirect("todo");
    }

    /**
     * getToDoItemsFromDb
     * Query to-do items from database to populate the list
     * @return List<ToDoItem>
     */
    public static List<ToDoItem> getToDoItemsFromDb() {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tdl_pu");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        List<ToDoItem> list = List.of();
        try {
            transaction.begin();
            list = manager.createQuery("SELECT t FROM ToDoItem t", ToDoItem.class).getResultList();
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }
        return list;
    }

    /**
     * addItemToDb
     * Adds passed item to database
     */
    public static boolean addItemToDb(ToDoItem $item) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tdl_pu");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        boolean result = false;
        try {
            transaction.begin();
            manager.persist($item);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return result;
    }

    /**
     * deleteItemFromDb
     * Delete item with passed ID from database
     */
    public static boolean deleteItemFromDb(Long id) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("tdl_pu");
        EntityManager manager = factory.createEntityManager();
        EntityTransaction transaction = manager.getTransaction();

        boolean result = false;
        try {
            transaction.begin();
            ToDoItem item = manager.find(ToDoItem.class, id);
            if (item != null) {
                manager.remove(item);
                result = true;
            } else {
                System.out.println("> Item not found in the database");
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        } finally {
            manager.close();
        }

        return result;
    }
}
