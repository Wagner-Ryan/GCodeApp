package src.code;

import javafx.scene.control.Alert.AlertType;
import src.util.AlertUtil;
import src.util.TextInputUtil;

public enum Codes implements CodeBasic{

    HL("Horizontal Line", 1), VL("Vertical Line", 2), DL("Diagonal Line", 3), ARC("Arc", 4), Point("Point", 5),
    Circle("Circle", 11), Rectangle("Rectangle", 12), Triangle("Triangle", 13), Trapezoid("Trapezoid", 14),
    FeedRate("Feed Rate Change", 21);



    private String name;
    private int action;

    private Codes(String name, int action)
    {
        this.name = name;
        this.action = action;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getAction() {
        return action;
    }
}