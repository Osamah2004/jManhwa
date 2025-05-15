package org.example.jmanhwa;
	
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {

    VBox v = new VBox();
    private static HostServices hostServices;
    private static jSoupAbstract j;

    @Override
    public void start(Stage stage) {
        hostServices = getHostServices();
        TextField t = new TextField();
        t.setPromptText("Insert name");
        Button b = new Button("Search");
        Button b2 = new Button("View Downloaded");
        b2.setOnAction(actionEvent -> {
            new viewDownloadedChapters(hostServices);
        });

        TableView<manhwaModel> table = new TableView<>();
        
        // Make table automatically resize columns and rows
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        
        // Make table fill available width
        table.setMaxWidth(Double.MAX_VALUE);

        TableColumn<manhwaModel, String> titles = new TableColumn<>("titles");
        titles.setCellValueFactory(new PropertyValueFactory<>("titles"));
        titles.setMinWidth(200); // Set minimum width for titles

        TableColumn<manhwaModel, String> lastChapter = new TableColumn<>("lastChapter");
        lastChapter.setCellValueFactory(new PropertyValueFactory<>("lastChapter"));
        lastChapter.setMinWidth(100); // Set minimum width for last chapter

        TableColumn<manhwaModel, Button> url = new TableColumn<>("url");
        url.setCellValueFactory(new PropertyValueFactory<>("url"));
        url.setMinWidth(100); // Set minimum width for URL

        table.getColumns().addAll(titles,lastChapter,url);

        b.setOnAction(actionEvent -> {
            j = new azora(t.getText());
            //j.searchResults();
            table.setItems(FXCollections.observableArrayList(j.searchResults()));
        });

        v.getChildren().add(new HBox(t,b,b2));
        v.getChildren().add(table);

        Scene scene = new Scene(v);
        stage.setTitle("jManhwa");
        stage.setWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }

    public static jSoupAbstract getJ() {
        return j;
    }

    public static HostServices getAppHostServices() {
        return hostServices;
    }
}