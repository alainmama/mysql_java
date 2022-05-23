package com.sic;

import java.sql.*;
import java.io.FileWriter;

public class App {
    public static void main(String[] args) {
        //Try-Catch para manejo de errores
        try {
            // Generar un nuevo archivo csv 
            FileWriter csvWriter = new FileWriter("archivo.csv"); 
            //Se crea la cabecera que contiene los nombres de las columnas 
            //Que se establecieron al crear la tabla 
            csvWriter.append("Id,Nombre,Apellido,Matricula\n"); 
            //Llamada a la libreria mysql conector
            Class.forName("com.mysql.cj.jdbc.Driver");
            //Nueva conexión                           conexión a localhost |   nombre de bd | ususario mysql | contraseña  
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/datos", "alainmama", "drogba10");
            //Nuevo statement para llamada de datos
            Statement stmt = con.createStatement();
            //Creación de Query | llamada a todos los datos de la tabla alumnos
            ResultSet rs = stmt.executeQuery("select * from alumnos");
            //Ciclo de todos los elementos obtenidos por el query
            while (rs.next())
                //Impresión de los valores
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+ " " + rs.getString(4));
            
            //Estos valores deben coincidir con los tipos de datos que se asignaron durante la creación de la tabla 
            stmt.executeUpdate("INSERT INTO alumnos VALUES(1,'Santiago','López','20524700')");
            rs = stmt.executeQuery("select * from alumnos");

            System.out.println("Se agrego una nueva línea");
            //Ciclo de todos los elementos obtenidos por el query
            while (rs.next())
                //Impresión de los valores
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+ " " + rs.getString(4));
            //Borra línea de la tabla
            stmt.executeUpdate("DELETE FROM alumnos WHERE id = 0;");
            rs = stmt.executeQuery("select * from alumnos");
            //Imprime tabla
            System.out.println("Se elimino una nueva línea");
            //Ciclo de todos los elementos obtenidos por el query
            while (rs.next()){
                //Impresión de los valores
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+ " " + rs.getString(4));
                //Se guardan los valores obtenidos por el query en una línea nueva del archivo 
                csvWriter.append(String.valueOf(rs.getInt(1))+","+String.valueOf(rs.getString(2))+","+String.valueOf(rs.getString(3))+","+String.valueOf(rs.getString(4))+'\n');
            }
               
            //Cerrar archivo csv 
            csvWriter.flush(); 
            csvWriter.close();
            
            
                    //Se cierra la conexión 
            con.close();
        } catch (Exception e) {
            //Imprimir errores 
            System.out.println(e);
        }
    }

}
