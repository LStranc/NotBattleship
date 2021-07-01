# NotBattleship
## Overview
This is a two player strategy game in which each team has a set of boats.
The object of the game is to destroy all of the other team's boats.
The game consists of a 10 x 10 board and each team starts with 6 boats. 
Each player only has a limited view of the map which depends on the location of their boats.
The winner is declared the player who destroyed all the other teams boats.

## Turn
### Reading the Map
A player's turn will always start with the display of the completely hidden/blind map. 
They will also be confronted with a prompt that looks similar to this:
1. Blind Map
2. Direction Map
3. Health Map
4. Act

They will be able to change the view of their map by typeing in the right corresponding number.
If the player chooses 2 or 3 they will be able to see the location of their boats and the spots on the map their boats can see. (Choice 4 is not a map and is described seperately bellow.)
The player will have the option to display their boats direction they are facing or their healths. 
A boat will be recongnized on the map with three characters paired together.
The first character will either be an arrow representing the boats dirrection or a number representing the boats health. 
This will depend on the mode the map is in.
The second character will be an individual letter, representing the type of boat it is.
The last character will be a number representing which team the boat is on.
For example, **5A1** represents an Aircraft Carrier that has 5 health and is on team 1.
The player can move freely from one map to another but once they choose to Act (choice 4) they must continue with their turn.

### Act
Once the player has choose to Act they will be prompted with a set of actions for a specific boat of theirs.
The set of actions are dependent on the type of the boat.
For example, the actions list for the Battleship are,

Choose any of the following actions for Battleship:
1. Move
2. Turn left
3. Turn right
4. Attack

Each boat is only allowed one action per turn. (With the expection of the Cruiser which acts twice) 
The Move action allows the boats to move forward once in the direction its facing.
The turn left and turn right action allow the boat to turn 45 degrees in the respective direction.
The Attack action varries for each type of boat and will be described in further detail below.
Once every boat has gone through their action the player's turn ends and the other player's turn begins.


## Types of Boats and their Abilities
Each boat has its own unique starting _Health_, _Strength_, and _Range of View_.
Each type of boat also will have one or more unique abilities.

There are 5 different kinds of boats: 

### Aircraft Carriers
#### Stats:

_Health_ = 5

_Strength_ = 1

_Range of View_ = 1

#### Abilities:
The Aircraft Carrier has planes which will attack every single enemy boat that is within its view. Although the planes do have a slight chance of being shot down which removes the ability of this boat to attack anymore.
