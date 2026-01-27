package org.example.quizzen;

import org.example.quizzen.preguntas.Opcion;
import org.example.quizzen.preguntas.Pregunta;
import org.example.quizzen.preguntas.PreguntaDesarrollo;
import org.example.quizzen.preguntas.PreguntaOpcionMultiple;
import org.example.quizzen.test.Categoria;
import org.example.quizzen.test.Test;

import java.sql.*;
import java.util.ArrayList;

public class GestorTest {
    public static void main(String[] args) {
        String url = "jdbc:mysql://127.0.0.1:3306/Quizzen";
        String usuario = "alumno";
        String password = "alumno";
        Connection conexion = null;
        ArrayList<Test> tests = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(url, usuario, password);

            System.out.println("Conexión realizada con éxito.");

            String sql = "SELECT * FROM Test";

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                int idTest = rs.getInt("id_test");
                String nombre = rs.getString("nombre");
                String descripcion = rs.getString("descripcion");
                String categoria = rs.getString("categoria");
                System.out.println(
                        "- Nombre: " + nombre +
                                "| Descripción: " + descripcion +
                                "| Categoría: " + categoria);
                String sqlPreguntas = "SELECT * FROM Pregunta WHERE Test_id_test LIKE " + idTest;
                Statement stPreguntas = conexion.createStatement();
                ResultSet rsPreguntas = stPreguntas.executeQuery(sqlPreguntas);
                ArrayList<Pregunta> preguntas = new ArrayList<>();

                while (rsPreguntas.next()){
                    int idPregunta = rsPreguntas.getInt("id_pregunta");
                    String enunciado = rsPreguntas.getString("enunciado");
                    int tipoPregunta = rsPreguntas.getInt("tipoPregunta");

                    if (tipoPregunta == 1){
                        String sqlPreguntasDesarrollo = "SELECT * FROM Pregunta_Desarrollo WHERE id_pregunta LIKE " + idPregunta;
                        Statement stPreguntasDesarrollo = conexion.createStatement();
                        ResultSet rsPreguntasDesarrollo = stPreguntasDesarrollo.executeQuery(sqlPreguntasDesarrollo);
                        while (rsPreguntasDesarrollo.next()){
                            String respuestaCorrecta = rsPreguntasDesarrollo.getString("respuesta");
                            PreguntaDesarrollo pregunta = new PreguntaDesarrollo(enunciado, respuestaCorrecta, false);
                            preguntas.add(pregunta);
                            System.out.println(" * "+ enunciado + ": " + respuestaCorrecta);
                        }
                        rsPreguntasDesarrollo.close();

                    } else if (tipoPregunta == 2){
                        String sqlPreguntasDesarrollo = "SELECT * FROM Pregunta_Opcion_Multiple WHERE id_pregunta LIKE " + idPregunta;
                        Statement stPreguntasOpcionMultiple = conexion.createStatement();
                        ResultSet rsPreguntasOpcionMultiple = stPreguntasOpcionMultiple.executeQuery(sqlPreguntasDesarrollo);
                        ArrayList<Opcion> opciones = new ArrayList<>();
                        while (rsPreguntasOpcionMultiple.next()){
                            String sqlOpciones = "SELECT * FROM Opcion WHERE id_pregunta LIKE " + idPregunta;
                            ResultSet rsOpciones = stPreguntas.executeQuery(sqlOpciones);
                            while (rsOpciones.next()){
                                String textoOpcion = rsOpciones.getString("texto_opcion");
                                boolean esCorrecto = rsOpciones.getBoolean("es_correcto");

                                System.out.println(textoOpcion + ", correcto = " + esCorrecto);
                                opciones.add(new Opcion(textoOpcion, esCorrecto));
                            }
                            PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple(enunciado, opciones);
                            preguntas.add(pregunta);
                        }
                        rsPreguntasOpcionMultiple.close();
                    }
                }
                tests.add(new Test(nombre, descripcion, categoria, preguntas));
            }
            rs.close();

        } catch (ClassNotFoundException e) {
            System.out.println("No se ha encontrado el driver de MySQL.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos: " + e.getMessage());
        } finally {
            if (conexion != null) {
                try {
                    conexion.close();
                    System.out.println("Conexión cerrada correctamente.");
                } catch (SQLException e) {
                    System.out.println("Error al cerrar la conexión.");
                }
            }
        }
        Test test = tests.get(0);
    }
}
