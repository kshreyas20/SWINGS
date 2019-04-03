//package edu.neu.csye6200.ma;

/*
Create a  MAFrameSet that holds multiple  MAFrames and can call the  MARule class repeatedly to evolve successive  MAFrames

Solution:
Arraylist is created to hold the frames in a list .
Everytime for New Frame to be created old frame is passed to Rule object to get the next frame .
 */

import java.util.ArrayList;
import java.util.Observable;




public class MAFrameSet extends Observable implements Runnable{

    private ArrayList<MAFrame> framearray = new ArrayList<>(); // List to hold the frame -> This can be used to
    private static int yaxis = 0; // Position of GUI index
    private static int xaxis = 0; // Position of GUI index
    private MARule rule;
    private int delay;
    private int numberofFrames;
    boolean suspended = false;
    private int framecount =0;
    boolean rewind = false;



    public MAFrameSet (MARule rule,int delay,int numberofFrames){
        this.rule= rule;
        this.delay= delay;
        this.numberofFrames=numberofFrames;

    }


    public void doAction(int i) { // tell our subscribing friends
        setChanged();
        notifyObservers(this.framearray.get(i));
        System.out.println("Sent Notification to Canvas");

    }

      /*
        Inserting frame to framset for each iteration
        Rule is determines the frame pattern
        NumberOfFrame is equal to NumberofRows and Numberof Columns
     */

    @Override
    public void run() {

        try {
            this.framearray.clear();
            if (this.numberofFrames < 100) {

             if(!rewind) {
                 this.framearray.add(new MAFrame(this.numberofFrames, this.numberofFrames));
                 doAction(0);
                 for (int i = 0; i < this.numberofFrames; i++) {
                     MAFrame frame = this.rule.getNewframe(this.framearray.get(i), i);
                     MAFrame newframe = new MAFrame(this.numberofFrames, this.numberofFrames);
                     newframe.setCells(frame.getCells());
                     this.framearray.add(newframe);
                     framecount++;
                     doAction(i);
                     System.out.println("frames" + i);
                     Thread.sleep(1000 * this.delay);
                     synchronized (this) {
                         while (suspended) {
                             wait();
                         }
                     }
                     if(rewind){
                         reverse();
                         i = framecount;
                     }
                     else{
                         continue;
                     }
                 }
             }



            } else {
                System.out.println("Number of frameset selected is beyond 100");
            }
        } catch (InterruptedException e) {

            System.out.println("Thread Sleep Interupted Exception");
        }
    }

    public void suspend() {
        suspended = true;
    }

    public synchronized void resume() {
        suspended = false;
        rewind = false;
        notify();
    }


    public void rewind(){

        rewind = true;
    }


    public void reverse(){

        try {
            for (int i = framecount; i >= 0; i--) {
                doAction(i);
                System.out.println("frames" + i);
                Thread.sleep(1000 * this.delay);
                synchronized (this) {
                    while (suspended) {
                        wait();
                    }
                }
                if(rewind){
                    framecount--;
                    continue;

                }
                else{
                    break;
                }
            }
        }
            catch (InterruptedException e) {

            System.out.println("Thread Sleep Interupted Exception");
        }
    }

}
