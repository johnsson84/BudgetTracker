package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

// Ställe för att ha extra metoder som jag annars antagligen hade kladdat ner main med.
public class OtherMethods {
    private static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    // Metod för att välja index i en lista eller en annan låg siffra.
    // Short för mindre minnesavtryck. Sållar ut bokstäver och negativa tal.
    public static short shortNumber() {
        short shortNumber = 0;
        while (true) {
            try {
                short number = BudgetTracker.input.nextShort();

                if (number >= 0) {
                    shortNumber = number;
                    break;
                }
                else
                    System.out.println("Cant be negative, try again...");
            }
            catch (InputMismatchException e) {
                System.out.println("Cant be letters, try again...");
                BudgetTracker.input.nextLine();
            }
        }

        return shortNumber;
    }

    //För inkomst och utgifts summor
    public static double inputAmount() {
        double sum = 0;
        while (true) {
            try {
                double number = BudgetTracker.input.nextDouble();

                sum = number;
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Cant be letters, try again...");
                BudgetTracker.input.nextLine();
            }
        }

        return sum;
    }
    // Metod för att returnera ett datum, standard är dagens datum men metoden tillåter mauell inmatning.
    public static String inputDate() {
        LocalDate todaysDate = LocalDate.now();
        String date = todaysDate.toString(); // ställ in dagens datum
        while (true) {
            System.out.print("Is this correct date (" + date + ") yes or no: ");
            String answer = BudgetTracker.input.nextLine();
            if (answer.equalsIgnoreCase("no")) {
                // Mata in datum manuellt och kolla så man håller sig inom ramarna
                // för rimliga år, månader och dagar.
                String year;
                String month;
                String day;
                while (true) {
                    System.out.print("Enter year: ");
                    short year1 = shortNumber();
                    if (year1 > 2015 && year1 < 2050) {
                        year = Short.toString(year1);
                        break;
                    }
                    else System.out.println("Must be between 2015-2050");
                }
                while (true) {
                    System.out.print("Enter month: ");
                    short month1 = shortNumber();
                    if (month1 > 0 && month1 < 13) {
                        month = Short.toString(month1);
                        break;
                    }
                    else System.out.println("Must be between 1-12");
                }
                while (true) {
                    System.out.print("Enter day: ");
                    short day1 = shortNumber();
                    if (day1 > 0 && day1 < 32) {
                        day = Short.toString(day1);
                        break;
                    }
                    else System.out.println("Must be between 1-31");

                }
                BudgetTracker.input.nextLine();
                // kolla om det stämmer efter manuell inmatning, om inte börja om.
                System.out.print("Is this correct (" + year + "-" + month + "-" + day + ")? yes or no: ");
                answer = BudgetTracker.input.nextLine();
                if (answer.equalsIgnoreCase("yes")) {
                    date = year + "/" + month + "/" + day;
                    break;
                }
                else System.out.println("Then try again...");
            }
            if (answer.equalsIgnoreCase("yes")) {
                break;
            }

        }
        return date;
    }
    // Metod för att välja kategori för inkomst
    public static EIncomeCategory inIncomeCategory() {
        for (EIncomeCategory cat : EIncomeCategory.values()) {
            System.out.println(cat);
        }
        EIncomeCategory category;
        while (true) {
            try {
                System.out.print("Enter category from the list: ");
                String catInput = BudgetTracker.input.nextLine();
                category = EIncomeCategory.valueOf(catInput.toUpperCase());
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Category not found");
            }
        }
        return category;
    }
    // Metod för att välja kategori för utgift
    public static EExpenseCategory inExpenseCategory() {
        for (EExpenseCategory cat : EExpenseCategory.values()) {
            System.out.println(cat);
        }
        EExpenseCategory category;
        while (true) {
            try {
                System.out.print("Enter category from the list: ");
                String catInput = BudgetTracker.input.nextLine();
                category = EExpenseCategory.valueOf(catInput.toUpperCase());
                break;
            }
            catch (IllegalArgumentException e) {
                System.out.println("Category not found");
            }
        }
        return category;
    }
    // Metod för att lägga till en default user om user listan är tom.
    public static void addDefaultUser() throws IOException {
        if (BudgetTracker.userList.isEmpty()) {
            BudgetTracker.userList.add(new User("default", "user"));
            saveUser();
        }
    }

