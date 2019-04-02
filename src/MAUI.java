//package edu.neu.csye6200.uia5;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
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

    private static Logger log = Logger.getLogger(WolfApp.class.getName());
    /*
        protected JPanel northPanel = null;
        protected JButton startBtn = null;
        protected JButton stopBtn = null;
        private MACanvas maPanel = null;
    */
    protected JPanel northPanel ;
    protected JButton startBtn ;
    protected JButton stopBtn ;
    private MACanvas maPanel ;
    private JTextField DelayHeading;
    private JTextField TextFieldValue;
    private JComboBox ComboBoxRule;
    private Thread threadframset;
    private MAFrameSet ma;
    private MACanvas mc;

    /**
     * Sample app constructor
     */
    public MAUI() {
        frame.setSize(500, 400);
        frame.setTitle("UIApp");
        frame.add(getNorthPanel(),BorderLayout.NORTH);
        menuMgr.createDefaultActions(); // Set up default menu items

        showUI(); // Cause the Swing Dispatch thread to display the JFrame
    }


    public JPanel getNorthPanel() {
        northPanel = new JPanel();
        northPanel.setLayout(new FlowLayout());

        startBtn = new JButton("Start");
        startBtn.addActionListener(this); // Allow the app to hear about button pushes
        northPanel.add(startBtn);

        stopBtn = new JButton("Stop"); // Allow the app to hear about button pushes
        stopBtn.addActionListener(this);
        northPanel.add(stopBtn);

        DelayHeading = new JTextField("Delay");
        DelayHeading.setEditable(false);
        northPanel.add(DelayHeading);
        TextFieldValue = new JTextField(10);
        TextFieldValue.addActionListener(this);
        // TextField.setBounds(1,1,30,3);   ===> Need to insert try catch block to only take interger greater than 1 and less than 100
        northPanel.add(TextFieldValue);

        ComboBoxRule = new JComboBox();
        for ( int i=0;i< MARule.ruleSet.size();i++){
            System.out.println(MARule.ruleSet.get(i));
            ComboBoxRule.addItem(MARule.ruleSet.get(i));
            // ===> Add listener which Rule u selected
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
        log.info("We received an ActionEvent " + ae);
        if (ae.getSource() == startBtn) {
            System.out.println("Start pressed");
            ma = new MAFrameSet();
            mc = new MACanvas();
            ma.addObserver(mc);
            threadframset = new Thread(ma);
            threadframset.start();
        }
        else if (ae.getSource() == stopBtn) {
            System.out.println("Stop pressed");
           // threadframset.
        }

        else if (ae.getSource() == ComboBoxRule) {
            System.out.println("Combo pressed");
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
        log.info("WolfApp started");
    }



}
