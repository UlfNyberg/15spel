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

    private int GRID_ROWS = 4;
    private int GRID_COLS = 4;
    private int WINDOW_WIDTH = 400;
    private int WINDOW_HEIGHT = 400;

    protected JPanel p = new JPanel(new BorderLayout());
    protected JPanel gameBoard = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS));
    protected JPanel infoButtonFieldUpper = new JPanel();
    protected JPanel infoButtonFieldLower = new JPanel();
    protected JPanel mainButtonField = new JPanel(new BorderLayout());

    protected JButton newGameButton = new JButton("New Game");
    protected JButton sortInRightOrder = new JButton("Sort");
    protected JButton exitGameButton = new JButton("Quit");
    protected JLabel countTextLabel = new JLabel("Move count: ");
    protected JLabel countNr = new JLabel("0");

    protected JLabel rowLabel = new JLabel("Row");
    protected JLabel colLabel = new JLabel("Col");
    protected JTextField rowTextField = new JTextField(GRID_ROWS);
    protected JTextField colTextField = new JTextField(GRID_COLS);


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

        infoButtonFieldUpper.add(newGameButton);
        infoButtonFieldUpper.add(sortInRightOrder);
        infoButtonFieldUpper.add(countTextLabel);
        infoButtonFieldUpper.add(countNr);
        infoButtonFieldUpper.add(exitGameButton);

        infoButtonFieldLower.add(rowLabel);
        infoButtonFieldLower.add(rowTextField);
        infoButtonFieldLower.add(colLabel);
        infoButtonFieldLower.add(colTextField);

        addInfoButtonActionListeners();
        initiateButtonArray();
        buttonArray = GameLogic.createButtonDisorder(buttonArray);
        updateGameBoard();

        mainButtonField.add(infoButtonFieldUpper, BorderLayout.NORTH);
        mainButtonField.add(infoButtonFieldLower, BorderLayout.SOUTH);

        p.setLayout(new BorderLayout());
        p.add(mainButtonField, BorderLayout.SOUTH);
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
