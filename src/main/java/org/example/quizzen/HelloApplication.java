package org.example.quizzen;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.quizzen.partidas.GestorPartida;
import org.example.quizzen.preguntas.*;
import org.example.quizzen.test.Test;

import java.io.IOException;
import java.util.ArrayList;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class HelloApplication extends Application {

    private BorderPane root;
    private ArrayList<Pregunta> listaPreguntas;
    private int indiceActual = 0;
    private GestorPartida gestorPartida;

    @Override
    public void start(Stage stage) throws IOException {

        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setFullScreen(true);

        ArrayList<Test> listaTest = GestorTest.importarTests();
        gestorPartida = new GestorPartida(listaTest);
        listaPreguntas = gestorPartida.getTest().getPreguntas();

        root = new BorderPane();
        root.setStyle("-fx-background-color: #0f172a;");

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        Button btonPreguntas = new Button("Hacer Test");
        btonPreguntas.setPrefWidth(500);
        btonPreguntas.setPrefHeight(500);

        String estiloBoton =
                "-fx-background-color: linear-gradient(to bottom right, #d8b4fe, #7c3aed);" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 30 10 30;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;" +
                        "-fx-font-size: 40px;";

        btonPreguntas.setStyle(estiloBoton);
        cambiarTamanyoBoton(btonPreguntas, 1.25, 1.0);

        btonPreguntas.setOnMouseClicked(e -> mostrarPregunta(stage));
        root.setCenter(btonPreguntas);
    }

    private static void cambiarTamanyoBoton(Button unBotonCualquiera, double tamanyoGrande, double tamanyoNormal) {
        ScaleTransition hacerBotonGrande = new ScaleTransition(Duration.millis(200), unBotonCualquiera);
        hacerBotonGrande.setToX(tamanyoGrande);
        hacerBotonGrande.setToY(tamanyoGrande);

        ScaleTransition botonTamanyoNormal = new ScaleTransition(Duration.millis(200), unBotonCualquiera);
        botonTamanyoNormal.setToX(tamanyoNormal);
        botonTamanyoNormal.setToY(tamanyoNormal);

        unBotonCualquiera.setOnMouseEntered(e -> hacerBotonGrande.playFromStart());
        unBotonCualquiera.setOnMouseExited(e -> botonTamanyoNormal.playFromStart());
    }

    private void mostrarPregunta(Stage stage) {
        Pregunta pregunta = listaPreguntas.get(indiceActual);

        if (pregunta instanceof PreguntaOpcionMultiple p) {
            Node vista = new PreguntaOpcionMultipleFX().mostrar(stage, p, this);
            root.setCenter(vista);
        } else if (pregunta instanceof PreguntaDesarrollo p) {
            Node vista = new PreguntaDesarrolloFX().mostrar(stage, p, this);
            root.setCenter(vista);
        }
    }

    public void siguientePregunta(Stage stage) {
        if (indiceActual < listaPreguntas.size() - 1) {
            indiceActual++;
            mostrarPregunta(stage);
        } else {
            mostrarPantallaFinal(stage);
        }
    }

    public void preguntaAnterior(Stage stage) {
        if (indiceActual > 0) {
            indiceActual--;
            mostrarPregunta(stage);
        }
    }

    public boolean comprobarRespuestaDesarrollo(PreguntaDesarrollo p) {
        String respuesta = p.getRespuestaUsuario();
        return gestorPartida.comprobarRespuestaActual(respuesta);
    }

    public boolean comprobarRespuestaOpcionMultiple(PreguntaOpcionMultiple p) {
        Integer seleccion = p.getRespuestaSeleccionada();
        if (seleccion == null) return false;

        String respuestaTexto = p.getOpciones().get(seleccion).getSentencia();
        return gestorPartida.comprobarRespuestaActual(respuestaTexto);
    }

    private void mostrarPantallaFinal(Stage stage) {
        VBox pantallaFinal = new VBox(20);
        pantallaFinal.setAlignment(Pos.CENTER);
        pantallaFinal.setPadding(new Insets(40));

        int fallos = gestorPartida.getResultados().totalRespuestasFalladas();

        Label titulo = new Label("¡TEST FINALIZADO!");
        titulo.setStyle("-fx-font-size: 40px; -fx-text-fill: white;");

        Label textoFallos = new Label("Has tenido " + fallos + " fallos.");
        textoFallos.setStyle("-fx-font-size: 20px; -fx-text-fill: white;");

        Button btnVerFallos = new Button("Ver preguntas falladas");
        btnVerFallos.setOnMouseClicked(e -> mostrarPreguntasFalladas(stage));

        pantallaFinal.getChildren().addAll(titulo, textoFallos, btnVerFallos);
        root.setCenter(pantallaFinal);
    }
    private void mostrarPreguntasFalladas(Stage stage) {
        VBox vbox = new VBox(20);
        vbox.setPadding(new Insets(20));

        Label titulo = new Label("Preguntas falladas:");
        titulo.setStyle("-fx-font-size: 30px; -fx-text-fill: white;");
        vbox.getChildren().add(titulo);

        gestorPartida.getResultados().getRespuestas()
                .forEach((pregunta, listaRespuestas) -> {

                    // Caja para cada pregunta
                    VBox cajaPregunta = new VBox(10);
                    cajaPregunta.setPadding(new Insets(15));
                    cajaPregunta.setStyle("-fx-background-color: #1e293b; -fx-border-color: #7c3aed; -fx-border-width: 2px; -fx-border-radius: 10px; -fx-background-radius: 10px;");

                    // Tipo de pregunta
                    String tipo = (pregunta instanceof PreguntaDesarrollo) ? "Desarrollo" : "Opción múltiple";
                    Label lblTipo = new Label("Tipo: " + tipo);
                    lblTipo.setStyle("-fx-text-fill: #d8b4fe; -fx-font-weight: bold; -fx-font-size: 16px;");

                    // Enunciado
                    Label lblPregunta = new Label("❌ " + pregunta.getEnunciado());
                    lblPregunta.setWrapText(true);
                    lblPregunta.setStyle("-fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 18px;");

                    cajaPregunta.getChildren().addAll(lblTipo, lblPregunta);

                    // Respuestas del usuario
                    int count = 1;
                    for (String r : listaRespuestas) {
                        Label lblResp = new Label("Intento " + count + ": " + r);
                        lblResp.setWrapText(true);
                        lblResp.setStyle("-fx-text-fill: #f87171; -fx-font-size: 16px;");
                        cajaPregunta.getChildren().add(lblResp);
                        count++;
                    }

                    // Respuesta correcta
                    String respuestaCorrecta = "";
                    if (pregunta instanceof PreguntaDesarrollo pDes) {
                        respuestaCorrecta = String.join(", ", pDes.getRespuestasCorrectas());
                    } else if (pregunta instanceof PreguntaOpcionMultiple pOpc) {
                        respuestaCorrecta = pOpc.getRespuestaCorrecta();
                    }

                    Label lblCorrecta = new Label("Respuesta correcta: " + respuestaCorrecta);
                    lblCorrecta.setWrapText(true);
                    lblCorrecta.setStyle("-fx-text-fill: #22c55e; -fx-font-weight: bold; -fx-font-size: 16px;");
                    cajaPregunta.getChildren().add(lblCorrecta);

                    vbox.getChildren().add(cajaPregunta);
                });

        ScrollPane scroll = new ScrollPane(vbox);
        scroll.setFitToWidth(true);
        scroll.setStyle("-fx-background: #0f172a; -fx-border-color: transparent;");
        root.setCenter(scroll);
    }
}
