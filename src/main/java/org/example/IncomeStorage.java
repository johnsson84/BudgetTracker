package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class IncomeStorage {

    // Temporär lista som håller den aktuella användarens värden.
    private static List<Income> incomeList = new ArrayList<>();

    private static String filename;
    private static String path;

    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Spara användarens fil.
    public static void saveFile() throws IOException {
        // Filnamnet blir ex. "Johan-Johnsson-income.json"
        filename = BudgetTracker.userList.get(BudgetTracker.activeUser).fileIncome();
        path = "src/main/files/" + filename;
        FileWriter write = new FileWriter(path);
        // Temporär incomeList sparas i aktuella användarens filnamn.
        gson.toJson(incomeList, write);
        write.close();
    }

    // Läs in användarens fil, incomeList töms på värden innan ny info läses in, om filen finns.
    public static void readFile() throws IOException {
        filename = BudgetTracker.userList.get(BudgetTracker.activeUser).fileIncome();
        path = "src/main/files/" + filename;
        Type type = new TypeToken<ArrayList<Income>>(){}.getType();
        File file = new File(path);
        List<Income> templist;
        incomeList.clear();
        if (file.exists()) {
            FileReader read = new FileReader(path);
            templist = gson.fromJson(read, type);
            incomeList.addAll(templist);
        }
    }

    // Printar ut all inkomst om listan inte är tom.
    public static void listIncome() {
        System.out.println("\nINCOME ALL");
        if (!incomeList.isEmpty()) {
            System.out.printf("   |%-15s |%-15s |%-15s |%-15s\n", "NAME", "CATEGORY", "AMOUNT", "DATE");
            System.out.println("-".repeat(80));
            for (int i = 0; i < incomeList.size(); i++) {
                System.out.print((i+1) + ". ");
                incomeList.get(i).printTransaction();
            }
        } else System.out.println("List is empty.");
    }

    // Samma som ovan fast listar efter angiven månad och år.
    public static void listIncomeMonth(String month, String year) {
        System.out.println("\nINCOME " + Month.getMonth(month) + " " + year);
        if (!incomeList.isEmpty()) {
            System.out.printf("   |%-15s |%-15s |%-15s |%-15s\n", "NAME", "CATEGORY", "AMOUNT", "MONTH");
            System.out.println("-".repeat(80));
            for (int i = 0; i < incomeList.size(); i++) {
                if (incomeList.get(i).getMonth().equalsIgnoreCase(month) && incomeList.get(i).getYear().equals(year)) {
                    System.out.print("   ");
                    incomeList.get(i).printTransaction();
                }
            }
        } else System.out.println("List is empty.");
    }

    // Lägga till en inkomst.
    public static void addIncome() throws IOException {
        System.out.print("Enter name of the income: ");
        String name = BudgetTracker.input.nextLine();
        System.out.print("Enter amount: ");
        double amount = OtherMethods.inputAmount();
        BudgetTracker.input.nextLine();
        String date = OtherMethods.inputDate(); // Datum metod
        EIncomeCategory category = OtherMethods.inIncomeCategory(); // Kategori metod
        incomeList.add(new Income(name, amount, date, category));
        listIncome();
        saveFile();
        System.out.print("Do you want to add more? Yes or ENTER to cancel: ");
        String answer = BudgetTracker.input.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            addIncome();
        }
    }

    // Metod för att ända ett värde på en sparad inkomst. Du kan ända namn, summa, datum eller kategori.
    public static void updateIncome() throws IOException {
        if (!incomeList.isEmpty()) {
            listIncome();
            System.out.print("Enter row number for the income you want to change 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            BudgetTracker.input.nextLine();
            if (rowChoice > 0 && rowChoice < (incomeList.size() + 1)) {
                rowChoice -= 1;
                while (true) {
                    System.out.println("What do you want to change?");
                    System.out.println("(name, amount, date, category (0 to cancel))");
                    String changeThis = BudgetTracker.input.nextLine();
                    if (changeThis.equalsIgnoreCase("name")) {
                        System.out.print("Enter new name: ");
                        String name = BudgetTracker.input.nextLine();
                        incomeList.get(rowChoice).setName(name);
                        System.out.println("Name changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("amount")) {
                        System.out.print("Enter new amount: ");
                        double amount = OtherMethods.inputAmount();
                        BudgetTracker.input.nextLine();
                        incomeList.get(rowChoice).setAmount(amount);
                        System.out.println("Amount changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("date")) {
                        System.out.println("Enter new date");
                        String date = OtherMethods.inputDate();
                        incomeList.get(rowChoice).setDate(date);
                        System.out.println("Date changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("category")) {
                        EIncomeCategory category = OtherMethods.inIncomeCategory();
                        incomeList.get(rowChoice).setCategory(category);
                        System.out.println("Category changed!");
                        saveFile();
                        break;
                    }
                    if (changeThis.equalsIgnoreCase("0")) break;

                    else System.out.println("Invalid choice!");
                }
                System.out.print("Do you want to update more? Yes or ENTER to cancel: ");
                String answer = BudgetTracker.input.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    updateIncome();
                }
            }
            else if (rowChoice == 0){} // Ska vara tom body, trycker man 0 för att avbryta så hamnar man här.
            else System.out.println("Must be a number on the list!");
        }
        else System.out.println("List is empty!");
    }

    // Ta bort en inkomst från listan.
    public static void removeIncome() throws IOException {
        if (!incomeList.isEmpty()) {
            listIncome();
            System.out.print("Enter row number for the income you want to remove 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            BudgetTracker.input.nextLine();
            if (rowChoice > 0 && rowChoice < (incomeList.size() + 1)) {
                rowChoice -= 1;
                incomeList.remove(rowChoice);
                System.out.println("Income removed!");
                saveFile();
                System.out.print("Do you want to remove more? Yes or ENTER to cancel: ");
                String answer = BudgetTracker.input.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    removeIncome();
                }
            }
            else if (rowChoice == 0) {} // Ska vara tom body, trycker man 0 för att avbryta så hamnar man här.
            else System.out.println("Wrong input!");
        }
        else System.out.println("List is empty!");

     }

    public static List<Income> getIncomeList() {
        return incomeList;
    }

    // Räkna ihop summan av alla inkomster.
    public static double totalValue() {
        double totalValue = 0;
        for (int i = 0; i < incomeList.size(); i++) {
            totalValue += incomeList.get(i).getAmount();
        }
        return totalValue;
    }
    // Räkna ihop summan av vald månads inkomster.
    public static double totalValueMonth(String month, String year) {
        double totalValue = 0;
        for (int i = 0; i < incomeList.size(); i++) {
            if (incomeList.get(i).getMonth().equalsIgnoreCase(month) && incomeList.get(i).getYear().equals(year)) {
                totalValue += incomeList.get(i).getAmount();
            }
        }
        return totalValue;
    }
}
