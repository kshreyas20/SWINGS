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


    public MAFrame getNewframe(MAFrame frame, int iteration){

        MAFrame newframe = new MAFrame();
        if (this.rule.equals("CHESS")){

            newframe=chessRule(frame,iteration);
        }
       /* else if(this.rule.equals("CHECKERS")){

            newframe=stepRule(cell,rowindex,columnindex);
        }
        else if(this.rule.equals("STEPS")){

            newframe=stepRule(cell,rowindex,columnindex);
        }*/
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
                    frame.insertCell(chessRule(frame.getCell(i, j), i, j), i, j + 1);
                    frame.displaycell(i,j);
                }


            }


            return frame;
        }


        private Cell chessRule (Cell cell, int rowindex, int columnindex) {

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
















}