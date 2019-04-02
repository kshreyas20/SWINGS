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
import java.io.IOException;
import java.io.PrintStream;


public class MAFrameSet {

    private ArrayList<MAFrame> framearray = new ArrayList<>(); // List to hold the frame -> This can be used to
    private static int yaxis = 0; // Position of GUI index
    private static int xaxis = 0; // Position of GUI index



    /*
     Graphical display of the Frame
                MAFrame => JFrame
                Cell => button
                States => Colour/Text etc are used to represent the active cell

     */

    private void view(MAFrame displayFrame) {



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
                    switch (displayFrame.getCell(i, j).getColour()) {
                        case "BLACK":
                            button.setBackground(Color.BLACK.darker());
                            break;
                        case "WHITE":
                            button.setBackground(Color.WHITE.darker());
                            break;
                        case "GREEN":
                            button.setBackground(Color.GREEN.darker());
                            break;
                        case "BLUE":
                            button.setBackground(Color.BLUE.darker());
                            break;
                        case "RED":
                            button.setBackground(Color.RED.darker());
                            break;
                        case "YELLOW":
                            button.setBackground(Color.YELLOW.darker());
                            break;
                    }

                } else if (displayFrame.getCell(i, j).getShade().equals("BRIGHT")) {
                    switch (displayFrame.getCell(i, j).getColour()) {
                        case "BLACK":
                            button.setBackground(Color.BLACK.brighter());
                            break;
                        case "WHITE":
                            button.setBackground(Color.WHITE.brighter());
                            break;
                        case "GREEN":
                            button.setBackground(Color.GREEN.brighter());
                            break;
                        case "BLUE":
                            button.setBackground(Color.BLUE.brighter());
                            break;
                        case "RED":
                            button.setBackground(Color.RED.brighter());
                            break;
                        case "YELLOW":
                            button.setBackground(Color.YELLOW.brighter());
                            break;
                    }

                } else {
                    switch (displayFrame.getCell(i, j).getColour()) {
                        case "BLACK":
                            button.setBackground(Color.BLACK);
                            break;
                        case "WHITE":
                            button.setBackground(Color.WHITE);
                            break;
                        case "GREEN":
                            button.setBackground(Color.GREEN);
                            break;
                        case "BLUE":
                            button.setBackground(Color.BLUE);
                            break;
                        case "RED":
                            button.setBackground(Color.RED);
                            break;
                        case "YELLOW":
                            button.setBackground(Color.YELLOW);

                            break;
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
            yaxis = yaxis + 5;
            xaxis = xaxis + 5;
        }
        System.out.println("=====END======");
        System.out.println("");

    }

    /*
        Inserting frame to framset for each iteration
        Rule is determines the frame pattern
        NumberOfFrame is equal to NumberofRows and Numberof Columns
     */
    public void insertFrame(int numberOfFrame,MARule rule,int delay){

        try {
            this.framearray.clear();
            if (numberOfFrame < 100) {


                this.framearray.add(new MAFrame(numberOfFrame, numberOfFrame));

                for (int i = 0; i < numberOfFrame; i++) {
                    MAFrame frame = rule.getNewframe(this.framearray.get(i), i);
                    MAFrame newframe = new MAFrame(numberOfFrame, numberOfFrame);
                    newframe.setCells(frame.getCells());
                    this.framearray.add(newframe);
                    System.out.println("frames" + i);
                   // this.view(this.framearray.get(i));
                    Thread.sleep(1000*delay);
                }
            }

            else {
                System.out.println("Number of frameset selected is beyond 100");
            }
        }catch (InterruptedException e){

            System.out.println("Thread Sleep Interupted Exception");
        }


    }

    // Display the frame in JFrame as well as in console

    public void displayGUI(int numberOfFrame){

        if(numberOfFrame > 0) {
            try {
                for (int i = 0; i < numberOfFrame; i++) {
                    this.view(this.framearray.get(i));

                }
            } catch (IndexOutOfBoundsException e) {
                System.out.println("Index is more than in arraylist");
            }
        }
        else {
            System.out.println("number of frame to display less than zero or zero");
        }

    }