    // List users.
    public static void listUsers() {
        System.out.println("\nUSERS");
        for (int i = 0; i < BudgetTracker.userList.size(); i++) {
            System.out.println((i+1) + ". " + BudgetTracker.userList.get(i).getFirstName() +
                    " " + BudgetTracker.userList.get(i).getLastName());
        }
    }

    // Save users
    public static void saveUser() throws IOException {
        FileWriter write = new FileWriter("files/userlist.json");
        gson.toJson(BudgetTracker.userList, write);
        write.close();
    }

    // Read in users
    public static void readUsers() throws IOException {

        Type type = new TypeToken<ArrayList<User>>() {}.getType();
        File file = new File("files/userlist.json");
        List<User> users;
        if (file.exists()) {
            FileReader read = new FileReader(file);
            users = gson.fromJson(read, type);
            BudgetTracker.userList.addAll(users);
        }
    }

    // Add user
    public static void addUser() throws IOException {
        System.out.print("Enter first name: ");
        String name = BudgetTracker.input.nextLine();
        System.out.print("Enter last name: ");
        String lastname = BudgetTracker.input.nextLine();
        BudgetTracker.userList.add(new User(name, lastname));
        System.out.println("User created!");
        saveUser();
        System.out.println("Do you want to change from current user?");
        System.out.println("yes or ENTER to cancel");
        String answer = BudgetTracker.input.nextLine();
        if (answer.equalsIgnoreCase("yes")) {
            changeUser();
        }
    }

    // Change user
    public static void changeUser() throws IOException {
        listUsers();
        while (true) {
            System.out.print("\nChange to user (number): ");
            short userNumber = shortNumber();
            BudgetTracker.input.nextLine();
            if (userNumber > 0 && userNumber < (BudgetTracker.userList.size() + 1)) {
                userNumber -= 1;
                BudgetTracker.activeUser = (userNumber);
                IncomeStorage.readFile();
                ExpenseStorage.readFile();
                System.out.println("User changed!");
                break;
            }
            else System.out.println("Wrong user input, try again...");
        }

    }

    public static void removeUser() throws IOException {
        if (!BudgetTracker.userList.isEmpty()) {
            listUsers();
            System.out.print("Enter row number for the income you want to change 0 to cancel: ");
            short rowChoice = OtherMethods.shortNumber();
            BudgetTracker.input.nextLine();
            if (rowChoice > 0) {
                rowChoice -= 1;
                while (true) {
                    System.out.print("Are you sure? This also deletes users files (yes or no): ");
                    String deleteAnswer = BudgetTracker.input.nextLine().toLowerCase();
                    if (deleteAnswer.equals("yes")) {
                        File incomeFile = new File("files/" + BudgetTracker.userList.get(rowChoice).fileIncome());
                        File expenseFile = new File("files/" + BudgetTracker.userList.get(rowChoice).fileExpense());
                        incomeFile.delete();
                        expenseFile.delete();
                        IncomeStorage.getIncomeList().clear();
                        ExpenseStorage.getExpenseList().clear();
                        BudgetTracker.userList.remove(rowChoice);
                        System.out.println("User removed!");
                        BudgetTracker.activeUser = 0;
                        saveUser();
                        break;
                    }
                    else if (deleteAnswer.equals("no")) break;
                    else System.out.println("Wrong input!");
                }

            }
        }
        else System.out.println("List is empty!");
    }

    public static void printBudget() {
        System.out.println("\nBUDGET OVERVIEW");
        System.out.println("Income total: " + IncomeStorage.totalValue() + "kr.");
        System.out.println("Expense total: " + ExpenseStorage.totalValue() + "kr.");
        System.out.println("TOTAL: " + (IncomeStorage.totalValue() - ExpenseStorage.totalValue()) + "kr.");
    }
}


