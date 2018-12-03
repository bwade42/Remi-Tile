package RemiTile;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.Collections;

class GameStatus
{

    private Tiles drawnTile;

    int phaseNumber = 0; // helps keep track of the current phase

    boolean playerHasDiscarded = false; // has the player discard a card

    boolean playerHasDrawn = false; // has the player drawn a card

    boolean isNewGame = false; // are we in the new game phase

    /**
     *
     */
    public void Reset()
    {
        playerHasDiscarded = false;
        playerHasDrawn = false;
        isNewGame = false;
        phaseNumber = 0;
    }

    /**
     *
     * @param newGame
     */
    public void NewGame(Button newGame)
    {
        newGame.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e)
            {
               isNewGame = true;
            }
        });
    }

    /**
     *
     * @param deck
     * @param hand
     * @param tilePoolButton
     */
    public void DrawTile(ArrayList<Tiles> deck, ArrayList<Tiles> hand, Button tilePoolButton)
    {
        tilePoolButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e)
            {

                /** Draw a Card **/
                //draw a card from the tile pool
                Tiles tile = deck.get(0);

                // place the at the end of the players hand
                tile.x = 750;
                tile.y = 400;

                tile.tileButton.setText(Integer.toString(tile.tileNumber));

                tile.tileButton.setTextFill(tile.tileColor);

                //it cant be played till next turn
                tile.isPlayable = false;

                //remove the tile from the deck
                drawnTile = tile;

                // add the tile to the play hand
                hand.add(tile);

                deck.remove(tile);

                playerHasDrawn = true;

            }
        });

    }

    /**
     *
     * @param hand
     * @param discardPile
     */
    public void DiscardTile(ArrayList<Tiles> hand, ArrayList<Tiles> discardPile) {

        for (int i = 0; i < hand.size(); i++)
        {
            Tiles currentTile = hand.get(i);
            final int buttonIndex = i;

            currentTile.tileButton.setOnAction(new EventHandler<ActionEvent>()
            {
                @Override
                public void handle(ActionEvent e)
                {

                    int lastTileIndex = 14;

                    Tiles lastTile = hand.get(14);
                    Tiles discard = hand.get(buttonIndex);

                    // place the tile at the end of the hand into the
                    //discard tiles position
                    lastTile.x = currentTile.x;

                    //update the hand
                    Collections.swap(hand,lastTileIndex,buttonIndex);

                    //remove tile from hand
                    hand.remove(currentTile);

                    // add tile to discard pile
                    discardPile.add(discard);

                   playerHasDiscarded = true;
                }
            });
        }


    }


}