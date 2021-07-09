package boats;

import map.World;
import map.Coordinates;

public class Submarine extends ScoutBoat implements Attacker {
    private int numOfTorpedoes;

    public Submarine(int TEAM, Coordinates location, int direction){
        super(TEAM,location,direction,3,2,6);
        numOfTorpedoes = 5;
    }

    public String getID(){
        return "S" + getTeam();
    }

    public String getActions(){
        return "Choose any of the following actions for the Submarine: \n" +
                "1. Idle\n" +
                "2. Move\n" +
                "3. Turn Left\n" +
                "4. Turn Right\n" +
                "5. Submerge\n" +
                "6. Fire Torpedoes (" + numOfTorpedoes + " torpedoes available)";
    }

    public String act(int[] choice, World world, int round){
        if (choice[0] == 1){
            return idle();
        }
        if (choice[0] == 2){
            return move(world);
        }
        if (choice[0] == 3){
            return turn(-1);
        }
        if (choice[0] == 4){
            return turn(1);
        }
        if (choice[0] == 5){
            return submerge(world);
        }
        else{
            return attack(world);
        }
    }

    public String attack(World world){
        if (numOfTorpedoes > 0){
            String response = "";
            boolean calledFire = false;
            Coordinates attacked1 = world.getAdjacentLocation(getLocation(), getDirectionNum());
            Boat attackedBoat1 = world.getOccupant(attacked1);
            Coordinates attacked2 =  world.getAdjacentLocation(attacked1, getDirectionNum());
            Boat attackedBoat2 = world.getOccupant(attacked2);
            if((attackedBoat1 != null && attackedBoat1.getTeam() != getTeam()) ||
                    (attackedBoat2 != null && attackedBoat2.getTeam() != getTeam())) {
                numOfTorpedoes--;
                calledFire = true;
                response += "Fire torpedoes! ";
                if(attackedBoat1 != null && attackedBoat1.getTeam() != getTeam()){
                    response += attackedBoat1.takeHit(getStrength());
                }
                if(attackedBoat2 != null && attackedBoat2.getTeam() != getTeam()){
                    response += attackedBoat2.takeHit(getStrength());
                }
                response += "\nSubmarine has " + numOfTorpedoes + " torpedoes left.";
                return response;
            }

            return "There are no boats in range currently. Please try again.";
        }
        return getID() + " has no torpedoes remaining.";
    }

    public String submerge(World world){
        int xCord = (int)(Math.random() * (world.getWidth()));
        int yCord = (int)(Math.random() * (world.getHeight()));
        Coordinates randomLocation = new Coordinates(xCord, yCord);
        if (Math.abs(xCord - getLocation().getX()) >= 2 && Math.abs(yCord - getLocation().getY()) >= 2 && (!world.isLocationOccupied(randomLocation))) {
            Coordinates oldLocation = getLocation();
            world.setOccupant(this, randomLocation);
            world.setOccupant(null, oldLocation);
            setLocation(randomLocation);
            return getID() + " moves from " + oldLocation + " to " + randomLocation + ".";
        }
        return submerge(world);
    }


}
