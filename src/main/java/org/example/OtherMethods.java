package org.example;

import java.util.InputMismatchException;

// Ställe för att ha extra metoder som jag annars hade kladdat ner main med.
public class OtherMethods {

    // Metod för att välja index i en lista. Short för mindre minnesavtryck. Sållar ut bokstäver och negativa tal.
    public short menuNumber() {
        short menuNumber = 0;
        while (true) {
            try {
                short number = BudgetTracker.input.nextShort();
                if (number >= 0) {
                    menuNumber = number;
                    break;
                }
                else
                    System.out.println("Cant be negative...");
            }
            catch (InputMismatchException e) {
                System.out.println("Cant be letters...");
            }
        }
        return menuNumber;
    }
}
