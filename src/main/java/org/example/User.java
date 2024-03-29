package org.example;

public class User {
    private String firstName;
    private String lastName;

    public User(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }


    public String getFirstName() {
        return firstName;
    }


    public String getLastName() {
        return lastName;
    }

    public String fileIncome() {
        return firstName + "-" + lastName + "-income.json";
    }

    public String fileExpense() {
        return firstName + "-" + lastName + "-expense.json";
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}
