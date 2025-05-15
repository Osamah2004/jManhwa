package org.example.jmanhwa;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class generateHtmlForChapter {
    private static final String HTML_TEMPLATE = """
            <!DOCTYPE html>
            <html lang="en">
            <head>
                <meta charset="UTF-8">
                <meta name="viewport" content="width=device-width, initial-scale=1.0">
                <title>%s - Chapter %s</title>
                <style>
                    body {
                        margin: 0;
                        padding: 0;
                        background-color: #1a1a1a;
                        color: #ffffff;
                        font-family: Arial, sans-serif;
                    }
                    .nav-button {
                        display: block;
                        width: 100%%;
                        padding: 10px;
                        background-color: #2d2d2d;
                        color: #ffffff;
                        text-align: center;
                        text-decoration: none;
                        font-size: 1.2em;
                        margin-bottom: 20px;
                    }
                    .nav-button:hover {
                        background-color: #404040;
                    }
                    .content {
                        padding: 20px;
                        text-align: center;
                    }
                    .manhwa-image {
                        max-width: 100%%;
                        height: auto;
                        margin: 10px auto;
                        display: block;
                    }
                    .disabled {
                        opacity: 0.5;
                        pointer-events: none;
                    }
                    .next-chapter {
                        position: absolute;
                        right: 0;
                        bottom: 0;
                        background-color: rgba(45, 45, 45, 0.8);
                        border-radius: 5px 0 0 0;
                    }
                    .next-chapter a {
                        color: #ffffff;
                        text-decoration: none;
                    }
                    .image-container {
                        position: relative;
                        display: inline-block;
                    }
                </style>
            </head>
            <body>
                <a href="%s" class="nav-button %s">← Previous Chapter</a>
                <div class="content">
                    %s
                </div>
                <script>
                    document.addEventListener('keydown', function(e) {
                        if (e.key === 'ArrowLeft') {
                            const prev = document.querySelector('.nav-button');
                            if (!prev.classList.contains('disabled')) {
                                window.location.href = prev.href;
                            }
                        } else if (e.key === 'ArrowRight') {
                            const next = document.querySelector('.next-chapter a');
                            if (next) {
                                window.location.href = next.href;
                            }
                        }
                    });
                </script>
            </body>
            </html>
            """;

    public generateHtmlForChapter(String manhwaTitle, String chapterName, List<String> allChapters) {
        File chapterDir = new File("downloads" + File.separator + manhwaTitle + File.separator + chapterName);
        if (!chapterDir.exists() || !chapterDir.isDirectory()) {
            return;
        }

        File[] imageFiles = chapterDir.listFiles((dir, name) -> 
            name.toLowerCase().endsWith(".jpg") || 
            name.toLowerCase().endsWith(".png") || 
            name.toLowerCase().endsWith(".gif") ||
            name.toLowerCase().endsWith(".webp"));
            
        if (imageFiles == null || imageFiles.length == 0) {
            return;
        }
        
        Arrays.sort(imageFiles);
        
        int currentIndex = allChapters.indexOf(chapterName);
        
        // Generate image HTML
        StringBuilder imagesHtml = new StringBuilder();
        for (int i = 0; i < imageFiles.length; i++) {
            File imageFile = imageFiles[i];
            String imageFileName = imageFile.getName();
            
            // For the last image, wrap it in a container with the next chapter link
            if (i == imageFiles.length - 1 && currentIndex < allChapters.size() - 1) {
                imagesHtml.append("<div class='image-container'>\n");
                imagesHtml.append(String.format("<img class='manhwa-image' src='%s' alt='Page'>\n", imageFileName));
                imagesHtml.append(String.format("<div class='next-chapter'><a href='../%s/chapter_0.html'>Next Chapter →</a></div>\n", 
                    allChapters.get(currentIndex + 1)));
                imagesHtml.append("</div>\n");
            } else {
                imagesHtml.append(String.format("<img class='manhwa-image' src='%s' alt='Page'>\n", imageFileName));
            }
        }
        
        // Generate navigation links
        String prevLink = currentIndex > 0 ? "../" + allChapters.get(currentIndex - 1) + "/chapter_0.html" : "#";
        String prevClass = currentIndex > 0 ? "" : "disabled";
        
        // Generate HTML content
        String htmlContent = String.format(HTML_TEMPLATE,
            manhwaTitle, chapterName,
            prevLink, prevClass,
            imagesHtml.toString());
        
        // Save HTML file
        try {
            File htmlFile = new File(chapterDir, "chapter_0.html");
            try (FileWriter writer = new FileWriter(htmlFile)) {
                writer.write(htmlContent);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<String> getAvailableChapters(String manhwaTitle) {
        File manhwaDir = new File("downloads" + File.separator + manhwaTitle);
        if (!manhwaDir.exists() || !manhwaDir.isDirectory()) {
            return Arrays.asList();
        }
        
        return Arrays.stream(manhwaDir.listFiles())
            .filter(File::isDirectory)
            .map(File::getName)
            .sorted((a, b) -> {
                try {
                    return Integer.compare(Integer.parseInt(a), Integer.parseInt(b));
                } catch (NumberFormatException e) {
                    // Fallback to string comparison if parsing fails
                    return a.compareTo(b);
                }
            })
            .collect(Collectors.toList());
    }
} 