package org.example;

public enum EExpenseCategory {
    CLOTHES,
    FOOD,
    GAS,
    HOUSEHOLD,
    OTHER;

    public String toString() {
        switch (this) {
            case CLOTHES: return "CLOTHES";
            case FOOD: return "FOOD";
            case GAS: return "GAS";
            case HOUSEHOLD: return "HOUSEHOLD";
            case OTHER: return "OTHER";
        }
        return null;
    }
}


