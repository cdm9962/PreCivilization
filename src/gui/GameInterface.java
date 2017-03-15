package gui;

import javafx.application.Application;
import javafx.stage.Stage;
import model.GameModel;

import java.util.Observable;
import java.util.Observer;

/**
 * @author Connor D. Milligan
 */
public class GameInterface extends Application implements Observer {
    private GameModel model;

    public GameInterface(){
        this.model = new GameModel();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
