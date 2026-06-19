package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://172.20.10.2:3306/liberty";
    private static final String USER = "liberty";
    private static final String PASSWORD = "12345";

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
