package org.example;

public enum EIncomeCategory {
    SALARY,
    OTHER;

    public String toString() {
        switch (this) {
            case SALARY: return "SALARY";
            case OTHER: return "OTHER";
        }
        return null;
    }
}
