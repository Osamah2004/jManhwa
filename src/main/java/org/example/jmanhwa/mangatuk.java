package org.example.jmanhwa;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class mangatuk extends jSoupAbstract {
    private ArrayList<manhwaModel> temp = new ArrayList<>();

    private Document d;

    public mangatuk(String url){
        url = url.replace(" ","+");
        url = "https://mangatuk.com/?s="+url+"&post_type=wp-manga";
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
        Element e1 = d.getElementById("loop-content");
        Elements e2 = e1.children();

        for (int i = 0; i < e2.size(); i++) {
            temp.add(new manhwaModel(
                e2.get(i).getElementsByTag("img").attr("alt"),
                e2.get(i).getElementsByClass("font-meta chapter").text(),
                e2.get(i).getElementsByTag("img").attr("src"),
                e2.get(i).getElementsByTag("a").attr("href")
            ));
        }

        return temp;
    }

    @Override
    public ArrayList<String> chaptersLinks() {
        return null;
    }
}
