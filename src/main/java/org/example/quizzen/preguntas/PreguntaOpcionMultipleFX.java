package org.example.quizzen.preguntas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PreguntaOpcionMultipleFX {

    public void mostrar(Stage stage, PreguntaOpcionMultiple preguntaOpcionMultiple){

        // Titulo:
        Label titulo = new Label(preguntaOpcionMultiple.getEnunciado());
        titulo.setFont(Font.font("Arial", FontWeight.BOLD,24)); // Amigable, legible, con grosor
        titulo.setWrapText(true);
        titulo.setMaxWidth(Double.MAX_VALUE); // Para que pueda centrarse
        titulo.setAlignment(Pos.CENTER); // Centra dentro del Label
        titulo.setTextAlignment(TextAlignment.CENTER); //centrar el texto

        // Opciones:
        ToggleGroup grupoOpciones = new ToggleGroup(); // solo se seleciona una opción
        VBox contenidoOpcion = new VBox(10); //contenedir en vertical
        contenidoOpcion.setAlignment(Pos.CENTER); // centramos las opciones

        char letra = 'a';
        for (Opcion opcion: preguntaOpcionMultiple.getOpciones()){

            RadioButton radioButton = new RadioButton(letra+") "+opcion.getSentencia());

            // con radioButton solo puede haber una selección correcta. sin el setToggleGroup se pueden selecionar varias opciones.
            radioButton.setToggleGroup(grupoOpciones);
            radioButton.setFont(Font.font("Arial",16));
            contenidoOpcion.getChildren().add(radioButton); //los resultados aparecen uno debajo del otro.
            letra++; // a) despues b) despues c) ...
        }

        // botones
        Button btonAtras = new Button("Atras");
        Button btonSiguiente = new Button("Siguiente");

        btonAtras.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        btonSiguiente.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        HBox contenedorBotones = new HBox(20,btonAtras,btonSiguiente);
        contenedorBotones.setPadding(new Insets(20,0,0,0));
        contenedorBotones.setAlignment(Pos.CENTER); // centramos los botones

        //Layout principal
        VBox root = new VBox(20, titulo, contenidoOpcion, contenedorBotones);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER); //centrar el bloque

        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("PreguntaOpcionMultiple");
        stage.show();

    }
}
