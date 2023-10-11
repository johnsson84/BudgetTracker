package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BudgetTracker {
    public static Scanner input = new Scanner(System.in);
    public static List<User> userList = new ArrayList<>();

    public static void main(String[] args) {
        userList.add(new User("defaultuser"));
        Menu menu = new Menu();
    }
}