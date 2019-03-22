import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;

public class MAFrameSet {

    private ArrayList<MAFrame> frameSet = new ArrayList<>();
    private static int yaxis = 0;
    private static int xaxis = 0;

    public void view (MAFrame cframe){


        JFrame frame= new JFrame();
        frame.setTitle("Demonstrate valid 2D Mobile Automata");
        frame.setSize(400, 400);
        frame.setLocation(xaxis, yaxis);
        frame.setResizable(true);
        frame.setLayout(new GridLayout(cframe.getRow(),cframe.getColumn()));
        frame.setBackground(Color.BLACK);

        System.out.print("======display=======");
        for(int i=0;i<cframe.getRow();i++){
            for (int j=0;j<cframe.getColumn();j++) {

                System.out.println(i+" "+j+cframe.getCell(i,j).getColour());
                JButton button = new JButton();
                if(cframe.getCell(i,j).getColour().equals("BLACK") ) {
                    button.setBackground(Color.BLACK.darker());
                }
                else if (cframe.getCell(i,j).getColour().equals("WHITE")){
                    button.setBackground(Color.WHITE.darker());
                }
                else if (cframe.getCell(i,j).getColour().equals("GREEN")){
                    button.setBackground(Color.GREEN.darker());
                }
                else if (cframe.getCell(i,j).getColour().equals("BLUE")){
                    button.setBackground(Color.BLUE.darker());
                }
                else if (cframe.getCell(i,j).getColour().equals("RED")){
                    button.setBackground(Color.RED.darker());
                }
                else if (cframe.getCell(i,j).getColour().equals("YELLOW")){
                    button.setBackground(Color.YELLOW.darker());
                }
                button.setOpaque(true);
                button.setBorderPainted(false);
                frame.add(button);
            }
        }
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        yaxis=yaxis+50;
        xaxis=xaxis+50;
    }

    public void chessRule (MAFrame cframe){

        Cell fcell = new Cell("SQUARE","BLACK","DARK","NONE",false);
        cframe.insertCell(fcell,0,0);

        for(int i=0;i<8;i++){
            for(int j=0;j<8;j++){

                if(j== 0 && i > 0){
                    cframe.insertCell(cframe.getCell(i-1, j+7), i, j );
                }
                cframe.insertCell(new MARule().chessRule(cframe.getCell(i, j), i, j), i, j + 1);

                cframe.displaycell(i,j);
            }
            MAFrameSet frameset = new MAFrameSet ();
            frameset.view(cframe);
        }



    }

    public void checkerRule (MAFrame chframe) {

        Cell fhcell = new Cell("SQUARE", "GREEN", "DARK", "NONE", false);
        chframe.insertCell(fhcell, 0, 0);

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {

                if (j == 0 && i > 0) {
                    chframe.insertCell(chframe.getCell(i - 1, j + 9), i, j);
                }
                chframe.insertCell(new MARule().checkersRule(chframe.getCell(i, j), i, j), i, j + 1);

                chframe.displaycell(i, j);
            }
        }
    }

    public void stepRule (MAFrame chframe) {

        Cell fhcell = new Cell("SQUARE", "BLUE", "DARK", "NONE", false);
        chframe.insertCell(fhcell, 0, 0);

        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {

                chframe.insertCell(new MARule().stepRule(chframe.getCell(i, j), i, j), i, j );

                chframe.displaycell(i, j);
            }
            MAFrameSet frameset = new MAFrameSet ();
            frameset.view(chframe);
        }
    }

    public void plusRule (MAFrame plusframe) {


                MAFrameSet frameset = new MAFrameSet();


                for(int i=0;i<plusframe.getRow();i++){
                    frameset.view(new MARule().plusRule(plusframe,i));
                }


    }




        public static void main(String[] args) {

        MAFrameSet frameset = new MAFrameSet ();
      //  MAFrame cframe = new MAFrame(8,8);
       // frameset.chessRule(cframe);
     //  frameset.view(cframe);
      /*  MAFrame chframe = new MAFrame(10,10);
        frameset.checkerRule(chframe);
        frameset.view(chframe);*/
       /* MAFrame stepframe = new MAFrame(20,20);
        frameset.stepRule(stepframe);*/
        //frameset.view(stepframe);*/
         /*   MAFrame pyrframe = new MAFrame(13,13);
        frameset.pyramidpRule(pyrframe);*/

            MAFrame plusframe = new MAFrame(9,9);
            frameset.plusRule(plusframe);

    }

}
