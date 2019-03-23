import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class MARule  {

    private final static ArrayList<String> ruleSet = new ArrayList<String>(Arrays.asList("CHESS","STEPS","PYRAMID","MOBILE"));
    private String rule;
    private String [] state;


    public MARule(String rule) {
        System.out.println(rule);
        if(ruleSet.contains(rule)) {
            this.rule = rule;
            setState(rule);
            System.out.println(rule);
        }
        else{
            this.rule = "CHESS";
            setState("CHESS");
        }
    }


    private void setState(String rule)
    {
        MACellHelper rulestate = new MACellHelper();
        if(rule.equals("CHESS")){
            this.state = new String [] {rulestate.getColours().get(1),rulestate.getColours().get(0)};
        }
        else if (rule.equals("STEPS")){
            this.state = new String [] {rulestate.getColours().get(1),rulestate.getColours().get(4),rulestate.getColours().get(0)};
        }
        else if(rule.equals("PYRAMID")){
            this.state = new String [] {rulestate.getColours().get(2),rulestate.getColours().get(1)};
        }
        else if(rule.equals("MOBILE")){
            this.state = new String [] {rulestate.getColours().get(0),rulestate.getColours().get(1),rulestate.getColours().get(5)};
        }
        else{
            this.state = new String [] {rulestate.getColours().get(1)};
        }
    }


    public MAFrame getNewframe(MAFrame frame, int iteration){

        MAFrame newframe = new MAFrame();
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
        else {
            newframe= frame;
        }
        return newframe;
    }


    private MAFrame chessRule(MAFrame frame, int iteration){

            Cell fcell = new Cell("SQUARE","BLACK","NEUTRAL","NONE",false);
            frame.insertCell(fcell,0,0);

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

    private MAFrame stepRule (MAFrame frame,int iteration) {

        Cell fhcell = new Cell("SQUARE", "BLUE", "NEUTRAL", "NONE", false);
        frame.insertCell(fhcell, 0, 0);

        for (int i = 0; i < iteration+1; i++) {
            for (int j = 0; j < frame.getColumn(); j++) {

                frame.insertCell(stepRuleCell(frame.getCell(i, j), i, j), i, j );
                frame.displaycell(i, j);
            }

        }
        return frame;
    }

    private Cell stepRuleCell(Cell cell,int rowindex,int columnindex){

        Cell newcell = new Cell();
        if(rowindex >= columnindex ){
            newcell.setColour(this.state[1]);
        }
        else {
            newcell.setColour(this.state[2]);
        }

        return newcell;
    }

    private MAFrame pyramidRule (MAFrame frame,int iteration) {

        Cell cell = new Cell("SQUARE", "RED", "NEUTRAL", "NONE", false);

        frame.insertCell(cell, 0, frame.getColumn() / 2);

        if(iteration<=frame.getRow()/2) {
            for (int i = 0; i < iteration; i++) {

                for (int j = (frame.getColumn() / 2 - i); j <= (frame.getColumn() / 2 + i); j++) {
                    System.out.print(i + " " + j);
                    frame.displaycell(i, j);
                    if (frame.getCell(i, j).getColour().equals("RED")) {
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

        private Cell pyramidRuleCell (Cell cell,int rowindex, int columnindex){

            Cell newcell = new Cell();

            newcell.setColour(this.state[0]);

            return newcell;
        }



    private MAFrame mobileRule (MAFrame frame,int iteration) {

        Random random = new Random();

        if(iteration == 0) {


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
            frame.getCell(0,frame.getColumn()/2).setColour(this.state[1]);
        }

        else {

            int[] position = new int[2];

            for (int i = iteration-1; i < iteration; i++) {
                for (int j = 0; j < frame.getColumn(); j++) {

                    System.out.println("==>" + i + " " + j);
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
                                position = MobileCellRule1(frame.getCell(i + 1, j).getColour(), frame.getCell(i, j + 1).getColour(), frame.getCell(i, j - 1).getColour());
                            }
                            frame.getCell(i + position[0], j + position[1]).setColour(this.state[1]);
                            frame.displaycell(i + position[0], j + position[1]);
                            System.out.println("break==>" + (i + position[0]) + " " + (j + position[1]));
                    }
                }
            }
        }
        return frame;
    }

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
            position[1] = 0;
        }

        System.out.println(position[0]+" "+position[1]);
        return position;
    }


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

            position[0] =-1;
            position[1] = -1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("YELLOW") && rightColour.equals("YELLOW") && LowerColour.equals("WHITE")){
            position[0] = -1;
            position[1] = -1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("YELLOW") && rightColour.equals("WHITE") && LowerColour.equals("YELLOW")){
            position[0] = -1;
            position[1] = 1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("YELLOW") && rightColour.equals("WHITE") && LowerColour.equals("WHITE")){
            position[0] = -1;
            position[1] = 1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("WHITE") && rightColour.equals("YELLOW") && LowerColour.equals("YELLOW")){
            position[0] = -1;
            position[1] = 1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("WHITE") && rightColour.equals("YELLOW") && LowerColour.equals("WHITE")){
            position[0] = -1;
            position[1] = 1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("WHITE") && rightColour.equals("WHITE") && LowerColour.equals("YELLOW")){
            position[0] = -1;
            position[1] = -1;
        }
        else if (UpperColour.equals("WHITE") && leftColour.equals("WHITE") && rightColour.equals("WHITE") && LowerColour.equals("WHITE")){
            position[0] = -1;
            position[1] = -1;
        }
        else{
            position[0] = 1;
            position[1] = 0;
        }
        
        System.out.println(position[0]+" "+position[1]);
        return position;
    }



}



