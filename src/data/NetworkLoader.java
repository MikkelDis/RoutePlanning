package data;

import model.Road;
import model.RoadNetwork;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
public class NetworkLoader {

    public RoadNetwork loadNetwork(){
        RoadNetwork newNetwork = new RoadNetwork();
        try (BufferedReader br = new BufferedReader(new FileReader("resources/roads.csv"))){
            String line;
            while ((line = br.readLine()) != null){
                String[] roadString = line.split(",");
                Road newRoad = new Road(roadString[0], roadString[1], Integer.parseInt(roadString[2]),Integer.parseInt(roadString[3]));
                newNetwork.addRoad(newRoad);
            }
            return newNetwork;
        }catch (IOException e){
            System.out.println(e);
            return null;
        }
    }
}
