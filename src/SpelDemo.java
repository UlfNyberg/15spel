import netscape.javascript.JSObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
        addInfoButtonActionListeners();

        createPanelArray();
        addTileButtonsInOrder();

        p.setLayout(new BorderLayout());
        p.add(infoButtonField, BorderLayout.SOUTH);
        p.add(gameBoard, BorderLayout.CENTER);
        add(p);
    }
    public void createPanelArray(){

        for(int i = 0; i < GRID_ROWS; i++){
            for(int j = 0; j < GRID_COLS; j++){
                panelArray[i][j] = new JPanel();
                gameBoard.add(panelArray[i][j]);
            }
        }
    }
    public void addTileButtonsInOrder(){
        int count = 1;

        for(int i = 0; i < GRID_ROWS; i++){
            for(int j = 0; j < GRID_COLS; j++){
                panelArray[i][j].add(new JButton(Integer.toString(count)));

                if (GRID_ROWS * GRID_COLS == count){
                    JButton knappen = (JButton) panelArray[i][j].getComponent(0);
                    knappen.setOpaque(true);
                    knappen.setBackground(Color.black);
                }

                count++;
            }
        }
    }

    public void addTileButtonsInDisOrder() {
        addTileButtonsInOrder();

        List<JButton> objects = new ArrayList<>();

        for (int j = 0; j < GRID_ROWS; j++) {
            for (int k = 0; k < GRID_COLS; k++) {
                objects.add((JButton) panelArray [j] [k].getComponent(0));
            }
        }

        Collections.shuffle(objects);

        gameBoard.removeAll();
        int index = 0;
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLS; j++) {
                JButton knapp = objects.get(index);
                panelArray[i][j].remove(0);
                panelArray[i][j].add(knapp);
                gameBoard.add(panelArray)
                gameBoard.updateUI();
                index++;
            }
        }
    }

        public static void main (String[]args){

            new SpelDemo();

        }

        @Override
        public void actionPerformed (ActionEvent e){

        }

        public void addInfoButtonActionListeners () {
            newGameButton.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    addTileButtonsInDisOrder();
                }
            });
            sortInRightOrder.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

        }
    }
