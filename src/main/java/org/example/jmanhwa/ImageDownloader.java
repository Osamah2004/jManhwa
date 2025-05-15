package org.example.jmanhwa;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;

public class ImageDownloader {
    private static ProgressScene progressScene = null;
    private static int currentChapterIndex = 0;
    private static int totalChapters = 0;
    
    public static void setProgressScene(ProgressScene scene) {
        progressScene = scene;
    }
    
    public static void setTotalChapters(int total) {
        totalChapters = total;
    }
    
    public static void downloadImages(String title, String chapterTitle, ArrayList<String> imageUrls) {
        // Create base directory if it doesn't exist
        String baseDir = "downloads";
        String manhwaDir = baseDir + File.separator + sanitizeFileName(title);
        String chapterDir = manhwaDir + File.separator + sanitizeFileName(chapterTitle);
        
        try {
            Files.createDirectories(Paths.get(chapterDir));
            
            if (progressScene != null) {
                progressScene.updateTotalProgress(currentChapterIndex);
            }
            
            for (int i = 0; i < imageUrls.size(); i++) {
                String imageUrl = imageUrls.get(i);
                String extension = getFileExtension(imageUrl);
                String fileName = String.format("%03d%s", i + 1, extension);
                Path destination = Paths.get(chapterDir, fileName);
                
                downloadImage(imageUrl, destination);
                
                if (progressScene != null) {
                    progressScene.updateChapterProgress(i + 1, imageUrls.size());
                    progressScene.logDownload("Downloaded: " + chapterTitle + " - " + fileName);
                } else {
                    System.out.println("Downloaded: " + fileName);
                }
            }
            
            if (progressScene != null) {
                progressScene.logDownload("Chapter completed: " + chapterTitle);
            } else {
                System.out.println("All images downloaded successfully to: " + chapterDir);
            }
            
            currentChapterIndex++;
            
            // If this was the last chapter, show completion scene
            if (progressScene != null && currentChapterIndex >= totalChapters) {
                progressScene.showCompletionScene();
                // Reset for next batch
                currentChapterIndex = 0;
                totalChapters = 0;
            }
            
        } catch (IOException e) {
            e.printStackTrace();
            if (progressScene != null) {
                progressScene.logDownload("Error: " + e.getMessage());
            } else {
                System.err.println("Error downloading images: " + e.getMessage());
            }
        }
    }
    
    private static void downloadImage(String imageUrl, Path destination) throws IOException {
        try (InputStream in = new URL(imageUrl).openStream()) {
            Files.copy(in, destination, StandardCopyOption.REPLACE_EXISTING);
        }
    }
    
    private static String getFileExtension(String url) {
        String extension = ".jpg"; // default extension
        int lastDot = url.lastIndexOf('.');
        if (lastDot > 0) {
            String ext = url.substring(lastDot).toLowerCase();
            if (ext.matches("\\.(jpg|jpeg|png|gif|webp)")) {
                extension = ext;
            }
        }
        return extension;
    }
    
    private static String sanitizeFileName(String fileName) {
        // Remove or replace invalid characters
        return fileName.replaceAll("[\\\\/:*?\"<>|]", "_").trim();
    }
} 