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

public class PreguntaOpcionMultipleFX {

    public void mostrar(Stage stage, PreguntaOpcionMultiple preguntaOpcionMultiple, HelloApplication app){

        //VBox root = new VBox(20, titulo, contenidoOpcion, contenedorBotones);

        // Titulo:
        VBox cajaTitulo = cajaDelTitulo(preguntaOpcionMultiple);

        // caja principal de las opciones
        VBox contenedorOpciones = cajaPrincipalDeLasOpciones();

        // Opciones:
        VBox columnaOpciones = new VBox(15); //contenedir en vertical
        columnaOpciones.setAlignment(Pos.CENTER_LEFT); // centramos las opciones

        // las opciones deben estar escritas en dos cajas, la primera con la susodicha opcion y la segunda con la información
        generarSubcajas(preguntaOpcionMultiple, contenedorOpciones, columnaOpciones);


        // botones A, B, C y D debajo del cuadro de las opciones

        HBox filaBotonesABCD = new HBox(20);
        filaBotonesABCD.setAlignment(Pos.CENTER);

        //Efecto de brillo:
        //aquí los botones



        botonesDeInteraccionConLasPreguntas(filaBotonesABCD, contenedorOpciones, columnaOpciones);


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

        btonSiguiente.setOnMouseClicked( e -> app.siguientePregunta(stage));
        btonAtras.setOnMouseClicked(e -> app.preguntaAnterior(stage));


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
     * Genera y configura los botones de interacción correspondientes a las opciones de respuesta
     * (A, B, C y D) que el usuario puede seleccionar durante la pregunta.
     *
     * <p>Cada botón se crea con un tamaño fijo de 400 px de ancho por 200 px de alto, un estilo
     * visual redondeado y un color distintivo según la opción:
     * <ul>
     *     <li>A → Rojo</li>
     *     <li>B → Azul</li>
     *     <li>C → Amarillo</li>
     *     <li>D → Verde</li>
     * </ul>
     * Además, todos los botones incorporan un efecto de resplandor blanco que se activa al pasar
     * el cursor por encima.</p>
     *
     * <p>Durante la creación, cada botón también aplica el efecto visual definido en el método
     * {@link #cambiarTamanyoBoton(Button, double, double)}, que ajusta su tamaño al interactuar
     * con el usuario. Asimismo, se aplica un efecto adicional mediante {@code aplicarEfectoOndas(Button)}.</p>
     *
     * <p>Finalmente, los botones generados se añaden al contenedor horizontal proporcionado, y la
     * columna de opciones se incorpora al contenedor principal.</p>
     *
     * @param filaBotonesABCD     contenedor horizontal donde se insertarán los botones A, B, C y D
     * @param contenedorOpciones  contenedor principal que agrupa las opciones de respuesta
     * @param columnaOpciones     columna que contiene los elementos relacionados con las opciones
     */

    private void botonesDeInteraccionConLasPreguntas(HBox filaBotonesABCD, VBox contenedorOpciones, VBox columnaOpciones) {

        // efecto visual de resplandor
        DropShadow glow = new DropShadow();
        glow.setColor(Color.WHITE);
        glow.setRadius(40); // radio del efecto
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
                            "-fx-border-color: #0033cc;"); // azul suabe
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
            aplicarEfectoOndas(boton, columnaOpciones, caracter);

        }

        //fuera del cuadro naranja
        contenedorOpciones.getChildren().add(columnaOpciones);
    }

    /**
     * Este metodo genera subcajas en la caja principal naranja donde se contendra las opciones. Dichas subcajas se comportaran de
     * la siguiente manera, en dos columnas, la primera columna estará contenida las diversas opciones "A", "B", "C", "D", y en la
     * segunda columna estará contenida la información de cada opción.
     * <p>Para ello se utiliza un objeto {@link HBox} que contiene la fila completa de una opcion. y dentro de este objeto
     * contiene dos objetos de tipo {@link Label} que seran los que contiene la informacion de la propia opcion "A", "B", "C", "D"
     * seguido de un caracter ")", y en la segundo, contiene la propia información. Despues usamos el objeto {@link StackPane}
     * que sirve para darle una un estilo en la parte visual.</p>
     * @param preguntaOpcionMultiple recibe este objeto que contiene las diversas opciones para descomponerlo y sacar las opciones
     *                               para posteriormente mostrarlo en la ventana
     * @param contenedorOpciones este objeto es la caja principal naranja donde se visualiza las diferentes opciones. Para que se
     *                           entienda, es como un molde donde encima de esta manta se colocan los plantos y cubiertos, es decir
     *                           las opciones.
     */

