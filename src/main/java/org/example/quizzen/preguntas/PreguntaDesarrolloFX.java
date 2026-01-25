package org.example.quizzen.preguntas;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

public class PreguntaDesarrolloFX {


    public void mostrar(Stage stage, PreguntaDesarrollo preguntaDesarrollo){

        // Titulo:
        VBox cajaTitulo = cajaDelTitulo(preguntaDesarrollo);




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
        titulo.setFont(Font.font("Arial", FontWeight.BOLD,24)); // Amigable, legible, con grosor
        titulo.setWrapText(true);
        titulo.setMaxWidth(Double.MAX_VALUE); // Para que pueda centrarse
        titulo.setAlignment(Pos.CENTER); // Centra dentro del Label
        titulo.setTextAlignment(TextAlignment.CENTER); //centrar el texto

        //caja para el titulo
        VBox cajaTitulo = new VBox(titulo);
        cajaTitulo.setAlignment(Pos.CENTER);
        cajaTitulo.setPadding(new Insets(20));

        //cajaTitulo.setMaxWidth(Double.MAX_VALUE); //la caja ocupa todo el ancho
        VBox.setMargin(cajaTitulo, new Insets(10));

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

    private void cajaDeLaRespuestaEscrita(){

    }



}
