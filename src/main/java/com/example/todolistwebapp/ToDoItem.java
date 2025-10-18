package com.example.todolistwebapp;

import jakarta.persistence.*;

/**
 * Software Development II
 * October 19, 2025
 * ToDoItem.java
 * @author Jacob Whitney
 */

@Entity
@Table(name = "items")
public class ToDoItem {
    // Attributes
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String description = "";

    // Constructor
    public ToDoItem(String description) {
        this.description = description;
    }

    public ToDoItem() {
        this.description = "";
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Custom Methods
    /**
     * getItemRow
     * Returns string displaying a to-do list item's
     * ID number, checckbox for completion, and description
     */
    public String getItemRow() {
        StringBuilder row = new StringBuilder();

        row.append(String.valueOf(this.getId()));
        if (id <= 9) {
            row.append("     ");
        } else if (id > 9) {
            row.append("    ");
        } else if (id > 99) {
            row.append("   ");
        } else if (id > 999) {
            row.append("  ");
        } else if (id > 9999) {
            row.append(" ");
        }

        row.append(description);

        return row.toString();
    }


}
