public abstract class Boat {
    private int team;
    private Coordinates location;
    private int direction;
    private int health;
    private int strength;
    private int vision;
    private boolean alive;

    public Boat(int team, Coordinates location, int direction, int health, int strength, int vision){
        this.team = team;
        this.location = location;
        this.direction = direction;
        this.health = health;
        this.strength = strength;
        this.vision = vision;
        alive = true;
    }

    public int getTeam(){
        return team;
    }

    public Coordinates getLocation(){
        return location;
    }

    public int getDirectionNum(){
        return direction;
    }

    public String getDirection(){
        String arrow = "";
        switch(direction){
            case 0: arrow = "\u2191"; break;
            case 1: arrow = "\u2197"; break;
            case 2: arrow = "\u2192"; break;
            case 3: arrow = "\u2198"; break;
            case 4: arrow = "\u2193"; break;
            case 5: arrow = "\u2199"; break;
            case 6: arrow = "\u2190"; break;
            case 7: arrow = "\u2196"; break;

        }
        return arrow;
    }

    public int getHealth(){
        return health;
    }

    public int getStrength(){
        return strength;
    }
    public int getVision(){
        return vision;
    }

    public abstract String getID();

    public abstract String act(int[] options, World world, int round);

    public abstract String getActions();

    public boolean getAlive(){return alive;}

    public String move(World world){
        Coordinates oldLocation = new Coordinates(getLocation().getX(), getLocation().getY());
        Coordinates movingLocation = world.getAdjacentLocation(getLocation(), direction);
        if(world.isLocationValid(movingLocation) && !world.isLocationOccupied(movingLocation)){
            world.setOccupant(this, movingLocation);

            world.setOccupant(null, oldLocation);
            setLocation(movingLocation);

            return toString() + " moves from " + oldLocation + " to " + movingLocation + ". ";
        }
        else if(world.isLocationOccupied(movingLocation)){
            return toString() + " cannot move to " + movingLocation + " as it is occupied. ";
        }
        else{
            return toString() + " cannot move off the map. ";
        }
    }

    public String turn(int turning){
        direction += turning;
        if(direction == 8){
            direction = 0;
        }
        if(direction == -1){
            direction = 7;
        }
        String whichWay = "";
        switch(turning){
            case -1: whichWay = "left"; break;
            case 1: whichWay = "right"; break;
        }
        return toString() + " turned " + whichWay + ", now facing " + getDirection() + ". ";
    }

    public String takeHit(int damage){
        health -= damage;

        if(health <= 0){
            health = 0;
            location = null;
            alive = false;
            return toString() + " has been sunk!";
        }
        return toString() + " takes " + damage + " damage.";
    }

    public void setLocation(Coordinates location){
        this.location = location;
    }

    public String toString(){
        return getID();
    }




}
