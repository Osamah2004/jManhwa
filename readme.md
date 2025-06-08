# jManhwa

A Java-based desktop application for downloading and reading manhwa (Korean comics) from various sources. Built with JavaFX, this application provides a user-friendly interface for searching, downloading, and reading manhwa chapters.

## Features

- 🔍 Search manhwa by title
- 📥 Download individual chapters or multiple chapters at once
- 📚 View downloaded chapters in a web browser
- 📊 Progress tracking for downloads
- 🎨 Clean and intuitive user interface
- 🔄 Automatic chapter organization
- 📱 Responsive reading experience
- 💻 Cross-platform support (Windows, macOS, Linux)

## Current Source Support

Currently, this application only supports downloading from [Azora Moon](https://azoramoon.com/). Support for additional sources may be added in future releases.

## Requirements

- Java 17 or higher
- JavaFX
- Internet connection for downloading

### OS-Specific Requirements
- **Windows+macOS**: No additional requirements
- **Linux**: 
  - OpenJDK 17 or higher
  - OpenJFX 17 or higher
  - For Ubuntu/Debian: `sudo apt-get install openjfx`

## Installation

### Option 1: Using the JAR file (For Users)
1. Go to the [Releases](https://github.com/Osamah2004/jManhwa/releases/tag/v1.0.0) page
2. Download the latest `jManhwa.jar`
3. Run the application:

**Windows:**
```bash
java -jar jManhwa.jar
```

**macOS/Linux:**
```bash
chmod +x jManhwa.jar
java -jar jManhwa.jar
```

### Option 2: Building from source (For Developers)
1. Clone the repository:
```bash
git clone https://github.com/Osamah2004/jManhwa.git
```

2. Navigate to the project directory:
```bash
cd jManhwa
```

3. Choose one of the following methods to run the application:

#### Method A: Run directly from source (Recommended for Development)
```bash
# Using Maven
mvn javafx:run

# Or using your IDE
# Open the project and run org.example.jmanhwa.Main
```

#### Method B: Build and run the JAR
```bash
# Build the project
mvn clean package

# Run the generated JAR
java -jar target/jManhwa-1.0-SNAPSHOT-jar-with-dependencies.jar
```

## Development

### Project Structure
```
jManhwa/
├── src/
│   └── main/
│       └── java/
│           └── org/
│               └── example/
│                   └── jmanhwa/
│                       ├── Main.java           # Main application entry point
│                       ├── ImageDownloader.java
│                       ├── ProgressScene.java
│                       └── ...
├── downloads/        # Downloaded chapters are stored here
│   └── [manhwa-titles]/
│       └── [chapters]/
└── pom.xml
```

### Running in Development Mode
For development, it's recommended to run the application directly from the source code:
1. Open the project in your preferred IDE (IntelliJ IDEA, Eclipse, etc.)
2. Locate `Main.java` in the project explorer
3. Run the `Main` class directly

This approach provides:
- Faster development cycle
- Better debugging capabilities
- Hot reloading (in supported IDEs)
- No need to rebuild the JAR for testing changes

## User Guide

### 1. Main Interface
![Main Interface](docs/images/main-interface.png)
*The main search interface where you can enter manhwa titles to search*

### 2. Search Results
![Search Results](docs/images/search-results.png)
*View search results with titles and available chapters*

### 3. Chapter Selection
![Chapter Selection](docs/images/chapter-selection.png)
*Select individual chapters or use the "Select All" option*

### 4. Download Progress
![Download Progress](docs/images/download-progress.png)
*Track download progress for individual chapters and overall progress*

### 5. Reading Interface
![Reading Interface](docs/images/reading-interface.png)
*View downloaded chapters in your default web browser*

## Usage

1. **Searching for Manhwa**
   - Enter the title in the search box
   - Click "Search" to find matching results
   - Results will display available manhwa titles

2. **Downloading Chapters**
   - Click "View Chapters" on any manhwa
   - Select individual chapters or use "Select all chapters"
   - Click "Download Selected" to start downloading
   - Monitor progress in the download window

3. **Reading Downloaded Chapters**
   - Click "View Downloaded" in the main interface
   - Select a manhwa from the list
   - Choose a chapter from the dropdown
   - Click "View Chapter" to open in your browser

## Keyboard Shortcuts

When reading chapters in the browser:
- Left Arrow: Previous chapter
- Right Arrow: Next chapter

## Contributing

Contributions are welcome! Please feel free to submit a Pull Request or suggest features.

## License

This project is licensed under the MIT License - see the LICENSE file for details.

## Acknowledgments

- Built with JavaFX
- Uses JSoup for web scraping
- Inspired by various manga/manhwa readers