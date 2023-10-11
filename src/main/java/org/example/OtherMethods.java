package org.example;

import java.time.LocalDate;
import java.util.InputMismatchException;

// Ställe för att ha extra metoder som jag annars hade kladdat ner main med.
public class OtherMethods {

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
                    System.out.println("Cant be negative...");
            }
            catch (InputMismatchException e) {
                System.out.println("Cant be letters...");
            }
        }
        return shortNumber;
    }

    //För inkomst och utgifts summor
    public static double inputAmount() {
        double menuNumber = 0;
        while (true) {
            try {
                double number = BudgetTracker.input.nextDouble();
                menuNumber = number;
                break;
            }
            catch (InputMismatchException e) {
                System.out.println("Cant be letters...");
            }
        }
        return menuNumber;
    }
    // Metod för att returnera ett datum, standard är dagen datum men metoden tillåter mauell inmatning.
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


}
