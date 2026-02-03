package org.example.quizzen.partidas;

import org.example.quizzen.preguntas.Opcion;
import org.example.quizzen.preguntas.Pregunta;
import org.example.quizzen.preguntas.PreguntaDesarrollo;
import org.example.quizzen.preguntas.PreguntaOpcionMultiple;
import org.example.quizzen.test.Test;

import java.util.*;

public class GestorPartida {

    private Partida partida;
    private int indicePregunta = 0;
    private ArrayList<Pregunta> preguntas = new ArrayList<>();

    public GestorPartida(ArrayList<Test> listaTest) {
        this.partida = new Partida();
        this.partida.setTest(listaTest.get(0)); // usamos el primer test
        this.partida.setResultados(new Resultados());

        // Mezclamos las preguntas para que salgan aleatorias
        this.preguntas = new ArrayList<>(partida.getTest().getPreguntas());
        Collections.shuffle(this.preguntas);
    }

    public Pregunta getPreguntaActual() {
        return preguntas.get(indicePregunta);
    }

    public void siguientePregunta() {
        if (indicePregunta < preguntas.size() - 1) {
            indicePregunta++;
        }
    }

    public void anteriorPregunta() {
        if (indicePregunta > 0) {
            indicePregunta--;
        }
    }

    public boolean comprobarRespuestaActual(String respuestaUsu) {
        Pregunta pregunta = getPreguntaActual();

        if (pregunta instanceof PreguntaDesarrollo pregDess) {
            List<String> palabras = List.of(respuestaUsu.toLowerCase().split(" "));

            boolean correcta = pregDess.getRespuestasCorrectas()
                    .stream()
                    .map(String::toLowerCase)
                    .anyMatch(palabras::contains);

            if (!correcta) {
                partida.getResultados().anyadirRespuestaFallada(pregDess, respuestaUsu);
            }

            return correcta;

        } else if (pregunta instanceof PreguntaOpcionMultiple pregOpc) {
            boolean correcta = pregOpc.getRespuestaCorrecta()
                    .equalsIgnoreCase(respuestaUsu);

            // Marcamos las opciones correctas/incorrectas
            for (Opcion op : pregOpc.getOpciones()) {
                op.setEsCorrecta(op.getSentencia().equalsIgnoreCase(pregOpc.getRespuestaCorrecta()));
            }

            if (!correcta) {
                partida.getResultados().anyadirRespuestaFallada(pregOpc, respuestaUsu);
            }

            return correcta;
        }

        return false;
    }

    public boolean finalizarPartida() {
        return indicePregunta >= preguntas.size() - 1;
    }

    public Resultados getResultados() {
        return partida.getResultados();
    }

    public Test getTest() {
        return partida.getTest();
    }
}
