public class Cruiser extends ScoutBoat{
    public Cruiser(int team, Coordinates location, int direction){
        super(team, location, direction, 3, 3);
    }

    public String getID(){
        return "C" + getTeam();
    }

    public String getActions() {
        return "Choose any of the following actions for the Cruiser: \n" +
                "1. Move\n" +
                "2. Turn Left\n" +
                "3. Turn Right\n";
    }

    public String act(int[] choice, World world, int round) {
            if(round == 2){
                return "";
            }
            else if (choice[round] == 1) {
                return move(world) + act(choice, world, ++round);
            } else if (choice[round] == 2) {
                return turn(-1) + act(choice, world, ++round);
            } else {
                return turn(1) + act(choice, world, ++round);
            }
    }
}
