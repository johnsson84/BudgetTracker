package org.example;

public class IncomeStorage {

    public IncomeStorage() {

    }

    private void saveFile() {

    }
    private void readFile() {

    }
    public static void listIncome() {
        for (int i = 0; i < BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().size(); i++) {
            System.out.println(BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().get(i).toString());
        }

    }
    public static void addIncome() {
        System.out.print("Enter name of the income: ");
        String name = BudgetTracker.input.nextLine();
        System.out.print("Enter amount: ");
        double amount = OtherMethods.inputAmount();
        BudgetTracker.input.nextLine();
        String date = OtherMethods.inputDate();
        for (EIncomeCategory cat : EIncomeCategory.values()) {
            System.out.println(cat);
        }
        EIncomeCategory category;
        while (true) {
            try {
                System.out.println("Enter category from the list: ");
                String catInput = BudgetTracker.input.nextLine();
                category = EIncomeCategory.valueOf(catInput);
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Category not found");
            }
        }
        BudgetTracker.userList.get(BudgetTracker.activeUser)
                .getIncomeList().add(new Income(name, amount, date, category));
        listIncome();
    }
    private void updateIncome() {

    }
    private void removeIncome() {

    }
}
