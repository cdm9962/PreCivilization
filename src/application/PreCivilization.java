package application;

import gui.GameInterface;

import javafx.application.Application;

/**
 * The launcher for PreCivilization.
 *
 * @author Connor D. Milligan
 */
public class PreCivilization {
    public static void main(String[] args) {
        GameInterface game = new GameInterface();
        Application.launch(GameInterface.class);
    }
}
