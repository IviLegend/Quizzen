package org.example.quizzen.partidas;

import org.example.quizzen.preguntas.Pregunta;

import java.util.*;

public class Resultados {

    // Mantener el orden de las preguntas
    private Map<Pregunta, List<String>> respuestas = new LinkedHashMap<>();

    public Resultados() {}

    public void anyadirRespuestaFallada(Pregunta preguntaActual, String respuestaUsuario) {
        respuestas.putIfAbsent(preguntaActual, new ArrayList<>());
        respuestas.get(preguntaActual).add(respuestaUsuario);
    }

    public int totalRespuestasFalladas() {
        int total = 0;
        for (List<String> lista : respuestas.values()) {
            total += lista.size();
        }
        return total;
    }

    public Map<Pregunta, List<String>> getRespuestas() {
        return respuestas;
    }
}

