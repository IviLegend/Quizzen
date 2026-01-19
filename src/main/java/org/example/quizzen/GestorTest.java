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
                        " - Nombre: " + nombre +
                                "| Descripción: " + descripcion +
                                "| Categoría: " + categoria);
                String sqlPreguntas = "SELECT * FROM Pregunta WHERE Test_id_test LIKE " + idTest;
                Statement stPreguntas = conexion.createStatement();
                ResultSet rsPreguntas = stPreguntas.executeQuery(sqlPreguntas);
                ArrayList<Pregunta> preguntas = new ArrayList<>();
                System.out.println("- Preguntas: ");
                while (rsPreguntas.next()){
                    String enunciado = rsPreguntas.getString("enunciado");
                    boolean esDesarrollo = rsPreguntas.getBoolean("tipo_pregunta");
                    if (esDesarrollo){
                        PreguntaDesarrollo pregunta = new PreguntaDesarrollo(enunciado, new ArrayList<String>(), false);
                        preguntas.add(pregunta);
                    } else {
                        PreguntaOpcionMultiple pregunta = new PreguntaOpcionMultiple(enunciado, new ArrayList<Opcion>());
                        preguntas.add(pregunta);
                    }
                }
                tests.add(new Test(nombre, descripcion, categoria, preguntas));
            }

        } catch (ClassNotFoundException e) {
            System.out.println("No se ha encontrado el driver de MySQL.");
        } catch (SQLException e) {
            System.out.println("Error al conectar con la base de datos.");
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
