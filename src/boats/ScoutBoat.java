package boats;

import map.Coordinates;
import map.World;

public abstract class ScoutBoat extends Boat {
    public ScoutBoat(int TEAM, Coordinates location, int direction, int health, int VISION, int NUMACTIONS){
        super(TEAM, location, direction, health, 2, VISION, NUMACTIONS);
    }

    public String takeHit(int damage, World map){
        if(Math.random() >= 0.25){
            return super.takeHit(damage, map);
        }
        else{
            return toString() + " has avoided the attack! ";
        }
    }

}
