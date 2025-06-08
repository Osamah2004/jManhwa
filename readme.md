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
*The main search interface where you can enter manhwa titles to search*

![Main Interface](https://github.com/user-attachments/assets/11132bf4-9a17-4a80-85ce-a54ea0a38b20)

### 2. Search Results
*View search results with titles and available chapters, in this image I will use Solo Leveling as an example*

![Search Results](https://github.com/user-attachments/assets/e1121c8f-cc6d-4e00-be36-3133df801246)

### 3. Chapter Selection
*Select individual chapters or use the "Select All" option.*
*there's 2 empty chapters in solo leveling's chapters page so that explains why the first chapter rows are empty.*

![Chapter Selection](https://github.com/user-attachments/assets/573e1213-81d6-4197-8560-d7045a6750a7)

### 4. Download Progress
*Track download progress for individual chapters and overall progress*

![Download Progress](https://github.com/user-attachments/assets/aff71b03-c199-4d90-bf4a-05d74cc51743)
![View Downloaded](https://github.com/user-attachments/assets/a8d642d3-4942-4947-a3ce-427c19ebb68e)

### 5. Reading Interface
*View downloaded chapters in your default web browser*

![Reading Interface](https://github.com/user-attachments/assets/4c771583-8e2b-4e5c-8b2c-97a2941f33ac)

### 6. Have fun

![image](https://github.com/user-attachments/assets/b48d282b-eef5-4c56-8c03-b2224f6a269b)

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
