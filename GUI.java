import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{
    private JFrame frame;
    private JPanel panel;
    private JPanel panel1;
    private GridLayout grid;

    private boolean playerOne = true;
    private boolean playerTwo = false;

    private JButton[][] buttonGrid;
    private JButton replay;
    private int numRows = 3;
    private int numCols = 3;

    public GUI(){
        frame = new JFrame();
        panel = new JPanel();
        panel1 = new JPanel();
        buttonGrid = new JButton[numRows][numCols];

        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                buttonGrid[i][j] = new JButton("");
                buttonGrid[i][j].setBackground(Color.WHITE);

                panel.add(buttonGrid[i][j]);
                buttonGrid[i][j].addActionListener(this);
            }
        }

        grid = new GridLayout(3, 3);

        panel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        panel.setLayout(grid);
        panel.setSize(300, 300);

        panel1.setBorder(BorderFactory.createEmptyBorder());
        replay= new JButton("Play again.");
        panel1.add(replay);
        replay.addActionListener(this);

        frame.add(panel, BorderLayout.CENTER); //add panel to frame
        frame.add(panel1, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //on frame close
        frame.setTitle("My GUI");
        frame.pack();
        frame.setVisible(true);

    }

    public static void main(String[] args) {
        new GUI();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == replay){
            for(int i=0; i<numRows; i++){
                for(int j=0; j<numCols; j++){
                    buttonGrid[i][j].setText("");
                }
            }

            playerOne = true;
            playerTwo = false;
        }

        if(playerOne && e.getSource() != replay){
            //set Button text to X
            JButton buttonClicked = (JButton)e.getSource();
            buttonClicked.setText("X");
            playerOne = false;
            playerTwo = true;
        }

        else if(playerTwo && e.getSource() != replay){
            //set Button to O
            JButton buttonClicked = (JButton)e.getSource();
            buttonClicked.setText("O");
            playerTwo = false;
            playerOne = true;
        }

        String winner = checkGrid();
        if(winner.equals("P1")){
            System.out.println("P1 wins.");
        }
        else if(winner.equals("P2")){
            System.out.println("P2 wins.");
        }
    }

    public String checkGrid(){
        int countVerticalX = 0;
        int countVerticalO = 0;
        int countHorizontalX = 0;
        int countHorizontalO = 0;
        int countDiagonalX = 0;
        int countDiagonalO = 0;

        //check horizontal rows
        for(int i=0; i<numRows; i++){
            countHorizontalX = 0; //reset counts
            countHorizontalO = 0;
            for(int j=0; j<numCols; j++){
                if(buttonGrid[i][j].getText().equals("X")){
                    countHorizontalX++;
                    if(countHorizontalX == 3){
                        return "P1";
                    }
                }
                else if(buttonGrid[i][j].getText().equals("O")){
                    countHorizontalO++;
                    if(countHorizontalO == 3){
                        return "P2";
                    }
                }
            }
        }

        //check vertical rows
        for(int i=0; i<numRows; i++){
            countVerticalX = 0;
            countVerticalO = 0;
            for(int j=0; j<numCols; j++){
                if(buttonGrid[j][i].getText().equals("X")){
                    countVerticalX++;
                    if(countVerticalX == 3){
                        return "P1";
                    }
                }
                else if(buttonGrid[j][i].getText().equals("O")){
                    countVerticalO++;
                    if(countVerticalO == 3){
                        return "P2";
                    }
                }
            }
        }

        //check diagonal rows
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                if(i==j && buttonGrid[j][i].getText().equals("X")){
                    countDiagonalX++;
                    if(countDiagonalX == 3){
                        return "P1";
                    }
                }
                else if(i==j && buttonGrid[j][i].getText().equals("O")){
                    countDiagonalO++;
                    if(countDiagonalO == 3){
                        return "P2";
                    }
                }
            }
        }

        return "";
    }
}