package org.example;

public class Transaction {
    private String name;
    private double amount;
    private String date;

    public Transaction(String name, double amount, String date) {
        this.name = name;
        this.amount = amount;
        this.date = date;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }
    public String getMonth() {
        return date.substring(5,7);
    }
    public void printTransaction() {
        System.out.printf("|%-15s |%-15s |%-15s\n", name, amount + " kr", date);
    }
}
