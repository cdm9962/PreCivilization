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
}
