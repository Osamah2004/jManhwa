package org.example.jmanhwa;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class otherScene {
    public otherScene(String title, ArrayList<ArrayList<String>> chapters) {
        Stage stage = new Stage();
        TableView<ArrayList<String>> table = new TableView<>();
        
        // Make table automatically resize columns and rows
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // Make table fill available width 
        table.setMaxWidth(Double.MAX_VALUE);

        TableColumn<ArrayList<String>, String> titleColumn = new TableColumn<>("Chapter");
        titleColumn.setCellValueFactory(data -> new SimpleStringProperty(data.getValue().get(0)));
        titleColumn.setMinWidth(200);

        TableColumn<ArrayList<String>, Button> urlColumn = new TableColumn<>("URL");
        urlColumn.setCellValueFactory(data -> {
            Button btn = new Button("Read");
            btn.setOnAction(e -> {
                String url = data.getValue().get(1);
                System.out.println(url);
            });
            return new SimpleObjectProperty<>(btn);
        });
        urlColumn.setMinWidth(100);

        table.getColumns().addAll(titleColumn, urlColumn);

        // Set the items directly since we already have an ArrayList
        ObservableList<ArrayList<String>> data = FXCollections.observableArrayList(chapters);
        table.setItems(data);
        
        VBox v = new VBox(table);
        Scene scene = new Scene(v);
        stage.setTitle(title);
        stage.setScene(scene);
        stage.setWidth(600);
        stage.show();
    }
}
