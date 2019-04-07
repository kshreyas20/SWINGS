
//package edu.neu.csye6200.ma;

/*
Create a  MACell helper class which defines the state of a cell

Solution:
This is a helper class for "CELL CLASS" . it restrict the states of the cell object By controlling the values assigned to
cell instant  variable .
*/

import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Logger;

public class MACellHelper {

    private static Logger log = Logger.getLogger(MACellHelper.class.getName());
    private final ArrayList<String> shapes;  // List of shapes cell can take.
    private final ArrayList<String> colours; // List of colours cell can portray
    private final ArrayList<String> shades; // List of scales cell can project

    // Default constructor to define the list which limit the state of the Cell class

    public MACellHelper() {
        log.info("MAHelper Constructor is used to decided the state of the Cell");
        this.shapes = new ArrayList<>(Arrays. asList("SQUARE"));
        this.colours = new ArrayList<>(Arrays.asList("WHITE", "BLACK", "RED", "GREEN", "BLUE", "YELLOW"));
        this.shades = new ArrayList<>(Arrays.asList("BRIGHT", "DARK", "NEUTRAL"));
    }

    // These getter methods are used in CELL class or RULE class to define or limit the state of the cell

    public ArrayList<String> getShapes() {
        return shapes;
    }

    public ArrayList<String> getColours() {
        return colours;
    }

    public ArrayList<String> getShades() {
        return shades;
    }

}
