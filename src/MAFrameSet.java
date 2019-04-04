//package edu.neu.csye6200.ma;

/*
Create a  MAFrameSet that holds multiple  MAFrames and can call the  MARule class repeatedly to evolve successive  MAFrames

Solution:
Arraylist is created to hold the frames in a list .
Everytime for New Frame to be created old frame is passed to Rule object to get the next frame .
 */

import java.util.ArrayList;
import java.util.Observable;
import java.util.logging.Logger;


public class MAFrameSet extends Observable implements Runnable{

    private static Logger log = Logger.getLogger(MAFrameSet.class.getName());
    private ArrayList<MAFrame> framearray = new ArrayList<>(); // List to hold the frame -> This can be used to
    private MARule rule;
    private int delay;
    private int numberofFrames;
    private boolean suspended = false;
    private int framecount =0;
    private boolean rewind = false;



    public MAFrameSet (MARule rule,int delay,int numberofFrames){
        log.info("New Frame Set is created");
        this.rule= rule;
        this.delay= delay;
        this.numberofFrames=numberofFrames;

    }


    public void doAction(int i) { // tell our subscribing friends
        setChanged();
        notifyObservers(this.framearray.get(i));
        log.info("Sent Notification to Canvas");

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
            if (this.numberofFrames < 100 && this.numberofFrames > 0) {

             if(!rewind) {
                 this.framearray.add(new MAFrame(this.numberofFrames, this.numberofFrames));
                 doAction(0);
                 for (int i = 0; i < this.numberofFrames; i++) {
                     MAFrame frame = this.rule.getNewframe(this.framearray.get(i), i);
                     MAFrame newframe = new MAFrame(this.numberofFrames, this.numberofFrames);
                     newframe.setCells(frame.getCells());
                     this.framearray.add(newframe);
                     framecount++;
                     doAction(i+1);
                     log.info("frames" + i);
                     checkstatus();
                     if(rewind){
                         reverse();
                         i = framecount;
                     }

                 }
             }



            } else {
                log.warning("Number of frameset selected is beyond 100 or less than zero");
            }
        } catch (IllegalArgumentException e){
            log.warning("Delay can't be negative");
        }
    }

    public void checkstatus(){

        try {

            Thread.sleep(1000 * this.delay);
            synchronized (this) {
                while (suspended) {
                    wait();
                }
            }
        }
        catch (InterruptedException e) {

            log.warning("Thread Sleep Interupted Exception");
        }

    }
    public void suspend() {
        log.info("Suspended");
        suspended = true;
    }

    public synchronized void resume() {
        log.info("Resumed");
        suspended = false;
        rewind = false;
        notify();
    }


    public void rewind(){
        log.info("Rewinded");
        rewind = true;
    }


    public void reverse(){

            for (int i = framecount; i >= 0; i--) {
                doAction(i);
                log.info("frames" + i);
                checkstatus();
                if(rewind){
                    framecount--;
                }
                else{
                    break;
                }
            }


    }

}
