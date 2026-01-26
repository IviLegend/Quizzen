package org.example.quizzen.preguntas;

import javafx.animation.Interpolator;
import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.example.quizzen.HelloApplication;

public class PreguntaDesarrolloFX {


    public Node mostrar(Stage stage, PreguntaDesarrollo preguntaDesarrollo, HelloApplication app){

        // Titulo:
        VBox cajaTitulo = cajaDelTitulo(preguntaDesarrollo);
        // caja principal de las opciones
        VBox contenedorOpciones = cajaPrincipalDeLaRespuesta();


        // AÑADIR BOTONES

        Button btonAtras = new Button("Atras");
        Button btonSiguiente = new Button("Siguiente");

        //tamaño fijo para evitar reorganicaciones de la colocación:
        btonAtras.setPrefWidth(220);
        btonAtras.setPrefHeight(70);

        btonSiguiente.setPrefWidth(220);
        btonSiguiente.setPrefHeight(70);

        //Estilo css colores morado con degradado

        String estiloBoton =
                "-fx-background-color: linear-gradient(to bottom right, #d8b4fe, #7c3aed);" +
                        "-fx-background-radius: 10;" +
                        "-fx-padding: 10 30 10 30;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-weight: bold;";

        btonAtras.setStyle(estiloBoton);
        btonSiguiente.setStyle(estiloBoton);

        //cambiar tamaño botones
        cambiarTamanyoBoton(btonAtras,1.25,1.0);

        cambiarTamanyoBoton(btonSiguiente,1.25,1.0);

        //Para pasar a la siguiente pregunta
        btonSiguiente.setOnMouseClicked( e -> app.siguientePregunta(stage));
        btonAtras.setOnMouseClicked(e -> app.preguntaAnterior(stage));

        btonAtras.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        btonSiguiente.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        HBox contenedorBotones = new HBox(40,btonAtras,btonSiguiente);
        contenedorBotones.setPadding(new Insets(20,0,0,0));
        contenedorBotones.setAlignment(Pos.CENTER); // centramos los botones

        // AÑADIR FONDO

        VBox root = new VBox(20, cajaTitulo, contenedorOpciones, contenedorBotones);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER); //centrar el bloque
        //root.getChildren().add(cajaTitulo); //añadir la propia caja


        root.setFillWidth(true);
        root.setMaxWidth(Double.MAX_VALUE);
        root.setMaxHeight(Double.MAX_VALUE);
        VBox.setVgrow(root, Priority.ALWAYS);

        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER);


        //VBox root = new VBox(cajaTitulo, contenedorOpciones, contenedorOpciones);
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);

        stage.setFullScreen(true);
        stage.setFullScreenExitHint("");
        stage.setFullScreenExitKeyCombination(KeyCombination.NO_MATCH);
        stage.setMaximized(true);

        stage.show();

        root.setStyle("-fx-background-color: #0f172a;"); //cambiar el fondo de la ventana a un grisaceo azulado


        return root;
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
        VBox.setMargin(cajaTitulo, new Insets(0,70,40,70)); // darle paddin

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
        VBox contenedorPrincipal = new VBox(60);
        contenedorPrincipal.setAlignment(Pos.CENTER);
        VBox.setMargin(contenedorPrincipal, new Insets(5, 300, 10, 300)); //Separarlo de los margenes

        //Estilo del cuadro naraja
        contenedorPrincipal.setStyle(
                "-fx-background-color: #ffcc99;" +
                        "-fx-border-color: #cc6600;" +
                        "-fx-border-width: 4px;" +
                        "-fx-border-radius: 30px;" +
                        "-fx-background-radius: 30px;"
        );
        contenedorPrincipal.setPadding(new Insets(20));

        //generar subcaja
        VBox subcaja = new VBox(20);
        VBox.setMargin(subcaja,new Insets(0,60,200,60));
        subcaja.setAlignment(Pos.CENTER);

//        subcaja.setStyle(
//                "-fx-background-color: #ffe6cc;" +
//                        "-fx-border-color: #cc6600;" +
//                        "-fx-border-width: 4px;" +
//                        "-fx-border-radius: 30px;" +
//                        "-fx-background-radius: 30px;"
//        );

        // Permite que la subcaja crezca dentro del contenedor principal.
        //VBox.setVgrow(subcaja, Priority.ALWAYS);

        // en vez de usar la clase Scanner, se usa la clase TextField ya que Scanner solo sirve para la consola.
        TextArea campoTexto = new TextArea();
        campoTexto.setPromptText("Escribe escruba su respuesta");
        campoTexto.setWrapText(true); // para que el texto salte de línea
        campoTexto.setPrefHeight(150);
        campoTexto.setPrefWidth(500);

        campoTexto.setStyle(
                "-fx-background-color: #transparent;" +
                "-fx-control-inner-background: #transparent;" +
                "-fx-background-insets: 0;" +
                "-fx-padding: 10;" +
                "-fx-background-radius: 30px;" +
                "-fx-border-radius: 30px;" +
                "-fx-border-color: #cc6600;" +
                "-fx-border-width: 3px;" +
                "-fx-font-size: 30px;" +
                "-fx-focus-color: transparent;" +
                "-fx-faint-focus-color: transparent;"+
                "-fx-border-insets: 0;" +
                "-fx-background-insets: 0;" +
                "-fx-border-width: 0;" + // <-- elimina la línea superior
                "-fx-border-color: transparent;" // <-- asegura que no se dibuje nada
        );

        //campoTexto.setStyle("-fx-font-size: 30px;"); // tamaño del texto

        // aplicar curva al contenido interno

//        Platform.runLater(() -> {
//            campoTexto.lookup(".content").setStyle(
//                    "-fx-background-radius: 30px;" +
//                            "-fx-padding: 10;"
//            );
//        });

        Platform.runLater(() -> {
            campoTexto.lookup(".content").setStyle(
                    "-fx-background-color: #ffffff;" +
                            "-fx-background-radius: 0px;" +
                            "-fx-padding: 10;" +
                            "-fx-border-width: 0;" +
                            "-fx-border-color: transparent;"
            );
        });


        // permitir que el campo crezca dentro de la subcaja
        //VBox.setVgrow(campoTexto,Priority.ALWAYS);
        //campoTexto.setPrefHeight(10); //ajuste manual

        //Añadir el campo a la subcaja
        subcaja.getChildren().add(campoTexto);

        //añadimos la subcaja a la principal
        contenedorPrincipal.getChildren().add(subcaja);

        // colocacion de las cajas.
        contenedorPrincipal.setMaxWidth(Double.MAX_VALUE);
        subcaja.setMaxWidth(Double.MAX_VALUE);
        contenedorPrincipal.setPrefHeight(500);
        subcaja.setPrefHeight(300);

        return contenedorPrincipal;
    }


    private void cajaDeLaRespuestaEscrita(){

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

        unBotonCualquiera.setOnMouseEntered(e ->
                hacerBotonGrande.playFromStart());
        unBotonCualquiera.setOnMouseExited(e ->
                botonTamanyoNormal.playFromStart());
    }



}
