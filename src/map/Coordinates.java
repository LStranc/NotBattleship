package map;

public class Coordinates {
    private int x;
    private int y;

    public Coordinates(){
        x = 0;
        y = 0;
    }

    public Coordinates(int x, int y){
        this.x = x;
        this.y = y;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void setCoordinates(int x,int y){
        this.x = x;
        this.y = y;
    }

    public boolean equals(Coordinates comparison){
        if(comparison != null && getX() == comparison.getX() && getY() == comparison.getY()){
            return true;
        }
        return false;
    }

    public String toString(){
        Character representY = (char)(65 + y);
        Integer representX = x + 1;
        return representY.toString() + representX;
    }
}
