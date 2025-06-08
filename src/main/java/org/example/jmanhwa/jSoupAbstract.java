package org.example.jmanhwa;
	
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public abstract class jSoupAbstract {

    // Abstract methods to be implemented by subclasses
    public abstract ArrayList<manhwaModel> searchResults();
    public abstract ArrayList<ArrayList<String>> chaptersLinks(String url);
    public abstract ArrayList<String> imageLinks(String url);
    public abstract String getSiteName();
    public abstract void setUrl(String url);

    public Document newDocument(String url){
        try {
            return Jsoup.connect(url).timeout(10000).get();
        } catch (IOException e) {
            new displayLabel("Connection timed out, please try again.", "jManhwa");
            return null;
        }
    }

}
