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
    // Temporär lista som håller den aktuella användarens värden.
    private static List<Expense> expenseList = new ArrayList<>();

    private static String filename;
    private static String path;
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();
    // Spara användarens fil.
    public static void saveFile() throws IOException {
        // Filnamnet blir ex. "Johan-Johnsson-expense.json"
        filename = BudgetTracker.userList.get(BudgetTracker.activeUser).fileExpense();
        path = "src/main/files/" + filename;
        FileWriter write = new FileWriter(path);
        // Temporär expenseList sparas i aktuella userns filnamn.
        gson.toJson(expenseList, write);
        write.close();
    }

    // Läs in användarens fil, expenseList töms på värden innan ny info läses in.
    public static void readFile() throws IOException {
        filename = BudgetTracker.userList.get(BudgetTracker.activeUser).fileExpense();
        path = "src/main/files/" + filename;
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
        System.out.println("\nEXPENSE");
        if (!expenseList.isEmpty()) {
            System.out.printf("   |%-15s |%-15s |%-15s |%-15s\n", "NAME", "CATEGORY", "AMOUNT", "DATE");
            System.out.println("-".repeat(80));
            for (int i = 0; i < expenseList.size(); i++) {
                System.out.print((i+1) + ". ");
                expenseList.get(i).printExpense();
            }
        } else System.out.println("List is empty.");
    }
    public static void listExpenseMonth(String month) {
        System.out.println("\nEXPENSE " + Month.getMonth(month));
        if (!expenseList.isEmpty()) {
            System.out.printf("   |%-15s |%-15s |%-15s |%-15s\n", "NAME", "CATEGORY", "AMOUNT", "MONTH");
            System.out.println("-".repeat(80));
            for (int i = 0; i < expenseList.size(); i++) {
                if (expenseList.get(i).getMonth().equalsIgnoreCase(month)) {
                    System.out.print((i+1) + ". ");
                    expenseList.get(i).printExpenseMonth(month);
                }
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
        System.out.print("Do you want to add more? yes or ENTER to cancel: ");
        String answer = BudgetTracker.input.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            addExpense();
        }
    }

    // Metod för att ända ett värde på en sparad utgift. Du kan ända namn, summa, datum eller kategori.
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
                        EExpenseCategory category = OtherMethods.inExpenseCategory();
                        expenseList.get(rowChoice).setCategory(category);
                        System.out.println("Category changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("0")) break;

                    else System.out.println("Invalid choice!");
                }
                System.out.print("Do you want to update more? yes or ENTER to cancel: ");
                String answer = BudgetTracker.input.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    updateExpense();
                }
            }
            else if (rowChoice == 0){}
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
            if (rowChoice > 0 && rowChoice < (expenseList.size() + 1)) {
                rowChoice -= 1;
                expenseList.remove(rowChoice);
                System.out.println("Expense removed!");
                saveFile();
                System.out.print("Do you want to remove more? yes or ENTER to cancel: ");
                String answer = BudgetTracker.input.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    removeExpense();
                }
            }
            else if (rowChoice == 0) {}
            else System.out.println("Wrong input!");
        }
        else System.out.println("List is empty!");
    }

    public static List<Expense> getExpenseList() {
        return expenseList;
    }
    // Räkna ihop summan av alla utgifter.
    public static double totalValue() {
        double totalValue = 0;
        for (int i = 0; i < expenseList.size(); i++) {
            totalValue += expenseList.get(i).getAmount();
        }
        return totalValue;
    }
    // Räkna ihop summan av vald månads utgifter.
    public static double totalValueMonth(String month) {
        double totalValue = 0;
        for (int i = 0; i < expenseList.size(); i++) {
            if (expenseList.get(i).getMonth().equalsIgnoreCase(month)) {
                totalValue += expenseList.get(i).getAmount();
            }
        }
        return totalValue;
    }
}
