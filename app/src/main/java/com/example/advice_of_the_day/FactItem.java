package com.example.advice_of_the_day;

public class FactItem {
    private String factText;
    private boolean isHistoryFact; // Для стилизации исторического факта иначе

    public FactItem(String factText, boolean isHistoryFact) {
        this.factText = factText;
        this.isHistoryFact = isHistoryFact;
    }

    public String getFactText() {
        return factText;
    }

    public boolean isHistoryFact() {
        return isHistoryFact;
    }
}