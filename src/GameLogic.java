import javax.swing.*;
import java.util.Random;

public class GameLogic {

    public static JButton[][] createButtonDisrder(JButton [][] buttonArray) {
        Random random = new Random();

        for (int i = buttonArray.length - 1; i > 0; i--) {
            for (int j = buttonArray[i].length - 1; j > 0; j--) {
                int m = random.nextInt(i + 1);
                int n = random.nextInt(j + 1);

                JButton temp = buttonArray[i][j];

                buttonArray[i][j] = buttonArray[m][n];
                buttonArray[m][n] = temp;
            }
        }
        return buttonArray;
    }

    public static JButton[][] swapWithBlackTile(JButton[][] buttonArray, JButton selectedTile){

        int maxTiles = buttonArray.length * buttonArray[0].length;
        int blackTileX = 0;
        int blackTileY = 0;

        //find black tile
        for (int i = 0; i < buttonArray.length ; i++) {
            for (int j = 0; j < buttonArray[i].length; j++) {
                if(buttonArray[i][j].getText().equals(Integer.toString(maxTiles))){
                    blackTileX = i;
                    blackTileY = j;
                }
            }
        }
        //check surroundings for correct tile
        if(buttonArray[blackTileX - 1 ][blackTileY] == selectedTile)
            buttonArray[blackTileX][blackTileY]
        //swap places
        if(selectedTile.getText().equals( Integer.toString(maxTiles) )){
            return buttonArray;
        }

        return buttonArray;
    }
}
