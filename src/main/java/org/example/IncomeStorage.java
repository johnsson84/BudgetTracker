package org.example;

import com.google.gson.Gson;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IncomeStorage {

    public IncomeStorage() {

    }

    public static void saveFile() throws IOException {
        Gson gson = new Gson();
        String filename = BudgetTracker.userList.get(BudgetTracker.activeUser).firstName() + ".incomelist.json";
        String path = "files/" + filename;
        FileWriter write = new FileWriter(path);
        gson.toJson(BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList(), write);
        write.close();
    }
    public static void readFile() throws FileNotFoundException {
        Gson gson = new Gson();
        for (int i = 0; i < BudgetTracker.userList.size(); i++) {
            String filename = BudgetTracker.userList.get(i).firstName() + ".incomelist.json";
            String path = "files/" + filename;
            File file = new File(path);
            List<Income> userlist = new ArrayList<>();
            if (file.exists()) {
                FileReader read = new FileReader(path);
                userlist = gson.fromJson(read, ArrayList.class);
                BudgetTracker.userList.get(i).getIncomeList().addAll(userlist);
            }
        }

     }
    public static void listIncome() {
        System.out.println();
        for (int i = 0; i < BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().size(); i++) {
            System.out.println((i+1) + ". " + BudgetTracker.userList.get(BudgetTracker.activeUser)
                    .getIncomeList().get(i));
        }

    }
    public static void addIncome() throws IOException {
        System.out.print("Enter name of the income: ");
        String name = BudgetTracker.input.nextLine();
        System.out.print("Enter amount: ");
        double amount = OtherMethods.inputAmount();
        BudgetTracker.input.nextLine();
        String date = OtherMethods.inputDate();
        EIncomeCategory category = OtherMethods.inIncomeCategory();
        BudgetTracker.userList.get(BudgetTracker.activeUser)
                .getIncomeList().add(new Income(name, amount, date, category));
        listIncome();
        saveFile();
    }
    public static void updateIncome() {
        if (!BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().isEmpty()) {
            listIncome();
            System.out.print("Enter row number for the income you want to change 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            BudgetTracker.input.nextLine();
            if (rowChoice > 0 && rowChoice < BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().size()) {
                rowChoice -= 1;
                while (true) {
                    System.out.println("What do you want to change?");
                    System.out.println("(name, amount, date, category (0 to cancel))");
                    String changeThis = BudgetTracker.input.nextLine();
                    if (changeThis.equalsIgnoreCase("name")) {
                        System.out.print("Enter new name: ");
                        String name = BudgetTracker.input.nextLine();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().get(rowChoice).setName(name);
                        System.out.println("Name changed!");
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("amount")) {
                        System.out.print("Enter new amount: ");
                        double amount = OtherMethods.inputAmount();
                        BudgetTracker.input.nextLine();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().get(rowChoice).setAmount(amount);
                        System.out.println("Amount changed!");
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("date")) {
                        System.out.println("Enter new date");
                        String date = OtherMethods.inputDate();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().get(rowChoice).setDate(date);
                        System.out.println("Date changed!");
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("category")) {
                        System.out.print("Enter new category: ");
                        EIncomeCategory category = OtherMethods.inIncomeCategory();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().get(rowChoice).setCategory(category);
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
        if (!BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().isEmpty()) {
            listIncome();
            System.out.print("Enter row number for the income you want to change 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            if (rowChoice > 0) {
                rowChoice -= 1;
                BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().remove(rowChoice);
                System.out.println("Income removed!");
            }
        }
        else System.out.println("List is empty!");
     }
}
