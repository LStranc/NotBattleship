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

        int boatsPerTeam = 2;
        //Creates array to hold all of Team one's Boats
        Boat[] teamOne = new Boat[boatsPerTeam];

        //Creates array to hold all of Team two's Boats
        Boat[] teamTwo = new Boat[boatsPerTeam];

        // Stops Game if teams aren't the same size
        if(teamOne.length != teamTwo.length){
            System.out.println("Unfair Advantage!");
            System.exit(0);
        }

        System.out.println("Great now that we have that settled. It's time for one of you to leave. No don't leave \n" +
                "the room lol, just make sure you aren't in view of the computer anymore. If you are still\n" +
                "in front of the computer you are Player 1. \nPress enter to continue...");
        waitForEnter();

        Coordinates[][] startingPoints = new Coordinates[mapWidth][mapHeight];
        for(int i = 0; i < mapWidth; i++){
            for(int j = 0; j < mapHeight; j++){
                startingPoints[i][j] = new Coordinates(i,j);
            }
        }

        //Team Ones Boats
        teamOne[0] = new Cruiser(1,startingPoints[0][9],0);
        teamOne[1] = new Submarine(1,startingPoints[4][5],0);
        /*
        teamOne[2] = new AircraftCarrier(1,startingPoints[5][9],0);
        teamOne[3] = new Destroyer(1,startingPoints[2][9],0);
        teamOne[4] = new Destroyer(1,startingPoints[7][9],0);
        teamOne[5] = new Battleship(1,startingPoints[4][9] ,0);
        */


        //Team Two's Boats
        teamTwo[0] = new Cruiser(2,startingPoints[9][0] , 4);
        teamTwo[1] = new Submarine(2,startingPoints[4][4] ,4);
        /*
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
        Boat[] team;
        int teamNum = 1;
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
