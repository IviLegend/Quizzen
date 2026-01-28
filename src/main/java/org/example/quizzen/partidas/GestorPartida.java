package org.example.quizzen.partidas;

import org.example.quizzen.preguntas.Pregunta;
import org.example.quizzen.preguntas.PreguntaDesarrollo;
import org.example.quizzen.preguntas.PreguntaOpcionMultiple;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestorPartida
{
    private Partida partida;
    private int indicePregunta;
    ArrayList<Pregunta> preguntas = partida.getTest().getPreguntas();



    public boolean comprobarRespuesta(String respuestaUsu)
    {
        Pregunta preguntaContestada = partida.getTest().getPreguntas().get(indicePregunta);

        if (preguntaContestada instanceof PreguntaDesarrollo pregDessContestada)
        {
            List<String> palabrasRespuesta= List.of(respuestaUsu.split(" "));
            return pregDessContestada.getRespuestasCorrectas().stream().anyMatch(palabrasRespuesta::contains);

        }else
        {
            PreguntaOpcionMultiple pregOpcContestada= (PreguntaOpcionMultiple) preguntaContestada;
            return pregOpcContestada.getRespuestaCorrecta().equalsIgnoreCase(respuestaUsu);
        }
    }

    //Si devuelve true ya ha mostrado todas las preguntas que contiene el test
    public boolean finalizarPartida()
    {
        return indicePregunta >= partida.getTest().getPreguntas().size();
    }

    //Solo funcionara la función cuando la partida este acabada, para evitar errores
    public void mostrarPregFalladas()
    {
        if (finalizarPartida())
        {
            Map<Pregunta,String> totalPregFalladas= partida.getResultados().getRespuestas();
            //todo mostrar contenido en la interfaz
        }
    }

}
