package org.example.quizzen.preguntas;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.quizzen.HelloApplication;

import java.util.ArrayList;
import java.util.Collections;

public class PreguntaOpcionMultipleFX {

    public Node mostrar(Stage stage, PreguntaOpcionMultiple preguntaOpcionMultiple, HelloApplication app){

        // Caja del título
        VBox cajaTitulo = cajaDelTitulo(preguntaOpcionMultiple);

        // Caja principal de las opciones
        VBox contenedorOpciones = cajaPrincipalDeLasOpciones();

        // Columna de opciones
        VBox columnaOpciones = new VBox(15);
        columnaOpciones.setAlignment(Pos.CENTER_LEFT);

        // Generar subcajas de las opciones (ya barajadas)
        generarSubcajas(preguntaOpcionMultiple, contenedorOpciones, columnaOpciones);

        // Recordar opción marcada
        recordarOpcionMarcada(preguntaOpcionMultiple, columnaOpciones);

        // Botones A,B,C,D
        HBox filaBotonesABCD = new HBox(20);
        filaBotonesABCD.setAlignment(Pos.CENTER);
        botonesDeInteraccionConLasPreguntas(filaBotonesABCD, contenedorOpciones, columnaOpciones, preguntaOpcionMultiple);

        // Botones siguiente y anterior
        Button btonAtras = new Button("Atras");
        Button btonSiguiente = new Button("Siguiente");

        btonAtras.setPrefSize(220,70);
        btonSiguiente.setPrefSize(220,70);

        String estiloBoton =
                "-fx-background-color: linear-gradient(to bottom right, #d8b4fe, #7c3aed);" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 30 10 30;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;";

        btonAtras.setStyle(estiloBoton);
        btonSiguiente.setStyle(estiloBoton);

        cambiarTamanyoBoton(btonAtras,1.25,1.0);
        cambiarTamanyoBoton(btonSiguiente,1.25,1.0);

        btonSiguiente.setOnMouseClicked(e -> app.siguientePregunta(stage));
        btonAtras.setOnMouseClicked(e -> app.preguntaAnterior(stage));

        btonAtras.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        btonSiguiente.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        HBox contenedorBotones = new HBox(40,btonAtras,btonSiguiente);
        contenedorBotones.setPadding(new Insets(20,0,0,0));
        contenedorBotones.setAlignment(Pos.CENTER);

        // Layout principal
        VBox root = new VBox(20, cajaTitulo, contenedorOpciones, filaBotonesABCD, contenedorBotones);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-background-color: #0f172a;");

        return root;
    }

