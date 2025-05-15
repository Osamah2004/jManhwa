package org.example;

import java.util.ArrayList;
import java.util.Arrays;

import org.example.jmanhwa.displayLabel;
import org.example.jmanhwa.jSoupAbstract;
import org.example.jmanhwa.manhwaModel;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class mangalek extends jSoupAbstract{
    private ArrayList<manhwaModel> temp = new ArrayList<>();
    private Document d;

    @Override
    public String getSiteName() {
        // TODO Auto-generated method stub
        return "mangalek";
    }

    public mangalek(String url){
        url = url.replace(" ","+");
        url = "https://lekmanga.net/?s="+url+"&post_type=wp-manga";
        d = newDocument(url);
    }

    @Override
    public ArrayList<manhwaModel> searchResults() {
        temp.clear();
        Element E = null;

        try {
            E = d.getElementsByClass("row c-tabs-item__content").first();
        } catch (Exception e) {
            new displayLabel("No results found", "jManhwa");
            return temp;
        }
        /*
        title;
        lastChapter
        coverURL;
        url;*/
        Elements e2 = E.children();
        for (Element element : e2) {
            temp.add(new manhwaModel(
                element.getElementsByTag("a").first().attr("title"),
                element.getElementsByTag("a").last().text(),
                element.getElementsByTag("a").first().attr("href"),
                this));
        }
        return temp;
    }
    @Override
    public ArrayList<ArrayList<String>> chaptersLinks(String url) {
        d = newDocument(url);
        ArrayList<ArrayList<String>> chapters = new ArrayList<>();
        Element e = d.getElementsByClass("main").first();
        Elements e2 = e.getElementsByTag("a");
        for (Element element : e2) {
            chapters.add(new ArrayList<>(Arrays.asList(element.text(), element.attr("href"))));
        }
        return chapters;
    }

    @Override
    public ArrayList<String> imageLinks(String url) {
        d = newDocument(url);
        ArrayList<String> images = new ArrayList<>();
        Elements i = d.getElementsByClass("wp-manga-chapter-img ");
        for (Element element : i) {
            images.add(element.attr("src"));
        }
        return images;
    }
    

}
