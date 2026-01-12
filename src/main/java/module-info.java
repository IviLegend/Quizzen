module org.example.quizzen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires javafx.graphics;

    opens org.example.quizzen to javafx.fxml;
    exports org.example.quizzen;
    exports org.example.quizzen.preguntas;
    opens org.example.quizzen.preguntas to javafx.fxml;
    exports org.example.quizzen.test;
    opens org.example.quizzen.test to javafx.fxml;
}