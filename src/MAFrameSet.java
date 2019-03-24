//package edu.neu.csye6200.ma;

/*
Create a  MAFrameSet that holds multiple  MAFrames and can call the  MARule class repeatedly to evolve successive  MAFrames

Solution:
Arraylist is created to hold the frames in a list .
Everytime for New Frame to be created old frame is passed to Rule object to get the next frame .
 */

import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MAFrameSet {

    private ArrayList<MAFrame> framearray = new ArrayList<>(); // List to hold the frame -> This can be used to
    private static int yaxis = 0; // Position of GUI index
    private static int xaxis = 0; // Position of GUI index



    /*
     Graphical display of the Frame
                MAFrame => JFrame
                Cell => button
                States => Colour Text etc are used to represent the active cell

     */

    public void view (MAFrame displayFrame) {



        JFrame frame = new JFrame();
        frame.setTitle("Demonstrate valid 2D Mobile Automata");
        frame.setSize(600,600);
        frame.setLocation(xaxis, yaxis);
        frame.setResizable(true);
        frame.setLayout(new GridLayout(displayFrame.getRow(), displayFrame.getColumn()));
        frame.setBackground(Color.BLACK);

        System.out.print("======display=======");
        System.out.println("");
        for (int i = 0; i < displayFrame.getRow(); i++) {
            for (int j = 0; j < displayFrame.getColumn(); j++) {

                System.out.print(displayFrame.getCell(i, j).getColour()+"       "); // Currently display colour (no text => MOBILE2 case)
                JButton button = new JButton();
                if (displayFrame.getCell(i, j).getShade().equals("DARK")) {
                    if (displayFrame.getCell(i, j).getColour().equals("BLACK")) {
                        button.setBackground(Color.BLACK.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("WHITE")) {
                        button.setBackground(Color.WHITE.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("GREEN")) {
                        button.setBackground(Color.GREEN.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("BLUE")) {
                        button.setBackground(Color.BLUE.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("RED")) {
                        button.setBackground(Color.RED.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("YELLOW")) {
                        button.setBackground(Color.YELLOW.darker());
                    }

                } else if (displayFrame.getCell(i, j).getShade().equals("BRIGHT")) {
                    if (displayFrame.getCell(i, j).getColour().equals("BLACK")) {
                        button.setBackground(Color.BLACK.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("WHITE")) {
                        button.setBackground(Color.WHITE.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("GREEN")) {
                        button.setBackground(Color.GREEN.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("BLUE")) {
                        button.setBackground(Color.BLUE.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("RED")) {
                        button.setBackground(Color.RED.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("YELLOW")) {
                        button.setBackground(Color.YELLOW.brighter());
                    }

                } else {
                    if (displayFrame.getCell(i, j).getColour().equals("BLACK")) {
                        button.setBackground(Color.BLACK);
                    } else if (displayFrame.getCell(i, j).getColour().equals("WHITE")) {
                        button.setBackground(Color.WHITE);
                    } else if (displayFrame.getCell(i, j).getColour().equals("GREEN")) {
                        button.setBackground(Color.GREEN);
                    } else if (displayFrame.getCell(i, j).getColour().equals("BLUE")) {
                        button.setBackground(Color.BLUE);
                    } else if (displayFrame.getCell(i, j).getColour().equals("RED")) {
                        button.setBackground(Color.RED);
                    } else if (displayFrame.getCell(i, j).getColour().equals("YELLOW")) {
                        button.setBackground(Color.YELLOW);

                    }
                    button.setOpaque(true);
                    button.setBorderPainted(false);
                    button.setRequestFocusEnabled(displayFrame.getCell(i, j).isEffects());
                    if (!displayFrame.getCell(i, j).getTextfield().equals("NONE")) {
                        button.setText(displayFrame.getCell(i, j).getTextfield());
                    }
                    frame.add(button);
                }
            }
            System.out.println("");
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // If you click on close all the frames will closed
            yaxis = yaxis + 50;
            xaxis = xaxis + 50;
        }
        System.out.println("=====END======");
        System.out.println("");

    }

    /*
        Inserting frame to framset for each iteration
        Rule is determines the frame pattern
        NumberOfFrame is equal to NumberofRows and Numberof Columns
     */
    public void insertFrame(int numberOfFrame,MARule rule){

        this.framearray.clear();
        if(numberOfFrame < 100) {

            this.framearray.add(new MAFrame(numberOfFrame, numberOfFrame));
            for (int i = 0; i < numberOfFrame; i++) {
                MAFrame frame = rule.getNewframe(this.framearray.get(i), i);
                MAFrame newframe = new MAFrame(numberOfFrame, numberOfFrame);
                newframe.setCells(frame.getCells());
                this.framearray.add(newframe);

            }
        }
        else {
            System.out.println("Number of frameset selected is beyond 100");
        }


    }

    // Display the frame in JFrame as well as in console

    public void displayGUI(int numberOfFrame){

        try {
            for (int i = 0; i < numberOfFrame; i++) {
                this.view(this.framearray.get(i));

            }
        }
        catch (IndexOutOfBoundsException e){
            System.out.println("Index is more than in arraylist");
        }

    }



    public static void main(String[] args) {


        MAFrameSet frames = new MAFrameSet ();
        System.out.println("Rule list");
        System.out.println(MARule.ruleSet);

        /*
        MARule chessrule = new MARule("CHESS");
        frames.insertFrame(8,chessrule);
        frames.displayGUI(8);
        */
        /*
       MARule steprule = new MARule("STEPS");
       frames.insertFrame(20,steprule);
       frames.displayGUI(20);
       */

        /*
        MARule pyramidrule = new MARule("PYRAMID");
        frames.insertFrame(11,pyramidrule);
        frames.displayGUI(11);
        */


        /*
        MARule mobilerule = new MARule("MOBILE");
        frames.insertFrame(8,mobilerule);
        frames.displayGUI(8);
        */


        /*
        MARule mobilerule2 = new MARule("MOBILE2");
        frames.insertFrame(10,mobilerule2);
        frames.displayGUI(10);
        */






    }

}
