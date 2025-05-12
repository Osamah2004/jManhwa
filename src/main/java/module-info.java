module org.example.jmanhwa {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.jsoup;


    opens org.example.jmanhwa to javafx.fxml;
    exports org.example.jmanhwa;
}