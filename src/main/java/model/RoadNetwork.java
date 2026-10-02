package model;

import de.topobyte.osm4j.core.model.iface.OsmNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

public class RoadNetwork {
    //Represents a graph as an adjacency list which contains all locations as vertices and roads as edges.
    private Map<OsmNode, List<Road>> network = new HashMap<>();
    private Map<Long, OsmNode> nodesById = new HashMap<>();

    //Adds a vertex (location)
    public void addLocation(OsmNode node)
    {
        this.network.put(node, new ArrayList<>());
        this.nodesById.put(node.getId(), node);

    }

    public OsmNode getNodeFromId(long id){
        return this.nodesById.get(id);
    }

    //Adds an edge (road)

    public void addRoad(OsmNode Node, Road newRoad){
        if(!this.network.containsKey(newRoad.getDestination())) {
            addLocation(newRoad.getDestination());
        }
        this.network.get(Node).add(newRoad);
    }

    public Map<OsmNode, List<Road>> getNetwork(){
        return network;
    }
}
