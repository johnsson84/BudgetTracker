package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.InputMismatchException;

// Ställe för att ha extra metoder som jag annars hade kladdat ner main med.
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
                System.out.print("Is this correct (" + year + "/" + month + "/" + day + ")? yes or no: ");
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
                System.out.println("Enter category from the list: ");
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
                System.out.println("Enter category from the list: ");
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

    // List users.
    public static void listUsers() {
        System.out.println("\nUSERS");
        for (int i = 0; i < BudgetTracker.userList.size(); i++) {
            System.out.println((i+1) + ". " + BudgetTracker.userList.get(i).firstName() +
                    BudgetTracker.userList.get(i).lastName());
        }
    }

    // Add user
    public static void addUser() throws FileNotFoundException {
        System.out.print("Enter name: ");
        String name = BudgetTracker.input.nextLine();
        System.out.print("Enter lastname: ");
        String lastname = BudgetTracker.input.nextLine();
        BudgetTracker.userList.add(new User(name, lastname));
        System.out.println("User created!");
    }

    // Change user
    public static void changeUser() {
        while (true) {
            System.out.print("\nChange to user (number): ");
            short userNumber = shortNumber();
            BudgetTracker.input.nextLine();
            if (userNumber > 0 && userNumber < (BudgetTracker.userList.size() + 1)) {
                userNumber -= 1;
                BudgetTracker.activeUser = (userNumber);
                System.out.println("User changed!");
                break;
            }
            else System.out.println("Wrong user input, try again...");
        }

    }

}
