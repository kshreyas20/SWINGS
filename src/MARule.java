//package edu.neu.csye6200.ma;

/*
Create a  MARule class  which can assign a new cell based on a prior cell frame

Solution:
There are two of rule
            1. Cellular Automata
            2. Mobile Automata

In Cellular Automata =
                        a.CHESS display
                        b.STEPS display
                        c.TWO-SIDED PYRAMID display
In Mobile AutoMata =
                        a. Mobile -> Compares 3 CELL next to it { RIGHT LEFT BELOW } => On how to Move down either left down or right down
                                 [] [X] []
                                  * []  *
                        b. Mobile2 -> Compares 4 CELL next to it  { RIGHT LEFT BELOW TOP}  => On how to Move down either left down or right down
                                     []
                                 [] [X] []
                                 *  []  *

 */


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MARule  {

    public final static ArrayList<String> ruleSet = new ArrayList<>(Arrays.asList("CHESS", "STEPS", "PYRAMID", "MOBILE", "MOBILE2","TRIANGLE")); // RULE list which Frame Set will use to display the
    private String rule; // FrameSet use this variable select the rule type
    private String [] state; //  Once the Rule is selcted -> Privately State are allocated. Currently we are dealing with COLOUR of the cells


    // Only Constructor to decide the MARule object -> RULE has to be mentioned -> If there is invalid rule CHESS pattern will be implemented

    public MARule(String rule) {

        if(ruleSet.contains(rule)) {
            this.rule = rule;
            setState(rule);
        }
        else{
            this.rule = "CHESS";
            setState("CHESS");
        }
    }

    public String getRule() {
        return rule;
    }
// Different states are allocated based on rule. Helper Class object is called to decide the states. => Currently only COLOURS are used to allocate the state of the Cell

    private void setState(String rule)
    {
        MACellHelper rulestate = new MACellHelper();
        if(rule.equals("CHESS")){
            this.state = new String [] {rulestate.getColours().get(1),rulestate.getColours().get(0)}; // BLACK and WHITE Colour
        }
        else if (rule.equals("STEPS")){
            this.state = new String [] {rulestate.getColours().get(1),rulestate.getColours().get(4),rulestate.getColours().get(0)}; // BLUE for steps WHITE for Non steps default BLACK colour is assigned
        }
        else if(rule.equals("PYRAMID")){
            this.state = new String [] {rulestate.getColours().get(2),rulestate.getColours().get(1)}; // RED is used to build the pyramid and BLACK as default background
        }
        else if(rule.equals("MOBILE")){
            this.state = new String [] {rulestate.getColours().get(0),rulestate.getColours().get(1),rulestate.getColours().get(5)}; // YELLOW WHITE is used to create the GRID and Movement of Active cells are shown by BLACK cells
        }
        else if(rule.equals("MOBILE2")){
            this.state = new String [] {rulestate.getColours().get(0),rulestate.getColours().get(1),rulestate.getColours().get(5)}; // YELLOW WHITE is used to create the GRID and Movement of Active cells are shown by "X" text in the cell
        }
        else if(rule.equals("TRIANGLE")){
            this.state = new String [] {rulestate.getColours().get(0),rulestate.getColours().get(1),rulestate.getColours().get(5)}; // YELLOW WHITE is used to create the GRID and Movement of Active cells are shown by "X" text in the cell
        }
        else{
            this.state = new String [] {rulestate.getColours().get(1)}; // Only BLACK Colour is assigned if there are no Rule
        }
    }

    //  Get Generic New frame expose to other Class hiding the frame and cell rule implementation

    public MAFrame getNewframe(MAFrame frame, int iteration){

        MAFrame newframe;
        if (this.rule.equals("CHESS")){

            newframe=chessRule(frame,iteration);
        }
        else if(this.rule.equals("STEPS")){

            newframe=stepRule(frame,iteration);
        }
        else if(this.rule.equals("PYRAMID")){

            newframe=pyramidRule(frame,iteration);
        }
        else if(this.rule.equals("MOBILE")){

            newframe=mobileRule(frame,iteration);
        }
        else if(this.rule.equals("MOBILE2")){

            newframe=mobileRule2(frame,iteration);
        }
        else if(this.rule.equals("TRIANGLE")){

            newframe=triangleRule(frame,iteration);
        }
        else {
            newframe= frame;
        }
        return newframe;
    }

    // Chess Rule which takes frame as a input will iterate each frame by  row

    private MAFrame chessRule(MAFrame frame, int iteration){

            for(int i=0;i<iteration+1;i++){
                for(int j=0;j<frame.getColumn();j++){

                    if(j== 0 && i > 0){
                        frame.insertCell(frame.getCell(i-1, j+frame.getColumn()-1), i, j );
                    }
                    frame.insertCell(chessRuleCell(frame.getCell(i, j), i, j), i, j + 1);
                    frame.displaycell(i,j);
                }

            }

            return frame;
        }

    // Internally called by Chess Rule to modify the State of each Cell

        private Cell chessRuleCell (Cell cell, int rowindex, int columnindex) {

            Cell newcell = new Cell();
            if (rowindex % 2 == 0 && columnindex == 0) {
                newcell.setColour(this.state[1]);
            } else if (rowindex % 2 != 0 && columnindex == 0) {
                newcell.setColour(this.state[0]);
            } else {
                if (cell.getColour().equals(this.state[0])) {

                    newcell.setColour(this.state[1]);
                } else {

                    newcell.setColour(this.state[0]);
                }
            }
            return newcell;
        }


    // Cellular pattern steps for each row => The steps will be  blue in colour ,non step will be white in colour and default background is black

    private MAFrame stepRule (MAFrame frame,int iteration) {


        frame.getCell(0,0).setColour(this.state[1]); // Changing the state to blue

        for (int i = 0; i < iteration+1; i++) {
            for (int j = 0; j < frame.getColumn(); j++) {

                frame.insertCell(stepRuleCell(frame.getCell(i, j), i, j), i, j );
                frame.displaycell(i, j);
            }
        }
        return frame;
    }

    //  Internally called by Step Rule to modify the State of each Cell

    private Cell stepRuleCell(Cell cell,int rowindex,int columnindex){

        Cell newcell = new Cell();
        if(rowindex >= columnindex ){
            newcell.setColour(this.state[1]); // Changing the state to blue
        }
        else {
            newcell.setColour(this.state[2]); // Changing the state to White
        }
        return newcell;
    }

    // Cellular pattern 2 SIDED Pyramid The pyramid will be  RED in colour and default background is black


    private MAFrame pyramidRule (MAFrame frame,int iteration) {


        frame.getCell(0,frame.getColumn() / 2 ).setColour(this.state[0]);

        if(iteration<=frame.getRow()/2) {
            for (int i = 0; i < iteration; i++) {

                for (int j = (frame.getColumn() / 2 - i); j <= (frame.getColumn() / 2 + i); j++) {
                    System.out.print(i + " " + j);
                    frame.displaycell(i, j);
                    if (frame.getCell(i, j).getColour().equals(this.state[0])) {
                        frame.insertCell(pyramidRuleCell((frame.getCell(0, frame.getColumn() / 2)), i, j), i + 1, j);
                        frame.insertCell(pyramidRuleCell((frame.getCell(0, frame.getColumn() / 2)), i, j), i + 1, j - 1);
                        frame.insertCell(pyramidRuleCell((frame.getCell(0, frame.getColumn() / 2)), i, j), i + 1, j + 1);
                        // frame.displaycell(i, j);
                    }

                }
            }
        }
        else{
            int k=0;
            for (int i = frame.getRow()/2; i <= iteration; i++) {

                for (int j = k; j < frame.getColumn()-k; j++) {
                        frame.insertCell(pyramidRuleCell((frame.getCell(0, frame.getColumn() / 2)), i, j), i , j);
                    }
                    k++;
                }
            }


        return frame;
    }


    //  Internally called by Pyramid Rule to modify the State of each Cell

        private Cell pyramidRuleCell (Cell cell,int rowindex, int columnindex){

            Cell newcell = new Cell();

            newcell.setColour(this.state[0]);

            return newcell;
        }

        // Create Random Grid pattern for mobile automata -> WHITE and YELLOW pattern

    public  void randomGridGenrator(MAFrame frame){

        Random random = new Random();
        for (int i = 0; i < frame.getRow() + 1; i++) {
            for (int j = 0; j < frame.getColumn(); j++) {
                int value = random.nextInt(2);
                if(value == 1) {
                    frame.insertCell(new Cell("SQUARE", this.state[2], "NEUTRAL", "NONE", false), i, j);
                    frame.displaycell(i, j);
                }
                else{
                    frame.insertCell(new Cell("SQUARE", this.state[0], "NEUTRAL", "NONE", false), i, j);
                    frame.displaycell(i, j);
                }

            }

        }
    }

    // Mobile Automata will compare  all the three side -> Lower , Right ,Left

    private MAFrame mobileRule (MAFrame frame,int iteration) {


        if(iteration == 0) {

            randomGridGenrator(frame);
            Random random = new Random();
            int value = random.nextInt(frame.getColumn());
           // frame.getCell(0,frame.getColumn()/2).setColour(this.state[1]);
            frame.getCell(0,value).setColour(this.state[1]);
        }

        else {

            int[] position = new int[2];

            for (int i = iteration-1; i < iteration; i++) {
                for (int j = 0; j < frame.getColumn(); j++) {

                    System.out.println("Cell =>" + i + " " + j);
                    if (frame.getCell(i, j).getColour().equals(this.state[1])) {
                            if(frame.getColumn() == 1){
                                position[0] = 1;
                                position[1] = 0;
                            }
                            else if( j ==0 ){
                                position[0] = 1;
                                position[1] = 1;
                            }
                            else if(j == frame.getColumn()-1){
                                position[0] = 1;
                                position[1] = -1;
                             }
                            else {
                                position = MobileCellRule1(frame.getCell(i + 1, j).getColour(), frame.getCell(i, j + 1).getColour(), frame.getCell(i, j - 1).getColour()); // Position of the next cell is determined by Rule function
                            }
                            frame.getCell(i + position[0], j + position[1]).setColour(this.state[1]);
                            frame.displaycell(i + position[0], j + position[1]);
                            System.out.println("Position of the Cell insert==>" + (i + position[0]) + " " + (j + position[1]));
                    }
                }
            }
        }
        return frame;
    }


    /* Mobile -> Compares 3 CELL next to it { RIGHT LEFT BELOW } => On how to Move down either left down or right down
            [] [X] []
            * []  *

            Currently position could have been a single column calculation
            For future if row position also needs to be changed => Position[0] has been implemented upfront
     */

    private int[] MobileCellRule1(String LowerColour, String rightColour, String leftColour){

        int [] position = new int[2];

        if (leftColour.equals("YELLOW") && rightColour.equals("YELLOW") && LowerColour.equals("YELLOW")){

            position[0] = 1;
            position[1] = -1;
        }
        else if (leftColour.equals("YELLOW") && rightColour.equals("YELLOW") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = -1;
        }
        else if (leftColour.equals("YELLOW") && rightColour.equals("WHITE") && LowerColour.equals("YELLOW")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (leftColour.equals("YELLOW") && rightColour.equals("WHITE") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (leftColour.equals("WHITE") && rightColour.equals("YELLOW") && LowerColour.equals("YELLOW")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (leftColour.equals("WHITE") && rightColour.equals("YELLOW") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (leftColour.equals("WHITE") && rightColour.equals("WHITE") && LowerColour.equals("YELLOW")){
            position[0] = 1;
            position[1] = -1;
        }
        else if (leftColour.equals("WHITE") && rightColour.equals("WHITE") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = -1;
        }
        else{
            position[0] = 1;
        }

        System.out.println(position[0]+" "+position[1]);
        return position;
    }


    // Mobile Automata will compare  all the three side -> Lower , Right ,Left ,Uppper Cells

    private MAFrame mobileRule2 (MAFrame frame,int iteration) {


        if(iteration == 0) {

            randomGridGenrator(frame);
            frame.getCell(0,frame.getColumn()/2).setTextfield("X"); // First Row Cells are selected by default
        }
        else if (iteration == 1){
            frame.getCell(1,frame.getColumn()/2).setTextfield("X"); // Second Row Cells are selected by default
        }
        else {

            int[] position = new int[2];

            for (int i = iteration-1; i < iteration; i++) {
                for (int j = 0; j < frame.getColumn(); j++) {

                    System.out.println("Cell ==>" + i + " " + j);
                    if (frame.getCell(i, j).getTextfield().equals(frame.getCell(1,frame.getColumn()/2).getTextfield())) {
                        if(frame.getColumn() == 1){
                            position[0] = 1;
                            position[1] = 0;
                        }
                        else if( j ==0 ){
                            position[0] = 1;
                            position[1] = 1;
                        }
                        else if(j == frame.getColumn()-1){
                            position[0] = 1;
                            position[1] = -1;
                        }

                        else {
                            position = MobileCellRule2(frame.getCell(i + 1, j).getColour(), frame.getCell(i - 1, j).getColour(),frame.getCell(i, j + 1).getColour(), frame.getCell(i, j - 1).getColour()); // Position of Upper ,Lower left and right are compared
                        }
                        frame.getCell(i + position[0], j + position[1]).setTextfield(frame.getCell(1,frame.getColumn()/2).getTextfield());
                        frame.displaycell(i + position[0], j + position[1]);
                        System.out.println("Position of the Cell insert==>" + (i + position[0]) + " " + (j + position[1]));
                    }
                }
            }
        }
        return frame;
    }

    /*  Mobile2 -> Compares 4 CELL next to it  { RIGHT LEFT BELOW TOP}  => On how to Move down either left down or right down
                                     []
                                 [] [X] []
                                 *  []  *

            Currently position could have been a single column calculation
            For future if row position also needs to be changed => Position[0] has been implemented upfront
     */

    private int[] MobileCellRule2(String LowerColour,String UpperColour ,String rightColour, String leftColour){

        int [] position = new int[2];

        if (UpperColour.equals("YELLOW") && leftColour.equals("YELLOW") && rightColour.equals("YELLOW") && LowerColour.equals("YELLOW")){

            position[0] = 1;
            position[1] = -1;
        }
        else if (UpperColour.equals("YELLOW") && leftColour.equals("YELLOW") && rightColour.equals("YELLOW") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = -1;
        }
        else if (UpperColour.equals("YELLOW") && leftColour.equals("YELLOW") && rightColour.equals("WHITE") && LowerColour.equals("YELLOW")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (UpperColour.equals("YELLOW") && leftColour.equals("YELLOW") && rightColour.equals("WHITE") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (UpperColour.equals("YELLOW") && leftColour.equals("WHITE") && rightColour.equals("YELLOW") && LowerColour.equals("YELLOW")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (UpperColour.equals("YELLOW") && leftColour.equals("WHITE") && rightColour.equals("YELLOW") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (UpperColour.equals("YELLOW") && leftColour.equals("WHITE") && rightColour.equals("WHITE") && LowerColour.equals("YELLOW")){
            position[0] = 1;
            position[1] = -1;
        }
        else if (UpperColour.equals("YELLOW") && leftColour.equals("WHITE") && rightColour.equals("WHITE") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = -1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("YELLOW") && rightColour.equals("YELLOW") && LowerColour.equals("YELLOW")){

            position[0] =1;
            position[1] = -1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("YELLOW") && rightColour.equals("YELLOW") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = -1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("YELLOW") && rightColour.equals("WHITE") && LowerColour.equals("YELLOW")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("YELLOW") && rightColour.equals("WHITE") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("WHITE") && rightColour.equals("YELLOW") && LowerColour.equals("YELLOW")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("WHITE") && rightColour.equals("YELLOW") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = 1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("WHITE") && rightColour.equals("WHITE") && LowerColour.equals("YELLOW")){
            position[0] = 1;
            position[1] = -1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("WHITE") && rightColour.equals("WHITE") && LowerColour.equals("WHITE")){
            position[0] = 1;
            position[1] = -1;
        }
        else{
            position[0] = 1;
        }

        System.out.println(position[0]+" "+position[1]);
        return position;
    }

    private MAFrame triangleRule (MAFrame frame,int iteration) {


        if(iteration == 0) {

            randomGridGenrator(frame);
            frame.getCell(0,frame.getColumn()/2).setTextfield("X"); // First Row Cells are selected by default
        }

        else {

            for (int i = iteration-1; i < iteration; i++) {
                for (int j = 0; j < frame.getColumn(); j++) {

                    System.out.println("Cell ==>" + i + " " + j);
                    if ((frame.getCell(i, j).getTextfield().equals(frame.getCell(0,frame.getColumn()/2).getTextfield()) && frame.getCell(i, j-1).getTextfield() == "NONE") || (frame.getCell(i, j).getTextfield().equals(frame.getCell(0,frame.getColumn()/2).getTextfield()) && frame.getCell(i, j+1).getTextfield() == "NONE")  ) {
                        System.out.println("Insert ==>" + i + " " + j);
                        if(j == 0){
                            frame.getCell(i+1,j).setTextfield("X");
                            frame.getCell(i+1,j+1).setTextfield("X");
                        }
                        else if (j == frame.getColumn()-1)
                        {
                            frame.getCell(i+1,j-1).setTextfield("X");
                            frame.getCell(i+1,j).setTextfield("X");
                        }
                        else {
                            frame.getCell(i + 1, j - 1).setTextfield("X");
                            frame.getCell(i + 1, j).setTextfield("X");
                            frame.getCell(i + 1, j + 1).setTextfield("X");
                        }
                    }

                }
            }
        }
        return frame;
    }



}



