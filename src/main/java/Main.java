import data.NetworkLoader;
import model.RoadNetwork;
import routing.DijkstraStrategy;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        NetworkLoader loader = new NetworkLoader();
        RoadNetwork network = loader.loadNetwork();

        DijkstraStrategy dijkstra = new DijkstraStrategy();
        dijkstra.calculateShortestPath(network.getNodeFromId(14135030699L),  network.getNodeFromId(2300917741L), network);

    }
}