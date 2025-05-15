package org.example.jmanhwa;
	
import javafx.scene.control.Button;

public class manhwaModel {

    private String titles;
    private String lastChapter;
    private String url;
    private jSoupAbstract j;

    public manhwaModel(String title, String lastChapter, String url, jSoupAbstract jsoup) {
        this.titles = title;
        this.lastChapter = lastChapter;
        this.url = url;
        this.j = jsoup;
    }

    public String getTitles() {
        return titles;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public Button getUrl() {
        Button url = new Button("View Chapters");
        url.setOnAction(event -> {
            new otherScene(titles, j.chaptersLinks(this.url));
        });
        return url;
    }

}