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
        boolean isRunning = true;
        while (isRunning) {
            OtherMethods.addDefaultUser();
            System.out.println("\nBUDGET TRACKER");
            System.out.println("User: " + BudgetTracker.userList.get(BudgetTracker.activeUser));
            System.out.println("1. Incomes" +
                               "\n2. Expenses" +
                               "\n3. Show budget" +
                               "\n4. Users" +
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
                    // System.out.println("Not implemented yet");
                    userMenu();
                    break;
                case "5":
                    System.out.println("quitting...");
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

    // User menu.
    private static void userMenu() throws IOException {
        System.out.println("\nUSER MENU");
        System.out.println("1. Add user" +
                         "\n2. Change user" +
                         "\n3. Remove user");
        System.out.print("Enter: ");
        menuChoice = BudgetTracker.input.nextLine();
        switch (menuChoice) {
            case "1":
                OtherMethods.addUser();
                break;
            case "2":
                OtherMethods.changeUser();
                break;
            case "3":
                OtherMethods.removeUser();
                break;
        }
    }
}
