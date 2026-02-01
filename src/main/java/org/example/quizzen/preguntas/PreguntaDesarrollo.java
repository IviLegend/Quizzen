package org.example.quizzen.preguntas;

import java.util.ArrayList;

public class PreguntaDesarrollo extends Pregunta
{
    private ArrayList<String> respuestasCorrectas;
    private String respuestaUsuario = "";

    public ArrayList<String> getRespuestasCorrectas()
    {
        return respuestasCorrectas;
    }

    public void setRespuestasCorrectas(ArrayList<String> respuestasCorrectas)
    {
        this.respuestasCorrectas = respuestasCorrectas;
    }

    public String getRespuestaUsuario() {
        return respuestaUsuario;
    }

    public void setRespuestaUsuario(String respuestaUsuario) {
        this.respuestaUsuario = respuestaUsuario;
    }
}


