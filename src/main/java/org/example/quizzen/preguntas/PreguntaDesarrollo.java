package org.example.quizzen.preguntas;

import java.util.ArrayList;

public class PreguntaDesarrollo extends Pregunta
{
    private String respuestaCorrecta;
    private boolean ignoraCasing;

    public PreguntaDesarrollo(String enunciado, String respuestaCorrecta, boolean ignoraCasing) {
        super(enunciado);
        this.respuestaCorrecta = respuestaCorrecta;
        this.ignoraCasing = ignoraCasing;
    }
}
