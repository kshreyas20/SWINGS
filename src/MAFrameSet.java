import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.GridLayout;
import java.awt.Color;

public class MAFrameSet {

    private ArrayList<MAFrame> framearray = new ArrayList<>();
    private static int yaxis = 0;
    private static int xaxis = 0;

    public void view (MAFrame displayFrame) {


        JFrame frame = new JFrame();
        frame.setTitle("Demonstrate valid 2D Mobile Automata");
        frame.setSize(400, 800);
        frame.setLocation(xaxis, yaxis);
        frame.setResizable(true);
        frame.setLayout(new GridLayout(displayFrame.getRow(), displayFrame.getColumn()));
        frame.setBackground(Color.BLACK);

       // System.out.print("======display=======");
        for (int i = 0; i < displayFrame.getRow(); i++) {
            for (int j = 0; j < displayFrame.getColumn(); j++) {

                //System.out.println(i + " " + j + displayFrame.getCell(i, j).getColour());
                JButton button = new JButton();
                if (displayFrame.getCell(i, j).getShade().equals("DARK")) {
                    if (displayFrame.getCell(i, j).getColour().equals("BLACK")) {
                        button.setBackground(Color.BLACK.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("WHITE")) {
                        button.setBackground(Color.WHITE.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("GREEN")) {
                        button.setBackground(Color.GREEN.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("BLUE")) {
                        button.setBackground(Color.BLUE.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("RED")) {
                        button.setBackground(Color.RED.darker());
                    } else if (displayFrame.getCell(i, j).getColour().equals("YELLOW")) {
                        button.setBackground(Color.YELLOW.darker());
                    }

                } else if (displayFrame.getCell(i, j).getShade().equals("BRIGHT")) {
                    if (displayFrame.getCell(i, j).getColour().equals("BLACK")) {
                        button.setBackground(Color.BLACK.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("WHITE")) {
                        button.setBackground(Color.WHITE.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("GREEN")) {
                        button.setBackground(Color.GREEN.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("BLUE")) {
                        button.setBackground(Color.BLUE.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("RED")) {
                        button.setBackground(Color.RED.brighter());
                    } else if (displayFrame.getCell(i, j).getColour().equals("YELLOW")) {
                        button.setBackground(Color.YELLOW.brighter());
                    }

                } else {
                    if (displayFrame.getCell(i, j).getColour().equals("BLACK")) {
                        button.setBackground(Color.BLACK);
                    } else if (displayFrame.getCell(i, j).getColour().equals("WHITE")) {
                        button.setBackground(Color.WHITE);
                    } else if (displayFrame.getCell(i, j).getColour().equals("GREEN")) {
                        button.setBackground(Color.GREEN);
                    } else if (displayFrame.getCell(i, j).getColour().equals("BLUE")) {
                        button.setBackground(Color.BLUE);
                    } else if (displayFrame.getCell(i, j).getColour().equals("RED")) {
                        button.setBackground(Color.RED);
                    } else if (displayFrame.getCell(i, j).getColour().equals("YELLOW")) {
                        button.setBackground(Color.YELLOW);

                    }
                    button.setOpaque(true);
                    button.setBorderPainted(false);
                    button.setRequestFocusEnabled(displayFrame.getCell(i, j).isEffects());
                    if (!displayFrame.getCell(i, j).getTextfield().equals("NONE")) {
                        button.setText(displayFrame.getCell(i, j).getTextfield());
                    }
                    frame.add(button);
                }
            }
            frame.setVisible(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            yaxis = yaxis + 50;
            xaxis = xaxis + 50;
        }


    }

    public static void chessdisplay(int numberOfFrame){

        MARule chessrule = new MARule("CHESS");
        MAFrameSet frames = new MAFrameSet ();
        frames.framearray.add(new MAFrame(numberOfFrame,numberOfFrame));
        for (int i=0;i<numberOfFrame;i++)
        {
            frames.framearray.add(i+1,chessrule.getNewframe(frames.framearray.get(i),i));
            frames.view(frames.framearray.get(i));

        }

    }

    public static void stepisplay(int numberOfFrame){

        MARule steprule = new MARule("STEPS");
        MAFrameSet frames = new MAFrameSet ();
        frames.framearray.add(new MAFrame(numberOfFrame,numberOfFrame));
        for (int i=0;i<numberOfFrame;i++)
        {
            frames.framearray.add(i+1,steprule.getNewframe(frames.framearray.get(i),i));
            frames.view(frames.framearray.get(i));

        }

    }

    public static void pyramidisplay(int numberOfFrame){

        MARule pyramidrule = new MARule("PYRAMID");
        MAFrameSet frames = new MAFrameSet ();
        frames.framearray.add(0,new MAFrame(numberOfFrame,numberOfFrame));

        for (int i=0;i<numberOfFrame;i++)
        {
            frames.framearray.add(i+1,pyramidrule.getNewframe(frames.framearray.get(i),i));
           //frames.view(frames.framearray.get(i));

        }

    }

    public static void mobiledisplay(int numberOfFrame){

        MARule mobilerule = new MARule("MOBILE");
        MAFrameSet frames = new MAFrameSet ();
        frames.framearray.add(0,new MAFrame(numberOfFrame,20));

        for (int i=0;i<numberOfFrame;i++)
        {
            frames.framearray.add(i+1,mobilerule.getNewframe(frames.framearray.get(i),i));
           // frames.view(frames.framearray.get(i));

        }
        frames.view(frames.framearray.get(numberOfFrame));
    }

        public static void main(String[] args) {


        //chessdisplay(8);
         //stepisplay(10);
             //pyramidisplay(31);
            mobiledisplay(60);





    }

}
