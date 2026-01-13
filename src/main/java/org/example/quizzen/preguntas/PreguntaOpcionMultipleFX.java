package org.example.quizzen.preguntas;

import javafx.animation.*;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
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
        VBox.setMargin(cajaTitulo, new Insets(10));

        //estilo de la caja
        cajaTitulo.setStyle(
                "-fx-background-color: #ccffcc;"+ // verde claro
                "-fx-border-color: #2e8b57;" + // borde verde oscuro
                "-fx-border-width: 5px;"+
                "-fx-border-radius: 50px;"+
                "-fx-background-radius: 50px;"
        );

        // caja principal de las opciones

        VBox contenedorOpciones = new VBox(20);
        contenedorOpciones.setAlignment(Pos.CENTER);
        VBox.setMargin(contenedorOpciones, new Insets(5, 200, 5, 200)); //Separarlo de los margenes

        //Estilo del cuadro naraja
        contenedorOpciones.setStyle(
                "-fx-background-color: #ffcc99;" +
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


        // las opciones deben estar escritas en dos cajas, la primera con la susodicha opcion y la segunda con la información
        char letra = 'A';
        for (Opcion opcion: preguntaOpcionMultiple.getOpciones()){

            //Fila completa de la opción
            HBox filaOpcion = new HBox(10);
            filaOpcion.setAlignment(Pos.CENTER_LEFT);
            //filaOpcion.setPadding(new Insets(10));

            // caja pequeña para A, B, C y D
            Label labelLetra = new Label(letra + ")");
            labelLetra.setFont(Font.font("Arial", FontWeight.BOLD, 16));
            labelLetra.setAlignment(Pos.CENTER);
            labelLetra.setMinWidth(40);

            StackPane cajaLetra = new StackPane(labelLetra);
            cajaLetra.setPadding(new Insets(8));
            cajaLetra.setStyle( "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #999999;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-background-radius: 8px;"
            );

            // caja grande para el texto de las opciones
            Label textoOpcion = new Label(opcion.getSentencia());
            textoOpcion.setFont(Font.font("Arial", 16));
            textoOpcion.setWrapText(true);

            textoOpcion.setAlignment(Pos.CENTER_LEFT);
            StackPane.setAlignment(textoOpcion, Pos.CENTER_LEFT);

            StackPane cajaTexto = new StackPane(textoOpcion);
            cajaTexto.setPadding(new Insets(8));
            cajaTexto.setStyle( "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #999999;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-background-radius: 8px;"
            );

            cajaTexto.setMaxWidth(Double.MAX_VALUE);

            HBox.setHgrow(cajaTexto, Priority.ALWAYS);

            // Añadimos las dos cajas a la misma línea
            filaOpcion.getChildren().addAll(cajaLetra, cajaTexto);

            // Añadimos la fila al cuadro naranja
            contenedorOpciones.getChildren().add(filaOpcion);

            filaOpcion.setStyle(
                    "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #999999;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-background-radius: 8px;" );

//            Label textoOpcion = new Label(letra + ") " + opcion.getSentencia());
//            textoOpcion.setFont(Font.font("Arial", 16));
//            filaOpcion.getChildren().add(textoOpcion);
//            columnaOpciones.getChildren().add(filaOpcion);
//
//            RadioButton radioButton = new RadioButton(letra+") "+opcion.getSentencia());

            // con radioButton solo puede haber una selección correcta. sin el setToggleGroup se pueden selecionar varias opciones.
//            radioButton.setToggleGroup(grupoOpciones);
//            radioButton.setFont(Font.font("Arial",16));
//            contenidoOpcion.getChildren().add(radioButton); //los resultados aparecen uno debajo del otro.

            letra++; // a) despues b) despues c) ...
        }
        // botones A, B, C y D debajo del cuadro de las opciones

        HBox filaBotonesABCD = new HBox(20);
        filaBotonesABCD.setAlignment(Pos.CENTER);

        //Efecto de brillo:
        DropShadow glow = new DropShadow();

        glow.setColor(Color.WHITE);
        glow.setRadius(40);
        glow.setSpread(0.3);


//        String estiloBotonABCD = "-fx-background-color: #ffff99;" +
//                "-fx-border-color: #cccc00;" +
//                "-fx-border-width: 3px;" + // grosor de la linea
//                "-fx-background-radius: 50px;" + // pronunciacion de la curva
//                "-fx-border-radius: 50px;" + // pronunciacion de la curva
//                "-fx-font-weight: bold;" +
//                "-fx-font-size: 50px;"; // tamaño de la letra


        for (char caracter = 'A'; caracter <= 'D'; caracter++){

            Button boton = new Button(String.valueOf(caracter));
            boton.setPrefWidth(400);
            boton.setPrefHeight(200);
            boton.setFont(Font.font("Arial", FontWeight.BOLD, 70));
            boton.setStyle("-fx-background-radius: 50px; -fx-border-radius: 50px; -fx-border-width: 4px;");

            // Colores según la letra

            switch (caracter){
                case 'A':
                    boton.setStyle(boton.getStyle() +
                            "-fx-background-color: #fd4d4d;" + // rojo suave
                            "-fx-border-color: #b30000;");
                    break;
                case 'B':
                    boton.setStyle(boton.getStyle() +
                            "-fx-background-color: #4d79ff;" +
                            "-fx-border-color: #0033cc;");
                    break;
                case 'C':
                    boton.setStyle(boton.getStyle() +
                            "-fx-background-color: #ffeb3b;" + // amarillo suave
                            "-fx-border-color: #e6c300;");
                    break;
                case 'D':
                    boton.setStyle(boton.getStyle() +
                            "-fx-background-color: #4dff4d;" + // verde suave
                            "-fx-border-color: #00b300;");
                    break;
            }
            //boton.setStyle(estiloBotonABCD);

            // Efecto de brillo al pasar el ratón
            boton.setOnMouseEntered(e ->
                    boton.setEffect(glow)
            );
            boton.setOnMouseExited(e ->
                    boton.setEffect(null)
            );

            cambiarTamanyoBoton(boton, 1.15, 1.0);

            StackPane contenedor = new StackPane(boton);
            contenedor.setPrefSize(boton.getPrefWidth(), boton.getPrefHeight());
            filaBotonesABCD.getChildren().add(contenedor);
            //filaBotonesABCD.getChildren().add(boton);
            aplicarEfectoOndas(boton);

        }

        //fuera del cuadro naranja
        contenedorOpciones.getChildren().add(columnaOpciones);



        // botones para ir hacia adelante o hacia atrás
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

        HBox contenedorBotones = new HBox(40,btonAtras,btonSiguiente);
        contenedorBotones.setPadding(new Insets(20,0,0,0));
        contenedorBotones.setAlignment(Pos.CENTER); // centramos los botones

        //Layout principal
        VBox root = new VBox(20, cajaTitulo, contenedorOpciones, filaBotonesABCD, contenedorBotones);
        root.setPadding(new Insets(20));
        root.setAlignment(Pos.CENTER); //centrar el bloque
        //root.getChildren().add(cajaTitulo); //añadir la propia caja

        root.setStyle("-fx-background-color: #0f172a;"); //cambiar el fondo de la ventana a un grisaceo azulado

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

    private void aplicarEfectoOndas(Button boton){
        boton.setOnMouseClicked( e ->{

            Circle onda = new Circle(0, Color.rgb(255,255,255,0.4)); // color blanco semitransparente
            onda.setCenterX(e.getX());
            onda.setCenterY(e.getY());

            StackPane parent = (StackPane) boton.getParent();
            parent.getChildren().add(onda);

            Timeline animacion = new Timeline(
                    new KeyFrame(Duration.ZERO,
                            new KeyValue(onda.radiusProperty(), 0),
                            new KeyValue(onda.opacityProperty(), 1)),
                    new KeyFrame(Duration.millis(400),
                            new KeyValue(onda.radiusProperty(), 200),
                            new KeyValue(onda.opacityProperty(), 0))
            );

            animacion.setOnFinished(ev -> parent.getChildren().remove(onda));
            animacion.play();

        });
    }
}
