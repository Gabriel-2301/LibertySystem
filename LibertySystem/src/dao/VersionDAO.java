package dao;

import conexion.Conexion;
import java.sql.*;

public class VersionDAO {

    public static String obtenerVersion() {

        String version = "";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(
                "SELECT version FROM system_version WHERE id=1"); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                version = rs.getString("version");
            }

        } catch (Exception e) {
            System.out.println("Error version: " + e);
        }

        return version;
    }

    public static String obtenerURL() {

        String url = "";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(
                "SELECT url FROM system_version WHERE id=1"); ResultSet rs = ps.executeQuery()) {

            if (rs.next()) {
                url = rs.getString("url");
            }

        } catch (Exception e) {
            System.out.println("Error url: " + e);
        }

        return url;
    }
}
