public class Submarine extends ScoutBoat implements Attacker{
    private int numOfTorpedoes;

    public Submarine(int TEAM, Coordinates location, int direction){
        super(TEAM,location,direction,3,2,5);
        numOfTorpedoes = 5;
    }

    public String getID(){
        return "S" + getTeam();
    }

    public String getActions(){
        return "Choose any of the following actions for the Submarine: \n" +
                "1. Move\n" +
                "2. Turn Left\n" +
                "3. Turn Right\n" +
                "4. Submerge\n" +
                "5. Fire Torpedoes (" + numOfTorpedoes + " torpedoes available)";
    }

    public String act(int[] choice, World world, int round){
        if (choice[0] == 1){
            return move(world);
        }
        if (choice[0] == 2){
            return turn(-1);
        }
        if (choice[0] == 3){
            return turn(1);
        }
        if (choice[0] == 4){
            return submerge(world);
        }
        else{
            return attack(world);
        }
    }

    public String attack(World world){
        if (numOfTorpedoes > 0){
            numOfTorpedoes--;
            Coordinates target = new Coordinates(getLocation().getX(),getLocation().getY());
            Boat attackedBoat = world.getOccupant(world.getAdjacentLocation(target, getDirectionNum()));
            for(int i = 0; i < getVision(); i++){
                if(attackedBoat != null && world.getOccupant(target).getTeam() != getTeam()){
                    return "Fire torpedoes! " + attackedBoat.takeHit(getStrength()) + "\nSubmarine has " + numOfTorpedoes + " torpedoes left.";
                }
                else{
                    target = world.getAdjacentLocation(target,getDirectionNum());
                }
            }
            numOfTorpedoes++;
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
