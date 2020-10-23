import netscape.javascript.JSObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
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

    protected JButton[][] buttonArray = new JButton[GRID_ROWS][GRID_COLS];


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
        addInfoButtonActionListeners();

        createButtonArray();
        updateGameBoard();

        p.setLayout(new BorderLayout());
        p.add(infoButtonField, BorderLayout.SOUTH);
        p.add(gameBoard, BorderLayout.CENTER);
        add(p);
    }
    public void createButtonArray(){

        int count = 1;
        for (int i = 0; i < GRID_COLS; i++) {
            for (int j = 0; j < GRID_ROWS; j++) {
                if(!(count == (GRID_COLS*GRID_ROWS)))
                    buttonArray[i][j] = new JButton(Integer.toString(count));
                else if(count == (GRID_ROWS*GRID_COLS)) {
                    buttonArray[i][j] = new JButton("");
                    buttonArray[i][j].setBackground(Color.black);
                }
                count++;
            }
        }
    }
    public void updateGameBoard(){
        gameBoard.removeAll();
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLS; j++) {
                gameBoard.add(buttonArray[i][j]);
            }
        }
        gameBoard.updateUI();
    }

        @Override
        public void actionPerformed (ActionEvent e){

        }

        public void addInfoButtonActionListeners () {
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    buttonArray = GameLogic.createButtonDisrder(buttonArray);
                    updateGameBoard();
                }
            });
            sortInRightOrder.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    createButtonArray();
                    updateGameBoard();
                }
            });

        }

    public static void main (String[]args){

        new SpelDemo();

    }
}
