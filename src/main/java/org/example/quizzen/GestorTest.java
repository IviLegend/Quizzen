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

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            conexion = DriverManager.getConnection(url, usuario, password);

            System.out.println("Conexión realizada con éxito.");

            String sql = "SELECT * FROM Test";

            Statement st = conexion.createStatement();
            ResultSet rs = st.executeQuery(sql);
            ArrayList<Test> tests = new ArrayList<>();
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
//                System.out.println(" · Preguntas: ");
                while (rsPreguntas.next()){
//                    System.out.println("Enunciado:");
                    String enunciado = rsPreguntas.getString("enunciado");
                    int tipoPregunta = rsPreguntas.getInt("tipoPregunta");

                    if (tipoPregunta == 1){
                        String sqlPreguntasDesarrollo = "SELECT * FROM Pregunta_Desarrollo WHERE Pregunta_Enunciado LIKE '" + enunciado + "'";
                        Statement stPreguntasDesarrollo = conexion.createStatement();
                        ResultSet rsPreguntasDesarrollo = stPreguntasDesarrollo.executeQuery(sqlPreguntasDesarrollo);
//                        System.out.println("- Preguntas: ");
                        while (rsPreguntasDesarrollo.next()){
                            String respuestaCorrecta = rsPreguntasDesarrollo.getString("respuesta");
                            PreguntaDesarrollo pregunta = new PreguntaDesarrollo(enunciado, respuestaCorrecta, false);
                            preguntas.add(pregunta);
                            System.out.println(" * "+ enunciado + ": " + respuestaCorrecta);
                        }

                    } else if (tipoPregunta == 2){
                        String sqlPreguntasDesarrollo = "SELECT * FROM Pregunta_Opcion_Multiple WHERE Pregunta_Enunciado LIKE '" + enunciado + "'";
                        Statement stPreguntasOpcionMultiple = conexion.createStatement();
                        ResultSet rsPreguntasOpcionMultiple = stPreguntasOpcionMultiple.executeQuery(sqlPreguntasDesarrollo);
//                        System.out.println("- Preguntas: ");
                        while (rsPreguntasOpcionMultiple.next()){
                            String respuestaCorrecta = rsPreguntasOpcionMultiple.getString("respuesta");
                            PreguntaDesarrollo pregunta = new PreguntaDesarrollo(enunciado, respuestaCorrecta, false);
                            preguntas.add(pregunta);
                            System.out.println(" * "+ enunciado + ": " + respuestaCorrecta);
                        }
                        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple(enunciado, new ArrayList<Opcion>());
                        preguntas.add(pregunta);
                    }
                }
                tests.add(new Test(nombre, descripcion, categoria, preguntas));
            }

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
    }
}
