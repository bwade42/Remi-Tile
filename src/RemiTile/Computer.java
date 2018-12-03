package RemiTile;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

class Computer
{
    public ArrayList<Tiles> computerTiles;

    public ArrayList<Tiles> computerDiscardTiles;

    public int offPlaySetCounter = 0; // used to keep track of the number of current sets in hand
    private int numberOfTilesInHand = 14;

    public boolean hasDiscarded = false; // has the computer add a card to the discard pile

    public boolean isDraw = false; // has the computer drawn a card

    public Computer()
    {
       computerTiles = new ArrayList<>();
       computerDiscardTiles = new ArrayList<>();
    }

    /**
     *  Reset the computers hand, discard pile, and number of sets
     */
    public void Reset()
    {
        computerTiles.clear();
        computerDiscardTiles.clear();
        offPlaySetCounter = 0;
    }


    /**
     * Functionality of this method includes, pulling the first tile off the tile pool deck
     * placing the tile in the players hand, making the tile visble, then removing the pulled
     * tile from the deck
     * @param deckTiles
     */
    public void LoadComputerHand(ArrayList<Tiles> deckTiles)
    {

        for(int i = 1; i < numberOfTilesInHand +1; i++)
        {

            Tiles tile = deckTiles.get(i-1);

            if(i == 1)
            {
                tile.x = 60;
                tile.y = 50;
            }

            tile.x = (i * 45) + 60;
            tile.y = 50;

            tile.tileButton.setVisible(true);

            computerTiles.add(tile);

            deckTiles.remove(tile);
        }

    }

    /**
     *   This method is designed to bypass a bug where if the computer has a winning hand configuration
     *   the computer will always start the round with the same winning hand, the player will lose 99 percent
     *   of the time.
     *
     *   This method works by generating a random value between 1-3 and 5-8 and then getting the tiles at
     *   the positions of the random values and then switching them, this make it so the computer would
     *   have to switch them back to get the same winning configuration
     */
    public void ShuffleComputerHand()
    {
        double rand1 = (Math.random() * ((3) + 1)) + 0;
        double rand2 = (Math.random() * ((8 - 5) + 1)) + 5;

        int n = (int)rand1;
        int n2 = (int)rand2;



        Tiles tile1 = computerTiles.get(n);
        Tiles tile2 = computerTiles.get(n2);


        int x1 = tile1.x; // store position of first tile to swamp
        int x2 = tile2.x; //position of second tile


        tile1.x = x2;
        tile2.x = x1;

        // swap positions of elements in list
        Collections.swap(computerTiles,n,n2);

    }
    /***********************************************************************************************************/
    /**
     *  Computer draws a card from the tile pool deck and places it at the end of its hand.
     * @param deck
     */
    /***********************************************************************************************************/

    public void DrawCard(ArrayList<Tiles> deck)
    {
        /* Draw a Card **/
        //draw a card from the tile pool
        Tiles tile = deck.get(0);

        // place the at the end of the compugdfx hand
        tile.x = 740;
        tile.y = 50;

        //it cant be played till next turn
        tile.isPlayable = false;

        // add the tile to the computer hand
        computerTiles.add(tile);

        deck.remove(tile);

        isDraw = true;

    }
    /***********************************************************************************************************/
    /**
     *   This method is suppose to build the core AI of the computer. It works by choosing two random tiles
     *   in the computers hand and switching them, and by some random chance the tiles may create a set.
     */
    /***********************************************************************************************************/

    public void SortComputerHand()
    {
        Random rand = new Random();

        //13 is the maximum and the 1 is our minimum
        int n = rand.nextInt(13) + 1;
        int n2 = rand.nextInt(7) + 1;

        Tiles randomTile1 = computerTiles.get(n);
        Tiles randomTile2 = computerTiles.get(n);

        randomTile1.x = randomTile2.x;
        randomTile2.x = randomTile1.x;

        //update the hand
        Collections.swap(computerTiles, n ,n2);
    }
    /***********************************************************************************************************/
    /**
     * Check to see if the computer has discarded a card, if it has, it chooses the second card in its hand
     * and discards it, then replaces the empty space with the last card in its hand
     *
     */
    /***********************************************************************************************************/
    public void DiscardTile()
    {
        if(!hasDiscarded) {

            //reference the second tile in computers hand
            Tiles tileToDsicard = computerTiles.get(2);


            //grab the last tile in hand
            Tiles lastTile = computerTiles.get(14);

            lastTile.x = tileToDsicard.x;

            //update the hand
            Collections.swap(computerTiles, 2, 14);

            //remove tile from hand
            computerTiles.remove(tileToDsicard);

            // add tile to discard pile
            computerDiscardTiles.add(tileToDsicard);

            hasDiscarded = true;
        }


    }
    /***********************************************************************************************************/
    /**
     *
     * @return the number sets the computer has formed
     */
    /***********************************************************************************************************/

