public abstract class ScoutBoat extends Boat {
    public ScoutBoat(int team, Coordinates location, int direction, int health, int vision){
        super(team, location, direction, health, 2, vision);
    }

    public String takeHit(int damage){
        if(Math.random() <= 0.25){
            return super.takeHit(damage);
        }
        else{
            return toString() + " has avoided the attack!";
        }
    }

}
