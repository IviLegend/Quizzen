package org.example.quizzen.preguntas;

import java.util.ArrayList;

public class PreguntaDesarrollo extends Pregunta
{
    private ArrayList<String> respuestasCorrectas;

    public ArrayList<String> getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    public void setRespuestasCorrectas(ArrayList<String> respuestasCorrectas) {
        this.respuestasCorrectas = respuestasCorrectas;
    }

    public PreguntaDesarrollo() {
        this.respuestasCorrectas = respuestasCorrectas;
    }
    public PreguntaDesarrollo(String enunciado ,String respuestaCorrectaUnica) {
        this.setEnunciado(enunciado);
        this.respuestasCorrectas.add(respuestaCorrectaUnica);
    }
}


