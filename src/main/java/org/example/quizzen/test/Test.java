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
    }
    public String getNombre()
    {
        return nombre;
    }

    public void setNombre(String nombre)
    {
        this.nombre = nombre;
    }

    public String getDescripcion()
    {
        return descripcion;
    }

    public void setDescripcion(String descripcion)
    {
        this.descripcion = descripcion;
    }

    public String getCategoria()
    {
        return categoria;
    }

    public void setCategoria(String categoria)
    {
        this.categoria = categoria;
    }

    public ArrayList<Pregunta> getPreguntas()
    {
        return preguntas;
    }

    public void setPreguntas(ArrayList<Pregunta> preguntas)
    {
        this.preguntas = preguntas;
    }
}
