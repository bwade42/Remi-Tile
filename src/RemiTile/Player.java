package RemiTile;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Player {

    protected ArrayList<Tiles> playerTiles;

    protected ArrayList<Tiles> playerDiscardTiles;

    private ArrayList<Tiles> standByTiles;


    int offPlaySetCounter = 0;

    private int lastClickedIndex = -1;
    private int lastClickedIndex2 = -1;

    private int globalCounter = 0;

    private int handShuffleCounter = 0;

    public Player() {
        playerTiles = new ArrayList<>();
        standByTiles = new ArrayList<>();
        playerDiscardTiles = new ArrayList<>();

    }

    /**
     *
     */
    public void Reset() {
        playerTiles.clear();
        playerDiscardTiles.clear();
        standByTiles.clear();
        offPlaySetCounter = 0;
    }

    /**
     * Loads th players hand with tiles from the tile pool deck
     * @param deckTiles
     */
    public void LoadPlayerHand(ArrayList<Tiles> deckTiles) {
        int numberOfTilesInHand = 14;
        for (int i = 1; i < numberOfTilesInHand + 1; i++) {
            Collections.shuffle(deckTiles);
            Tiles tile = deckTiles.get(i - 1);

            if (i == 1) {
                tile.x = 70;
                tile.y = 400;
            }

            tile.x = (i * 45) + 70;
            tile.y = 400;

            //tile.x = i * 45;
            //tile.y = 400;

            tile.tileButton.setText(Integer.toString(tile.tileNumber));

            tile.tileButton.setTextFill(tile.tileColor);

            tile.tileButton.setVisible(true);

            playerTiles.add(tile);

            deckTiles.remove(tile);
        }

    }

    /**
     *  This methods functionality includes giving the player the ability to swap tiles in the players hand
     *  This is the core functionality for forming winning configurations
     */
    public void SortHand() {
        for (int i = 0; i < playerTiles.size(); i++) {
            final int buttonIndex = i;
            playerTiles.get(i).tileButton.setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent e) {

                    globalCounter++;

                    if (globalCounter == 1) {
                        lastClickedIndex = buttonIndex;
                    }

                    if (globalCounter == 2) {
                        lastClickedIndex2 = buttonIndex;

                        int x1 = playerTiles.get(lastClickedIndex).x;

                        playerTiles.get(lastClickedIndex).x = playerTiles.get(lastClickedIndex2).x;
                        playerTiles.get(lastClickedIndex2).x = x1;

                        // swap positions of elements in list
                        Collections.swap(playerTiles, lastClickedIndex, lastClickedIndex2);

                        globalCounter = 0;
                    }


                }

            });

        }

    }

    /**
     *  This method was designed to resolve a similar bug such as that of the Computer's class
     *  method 'ShuffleComputerHand' the main difference is the first tile and the 5th tile in the players
     *  hand will be swapped at the end of a phase.
     */
    public void ShuffleHand() {

        Tiles tile1 = playerTiles.get(handShuffleCounter);
        Tiles tile2 = playerTiles.get(handShuffleCounter + 5);

        int x1 = tile1.x;
        int x2 = tile2.x;

        if (handShuffleCounter == 2) {
            handShuffleCounter = 0;
        }

        tile1.x = x2;
        tile2.x = x1;

        // swap positions of elements in list
        Collections.swap(playerTiles, handShuffleCounter, handShuffleCounter + 5);

        handShuffleCounter++;


    }

    /**
     *
     * @return the number of sets the play has formed in his / her hand
     */
    public int HowManySets() {
        int i = 0;
        //check tiles 0 - 4
        // see if tiles 0-2 match
        // see if tiles 0-3 match
        if (offPlaySetCounter == 0) {
            int tile1 = playerTiles.get(i).tileNumber;
            int tile2 = playerTiles.get(i + 1).tileNumber;
            int tile3 = playerTiles.get(i + 2).tileNumber;
            int tile4 = playerTiles.get(i + 3).tileNumber;

            if (tile1 == tile2 && tile1 == tile3 && tile1 == tile4
                    || tile1 == tile2 && tile1 == tile3) {
                offPlaySetCounter++;
            }
        }
        // if there was a 0-2 match from above
        // check tiles 3 - 5
        // if there was a 0-3 match check tiles 4 -7
        if (offPlaySetCounter == 1) {
            int tile1 = playerTiles.get(i + 3).tileNumber;
            int tile2 = playerTiles.get(i + 4).tileNumber;
            int tile3 = playerTiles.get(i + 5).tileNumber;
            int tile4 = playerTiles.get(i + 6).tileNumber;
            int tile5 = playerTiles.get(i + 7).tileNumber;


            if (tile1 == tile2 && tile1 == tile3 || tile2 == tile3
                    && tile2 == tile4 && tile2 == tile5) {
                offPlaySetCounter++;
            }
        }

        //if there was a 3 - 5 tile match from above check tiles 6  - 8
        //if there was a  4 - 7 match check tiles 8 - 11
        if (offPlaySetCounter == 2) {
            int tile1 = playerTiles.get(i + 6).tileNumber;
            int tile2 = playerTiles.get(i + 7).tileNumber;
            int tile3 = playerTiles.get(i + 8).tileNumber;
            int tile4 = playerTiles.get(i + 9).tileNumber;
            int tile5 = playerTiles.get(i + 10).tileNumber;
            int tile6 = playerTiles.get(i + 11).tileNumber;

            if (tile3 == tile4 && tile3 == tile4 && tile3 == tile5 && tile3 == tile6
                    || tile1 == tile2 && tile1 == tile3) {
                offPlaySetCounter++;
            }
        }

        //if there was a  6 - 8 match from above check tiles 9 - 11
        // if there was a 8 - 11 match check tiles 12 - 16
        if (offPlaySetCounter == 3) {
            int tile1 = playerTiles.get(i + 9).tileNumber;
            int tile2 = playerTiles.get(i + 10).tileNumber;
            int tile3 = playerTiles.get(i + 11).tileNumber;


            if (tile1 == tile2 && tile1 == tile3) {
                offPlaySetCounter++;
            }
        }

        return offPlaySetCounter;
    }


    /**
     *
     * @returns the number of possible sets in the players hand
     */
    public int HowManyExpectedSets() {
        int playerHand[] = new int[playerTiles.size()];

        int count[] = new int[playerTiles.size()];
        int temp;

        int numberOfSets = 0;

        for (int i = 0; i < playerTiles.size(); i++) {
            playerHand[i] = playerTiles.get(i).tileNumber;
        }

        sort(playerHand);

        for (int aPlayerHand : playerHand) {
            temp = aPlayerHand;
            count[temp]++;
        }

        for (int i = 1; i < count.length; i++) {

            if (count[i] > 0 && count[i] == 1) {

            } else if (count[i] >= 3) {
                numberOfSets++;
            }

        }

        return numberOfSets;
    }

    /**
     *  Insertion Sort used for  HowManyExpectedSets
     * @param arr
     */
    private void sort(int arr[]) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int key = arr[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && arr[j] > key) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
    }

}
