package org.example.jmanhwa;

import javafx.scene.control.Button;

public class manhwaModel {

    private String titles;
    private String lastChapter;
    private String url;

    public manhwaModel(String title, String lastChapter, String url) {
        this.titles = title;
        this.lastChapter = lastChapter;
        this.url = url;
    }

    public String getTitles() {
        return titles;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public Button getUrl() {
        Button url = new Button("Print url");
        url.setOnAction(actionEvent -> System.out.println(this.url));
        return url;
    }

}
