import javax.swing.*;
import java.util.Random;

public class GameLogic {

    private static int moveCounter = 0;

    public static JButton[][] createButtonDisrder(JButton[][] buttonArray) {
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

        System.out.println("Black tile x= " + blackTileX);
        System.out.println("Black tile y= " + blackTileY);

        if (blackTileY != 0) {
            if (buttonArray[blackTileY - 1][blackTileX] == selectedTile) {
                System.out.println("selectedTile är ovanför");
                buttonArray[blackTileY][blackTileX] = selectedTile;
                buttonArray[blackTileY - 1][blackTileX] = blackTile;
                hasMoved = true;
            }
        }
        if (!(blackTileY >= buttonArray.length - 1)) {
            if (buttonArray[blackTileY + 1][blackTileX] == selectedTile) {
                System.out.println("selectedTile är nedanför");
                buttonArray[blackTileY][blackTileX] = selectedTile;
                buttonArray[blackTileY + 1][blackTileX] = blackTile;
                hasMoved = true;
            }
        }
        if (blackTileX != 0) {
            if (buttonArray[blackTileY][blackTileX - 1] == selectedTile) {
                System.out.println("selectedTile är till vänster");
                buttonArray[blackTileY][blackTileX] = selectedTile;
                buttonArray[blackTileY][blackTileX - 1] = blackTile;
                hasMoved = true;
            }
        }
        if (!(blackTileX >= buttonArray[0].length - 1)) {
            if (buttonArray[blackTileY][blackTileX + 1] == selectedTile) {
                System.out.println("selectedTile är till höger");
                buttonArray[blackTileY][blackTileX] = selectedTile;
                buttonArray[blackTileY][blackTileX + 1] = blackTile;
                hasMoved = true;
            }
        }

        if (hasMoved)
            moveCounter++;


        return buttonArray;
    }

    public static boolean checkVictoryCondition(JButton[][] buttonArray) {
        boolean solved;

        int counter = 1;

        for (int row = 0; row < buttonArray.length; row++) {
            for (int col = 0; col < buttonArray[row].length; col++) {
                if ((buttonArray[row][col].getText().equals(Integer.toString(counter)))) {
                    System.out.println("ButtonGetText " + buttonArray[row][col].getText());
                    if (counter == (buttonArray.length * buttonArray[0].length - 1)) {
                        System.out.println("Grattis! Du har klarat spelet!");
                        return (true);
                    }

                } else
                    break;
                counter++;

            }
        }

        System.out.println(counter + " Countern");

        return (false);


    }

    public static int getMoveCounter() {
        return moveCounter;
    }


}

