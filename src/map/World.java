package map;

import boats.Boat;

public class World {
    private Boat[][] map;
    public static final int NORTH = 0;
    public static final int NORTHEAST = 1;
    public static final int EAST = 2;
    public static final int SOUTHEAST = 3;
    public static final int SOUTH = 4;
    public static final int SOUTHWEST = 5;
    public static final int WEST = 6;
    public static final int NORTHWEST = 7;

    public World(int x, int y) {
        if (x < 4) {
            x = 4;
        }
        if (x > 10) {
            x = 10;
        }
        if (y < 4) {
            y = 4;
        }
        if (y > 10) {
            y = 10;
        }
        map = new Boat[y][x];
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                map[i][j] = null;
            }
        }
    }

    public int getWidth() {
        return map.length;
    }

    public int getHeight() {
        return map[0].length;
    }

    public Boat getOccupant(Coordinates coordinates) {
        return map[coordinates.getX()][coordinates.getY()];
    }

    public boolean isLocationValid(Coordinates coordinates) {
        if (coordinates != null && coordinates.getX() < getWidth() && coordinates.getX() >= 0 && coordinates.getY() < getHeight() && coordinates.getY() >= 0) {
            return true;
        }
        return false;
    }

    public boolean isLocationOccupied(Coordinates coordinates) {
        if (isLocationValid(coordinates) && map[coordinates.getX()][coordinates.getY()] != null) {
            return true;
        }
        return false;
    }

    public boolean setOccupant(Boat boat, Coordinates coordinates) {
        if (!isLocationOccupied(coordinates) || boat == null && isLocationValid(coordinates)) {
            map[coordinates.getX()][coordinates.getY()] = boat;
            return true;
        }
        map[coordinates.getX()][coordinates.getY()] = null;
        return false;
    }

    public Coordinates getAdjacentLocation(Coordinates coordinates, int direction) {
        int x = 0;
        int y = 0;

        switch (direction) {
            case NORTH:
                x = 0;
                y = -1;
                break;
            case NORTHEAST:
                x = 1;
                y = -1;
                break;
            case EAST:
                x = 1;
                y = 0;
                break;
            case SOUTHEAST:
                x = 1;
                y = 1;
                break;
            case SOUTH:
                x = 0;
                y = 1;
                break;
            case SOUTHWEST:
                x = -1;
                y = 1;
                break;
            case WEST:
                x = -1;
                y = 0;
                break;
            case NORTHWEST:
                x = -1;
                y = -1;
                break;
        }
        Coordinates testCoordinates = new Coordinates(coordinates.getX() + x, coordinates.getY() + y);
        if (isLocationValid(testCoordinates)) {
            return testCoordinates;
        }
        return null;
    }

    public String drawTeamMap(Boat[] boats, int view) {
        String display = "";
        display += "@";
        for (int i = 1; i <= map.length; i++) {
            display += "  " + i + "  ";
        }
        for (int i = 0; i < getHeight(); i++) {
            display += "\n";
            display += (char) (65 + i) + " ";
            for (int j = 0; j < getWidth(); j++) {

                String characterType = "";
                outerLoop:
                if (view == 1) {
                    characterType = "#### ";
                }

                else{
                    Coordinates coordinate = new Coordinates(i, j);
                    for(int a = 0; a < boats.length; a++){
                        if (map[j][i] != null && map[j][i].getLocation() != null && boats[a].getLocation() != null && map[j][i].getLocation().equals(boats[a].getLocation())) {
                            characterType = map[j][i].getHealth() + map[j][i].getDirection() + map[j][i].getID() + " ";
                        }
                    }

                    if (characterType.equals("")) {
                        for (int k = -coordinate.getX(); k < getWidth() - coordinate.getX(); k++) {
                            for (int l = -coordinate.getY(); l < getHeight() - coordinate.getY(); l++) {
                                Coordinates viewCheck = new Coordinates(j + l, i + k); //checks every point from j and i
                                for (int a = 0; a < boats.length; a++) {
                                    if (boats[a].getLocation() != null && boats[a].getLocation().equals(viewCheck)) {
                                        if (boats[a].getVision() >= Math.abs(k) && boats[a].getVision() >= Math.abs(l)) {
                                            if (map[j][i] != null && map[j][i].getLocation() != null) {
                                                characterType = map[j][i].getHealth()+ map[j][i].getDirection() + map[j][i].getID() + " ";
                                            } else {
                                                characterType = "~~~~ ";
                                            }
                                            break outerLoop;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
                if (characterType.equals("")) {
                    characterType = "#### ";
                }

                display += characterType;
            }
        }
        return display;
    }
}
