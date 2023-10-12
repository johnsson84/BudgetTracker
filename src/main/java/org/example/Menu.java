package org.example;

// Alla menyer.
public class Menu {


    private static String menuChoice;


    public Menu() {
        mainMenu();
    }

    private static void mainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nBUDGET TRACKER 3000-o-matic");
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
    private static void incomeMenu() {
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
    private static void expenseMenu() {
        // Add list expense method
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
                ExpenseStorage.updateIncome();
                break;
            case "3":
                ExpenseStorage.removeIncome();
                break;
        }
    }
}
