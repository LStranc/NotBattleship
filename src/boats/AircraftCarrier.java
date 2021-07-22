package boats;

import map.Coordinates;
import map.World;

public class AircraftCarrier extends Boat implements Attacker {

    private boolean hasPlanes;

    public AircraftCarrier(int TEAM, Coordinates location, int direction){
        super(TEAM, location, direction, 5,1,1, 5);
        hasPlanes = true;
    }

    public String getID(){
        return "A" + getTeam();
    }

    public String getBoatType() {return "AircraftCarrier:";}

    public String getActions(){
        return super.getActions() +
                "5. Launch planes (" + (hasPlanes?"Available":"Destroyed") + ")";
    }

    public String act(int choice, World world){
        if(choice == 1){
            return idle();
        }
        else if(choice == 2){
            return move(world);
        }
        else if(choice == 3){
            return turn(-1);
        }
        else if(choice == 4){
            return turn(1);
        }
        else{
            return attack(world);
        }
    }

    public String act(int[] choice, World map, int round){
        if(choice[0] == 1){
            return idle();
        }
        if(choice[0] == 2){
            return move(map);
        }
        if(choice[0] == 3){
            return turn(-1);
        }
        if(choice[0] == 4){
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
                            response += map.getOccupant(attacked).takeHit(getStrength(), map) + " ";
                        }
                        else{
                            hasPlanes = false;
                            return response + "Ohh no! The planes got shot down!";
                        }
                    }
                }
            }
            if(!calledAirRaid){
                return "There are no boats in range currently. Please try again.";
            }
        }
        else{
            return getID() + " has no planes remaining.";
        }
        return response;
    }


}
