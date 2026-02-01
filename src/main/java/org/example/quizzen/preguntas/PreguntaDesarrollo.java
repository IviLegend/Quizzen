package org.example.quizzen.preguntas;

import java.util.ArrayList;

public class PreguntaDesarrollo extends Pregunta
{
    private ArrayList<String> respuestasCorrectas=new ArrayList<>();

    public ArrayList<String> getRespuestasCorrectas() {
        return respuestasCorrectas;
    }

    String respuestaUsuario;
    public void setRespuestasCorrectas(ArrayList<String> respuestasCorrectas) {
        this.respuestasCorrectas = respuestasCorrectas;
    }

    public PreguntaDesarrollo() {
        this.respuestasCorrectas = respuestasCorrectas;
    }

    public String getRespuestaUsuario() {
        return respuestaUsuario;
    }

    public void setRespuestaUsuario(String respuestaUsuario) {
        this.respuestaUsuario = respuestaUsuario;
    }

    public PreguntaDesarrollo(String enunciado ,String respuestaCorrectaUnica) {
        this.setEnunciado(enunciado);
        this.respuestasCorrectas.add(respuestaCorrectaUnica);
    }
}


