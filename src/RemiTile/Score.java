package RemiTile;

import javafx.scene.text.Text;

import java.util.ArrayList;

class Score
{
    public void SetScore(Text score, int amount)
    {
        score.setText(Integer.toString(amount));
    }

    public int GetScore(Text score)
    {
        String s = score.getText();
        return  Integer.parseInt(s);
    }

    public int CurrentHandScore(ArrayList<Tiles> hand)
    {
        int handScore = 0;

        for (Tiles aHand : hand) {
            handScore += aHand.tileNumber;
        }

        return handScore;
    }
}
