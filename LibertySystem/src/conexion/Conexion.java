package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {

    private static final String URL = "jdbc:mysql://172.20.10.2:3306/liberty";
    private static final String USER = "liberty";
    private static final String PASSWORD = "12345";

    public static Connection conectar() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            System.out.println("Error conexion: " + e);
            return null;
        }
    }

    public static boolean probarConexion() { //prueba de conexión
        try (Connection con = conectar()) {
            return con != null && !con.isClosed();
        } catch (Exception e) {
            return false;
        }
    }
}
