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
import java.util.Observable;




public class MAFrameSet extends Observable implements Runnable{

    private ArrayList<MAFrame> framearray = new ArrayList<>(); // List to hold the frame -> This can be used to
    private static int yaxis = 0; // Position of GUI index
    private static int xaxis = 0; // Position of GUI index
    private MARule rule;
    private int delay;
    private int numberofFrames;



    public MAFrameSet (MARule rule,int delay,int numberofFrames){
        this.rule= rule;
        this.delay= delay;
        this.numberofFrames=numberofFrames;

    }


    /*
        Inserting frame to framset for each iteration
        Rule is determines the frame pattern
        NumberOfFrame is equal to NumberofRows and Numberof Columns
     */

    public void insertFrame() {

        try {
            this.framearray.clear();
            if (this.numberofFrames < 100) {


                this.framearray.add(new MAFrame(this.numberofFrames, this.numberofFrames));
                doAction(0);
                for (int i = 0; i < this.numberofFrames; i++) {
                    MAFrame frame = this.rule.getNewframe(this.framearray.get(i), i);
                    MAFrame newframe = new MAFrame(this.numberofFrames, this.numberofFrames);
                    newframe.setCells(frame.getCells());
                    this.framearray.add(newframe);
                    doAction(i+1);
                    System.out.println("frames" + i);
                    Thread.sleep(1000 * delay);
                }
            } else {
                System.out.println("Number of frameset selected is beyond 100");
            }
        } catch (InterruptedException e) {

            System.out.println("Thread Sleep Interupted Exception");
        }
    }



    public void doAction(int i) { // tell our subscribing friends
        setChanged();
        notifyObservers(this.framearray.get(i));
        System.out.println("Sent Notification to Canvas");

    }


    @Override
    public void run() {

        insertFrame();
    }


}
