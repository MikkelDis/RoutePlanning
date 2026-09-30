import data.NetworkLoader;
import model.RoadNetwork;
import routing.DijkstraStrategy;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        NetworkLoader loader = new NetworkLoader();
        RoadNetwork network = loader.loadNetwork();

        DijkstraStrategy dijkstraAlgo = new DijkstraStrategy();

        Map<String, Double> shortespaths = dijkstraAlgo.calculateShortestPath("Aalborg", "Asaa", network);
        System.out.println(shortespaths);

    }
}