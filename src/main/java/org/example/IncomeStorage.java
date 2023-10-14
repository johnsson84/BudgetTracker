package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IncomeStorage {

    private static String filename = BudgetTracker.userList.get(BudgetTracker.activeUser).firstName() + ".incomelist.json";
    private static String path = "files/" + filename;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    public IncomeStorage() {

    }

    public static void saveFile() throws IOException {
        FileWriter write = new FileWriter(path);
        gson.toJson(BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList(), write);
        write.close();
    }
    public static void readFile() throws IOException {
        Type type = new TypeToken<ArrayList<Income>>(){}.getType();
        for (int i = 0; i < BudgetTracker.userList.size(); i++) {
            File file = new File(path);
            List<Income> userlist;
            if (file.exists()) {
                FileReader read = new FileReader(path);
                userlist = gson.fromJson(read, type);
                BudgetTracker.userList.get(i).getIncomeList().addAll(userlist);
            }
        }

     }
    public static void listIncome() {
        System.out.println("\nINCOME LIST");
        if (!BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().isEmpty()) {
            for (int i = 0; i < BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().size(); i++) {
                System.out.println((i+1) + ". " + BudgetTracker.userList.get(BudgetTracker.activeUser)
                        .getIncomeList().get(i));
            }
        } else System.out.println("List is empty.");
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
    public static void updateIncome() throws IOException {
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
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("amount")) {
                        System.out.print("Enter new amount: ");
                        double amount = OtherMethods.inputAmount();
                        BudgetTracker.input.nextLine();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().get(rowChoice).setAmount(amount);
                        System.out.println("Amount changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("date")) {
                        System.out.println("Enter new date");
                        String date = OtherMethods.inputDate();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().get(rowChoice).setDate(date);
                        System.out.println("Date changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("category")) {
                        System.out.print("Enter new category: ");
                        EIncomeCategory category = OtherMethods.inIncomeCategory();
                        BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().get(rowChoice).setCategory(category);
                        System.out.println("Category changed!");
                        saveFile();
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
    public static void removeIncome() throws IOException {
        if (!BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().isEmpty()) {
            listIncome();
            System.out.print("Enter row number for the income you want to change 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            if (rowChoice > 0) {
                rowChoice -= 1;
                BudgetTracker.userList.get(BudgetTracker.activeUser).getIncomeList().remove(rowChoice);
                System.out.println("Income removed!");
                saveFile();
            }
        }
        else System.out.println("List is empty!");
     }
}
