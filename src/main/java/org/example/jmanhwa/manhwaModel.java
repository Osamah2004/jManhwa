package org.example.jmanhwa;

public class manhwaModel {

    private String titles;
    private String lastChapter;
    private String coverUrl;
    private String url;

    public manhwaModel(String title, String lastChapter, String coverUrl, String url) {
        this.titles = title;
        this.lastChapter = lastChapter;
        this.coverUrl = coverUrl;
        this.url = url;
    }

    public String getTitles() {
        return titles;
    }

    public String getLastChapter() {
        return lastChapter;
    }

    public String getCoverUrl() {
        return coverUrl;
    }

    public String getUrl() {
        return url;
    }

}
