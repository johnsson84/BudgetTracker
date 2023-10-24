package org.example;

import java.io.IOException;

// All menus.
public class Menu {
    private static String menuChoice;

    public Menu() throws IOException {
        mainMenu();
    }

    // Main menu
    private static void mainMenu() throws IOException {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nBUDGET TRACKER");
            System.out.println("User: " + BudgetTracker.userList.get(BudgetTracker.activeUser));
            System.out.println("""
                    1. Incomes
                    2. Expenses
                    3. Budget
                    4. Search
                    5. Users
                    6. Quit""");
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
                    budgetMenu();
                    break;
                case "4":
                    OtherMethods.search();
                    break;
                case "5":
                    userMenu();
                    break;
                case "6":
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
        System.out.println("""
                1. Add income
                2. Change income
                3. Remove income
                Press ENTER for main menu""");
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
        System.out.println("""
                1. Add expense
                2. Change expense
                3. Remove expense
                Press ENTER for main menu""");
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
        System.out.println("""
                1. Add user
                2. Change user
                3. Remove user
                Press ENTER for main menu""");
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

    private static void budgetMenu() {
        System.out.println("\nBUDGET MENU");
        System.out.println("""
                1. Total overview
                2. Monthly overview
                Press ENTER for main menu""");
        System.out.print("Enter: ");
        menuChoice = BudgetTracker.input.nextLine();
        switch (menuChoice) {
            case "1":
                IncomeStorage.listIncome();
                ExpenseStorage.listExpense();
                OtherMethods.printBudget();
                System.out.println("Press ENTER to continue...");
                BudgetTracker.input.nextLine();
                break;
            case "2":
                System.out.print("Enter a month (ex January or jan): ");
                String month = OtherMethods.inputMonth();
                System.out.print("Enter year: ");
                String year = BudgetTracker.input.nextLine();
                IncomeStorage.listIncomeMonth(month, year);
                ExpenseStorage.listExpenseMonth(month, year);
                OtherMethods.printBudgetMonth(month, year);
                System.out.println("Press ENTER to continue...");
                BudgetTracker.input.nextLine();
                break;
        }
    }
}
