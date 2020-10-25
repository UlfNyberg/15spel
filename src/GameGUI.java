import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by: Ulf Nyberg
 * Date: 2020-10-22
 * Time: 09:09
 * Project: 15spel
 * Copyright: MIT
 */
public class GameGUI extends JFrame {

    private final int GRID_ROWS = 4;
    private final int GRID_COLS = 4;
    private final int WINDOW_WIDTH = 400;
    private final int WINDOW_HEIGHT = 400;

    JPanel p = new JPanel(new BorderLayout());
    JPanel gameBoard = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS));
    JPanel infoButtonField = new JPanel();

    JButton newGameButton = new JButton("New Game");
    JButton sortInRightOrder = new JButton("Sort");
    JButton exitGameButton = new JButton("Quit");
    JLabel countTextLabel = new JLabel("Move count: ");
    JLabel countNr = new JLabel("0");

    protected JButton[][] buttonArray = new JButton[GRID_ROWS][GRID_COLS];


    GameGUI() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        double screenWidth = screenSize.getWidth();
        double screenHeight = screenSize.getHeight();

        constructGameBoard();
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocation((int) screenWidth /2 - 200, (int)screenHeight/2 - 200);
        setTitle("15-spel");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public void constructGameBoard() {

        infoButtonField.add(newGameButton);
        infoButtonField.add(sortInRightOrder);
        infoButtonField.add(countTextLabel);
        infoButtonField.add(countNr);
        infoButtonField.add(exitGameButton);
        addInfoButtonActionListeners();

        initiateButtonArray();
        buttonArray = GameLogic.createButtonDisorder(buttonArray);
        updateGameBoard();

        p.setLayout(new BorderLayout());
        p.add(infoButtonField, BorderLayout.SOUTH);
        p.add(gameBoard, BorderLayout.CENTER);
        add(p);
    }

    public void initiateButtonArray() {

        int count = 1;
        //Mac-anpassad svartknapp-metod med try/catch
        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            for (int i = 0; i < GRID_COLS; i++) {
                for (int j = 0; j < GRID_ROWS; j++) {
                    if (!(count == (GRID_COLS * GRID_ROWS)))
                        buttonArray[i][j] = new JButton(Integer.toString(count));
                    else if (count == (GRID_ROWS * GRID_COLS)) {
                        buttonArray[i][j] = new JButton("");
                        buttonArray[i][j].setBackground(Color.black);
                    }
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        addTileActionListeners();
    }

    public void updateGameBoard() {
        gameBoard.removeAll();
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLS; j++) {
                gameBoard.add(buttonArray[i][j]);
            }
        }
        updateMoveCounter();
        gameBoard.updateUI();
    }

    private void updateMoveCounter() {
        countNr.setText(Integer.toString( GameLogic.getMoveCounter()) );
    }

    private void showVictoryMessage() {
        JOptionPane.showMessageDialog(null,"Seger! Du klarade det på " + GameLogic.getMoveCounter() + " drag!");
    }

    public void addInfoButtonActionListeners() {
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonArray = GameLogic.createButtonDisorder(buttonArray);
                GameLogic.resetMoveCounter();
                updateGameBoard();
            }
        });
        sortInRightOrder.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                initiateButtonArray();
                updateGameBoard();
            }
        });
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }

    public void addTileActionListeners() {
        ActionListener AL = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonArray = GameLogic.swapWithBlackTile(buttonArray, (JButton) e.getSource());
                updateGameBoard();
                if(GameLogic.checkVictoryCondition(buttonArray)){
                    showVictoryMessage();
                    GameLogic.resetMoveCounter();
                    updateGameBoard();
                }
            }
        };
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLS; j++) {
                buttonArray[i][j].addActionListener(AL);

            }
        }
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
