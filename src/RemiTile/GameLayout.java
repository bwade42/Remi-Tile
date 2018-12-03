package RemiTile;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.text.Text;
import java.util.ArrayList;

class GameLayout
{

    Button tilePoolButton;
    Text tilePoolText;

    private Button playerDiscardButton;
    private Text playerDiscardText;

    private Button computerDiscardButton;
    private Text computerDiscardText;

    Button newGameButton;


    private Button rulesButton;

    public Text playerOneScore;
    public Text computerScore;

    private Canvas canvas;
    public Group root;
    private GraphicsContext gc;

    public GameLayout()
    {
        canvas = new Canvas(getWidth(), getHeight());

        tilePoolButton = new Button();
        playerDiscardButton = new Button();
        computerDiscardButton = new Button();
        newGameButton = new Button("new\n game");
        rulesButton = new Button("rules");

        tilePoolText = new Text("Tile Pool");
        playerDiscardText = new Text("Player 1 Discard Pile");
        computerDiscardText= new Text("Player 2 Discard Pile");
        playerOneScore = new Text("0");
        computerScore = new Text("0");

        gc = canvas.getGraphicsContext2D();
        root = new Group ();

         LoadGraphics();


    }
    /***********************************************************************************************************/
    /**
     /************************************************************************************************************/
    private void LoadGraphics()
    {
        root.getChildren().add(canvas);

        root.getChildren().add(tilePoolButton);
        root.getChildren().add(newGameButton);
        root.getChildren().add(rulesButton);
        root.getChildren().add(playerDiscardButton);
        root.getChildren().add(computerDiscardButton);

        root.getChildren().add(computerDiscardText);
        root.getChildren().add(playerDiscardText);
        root.getChildren().add(tilePoolText);
        root.getChildren().add(playerOneScore);
        root.getChildren().add(computerScore);

        DrawNewGameButton();
        DrawRulesButton();
        DrawTilePoolButton();
        DrawScoreBoard();

    }

    private int getHeight()
    {
        // height of the game screen
        return 600;
    }

    private int getWidth()
    {
        // width of the game screen
        return 850;
    }


    /**
     * Create the graphics for the tiles in the players hand
     * @param playerTiles
     */
    public void DrawPlayerTiles(ArrayList<Tiles> playerTiles)
    {
        Image image = new Image("/RemiTile/Images/joker.png");

        for (Tiles currentTile : playerTiles) {
            if (currentTile.tileNumber == 0) {
                currentTile.tileButton.setGraphic(new ImageView(image));

            }

            currentTile.tileButton.setMinSize(40, 60);
            currentTile.tileButton.setMaxSize(40, 60);

            currentTile.tileButton.setLayoutX(currentTile.x);
            currentTile.tileButton.setLayoutY(currentTile.y);

            currentTile.tileButton.setStyle("-fx-background-color: White;-fx-border-color: Black;-fx-border-radius:8;");

        }

    }

    /**
     * Create graphics for the tiles in the computers hand
     * @param computerTiles
     */
    public void DrawComputerTiles(ArrayList<Tiles> computerTiles)
    {
        Image image = new Image("/RemiTile/Images/joker.png");
       // -fx-background-position: center;
        for (Tiles currentTile : computerTiles) {
            if (currentTile.tileNumber == 0) {
                currentTile.tileButton.setGraphic(new ImageView(image));
                currentTile.tileButton.setVisible(false);
            }

            currentTile.tileButton.setMinSize(40, 60);
            currentTile.tileButton.setMaxSize(40, 60);

            currentTile.tileButton.setLayoutX(currentTile.x);
            currentTile.tileButton.setLayoutY(currentTile.y);

            currentTile.tileButton.setStyle("-fx-background-color: Blue;-fx-border-color: Black;-fx-border-radius:5;");

        }

    }

    /**
     *  Creates the graphics for the tile pool deck
     */
    public void DrawTilePoolButton()
    {
        Image image = new Image("/RemiTile/Images/TilePoolImage.png");

        tilePoolButton.setGraphic(new ImageView(image));

        tilePoolButton.setMaxSize(50,50);

        tilePoolButton.setLayoutX(20);
        tilePoolButton.setLayoutY(215);

        tilePoolButton.setStyle("-fx-background-color: transparent;");

        tilePoolText.setX(35);
        tilePoolText.setY(330);
        tilePoolText.setFill(Color.RED);

    }

