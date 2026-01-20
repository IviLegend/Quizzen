package org.example.quizzen.preguntas;

public class Opcion
{
    private String sentencia;
    private boolean esCorrecta;

    public Opcion(String sentencia) {
        this.sentencia = sentencia;
    }

    public Opcion(String sentencia, boolean esCorrecta) {
        this.sentencia = sentencia;
        this.esCorrecta = esCorrecta;
    }

    public String getSentencia() {
        return sentencia;
    }

    public boolean isEsCorrecta() {
        return esCorrecta;
    }
}
