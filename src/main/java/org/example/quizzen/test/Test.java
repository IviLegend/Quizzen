package org.example.quizzen.test;

import org.example.quizzen.preguntas.Pregunta;

import java.util.ArrayList;

public class Test
{
    private String nombre;
    private String descripcion;
    private String categoria;
    private ArrayList<Pregunta> preguntas;

    public Test(String nombre, String descripcion, String categoria, ArrayList<Pregunta> preguntas) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.preguntas = preguntas;
    }
}
