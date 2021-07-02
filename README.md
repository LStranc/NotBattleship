# NotBattleship (v1.0.0-beta)
## Overview
This is a two player strategy game in which each team has a set of boats.
The object of the game is to destroy all of the other team's boats.
The game consists of a 10 x 10 board and each team starts with 6 boats. 
Each player only has a limited view of the map which depends on the location of their boats.
The winner is declared the player who destroyed all the other teams boats.

## Requirements
Currently, in order for this game to operate correctly it must be run on a __Java IDE__ such as Eclispe, NetBeans, or IntelliJ IDEA.

Your operating system must have the __Java Development Kit (JDK) 15__ which can be found on Orcale's website. 

## Starting Up
1. Start with downloading the JDK and a Java IDE of your choice.
2. Download the Zip file of the Repository from Github.
3. Set up the Java IDE and open up the _!Battleship_ project.
4. Click on _!Battleship_ to see folders within it and then click on the _src_ file.
5. Then double click on _Game.java_ to open it.
6. Build the project by clicking on the build button at the top or pressing CTRL+F9 (for Windows).
7. Then once the project has built, click the run button at the top or press SHIFT+F10 (for Windows). This will start up the game so you are all ready to start sinking boats!

## How to Play
### Reading the Map
A player's turn will always start with the hidden/blind map showing. 
They will also be confronted with a prompt that looks similar to this:
1. Blind Map
2. Direction Map
3. Health Map
4. Act

They will be able to change the view of their map by typeing in the right corresponding number.
#### Blind Map
It will change the map so that it hides both teams' boats.

#### Direction Map
The map will show your team's boats, the spots on the map your boats have view of, and the direction of all boats in view.

#### Health Map
The map will show your team's boats, the spots on the map your boats have view of, and remaining health of all boats in view.

### Understanding Map Symbols
A boat will be recongnized on the map with three characters paired together.

The __first character__ will either be an arrow representing the boat's dirrection or a number representing the boat's health. 
This will depend on the mode the map is in.

The __second character__ will be an individual letter, representing the first letter of the type of boat it is.

The __last character__ will be a number representing which team the boat is on.

For example, **5A1** represents an Aircraft Carrier that has 5 health and is on team 1.

The player can move freely from one map to another but once they choose to Act (choice 4) they must continue with their turn.

### Act
Once the player has chose to Act, they will be prompted with a set of actions for a specific boat of theirs.
The set of actions are dependent on the type of the boat.
For example, the actions list for the Battleship are,

Choose any of the following actions for Battleship:
1. Move
2. Turn left
3. Turn right
4. Attack

Each boat is only allowed one action per turn. (With the expection of the Cruiser which acts twice) 

The _Move_ action allows the boats to move forward once in the direction its facing.

The _Turn Left_ and _Turn Right_ action allow the boat to turn 45 degrees in the respective direction.

The _Attack_ action varries for each type of boat and will be described in further detail below.

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
The Aircraft Carrier has planes which will attack every single enemy boat that is within its personal view (of 1), not just the boat in front of it. Although the planes do have a slight chance of being shot down which removes the ability of this boat to attack anymore.


### Battleship
#### Stats:

_Health_ = 4

_Strength_ = 3

_Range of View_ = 1

#### Abilities:
The Battleship's attack method is a generic attack method and will attack the boat that it is facing.
The Battleship is special because the _Strength_ of its attack.


### Cruiser
#### Stats:

_Health_ = 3

_Strength_ = 0

_Range of View_ = 3

#### Abilities:
The Cruiser is a scout boat and doesn't have any attack method.  Although the Cruiser does have a good chance of dodging attacks. It also gets two actions within one turn while all other boats only get one action.


### Destroyer
#### Stats:

_Health_ = 3

_Strength_ = 2

_Range of View_ = 1

#### Abilities:
The Destroyer's attack method is also a generic attack method. 
Although the Destroyer has a high chance of dodging attacks from other boats.

### Submarine
#### Stats:

_Health_ = 3

_Strength_ = 2

_Range of View_ = 2

#### Abilities:
The Submarine has 5 torpedos that it can use to attack other ships.
The torpedos will atack any boats that are directly in front of the Submarine and are within its personal view (of 2), meaning the Submarine has the ability to attack two boats at once.
Once the Submarine is out of torpedos the Submarine can no longer attack.
Even though the Submarine can attack it is techniquely a scout boat which means it has a good chance of dodging attacks. 
Lastily, the Submarine has the ability to _Submerge_ which means the boat will move to a random spot on the map that wasn't currently in its personal view (of 2).

## Version
This game is entering Beta version v1.0.0-beta as of 7/1/2021. 

More testing and adjustments will need to be made to the project before it can offically be launched out of Beta.

Once the game has a stable release, the next step in the process will be creating graphics for the game which should appear in v2.0.0.


