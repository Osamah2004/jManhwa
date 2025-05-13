package org.example.jmanhwa;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;

public class azora extends jSoupAbstract{

    private ArrayList<manhwaModel> temp = new ArrayList<>();

    private Document d;

    public azora(String url){
        url = url.replace(" ","+");
        url = "https://azoramoon.com/?s="+url+"&post_type=wp-manga";
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

        Element e = d.getElementsByClass("c-tabs-item").getFirst();
        Elements e2 = e.children();
        for (Element element : e2) {
            temp.add(new manhwaModel(
                    element.getElementsByTag("a").getFirst().attr("title"),
                    element.getElementsByTag("a").getLast().text(),
                    element.getElementsByTag("a").getFirst().attr("href")
            ));
        }
        return temp;
    }

    @Override
    public ArrayList<String> chaptersLinks() {
        return null;
    }
}
