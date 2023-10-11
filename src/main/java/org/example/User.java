package org.example;

import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private List<String> incomeList;
    private List<String> expenseList;


    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User(String firstName) {
        this.firstName = firstName;
    }
}
