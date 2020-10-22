import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by: Ulf Nyberg
 * Date: 2020-10-22
 * Time: 09:09
 * Project: 15spel
 * Copyright: MIT
 */
public class SpelDemo extends JFrame implements ActionListener {

    private final int GRID_ROWS = 4;
    private final int GRID_COLS = 4;

    JPanel p = new JPanel(new BorderLayout());
    JPanel gameBoard = new JPanel(new GridLayout(GRID_ROWS, GRID_COLS));
    JPanel infoButtonField = new JPanel();

    JButton newGameButton = new JButton("New Game");
    JButton sortInRightOrder = new JButton("Sort");

    protected JPanel[][] panelArray = new JPanel[GRID_ROWS][GRID_COLS];


    SpelDemo(){

        constructGameBoard();
        setSize(400,400);
        setTitle("15-spel");
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

    }
    public void constructGameBoard(){

        infoButtonField.add(newGameButton);
        infoButtonField.add(sortInRightOrder);

        p.setLayout(new BorderLayout());
        p.add(infoButtonField, BorderLayout.SOUTH);
        p.add(gameBoard, BorderLayout.CENTER);
        add(p);
    }
    public void createPanelArray(){

        for(int i = 0; i < GRID_ROWS; i++){
            for(int j = 0; j < GRID_COLS; j++){
                panelArray[i][j] = new JPanel();
            }
        }
    }


    public static void main(String[] args) {

        new SpelDemo();

    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
