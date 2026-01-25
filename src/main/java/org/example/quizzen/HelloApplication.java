package org.example.quizzen;

import javafx.application.Application;
import javafx.stage.Stage;
import org.example.quizzen.preguntas.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private ArrayList<Pregunta> listaPreguntas;
    private int indiceActual = 0;

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

        listaPreguntas= muchasPreguntas();


        mostrarPregunta(stage);

//        for (Pregunta pregunta:listaPreguntas) {
//            if (pregunta instanceof PreguntaOpcionMultiple){
//
//                PreguntaOpcionMultiple preguntaOpcionMultiple = (PreguntaOpcionMultiple) pregunta;
//                new PreguntaOpcionMultipleFX().mostrar(stage,preguntaOpcionMultiple, this);
//
//
//            }
//        }

//        PreguntaOpcionMultiple preguntaOpcionMultiplePrueba = new PreguntaOpcionMultiple();
//        preguntaOpcionMultiplePrueba.setEnunciado("¿Cuanto es 2 + 2?");
//        preguntaOpcionMultiplePrueba.setOpciones( new ArrayList<>(List.of(
//                new Opcion("4"),
//                new Opcion("5"),
//                new Opcion("3"),
//                new Opcion("4,3")
//        )));
//
//       new PreguntaOpcionMultipleFX().mostrar(stage,preguntaOpcionMultiplePrueba);
//
//        PreguntaDesarrollo preguntaDesarrollo = new PreguntaDesarrollo();
//        preguntaDesarrollo.setEnunciado("Defineme el modelo del absolutismo de Fernando IV y por que no tubo tanta repercusión con su hija Isabel II");
//
//        new PreguntaDesarrolloFX().mostrar(stage,preguntaDesarrollo);


    }

    private void mostrarPregunta(Stage stage){

        Pregunta pregunta = listaPreguntas.get(indiceActual);

        stage.setFullScreen(true); // 🔥 fuerza pantalla completa SIEMPRE

        if (pregunta instanceof PreguntaOpcionMultiple preguntaOpcionMultiple){
            new PreguntaOpcionMultipleFX().mostrar(stage,preguntaOpcionMultiple,this);
        }


    }

    public void siguientePregunta (Stage stage){

        if (indiceActual < listaPreguntas.size() - 1) {
            indiceActual++;
            mostrarPregunta(stage);
        }

    }

    public void preguntaAnterior(Stage stage){
        if (indiceActual > 0) {
            indiceActual--;
            mostrarPregunta(stage);
        }

    }
    private static ArrayList<Pregunta> muchasPreguntas(){

        ArrayList<Pregunta> muchasPregunta = new ArrayList<>();

        PreguntaOpcionMultiple p1 = new PreguntaOpcionMultiple();
        p1.setEnunciado("¿Cuanto es 2 + 2?");
        p1.setOpciones( new ArrayList<>(List.of(
                new Opcion("4"),
                new Opcion("5"),
                new Opcion("3"),
                new Opcion("4,3")
        )));

        PreguntaOpcionMultiple p2 = new PreguntaOpcionMultiple();
        p2.setEnunciado("¿Cuando se descurbio la gravedad");
        p2.setOpciones( new ArrayList<>(List.of(
                new Opcion("Isaac Newton"),
                new Opcion("Albert Einstein"),
                new Opcion("Nikola Tesla"),
                new Opcion("Galileo Galilei")
        )));

        PreguntaOpcionMultiple p3 = new PreguntaOpcionMultiple();
        p3.setEnunciado("Cuando se descubrio america");
        p3.setOpciones( new ArrayList<>(List.of(
                new Opcion("1942"),
                new Opcion("1492"),
                new Opcion("1429"),
                new Opcion("2149")
        )));

        muchasPregunta.add(p1);
        muchasPregunta.add(p2);
        muchasPregunta.add(p3);

        return muchasPregunta;

    }
}
