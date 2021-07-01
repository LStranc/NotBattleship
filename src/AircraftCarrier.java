public class AircraftCarrier extends Boat implements Attacker{

    private boolean hasPlanes;

    public AircraftCarrier(int team, Coordinates location, int direction){
        super(team, location, direction, 5,1,1);
        hasPlanes = true;
    }

    public String getID(){
        return "A" + getTeam();
    }

    public String getActions(){
        return "Choose any of the following actions for AircraftCarrier:\n" +
                "1. Move\n" +
                "2. Turn Left\n" +
                "3. Turn Right\n" +
                "4. Launch planes (" + (hasPlanes?"Available":"Destroyed") + ")";
    }

    public String act(int[] choice, World map, int round){
        if(choice[0] == 1){
            return move(map);
        }
        if(choice[0] == 2){
            return turn(-1);
        }
        if(choice[0] == 3){
            return turn(1);
        }
        else{
            return attack(map);
        }
    }

    public String attack(World map){
        boolean calledAirRaid = false;
        String response = "";
        if(hasPlanes){
            for(int i = -getVision(); i <= getVision(); i++){
                for(int j = -getVision(); j <= getVision(); j++){
                    Coordinates attacked = new Coordinates(getLocation().getX()+i,getLocation().getY()+j);
                    if(map.isLocationOccupied(attacked) && map.getOccupant(attacked).getTeam() != getTeam()) {
                        if(!calledAirRaid) {
                            calledAirRaid = true;
                            response += "Air Raid! ";
                        }
                        double k = Math.random();
                        if(k > 0.1) {
                            response += map.getOccupant(attacked).takeHit(getStrength()) + " ";
                        }
                        else{
                            hasPlanes = false;
                            return response + "Ohh no! The planes got shot down!";
                        }
                    }
                }
            }
            if(!calledAirRaid){
                return "There are no boats in range currently.";
            }
        }
        else{
            return getID() + " has no planes remaining.";
        }
        return response;
    }


}
