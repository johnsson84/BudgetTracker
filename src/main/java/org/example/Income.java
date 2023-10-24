package org.example;

public class Income extends Transaction {

    private EIncomeCategory category;

    public Income(String name, double amount, String date, EIncomeCategory category) {
        super(name, amount, date);
        this.category = category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }

    /*public void printIncome() {
        System.out.printf("|%-15s |%-15s |%-15s |%-15s\n", super.getName(), category, super.getAmount() + " kr", super.getDate());
    }
    */
}
