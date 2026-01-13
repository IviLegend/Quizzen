package org.example.quizzen.preguntas;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
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
import javafx.util.Duration;

public class PreguntaOpcionMultipleFX {

    public void mostrar(Stage stage, PreguntaOpcionMultiple preguntaOpcionMultiple){

        //VBox root = new VBox(20, titulo, contenidoOpcion, contenedorBotones);

        // Titulo:
        Label titulo = new Label(preguntaOpcionMultiple.getEnunciado());
        titulo.setFont(Font.font("Arial", FontWeight.BOLD,24)); // Amigable, legible, con grosor
        titulo.setWrapText(true);
        titulo.setMaxWidth(Double.MAX_VALUE); // Para que pueda centrarse
        titulo.setAlignment(Pos.CENTER); // Centra dentro del Label
        titulo.setTextAlignment(TextAlignment.CENTER); //centrar el texto

        //caja para el titulo
        VBox cajaTitulo = new VBox(titulo);
        cajaTitulo.setAlignment(Pos.CENTER);
        cajaTitulo.setPadding(new Insets(20));
        cajaTitulo.setMaxWidth(Double.MAX_VALUE); //la caja ocupa todo el ancho

        //estilo de la caja
        cajaTitulo.setStyle(
                "-fx-background-color: #ccffcc;"+ // verde claro
                "-fx-border-color: #2e8b57;" + // borde verde oscuro
                "-fx-border-width: 3px;"+
                "-fx-border-radius: 10px;"+
                "-fx-background-radius: 10px;"
        );


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

        //tamaño fijo para evitar reorganicaciones de la colocación:
        btonAtras.setPrefWidth(120);
        btonSiguiente.setPrefWidth(120);

        //Estilo css colores morado con degradado

        String estiloBoton =
                "-fx-background-color: linear-gradient(to bottom right, #d8b4fe, #7c3aed);" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;";

        btonAtras.setStyle(estiloBoton);
        btonSiguiente.setStyle(estiloBoton);

        //efecto de agrandar cuando el cursor esta encima
//        btonAtras.setOnMouseEntered(e -> {
//            btonAtras.setScaleX(1.2);
//            btonAtras.setScaleY(1.2);
//        });
//
//        btonAtras.setOnMouseExited(e -> {
//            btonAtras.setScaleX(1.0);
//            btonAtras.setScaleY(1.0);
//        });
//
//        btonSiguiente.setOnMouseEntered(e ->{
//            btonSiguiente.setScaleX(1.2);
//            btonSiguiente.setScaleY(1.2);
//        });
//
//        btonSiguiente.setOnMouseExited(e ->{
//            btonSiguiente.setScaleX(1.0);
//            btonSiguiente.setScaleY(1.0);
//        });
        //animación de tipo gelatina

        ScaleTransition transicionBotonAtrasGrande = new ScaleTransition(Duration.millis(200),btonAtras);
        transicionBotonAtrasGrande.setToX(1.25);
        transicionBotonAtrasGrande.setToY(1.25);
        transicionBotonAtrasGrande.setInterpolator(Interpolator.EASE_OUT);

        ScaleTransition transicionBotonAtrasNormal = new ScaleTransition(Duration.millis(200), btonAtras);
        transicionBotonAtrasNormal.setToX(1.0);
        transicionBotonAtrasNormal.setToY(1.0);
        transicionBotonAtrasNormal.setInterpolator(Interpolator.EASE_BOTH);

        btonAtras.setOnMouseEntered(e ->
                transicionBotonAtrasGrande.playFromStart());
        btonAtras.setOnMouseExited(e ->
                transicionBotonAtrasNormal.playFromStart());

        ScaleTransition transicionBotonSiguienteGrande = new ScaleTransition(Duration.millis(200), btonSiguiente);
        transicionBotonSiguienteGrande.setToX(1.25);
        transicionBotonSiguienteGrande.setToY(1.25);
        transicionBotonSiguienteGrande.setInterpolator(Interpolator.EASE_OUT);

        ScaleTransition transicionBotonSiguienteNormal = new ScaleTransition(Duration.millis(200), btonSiguiente);
        transicionBotonSiguienteNormal.setToX(1.0);
        transicionBotonSiguienteNormal.setToY(1.0);
        transicionBotonSiguienteNormal.setInterpolator(Interpolator.EASE_BOTH);

        btonSiguiente.setOnMouseEntered(e -> transicionBotonSiguienteGrande.playFromStart());
        btonSiguiente.setOnMouseExited(e -> transicionBotonSiguienteNormal.playFromStart());



        btonAtras.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        btonSiguiente.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        HBox contenedorBotones = new HBox(20,btonAtras,btonSiguiente);
        contenedorBotones.setPadding(new Insets(20,0,0,0));
        contenedorBotones.setAlignment(Pos.CENTER); // centramos los botones

        //Layout principal
        VBox root = new VBox(20, /*titulo*/cajaTitulo, contenidoOpcion, contenedorBotones);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER); //centrar el bloque
        //root.getChildren().add(cajaTitulo); //añadir la propia caja

        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("PreguntaOpcionMultiple");
        stage.show();

    }
}
