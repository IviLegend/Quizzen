package org.example.quizzen.partidas;

import org.example.quizzen.preguntas.Pregunta;

import java.util.Map;

public class Resultados
{
    public Map<Pregunta,String> respuestas;

    public Resultados()
    {

    }

    public void anyadirRespuestaFallada(Pregunta preguntaActual, String respuestaUsuario)
    {
        this.respuestas.put(preguntaActual, respuestaUsuario);
    }

    public int totalRespuestasFalladas()
    {
        return respuestas.size();
    }
}

