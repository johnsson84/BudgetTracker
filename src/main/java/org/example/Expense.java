package org.example;

public class Expense extends Transaction {

    EExpenseCategory category;

    public Expense(double amount, String date, EExpenseCategory category) {
        super(amount, date);
        this.category = category;
    }
}
