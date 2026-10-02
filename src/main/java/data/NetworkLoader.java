package data;

import de.topobyte.osm4j.core.model.iface.EntityContainer;
import de.topobyte.osm4j.core.model.iface.EntityType;
import de.topobyte.osm4j.core.model.iface.OsmNode;
import de.topobyte.osm4j.core.model.iface.OsmWay;
import de.topobyte.osm4j.core.model.util.OsmModelUtil;
import de.topobyte.osm4j.pbf.seq.PbfIterator;
import model.Road;
import model.RoadNetwork;


import java.io.*;
import java.util.Map;
import java.util.Set;

public class NetworkLoader {
    //Radius of the earth
    static double r = 6371;

    //A set of alle drivable highways in the OSM data
    static Set<String> drivableHighways = Set.of(
            "motorway",
            "motorway_link",
            "trunk",
            "trunk_link",
            "primary",
            "primary_link",
            "secondary",
            "secondary_link",
            "tertiary",
            "tertiary_link",
            "unclassified",
            "residential",
            "living_street",
            "service"
    );

    public RoadNetwork loadNetwork(){
        RoadNetwork newNetwork = new RoadNetwork();
        InputStream vendsysselPBF = null;
        InputStream vendsysselPBF2 = null;
        try {
            vendsysselPBF = new FileInputStream("src/main/resources/Vendsyssel.osm.pbf");
            vendsysselPBF2 = new FileInputStream("src/main/resources/Vendsyssel.osm.pbf");
        }catch (FileNotFoundException e){
            System.out.println("Could not find the file");
        }

        if (vendsysselPBF != null){
            PbfIterator iterator = new PbfIterator(vendsysselPBF, false);
            //First iteration, focus only on nodes
            for (EntityContainer container : iterator) {
                if (container.getType() == EntityType.Node) {
                    OsmNode node = (OsmNode) container.getEntity();
                    newNetwork.addLocation(node);
                }
            }


            PbfIterator iterator2 = new PbfIterator(vendsysselPBF2, false);
            for (EntityContainer container : iterator2) {
                if (container.getType() == EntityType.Way) {
                    boolean driveable = false;
                    int speedLimit = 0;
                    boolean oneway = false;
                    OsmWay way = (OsmWay) container.getEntity();
                    for (int i = 0; i < way.getNumberOfTags(); i++) {
                        if(way.getTag(i).getKey().equals("highway")) {
                            if (drivableHighways.contains(way.getTag(i).getValue())) {
                                driveable=true;
                            }
                        } else if(way.getTag(i).getKey().equals("maxspeed")) {
                            speedLimit = Integer.parseInt(way.getTag(i).getValue());
                        } else if(way.getTag(i).getKey().equals("oneway")){
                            if(way.getTag(i).getValue().equals("yes")){
                                oneway = true;
                            }
                        }
                    }

                    if(driveable){
                        for(int j = 0; j < way.getNumberOfNodes()-1; j++){
                            System.out.println(way.getNodeId(j));
                            long sourceId = way.getNodeId(j);
                            long destinationId = way.getNodeId(j + 1);
                            /*The Haversine Formula.
                             * Calculates distance between 2 points on earth
                             * using their latitude and longtitude.
                             * */
                            double deltaLat = Math.toRadians(newNetwork.getNodeFromId(destinationId).getLatitude() - newNetwork.getNodeFromId(sourceId).getLatitude());
                            double deltaLon = Math.toRadians(newNetwork.getNodeFromId(destinationId).getLongitude() - newNetwork.getNodeFromId(sourceId).getLongitude());

                            double rLat1 = Math.toRadians(newNetwork.getNodeFromId(destinationId).getLatitude());
                            double rLat2 = Math.toRadians(newNetwork.getNodeFromId(sourceId).getLatitude());

                            double a = Math.pow(Math.sin(deltaLat / 2), 2) + Math.pow(Math.sin(deltaLon / 2), 2) *
                                    Math.cos(rLat1) * Math.cos(rLat2);

                            double distance = 2 * r * Math.asin(Math.sqrt(a));
                            Road newRoad = new Road(newNetwork.getNodeFromId(destinationId), distance, speedLimit);
                            newNetwork.addRoad(newNetwork.getNodeFromId(sourceId), newRoad);
                            if(!oneway){
                                Road backRoad = new Road(newNetwork.getNodeFromId(sourceId), distance, speedLimit);
                                newNetwork.addRoad(newNetwork.getNodeFromId(destinationId), backRoad);
                            }

                        }
                    }
                }

            }
        }
        return newNetwork;
    }
}
