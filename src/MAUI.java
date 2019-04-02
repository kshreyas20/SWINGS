//package edu.neu.csye6200.uia5;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.JPanel;

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

    /**
     * Sample app constructor
     */
    public MAUI() {
        frame.setSize(600, 600);
        frame.setTitle("UIApp");

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

        return northPanel;
    }

    @Override
    public JPanel getMainPanel() {
        return maPanel = new MACanvas();
    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        log.info("We received an ActionEvent " + ae);
        if (ae.getSource() == startBtn)
            System.out.println("Start pressed");
        else if (ae.getSource() == stopBtn)
            System.out.println("Stop pressed");
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
     * Sample MAUI application starting point
     * @param args
     */
    public static void main(String[] args) {
        MAUI wapp = new MAUI();
        wapp.getNorthPanel();
        log.info("WolfApp started");
    }



}
