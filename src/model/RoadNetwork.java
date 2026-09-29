package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class RoadNetwork {
    //Represents a graph as an adjacency list which contains all locations as vertices and roads as edges.
    private Map<String, List<Road>> network = new HashMap<>();

    //Adds a vertex (location)
    public void addLocation(String location){
        network.putIfAbsent(location, new ArrayList<>());
    }

    //Adds an edge (road)
    public void addRoad(Road newRoad){
        if(!this.network.containsKey(newRoad.getDeparture())) {
            addLocation(newRoad.getDeparture());
        }
        if(!this.network.containsKey(newRoad.getDestination())) {
            addLocation(newRoad.getDestination());
        }
        this.network.get(newRoad.getDeparture()).add(newRoad);
    }

    public Map<String, List<Road>> getNetwork(){
        return network;
    }
}