    // Recordar opción marcada
    private static void recordarOpcionMarcada(PreguntaOpcionMultiple preguntaOpcionMultiple, VBox columnaOpciones) {
        Integer seleccion = preguntaOpcionMultiple.getRespuestaSeleccionada();
        if (seleccion != null){
            HBox fila = (HBox) columnaOpciones.getChildren().get(seleccion);
            for (Node sub : fila.getChildren()) {
                if (sub instanceof StackPane) {
                    sub.setStyle("-fx-background-color: #ccffcc; -fx-border-color: #66cc66; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
                }
            }
        }
    }

    private void botonesDeInteraccionConLasPreguntas(HBox filaBotonesABCD, VBox contenedorOpciones, VBox columnaOpciones, PreguntaOpcionMultiple pregunta) {
        DropShadow glow = new DropShadow();
        glow.setColor(Color.WHITE);
        glow.setRadius(40);
        glow.setSpread(0.3);

        for (char caracter = 'A'; caracter <= 'D'; caracter++){
            Button boton = new Button(String.valueOf(caracter));
            boton.setPrefSize(400,200);
            boton.setFont(Font.font("Arial", FontWeight.BOLD, 70));
            boton.setStyle("-fx-background-radius: 50px; -fx-border-radius: 50px; -fx-border-width: 4px;");

            switch (caracter){
                case 'A' -> boton.setStyle(boton.getStyle() + "-fx-background-color: #fd4d4d; -fx-border-color: #b30000;");
                case 'B' -> boton.setStyle(boton.getStyle() + "-fx-background-color: #4d79ff; -fx-border-color: #0033cc;");
                case 'C' -> boton.setStyle(boton.getStyle() + "-fx-background-color: #ffeb3b; -fx-border-color: #e6c300;");
                case 'D' -> boton.setStyle(boton.getStyle() + "-fx-background-color: #4dff4d; -fx-border-color: #00b300;");
            }

            boton.setOnMouseEntered(e -> boton.setEffect(glow));
            boton.setOnMouseExited(e -> boton.setEffect(null));
            cambiarTamanyoBoton(boton, 1.15, 1.0);

            StackPane contenedor = new StackPane(boton);
            contenedor.setPrefSize(boton.getPrefWidth(), boton.getPrefHeight());
            filaBotonesABCD.getChildren().add(contenedor);

            aplicarEfectoOndas(boton, columnaOpciones, caracter, pregunta);
        }

        contenedorOpciones.getChildren().add(columnaOpciones);
    }

    private static void generarSubcajas(PreguntaOpcionMultiple preguntaOpcionMultiple, VBox contenedorOpciones,  VBox columnaOpciones) {
        // Barajar las opciones
        ArrayList<Opcion> opcionesBarajadas = new ArrayList<>(preguntaOpcionMultiple.getOpciones());
        Collections.shuffle(opcionesBarajadas);

        char letra = 'A';
        for (Opcion opcion: opcionesBarajadas){
            HBox filaOpcion = new HBox(10);
            filaOpcion.setAlignment(Pos.CENTER_LEFT);

            Label labelLetra = new Label(letra + ")");
            labelLetra.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            labelLetra.setAlignment(Pos.CENTER);
            labelLetra.setMinWidth(40);

            StackPane cajaLetra = new StackPane(labelLetra);
            cajaLetra.setPadding(new Insets(8));
            cajaLetra.setStyle("-fx-background-color: #ffffff; -fx-border-color: #999999; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");

            Label textoOpcion = new Label(opcion.getSentencia());
            textoOpcion.setFont(Font.font("Arial", 16));
            textoOpcion.setWrapText(true);
            textoOpcion.setAlignment(Pos.CENTER_LEFT);
            StackPane.setAlignment(textoOpcion, Pos.CENTER_LEFT);

            StackPane cajaTexto = new StackPane(textoOpcion);
            cajaTexto.setPadding(new Insets(8));
            cajaTexto.setStyle("-fx-background-color: #ffffff; -fx-border-color: #999999; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
            cajaTexto.setMaxWidth(Double.MAX_VALUE);
            HBox.setHgrow(cajaTexto, Priority.ALWAYS);

            filaOpcion.getChildren().addAll(cajaLetra, cajaTexto);
            contenedorOpciones.getChildren().add(filaOpcion);
            columnaOpciones.getChildren().add(filaOpcion);

            filaOpcion.setStyle("-fx-background-color: #ffffff; -fx-border-color: #999999; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");

            letra++;
        }
    }

    private static VBox cajaPrincipalDeLasOpciones() {
        VBox contenedorOpciones = new VBox(20);
        contenedorOpciones.setAlignment(Pos.CENTER);
        VBox.setMargin(contenedorOpciones, new Insets(5, 300, 5, 300));
        contenedorOpciones.setStyle("-fx-background-color: #ffcc99; -fx-border-color: #cc6600; -fx-border-width: 4px; -fx-border-radius: 10px; -fx-background-radius: 10px;");
        contenedorOpciones.setPadding(new Insets(20));
        return contenedorOpciones;
    }

    private static VBox cajaDelTitulo(PreguntaOpcionMultiple preguntaOpcionMultiple) {
        Label titulo = new Label(preguntaOpcionMultiple.getEnunciado());
        titulo.setFont(Font.font("Arial", FontWeight.BOLD,24));
        titulo.setWrapText(true);
        titulo.setMaxWidth(Double.MAX_VALUE);
        titulo.setAlignment(Pos.CENTER);
        titulo.setTextAlignment(TextAlignment.CENTER);

        VBox cajaTitulo = new VBox(titulo);
        cajaTitulo.setAlignment(Pos.CENTER);
        cajaTitulo.setPadding(new Insets(20));
        VBox.setMargin(cajaTitulo, new Insets(10));
        cajaTitulo.setStyle("-fx-background-color: #ccffcc; -fx-border-color: #2e8b57; -fx-border-width: 5px; -fx-border-radius: 50px; -fx-background-radius: 50px;");
        return cajaTitulo;
    }

    private static void cambiarTamanyoBoton(Button unBotonCualquiera, double tamanyoGrande, double tamanyoNormal) {
        ScaleTransition hacerBotonGrande = new ScaleTransition(Duration.millis(200), unBotonCualquiera);
        hacerBotonGrande.setToX(tamanyoGrande);
        hacerBotonGrande.setToY(tamanyoGrande);
        hacerBotonGrande.setInterpolator(Interpolator.EASE_OUT);

        ScaleTransition botonTamanyoNormal = new ScaleTransition(Duration.millis(200), unBotonCualquiera);
        botonTamanyoNormal.setToX(tamanyoNormal);
        botonTamanyoNormal.setToY(tamanyoNormal);
        botonTamanyoNormal.setInterpolator(Interpolator.EASE_BOTH);

        unBotonCualquiera.setOnMouseEntered(e -> hacerBotonGrande.playFromStart());
        unBotonCualquiera.setOnMouseExited(e -> botonTamanyoNormal.playFromStart());
    }

    private void aplicarEfectoOndas(Button boton, VBox columnaOpciones, char letra, PreguntaOpcionMultiple pregunta){
        boton.setOnMouseClicked(e -> {

            Circle onda = new Circle(0, Color.rgb(255,255,255,0.4));
            onda.setCenterX(e.getX());
            onda.setCenterY(e.getY());
            StackPane parent = (StackPane) boton.getParent();
            parent.getChildren().add(onda);
            Timeline animacion = new Timeline(
                    new KeyFrame(Duration.ZERO, new KeyValue(onda.radiusProperty(), 0), new KeyValue(onda.opacityProperty(), 1)),
                    new KeyFrame(Duration.millis(400), new KeyValue(onda.radiusProperty(), 100), new KeyValue(onda.opacityProperty(), 0))
            );
            animacion.setOnFinished(ev -> parent.getChildren().remove(onda));
            animacion.play();

            int index = letra - 'A';
            pregunta.setRespuestaSeleccionada(index);
            boolean correcta = pregunta.getOpciones().get(index).isEsCorrecta();

            for (int i = 0; i < columnaOpciones.getChildren().size(); i++) {
                HBox fila = (HBox) columnaOpciones.getChildren().get(i);
                Opcion op = pregunta.getOpciones().get(i);

                for (Node sub : fila.getChildren()) {
                    if (sub instanceof StackPane) {
                        if (correcta) {
                            if (op.isEsCorrecta()) {
                                sub.setStyle("-fx-background-color: #22c55e; -fx-border-color: #15803d; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
                            } else {
                                sub.setStyle("-fx-background-color: #ffffff; -fx-border-color: #999999; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
                            }
                        } else {
                            if (i == index) {
                                sub.setStyle("-fx-background-color: #ff4d4d; -fx-border-color: #b30000; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
                            } else if (op.isEsCorrecta()) {
                                sub.setStyle("-fx-background-color: #22c55e; -fx-border-color: #15803d; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
                            } else {
                                sub.setStyle("-fx-background-color: #ffffff; -fx-border-color: #999999; -fx-border-width: 2px; -fx-border-radius: 8px; -fx-background-radius: 8px;");
                            }
                        }
                    }
                }
            }

            if (!correcta) {
                pregunta.getOpciones().get(index).setEsCorrecta(false);
            }
        });
    }
}
