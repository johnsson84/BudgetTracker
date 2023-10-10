package org.example;

import java.util.ArrayList;
import java.util.List;

public class Expense extends Transaction {

    List<EExpenseCategory> categories = new ArrayList<>();
    public Expense(double amount, String date) {
        super(amount, date);
    }


}
