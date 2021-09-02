package src.controllers;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;

import src.util.AlertUtil;
import src.util.RecentFilesUtil;
import src.util.TextFileManager;
import src.util.TextInputUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.TitledPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;

public class MainController extends AbstractController {
    @FXML
    private TitledPane recentTitledPane;

    @FXML
    private Button editFileButton;

    @FXML
    private Button tutorialButton;

    @FXML
    private Button newFileButton;

    private File file;

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
        menuBar.setUseSystemMenuBar(
                System.getProperty("os.name") != null && System.getProperty("os.name").startsWith("Mac"));
        setRecentFilesInPane();
        setRecentFilesInMenuBar();
    }

    /**
     * Initiates the edit file version of the creator stage
     * 
     * @param event ActionEvent
     * @throws IOException
     */
    @FXML
    void editFilePressed(ActionEvent event) throws IOException {
        file = editFileSetUp();

        TextFileManager.setTextFile(file);
        RecentFilesUtil.putRecentFile(file.getAbsolutePath());
        
        if(checkHeader() == false){
            return;
        }

        activateCreatorController();
    }

    /**
     * Initiates the new file version of the creator stage
     * 
     * @param event ActionEvent
     * @throws IOException
     */
    @FXML
    void newFilePressed(ActionEvent event) throws IOException {
        file = newFileSetup();
        TextFileManager.setTextFile(file);
        RecentFilesUtil.putRecentFile(file.getAbsolutePath());

        if(checkHeader() == false){
            return;
        }

        activateCreatorController();
    }

    // TODO
    @FXML
    void tutorialPressed(ActionEvent event) {
        for (String s : RecentFilesUtil.recentFilesList) {
            System.out.println(s);
        }
    }

    /**
     * Creates and places a new file through user input and the selection of a
     * directory in the file explorer.
     * 
     * @return File textFile
     */
    private File newFileSetup() {
        while (true) {

            TextInputDialog td = new TextInputDialog();

            td.setContentText("Enter A File Name: ");
            td.showAndWait();

            String fileName = td.getResult();

            if (fileName != null) {
                File selectedDirectory = new File("foo.ngc"); // must be initalized in order to avoid errors in code
                                                              // below
                boolean checkNull = true;

                while (checkNull) {
                    DirectoryChooser directoryChooser = new DirectoryChooser();
                    selectedDirectory = directoryChooser.showDialog(current_stage);
                    if (selectedDirectory == null) {
                        return null;
                    } else {
                        checkNull = false;
                        System.out.println(selectedDirectory.getAbsolutePath());
                    }
                }

                File file = new File(selectedDirectory.getAbsolutePath() + "/" + fileName + ".ngc");

                // Error management
                if (!file.exists() && !file.getAbsolutePath().contains("null")) {
                    try {
                        file.createNewFile();
                        System.out.println(file.getAbsolutePath());
                        return file;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else if (file.getAbsolutePath().contains("null")) {
                    AlertUtil.alertRunner(AlertType.ERROR, "ERROR MESSAGE", "ERROR: FILE NAME OR DIRECTORY NOT SPECIFIED");
                } else {
                    AlertUtil.alertRunner(AlertType.ERROR, "ERROR MESSAGE", "ERROR: FILE ALREADY CREATED");
                }
            } else {
                break;
            }
        }
        return null;
    }

    /**
     * Opens the file explorer to allow user to select a file
     */
    private File editFileSetUp() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Open Resource File");
        fileChooser.getExtensionFilters().addAll(new ExtensionFilter("Text Files", "*.ngc"));

        File selectedFile = fileChooser.showOpenDialog(current_stage);

        return selectedFile;
    }

    private void setRecentFilesInPane() {
        List<String> rFList = RecentFilesUtil.recentFilesList;
        GridPane filesGridPane = new GridPane();

        for (int i = 0; i < rFList.size(); i++) {
            String fileShort = rFList.get(i).substring(rFList.get(i).lastIndexOf("/") + 1);
            Hyperlink fileName = new Hyperlink(fileShort);
            int index = i;

            fileName.setOnAction(event -> {
                File file = new File(rFList.get(index));
                TextFileManager.setTextFile(file);
                try {
                    if(checkHeader() == false){
                        return;
                    }
                    activateCreatorController();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                RecentFilesUtil.putRecentFile(file.getAbsolutePath());
            });

            filesGridPane.add(fileName, 0, i);
        }

        recentTitledPane.setContent(filesGridPane);
    }

    public boolean checkHeader() {
        HashMap<Integer, String> textMap = new HashMap<Integer, String>(TextFileManager.TEXT_LINES_MAP);

        if(textMap.size() == 0){
            return formHeader();
        }
        else if(!textMap.get(1).contains("(Project: ")) {
            return formHeader();
        }

        return true;
    }

    public boolean formHeader(){
        String projectTitle = TextInputUtil.textInputRunner("Enter Title For Project: ");
        projectTitle = "(Project: " + projectTitle + ")";

        String name = TextInputUtil.textInputRunner("Enter Your Name: ");
        name = "(Created by: " + name + ")";


        String spinRateStr = "";
        double spinRate = 0;

        boolean dblCheck = false;
        while(!dblCheck){
            String spinRateStr2 = TextInputUtil.textInputRunner("Enter Spindle Spin Rate (RPM): ");

            try {
                spinRate = Double.parseDouble(spinRateStr2);
                dblCheck = true;
            } catch (Exception e) {
                AlertUtil.alertRunner(AlertType.ERROR, "ERROR MESSAGE", "ERROR: Spin Rate Must Expressed As A Decimal or Integer");
                dblCheck = false;
            }
        }

        String feedRateStr = "";
        double feedRate = 0;

        dblCheck = false;
        while(!dblCheck){
            String feedRateStr2 = TextInputUtil.textInputRunner("Enter Feed Rate (IN/MIN): ");
            try {
                feedRate = Double.parseDouble(feedRateStr2);
                dblCheck = true;
            } catch (Exception e) {
                AlertUtil.alertRunner(AlertType.ERROR, "ERROR MESSAGE", "ERROR: Feed Rate Must Expressed As A Decimal or Integer");
            }
        }

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        Date date = new Date();
        String dateStr = "(Created on: " + formatter.format(date) + ")";

        spinRateStr = "S " + spinRate + " (RPM)";
        feedRateStr = "F " + feedRate + " (Feed, inches/minute)";

        if(name.contains("null") || projectTitle.contains("null") || spinRateStr.contains("null") || feedRateStr.contains("null")){
            return false;
        }
        else{
            TextFileManager.setHeader(projectTitle, name, dateStr, spinRateStr, feedRateStr);
            return true;
        }
    }
}