package routing;

import de.topobyte.osm4j.core.model.iface.OsmNode;

class NodeDistance {
    private double distance;
    private OsmNode location;

    public NodeDistance(double distance, OsmNode location){
        this.distance = distance;
        this.location = location;
    }

    public double getDistance(){
        return this.distance;
    }
    public OsmNode getLocation(){
        return this.location;
    }

}
