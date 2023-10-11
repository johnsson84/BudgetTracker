package org.example;

public class Expense extends Transaction {

    EExpenseCategory category;

    public Expense(String name, double amount, String date, EExpenseCategory category) {
        super(name, amount, date);
        this.category = category;
    }
}
