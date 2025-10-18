package com.example.todolistwebapp;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Software Development II
 * October 19, 2025
 * EntityUtil.java
 * @author Jacob Whitney
 */
public interface EntityUtil {
    public static final EntityManagerFactory factory = Persistence.createEntityManagerFactory("tdl_pu");
    public static final EntityManager manager = factory.createEntityManager();
    public static final EntityTransaction transaction = manager.getTransaction();

    /**
     * getToDoItemsFromDb
     * Query to-do items from database to populate the ToDoList
     * @return ToDoList
     */
    public static ToDoList getToDoItemsFromDb() {
        ToDoList list = new ToDoList();
        try {
            transaction.begin();
            List<ToDoItem> items = manager.createQuery("SELECT t FROM ToDoItem t", ToDoItem.class).getResultList();

            for (ToDoItem item : items) {
                list.addItem(item);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }
        return list;
    }

    /**
     * addItemToDb
     * Adds passed item to database
     */
    public static boolean addItemToDb(ToDoItem $item) {
        boolean result = false;
        try {
            transaction.begin();
            manager.persist($item);
            transaction.commit();
            result = true;
        } catch (Exception e) {
            if (transaction.isActive()) transaction.rollback();
            e.printStackTrace();
        }

        return result;
    }

    /**
     * deleteItemFromDb
     * Delete item with passed ID from database
     */
    public static boolean deleteItemFromDb(Long id) {
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
        }

        return result;
    }

    /**
     * closeTransactionManager
     * Closes transaction manager after all transactions are complete
     */
    public static boolean closeTransactionManager() {
        boolean result = false;
        try {
            if (transaction.isActive()) transaction.rollback();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            manager.close();
        }

        return result;
    }
}
