package model;

public class Road {

    private String departureLocation;
    private String destination;

    private double distance;
    private int speedLimit;


    public Road(String departureLocation, String destination, double distance, int speedLimit){
        this.departureLocation = departureLocation;
        this.destination = destination;
        this.distance = distance;
        this.speedLimit = speedLimit;
    }

    public String getDeparture(){
        return this.departureLocation;
    }

    public String getDestination(){
        return this.destination;
    }
}
