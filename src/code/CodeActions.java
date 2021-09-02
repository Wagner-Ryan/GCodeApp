package src.code;

import javafx.scene.control.Alert.AlertType;
import src.util.AlertUtil;
import src.util.TextInputUtil;

public class CodeActions {

    public static String lineParams(int type) {
        if (type < 10) {
            return oneDLineParams(type);
        }
        if(type < 20){
            return twoDParams(type);
        }
        if(type < 30){
            return utilParams(type);
        }

        return null;
    }

    private static String utilParams(int type) {
        if(type < 21){
            boolean dblCheck = false;
            while(!dblCheck){
                String feedRateStr = TextInputUtil.textInputRunner("Enter Feed Rate (IN/MIN): ");
                try {
                    double feedRate = Double.parseDouble(feedRateStr);
                    dblCheck = true;
                    return "F " + feedRate + " (Feed, inches/minute)";
                } catch (Exception e) {
                    AlertUtil.alertRunner(AlertType.ERROR, "ERROR MESSAGE", "ERROR: Feed Rate Must Expressed As A Decimal or Integer");
                }
            }
        }

        return null;
    }

    /**
     * 
     * @param type 1 = horizontal line, 2 = vertical line, 3 = diagonal line 4 =
     *             arc, 5 = point;
     * @return
     */
    public static String oneDLineParams(int type) {
        if (type == 1) {
            boolean checkDbl = false;
            while (!checkDbl) {
                try {
                    String xStr = TextInputUtil.textInputRunner(
                            "Enter length of horizontal line (Negative for left, positive for right): ");
                    double x = Double.parseDouble(xStr);
                    return "G1 X " + x + (" Y 0 (Horizontal line " + x + " in. long)");
                } catch (Exception e) {
                    AlertUtil.alertRunner(AlertType.ERROR, "ERROR",
                            "DIMENSIONS MUST BE ENTERED AS A DECIMAL OR INTEGER");
                    checkDbl = false;
                }
            }
        }

        if (type == 2) {
            boolean checkDbl = false;
            while (!checkDbl) {
                try {
                    String yStr = TextInputUtil
                            .textInputRunner("Enter length of vertical line (Negative for left, positive for right): ");
                    double y = Double.parseDouble(yStr);
                    return "G1 X 0 Y " + y + (" (Vertical line " + y + " in. long)");
                } catch (Exception e) {
                    AlertUtil.alertRunner(AlertType.ERROR, "ERROR",
                            "DIMENSIONS MUST BE ENTERED AS A DECIMAL OR INTEGER");
                    checkDbl = false;
                }
            }
        }

        if (type == 3) {
            boolean checkDbl = false;
            while (!checkDbl) {
                try {
                    AlertUtil.alertRunner(AlertType.INFORMATION, "INFO",
                            "Diagonal line is formed from a horizontal and vertical length. \n"
                                    + "Think of the diagonal as the hypotenuse of a triangle, with the horizontal being the width and the veritcal being the height.");
                    String xStr = TextInputUtil.textInputRunner(
                            "Enter length of horizontal component (Negative for left, positive for right): ");
                    double x = Double.parseDouble(xStr);
                    String yStr = TextInputUtil.textInputRunner(
                            "Enter length of vertical compnent (Negative for left, positive for right): ");
                    double y = Double.parseDouble(yStr);
                    return "G1 X " + x + " Y " + y + (" (Diagonal line "
                            + Math.round(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))) + " in. long)");
                } catch (Exception e) {
                    AlertUtil.alertRunner(AlertType.ERROR, "ERROR",
                            "DIMENSIONS MUST BE ENTERED AS A DECIMAL OR INTEGER");
                    checkDbl = false;
                }
            }
        }

        if (type == 4) {
            boolean checkDbl = false;
            while (!checkDbl) {
                try {
                } catch (Exception e) {
                    AlertUtil.alertRunner(AlertType.ERROR, "ERROR",
                            "DIMENSIONS MUST BE ENTERED AS A DECIMAL OR INTEGER");
                    checkDbl = false;
                }
            }
        }

        if (type == 5) {
            boolean checkDbl = false;
            while (!checkDbl) {
                try {
                    AlertUtil.alertRunner(AlertType.INFORMATION, "INFO",
                            "Diagonal line is formed from a horizontal and vertical length. \n"
                                    + "Think of the diagonal as the hypotenuse of a triangle, with the horizontal being the width and the veritcal being the height.");
                    String xStr = TextInputUtil.textInputRunner(
                            "Enter length of horizontal component (Negative for left, positive for right): ");
                    double x = Double.parseDouble(xStr);
                    String yStr = TextInputUtil.textInputRunner(
                            "Enter length of vertical compnent (Negative for left, positive for right): ");
                    double y = Double.parseDouble(yStr);
                    return "G1 X " + x + " Y " + y + (" (Diagonal line "
                            + Math.round(Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2))) + " in. long)");
                } catch (Exception e) {
                    AlertUtil.alertRunner(AlertType.ERROR, "ERROR",
                            "DIMENSIONS MUST BE ENTERED AS A DECIMAL OR INTEGER");
                    checkDbl = false;
                }
            }
        }

        return null;
    }

    public static String twoDParams(int type) {
        if (type == 11) {
            boolean checkDbl = false;
            while (!checkDbl) {
                try {
                    String xStr = TextInputUtil
                            .textInputRunner("Enter starting point of X (Negative for left, positive for right): ");
                    double x = Double.parseDouble(xStr);
                    String yStr = TextInputUtil
                            .textInputRunner("Enter Starting point of Y (Negative for left, positive for right): ");
                    double y = Double.parseDouble(yStr);

                    String rStr = TextInputUtil.textInputRunner("Enter radius");
                    double r = Double.parseDouble(rStr);
                    return "G02 X " + x + " Y " + y + " R " + r + " (Circle at " + x + "," + y + " with radius of " + r +")";
                } catch (Exception e) {
                    AlertUtil.alertRunner(AlertType.ERROR, "ERROR",
                            "DIMENSIONS MUST BE ENTERED AS A DECIMAL OR INTEGER");
                    checkDbl = false;
                }
            }
        }
        return null;
    }
}
