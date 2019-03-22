
public class MARule  {

    private final String [] ruleSet = new String[]{"CHESS","CHECKERS","STEPS","PLUS"};
    private String rule;
    private String [] state;


    public MARule(String rule) {
        if(ruleSet.equals(rule)) {
            this.rule = rule;
            setState(rule);
        }
        else{
            this.rule = "CHESS";
            setState("CHESS");
        }
    }

    public String[] getRuleSet() {
        return ruleSet;
    }


    private void setState(String rule)
    {
        MACellHelper rulestate = new MACellHelper();
        if(rule.equals("CHESS")){
            this.state = new String [] {rulestate.getColours().get(1),rulestate.getColours().get(0)};
        }
        else if (rule.equals("CHECKERS")){
            this.state = new String [] {rulestate.getColours().get(1),rulestate.getColours().get(3),rulestate.getColours().get(0)};
        }
        else if (rule.equals("STEPS")){
            this.state = new String [] {rulestate.getColours().get(1),rulestate.getColours().get(4),rulestate.getColours().get(0)};
        }
        else if(rule.equals("PLUS")){
            this.state = new String [] {rulestate.getColours().get(2),rulestate.getColours().get(1)};
        }
        else{
            this.state = new String [] {rulestate.getColours().get(1)};
        }
    }


    public Cell getNewCell(Cell cell,int rowindex,int columnindex){

        Cell newcell = new Cell();
        if (this.rule.equals("CHESS")){

            newcell=chessRule(cell,rowindex,columnindex);
        }
        else if(this.rule.equals("CHECKERS")){

            newcell=stepRule(cell,rowindex,columnindex);
        }
        else if(this.rule.equals("STEPS")){

            newcell=stepRule(cell,rowindex,columnindex);
        }
        else {
            newcell= cell;
        }
        return newcell;
    }


    private Cell chessRule(Cell cell, int rowindex, int columnindex){


        Cell newcell = new Cell();
        if(rowindex%2 == 0 && columnindex ==0 ){
            newcell.setColour(this.state[1]);
        }
        else if(rowindex%2 != 0 && columnindex ==0 ){
            newcell.setColour(this.state[0]);
        }

        else {
            if (cell.getColour().equals(this.state[0])) {

                newcell.setColour(this.state[1]);
            } else {

                newcell.setColour(this.state[0]);
            }
        }
        return newcell;
    }

    private Cell checkersRule(Cell cell,int rowindex,int columnindex){


        Cell newcell = new Cell();

        if(rowindex%2 == 0 && columnindex ==0 ){
            newcell.setColour(this.state[2]);
        }
        else if(rowindex%2 != 0 && columnindex ==0 ){
            newcell.setColour(this.state[1]);
        }

        else {
            if (cell.getColour().equals(this.state[1])) {
                newcell.setColour(this.state[2]);
            } else {
                newcell.setColour(this.state[1]);
            }
        }
        return newcell;
    }

    private Cell stepRule(Cell cell,int rowindex,int columnindex){


        Cell newcell = new Cell();

        if(rowindex > columnindex ){
            newcell.setColour(this.state[1]);
        }
        else {
            newcell.setColour(this.state[2]);
        }

        return newcell;
    }

    private MAFrame plusRule(MAFrame frame,int iteration){



        if(iteration == 0){
            frame.insertCell((new Cell("SQUARE", "RED", "DARK", "NONE", false)),frame.getRow()/2,frame.getColumn()/2);
        }

        else {
           for(int i=0;i<iteration;i++) {
               for (int k = 0; k < frame.getRow()-1; k++) {
                   for (int l = 0; l < frame.getColumn()-1; l++) {

                       if (frame.getCell(k, l).getColour().equals("RED")) {

                           frame.insertCell((new Cell("SQUARE", "RED", "DARK", "NONE", false)), k + 1, l);
                           frame.insertCell((new Cell("SQUARE", "RED", "DARK", "NONE", false)), k - 1, l);
                           frame.insertCell((new Cell("SQUARE", "RED", "DARK", "NONE", false)), k, l + 1);
                           frame.insertCell((new Cell("SQUARE", "RED", "DARK", "NONE", false)), k, l - 1);

                           k = frame.getRow();
                           l = frame.getColumn();
                       }
                   }
               }
           }
        }



        return frame;

    }


}