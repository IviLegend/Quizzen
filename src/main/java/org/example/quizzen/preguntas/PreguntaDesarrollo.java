package org.example.quizzen.preguntas;

import java.util.ArrayList;

public class PreguntaDesarrollo extends Pregunta
{
    private ArrayList<String> respuestasCorrectas;
    private boolean ignoraCasing;

    public PreguntaDesarrollo(String enunciado, ArrayList<String> respuestasCorrectas, boolean ignoraCasing) {
        super(enunciado);
        this.respuestasCorrectas = respuestasCorrectas;
        this.ignoraCasing = ignoraCasing;
    }
}
