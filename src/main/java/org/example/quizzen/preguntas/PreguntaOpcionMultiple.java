package org.example.quizzen.preguntas;

import java.util.ArrayList;

public class PreguntaOpcionMultiple extends Pregunta
{
    private ArrayList<Opcion> opciones;

    private Integer respuestaSeleccionada = null; // 0=A, 1=B, 2=C, 3=D

    private String respuestaCorrecta;

    public PreguntaOpcionMultiple()
    {
        //super();
        this.opciones = new ArrayList<>();
    }

    public String getRespuestaCorrecta()
    {
        return respuestaCorrecta;
    }

    public Integer getRespuestaSeleccionada() {
        return respuestaSeleccionada;
    }

    public void setRespuestaSeleccionada(Integer respuestaSeleccionada)
    {
        this.respuestaSeleccionada = respuestaSeleccionada;
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }
}
