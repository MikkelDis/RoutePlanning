import dk.mikkel.routeplanning.data.NetworkLoader;
import dk.mikkel.routeplanning.model.RoadNetwork;
import dk.mikkel.routeplanning.routing.DijkstraStrategy;

public class Main {
    public static void main(String[] args) {
        NetworkLoader loader = new NetworkLoader();
        RoadNetwork network = loader.loadNetwork();

        DijkstraStrategy dijkstra = new DijkstraStrategy();
        dijkstra.calculateShortestPath(network.getNodeFromId(14135030699L),  network.getNodeFromId(2300917741L), network);

    }
}