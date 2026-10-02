package routing;

import de.topobyte.osm4j.core.model.iface.OsmNode;
import model.Road;
import model.RoadNetwork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraStrategy {

   public Map<OsmNode, Double> calculateShortestPath(final OsmNode source, final OsmNode destination, final RoadNetwork network){
       System.out.println("Source exists: " +
               network.getNetwork().containsKey(source));

       System.out.println("Destination exists: " +
               network.getNetwork().containsKey(destination));

       System.out.println("Source outgoing roads: " +
               network.getNetwork().get(source).size());

       System.out.println("Destination outgoing roads: " +
               network.getNetwork().get(destination).size());
        PriorityQueue<NodeDistance> minQueue = new PriorityQueue<>((a, b) -> Double.compare(a.getDistance(), b.getDistance()));
        Map<OsmNode, Double> dist = new HashMap<>();
        Map<OsmNode, OsmNode> previous = new HashMap<>();
        for (OsmNode location : network.getNetwork().keySet()) {
            dist.put(location, Double.MAX_VALUE);
            previous.put(location, null);
        }

        //Overwrite source, makes distance to itself 0.
        dist.put(source, 0.0);

        //Add the source to the min queue
        minQueue.offer(new NodeDistance(0, source));

        while (!minQueue.isEmpty()){
            NodeDistance min = minQueue.poll();
            //No possible way this is shorter, if its larger than the current shortest
            if(min.getDistance() > dist.get(min.getLocation())){
                continue;
            }

            for(Road i : network.getNetwork().get(min.getLocation())){
                if(dist.get(min.getLocation()) + i.getDistance() < dist.get(i.getDestination())){
                    dist.put(i.getDestination(), dist.get(min.getLocation()) + i.getDistance());
                    previous.put(i.getDestination(), min.getLocation());
                    minQueue.offer(new NodeDistance(dist.get(i.getDestination()), i.getDestination()));
                }
            }
        }

        OsmNode currentDest = destination;
        System.out.println(currentDest.getId() + " AND " + currentDest.getNumberOfTags());
       System.out.println("Distance: " + dist.get(destination));
       System.out.println("Previous: " + previous.get(destination));
        for(int i = 0; i < currentDest.getNumberOfTags(); i++){
            System.out.println(currentDest.getTag(i).getKey() + " = " + currentDest.getTag(i).getValue());
        }
       System.out.println("---------");
        do {
            currentDest = previous.get(currentDest);
            for(int i = 0; i < currentDest.getNumberOfTags(); i++){
                System.out.println(currentDest.getTag(i).getKey() + " = " + currentDest.getTag(i).getValue());
            }
            System.out.println("---------");
        } while (!currentDest.equals(source));
        return dist;
    }
}
