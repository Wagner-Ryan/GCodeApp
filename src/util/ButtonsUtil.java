package src.util;

import java.util.ArrayList;
import java.util.List;

import javafx.geometry.Insets;
import javafx.scene.control.Button;

public abstract class ButtonsUtil {
    private static final Button horizontaLine, verticalLine, diagonalLine, arc, point;
    private static final Button circle, triangle, rectangle, trapezoid;
    private static final Button toolChange, coolantOn, coolantOff, speedChange, feedRateChange, returnToStart;

    public static final List<Button> MASTER_LIST = new ArrayList<Button>(); 
    public static final List<Button> LINES_LIST = new ArrayList<Button>();
    public static final List<Button> TWO_D_SHAPES_LIST = new ArrayList<Button>();
    public static final List<Button> UTIL_LIST = new ArrayList<Button>();
    public static final List<Button> MISC_LIST = new ArrayList<Button>();

    private static final Insets paddingValues = new Insets(5, 5, 5, 5);

    static{
        horizontaLine = new Button("Horizontal Line");
        verticalLine = new Button("Vertical Line");
        diagonalLine = new Button("Diagonal Line");
        arc = new Button("Arc");
        point = new Button("Point");

        circle = new Button("Circle");
        triangle = new Button("Triangle");
        rectangle = new Button ("Square");
        trapezoid = new Button("Trapezoid");

        toolChange = new Button ("Tool Change");
        speedChange = new Button ("Speed Change");
        feedRateChange = new Button("Feed Rate Change");
        returnToStart = new Button("Return To Starting Point");
        coolantOff = new Button("Coolant Off");
        coolantOn = new Button("Coolant On");

        LINES_LIST.add(horizontaLine);
        LINES_LIST.add(verticalLine);
        LINES_LIST.add(diagonalLine);
        LINES_LIST.add(arc);
        LINES_LIST.add(point);

        TWO_D_SHAPES_LIST.add(circle);
        TWO_D_SHAPES_LIST.add(rectangle);
        TWO_D_SHAPES_LIST.add(triangle);
        TWO_D_SHAPES_LIST.add(trapezoid);
        
        UTIL_LIST.add(toolChange);
        UTIL_LIST.add(speedChange);
        UTIL_LIST.add(feedRateChange);
        UTIL_LIST.add(returnToStart);
        UTIL_LIST.add(coolantOff);
        UTIL_LIST.add(coolantOn);



        for(Button b : LINES_LIST)
        {
            b.setPadding(paddingValues);
            b.setPrefWidth(375);
            MASTER_LIST.add(b);
        }

        for(Button b : TWO_D_SHAPES_LIST)
        {
            b.setPadding(paddingValues);
            b.setPrefWidth(375);
            MASTER_LIST.add(b);
        }

        for(Button b : UTIL_LIST)
        {
            b.setPadding(paddingValues);
            b.setPrefWidth(375);
            MASTER_LIST.add(b);
        }
    }
}