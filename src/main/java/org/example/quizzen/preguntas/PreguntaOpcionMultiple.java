package org.example.quizzen.preguntas;

import java.util.ArrayList;

public class PreguntaOpcionMultiple extends Pregunta
{
    private ArrayList<Opcion> opciones;

    private Integer respuestaSeleccionada = null; // 0=A, 1=B, 2=C, 3=D

    public PreguntaOpcionMultiple() {
        //super();
        this.opciones = new ArrayList<>();
    }

    public Integer getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(Integer respuestaSeleccionada) {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }
}
