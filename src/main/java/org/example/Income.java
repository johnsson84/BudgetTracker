package org.example;

public class Income extends Transaction{

    EIncomeCategory category;

    public Income(double amount, String date, EIncomeCategory category) {
        super(amount, date);
        this.category = category;
    }
}
