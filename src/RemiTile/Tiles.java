package RemiTile;

import javafx.scene.control.Button;
import javafx.scene.paint.Color;

class Tiles
{
    public Color tileColor;

    public int x;

    public int y;

    public Button tileButton;

    public int tileNumber;

    public boolean isPlayable;

    public Tiles(Button button, Color c, int xpos, int ypos, int num, boolean playable)
    {
        x = xpos;
        y = ypos;
        tileColor = c;

        tileButton = button;

        tileNumber = num;

        isPlayable = playable;

    }


}
