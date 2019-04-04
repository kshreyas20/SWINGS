
//package edu.neu.csye6200.ma;

/*
Cell Class is to define the blue print of the individual cell - Imagining Cell as a button this class was designed
Note :  States are restricted by MACellHelper Class
*/

import java.util.logging.Logger;

public class Cell {
    private static Logger log = Logger.getLogger(Cell.class.getName());
    private static final MACellHelper cellhelp = new MACellHelper();// Before assigning the value to the cell state - Helper Object has to be created static - this can be used by all the cell
    private String shape; // Currently only Square button is supported in Jbutton  Hence restricted by helperClass to take only SQUARE
    private String colour; // Colours are Restricted by helper calls to take only restricted colours defined in MAHelper class
    private String shade; // Currently we can display different shades of grey scale dark and bright  --> Future purposes -> By default its NORMAL
    private String textfield; // Text field can be applied to show the active cell in some of the cases -> Refer Mobile2 case.
    private boolean effects; // Current this is used to set the highlight the button when the mouse is moved -> future purposes .By default its FALSE
    private static int counter = 0; // Future purpose - To Count the number of Cell objects created 7


    // User defined Constructor for any Cell creation -> Value is restricted by MACellHelper class

    public Cell(String shape, String colour, String shade, String textfield, boolean effects) {
        log.info("Custom Cell Created");
        setShape(shape);
        setColour(colour);
        setShade(shade);
        setTextfield(textfield);
        this.effects = effects;
        counter();

    }


    /* Default Constructor for any Cell creation -> This is used when Initial Frame is created -> BLACK SCREEN this how it would look like

    public Cell() {
        this.colour = "BLACK";
        this.shape = "SQUARE";
        this.shade = "NEUTRAL";
        this.textfield = "NONE";
        this.effects = false;
        counter();
    }
    */

    public Cell() {
        log.info("Default Cell Created");
        this.colour = cellhelp.getColours().get(1);
        this.shape = cellhelp.getShapes().get(0);
        this.shade = cellhelp.getShades().get(2);
        this.textfield = "NONE";
        this.effects = false;
        counter();
    }

    // Getting the shape of the Cell

    public String getShape() {
        return shape;
    }

    // I could have hardcoded the shape of the cell to SQUARE .But in future if I want to add more shape like CIRCLE  Hence I have kept the code for future reasons

    public void setShape(String shape) {


        if(cellhelp.getShapes().contains(shape)){
            this.shape = shape;
        }
        else{
            log.warning("Default Shape used SQUARE");
            this.shape = cellhelp.getShapes().get(0);
        }
    }

    // Getting the Colour information of the cell -> Mostly used in rules to do pattern and defining the state of the rule in MA Rule calls


    public String getColour() {
        return colour;
    }


    // This is majorly used in setting the state of active cell movements in mobile automata -> Colour list must be present in Helper class

    public void setColour(String colour) {

        if(cellhelp.getColours().contains(colour)){
            this.colour = colour;
        }
        else{
            log.warning("Default Colour is used Black");
            this.colour = cellhelp.getColours().get(1);
        }
    }

    // Future reference code to get the shades of colour

    public String getShade() {
        return shade;
    }

    // Future reference code to set the shades of colour

    public void setShade(String shade) {


        if(cellhelp.getShades().contains(shade)){
            this.shade = shade;
        }
        else{
            log.warning("Default Shade is used NEUTRAL");
            this.shade = cellhelp.getShades().get(2);
        }
    }

    //  Code to get the text field inside cell -> Used in mobile Automate case- 2

    public String getTextfield() {
        return textfield;
    }

    //  Code to set the text field inside cell -> Used in mobile Automate case- 2

    public void setTextfield(String textfield) {

        if(textfield.length() < 5) {
            this.textfield = textfield;
        }
        else{
            log.warning("Text is more than 5 Letters");
            this.textfield = "NONE";
        }

    }

    // Future reference code for geting the effects

    public boolean isEffects() {
        return effects;
    }

    // Future reference code for setting up the effects

    public void setEffects(boolean effects) {
        this.effects = effects;
    }


    // Future reference code for setting limit for number of cell objects created etc -> Memory optmization

    private void counter() {
        counter++;
    }

    public static int getCounter() {
        return counter;
    }

    // To display the values holding by the Cell object -> Used in change in state of the cell

    public String toString() {
        System.out.println();
        return String.format("%10s%10s%10s%10s%10s",shape,shade,colour,textfield,effects);

    }


}


