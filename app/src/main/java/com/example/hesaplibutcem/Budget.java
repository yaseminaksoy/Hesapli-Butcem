package com.example.hesaplibutcem;

public class Budget {
    private String name;
    private String date;
    private double cost;
    private boolean isIncome;

    public Budget(String name, String date, double cost, boolean isIncome) {
        this.name = name;
        this.date = date;
        this.cost = cost;
        this.isIncome = isIncome;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public boolean isIncome() {
        return isIncome;
    }

    public void setIncome(boolean income) {
        isIncome = income;
    }
}










