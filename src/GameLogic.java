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

        int blackTileX = 0;
        int blackTileY = 0;
        JButton blackTile = null;

        for (int i = 0; i < buttonArray.length ; i++) {
            for (int j = 0; j < buttonArray[i].length; j++) {
                if(buttonArray[i][j].getText().equals("")){
                    blackTile = buttonArray[i][j];
                    blackTileX = i;
                    blackTileY = j;
                }
            }
        }

        if(buttonArray[blackTileX - 1 ][blackTileY] == selectedTile) {
            buttonArray[blackTileX][blackTileY] = selectedTile;
            buttonArray[blackTileX - 1 ][blackTileY] = blackTile;
        }
        if(buttonArray[blackTileX + 1 ][blackTileY] == selectedTile) {
            buttonArray[blackTileX][blackTileY] = selectedTile;
            buttonArray[blackTileX + 1 ][blackTileY] = blackTile;
        }
        if(buttonArray[blackTileX ][blackTileY - 1] == selectedTile) {
            buttonArray[blackTileX][blackTileY] = selectedTile;
            buttonArray[blackTileX ][blackTileY - 1] = blackTile;
        }
        if(buttonArray[blackTileX ][blackTileY + 1] == selectedTile) {
            buttonArray[blackTileX][blackTileY] = selectedTile;
            buttonArray[blackTileX ][blackTileY + 1] = blackTile;
        }

        return buttonArray;
    }
}
