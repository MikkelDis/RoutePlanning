package routing;

class NodeDistance {
    private int distance;
    private String location;

    public NodeDistance(int distance, String location){
        this.distance = distance;
        this.location = location;
    }

    public int getDistance(){
        return this.distance;
    }
    public String getLocation(){
        return this.location;
    }

}
