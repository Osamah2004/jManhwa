package org.example.jmanhwa;
	
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class mangatuk extends jSoupAbstract {
    private ArrayList<manhwaModel> temp = new ArrayList<>();
    private Document d;

    @Override
    public String getSiteName() {
        return "mangatuk";
    }

    public mangatuk(){
        super();
    }

    @Override
    public void setUrl(String url){
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
        System.out.println(url);
        d = newDocument(url);
        ArrayList<ArrayList<String>> chapters = new ArrayList<>();
        Element e = d.getElementsByClass("listing-chapters_wrap").first();
        Elements e2 = e.getElementsByTag("a");
        
        for (int i = e2.size()-1; i >= 0; i--) {
            ArrayList<String> row = new ArrayList<>();
            row.add(e2.get(i).text());  // Chapter title
            row.add(e2.get(i).attr("href"));  // Chapter URL
            chapters.add(row);
        }
        return chapters;
    }

    @Override
    public ArrayList<String> imageLinks(String url) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'imageLinks'");
    }
}
