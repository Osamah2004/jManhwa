package org.example.jmanhwa;

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Priority;
import java.io.File;

public class ProgressScene {
    private Stage stage;
    private ProgressBar chapterProgress;
    private ProgressBar totalProgress;
    private TextArea logArea;
    private Label statusLabel;
    private int totalChapters;
    private int currentChapter;
    private int totalImages;
    private int currentImage;
    private String manhwaTitle;

    public ProgressScene(String title, int chapters) {
        this.manhwaTitle = title;
        this.totalChapters = chapters;
        this.currentChapter = 0;
        
        stage = new Stage();
        VBox root = new VBox(10);
        root.setPadding(new Insets(15));

        // Status label
        statusLabel = new Label("Preparing download...");
        
        // Chapter progress
        Label chapterLabel = new Label("Chapter Progress:");
        chapterProgress = new ProgressBar(0);
        chapterProgress.setMaxWidth(Double.MAX_VALUE);
        
        // Total progress
        Label totalLabel = new Label("Total Progress:");
        totalProgress = new ProgressBar(0);
        totalProgress.setMaxWidth(Double.MAX_VALUE);
        
        // Log area
        logArea = new TextArea();
        logArea.setEditable(false);
        logArea.setWrapText(true);
        logArea.setPrefRowCount(10);
        VBox.setVgrow(logArea, Priority.ALWAYS);
        
        root.getChildren().addAll(
            statusLabel,
            chapterLabel,
            chapterProgress,
            totalLabel,
            totalProgress,
            logArea
        );

        Scene scene = new Scene(root, 500, 400);
        stage.setTitle("Download Progress - " + title);
        stage.setScene(scene);
    }

    public void show() {
        stage.show();
    }

    public void updateChapterProgress(int current, int total) {
        Platform.runLater(() -> {
            this.totalImages = total;
            this.currentImage = current;
            double progress = (double) current / total;
            chapterProgress.setProgress(progress);
            statusLabel.setText(String.format("Downloading chapter %d/%d - Image %d/%d", 
                currentChapter + 1, totalChapters, current, total));
        });
    }

    public void updateTotalProgress(int chapter) {
        Platform.runLater(() -> {
            this.currentChapter = chapter;
            double progress = (double) (chapter + 1) / totalChapters;
            totalProgress.setProgress(progress);
        });
    }

    public void logDownload(String message) {
        Platform.runLater(() -> {
            logArea.appendText(message + "\n");
            logArea.setScrollTop(Double.MAX_VALUE);
        });
    }

    public void showCompletionScene() {
        Platform.runLater(() -> {
            stage.close();
            
            Stage completionStage = new Stage();
            VBox completionRoot = new VBox(10);
            completionRoot.setPadding(new Insets(15));
            
            Label completionLabel = new Label("Download Complete!");
            Button openFolderButton = new Button("View Downloaded Chapters");

            openFolderButton.setOnAction(e -> {
                new viewDownloadedChapters(Main.getAppHostServices());
            });
            
            completionRoot.getChildren().addAll(completionLabel, openFolderButton);
            Scene completionScene = new Scene(completionRoot, 300, 100);
            completionStage.setTitle("Download Complete");
            completionStage.setScene(completionScene);
            completionStage.show();
        });
    }
} 