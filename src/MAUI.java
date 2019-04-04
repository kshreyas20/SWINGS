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
    protected JPanel northPanel ;
    protected JButton startBtn ;
    protected JButton stopBtn ;
    protected JButton PauseBtn;
    protected JButton ResumeBtn;
    protected JButton RewindBtn;
    protected MACanvas maPanel ;
    protected JTextField DelayHeading;
    protected JTextField TextFieldValue;
    protected JTextField NumberofFrames;
    protected JTextField TextFieldFrames;
    protected JComboBox ComboBoxRule;
    private Thread threadframset;
    private MAFrameSet ma;


    /**
     * Sample app constructor
     */
    public MAUI() {
        frame.setSize(1200, 600);
        frame.setTitle("UIApp");
        frame.add(getNorthPanel(),BorderLayout.NORTH);
        menuMgr.createDefaultActions(); // Set up default menu items

        showUI(); // Cause the Swing Dispatch thread to display the JFrame
    }


    public JPanel getNorthPanel() {

        System.out.println("Jpanel North");
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        startBtn = new JButton("Start");
        startBtn.addActionListener(this); // Allow the app to hear about button pushes
        northPanel.add(startBtn);

        stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
        stopBtn.addActionListener(this);
        northPanel.add(stopBtn);

        PauseBtn = new JButton("Pause");
        PauseBtn.addActionListener(this);
        northPanel.add(PauseBtn);

        ResumeBtn = new JButton("Resume");
        ResumeBtn.addActionListener(this);
        northPanel.add(ResumeBtn);

        RewindBtn = new JButton("Rewind");
        RewindBtn.addActionListener(this);
        northPanel.add(RewindBtn);

        DelayHeading = new JTextField("Delay");
        DelayHeading.setEditable(false);
        northPanel.add(DelayHeading);
        TextFieldValue = new JTextField(10);
        TextFieldValue.addActionListener(this);
        northPanel.add(TextFieldValue);

        NumberofFrames = new JTextField("NumberofFrames");
        NumberofFrames.setEditable(false);
        northPanel.add(NumberofFrames);

        TextFieldFrames = new JTextField(10);
        TextFieldFrames.addActionListener(this);
        northPanel.add(TextFieldFrames);

        ComboBoxRule = new JComboBox();

        for ( int i=0;i< MARule.ruleSet.size();i++){
            System.out.println(MARule.ruleSet.get(i));
            ComboBoxRule.addItem(MARule.ruleSet.get(i));

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
            log.info("We received an ActionEvent " + ae);
            String rulelocal = ComboBoxRule.getSelectedItem().toString();
            int delay = Integer.parseInt(TextFieldValue.getText());
            int numerofFrames = Integer.parseInt(TextFieldFrames.getText());
            System.out.println(rulelocal + " " + delay);
            if (ae.getSource() == startBtn) {
                System.out.println("Start pressed");
                ma = new MAFrameSet(new MARule(rulelocal), delay, numerofFrames);
                ma.addObserver(this.maPanel);
                threadframset = new Thread(ma);
                threadframset.start();
            } else if (ae.getSource() == stopBtn) {
                System.out.println("Stop pressed");
                threadframset.interrupt();
            } else if (ae.getSource() == PauseBtn) {
                System.out.println("pause pressed");
                ma.suspend();
            } else if (ae.getSource() == ResumeBtn) {
                System.out.println("resume pressed");
                ma.resume();
            } else if (ae.getSource() == RewindBtn) {
                System.out.println("rewind pressed");
                ma.rewind();
            }
        }catch (NumberFormatException e){
            log.warning("Please enter the value in delay and Numberof Frame field");
        }catch (NullPointerException e) {
            log.warning("Please press start button");
        }
    }

    @Override
    public void windowOpened(WindowEvent e) {
        log.info("Window opened");
    }



    @Override
    public void windowClosing(WindowEvent e) {
    }



    @Override
    public void windowClosed(WindowEvent e) {
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
