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

        //cajaTitulo.setMaxWidth(Double.MAX_VALUE); //la caja ocupa todo el ancho
        VBox.setMargin(cajaTitulo, new Insets(50));

        //estilo de la caja
        cajaTitulo.setStyle(
                "-fx-background-color: #ccffcc;"+ // verde claro
                "-fx-border-color: #2e8b57;" + // borde verde oscuro
                "-fx-border-width: 5px;"+
                "-fx-border-radius: 10px;"+
                "-fx-background-radius: 10px;"
        );

        // caja principal de las opciones

        VBox contenedorOpciones = new VBox(20);
        contenedorOpciones.setAlignment(Pos.CENTER);

        //Estilo del cuadro naraja
        contenedorOpciones.setStyle( "-fx-background-color: #ffcc99;" +
                "-fx-border-color: #cc6600;" +
                "-fx-border-width: 4px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );
        contenedorOpciones.setPadding(new Insets(20));

        // Opciones:

        VBox columnaOpciones = new VBox(15); //contenedir en vertical
        columnaOpciones.setAlignment(Pos.CENTER_LEFT); // centramos las opciones

//        ToggleGroup grupoOpciones = new ToggleGroup(); // solo se seleciona una opción
//        VBox contenidoOpcion = new VBox(10); //contenedir en vertical
//        contenidoOpcion.setAlignment(Pos.CENTER); // centramos las opciones

        char letra = 'A';
        for (Opcion opcion: preguntaOpcionMultiple.getOpciones()){

            HBox cajaOpcion = new HBox(10);
            cajaOpcion.setAlignment(Pos.CENTER_LEFT);
            cajaOpcion.setPadding(new Insets(10));

            cajaOpcion.setStyle(
                    "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #999999;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-background-radius: 8px;" );

            Label textoOpcion = new Label(letra + ") " + opcion.getSentencia());
            textoOpcion.setFont(Font.font("Arial", 16));
            cajaOpcion.getChildren().add(textoOpcion);
            columnaOpciones.getChildren().add(cajaOpcion);

            RadioButton radioButton = new RadioButton(letra+") "+opcion.getSentencia());

            // con radioButton solo puede haber una selección correcta. sin el setToggleGroup se pueden selecionar varias opciones.
//            radioButton.setToggleGroup(grupoOpciones);
//            radioButton.setFont(Font.font("Arial",16));
//            contenidoOpcion.getChildren().add(radioButton); //los resultados aparecen uno debajo del otro.

            letra++; // a) despues b) despues c) ...
        }
        // botones A, B, C y D debajo del cuadro de las opciones

        HBox filaBotonesABCD = new HBox(20);
        filaBotonesABCD.setAlignment(Pos.CENTER);

        String estiloBotonABCD = "-fx-background-color: #ffff99;" +
                "-fx-border-color: #cccc00;" +
                "-fx-border-width: 3px;" +
                "-fx-background-radius: 10px;" +
                "-fx-border-radius: 10px;" +
                "-fx-font-weight: bold;" +
                "-fx-font-size: 16px;";

        for (char caracter = 'A'; caracter <= 'D'; caracter++){
            Button boton = new Button(String.valueOf(caracter));
            boton.setPrefWidth(60);
            boton.setStyle(estiloBotonABCD);
            cambiarTamanyoBoton(boton, 1.25, 1.0);
            filaBotonesABCD.getChildren().add(boton);

        }

        // Añadimos opciones + botones ABCD al cuadro naranja
        contenedorOpciones.getChildren().addAll(columnaOpciones, filaBotonesABCD);



        // botones para ir hacia adelante o hacia atrás
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

        cambiarTamanyoBoton(btonAtras,1.25,1.0);

        cambiarTamanyoBoton(btonSiguiente,1.25,1.0);


        btonAtras.setFont(Font.font("Arial", FontWeight.NORMAL, 14));
        btonSiguiente.setFont(Font.font("Arial", FontWeight.NORMAL, 14));

        HBox contenedorBotones = new HBox(20,btonAtras,btonSiguiente);
        contenedorBotones.setPadding(new Insets(20,0,0,0));
        contenedorBotones.setAlignment(Pos.CENTER); // centramos los botones

        //Layout principal
        VBox root = new VBox(20, /*titulo*/cajaTitulo, contenedorOpciones, contenedorBotones);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER); //centrar el bloque
        //root.getChildren().add(cajaTitulo); //añadir la propia caja

        Scene scene = new Scene(root,600,400);
        stage.setScene(scene);
        stage.setTitle("PreguntaOpcionMultiple");
        stage.show();

    }

    /**
     * Este metodo aplica un aumento al botón cuando el raton se posiciona encima del botón.
     * A su vez, cuando el cursor deja estar encima del botón, este vuelve al tamaño normal
     * Cuando el cursor se posiciona encima de cualquier boton, o deja de estar encima, el
     * boton se agrande y vuelve a su forma nomrla de forma suave
     * <p>Este metodo esta compuesto por dos animaciones de tipp {@link ScaleTransition}
     * ya que este objeto se encarga de aumentar y disminuir los tamaños</p>
     * @ autor Alvaro Segura
     * @param unBotonCualquiera se le pasa un objeto de tipo Button al que se le aplica las
     *                          animaciones de escala
     */

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