    /**
     * Creates graphics for cards in the computers and players discard piles
     * One thing to note is an error i was coming across when trying to draw an empty discard pile
     * so i added 2 different for loops for each discard pile, one is for when the pile is empty, and the
     * only when it has at least 1 item in it
     * @param playerDiscardTiles
     * @param computerDiscardTiles
     */
    public void DrawDiscardPiles(ArrayList<Tiles> playerDiscardTiles,ArrayList<Tiles> computerDiscardTiles)
    {
        Image image = new Image("/RemiTile/Images/joker.png");

        if(playerDiscardTiles.size() > 0)
        {
            for (Tiles currentTile : playerDiscardTiles)
            {
                /** Joker **/
                if (currentTile.tileNumber == 0) {
                    currentTile.tileButton.setGraphic(new ImageView(image));

                }

                currentTile.tileButton.setMinSize(40, 60);
                currentTile.tileButton.setMaxSize(40, 60);

                currentTile.tileButton.setLayoutX(30);
                currentTile.tileButton.setLayoutY(50);

                currentTile.tileButton.setStyle("-fx-background-color: White;-fx-border-color: Black;-fx-border-radius:5;");

            }
        }

        else
        {
            playerDiscardButton.setMinSize(40,60);
            playerDiscardButton.setMaxSize(40,60);

            playerDiscardButton.setLayoutX(30);
            playerDiscardButton.setLayoutY(50);

            playerDiscardText.setX(15); // 15
            playerDiscardText.setY(40); // 25
            playerDiscardText.setFill(Color.RED);
            playerDiscardButton.setStyle("-fx-background-color: White;-fx-border-color: Black;-fx-border-radius:5;");

        }


       if(computerDiscardTiles.size() > 0) {

           Tiles currentTile = computerDiscardTiles.get(0);

           /** Joker **/
           if (currentTile.tileNumber == 0) {
               currentTile.tileButton.setGraphic(new ImageView(image));

           }

           currentTile.tileButton.setMinSize(40, 60);
           currentTile.tileButton.setMaxSize(40, 60);

           currentTile.tileButton.setLayoutX(30);
           currentTile.tileButton.setLayoutY(400);

           currentTile.tileButton.setText(Integer.toString(currentTile.tileNumber));
           currentTile.tileButton.setTextFill(currentTile.tileColor);

           //currentTile.tileButton.setStyle("-fx-background-color: White;-fx-border-color: Black;-fx-border");
           currentTile.tileButton.setStyle("-fx-background-color: White;-fx-border-color: Black;-fx-border-radius:5;");

       }
        else
        {
            computerDiscardButton.setMinSize(40,60);
            computerDiscardButton.setMaxSize(40,60);

            computerDiscardButton.setLayoutX(30);
            computerDiscardButton.setLayoutY(400);

            computerDiscardButton.setStyle("-fx-background-color: White;-fx-border-color: Black;-fx-border-radius:5;");

            computerDiscardText.setX(10); // 20
            computerDiscardText.setY(490); // 490
            computerDiscardText.setFill(Color.RED);
        }

    }

    /**
     * Create the score board graphics for the player and computer
     */
    private void DrawScoreBoard()
    {
        // draw player 1 scoreboard
        gc.setLineWidth(3); // outline of cell
        gc.strokeRect(795,400,45,60);
        gc.setStroke(Color.LIGHTBLUE); // color of outline of cell
        gc.setFill(Color.GREY);
        gc.fillRect(795,400,45,60);

        playerOneScore.setX(800);
        playerOneScore.setY(440);
        playerOneScore.setFill(Color.RED);
        playerOneScore.setStyle("-fx-font: 16 arial;");


        // draw player 2 scoreboard
        gc.setLineWidth(3); // outline of cell
        gc.strokeRect(795,50,45,60);
        gc.setStroke(Color.LIGHTBLUE); // color of outline of cell
        gc.setFill(Color.GREY);
        gc.fillRect(795,50,45,60);


        computerScore.setX(800);
        computerScore.setY(90);
        computerScore.setFill(Color.RED);
        playerOneScore.setStyle("-fx-font: 16 arial;");


    }

    /**
     * Create the graphics for the new game button
     */
    private void DrawNewGameButton()
    {
        newGameButton.setMinSize(70,60);
        newGameButton.setMaxSize(70,60);


        newGameButton.setLayoutX(750);
        newGameButton.setLayoutY(230);

        newGameButton.setStyle("-fx-background-color: Yellow;-fx-border-color: Black;-fx-border-radius:5;");

        newGameButton.setTextFill(Color.RED);


    }

    /**
     *   Create graphics for the rules button as well implents the event listner when the player clicks
     *   the button
     */
    private void DrawRulesButton()
    {
        rulesButton.setMinSize(70,60);
        rulesButton.setMaxSize(70,60);


        rulesButton.setLayoutX(750);
        rulesButton.setLayoutY(160);

        rulesButton.setStyle("-fx-background-color: Yellow;-fx-border-color: Black;-fx-border-radius:5;");

        rulesButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e)
            {
                Alert alert = new Alert(AlertType.INFORMATION);
                String part1 = "1. For Version 1.0 Player one will always go first\n\n";

                String part2 = "2. both players must click the tile pool deck to get draw a card\n\n";


                String part3 = "3. Once both players have drawn a card the game enters the discard phase, in which " +
                                "both players must click a tile in their hand that will be added to individual\n" +
                                "discard piles\n\n";

                String part4 = "4. The player now needs to organize their hand into a set or run, tiles can be orgainized\n"+
                                " by swapping tiles(click 2 different tiles to swap position)\n\n";

                String part5 = " Set: when 3 or 4 tiles have the same number but different colors\n";

                String part6 = " Run : 3 or more tiles in sequence and of the same color \n\n";

                String part7 = " It is best to form your first set at the begining of the hand, 2nd set next to it etc.\n\n";

                String part8 = "5. A player wins the game by being the first to build a winning configuration:\n\n";

                String part9 = "-Winner Gains -50 Points\n-Loser gains the sum total of the values of the tiles on board with\n" +
                        "-The jocker counted as 25 points\n" + "-The game is played until a player reaches 200 points\n" +
                        "-The overall winner is the player with the fewest points\n";

                alert.setTitle("Remi Tile Version 1.0");
                alert.setHeaderText("Game Rules");
                alert.setContentText(part1 + part2 + part3 + part4 + part5 + part6 + part7 + part8 + part9);

                alert.showAndWait();
            }
        });

    }
}