    public static void ruleMenu (){

            System.out.println(
                    "                                           MENU                                                   ");
            System.out.println(
                    "                                   "+MARule.ruleSet+"                                             ");
            System.out.println(
                    " -------------------------------------------------------------------------------------------------");
            System.out.println(
                    "|  1: CHESS BOX Pattern                                                                           |");
            System.out.println(
                    "|  2: STEPS BOX Pattern                                                                           |");
            System.out.println(
                    "|  3: TWO SIDED PYRAMID Pattern                                                                   |");
            System.out.println(
                    "|  4: MOBILE AUTOMATA Pattern - 1                                                                 |");
            System.out.println(
                    "|  5: MOBILE AUTOMATA Pattern - 2                                                                 |");
            System.out.println(
                    "|  6: TRIANGLE pattern                                                                            |");
            System.out.println(
                    "|  7: DISPLAY the frames                                                                          |");
            System.out.println(
                    "|  8: Quit � Allow the user to exit from the program                                              |");
            System.out.println(
                    " -------------------------------------------------------------------------------------------------");
            System.out.println("");


    }

    public static void threadCall (){

        boolean done = false;
        MAFrameSet frameset = new MAFrameSet();
        Scanner scan;
        Scanner scan2;
        Scanner sc;
        int delay =1;
        String option = "6";
        int numberofFrames = 10;

        while(!done){

            ruleMenu();


            try {
                System.out.println("Please enter the options");
                sc = new Scanner(System.in);
                option = sc.nextLine();



                switch (option) {
                    case "1":
                        System.out.println("Please enter the number of frames");
                        scan = new Scanner(System.in);
                        numberofFrames = scan.nextInt();
                        System.out.println("Please enter delay");
                        scan2 = new Scanner(System.in);
                        delay = scan2.nextInt();
                        frameset.insertFrame(numberofFrames, new MARule("CHESS"),delay);
                        break;
                    case "2":
                        System.out.println("Please enter the number of frames");
                        scan = new Scanner(System.in);
                        numberofFrames = scan.nextInt();
                        System.out.println("Please enter delay");
                        scan2 = new Scanner(System.in);
                        delay = scan2.nextInt();
                        frameset.insertFrame(numberofFrames, new MARule("STEPS"),delay);
                        break;
                    case "3":
                        System.out.println("Please enter the number of frames");
                        scan = new Scanner(System.in);
                        numberofFrames = scan.nextInt();
                        System.out.println("Please enter delay");
                        scan2 = new Scanner(System.in);
                        delay = scan2.nextInt();
                        frameset.insertFrame(numberofFrames, new MARule("PYRAMID"),delay);
                        break;
                    case "4":
                        System.out.println("Please enter the number of frames");
                        scan = new Scanner(System.in);
                        numberofFrames = scan.nextInt();
                        System.out.println("Please enter delay");
                        scan2 = new Scanner(System.in);
                        delay = scan2.nextInt();
                        frameset.insertFrame(numberofFrames, new MARule("MOBILE"),delay);
                        break;
                    case "5":
                        System.out.println("Please enter the number of frames");
                        scan = new Scanner(System.in);
                        numberofFrames = scan.nextInt();
                        System.out.println("Please enter delay");
                        scan2 = new Scanner(System.in);
                        delay = scan2.nextInt();
                        frameset.insertFrame(numberofFrames, new MARule("MOBILE2"),delay);
                        break;
                    case "6":
                        System.out.println("Please enter the number of frames");
                        scan = new Scanner(System.in);
                        numberofFrames = scan.nextInt();
                        System.out.println("Please enter delay");
                        scan2 = new Scanner(System.in);
                        delay = scan2.nextInt();
                        frameset.insertFrame(numberofFrames, new MARule("TRIANGLE"),delay);
                        break;
                    case "7":
                        System.out.println("Please enter the number of frames");
                        scan2 = new Scanner(System.in);
                        delay = scan2.nextInt();
                        frameset.displayGUI(numberofFrames);
                        break;
                    case "8":
                        System.out.println(".....................................");
                        System.out.println(":      Quiting the program          :");
                        System.out.println(".....................................");
                        frameset.framearray.clear();
                        done = true;
                        break;
                    default:
                        System.out.println("ERROR - Incorrect Option \"" + option + "\" has been selected");
                        System.out.println("");
                }
            }
            catch (InputMismatchException e)
            {
                System.out.println("ERROR - Invalid input from the user");
                break;
            }

        }

    }


    public static void main(String[] args) {



        threadCall();
        System.out.println("Ending the program");



    }

}
