package org.example.quizzen.test;

import org.example.quizzen.preguntas.Pregunta;

import java.util.ArrayList;

public class Test
{
    private String nombre;
    private String descripcion;
    private Categoria categoria;
    private ArrayList<Pregunta> preguntas;

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

    public Categoria getCategoria()
    {
        return categoria;
    }

    public void setCategoria(Categoria categoria)
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
