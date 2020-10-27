import javax.swing.*;
import java.util.Random;

public class GameLogic {

    private static int moveCounter = 0;
    private static boolean isVictory = false;

    public static JButton[][] createButtonDisorder(JButton[][] buttonArray) {
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

    public static JButton[][] swapWithBlackTile(JButton[][] buttonArray, JButton selectedTile) {

        int blackTileX = -1;
        int blackTileY = -1;
        JButton blackTile = null;
        boolean hasMoved = false;

        for (int i = 0; i < buttonArray.length; i++) {
            for (int j = 0; j < buttonArray[i].length; j++) {
                if (buttonArray[i][j].getText().equals("")) {
                    blackTile = buttonArray[i][j];
                    blackTileX = j;
                    blackTileY = i;
                }
            }
        }

        if (blackTileY != 0) {
            if (buttonArray[blackTileY - 1][blackTileX] == selectedTile) {
                buttonArray[blackTileY][blackTileX] = selectedTile;
                buttonArray[blackTileY - 1][blackTileX] = blackTile;
                hasMoved = true;
            }
        }
        if (!(blackTileY >= buttonArray.length - 1)) {
            if (buttonArray[blackTileY + 1][blackTileX] == selectedTile) {
                buttonArray[blackTileY][blackTileX] = selectedTile;
                buttonArray[blackTileY + 1][blackTileX] = blackTile;
                hasMoved = true;
            }
        }
        if (blackTileX != 0) {
            if (buttonArray[blackTileY][blackTileX - 1] == selectedTile) {
                buttonArray[blackTileY][blackTileX] = selectedTile;
                buttonArray[blackTileY][blackTileX - 1] = blackTile;
                hasMoved = true;
            }
        }
        if (!(blackTileX >= buttonArray[0].length - 1)) {
            if (buttonArray[blackTileY][blackTileX + 1] == selectedTile) {
                buttonArray[blackTileY][blackTileX] = selectedTile;
                buttonArray[blackTileY][blackTileX + 1] = blackTile;
                hasMoved = true;
            }
        }

        if (hasMoved) {
            moveCounter++;
            isVictory = checkVictoryCondition(buttonArray);
        }
        return buttonArray;
    }

    public static boolean checkVictoryCondition(JButton[][] buttonArray) {
        boolean solved;

        int counter = 1;

        outerloop:
        for (int row = 0; row < buttonArray.length; row++) {
            for (int col = 0; col < buttonArray[row].length; col++) {
                if (buttonArray[row][col].getText().equals( Integer.toString(counter) )) {
                    if (counter == (buttonArray.length * buttonArray[0].length - 1)) {
                        return (true);
                    }
                } else
                    break outerloop;
                counter++;
            }
        }
        return (false);
    }

    public static int getMoveCounter() {
        return moveCounter;
    }


    public static void resetMoveCounter() {
        moveCounter = 0;
    }

    public static boolean getIsVictory (){
        return isVictory;
    }
}