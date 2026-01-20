package org.example.quizzen.preguntas;

import java.util.ArrayList;

public class PreguntaOpcionMultiple extends Pregunta
{
    private ArrayList<Opcion> opciones;

    public PreguntaOpcionMultiple() {
        //super();
        this.opciones = new ArrayList<>();
    }

    public ArrayList<Opcion> getOpciones() {
        return opciones;
    }

    public void setOpciones(ArrayList<Opcion> opciones) {
        this.opciones = opciones;
    }
}
