package org.example;

public class Expense extends Transaction {

    private EExpenseCategory category;

    public Expense(String name, double amount, String date, EExpenseCategory category) {
        super(name, amount, date);
        this.category = category;
    }

    public void setCategory(EExpenseCategory category) {
        this.category = category;
    }

    // Polymorphism, se Income.java och dokument.
    @Override
    public void printTransaction() {
        System.out.printf("|%-15s |%-15s |%-15s |%-15s\n", super.getName(), category, super.getAmount() + " kr", super.getDate());
    }

}
