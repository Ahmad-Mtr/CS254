package model;

import java.util.ArrayList;

public class Order {
    private double cost;

    
    private ArrayList<Pizza> pizzas;
    
    
    public double getCost() {
        return cost;
    }
    public ArrayList<Pizza> getPizzas() {
        return pizzas;
    }

    public Order() {
        this.pizzas = new ArrayList<>();
        cost = 0;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public double calculateTotalCost() {
        double totalCost = 0.0;
        for (Pizza pizza :
                pizzas) {
            totalCost += pizza.calculateCost();
        }
        cost = totalCost;
        return totalCost;
    }

    public void restart() {
        pizzas = new ArrayList<>();
    }
}
