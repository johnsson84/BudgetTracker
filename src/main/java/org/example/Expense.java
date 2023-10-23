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

    public void printExpense() {
        System.out.printf("|%-15s |%-15s |%-15s |%-15s\n", super.getName(), category, super.getAmount() + "kr", super.getDate());
    }

}
