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

    // Polymorphism
    // Denna metoden skriver över super klassens metod, den ser dock likadan ut som den overridade metoden i
    // Expense.java och det är för att jag har byggd utskriften av budget listan på ett visst sätt.

    // Exempel på vad som hade skiljt denna overriden från expense hade varit att haft såhär "Income: " + category
    // där category står, likaså "Expense: " + category i Expense.java overriden.
    // Mer om det i dokumentet.
     @Override
    public void printTransaction() {
        System.out.printf("|%-15s |%-15s |%-15s |%-15s\n", super.getName(), category, super.getAmount() + " kr", super.getDate());
    }
    // Utkommenterad i syfte att visa hur den kunde skiljt sig från Expense.printTransaction()
    /*@Override
    public void printTransaction() {
        System.out.printf("|%-15s |%-15s |%-15s |%-15s\n", super.getName(), "Income/Expense: " + category, super.getAmount() + " kr", super.getDate());
    }
    */
}
