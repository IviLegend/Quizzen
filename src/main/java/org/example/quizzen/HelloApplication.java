package org.example.quizzen;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import org.example.quizzen.preguntas.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HelloApplication extends Application {

    private BorderPane root; // contenedor principal
    private ArrayList<Pregunta> listaPreguntas;
    private int indiceActual = 0;

    @Override
    public void start(Stage stage) throws IOException {

        //Hacer un boton para ir a hacer las preguntas.

        //



        stage.setFullScreenExitHint(""); // esto de abajo evita que aparezca el tipico mensaje de : si quieres salir de la pantalla completa pulsa esc
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH); //esto evita que si se pulsa el boton esc se deja de estar en pantalla completa
        stage.setFullScreen(true);

        listaPreguntas= muchasPreguntas();

        root = new BorderPane();
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        mostrarPregunta(stage);

    }

    private void mostrarPregunta(Stage stage){

        Pregunta pregunta = listaPreguntas.get(indiceActual);

        //stage.setFullScreen(true); // fuerza pantalla completa SIEMPRE

        if (pregunta instanceof PreguntaOpcionMultiple preguntaOpcionMultiple){
            Node vista = new PreguntaOpcionMultipleFX().mostrar(stage, preguntaOpcionMultiple, this);
            root.setCenter(vista); //esto hace que solo cambi el contenido, no la escena.
            //new PreguntaOpcionMultipleFX().mostrar(stage,preguntaOpcionMultiple,this);
        } else if (pregunta instanceof PreguntaDesarrollo preguntaDesarrollo){
            Node vista = new PreguntaDesarrolloFX().mostrar(stage,preguntaDesarrollo, this);
            root.setCenter(vista);
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
        PreguntaDesarrollo p4 = new PreguntaDesarrollo();
        p4.setEnunciado("Desarrolla la etapa de Carlos II");


        PreguntaDesarrollo p5 = new PreguntaDesarrollo();
        p5.setEnunciado("Para que sirve el polimorfismo");

        muchasPregunta.add(p1);
        muchasPregunta.add(p2);
        muchasPregunta.add(p3);
        muchasPregunta.add(p4);
        muchasPregunta.add(p5);

        return muchasPregunta;

    }
}
