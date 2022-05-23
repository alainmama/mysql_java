package com.sic;

import java.sql.*;

public class App {
    public static void main(String[] args) {
        //Try-Catch para manejo de errores
        try {
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
            stmt.executeUpdate("INSERT INTO alumnos VALUES(2,'Santiago','López','20524700')");
            rs = stmt.executeQuery("select * from alumnos");

            System.out.println("Se agrego una nueva línea");
            //Ciclo de todos los elementos obtenidos por el query
            while (rs.next())
                //Impresión de los valores
                System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+ " " + rs.getString(4));
                stmt.executeUpdate("DELETE FROM alumnos WHERE id = 0;");
                rs = stmt.executeQuery("select * from alumnos");
    
                System.out.println("Se elimino una nueva línea");
                //Ciclo de todos los elementos obtenidos por el query
                while (rs.next())
                    //Impresión de los valores
                    System.out.println(rs.getInt(1) + "  " + rs.getString(2) + "  " + rs.getString(3)+ " " + rs.getString(4));
            //Se cierra la conexión 
            con.close();
        } catch (Exception e) {
            //Imprimir errores 
            System.out.println(e);
        }
    }

}
