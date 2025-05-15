package org.example.jmanhwa;
	
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class manhwatuk extends jSoupAbstract {
    private ArrayList<manhwaModel> temp = new ArrayList<>();

    private Document d;

    @Override
    public String getSiteName() {
        return "manhwatuk";
    }

    public manhwatuk(String url){
        url = url.replace(" ","+");
        url = "https://manhwatuk.com/?s="+url+"&post_type=wp-manhwa";
        d = newDocument(url);
    }
/*
title;
lastChapter
coverURL;
url;*/
    @Override
    public ArrayList<manhwaModel> searchResults() {
        temp.clear();
        Element e1 = null;
        Elements e2 = null;

        try{
            e1 = d.getElementById("loop-content");
            e2 = e1.children();
        }catch(Exception e){
            new displayLabel("No results found", "jManhwa");
            return temp;
        }

        for (Element element : e2) {
            temp.add(new manhwaModel(
                    element.getElementsByTag("img").attr("alt"),
                    element.getElementsByClass("font-meta chapter").text(),
                    element.getElementsByTag("a").attr("href"),
                    this
            ));
        }

        return temp;
    }

    @Override
    public ArrayList<ArrayList<String>> chaptersLinks(String url) {
        ArrayList<ArrayList<String>> chapters = new ArrayList<>();
        // Implementation needed
        return chapters;
    }
    @Override
    public ArrayList<String> imageLinks(String url) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'imageLinks'");
    }
}
