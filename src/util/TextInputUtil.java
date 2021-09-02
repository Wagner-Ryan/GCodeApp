package src.util;

import javafx.scene.control.TextInputDialog;

public class TextInputUtil {
    public static String textInputRunner(String contentText){
        TextInputDialog tID = new TextInputDialog();
        tID.setContentText(contentText);
        tID.showAndWait();
        tID.close();

        return tID.getResult();
    }
}
