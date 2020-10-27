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

    private int gridRows = 4;
    private int gridCols = 4;
    private int WINDOW_WIDTH = 400;
    private int WINDOW_HEIGHT = 400;

    protected JPanel p = new JPanel(new BorderLayout());
    protected JPanel gameBoard = new JPanel(new GridLayout(gridRows, gridCols));
    protected JPanel infoButtonFieldUpper = new JPanel();
    protected JPanel infoButtonFieldLower = new JPanel();
    protected JPanel mainButtonField = new JPanel(new BorderLayout());

    protected JButton newGameButton = new JButton("New Game");
    protected JButton sortInRightOrder = new JButton("Sort");
    protected JButton exitGameButton = new JButton("Quit");
    protected JButton resizeGridButton = new JButton("Resize");

    protected JLabel countTextLabel = new JLabel("Move count: ");
    protected JLabel countNr = new JLabel("0");
    protected JLabel rowLabel = new JLabel("Row");
    protected JLabel colLabel = new JLabel("Col");

    protected JTextField rowTextField = new JTextField("4",2);
    protected JTextField colTextField = new JTextField("4",2);

    protected JButton[][] buttonArray = new JButton[gridRows][gridCols];

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
        infoButtonFieldLower.add(resizeGridButton);

        addInfoButtonActionListeners();
        buttonArray = initiateButtonArray(gridCols,gridRows);
        addTileActionListeners();

        buttonArray = GameLogic.createButtonDisorder(buttonArray);
        updateGameBoard();

        mainButtonField.add(infoButtonFieldUpper, BorderLayout.NORTH);
        mainButtonField.add(infoButtonFieldLower, BorderLayout.SOUTH);

        p.setLayout(new BorderLayout());
        p.add(mainButtonField, BorderLayout.SOUTH);
        p.add(gameBoard, BorderLayout.CENTER);
        add(p);
    }

    public JButton[][] initiateButtonArray(int gridRows, int gridCols) {

        this.gridRows = gridRows;
        this.gridCols = gridCols;

        int count = 1;
        JButton[][] jButtonArray = new JButton[gridRows][gridCols];

        try {
            UIManager.setLookAndFeel(UIManager.getCrossPlatformLookAndFeelClassName());
            for (int y = 0; y < gridRows; y++) {
                for (int x = 0; x < gridCols; x++) {
                    if (!(count == (gridCols * gridRows)))
                        jButtonArray[y][x] = new JButton(Integer.toString(count));
                    else {
                        jButtonArray[y][x] = new JButton("");
                        jButtonArray[y][x].setBackground(Color.black);
                    }
                    count++;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jButtonArray;
    }

    public void updateGameBoard() {
        gameBoard.removeAll();
        for (int y = 0; y < buttonArray.length; y++) {
            for (int x = 0;x < buttonArray[y].length; x++) {
                gameBoard.add(buttonArray[y][x]);
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
                buttonArray = initiateButtonArray(gridRows,gridCols);
                addTileActionListeners();
                updateGameBoard();
            }
        });
        exitGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        resizeGridButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = gridRows;
                int col = gridCols;
                try {
                    row = Integer.parseInt(rowTextField.getText());
                    col = Integer.parseInt(colTextField.getText());
                    setGridSize(row,col);
                }catch(NumberFormatException x){
                    rowTextField.setText(Integer.toString(gridRows));
                    colTextField.setText(Integer.toString(gridCols));
                }
            }
        });
    }

    public void setGridSize(int row, int col){
        buttonArray = initiateButtonArray(row,col);
        buttonArray = GameLogic.createButtonDisorder(buttonArray);
        gameBoard.setLayout(new GridLayout(row,col));
        addTileActionListeners();
        updateGameBoard();

    }

    public void addTileActionListeners() {
        ActionListener listener = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonArray = GameLogic.swapWithBlackTile(buttonArray, (JButton) e.getSource());
                if(GameLogic.getIsVictory()){
                    showVictoryMessage();
                    GameLogic.resetMoveCounter();

                }
                updateGameBoard();
            }
        };

        for (int i = 0; i < gridRows; i++) {
            for (int j = 0; j < gridCols; j++) {
                buttonArray[i][j].addActionListener(listener);
            }
        }
    }

    public static void main(String[] args) {
        new GameGUI();
    }
}
