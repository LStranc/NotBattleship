package boats;

import map.Coordinates;
import map.World;

public class Cruiser extends ScoutBoat {
    public Cruiser(int TEAM, Coordinates location, int direction){
        super(TEAM, location, direction, 3, 3, 4);
    }

    public String getID(){
        return "C" + getTeam();
    }

    public String getBoatType(){ return "Cruiser";}

    public String getActions() {
        return super.getActions();
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
        else {
            return turn(1);
        }
    }

    public String act(int[] choice, World world, int round){
            if(round == 2){
                return "";
            }
            else if (choice[round] == 1){
                return idle() + act(choice, world, ++round);
            }
            else if (choice[round] == 2) {
                return move(world) + act(choice, world, ++round);
            } else if (choice[round] == 3) {
                return turn(-1) + act(choice, world, ++round);
            } else {
                return turn(1) + act(choice, world, ++round);
            }
    }

}
