package org.example.jmanhwa;
	
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.Collectors;

public class otherScene {
    private Button list = new Button("Select all chapters");
    private Button download = new Button("Download Selected");
    private HashMap<String, Boolean> selectedChapters = new HashMap<>();

    public otherScene(String title, ArrayList<ArrayList<String>> chapters) {
        Stage stage = new Stage();
        TableView<ArrayList<String>> table = new TableView<>();
        
        // Initialize HashMap with all chapters unselected
        for (ArrayList<String> chapter : chapters) {
            selectedChapters.put(chapter.get(1), false);
        }

        // Make table automatically resize columns and rows
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // Make table fill available width 
        table.setMaxWidth(Double.MAX_VALUE);

        TableColumn<ArrayList<String>, String> titleColumn = new TableColumn<>("Chapter");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        titleColumn.setMinWidth(100);

        final TableColumn<ArrayList<String>, CheckBox> checkBoxes = getArrayListCheckBoxTableColumn();

        TableColumn<ArrayList<String>, Button> urlColumn = new TableColumn<>("Download chapter");
        urlColumn.setCellValueFactory(data -> {
            Button btn = new Button("Download");
            btn.setOnAction(e -> {
                String url = data.getValue().get(1);
                jSoupAbstract j = Main.getJ();
                ArrayList<String> images = j.imageLinks(url);
                
                // Create progress scene for single chapter
                ProgressScene progressScene = new ProgressScene(title, 1);
                ImageDownloader.setProgressScene(progressScene);
                ImageDownloader.setTotalChapters(1);
                progressScene.show();
                
                // Start download in a new thread
                new Thread(() -> {
                    ImageDownloader.downloadImages(title, data.getValue().get(0), images);
                }).start();
            });
            return new SimpleObjectProperty<>(btn);
        });
        urlColumn.setMinWidth(100);

        table.getColumns().addAll(titleColumn, checkBoxes, urlColumn);

        // Set the items directly since we already have an ArrayList
        ObservableList<ArrayList<String>> data = FXCollections.observableArrayList(chapters);
        table.setItems(data);

        // Handle "Select all" button
        list.setOnAction(e -> {
            boolean selectAll = list.getText().equals("Select all chapters");
            if (selectAll){
                if (chapters.size() > 200){
                    new displayLabel("That's a ridiculous amount of chapters, I highly advice you to select fewer, proceed at your own risk.", "warning");
                }
                else if (chapters.size() > 100){
                    new displayLabel("You have selected more than 100 chapters, check your disk space before continuing", "warning");
                }
                else if (chapters.size() > 50){
                    new displayLabel("There's a lot of chapters, this may consume large amounts of storage.", "warning");
                }
            }
            for (ArrayList<String> chapter : chapters) {
                selectedChapters.put(chapter.get(1), selectAll);
            }
            list.setText(selectAll ? "Deselect all chapters" : "Select all chapters");
            table.refresh(); // Refresh the table to update checkboxes
        });

        // Handle download button
        download.setOnAction(e -> {
            // Get selected chapters
            ArrayList<ArrayList<String>> selectedChaptersList = chapters.stream()
                .filter(chapter -> selectedChapters.get(chapter.get(1)))
                .collect(Collectors.toCollection(ArrayList::new));
            
            if (selectedChaptersList.isEmpty()) {
                return;
            }
            
            // Create progress scene for batch download
            ProgressScene progressScene = new ProgressScene(title, selectedChaptersList.size());
            ImageDownloader.setProgressScene(progressScene);
            ImageDownloader.setTotalChapters(selectedChaptersList.size());
            progressScene.show();
            
            // Start downloads in a new thread
            new Thread(() -> {
                jSoupAbstract j = Main.getJ();
                for (ArrayList<String> chapter : selectedChaptersList) {
                    String url = chapter.get(1);
                    ArrayList<String> images = j.imageLinks(url);
                    ImageDownloader.downloadImages(title, chapter.get(0), images);
                }
            }).start();
        });

        VBox v = new VBox(10); // Add spacing between elements
        v.getChildren().addAll(list, download, table);
        Scene scene = new Scene(v);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.show();
    }

    private TableColumn<ArrayList<String>, CheckBox> getArrayListCheckBoxTableColumn() {
        TableColumn<ArrayList<String>, CheckBox> checkBoxes = new TableColumn<>("Add to download list");
        checkBoxes.setCellValueFactory(data -> {
            CheckBox c = new CheckBox();
            String url = data.getValue().get(1);
            
            // Set initial state
            c.setSelected(selectedChapters.get(url));
            
            // Handle checkbox changes
            c.setOnAction(e -> {
                selectedChapters.put(url, c.isSelected());
                System.out.println("URL: " + url + " selected: " + c.isSelected());
            });
            
            return new SimpleObjectProperty<>(c);
        });
        checkBoxes.setMinWidth(100);
        return checkBoxes;
    }
}
