package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.GameModel;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Connor D. Milligan
 */
public class GameInterface extends Application implements Observer {
    private GameModel model;

    public static final String TITLE = "PreCivilization";

    public GameInterface(){
        this.model = new GameModel();
    }

    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle(TITLE);
        primaryStage.setScene(new Scene(makeBorder()));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        super.stop();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private BorderPane makeBorder(){
        BorderPane border = new BorderPane();
        border.setTop(makeLabel());
        return border;
    }

    private Pane makeLabel(){
        Label label = new Label("Welcome to PreCivilization!");
        Pane pane = new Pane(label);
        return pane;
    }
}
