package org.example;

public enum Month {
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER;

    public static Month getMonth(String monthNumber) {
        switch (monthNumber) {
            case "01": return JANUARY;
            case "02": return FEBRUARY;
            case "03": return MARCH;
            case "04": return APRIL;
            case "05": return MAY;
            case "06": return JUNE;
            case "07": return JULY;
            case "08": return AUGUST;
            case "09": return SEPTEMBER;
            case "10": return OCTOBER;
            case "11": return NOVEMBER;
            case "12": return DECEMBER;
        }
        return null;
    }
}