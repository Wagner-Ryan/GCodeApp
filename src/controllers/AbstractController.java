package src.controllers;

import java.io.File;
import java.io.IOException;
import java.util.List;

import src.util.RecentFilesUtil;
import src.util.TextFileManager;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Class for controllers to extend in order to get access to the primary stage
 */
public abstract class AbstractController implements Initializable {

    @FXML
    protected MenuBar menuBar;

    @FXML
    protected Menu openRecentMenu;

    public static Stage current_stage;

    
    /**
     * Opens the welcome stage and closes current stage
     * 
     */
    public void activateMainStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/Scenes/Main.fxml"));
        Parent root = (Parent) loader.load();
        Scene maker = new Scene(root, current_stage.getHeight(), current_stage.getWidth());
        Stage primaryStage = new Stage();

        primaryStage.setTitle("GCode Creator");
        primaryStage.setScene(maker);
        primaryStage.setResizable(false);
        primaryStage.show();
        current_stage = primaryStage;
    }

    /**
     * Opens the creator stage and closes current stage.
     * 
     * Sets up a new file
     *
     * 
     */
    public void activateCreatorController() throws IOException {
        if(TextFileManager.getTextFile().exists()){
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/Scenes/FileCreator.fxml"));
            Parent root = (Parent) loader.load();
            Scene maker = new Scene(root, 1074, 700);
            Stage creatorStage = new Stage();

            creatorStage.setTitle("GCode Creator");
            creatorStage.setScene(maker);

            creatorStage.show();

            current_stage.close();
            current_stage = creatorStage;
        }
        else{
            Alert alert = new Alert(AlertType.ERROR);
            alert.setTitle("ERROR MESSAGE");
            alert.setContentText("ERROR: File Not Found");
            alert.showAndWait();
            System.out.println("File not found");
        }
    }

    public void activateChat() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/Scenes/Chat.fxml"));
        Parent root = (Parent) loader.load();
        Scene chat = new Scene(root, 500, 350);
        Stage chatStage = new Stage();

        chatStage.setTitle("GCode Chat");
        chatStage.setScene(chat);

        chatStage.show();
    }

    /**
     * Changes the scene to the specefied fxml file
     *
     * 
     * @param currentStage current stage of the application
     * @param fxml         file path for fxml file to pull from for scene design
     */
    public void changeScene(Stage currentStage, String fxml) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(fxml));
        Parent root = (Parent) loader.load();
        Scene scene = new Scene(root, current_stage.getWidth(), current_stage.getHeight());

        currentStage.setScene(scene);
    }

    public void manageMenuBar() {

    }

    protected void setRecentFilesInMenuBar() {
        List<String> rFList = RecentFilesUtil.recentFilesList;

        for (int i = 0; i < rFList.size(); i++) {
            String fileShort = rFList.get(i).substring(rFList.get(i).lastIndexOf("/") + 1);
            MenuItem recentItem = new MenuItem(fileShort);
            int index = i;

            recentItem.setOnAction(event -> {
                File file = new File(rFList.get(index));
                TextFileManager.setTextFile(file);
                try {
                    activateCreatorController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

            openRecentMenu.getItems().add(recentItem);
        }
    }

    // TODO set up sylesheet selection
    public void setStyleSheet(Stage currentStage) {
    }

    // TODO set up preferences reading
    public void readPreferences() {

    }
}