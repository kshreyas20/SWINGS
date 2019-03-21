
import java.util.ArrayList;
import java.util.Arrays;

public class MACellHelper {

    private ArrayList<String> shapes;
    private ArrayList<String> colours;
    private ArrayList<String> shades;

    public MACellHelper(ArrayList<String> shapes, ArrayList<String> colours, ArrayList<String> shades) {
        this.shapes = shapes;
        this.colours = colours;
        this.shades = shades;
    }

    public MACellHelper() {

        this.shapes = new ArrayList<String>(Arrays.asList("SQUARE","CIRCLE","TRIANGLE"));
        this.colours = new ArrayList<String>(Arrays.asList("WHITE","BLACK","RED","GREEN","BLUE","YELLOW"));
        this.shades = new ArrayList<String>(Arrays.asList("BRIGHT","DARK","NEUTRAL"));
    }

    public ArrayList<String> getShapes() {
        return shapes;
    }

    public void setShapes(ArrayList<String> shapes) {
        this.shapes = shapes;
    }

    public ArrayList<String> getColours() {
        return colours;
    }

    public void setColours(ArrayList<String> colours) {
        this.colours = colours;
    }

    public ArrayList<String> getShades() {
        return shades;
    }

    public void setShades(ArrayList<String> shades) {
        this.shades = shades;
    }


}