    private static void generarSubcajas(PreguntaOpcionMultiple preguntaOpcionMultiple, VBox contenedorOpciones,  VBox columnaOpciones) {
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

            // Añadimos las dos cajas a la misma línea del cuadro naranja
            filaOpcion.getChildren().addAll(cajaLetra, cajaTexto);

            // Añadimos la fila completa al cuadro naranja
            contenedorOpciones.getChildren().add(filaOpcion);
            columnaOpciones.getChildren().add(filaOpcion);

            filaOpcion.setStyle(
                    "-fx-background-color: #ffffff;" +
                    "-fx-border-color: #999999;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-background-radius: 8px;" );

            letra++; // a) despues b) despues c) ...
        }
    }

    /**
     * Este metodo genera la caja principal donde estarán las multiples opciones y su estilo de diseño.
     * @return devuelve un objeto caja {@link VBox} para mostrarlo posteriormente en la ventana
     */

    private static VBox cajaPrincipalDeLasOpciones() {
        VBox contenedorOpciones = new VBox(20);
        contenedorOpciones.setAlignment(Pos.CENTER);
        VBox.setMargin(contenedorOpciones, new Insets(5, 300, 5, 300)); //Separarlo de los margenes

        //Estilo del cuadro naraja
        contenedorOpciones.setStyle(
                "-fx-background-color: #ffcc99;" +
                "-fx-border-color: #cc6600;" +
                "-fx-border-width: 4px;" +
                "-fx-border-radius: 10px;" +
                "-fx-background-radius: 10px;"
        );
        contenedorOpciones.setPadding(new Insets(20));
        return contenedorOpciones;
    }

    /**
     * Este metodo recibe el título de un objeto {@link PreguntaOpcionMultiple} y se extrae el titulo de la pregunta
     * dandole un formate en el centrarse. Al mismo tiempo el {@link Label} que contiene el titulo será inducido a un objeto
     * de tipo caja {@link VBox} para centrarlo en la ventana y dandole un formato concreto, tamaño y color.
     * @param preguntaOpcionMultiple es un objeto del backen dond está compuesto las preguntas y las diversas opciones.
     * @return devuelve un objeto de tipo {@link VBox} para despues iniciar su visualización en la ventana.
     */

    private static VBox cajaDelTitulo(PreguntaOpcionMultiple preguntaOpcionMultiple) {
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
        return cajaTitulo;
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

    /** * Aplica un efecto visual de onda expansiva al botón indicado cuando el usuario hace clic sobre él.
     *
     *  <p>Al producirse el clic, se genera un círculo blanco semitransparente en la * posición exacta del cursor.
     *  Este círculo se expande rápidamente mientras reduce * su opacidad, creando un efecto de onda o “ripple” similar
     *  al de las interfaces * modernas.</p>
     *
     *  <p>El efecto se construye mediante una animación {@link Timeline} que modifica el radio y la opacidad del círculo
     *  durante 400 milisegundos. Una vez finalizada * la animación, el círculo se elimina automáticamente del contenedor
     *  padre ({@link StackPane}) del botón.</p>
     *
     *  @param boton botón al que se le aplicará el efecto de onda al hacer clic
     *  */

    private void aplicarEfectoOndas(Button boton, VBox columnaOpciones, char letra){
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
                            new KeyValue(onda.radiusProperty(), 100),
                            new KeyValue(onda.opacityProperty(), 0))
            );

            animacion.setOnFinished(ev -> parent.getChildren().remove(onda));
            animacion.play();

            int index = letra - 'A'; // A=0, B=1, C=2, D=3

            for (Node nodo: columnaOpciones.getChildren()){
                if (nodo instanceof HBox filaOpcion){
                    filaOpcion.setStyle("-fx-background-color: #ffffff;" +
                            "-fx-border-color: #999999;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-radius: 8px;" +
                            "-fx-background-radius: 8px;");
                    for (Node sub: filaOpcion.getChildren()){
                        sub.setStyle("-fx-background-color: #ffffff;" +
                                "-fx-border-color: #999999;" +
                                "-fx-border-width: 2px;" +
                                "-fx-border-radius: 8px;" +
                                "-fx-background-radius: 8px;");
                    }
                }
            }



            HBox fila = (HBox) columnaOpciones.getChildren().get(index);

            // colorear la fila completa
            fila.setStyle( "-fx-background-color: #ccffcc;" +
                    "-fx-border-color: #66cc66;" +
                    "-fx-border-width: 2px;" +
                    "-fx-border-radius: 8px;" +
                    "-fx-background-radius: 8px;" );
            // colorear subcajas

            for (Node sub : fila.getChildren()) {
                if (sub instanceof StackPane) {
                    sub.setStyle( "-fx-background-color: #ccffcc;" +
                            "-fx-border-color: #66cc66;" +
                            "-fx-border-width: 2px;" +
                            "-fx-border-radius: 8px;" +
                            "-fx-background-radius: 8px;");
                }
            }

        });
    }
}
