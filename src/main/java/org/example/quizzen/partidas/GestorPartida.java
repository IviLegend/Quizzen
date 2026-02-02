package org.example.quizzen.partidas;

import org.example.quizzen.preguntas.Opcion;
import org.example.quizzen.preguntas.Pregunta;
import org.example.quizzen.preguntas.PreguntaDesarrollo;
import org.example.quizzen.preguntas.PreguntaOpcionMultiple;
import org.example.quizzen.test.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GestorPartida
{
    private Partida partida;
    private int indicePregunta;
    ArrayList<Pregunta> preguntas = new ArrayList<>()/*= partida.getTest().getPreguntas()*/;
    ArrayList<Test> listaTest;

    public GestorPartida(ArrayList<Test> Test) {
        this.listaTest = Test;
    }

    public ArrayList<Pregunta> listaPreguntas(int indicePregunta){
        Test test = listaTest.get(indicePregunta);

        ArrayList<Pregunta> listaDePreguntas = test.getPreguntas();
        //Pregunta pregunta = test.getPreguntas().get(indicePregunta);

        for (int pos = 0; pos < listaDePreguntas.size(); pos++) {
            //Pregunta pregunta = test.getPreguntas().get(indicePregunta);
            Pregunta pregunta = listaDePreguntas.get(pos);
            if (pregunta instanceof PreguntaDesarrollo){
                PreguntaDesarrollo preguntaDesarrollo = new PreguntaDesarrollo();
                String enunciado = pregunta.getEnunciado();
                preguntaDesarrollo.setEnunciado(enunciado);
                preguntas.add(preguntaDesarrollo);
            } else if (pregunta instanceof  PreguntaOpcionMultiple){
                PreguntaOpcionMultiple preguntaOpcionMultiple = new PreguntaOpcionMultiple();
                String enunciado = pregunta.getEnunciado();
                preguntaOpcionMultiple.setEnunciado(enunciado);
                ArrayList<Opcion> listaOpciones=((PreguntaOpcionMultiple) pregunta).getOpciones();
                ArrayList<Opcion> opciones =new ArrayList<>();
                for (int i = 0; i < listaOpciones.size(); i++) {
                    Opcion opcion = listaOpciones.get(i);
                    opciones.add(opcion);
                    System.out.println(opcion);
                    System.out.println("Opciones: "+listaOpciones);
                }
                preguntaOpcionMultiple.setOpciones(opciones);
                preguntas.add(preguntaOpcionMultiple);
            }
        }

        return preguntas;
    }

    public boolean comprobarRespuesta(String respuestaUsu) {

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
