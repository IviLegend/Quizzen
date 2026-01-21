package org.example.quizzen;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.example.quizzen.preguntas.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
//        Scene scene = new Scene(fxmlLoader.load(), 320, 240);
//        stage.setTitle("Hello!");
//        stage.setScene(scene);
//        stage.show();
//
//        System.out.println("Ivan");
//        System.out.println("Alvaro");

        PreguntaOpcionMultiple preguntaOpcionMultiple = new PreguntaOpcionMultiple();
        preguntaOpcionMultiple.setEnunciado("¿Cuanto es 2 + 2?");
        preguntaOpcionMultiple.setOpciones( new ArrayList<>(List.of(
                new Opcion("4"),
                new Opcion("5"),
                new Opcion("3"),
                new Opcion("4,3")
        )));

       new PreguntaOpcionMultipleFX().mostrar(stage,preguntaOpcionMultiple);

        PreguntaDesarrollo preguntaDesarrollo = new PreguntaDesarrollo();
        preguntaDesarrollo.setEnunciado("Defineme el modelo del absolutismo de Fernando IV y por que no tubo tanta repercusión con su hija Isabel II");

        new PreguntaDesarrolloFX().mostrar(stage,preguntaDesarrollo);


    }
}
