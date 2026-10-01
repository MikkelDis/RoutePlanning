package routing;

class NodeDistance {
    private double distance;
    private String location;

    public NodeDistance(double distance, String location){
        this.distance = distance;
        this.location = location;
    }

    public double getDistance(){
        return this.distance;
    }
    public String getLocation(){
        return this.location;
    }

}