    public int HowManySets() {
        int i = 0;

        //check tiles 0 - 4
        // see if tiles 0-2 match
        // see if tiles 0-3 match
        if(offPlaySetCounter == 0)
        {
            int tile1 = computerTiles.get(i).tileNumber;
            int tile2 = computerTiles.get(i+1).tileNumber;
            int tile3 = computerTiles.get(i+2).tileNumber;
            int tile4 = computerTiles.get(i+3).tileNumber;

            if(tile1 == tile2 && tile1 == tile3 && tile1 == tile4
                    ||tile1 == tile2 && tile1 == tile3)
            {
                offPlaySetCounter++;
            }
        }
        // if there was a 0-2 match from above
        // check tiles 3 - 5
        // if there was a 0-3 match check tiles 4 -7
        if(offPlaySetCounter == 1)
        {
            int tile1 = computerTiles.get(i+3).tileNumber;
            int tile2 = computerTiles.get(i+4).tileNumber;
            int tile3 = computerTiles.get(i+5).tileNumber;
            int tile4 = computerTiles.get(i+6).tileNumber;
            int tile5 = computerTiles.get(i+7).tileNumber;


            if(tile1 == tile2 && tile1 == tile3  || tile2 == tile3
                    && tile2 == tile4 && tile2 == tile5 )
            {
                offPlaySetCounter++;
            }
        }

        //if there was a 3 - 5 tile match from above check tiles 6  - 8
        //if there was a  4 - 7 match check tiles 8 - 11
        if(offPlaySetCounter == 2)
        {
            int tile1 = computerTiles.get(i+6).tileNumber;
            int tile2 = computerTiles.get(i+7).tileNumber;
            int tile3 = computerTiles.get(i+8).tileNumber;
            int tile4 = computerTiles.get(i+9).tileNumber;
            int tile5 = computerTiles.get(i+10).tileNumber;
            int tile6 = computerTiles.get(i+11).tileNumber;

            if(tile3 == tile4 && tile3 == tile4 && tile3 == tile5 && tile3 == tile6
                    || tile1 == tile2 && tile1 == tile3)
            {
                offPlaySetCounter++;
            }
        }

        //if there was a  6 - 8 match from above check tiles 9 - 11
        // if there was a 8 - 11 match check tiles 12 - 16
        if(offPlaySetCounter == 3)
        {
            int tile1 = computerTiles.get(i+9).tileNumber;
            int tile2 = computerTiles.get(i+10).tileNumber;
            int tile3 = computerTiles.get(i+11).tileNumber;


            if(tile1 == tile2 && tile1 == tile3)
            {
                offPlaySetCounter++;
            }
        }

        return offPlaySetCounter;
    }
    /***********************************************************************************************************/
    /**
     * Sorts the tiles in the computer hand in ascending order by on tile numbers, then checks the
     * occurences of each number, if there is 3 or more then thats counted as a set
     * @return the number of expected sets in the computers hand
     */
    /***********************************************************************************************************/
    public int HowManyExpectedSets()
    {
        int computerHand[] = new int[computerTiles.size()];

        int count[] = new int [computerTiles.size()];
        int temp;

        int numberOfSets = 0;

        for(int i = 0; i < computerTiles.size();i++)
        {
            computerHand[i] = computerTiles.get(i).tileNumber;
        }

        sort(computerHand);

        for (int aComputerHand : computerHand) {
            temp = aComputerHand;
            count[temp]++;
        }

        for(int i=1; i < count.length; i++)
        {

            if(count[i] > 0 && count[i] == 1)
            {

            }
            else if(count[i] == 3 || count[i] == 4)
            {
                numberOfSets++;
            }

        }

        return numberOfSets;
    }
    /***********************************************************************************************************/
    /**
     *  Insertion sort
     * @param arr
     */
    /***********************************************************************************************************/

    private void sort(int arr[])
    {
        int n = arr.length;
        for (int i=1; i<n; ++i)
        {
            int key = arr[i];
            int j = i-1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j>=0 && arr[j] > key)
            {
                arr[j+1] = arr[j];
                j = j-1;
            }
            arr[j+1] = key;
        }
    }


}
