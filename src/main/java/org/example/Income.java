package org.example;

public class Income extends Transaction{

    EIncomeCategory category;

    public Income(String name,double amount, String date, EIncomeCategory category) {
        super(name, amount, date);
        this.category = category;
    }


    public EIncomeCategory category() {
        return category;
    }

    @Override
    public String toString() {
        return "Income{" +
                "category=" + category +
                '}' + super.toString();
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }
}
