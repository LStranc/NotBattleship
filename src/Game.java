import boats.*;
import map.Coordinates;
import map.World;
import music.Music;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Game {

    public static void waitForEnter(){
        try {
            System.in.read();
        }catch(Exception e){

        }
    }

    public static Coordinates validateInputLocation(int team, Scanner scanner,Boat[] fleet){
        String inputLocation = scanner.nextLine();
        Coordinates validInput;
        if((inputLocation.length() == 2 || inputLocation.length() == 3)){
            int index0 = inputLocation.codePointAt(0);
            if((index0 >= 97 && index0 <= 99) || (index0 >= 104 && index0 <= 106)){
                index0 -= 32;
            }
            int index1 = inputLocation.codePointAt(1);
            int index2 = 0;
            if(inputLocation.length() == 3) {
                index2 = inputLocation.codePointAt(2);
            }
            if(index1 >= 49 && index1 <= 57){
                if((team == 1 && index0 >= 65 && index0 <= 67) || (team == 2 && index0 >= 72 && index0 <= 74)) {
                    if(index2 == 48 && index1 == 49){
                        validInput = new Coordinates(9, index0 - 65);
                        for(int i = 0; i < fleet.length - 1; i++) {
                            if(fleet[i].getLocation().equals(validInput)){
                                System.out.println(fleet[i].getBoatType() + " already occupies this location. Please try again.");
                                return validateInputLocation(team, scanner, fleet);
                            }
                        }
                            return validInput;
                    }
                    else if(inputLocation.length() == 3){
                        System.out.println("Invalid input. Please try again.");
                        return validateInputLocation(team, scanner, fleet);
                    }
                    else{
                        validInput = new Coordinates(index1 - 49, index0 - 65);
                        for(int i = 0; i < fleet.length - 1; i++) {
                            if(fleet[i].getLocation().equals(validInput)){
                                System.out.println(fleet[i].getBoatType() + " already occupies this location. Please try again.");
                                return validateInputLocation(team, scanner, fleet);
                            }
                        }
                        return validInput;
                    }
                }
            }
        }
        System.out.println("Invalid input. Please try again.");
        return validateInputLocation(team, scanner, fleet);
    }

    public static boolean boatsAlive(Boat[] team){
        for(int i = 0; i < team.length; i++){
            if(team[i].getHealth()>0){
                return true;
            }
        }
        int loser = team[0].getTeam();
        int winner = -1;
        if(loser == 1){
            winner = 2;
        }
        else{
            winner = 1;
        }
        System.out.print("Team " + loser + " has lost all of their ships. Team " + winner + " is the winner!");
        return false;
    }

    public static int validateInput(Scanner scanner, Boat boatCheck){
        int input = 4;
        do{
            try {
                input = scanner.nextInt();
                if (input > boatCheck.getNumActions() || input < 1) {
                    throw new InputMismatchException();
                }
            }
            catch(InputMismatchException e){
                System.out.println("Invalid Input. Please try again.");
                input = 0;
            }
            scanner.nextLine();
        } while(!(input <= boatCheck.getNumActions() && input >= 1));
        return input;
    }

    public static void boatActs(Boat[] team, int order, Scanner scanner, World board){
        String action = "";
        System.out.println(board.drawTeamMap(team, 2));
        System.out.println("\n" + team[order].getID() + " is located at " + team[order].getLocation() + " facing " + team[order].getDirection());
        do{
            System.out.println(team[order].getActions());


            int choice = validateInput(scanner, team[order]);
            action = team[order].act(choice, board);
            if (action.contains(("cannot move"))) {
                System.out.println(action.substring(action.indexOf("cannot move") - 3, action.indexOf("n.") + 2));
            }
            else{
                System.out.println(action);
            }
        } while(action.contains("cannot move") || action.contains("no boats in range") || action.contains(("not ready yet")));
    }

    public static void main(String[] args){
        //
        // Launcher launcher = new Launcher();
        Music music = new Music("src\\music\\!Battleship.wav");
        music.play();
        Scanner s = new Scanner(System.in);

        System.out.println("\nWelcome to the Game of !Battleship! A game of love, laugh, blood, sweat and tears (Tears \n" +
                "if you are the loser anyways lol.) The objective of the game is to destroy all of the\n" +
                "your opponents boats using clever strategy. Each boat has unique properties so make sure \n" +
                "to use each of their skills to the best of your advantage.\n" +
                "Please address the README.md file on Github Strancy27/NotBattleship for instructions on how \n" +
                "to play and details of boats abilities.\n" +
                "Press enter to continue..."
        );
        waitForEnter();

        int mapWidth = 10;
        int mapHeight = 10;

        //Creates the map and sets its size
        World map = new World(mapWidth,mapHeight);

        Coordinates[][] startingPoints = new Coordinates[mapWidth][mapHeight];
        for(int i = 0; i < mapWidth; i++){
            for(int j = 0; j < mapHeight; j++){
                startingPoints[i][j] = new Coordinates(i,j);
            }
        }

        int boatsPerTeam = 1;
        //Creates array to hold all of Team one's Boats
        Boat[] teamOne = new Boat[boatsPerTeam];

        //Creates array to hold all of Team two's Boats
        Boat[] teamTwo = new Boat[boatsPerTeam];

        System.out.println(map.drawTeamMap(teamOne, 1) + "\n" +
                "This is the game map. You each will get to choose the location of each of your boats in a \n" +
                "designated area. Player 1 and Player 2 can place his/her boats in rows A through C and\n" +
                "rows H through I, respectively. When choosing your location, type the row capital letter first\n" +
                "then the column number. Such as \"B8\".\n" +
                "Press enter to continue...\n");
        waitForEnter();

        System.out.println("Great now that we have that settled. It's time for one of you to leave. No don't leave \n" +
                "the room lol, just make sure you aren't in view of the computer anymore. If you are still\n" +
                "in front of the computer you are Player 1. \nPress enter to continue...\n");
        waitForEnter();



        Boat[] team;
        int teamNum;
        int startingDirection;
        String firstRow = "";
        String lastRow = "";
        for(int i = 1; i < 3; i++) {
            if(i == 1){
                teamNum = 1;
                firstRow = "A";
                lastRow = "C";
                team = teamOne;
                startingDirection = map.SOUTH;
            }
            else{
                teamNum = 2;
                firstRow = "H";
                lastRow = "J";
                team = teamTwo;
                startingDirection = map.NORTH;
            }
            System.out.println("Player " + teamNum + " press enter to choose location of your ships.");
            waitForEnter();
            System.out.println("Player " + teamNum + ": Type the location of your Cruiser anywhere in rows " + firstRow + " through " + lastRow + ".");
            team[0] = new Cruiser(i,validateInputLocation(i, s, team),startingDirection);

            Boat[] growingTeam = new Boat[2];
            for(int j = 0; j < 1; j++){
                growingTeam[j] = team[j];
            }
            team = growingTeam;

            System.out.println("Player " + teamNum + ": Choose the location of your Submarine anywhere in rows " + firstRow + " through " + lastRow + ".");
            team[1] = new Submarine(i,validateInputLocation(i, s, team),startingDirection);

            growingTeam = new Boat[3];
            for(int j = 0; j < 2; j++){
                growingTeam[j] = team[j];
            }
            team = growingTeam;

            System.out.println("Player " + teamNum + ": Choose the location of your AircraftCarrier anywhere in rows " + firstRow + " through " + lastRow + ".");
            team[2] = new AircraftCarrier(i,validateInputLocation(i, s, team),startingDirection);

            growingTeam = new Boat[4];
            for(int j = 0; j < 3; j++){
                growingTeam[j] = team[j];
            }
            team = growingTeam;

            System.out.println("Player " + teamNum + ": Choose the location of your Destroyer anywhere in rows rows " + firstRow + " through " + lastRow + ".");
            team[3] = new Destroyer(i,validateInputLocation(i, s, team),startingDirection);

            growingTeam = new Boat[5];
            for(int j = 0; j < 4; j++){
                growingTeam[j] = team[j];
            }
            team = growingTeam;

            System.out.println("Player " + teamNum + ": Choose the location of your Battleship anywhere in rows " + firstRow + " through " + lastRow + ".");
            team[4] = new Battleship(i,validateInputLocation(i, s, team),startingDirection);
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            if(i == 1){
                System.out.println(map.drawTeamMap(team,1));
                teamOne = team;
            }
            else{
                teamTwo = team;
            }
        }
        //Team Ones Boats
        /*
        teamOne[0] = new Cruiser(1,startingPoints[0][9],0);
        teamOne[1] = new Submarine(1,startingPoints[4][5],0);
        teamOne[2] = new AircraftCarrier(1,startingPoints[5][9],0);
        teamOne[3] = new Destroyer(1,startingPoints[2][9],0);
        teamOne[4] = new Destroyer(1,startingPoints[7][9],0);
        teamOne[5] = new Battleship(1,startingPoints[4][9] ,0);


        //Team Two's Boats
        teamTwo[0] = new Cruiser(2,startingPoints[9][0] , 4);
        teamTwo[1] = new Submarine(2,startingPoints[4][4] ,4);
        teamTwo[2] = new AircraftCarrier(2,startingPoints[4 ][0],4);
        teamTwo[3] = new Destroyer(2,startingPoints[7][0] ,4);
        teamTwo[4] = new Destroyer(2,startingPoints[2][0] ,4);
        teamTwo[5] = new Battleship(2,startingPoints[5][0] ,4);
*/

        for(int i = 0; i<teamOne.length; i++){
            map.setOccupant(teamOne[i], teamOne[i].getLocation());
            map.setOccupant(teamTwo[i], teamTwo[i].getLocation());
        }

        System.out.println("Okay Great! Now we can start the actual game! Player 1, it's your turn.\n");
        int turn = 0;
        while(boatsAlive(teamOne) && boatsAlive(teamTwo)) {
            if(turn % 2 == 0){
                team = teamOne;
                teamNum = 1;
            }
            else{
                team = teamTwo;
                teamNum = 2;
            }
            System.out.println("Player " + teamNum);

            System.out.println(map.drawTeamMap(team, 1));
            System.out.println("Press enter to start turn...");
            waitForEnter();

            for (int i = 0; i < team.length; i++) {
                if(team[i].getAlive()) {
                    boatActs(team, i, s, map);
                    if (team[i].getID().contains("C")){
                        System.out.println("\nThe Cruiser is blazing with speed. Choose your second action.");
                        boatActs(team, i, s, map);
                    }

                    /*do{
                        int choice1 = validateInput(s, team[i]);
                        int choice2 = 4;
                        if (team[i].getID().contains("C")) {
                            System.out.println("The Cruiser is blazing with speed. Choose your second action.\n" + team[i].getActions());
                            choice2 = validateInput(s, team[i]);
                        }
                        int[] choices = {choice1, choice2};
                        action = team[i].act(choices, map, 0);
                        if (action.contains(("cannot move"))){
                            System.out.println(action.substring(action.indexOf("cannot move")-3, action.indexOf("n.") + 2));
                        }
                        else {
                            System.out.println(action);
                        }
                     */


                }
            }
            System.out.println(map.drawTeamMap(team, 2));
            System.out.println("---------------------------------------------------");
            System.out.println("Press enter to finish turn...");
            waitForEnter();
            System.out.println("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
            turn++;
        }
    }
}
