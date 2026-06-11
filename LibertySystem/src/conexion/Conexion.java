package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://localhost:3306/liberty";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public static Connection conectar() {

        Connection con = null;

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            con = DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexion exitosa");

        } catch (ClassNotFoundException | SQLException e) {

            System.out.println("Error conexion: " + e);

        }

        return con;
    }

    // MAIN PARA PROBAR
    public static void main(String[] args) {

        conectar();
    }
}
