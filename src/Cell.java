

public class Cell {

    private static MACellHelper cellhelp = new MACellHelper();
    private String shape;
    private String colour;
    private String shade;
    private String textfield;
    private boolean effects;
    private static int counter = 0;


    public Cell(String shape, String colour, String shade, String textfield, boolean effects) {
        setShape(shape);
        setColour(colour);
        setShade(shade);
        setTextfield(textfield);
        this.effects = effects;
        counter();

    }


    public Cell() {
        this.colour = "BLACK";
        this.shape = "SQUARE";
        this.shade = "NEUTRAL";
        this.textfield = "NONE";
        this.effects = false;
        counter();
    }



    public String getShape() {
        return shape;
    }

    public void setShape(String shape) {

            this.shape = "SQUARE"; // Enforcing the shape of the cell to Square - Button only supports Square shape

    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {

        if(cellhelp.getColours().contains(colour)){
            this.colour = colour;
        }
        else{
            this.colour = "BLACK";
        }
    }

    public String getShade() {
        return shade;
    }

    public void setShade(String shade) {


        if(cellhelp.getShades().contains(shade)){
            this.shade = shade;
        }
        else{
            this.shade = "NEUTRAL";
        }
    }

    public String getTextfield() {
        return textfield;
    }

    public void setTextfield(String textfield) {

        if(textfield.length() < 5) {
            this.textfield = textfield;
        }
        else{
            this.textfield = "NONE";
        }

    }

    public boolean isEffects() {
        return effects;
    }

    public void setEffects(boolean effects) {
        this.effects = effects;
    }

    public void counter() {
        counter++;
    }


    public static int getCounter() {
        return counter;
    }

    public String toString() {
        System.out.println();
        return String.format("%10s%10s%10s%10s%10s",shape,shade,colour,textfield,effects);

    }



}


