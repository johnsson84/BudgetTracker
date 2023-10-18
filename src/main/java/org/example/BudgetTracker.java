package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetTracker {
    public static Scanner input = new Scanner(System.in);
    public static List<User> userList = new ArrayList<>();
    public static short activeUser = 0;

    public static void main(String[] args) throws IOException {
        OtherMethods.readUsers();
        OtherMethods.addDefaultUser();
        // Läser in inkomst och utgift filer. I detta fallet läser den in till första usern på listan som är default
        // user om man inte har tagit bort den och en annan user finns på plats 0.
        IncomeStorage.readFile();
        ExpenseStorage.readFile();
        Menu menu = new Menu(); // Leder till klass med alla menyer
    }
}