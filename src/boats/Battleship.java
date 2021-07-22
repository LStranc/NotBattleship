package boats;

import map.Coordinates;
import map.World;

public class Battleship extends Boat implements Attacker {
    public Battleship(int TEAM, Coordinates location, int direction){
        super(TEAM, location, direction, 4, 3,1, 5);
    }

    public String getID(){
        return "B" + getTeam();
    }

    public String getBoatType() {return "Battleship";}

    public String getActions() {
        return super.getActions() +
                "5. Attack";
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
        Coordinates target = new Coordinates(getLocation().getX(),getLocation().getY());
        Boat attackedBoat = map.getOccupant(map.getAdjacentLocation(target,getDirectionNum()));
        for(int i = 0; i < getVision(); i++){
            if(attackedBoat != null && attackedBoat.getTeam() != getTeam()){
                return "Fire cannons! " + attackedBoat.takeHit(getStrength());
            }
            else{
                target = map.getAdjacentLocation(target,getDirectionNum());
            }
        }
        return "There are no boats in range currently. Please try again.";
    }

}
