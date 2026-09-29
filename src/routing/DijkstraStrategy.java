package routing;

import model.Road;
import model.RoadNetwork;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DijkstraStrategy {

    public Map<String, Integer> calculateShortestPath(final String source, final String destination, final RoadNetwork network){
        PriorityQueue<NodeDistance> minQueue = new PriorityQueue<>((a, b) -> a.getDistance()-b.getDistance());
        Map<String, Integer> dist = new HashMap();
        for (String location : network.getNetwork().keySet()){
            dist.put(location, Integer.MAX_VALUE);
        }

        //Overwrite source, makes distance to itself 0.
        dist.put(source, 0);

        //Add the source to the min queue
        minQueue.offer(new NodeDistance(0, source));

        while (!minQueue.isEmpty()){
            NodeDistance min = minQueue.poll();
            //No possible way this is shorter, if its larger than the current shortest
            if(min.getDistance() > dist.get(min.getLocation())){
                continue;
            }

            for(Road i : network.getNetwork().get(min.getLocation())){
                if(dist.get(min.getLocation()) + min.getDistance() < dist.get(i.getDestination())){
                    dist.put(i.getDestination(), dist.get(min.getLocation()) + min.getDistance());
                    minQueue.offer(new NodeDistance(dist.get(i.getDestination()), i.getDestination()));
                }
            }
        }

        return dist;
    }

}
