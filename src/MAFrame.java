//package edu.neu.csye6200.ma;

/*
Create a  MAFrame , which holds a 2D array of cells

Solution:
2D Array which holds Cell Objects inside it  => Imagining MAFrame as a Frame(JFrame) this class was designed
 */

import java.util.logging.Logger;

public class MAFrame  {

    private static Logger log = Logger.getLogger(MAFrame.class.getName());
    private int row; // Number of Rows in the cell
    private int column; // Number of Column in the cell
    private Cell[][] cells = new Cell[100][100]; // Limiting the cell limit to 200x200 for MaximumFrame
    private static int framecount = 0; // Future purpose to limit the number of Frame object initated
    private String title; // Future purpose -> To display Title of each frame

    /*
     User defined Constructor =>  define Rows and Column Row will determine the number of cells
     Note : All the Cell will be created with default values => If you Display this frame it will be BLACK (default Cell colour)
     */

    public MAFrame(int row, int column) {
        log.info("New Frame is created");
        this.row = row;
        this.column = column;
        setCells(row,column);
        setFramecount();

    }

    /*
     Default Constructor => If the User doesnt define Rows and Column Row will be set 25 and Column will be set to 20
     Note : All the Cell will be created with default values => If you Display this frame it will be BLACK (default Cell colour)
     */

    public MAFrame() {
        log.info("Default Frame is created");
        this.row = 50;
        this.column = 40;
        setCells(50,40);
        setFramecount();
    }

    // Row and Column this is used by Rule class for limiting the loops

    public int getRow() {
        return row;
    }

    public int getColumn() {
        return column;
    }


    // FrameSet class uses this method reterive the previous frame -> Need to check

    public Cell[][] getCells(int i, int j) {
        return cells;
    }

    // Used by constructor  to set the default cells in the frame -> The Frame will be black in colour

    private void setCells(int row,int column) {

        try {
            for (int i = 0; i < row; i++) {
                for (int j = 0; j < column; j++) {
                    this.cells[i][j] = new Cell();
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            log.warning("Index is more than 200");
        }

    }

    // Future purpose code to count the number of frame created

    public static int getFramecount() {
        return framecount;
    }

    private static void setFramecount() {
        framecount++;
    }


    // Code to insert cell object into frame .=> This is mainly used by rule class to replace the exisiting cell object( BLACK Cell) new object

    public void insertCell (Cell cell,int rowindex,int columnindex){

        try {
            this.cells[rowindex][columnindex] = cell;
        }
        catch (ArrayIndexOutOfBoundsException e){
            log.warning("Index is more than 200");
        }

    }

    // Code is returns the Cell object from the Frame

    public Cell getCell (int rowindex,int columnindex){
        try {
            if ((rowindex < 0 && rowindex >= this.row) || (columnindex < 0 && columnindex >= this.column)) {
                log.warning("Index is more than row and column limit or less than 0 => Sending 0=0 index value ");
                rowindex = 0;
                columnindex = 0;
            }
        }
        catch (ArrayIndexOutOfBoundsException e){
            log.warning("Index is more than 200");
        }
        return this.cells[rowindex][columnindex];
    }

    // Code is to display each indvidual cell object in the console

    public void displaycell (int rowindex,int columnindex){

        try {
            if ((rowindex < 0 && rowindex >= this.row) || (columnindex < 0 && columnindex >= this.column)) {
                log.warning("Index is more than row and column limit or less than 0 ");
            } else {
                System.out.println(this.cells[rowindex][columnindex]);
            }
        }catch (ArrayIndexOutOfBoundsException e){
            log.warning("Index is more than 200");
        }
    }

    // Function is used by Frameset to get the complete frame

    public Cell[][] getCells() {
        return cells;
    }

    // Function is used by Frameset to copy  the complete frame to new Frame

    public void setCells(Cell[][] cells) {

        try {
            for (int i = 0; i < this.getRow(); i++) {
                for (int j = 0; j < this.getColumn(); j++) {
                    this.cells[i][j] = copyCell(cells[i][j]);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            log.warning("Index is more than 200");
        }
    }
    // Copying each individual Cells State to new Cells

    private Cell copyCell(Cell cell) {

        Cell newcell = new Cell(cell.getShape(),cell.getColour(),cell.getShade(),cell.getTextfield(),cell.isEffects());

        return  newcell;
    }
}

