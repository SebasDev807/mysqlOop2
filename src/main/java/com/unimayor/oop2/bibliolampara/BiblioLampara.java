package com.unimayor.oop2.bibliolampara;

import com.unimayor.oop2.bibliolampara.modelo.Conexion;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author sebas
 */
public class BiblioLampara {

    public static void main(String[] args) {
        Conexion conexion = new Conexion();
//        insertarLibro("TerceraPruebaJava", "prueba", 250, 11);
//        eliminarLibro(1);
        mostrarLibros();
    }

    /**
     * Inserta un nuevo libro en la base de datos
     * 
     * @Param nombre El nombre del libro
     * @Param genero El genero del libro
     * @Param noPaginas El numero de paginas
     * @Param nombre El numero de copias
     */
    public static void insertarLibro(String nombre, String genero, int noPaginas, int copias) {

        try {
            var conexion = new Conexion();
            var myConnection = conexion.conectar();
            String query = "INSERT INTO libros (nombre, genero, numero_paginas, copias)"
                    + "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = myConnection.prepareStatement(query);
            statement.setString(1, nombre);
            statement.setString(2, genero);
            statement.setInt(3, noPaginas);
            statement.setInt(4, copias);
            statement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

     /**
     * Elimina un libro en la base de datos
     * 
     * @Param idLibro identificador del libro para eliminar
     */
    public static void eliminarLibro(int idLibro) {
        var conexion = new Conexion();
        var myConnection = conexion.conectar();
        String Query = "DELETE FROM libros WHERE id_libro = ?";
        try {
            PreparedStatement statement = myConnection.prepareStatement(Query);
            statement.setInt(1, idLibro);
            int rowsDeleted = statement.executeUpdate();
            JOptionPane.showMessageDialog(null, "Se eliminaron " + rowsDeleted + " filas");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }

    }

    /**
    *Muestra los registros de la base de datos
    */
    public static void mostrarLibros() {
        var conexion = new Conexion();
        var myConnection = conexion.conectar();
        String query = "SELECT * FROM libros";
        try {
            PreparedStatement statement = myConnection.prepareStatement(query);
            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                int id = resultSet.getInt("id_libro");
                String nombre = resultSet.getString("nombre");
                String genero = resultSet.getString("genero");
                int noPaginas = resultSet.getInt("numero_paginas");
                int copias = resultSet.getInt("copias");

                System.out.println("id_libro: " + id + ""
                        + "\n nombre : " + nombre
                        + "\n genero : " + genero
                        + "\n numero de paginas : " + noPaginas
                        + "\n numero de copias : " + copias);
            }
        } catch (Exception e) {
        }
    }

}
