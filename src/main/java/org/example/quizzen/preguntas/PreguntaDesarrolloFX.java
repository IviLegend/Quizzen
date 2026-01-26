package org.example.quizzen.preguntas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PreguntaDesarrolloFX {


    public void mostrar(Stage stage, PreguntaDesarrollo preguntaDesarrollo){

        // Titulo:
        VBox cajaTitulo = cajaDelTitulo(preguntaDesarrollo);
        // caja principal de las opciones
        VBox contenedorOpciones = cajaPrincipalDeLaRespuesta();


        // AÑADIR BOTONES

        // AÑADIR FONDO



        VBox root = new VBox(cajaTitulo, contenedorOpciones);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene); stage.show();






    }

    /**
     * Este metodo recibe el título de un objeto {@link PreguntaDesarrollo} y se extrae el titulo de la pregunta
     * dandole un formate en el centrarse. Al mismo tiempo el {@link Label} que contiene el titulo será inducido a un objeto
     * de tipo caja {@link VBox} para centrarlo en la ventana y dandole un formato concreto, tamaño y color.
     * @param preguntaDesarrollo es un objeto del backen dond está compuesto las preguntas y las diversas opciones.
     * @return devuelve un objeto de tipo {@link VBox} para despues iniciar su visualización en la ventana.
     */

    private static VBox cajaDelTitulo(PreguntaDesarrollo preguntaDesarrollo) {
        Label titulo = new Label(preguntaDesarrollo.getEnunciado());
        titulo.setFont(Font.font("Arial", FontWeight.BOLD,40)); // Amigable, legible, con grosor
        titulo.setWrapText(true);
        titulo.setMaxWidth(Double.MAX_VALUE); // Para que pueda centrarse
        titulo.setAlignment(Pos.CENTER); // Centra dentro del Label
        titulo.setTextAlignment(TextAlignment.CENTER); //centrar el texto

        //caja para el titulo
        VBox cajaTitulo = new VBox(titulo);
        cajaTitulo.setAlignment(Pos.CENTER);
        cajaTitulo.setPadding(new Insets(20));

        //cajaTitulo.setMaxWidth(Double.MAX_VALUE); //la caja ocupa todo el ancho
        VBox.setMargin(cajaTitulo, new Insets(200,70,50,70)); // darle paddin

        //estilo de la caja
        cajaTitulo.setStyle(
                "-fx-background-color: #ccffcc;"+ // verde claro
                        "-fx-border-color: #2e8b57;" + // borde verde oscuro
                        "-fx-border-width: 5px;"+
                        "-fx-border-radius: 50px;"+
                        "-fx-background-radius: 50px;"
        );
        return cajaTitulo;
    }

    private static VBox cajaPrincipalDeLaRespuesta() {
        VBox contenedorPrincipal = new VBox(20);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        VBox.setMargin(contenedorPrincipal, new Insets(10, 300, 10, 300)); //Separarlo de los margenes

        //Estilo del cuadro naraja
        contenedorPrincipal.setStyle(
                "-fx-background-color: #ffcc99;" +
                        "-fx-border-color: #cc6600;" +
                        "-fx-border-width: 4px;" +
                        "-fx-border-radius: 10px;" +
                        "-fx-background-radius: 10px;"
        );
        contenedorPrincipal.setPadding(new Insets(20));

        //generar subcaja
        VBox subcaja = new VBox(20);
        VBox.setMargin(subcaja,new Insets(20,60,20,60));
        subcaja.setAlignment(Pos.CENTER);

        // Permite que la subcaja crezca dentro del contenedor principal.
        //VBox.setVgrow(subcaja, Priority.ALWAYS);

        // en vez de usar la clase Scanner, se usa la clase TextField ya que Scanner solo sirve para la consola.
        TextArea campoTexto = new TextArea();
        campoTexto.setPromptText("Escribe escruba su respuesta");
        campoTexto.setWrapText(true); // para que el texto salte de línea
        campoTexto.setPrefHeight(15);
        campoTexto.setStyle("-fx-font-size: 30px;"); // tamaño del texto


        // permitir que el campo crezca dentro de la subcaja
        //VBox.setVgrow(campoTexto,Priority.ALWAYS);
        campoTexto.setPrefHeight(10); //ajuste manual

        //Añadir el campo a la subcaja
        subcaja.getChildren().add(campoTexto);

        //añadimos la subcaja a la principal
        contenedorPrincipal.getChildren().add(subcaja);

        // colocacion de las cajas.
        contenedorPrincipal.setMaxWidth(Double.MAX_VALUE);
        subcaja.setMaxWidth(Double.MAX_VALUE);
        contenedorPrincipal.setPrefHeight(300);
        subcaja.setPrefHeight(100);

        return contenedorPrincipal;
    }


    private void cajaDeLaRespuestaEscrita(){

    }



}
