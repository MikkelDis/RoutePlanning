package model;

import java.time.LocalDate;

public class Item {
    private String name;
    private double weight;
    private String destination;
    private LocalDate oderDate;

    public Item(String name, double weight, String destination, LocalDate orderDate){
        this.name = name;
        this.weight = weight;
        this.destination = destination;
        this.oderDate = orderDate;
    }


}
