package RemiTile;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;

import javafx.event.EventHandler;
import javafx.scene.paint.Color;

import java.util.ArrayList;


class TilePool {
    int numberOfTilesInDeck = 108; // needs to be 108

    public ArrayList<Tiles> deckTiles;

    public TilePool() {
        deckTiles = new ArrayList<>();
        //LoadTilePoolDeck();
    }


    /** CREATE THE DECK THAT WILL BE ADDED TO THE TILE POOL **/
    /** I set each button to to be non-visible, only because when i instaniate each button it is placed in the 0,0 **/
    /**
     * position on the canvas, and i dont want that to be visible
     **/
    public void LoadTilePoolDeck() {

        int red_counter = 1;
        int green_counter = 1;
        int yellow_counter = 1;
        int blue_counter = 1;
        int joker_Counter = 1;

        int cardNumber = 1;


        for (int i = 0; i < numberOfTilesInDeck; i++) {
            //create tiles 1 - 26 for red tiles
            if (red_counter <= 26) {
                Button redButton = new Button();
                redButton.setVisible(false);
                Tiles tile = new Tiles(redButton, Color.RED, 0, 0, cardNumber, true);

                deckTiles.add(tile);

                red_counter++;
            }

            //create tiles 1 - 26 for green tiles
            if (green_counter <= 26) {
                Button greenButton = new Button();
                greenButton.setVisible(false);
                Tiles tile = new Tiles(greenButton, Color.GREEN, 0, 0, cardNumber, true);

                deckTiles.add(tile);

                green_counter++;

            }

            //create tiles 1 - 26 for yellow tiles
            if (yellow_counter <= 26) {
                Button yellowButton = new Button();
                yellowButton.setVisible(false);
                Tiles tile = new Tiles(yellowButton, Color.YELLOW, 0, 0, cardNumber, true);

                deckTiles.add(tile);

                yellow_counter++;

            }

            //create tiles 1 - 26 for blue tiles
            if (blue_counter <= 26) {
                Button blueButton = new Button();
                blueButton.setVisible(false);
                Tiles tile = new Tiles(blueButton, Color.BLUE, 0, 0, cardNumber, true);

                deckTiles.add(tile);

                blue_counter++;


            }

            // add 4 jokers
            if (joker_Counter < 5) {
                Button jokerButton = new Button();

                jokerButton.setVisible(false);
                Tiles tile = new Tiles(jokerButton, Color.BLACK, 0, 0, 0, true);
                deckTiles.add(tile);
                joker_Counter++;

            }
            cardNumber++;

            if (cardNumber > 13) {
                cardNumber = 1;
            }

        }

    }

    public void ResetDeck() {
        deckTiles.clear();
    }


}
