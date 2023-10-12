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

    public String name() {
        return name;
    }

    public double amount() {
        return amount;
    }

    public String date() {
        return date;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "name='" + name + '\'' +
                ", amount=" + amount +
                ", date='" + date + '\'' +
                '}';
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
}
