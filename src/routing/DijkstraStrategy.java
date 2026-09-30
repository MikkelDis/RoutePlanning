package routing;

import model.Road;
import model.RoadNetwork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraStrategy {

    public Map<String, Double> calculateShortestPath(final String source, final String destination, final RoadNetwork network){
        PriorityQueue<NodeDistance> minQueue = new PriorityQueue<>((a, b) -> Double.compare(a.getDistance(), b.getDistance()));
        Map<String, Double> dist = new HashMap();
        Map<String, String> previous = new HashMap<>();
        for (String location : network.getNetwork().keySet()){
            dist.put(location, Double.MAX_VALUE);
            previous.put(location, "");
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

        String currentDest = destination;
        System.out.println(currentDest);
        do {
            currentDest = previous.get(currentDest);
            System.out.println(currentDest);
        } while (!currentDest.equals(source));
        return dist;
    }

}
