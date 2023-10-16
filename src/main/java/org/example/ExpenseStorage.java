package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class ExpenseStorage {
    private static List<Expense> expenseList = new ArrayList<>();

    private static String filename;
    private static String path;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    public ExpenseStorage() {

    }

    // Save user specific file.
    public static void saveFile() throws IOException {
        filename = BudgetTracker.userList.get(BudgetTracker.activeUser).fileExpense();
        path = "files/" + filename;
        FileWriter write = new FileWriter(path);
        gson.toJson(expenseList, write);
        write.close();
    }

    // Read all users expense files.
    public static void readFile() throws IOException {
        filename = BudgetTracker.userList.get(BudgetTracker.activeUser).fileExpense();
        path = "files/" + filename;
        Type type = new TypeToken<ArrayList<Expense>>(){}.getType();
        File file = new File(path);
        List<Expense> templist;
        expenseList.clear();
        if (file.exists()) {
            FileReader read = new FileReader(path);
            templist = gson.fromJson(read, type);

            expenseList.addAll(templist);
        }
    }

    // List items in expense list.
    public static void listExpense() {
        System.out.println("\nEXPENSE LIST");
        if (!expenseList.isEmpty()) {
            for (int i = 0; i < expenseList.size(); i++) {
                System.out.println((i+1) + ". " + expenseList.get(i));
            }
        } else System.out.println("List is empty.");

    }

    // Add to expense list
    public static void addExpense() throws IOException {
        System.out.print("Enter name of the expense: ");
        String name = BudgetTracker.input.nextLine();
        System.out.print("Enter amount: ");
        double amount = OtherMethods.inputAmount();
        BudgetTracker.input.nextLine();
        String date = OtherMethods.inputDate();
        EExpenseCategory category = OtherMethods.inExpenseCategory();
        expenseList.add(new Expense(name, amount, date, category));
        listExpense();
        saveFile();
    }

    // Change one value on an item in expense list.
    public static void updateExpense() throws IOException {
        if (!expenseList.isEmpty()) {
            listExpense();
            System.out.print("Enter row number for the expense you want to change 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            BudgetTracker.input.nextLine();
            if (rowChoice > 0 && rowChoice < (expenseList.size() + 1)) {
                rowChoice -= 1;
                while (true) {
                    System.out.println("What do you want to change?");
                    System.out.println("(name, amount, date, category (0 to cancel))");
                    String changeThis = BudgetTracker.input.nextLine();
                    if (changeThis.equalsIgnoreCase("name")) {
                        System.out.print("Enter new name: ");
                        String name = BudgetTracker.input.nextLine();
                        expenseList.get(rowChoice).setName(name);
                        System.out.println("Name changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("amount")) {
                        System.out.print("Enter new amount: ");
                        double amount = OtherMethods.inputAmount();
                        BudgetTracker.input.nextLine();
                        expenseList.get(rowChoice).setAmount(amount);
                        System.out.println("Amount changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("date")) {
                        System.out.println("Enter new date");
                        String date = OtherMethods.inputDate();
                        expenseList.get(rowChoice).setDate(date);
                        System.out.println("Date changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("category")) {
                        System.out.print("Enter new category: ");
                        EExpenseCategory category = OtherMethods.inExpenseCategory();
                        expenseList.get(rowChoice).setCategory(category);
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

    // Remove an item from list.
    public static void removeExpense() throws IOException {
        if (!expenseList.isEmpty()) {
            listExpense();
            System.out.print("Enter row number for the expense you want to remove 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            BudgetTracker.input.nextLine();
            if (rowChoice > 0) {
                rowChoice -= 1;
                expenseList.remove(rowChoice);
                System.out.println("Expense removed!");
                saveFile();
            }
        }
        else System.out.println("List is empty!");
    }

    public static List<Expense> getExpenseList() {
        return expenseList;
    }
}
