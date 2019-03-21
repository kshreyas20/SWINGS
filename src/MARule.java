
public class MARule  {

    private int ruleset [] = {1,2};



    public Cell chessRule(Cell cell,int rowindex,int columnindex){


        Cell newcell = new Cell();

        if(rowindex%2 == 0 && columnindex ==0 ){
            newcell.setColour("WHITE");
        }
        else if(rowindex%2 != 0 && columnindex ==0 ){
            newcell.setColour("BLACK");
        }

        else {
            if (cell.getColour().equals("BLACK")) {

                newcell.setColour("WHITE");
            } else {

                newcell.setColour("BLACK");
            }
        }
        return newcell;
    }

    public Cell checkersRule(Cell cell,int rowindex,int columnindex){


        Cell newcell = new Cell();

        if(rowindex%2 == 0 && columnindex ==0 ){
            newcell.setColour("WHITE");
        }
        else if(rowindex%2 != 0 && columnindex ==0 ){
            newcell.setColour("GREEN");
        }

        else {
            if (cell.getColour().equals("GREEN")) {
                // newcell = cell;
                newcell.setColour("WHITE");
            } else {
                // newcell = cell;
                newcell.setColour("GREEN");
            }
        }
        return newcell;
    }

    public Cell stepRule(Cell cell,int rowindex,int columnindex){


        Cell newcell = new Cell();

        if(rowindex > columnindex ){
            newcell.setColour("BLUE");
        }
        else {
            newcell.setColour("WHITE");
        }

        return newcell;
    }

    public Cell pyramidRule(Cell cell,int rowindex,int columnindex){


        Cell newcell = new Cell();

        if(rowindex <5 && columnindex <5){
            newcell.setColour("GREEN");
        }
        else if (rowindex <5 && columnindex >8) {
            newcell.setColour("BLUE");
        }

        return newcell;
    }



    public Cell getCellState(MAFrame frame,int row,int column){

        return frame.getCell(row,column);
    }

    public void setCellState(MAFrame frame,Cell cell,int row,int column){

        frame.insertCell(cell,row,column);

    }




}