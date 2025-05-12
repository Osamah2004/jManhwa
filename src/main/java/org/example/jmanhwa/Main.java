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

        TableColumn<manhwaModel, String> coverUrl = new TableColumn<>("coverUrl");
        coverUrl.setCellValueFactory(new PropertyValueFactory<>("coverUrl"));

        TableColumn<manhwaModel, String> url = new TableColumn<>("url");
        url.setCellValueFactory(new PropertyValueFactory<>("url"));

        table.getColumns().addAll(coverUrl,titles,lastChapter,url);

        b.setOnAction(actionEvent -> {
            j = new mangatuk(t.getText());
            table.setItems(FXCollections.observableArrayList(j.searchResults()));
        });


        v.getChildren().add(new HBox(t,b));
        ImageView i2 = new ImageView();
        Image i = new Image("https://image.webp",true);

        i2.setImage(i);

        v.getChildren().add(i2);

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