package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class User {
    private String firstName;
    private String lastName;
    private List<Income> incomeList;
    private List<Expense> expenseList;


    public User(String firstName, String lastName) throws FileNotFoundException {
        this.firstName = firstName;
        this.lastName = lastName;
        incomeList = new ArrayList<>();
        expenseList = new ArrayList<>();
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public List<Expense> getExpenseList() {
        return expenseList;
    }


    public String firstName() {
        return firstName;
    }

    public String lastName() {
        return lastName;
    }
}
