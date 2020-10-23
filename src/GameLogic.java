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

        int blackTileX = -1;
        int blackTileY = -1;
        JButton blackTile = null;

        for (int i = 0; i < buttonArray.length ; i++) {
            for (int j = 0; j < buttonArray[i].length; j++) {
                if(buttonArray[i][j].getText().equals("")){
                    blackTile = buttonArray[i][j];
                    blackTileX = j;
                    blackTileY = i;
                }
            }
        }

        System.out.println("Black tile x= " + blackTileX);
        System.out.println("Black tile y= " + blackTileY);

        if((buttonArray[blackTileY - 1 ][blackTileX] == selectedTile) && !(blackTileY == 0)) {
            System.out.println("selectedTile är ovanför");
            buttonArray[blackTileY][blackTileX] = selectedTile;
            buttonArray[blackTileY - 1 ][blackTileX] = blackTile;
        }
        else if((buttonArray[blackTileY + 1 ][blackTileX] == selectedTile) && !(blackTileY == buttonArray.length - 1)) {
            System.out.println("selectedTile är nedanför");
            buttonArray[blackTileY][blackTileX] = selectedTile;
            buttonArray[blackTileY + 1 ][blackTileX] = blackTile;
        }
        else if((buttonArray[blackTileY ][blackTileX - 1] == selectedTile) && !(blackTileX == 0)) {
            System.out.println("selectedTile är till vänster");
            buttonArray[blackTileY][blackTileX] = selectedTile;
            buttonArray[blackTileY ][blackTileX - 1] = blackTile;
        }
        else if((buttonArray[blackTileY ][blackTileX + 1] == selectedTile) && !(blackTileX == buttonArray[0].length - 1)) {
            System.out.println("selectedTile är till höger");
            buttonArray[blackTileY][blackTileX] = selectedTile;
            buttonArray[blackTileY ][blackTileX + 1] = blackTile;
        }

        return buttonArray;
    }

    public static void checkVictoryCondition(JButton[][] buttonArray){

    }
}
