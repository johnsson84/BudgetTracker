package org.example;

public class Income extends Transaction{

    EIncomeCategory category;

    public Income(String name,double amount, String date, EIncomeCategory category) {
        super(name, amount, date);
        this.category = category;
    }
}
