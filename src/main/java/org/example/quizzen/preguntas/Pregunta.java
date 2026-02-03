package org.example.quizzen.preguntas;

import java.util.Objects;

public abstract class Pregunta
{
    private String enunciado;

    public String getEnunciado() {
        return enunciado;
    }

    public void setEnunciado(String enunciado) {
        this.enunciado = enunciado;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Pregunta pregunta = (Pregunta) o;
        return Objects.equals(enunciado, pregunta.enunciado);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(enunciado);
    }
}
