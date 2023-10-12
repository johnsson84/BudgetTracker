package org.example;

public class Income extends Transaction{

    private EIncomeCategory category;

    public Income(String name,double amount, String date, EIncomeCategory category) {
        super(name, amount, date);
        this.category = category;
    }


    public EIncomeCategory category() {
        return category;
    }

    public void setCategory(EIncomeCategory category) {
        this.category = category;
    }

    @Override
    public String toString() {
        /*return "{" +
                "category=" + category +
                ", " + super.toString();

         */
        return super.toString() + " |Category: " + category;
    }
}
