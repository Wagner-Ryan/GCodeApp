package src.controllers;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import src.util.TextFileManager;


public class DeleteWindowController extends CreatorController{
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac"));

        setTextArea();
        addResizeListeners();
        setRecentFilesInMenuBar();

        textArea.setMinWidth(textAreaPane.getWidth());

        HBox.setHgrow(splitPane, Priority.ALWAYS);
        VBox.setVgrow(hBox, Priority.ALWAYS);
        findLine();
    }

    @FXML
    protected void openChatButtonPressed(ActionEvent e){
        try {
            activateChat();
        } catch (IOException e1) {
            e1.printStackTrace();
        }
    }
    
    private void findLine(){
        String text = TextFileManager.getTextFile().toString();
        System.out.println(text);
    }
    
}