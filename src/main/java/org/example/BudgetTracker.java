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
        // Reads income and expense files.
        IncomeStorage.readFile();
        ExpenseStorage.readFile();
        Menu menu = new Menu();
    }
}