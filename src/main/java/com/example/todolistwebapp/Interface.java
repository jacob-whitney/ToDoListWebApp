package com.example.todolistwebapp;
import java.util.Scanner;

/**
 * Software Development II
 * October 19, 2025
 * Interface.java
 * @author Jacob Whitney
 * Description: Users can add a to-do item, delete a to-do item, and
 * view a list of to-do items
 */

public class Interface implements EntityUtil {
    // Attributes
    private ToDoList list;
    private static final Scanner sc = new Scanner(System.in);

    // Constructors
    public Interface() {
        list = EntityUtil.getToDoItemsFromDb();
        printStart();
    }

    // Custom Methods
    /**
     * printStart
     * Prints components of start page to console
     */
    public void printStart() {
        printHeader();
        list.printList();
        printMenuOptions();
        String input = sc.nextLine();
        getMenuActions(input);
    }

    /**
     * printHeader
     * Prints header for start page to console
     */
    public void printHeader() {
        System.out.println();
        System.out.println("********************************************************************");
        System.out.println("* - - - - - - - - - - - - - TO DO LIST - - - - - - - - - - - - - - *");
        System.out.println("********************************************************************");
    }
    /**
     * printMenuOptions
     * Prints interactive options for users
     */
    public void printMenuOptions() {
        System.out.print("Type 'a' to add an item, 'd' to delete, or 'q' to quit: ");
    }

    /**
     * getMenuActions
     * Using switch case, stores actions taken when a user chooses a menu option
     */
    public void getMenuActions(String input) {
        while (true) {
            switch (input) {
                case "a":
                    System.out.print("Describe your new to-do item: ");
                    String desc = sc.nextLine();
                    ToDoItem newItem = new ToDoItem(desc);
                    if (EntityUtil.addItemToDb(newItem)) {
                        list.addItem(newItem);
                    } else {
                        System.out.println("> Item could not be added to the list");
                    }

                    printStart();
                    break;
                case "d":
                    if ( list.getSize() > 0 ) {
                        System.out.print("Enter the ID of the to-do item to delete: ");
                        String id = sc.nextLine();
                        Long longId = 0L;
                        try {
                            longId = (long) Integer.parseInt(id);
                        } catch (NumberFormatException e) {
                            System.out.println("> Not a valid ID");
                            printStart();
                            break;
                        }

                        if (EntityUtil.deleteItemFromDb(longId)) {
                            list.deleteItem(longId);
                        } else {
                            System.out.println("> Item could not be deleted from the list");
                        }
                        printStart();
                        break;
                    } else {
                        System.out.println("> To-do list is empty, no items to delete");
                        printStart();
                        break;
                    }

                case "q":
                    EntityUtil.closeTransactionManager();
                    System.out.println("Goodbye!");
                    System.exit(0);
                default:
                    System.out.println("> Invalid input, please try again");
                    printStart();
                    break;
            }
        }
    }
}

