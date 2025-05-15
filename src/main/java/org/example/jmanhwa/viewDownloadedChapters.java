package org.example.jmanhwa;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;
import javafx.stage.Stage;
import javafx.application.HostServices;
import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class viewDownloadedChapters {
    private TableView<String> table;
    private final HostServices hostServices;
    
    public viewDownloadedChapters(HostServices hostServices) {
        this.hostServices = hostServices;
        Stage stage = new Stage();
        
        // Create table
        table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.setMaxWidth(Double.MAX_VALUE);
        
        // Create title column
        TableColumn<String, String> titleColumn = new TableColumn<>("manhwa Title");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue()));
        titleColumn.setMinWidth(200);
        
        // Create combined chapter selection and view column
        TableColumn<String, HBox> actionColumn = new TableColumn<>("Chapter Selection & View");
        actionColumn.setCellValueFactory(data -> {
            HBox container = new HBox(10); // 10 pixels spacing
            container.setPadding(new Insets(5));
            
            ChoiceBox<String> chapterChoice = new ChoiceBox<>();
            List<String> chapters = generateHtmlForChapter.getAvailableChapters(data.getValue());
            chapterChoice.setItems(FXCollections.observableArrayList(chapters));
            if (!chapters.isEmpty()) {
                chapterChoice.setValue(chapters.get(0));
            }
            
            Button viewButton = new Button("View Chapter");
            
            // Update button text when choice changes
            chapterChoice.valueProperty().addListener((obs, oldVal, newVal) -> {
                if (newVal != null) {
                    viewButton.setText("View Chapter " + newVal);
                } else {
                    viewButton.setText("Select a chapter");
                }
            });
            
            // Set initial button text
            if (chapterChoice.getValue() != null) {
                viewButton.setText("View chapter " + chapterChoice.getValue());
            }
            
            viewButton.setOnAction(e -> {
                String manhwaTitle = data.getValue();
                String selectedChapter = chapterChoice.getValue();
                if (selectedChapter != null) {
                    // Generate HTML for all chapters if they don't exist
                    List<String> chapters2 = generateHtmlForChapter.getAvailableChapters(manhwaTitle);
                    for (String chapter : chapters2) {
                        new generateHtmlForChapter(manhwaTitle, chapter, chapters2);
                    }
                    
                    // Open the selected chapter in default browser
                    File htmlFile = new File("downloads" + File.separator + 
                        manhwaTitle + File.separator + 
                        selectedChapter + File.separator + 
                        "chapter_0.html");
                    hostServices.showDocument(htmlFile.toURI().toString());
                }
            });
            
            container.getChildren().addAll(chapterChoice, viewButton);
            return new SimpleObjectProperty<>(container);
        });
        actionColumn.setMinWidth(300);
        
        table.getColumns().addAll(titleColumn, actionColumn);
        
        // Load manhwa entries
        loadmanhwaEntries();
        
        // Create layout
        VBox root = new VBox(10);
        root.getChildren().add(table);
        
        // Create scene
        Scene scene = new Scene(root, 800, 400);
        stage.setTitle("Downloaded manhwa");
        stage.setScene(scene);
        stage.show();
    }
    
    private void loadmanhwaEntries() {
        File downloadsDir = new File("downloads");
        if (!downloadsDir.exists() || !downloadsDir.isDirectory()) {
            return;
        }
        
        List<String> entries = Arrays.stream(downloadsDir.listFiles())
            .filter(File::isDirectory)
            .map(File::getName)
            .sorted()
            .collect(Collectors.toList());
            
        table.setItems(FXCollections.observableArrayList(entries));
    }
}
