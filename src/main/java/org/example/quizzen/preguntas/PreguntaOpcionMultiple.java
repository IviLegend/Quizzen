package org.example.quizzen.preguntas;

import java.util.ArrayList;

public class PreguntaOpcionMultiple extends Pregunta {
    private ArrayList<Opcion> opciones;

    public PreguntaOpcionMultiple(String enunciado, ArrayList<Opcion> opciones) {
        super(enunciado);
        this.opciones = opciones;
    }
}
