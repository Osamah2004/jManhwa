package org.example.jmanhwa;

import javafx.application.Application;

public class Launcher {
    public static void main(String[] args) {
        // Add JavaFX modules to the module path
        System.setProperty("javafx.verbose", "true");
        Application.launch(Main.class, args);
    }
} 