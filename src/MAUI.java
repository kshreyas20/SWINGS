//package edu.neu.csye6200.uia5;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.*;

/**
 * A Test application for the Wolfram Celular Autonomon application
 * @author MMUNSON
 *
 */
public class MAUI extends MAApp {

    private static Logger log = Logger.getLogger(MAUI.class.getName());
    protected JPanel northPanel ; // NorthPanel is used to create Panel for button ,TextField and ComboBoxRule
    protected JButton startBtn ; // Start button used to start the thread
    protected JButton stopBtn ;// Stop button used to stop the thread
    protected JButton PauseBtn;// Pause button used to Pause the thread
    protected JButton ResumeBtn;// Resume button used to Resume the thread in forward direction
    protected JButton RewindBtn;// Rewind button used to Rewind the thread
    protected MACanvas maPanel ;   // To repaint the MAframe object in Canvas
    protected JTextField DelayHeading; // Delay Heading
    protected JTextField TextFieldValue;  // To capture the Delay text input field
    protected JTextField NumberofFrames; // Number of Frames heading
    protected JTextField TextFieldFrames; // To capture the Number of frame text input field
    protected JComboBox ComboBoxRule; // To select the rule
    private Thread threadframset; // To create a Runnable thread
    private MAFrameSet ma; // To create MAFramset


    /**
     * Sample app constructor
     */
    public MAUI() {
        frame.setSize(1000, 880);
        frame.setResizable(true);
        frame.setTitle("UIApp");
        frame.add(getNorthPanel(),BorderLayout.NORTH);

        menuMgr.createDefaultActions(); // Set up default menu items

        showUI(); // Cause the Swing Dispatch thread to display the JFrame
    }


    public JPanel getNorthPanel() {

        System.out.println("Jpanel North");
        northPanel = new JPanel();
      //  northPanel.setLayout(new FlowLayout());
        northPanel.setLayout(new GridLayout());

        startBtn = new JButton("Start");
        startBtn.addActionListener(this); // Allow the app to hear about button pushes - Start Button
        northPanel.add(startBtn);

        stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes - Stop Button
        stopBtn.addActionListener(this);
        northPanel.add(stopBtn);

        PauseBtn = new JButton("Pause");  // Allow the app to hear about button pushes - Pause Button
        PauseBtn.addActionListener(this);
        northPanel.add(PauseBtn);

        ResumeBtn = new JButton("Resum"); // Allow the app to hear about button pushes - Resume Button
        ResumeBtn.addActionListener(this);
        northPanel.add(ResumeBtn);

        RewindBtn = new JButton("Rewind"); // Allow the app to hear about button pushes - Rewind Button
        RewindBtn.addActionListener(this);
        northPanel.add(RewindBtn);

        DelayHeading = new JTextField("Delay");
        DelayHeading.setEditable(false);
        northPanel.add(DelayHeading);
        TextFieldValue = new JTextField(); // Text field to entered for delaying the thread
        TextFieldValue.setColumns(2);
        TextFieldValue.addActionListener(this);
        northPanel.add(TextFieldValue);

        NumberofFrames = new JTextField("No.Frames");
        NumberofFrames.setEditable(false);
        northPanel.add(NumberofFrames);

        TextFieldFrames = new JTextField();
        TextFieldFrames.setColumns(2);
        TextFieldFrames.addActionListener(this);  // Text field to entered for creating the number of frames
        northPanel.add(TextFieldFrames);

        ComboBoxRule = new JComboBox();


        for ( int i=0;i< MARule.ruleSet.size();i++){
            System.out.println(MARule.ruleSet.get(i));
            ComboBoxRule.addItem(MARule.ruleSet.get(i)); // Adding the rule to the comboxRule

        }

        ComboBoxRule.addActionListener(this);
        northPanel.add(ComboBoxRule);


        return northPanel;
    }

    @Override
    public JPanel getMainPanel() {
        return maPanel = new MACanvas();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        try {
            log.info("We received an ActionEvent " + ae); // Logging the event

            String rulelocal = ComboBoxRule.getSelectedItem().toString(); // Get the Rule selected
            int delay = Integer.parseInt(TextFieldValue.getText()); //Get the Delay selected
            int numerofFrames = Integer.parseInt(TextFieldFrames.getText()); // Get the Frame count selected
            log.info(rulelocal + " " + delay+" "+numerofFrames);

            if (ae.getSource() == startBtn) {
                log.info("Start pressed");

                    ma = new MAFrameSet(new MARule(rulelocal), delay, numerofFrames);   // Create a Object of the Frameset
                    ma.addObserver(this.maPanel); //add Observer

                    try {
                        threadframset.interrupt();
                    }catch (Exception e) {
                        log.warning("Pressed Start button without Stopping");  // IF the User press start button continuingly
                    }

                    threadframset = new Thread(ma); // Create a runnable thread
                    threadframset.start(); // Start the thread
                    frame.setSize(numerofFrames * 20, numerofFrames * 20 + 70); // Set the Framesize accordingly
                    frame.setResizable(false); // Once the thread is started User shouldnt be able to change the frame size

            } else if (ae.getSource() == stopBtn) {

                log.info("Stop pressed");
                threadframset.interrupt(); // Stop the thread

            } else if (ae.getSource() == PauseBtn) {

                log.info("pause pressed");
                ma.suspend();   // Pause the thread

            } else if (ae.getSource() == ResumeBtn) {

                log.info("resume pressed");
                ma.resume();       //Resume the thread

            } else if (ae.getSource() == RewindBtn) {

                log.info("rewind pressed");
                ma.rewind();    // Rewind the thread

            } else if (ae.getSource() == ComboBoxRule){

                if(threadframset.isAlive()){
                    threadframset.interrupt(); // If User changes the rule in between the execution - the execution stops
                    log.warning("Don't Change the Rule in between of the thread execution execution");
                }
            }
        }catch (NumberFormatException e){
            log.warning("Please enter the value in delay and Numberof Frame field"); // If the start button is pressed without any input values
        }catch (NullPointerException e) {
            log.warning("Please press start button"); // if there is a thread exception
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        log.info("Window opened");
    }



    @Override
    public void windowClosing(WindowEvent e) {
        log.info("Window Closing");
    }



    @Override
    public void windowClosed(WindowEvent e) {
        log.info("Window Closed");
        threadframset.interrupt();
    }



    @Override
    public void windowIconified(WindowEvent e) {
        log.info("Window iconified");
    }



    @Override
    public void windowDeiconified(WindowEvent e) {
        log.info("Window deiconified");
    }



    @Override
    public void windowActivated(WindowEvent e) {
        log.info("Window activated");
    }



    @Override
    public void windowDeactivated(WindowEvent e) {
        log.info("Window deactivated");
    }

    /**
     * Sample Wolf application starting point
     * @param args
     */
    public static void main(String[] args) {
        MAUI wapp = new MAUI();
        log.info("MAUI started");
    }



}
