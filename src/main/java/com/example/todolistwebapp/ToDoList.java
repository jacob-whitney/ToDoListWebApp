package com.example.todolistwebapp;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Software Development II
 * October 19, 2025
 * ToDoList.java
 * @author Jacob Whitney
 */

public class ToDoList {
    // Attributes
    private ArrayList<ToDoItem> list;

    // Constructors
    public ToDoList() {
        this.list = new ArrayList<>();
    }

    // Getters and Setters
    public ArrayList<ToDoItem> getList() {
        return list;
    }

    public void setList(ArrayList<ToDoItem> list) {
        this.list = list;
    }

    // -- Custom Methods --
    /**
     * getSize
     * Gets size of the to-do list
     */
    public int getSize() {
        return list.size();
    }

    /**
     * addItem
     * Adds a to-do item to the list
     */
    public void addItem(ToDoItem item) {
        list.add(item);
    }

    /**
     * getNewId
     * Gets new to-do item ID number that is unique in the list
     */
    public Long getNewId() {
        Long highestId = 0L;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() > highestId) {
                highestId = list.get(i).getId();
            }
        }
        return highestId + 1;
    }

    /**
     * deleteItem
     * Deletes a to-do time from the list
     */
    public boolean deleteItem(Long id) {
        boolean found = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getId() == id) {
                found = true;
                list.remove(i);
                break;
            }
        }
        if (!found) {
            return false;
        } else {
            return true;
        }
    }


    /**
     * printList
     * Prints each to-do item in list
     */
    public void printList() {
        StringBuilder listStr = new StringBuilder();
        if (list.isEmpty()) {
            listStr.append("+                                                                  +\n");
            listStr.append("                            (Empty...)\n");
            listStr.append("+                                                                  +\n");
        } else {
            for (ToDoItem item : list) {
                listStr.append(item.getItemRow());
                listStr.append("\n");
            }
        }
        System.out.println(listStr.toString());
    }
}

