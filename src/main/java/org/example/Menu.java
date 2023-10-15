package org.example;

import java.io.IOException;

// A class with all menus.
public class Menu {
    private static String menuChoice;

    public Menu() throws IOException {
        mainMenu();
    }

    // Main menu
    private static void mainMenu() throws IOException {
        IncomeStorage.readFile(); // Läser in filer om  dem finns.
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nBUDGET TRACKER");
            System.out.println("User: " + BudgetTracker.userList.get(BudgetTracker.activeUser));
            System.out.println("1. Incomes menu" +
                               "\n2. Expenses menu" +
                               "\n3. Show budget" +
                               "\n4. Users menu" +
                               "\n5. Quit");
            System.out.print("Enter: ");
            menuChoice = BudgetTracker.input.nextLine();
            switch (menuChoice) {
                case "1":
                    incomeMenu();
                    break;
                case "2":
                    expenseMenu();
                    break;
                case "3":
                    IncomeStorage.listIncome();
                    ExpenseStorage.listExpense();
                    break;
                case "4":

                    break;
                case "5":
                    System.out.println("Saving...quits...");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Please choose from the menu...");
            }
        }
    }
    // Income menu
    private static void incomeMenu() throws IOException {
        // Add list income method
        System.out.println("\nINCOME MENU");
        System.out.println("1. Add income" +
                           "\n2. Change income" +
                           "\n3. Remove income");
        System.out.print("Enter: ");
        menuChoice = BudgetTracker.input.nextLine();
        switch (menuChoice) {
            case "1":
                IncomeStorage.addIncome();
                break;
            case "2":
                IncomeStorage.updateIncome();
                break;
            case "3":
                IncomeStorage.removeIncome();
                break;
        }
    }
    // Expense menu.
    private static void expenseMenu() throws IOException {
        System.out.println("\nEXPENSE MENU");
        System.out.println("1. Add expense" +
                "\n2. Change expense" +
                "\n3. Remove expense");
        System.out.print("Enter: ");
        menuChoice = BudgetTracker.input.nextLine();
        switch (menuChoice) {
            case "1":
                ExpenseStorage.addExpense();
                break;
            case "2":
                ExpenseStorage.updateExpense();
                break;
            case "3":
                ExpenseStorage.removeExpense();
                break;
        }
    }
}
