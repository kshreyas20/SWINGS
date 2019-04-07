//package edu.neu.csye6200.uia5;

import javafx.scene.shape.Circle;

import java.awt.*;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import javax.swing.JPanel;


/**
 * A sample canvas that draws a rainbow of lines
 * @author MMUNSON
 */
public class MACanvas extends JPanel implements Observer {

	private static final long serialVersionUID = 1L;
	private Logger log = Logger.getLogger(MACanvas.class.getName());
    private int stepSize = 20;
    private Color col;
    private long counter = 0L;
	private static MAFrame localFrame = new MAFrame(); // Create a static Frame to display the frame in canvas


	/**
     * MACanvas constructor
     */
	public MACanvas() {
		log.info("MACanvas created");
		col = Color.WHITE;
	}

	/**
	 * The UI thread calls this method when the screen changes, or in response.
	 * to a user initiated call to repaint();
	 */
	public void paint(Graphics g) {

		log.info("Paint Method Called");
		drawMA( (Graphics2D) g); // Our Added-on drawing
    }
	
	/**
	 * Draw the MA graphics panel
	 * @param g
	 */

	public void drawMA(Graphics2D g2d) {

		log.info("Two Display frame Called");
		Dimension size = getSize();

		g2d.fillRect(0, 0, size.width, size.height);
		g2d.setColor(Color.BLACK);


		Color c = new Color(1,1,1); //Default colour to be painted

			for (int i = 0; i <localFrame.getRow(); i++){
				for (int j = 0; j < localFrame.getColumn(); j++){

					boolean Ovalshape = false; // Default shape is rectanagle

					int startx = i*stepSize;
					int starty = j*stepSize;

					int width = size.width/10;
					int height = size.height/10;

					// Set the Color based on the Cell Object state

					switch (localFrame.getCell(i, j).getColour()) {
						case "BLACK":
							c = Color.BLACK;
							break;
						case "WHITE":
							c = Color.WHITE;
							break;
						case "GREEN":
							c = Color.GREEN;
							break;
						case "BLUE":
							c = Color.BLUE;
							break;
						case "RED":
							c = Color.RED;
							break;
						case "YELLOW":
							c = Color.YELLOW;
							break;
					}

				if(!localFrame.getCell(i,j).getTextfield().equals("NONE")){  // If the Cell has Text field the Make it Oval and Green in Colour
					c = Color.GREEN;
					Ovalshape=true;
				}


				// Draw a box, one pixel less than the step size to create a black outline
				paintRect(g2d, startx, starty,stepSize-1,stepSize-1 , c,Ovalshape);// Creating a fixed size pixel


			}
		}

	}

	
	/*
	 * A local routine to ensure that the color value is in the valid 0 to 255 range.
	 */
	private int validColor(int colorVal) {
		//if (colorVal > 255)
		//	colorVal = 255;
		//if (colorVal < 0)
		//	colorVal = 0;
		colorVal &= 0xFF; // It's faster to just boolean Ovalshape = false;crop the value to the lower 8 bits (i.e. 0x00 through 0xFF)
		return colorVal;
	}
	
	/*
	 * A convenience routine to set the color and draw a line
	 * @param g2d the 2D Graphics context
	 * @param startx the line start position on the x-Axis
	 * @param starty the line start position on the y-Axis
	 * @param endx the line end position on the x-Axis
	 * @param endy the line end position on the y-Axis
	 * @param color the line color
	 */
	private void paintLine(Graphics2D g2d, int startx, int starty, int endx, int endy, Color color) {
		g2d.setColor(color);
		g2d.drawLine(startx, starty, endx, endy);
	}
	
	/*
	 * A convenience routine to set the color and draw a filled rectangle
	 * @param g2d
	 * @param x the upper top left box start position on the x-Axis
	 * @param y the upper top left box start position on the y-Axis
	 * @param width the width in pixels
	 * @param height the height in pixels
	 * @param color
	 */
	private void paintRect(Graphics2D g2d, int x, int y, int width, int height, Color color,boolean OvalShape) {
		g2d.setColor(color);

		if (OvalShape){
			g2d.fillOval(x, y, width, height);
		}
		else {
			g2d.fillRect(x, y, width, height);
		}

	}	

	// Get the frame object from the frameset class Using Command Desgin Pattern

	@Override 
	public void update(Observable o, Object arg) {
		log.info("MACanvas received an update");
		localFrame = (MAFrame)arg; // get the frame from frameset to the store it in static localframe variable
		this.repaint();// Request the UI to redraw the JPanel
	}
	
}
