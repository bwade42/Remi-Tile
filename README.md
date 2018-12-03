Project: Remi Tile

Brandon Wade


Introduction

My goal was to design and implent 2 versions of the game Remi Tile. The first version will only have implementation of the basic rules, while the second version will have a more advanced rule set.

Contributions

Usage

Within this project folder you should be able to find two seperate jar files. One labled "RemiTile_1.0.jar" and the second labeled "RemiTile_2.0.jar". To run you would simply double click the jar file, and when the game opens just press new game! Also check out the rules before starting

Project Assumptions
Assignment2_RemiTile/Remi Tile 1.0 Diagram.pdf


Versions

Where are the .jar files?
Assignment2/Assignment2_RemiTile/RemiTile_1.0.jar

V1

Version 1 is my best shot at implementing the basic rules. From the Users point of view the game is played:

1. User presss new game, the computer and players hand should be loaded with tiles
2. The user is now in the draw phase and is required to draw a card by clicking the tile pool deck
3. The user is now in the discard phase and now must click a tile in his/her to discard



4. The user is now in the play state, where they must organize there cards into all possible sets. This is done by swapping tiles, by clicking two individual tiles. Unforuntately version 1 doesnt have 'Run' detection. So winning configurations are based on Set formations
5. After all possible sets are formed, the game checks who formed the set, first if the computer has a winning configuration before the player, the computer gains -50 points and the user gains the sum total of the values of the tiles on the board.


6. The game is played until the user or computer reaches 250 points.





V2

Still in progress


Docs

Assignme


Status


Implemented Features
Version 1:
Rules:
1. Displays the rules of the game

New Game:
1. Resets the game



The users functionality includes:
1. Drawing tiles from tile pool deck
2. discard tiles
3. building sets by reranging tiles in hand


The computer core functionality includes:
1. Discard tiles
2. Randomly sort hand until it builds a winning configuration
3. Draw tiles from tile pool deck

Score:
1. User and Computer can gain and lose points based on winning and losing configurations.


Version 2:

Known Issues

Version 1 Bugs:

Bug 1:
If a user discards a tile that could of been used as a possible set, the game for some reason doesnt update the number of expected sets the user has in their had, so when the user enters the next phase, the game will be a stuck in a state where it waits for the user to construct the set that they would of had.

Bug 2: The Joker is considered a '0' tiles and therfore is only worth 0 points. Unfortunately i didnt have enough time to implement the jokers core functionality.

Bug 3:
Players nor the Computer are able to draw tiles from perspective discard piles.


Version 2: Bugs



Testing and Debugging


