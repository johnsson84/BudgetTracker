package org.example;

public class ExpenseStorage {
    public ExpenseStorage() {

    }

    public static void saveFile() {

    }
    public static void readFile() {

    }
    public static void listExpense() {
        System.out.println();
        for (int i = 0; i < BudgetTracker.userList.get(BudgetTracker.activeUser).getExpenseList().size(); i++) {
            System.out.println((i+1) + ". " + BudgetTracker.userList.get(BudgetTracker.activeUser)
                    .getExpenseList().get(i));
        }

    }
    public static void addExpense() {
        System.out.print("Enter name of the expense: ");
        String name = BudgetTracker.input.nextLine();
        System.out.print("Enter amount: ");
        double amount = OtherMethods.inputAmount();
        BudgetTracker.input.nextLine();
        String date = OtherMethods.inputDate();
        EExpenseCategory category = OtherMethods.inExpenseCategory();
        BudgetTracker.userList.get(BudgetTracker.activeUser)
                .getExpenseList().add(new Expense(name, amount, date, category));
        listExpense();
    }
    public static void updateIncome() {
        if (!BudgetTracker.userList.get(BudgetTracker.activeUser).getExpenseList().isEmpty()) {
            listExpense();
            System.out.print("Enter row number for the expense you want to change 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            BudgetTracker.input.nextLine();
            if (rowChoice > 0 && rowChoice < BudgetTracker.userList.get(BudgetTracker.activeUser).getExpenseList().size()) {
                rowChoice -= 1;
                while (true) {
                    System.out.println("What do you want to change?");
                    System.out.println("(name, amount, date, category (0 to cancel))");
                    String changeThis = BudgetTracker.input.nextLine();
                    if (changeThis.equalsIgnoreCase("name")) {
                        System.out.print("Enter new name: ");
                        String name = BudgetTracker.input.nextLine();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getExpenseList().get(rowChoice).setName(name);
                        System.out.println("Name changed!");
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("amount")) {
                        System.out.print("Enter new amount: ");
                        double amount = OtherMethods.inputAmount();
                        BudgetTracker.input.nextLine();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getExpenseList().get(rowChoice).setAmount(amount);
                        System.out.println("Amount changed!");
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("date")) {
                        System.out.println("Enter new date");
                        String date = OtherMethods.inputDate();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getExpenseList().get(rowChoice).setDate(date);
                        System.out.println("Date changed!");
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("category")) {
                        System.out.print("Enter new category: ");
                        EExpenseCategory category = OtherMethods.inExpenseCategory();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getExpenseList().get(rowChoice).setCategory(category);
                        System.out.println("Category changed!");
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("0")) break;

                    else System.out.println("Invalid choice!");
                }
            }
            if (rowChoice == 0){}
            else System.out.println("Must be a number on the list!");
        }
        else System.out.println("List is empty!");

    }
    public static void removeIncome() {
        if (!BudgetTracker.userList.get(BudgetTracker.activeUser).getExpenseList().isEmpty()) {
            listExpense();
            System.out.print("Enter row number for the income you want to change 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            if (rowChoice > 0) {
                rowChoice -= 1;
                BudgetTracker.userList.get(BudgetTracker.activeUser).getExpenseList().remove(rowChoice);
                System.out.println("Income removed!");
            }
        }
        else System.out.println("List is empty!");
    }
}
