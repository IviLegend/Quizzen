package org.example.quizzen.partidas;

import org.example.quizzen.test.Test;

import java.time.LocalDateTime;
import java.util.List;

public class Partida {
    private Test test;
    private LocalDateTime fechaPartida;
    private Resultados resultados;

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public LocalDateTime getFechaPartida() {
        return fechaPartida;
    }

    public void setFechaPartida(LocalDateTime fechaPartida) {
        this.fechaPartida = fechaPartida;
    }

    public Resultados getResultados() {
        return resultados;
    }

    public void setResultados(Resultados resultados) {
        this.resultados = resultados;
    }
}
