import java.util.HashMap;

public class MAFrame  {

    private int row;
    private int column;
    private Cell cells [] [] = new Cell[100][100];
    private static int framecount = 0;
    private String title;



    public MAFrame(int row, int column) {
        this.row = row;
        this.column = column;
        setCells(row,column);
        setFramecount();

    }

    public MAFrame() {
        this.row = 25;
        this.column = 20;
        setCells(25,20);
        setFramecount();
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public Cell[][] getCells(int i, int j) {
        return cells;
    }

    private void setCells(int row,int column) {

        for (int i=0;i<row;i++){
            for(int j=0;j<column;j++) {
                this.cells[i][j] = new Cell();
            }
        }
    }

    public static int getFramecount() {
        return framecount;
    }

    public static void setFramecount() {
        framecount++;
    }

    public void insertCell (Cell cell,int rowindex,int columnindex){

         this.cells[rowindex][columnindex] = cell;

    }
    public Cell getCell (int rowindex,int columnindex){

        return this.cells[rowindex][columnindex];
    }

    public void displaycell (int rowindex,int columnindex){

        System.out.println(this.cells[rowindex][columnindex]);


    }

}

