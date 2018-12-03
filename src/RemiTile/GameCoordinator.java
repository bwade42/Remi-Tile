package RemiTile;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.util.Duration;
import javafx.event.EventHandler;

import javafx.scene.Group;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Collections;


public class GameCoordinator extends Application {

    /**
     * Global Variables
     */
    private GameLayout gameLayout;

    private GameStatus gameStatus;

    private Score gameScore;

    private Player player;

    private Computer computer;

    private TilePool tilePool;

    /**
     *  Initialize all the componets that game coordinator will be in control of as specified in
     *  design diagram
     */
    public GameCoordinator()
    {
        gameLayout = new GameLayout();

        gameScore = new Score();

        player = new Player();

        computer = new Computer();

        computer = new Computer();

        tilePool = new TilePool();

        gameStatus = new GameStatus();

        //root = new Group ();
        gameLayout.tilePoolButton.setVisible(false);
        gameLayout.tilePoolText.setVisible(false);

    }

    @Override
    public void start(Stage primaryStage)
    {
        primaryStage.setTitle("Remi Tile 1.0");

        primaryStage.setScene(new Scene(new Group(gameLayout.root)));
        primaryStage.show();

        /******* START GAME THREAD ***********************/
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(10),
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent t) {
                        CheckGameStatus(); // checks the current state of game

                        UpdateDisplay(); // show the changes

                    }

                }));
        //number of times to play animation
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

    }

    public static void main(String[] args) {
        launch(args);
    }
    /***********************************************************************************************************/
    /** Updates the game board by drawing the cards in the players hand, computers hands and discard piles **/
    /************************************************************************************************************/
    private void UpdateDisplay() {
        gameLayout.DrawTilePoolButton();

        gameLayout.DrawDiscardPiles(player.playerDiscardTiles, computer.computerDiscardTiles);

        gameLayout.DrawPlayerTiles(player.playerTiles);
        gameLayout.DrawComputerTiles(computer.computerTiles);
    }

    /***********************************************************************************************************/
    /**  The game status is based on phase's which changes based on different user actions interactions
     ************************************************************************************************************/
    private void CheckGameStatus() {

        gameStatus.NewGame(gameLayout.newGameButton); // listen to the new game button

        //check to see if tile pool deck has been clicked
        gameStatus.DrawTile(tilePool.deckTiles, player.playerTiles, gameLayout.tilePoolButton);

        //System.out.println("Current Phase = " + gameStatus.phaseNumber);

        if (gameStatus.isNewGame)
        {
            NewGame();
            gameStatus.phaseNumber = 1;
        }

        /** Draw Phase **/
        if (gameStatus.phaseNumber == 1)
        {

            //check to make sure the player has drawn a tile
            if (gameStatus.playerHasDrawn && player.playerTiles.size() > 14)
            {
                player.playerTiles.get(14).tileButton.setVisible(true);
                //
                if (computer.computerTiles.size() < 15)
                {
                    computer.DrawCard(tilePool.deckTiles);
                    computer.computerTiles.get(14).tileButton.setVisible(true);
                }
                gameStatus.phaseNumber = 2;
            }

        }

       /** Discard Phase **/
        if (gameStatus.phaseNumber == 2) {
            gameStatus.DiscardTile(player.playerTiles, player.playerDiscardTiles);

            if (!computer.hasDiscarded) {
                computer.DiscardTile();
            }

            if (gameStatus.playerHasDiscarded) {
                gameStatus.phaseNumber = 3;
            }

        }

        /** Play phase where each player must sort there hand into a winning configuration **/
        if (gameStatus.phaseNumber == 3)
        {
            if (computer.computerTiles.size() > 14)
            {
                computer.computerTiles.get(14).tileButton.setVisible(false);
            }
            player.SortHand();
            CheckForRoundWinner();
        }

        /** End phase, check to see if the game has any winners, if we start the game over **/
        if (gameStatus.phaseNumber == 5)
        {
            player.offPlaySetCounter = 0;
            computer.offPlaySetCounter = 0;
            if (gameScore.GetScore(gameLayout.playerOneScore) >= 250
                    || gameScore.GetScore(gameLayout.computerScore) >= 250) {
                gameStatus.phaseNumber = 6;
            }
            else
                {
                computer.isDraw = false;
                gameStatus.playerHasDrawn = false;
                gameStatus.playerHasDiscarded = false;
                gameStatus.phaseNumber = 1;
            }

        }

        if (gameStatus.phaseNumber == 6)
        {
            System.out.println("Game Over");
        }

    }

    /**
     *
     */
    private void CheckForRoundWinner()
    {
        if (player.HowManySets() != player.HowManySets() && computer.HowManySets() != computer.HowManyExpectedSets())
        {
            computer.SortComputerHand();
        }

        if(player.HowManySets() == player.HowManyExpectedSets()
                || computer.HowManyExpectedSets() == computer.HowManySets())
        {

            int previous_score_player = gameScore.GetScore(gameLayout.playerOneScore);
            int updatedScorePlayer = previous_score_player + gameScore.CurrentHandScore(computer.computerTiles);

            int previous_score_computer = gameScore.GetScore(gameLayout.computerScore);
            int updatedScoreComputer = previous_score_computer + gameScore.CurrentHandScore(player.playerTiles);

            //check to see if player wins
            if(player.HowManySets() > computer.HowManySets())
            {
                gameScore.SetScore(gameLayout.playerOneScore, previous_score_player - 50);
                gameScore.SetScore(gameLayout.computerScore,updatedScoreComputer);
                gameStatus.phaseNumber = 5;

                player.ShuffleHand();

                System.out.println("Player Wins Round");
            }

            //Check to see if computer wins
            if(computer.HowManySets() > player.HowManySets())
            {
                gameScore.SetScore(gameLayout.computerScore, previous_score_computer - 50);
                gameScore.SetScore(gameLayout.playerOneScore,updatedScorePlayer);
                gameStatus.phaseNumber = 5;

                computer.ShuffleComputerHand();

                System.out.println("Computer Wins Round");
            }

            //Check for Tie
            if(player.HowManyExpectedSets() == computer.HowManyExpectedSets())
            {
                gameStatus.phaseNumber = 5;
                System.out.println("No One Wins Round");
            }

            //check for an automatic lose for the player
            if(player.HowManyExpectedSets() == 0)
            {
                gameStatus.phaseNumber = 5;
                gameScore.SetScore(gameLayout.playerOneScore,updatedScorePlayer);
                System.out.println("Player Doesn't Have Any Sets!");
            }

        }

    }
    /***********************************************************************************************************/
    /** Reset the game, includes reloading the tile pool deck player/computers hands
    /************************************************************************************************************/
    private void NewGame() {



        /* Reset Game Status Booleans **/
        gameStatus.Reset();

        /* Reset the tile pool Deck **/
        tilePool.ResetDeck(); // empty the tile pool

        /* Reset the player  **/
        player.Reset(); // empty the play hand

        /* Reset the computer **/
        computer.Reset();

        /* Redisplay the tile pool deck **/
        gameLayout.tilePoolButton.setVisible(true);
        gameLayout.tilePoolText.setVisible(true);

        /* Load the tile pool deck with tiles **/
        tilePool.LoadTilePoolDeck();

        /* shuffle the deck */
        Collections.shuffle(tilePool.deckTiles);

        /* Tell the program what buttons will be used **/
        for (int i = 0; i < tilePool.numberOfTilesInDeck; i++) {
            gameLayout.root.getChildren().add(tilePool.deckTiles.get(i).tileButton);
        }

        /* Load the players hand with tiles **/
        player.LoadPlayerHand(tilePool.deckTiles);

        /* Load the computers hand with tiles **/
        computer.LoadComputerHand(tilePool.deckTiles);

        /* Reset the score **/
        gameScore.SetScore(gameLayout.playerOneScore,0);
        gameScore.SetScore(gameLayout.computerScore,0);

    }

}
