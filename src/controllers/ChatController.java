package src.controllers;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.security.Principal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.Scanner;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.WindowEvent;
import src.chat.ChatRunner;

public class ChatController implements Initializable {
    @FXML
    TextArea textArea;

    @FXML
    TextField textField;

    private File file;

    private ChatRunner chatRunner;

    /**
     * Called to initialize a controller after its root element has been completely
     * processed.
     * 
     * Sets menu bar to system default if on MacOS.
     * 
     * Method from interface Initalizeable. Initalizeable interface originally
     * implemented in "AbstractController."
     * 
     * @param location  The location used to resolve relative paths for the root
     *                  object, or <tt>null</tt> if the location is not known.
     *
     * @param resources The resources used to localize the root object, or
     *                  <tt>null</tt> if the root object was not localized.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        Date date = new Date();
        SimpleDateFormat sDateFormat = new SimpleDateFormat("yyyy-MM-dd:HH:mm:ss");
        String dateFormat = sDateFormat.format(date);

        file = new File("/Users/alexscheibe/Documents/sparks_temp/chat_log: " + dateFormat + ".txt");

        try {
            file.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        chatRunner = new ChatRunner();

        textArea.setText("**Initiate Chat**");
    }

    @FXML
    protected void onTextInput() throws FileNotFoundException {
        PrintWriter printWriter = new PrintWriter(file);

        String textToWrite;
        String inputText = textField.getText();
        String ogTextAreaText = textArea.getText();

        chatRunner.setInput(inputText);

        textToWrite = ogTextAreaText + "\n>" + inputText + "\n" + chatRunner.getOutput();

        textArea.setText(textToWrite);
        printWriter.write(textToWrite);

        textField.clear();
        printWriter.close();
    }
}