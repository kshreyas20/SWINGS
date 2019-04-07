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
    private MARule rule;  // Rule to select the Pattern
    private int delay; // Delay in 100 ms
    private int numberofFrames; // Defines the row and columns in frames
    private boolean suspended = false; //  Pause  execution Flag
    private int framecount =0; //To Rewind /Resume the frame set this counter is used
    private boolean rewind = false; // Rewind execution Flag


    // Constructor to Create the FrameSet It takes 3 Input value

    public MAFrameSet (MARule rule,int delay,int numberofFrames){
        log.info("New Frame Set is created");
        this.rule= rule;    // Defines the Pattern to be generated
        this.delay= delay;  // Delay each frame iteration
        this.numberofFrames=numberofFrames; // Maximum number of Frames this is equal to row /column in each frame

    }


    // Event and Frame Object is notified to the Canvas

    public void doAction(int i) {
        setChanged();
        notifyObservers(this.framearray.get(i)); // Each frame is sent to canvas instead of target cell - In future if need
        log.info("Sent Notification to Canvas");

    }

      /*
        Runnable thread - It can be paused/Resume/Rewinded based on the boolean parameters
        Inserting frame to framset for each iteration
        Rule is determines the frame pattern
        NumberOfFrame is equal to NumberofRows and Numberof Columns

     */

    @Override
    public void run() {

        try {
            this.framearray.removeAll(this.framearray);
            if (this.numberofFrames < 100 && this.numberofFrames > 0) {

             if(!rewind) {
                 this.framearray.add(new MAFrame(this.numberofFrames, this.numberofFrames));
                 doAction(0);
                 for (int i = 0; i < this.numberofFrames; i++) {
                     MAFrame frame = this.rule.getNewframe(this.framearray.get(i), i);  // Get Modified Frame from the Rule
                     MAFrame newframe = new MAFrame(this.numberofFrames, this.numberofFrames); // Create a new Frame
                     newframe.setCells(frame.getCells()); // Set the New Frame with values which got Rule Frame => Basically create New Frame storing it in frameset and retaining the old frame
                     this.framearray.add(newframe);
                     framecount++;
                     doAction(i+1);
                     log.info("frames" + i);
                     Thread.sleep(100 * this.delay); // Delaying the thread - Sleep time

                     synchronized (this) {              // Pause Resume Thread
                         while (suspended) {
                             wait();
                         }
                     }
                     if(rewind){
                         reverse();             // Reverse - Rewind the frame execution
                         i = framecount-1;
                     }
                     if(framecount==0){
                         this.framearray.removeAll(this.framearray);
                         this.framearray.add(new MAFrame(this.numberofFrames, this.numberofFrames)); // if the frameset is 0 bring it back to black screen
                         doAction(0);
                         break;
                     }

                 }
             }


            } else {
                log.warning("Number of frameset selected is beyond 100 or less than zero"); // warning if the Framset selected is more than 100
            }
        } catch (IllegalArgumentException e){
            log.warning("Delay can't be negative"); // Negative integer value in Inputs
        }catch (IndexOutOfBoundsException e) {
            log.warning("Index is out of range"); // Index out of Range catch
        } catch (InterruptedException e) {

            log.warning("Thread Sleep Interupted Exception"); // If there is interupted -> Like Stop button come out of the thread
        }

    }

    // To pause the Frame execution Flag

    public void suspend() {
        log.info("Suspended");
        suspended = true;
    }

    // To Resume the Frame Execution -> It is applicable to pause/resume and rewind/resume Flag

    public synchronized void resume() {
        log.info("Resumed");
        suspended = false;
        rewind = false;
        notify();
    }

    // To Rewind or Reverse the Frame Execution Flag

    public void rewind(){
        log.info("Rewinded");
        rewind = true;
    }

    // To Reverse the Frame execution and delete the frame from frameset

    public void reverse(){

        try {
            for (int i = framecount; i >= 0; i--) {
                doAction(i);
                log.info("frames" + i);
                Thread.sleep(100 * this.delay);
                synchronized (this) {
                    while (suspended) {
                        wait();
                    }
                }
                if (rewind && framecount !=0) {         // To reduce the count of the static counter -> Useful in replaying /resuming back the flow
                    framecount--;
                } else {
                    rewind = false;    // Going back to Run method - Falling back to normal mode
                    break;
                }
                this.framearray.remove(i);   // To remove the frame from the Framset
            }
        }  catch (InterruptedException e) {

            log.warning("Thread Sleep Interupted Exception"); // Thread Sleep execption catch
        }

    }

}
