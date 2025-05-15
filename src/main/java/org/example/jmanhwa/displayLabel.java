package org.example.jmanhwa;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class displayLabel {
    public displayLabel(String text, String title) {
        Stage stage = new Stage();
        
        // Create components
        Label label = new Label(text);
        label.setWrapText(true); // Enable text wrapping
        label.setMaxWidth(230); // Set max width for wrapping (scene width - padding)
        Button close = new Button("Close");
        
        // Create VBox and set its properties
        VBox root = new VBox(10); // 10 pixels spacing between components
        root.setPadding(new javafx.geometry.Insets(20)); // Add padding around components
        root.setPrefWidth(250); // Set preferred width
        
        // Add components to VBox
        root.getChildren().addAll(label, close);
        
        // Set up the scene with dynamic sizing
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle(title); // Use the provided title
        
        // Set minimum width but allow height to adjust
        stage.setMinWidth(250);
        stage.sizeToScene(); // Adjust stage size to fit content
        
        // Handle close button
        close.setOnAction(e -> stage.close());
        
        stage.show();
    }
}
