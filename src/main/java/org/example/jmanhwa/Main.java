package org.example.jmanhwa;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.control.Button;
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

public class Main extends Application {

    VBox v = new VBox();
    jSoupAbstract j;


    @Override
    public void start(Stage stage) {
        TextField t = new TextField();
        t.setPromptText("Insert name");
        Button b = new Button("Search");

        TableView<manhwaModel> table = new TableView<>();

        TableColumn<manhwaModel, String> titles = new TableColumn<>("titles");
        titles.setCellValueFactory(new PropertyValueFactory<>("titles"));

        TableColumn<manhwaModel, String> lastChapter = new TableColumn<>("lastChapter");
        lastChapter.setCellValueFactory(new PropertyValueFactory<>("lastChapter"));

        TableColumn<manhwaModel, Button> url = new TableColumn<>("url");
        url.setCellValueFactory(new PropertyValueFactory<>("url"));

        table.getColumns().addAll(titles,lastChapter,url);

        b.setOnAction(actionEvent -> {
            j = new azora(t.getText());
            //j.searchResults();
            table.setItems(FXCollections.observableArrayList(j.searchResults()));
            System.out.println();
        });

        v.getChildren().add(new HBox(t,b));
        v.getChildren().add(table);

        Scene scene = new Scene(v);
        stage.setTitle("Hello!");
        stage.setWidth(600);
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}