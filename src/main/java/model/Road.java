package model;

import de.topobyte.osm4j.core.model.iface.OsmNode;

public class Road {

    private OsmNode destination;

    private double distance;
    private int speedLimit;


    public Road(OsmNode destination, double distance, int speedLimit){
        this.destination = destination;
        this.distance = distance;
        this.speedLimit = speedLimit;
    }

    public OsmNode getDestination(){
        return this.destination;
    }

    public double getDistance(){
        return this.distance;
    }
}
