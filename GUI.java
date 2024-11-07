import java.awt.*;
import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GUI implements ActionListener{
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel controlPanel;

    private boolean playerOne = true;

    private JButton[][] buttonGrid;
    private JButton replay;
    private int numRows = 3;
    private int numCols = 3;

    public GUI(){
        frame = new JFrame();
        mainPanel = new JPanel();
        controlPanel = new JPanel();
        buttonGrid = new JButton[numRows][numCols];

        mainPanel.setBorder(BorderFactory.createEmptyBorder(30,30,10,30));
        mainPanel.setLayout(new GridLayout(3, 3));
        mainPanel.setSize(300, 300);        

        initializeButtons();
        setUpFrame();
    }

    public void initializeButtons(){
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                buttonGrid[i][j] = new JButton("");
                buttonGrid[i][j].setBackground(Color.WHITE);
                buttonGrid[i][j].setFont(new Font("Arial", Font.BOLD, 40));
                buttonGrid[i][j].setFocusPainted(false);
                buttonGrid[i][j].addActionListener(this);
                mainPanel.add(buttonGrid[i][j]);
            }
        }

        replay= new JButton("Play again.");
        replay.addActionListener(this);
        controlPanel.add(replay);
    }

    public void setUpFrame(){
        frame.add(mainPanel, BorderLayout.CENTER); //add panel to frame
        frame.add(controlPanel, BorderLayout.SOUTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //on frame close
        frame.setTitle("My GUI");
        frame.pack();
        frame.setVisible(true);
        frame.setSize(500, 500);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == replay){
            resetGrid();
            playerOne = true;
        }

        if(playerOne && e.getSource() != replay){
            //set Button text to X
            JButton buttonClicked = (JButton)e.getSource();
            if(!buttonClicked.getText().equals("")){
                JOptionPane.showMessageDialog(frame, "Cell already played in.");
            }
            else{
                buttonClicked.setText("X");
                buttonClicked.setForeground(Color.RED);
                playerOne = false;
            }
        }

        else if(!playerOne && e.getSource() != replay){
            //set Button to O
            JButton buttonClicked = (JButton)e.getSource();
            if(!buttonClicked.getText().equals("")){
                JOptionPane.showMessageDialog(frame, "Cell already played in.");
            }
            else{
                buttonClicked.setText("O");
                buttonClicked.setForeground(Color.BLUE);
                playerOne = true;
            }
        }

        String winner = checkGrid();
        if(winner.equals("X")){
            JOptionPane.showMessageDialog(frame, "Player One wins.");
            resetGrid();
            playerOne= true;
        }
        else if(winner.equals("O")){
            JOptionPane.showMessageDialog(frame, "Player Two wins.");
            resetGrid();
            playerOne = true;
        }
        else if(winner.equals("Draw")){
            JOptionPane.showMessageDialog(frame, "It's a draw!");
            resetGrid();
            playerOne = true;
        }
    }

    public String checkGrid(){
        if(isGridFull()){
            return "Draw";
        }
        //check vertical and horizontal rows
        for(int i = 0; i<3; i++){
            if(buttonGrid[i][0].getText().equals(buttonGrid[i][1].getText()) && buttonGrid[i][1].getText().equals(buttonGrid[i][2].getText()) && buttonGrid[i][0].getText().equals(buttonGrid[i][2].getText())){
                return buttonGrid[i][0].getText();
            }
            else if(buttonGrid[0][i].getText().equals(buttonGrid[1][i].getText()) && buttonGrid[1][i].getText().equals(buttonGrid[2][i].getText()) && buttonGrid[0][i].getText().equals(buttonGrid[2][i].getText())){
                return buttonGrid[0][i].getText();
            }
        }

        //check diagonals
        if(buttonGrid[0][0].getText().equals(buttonGrid[1][1].getText()) && buttonGrid[1][1].getText().equals(buttonGrid[2][2].getText()) && buttonGrid[0][0].getText().equals(buttonGrid[2][2].getText())){
            return buttonGrid[0][0].getText();
        }
        else if(buttonGrid[0][2].getText().equals(buttonGrid[1][1].getText()) && buttonGrid[1][1].getText().equals(buttonGrid[2][0].getText()) && buttonGrid[0][2].getText().equals(buttonGrid[2][0].getText())){
            return buttonGrid[0][2].getText();
        }

        return "";
    }

    public boolean isGridFull(){
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                if(buttonGrid[i][j].getText().equals("")){
                    return false;
                }
            }
        }

        return true;
    }

    public void resetGrid(){
        for(int i=0; i<numRows; i++){
            for(int j=0; j<numCols; j++){
                buttonGrid[i][j].setText("");
            }
        }
    }

    public static void main(String[] args) {
        new GUI();
    }
}