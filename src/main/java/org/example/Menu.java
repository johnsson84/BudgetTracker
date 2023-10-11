package org.example;

public class Menu {

    private static String menuChoice;


    public Menu() {
        mainMenu();
    }

    private static void mainMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\nBUDGET TRACKER 3000-o-matic");
            System.out.println("1. Incomes" +
                    "\n2. Expenses" +
                    "\n3. Show budget" +
                    "\n4. Change user" +
                    "\n5. Quit");
            menuChoice = BudgetTracker.input.nextLine();
            switch (menuChoice) {
                case "1":
                    break;
                case "2":
                    break;
                case "3":
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
}
