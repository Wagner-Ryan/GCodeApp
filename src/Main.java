package src;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import src.controllers.*;


public class Main extends Application {

    Scene home;

    //Main method initiates program
    public static void main(String[] args){
        launch(args);
    }

    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * <p>
     * NOTE: This method is called on the JavaFX Application Thread.
     * </p>
     *
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set. The primary stage will be embedded in
     * the browser if the application was launched as an applet.
     * Applications may create other stages, if needed, but they will not be
     * primary stages and will not be embedded in the browser.
     */
    @Override
    public void start(Stage primaryStage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/src/Scenes/Main.fxml"));
        Parent root = (Parent)loader.load();
        home = new Scene(root, 640, 400);
        
        primaryStage.setTitle("SPARKS");
        primaryStage.setScene(home);

        primaryStage.setOnShown(new EventHandler<WindowEvent>() {
            @Override
            public void handle(WindowEvent event) {
                AbstractController.current_stage = primaryStage;
            }
        });

        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
