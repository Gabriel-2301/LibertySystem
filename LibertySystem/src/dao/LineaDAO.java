package dao;

import conexion.Conexion;
import static conexion.Conexion.conectar;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class LineaDAO {

    // =========================
    // MOSTRAR DATOS
    // =========================
    public DefaultTableModel mostrarDatos() {

        String[] columnas = {
            "Numero", "Estado", "Fecha_Ultimo_Estado", "Municipio", "Cliente", "Servicio"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String sql = "SELECT "
                + "l.numero, "
                + "e.nombre AS estado, "
                + "l.fechas_ultimo_estado, "
                + "m.nombre AS municipio, "
                + "c.nombre AS cliente, "
                + "s.nombre AS servicio "
                + "FROM lineas l "
                + "LEFT JOIN estados e ON l.estado_id = e.id "
                + "LEFT JOIN municipios m ON l.municipio_id = m.id "
                + "LEFT JOIN clientes c ON l.cliente_id = c.id "
                + "LEFT JOIN servicios s ON l.servicio_id = s.id "
                + "ORDER BY l.id ASC";

        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                modelo.addRow(new Object[]{
                    rs.getString("numero"),
                    rs.getString("estado"),
                    rs.getString("fechas_ultimo_estado"),
                    rs.getString("municipio"),
                    rs.getString("cliente"),
                    rs.getString("servicio")
                });
            }

        } catch (Exception e) {
            System.out.println("Error mostrarDatos: " + e);
        }

        return modelo;
    }

    //BUSCAR
    public DefaultTableModel buscarNumeroOCliente(String texto) {

        String[] columnas = {
            "Numero", "Estado", "Fecha_Ultimo_Estado", "Municipio", "Cliente", "Servicio"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String sql
                = "SELECT "
                + "l.numero, "
                + "e.nombre AS estado, "
                + "l.fechas_ultimo_estado, "
                + "m.nombre AS municipio, "
                + "c.nombre AS cliente, "
                + "s.nombre AS servicio "
                + "FROM lineas l "
                + "LEFT JOIN estados e ON l.estado_id=e.id "
                + "LEFT JOIN municipios m ON l.municipio_id=m.id "
                + "LEFT JOIN clientes c ON l.cliente_id=c.id "
                + "LEFT JOIN servicios s ON l.servicio_id=s.id "
                + "WHERE l.numero LIKE ? "
                + "OR LOWER(c.nombre) LIKE LOWER(?) "
                + "ORDER BY l.id ASC";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            String filtro = "%" + texto.trim() + "%";

            ps.setString(1, filtro);
            ps.setString(2, filtro);

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {

                modelo.addRow(new Object[]{
                    rs.getString("numero"),
                    rs.getString("estado"),
                    rs.getString("fechas_ultimo_estado"),
                    rs.getString("municipio"),
                    rs.getString("cliente"),
                    rs.getString("servicio")
                });
            }

        } catch (Exception e) {
            System.out.println("Error buscar: " + e);
        }

        return modelo;
    }

    //FILTRO
    public DefaultTableModel filtrarLineasFlexible(
            int estadoIndex,
            int servicioIndex,
            int municipioIndex,
            String estado,
            String servicio,
            String municipio,
            Integer cantidad) {

        String[] columnas = {
            "Numero", "Estado", "Fecha_Ultimo_Estado", "Municipio", "Cliente", "Servicio"
        };

        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        StringBuilder sql = new StringBuilder();

        sql.append("SELECT ")
                .append("l.numero, ")
                .append("COALESCE(e.nombre, 'SIN ESTADO') AS estado, ")
                .append("l.fechas_ultimo_estado, ")
                .append("COALESCE(m.nombre, 'SIN MUNICIPIO') AS municipio, ")
                .append("COALESCE(c.nombre, 'SIN CLIENTE') AS cliente, ")
                .append("COALESCE(s.nombre, 'SIN SERVICIO') AS servicio ")
                .append("FROM lineas l ")
                .append("LEFT JOIN estados e ON l.estado_id=e.id ")
                .append("LEFT JOIN municipios m ON l.municipio_id=m.id ")
                .append("LEFT JOIN clientes c ON l.cliente_id=c.id ")
                .append("LEFT JOIN servicios s ON l.servicio_id=s.id ")
                .append("WHERE 1=1 ");

        if (estadoIndex > 0) {
            sql.append("AND e.nombre='").append(estado).append("' ");
        }

        if (servicioIndex > 0) {
            sql.append("AND s.nombre='").append(servicio).append("' ");
        }

        if (municipioIndex > 0) {
            sql.append("AND m.nombre='").append(municipio).append("' ");
        }

        sql.append("ORDER BY l.id ASC ");

        if (cantidad != null && cantidad > 0) {
            sql.append("LIMIT ").append(cantidad);
        }

        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql.toString())) {

            while (rs.next()) {

                modelo.addRow(new Object[]{
                    rs.getString("numero"),
                    rs.getString("estado"),
                    rs.getString("fechas_ultimo_estado"),
                    rs.getString("municipio"),
                    rs.getString("cliente"),
                    rs.getString("servicio")
                });
            }

        } catch (Exception e) {
            System.out.println("Error filtro: " + e);
        }

        return modelo;
    }

    // MUNICIPIOS
    public void cargarMunicipios(javax.swing.JComboBox<String> combo) {

        try {

            if (combo == null) {
                return;
            }

            Connection con = Conexion.conectar();
            if (con == null) {
                return;
            }

            String sql = "SELECT nombre FROM municipios ORDER BY nombre ASC";

            Statement st = con.createStatement();
            ResultSet rs = st.executeQuery(sql);

            combo.removeAllItems();
            combo.addItem("Seleccionar Municipio");

            while (rs.next()) {
                combo.addItem(rs.getString("nombre"));
            }

            rs.close();
            st.close();
            con.close();

        } catch (Exception e) {
            System.out.println("Error municipios: " + e);
        }
    }

    // INSERTAR (SIN CAMBIOS NECESARIOS)
    public int insertarLinea(String numero,
            String estado,
            String fecha,
            String municipio,
            String cliente,
            String servicio) {

        try (Connection con = Conexion.conectar()) {

            int estadoId = obtenerId(con, "estados", estado);
            int municipioId = obtenerId(con, "municipios", municipio);
            int clienteId = obtenerOInsertarCliente(con, cliente);
            int servicioId = obtenerId(con, "servicios", servicio);

            String sql = "INSERT INTO lineas " + "(numero,  estado_id, fechas_ultimo_estado, municipio_id, cliente_id, servicio_id) " + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, numero);
            ps.setInt(2, estadoId);
            ps.setString(3, fecha);
            ps.setInt(4, municipioId);
            ps.setInt(5, clienteId);
            ps.setInt(6, servicioId);

            ps.executeUpdate();

            return 1;

        } catch (java.sql.SQLIntegrityConstraintViolationException e) {

            System.out.println("Duplicado: " + e.getMessage());
            return 0; // duplicado

        } catch (Exception e) {

            System.out.println("Error insertar: " + e);
            return -1; // error
        }
    }

    // HELPERS
    public int obtenerId(Connection con, String tabla, String nombre) throws SQLException {

        String sql = "SELECT id FROM " + tabla + " WHERE nombre=?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombre);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        return -1;
    }

    public int obtenerOInsertarCliente(Connection con, String cliente) throws SQLException {

        String sql = "SELECT id FROM clientes WHERE LOWER(nombre)=LOWER(?)";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, cliente);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt(1);
        }

        String insert = "INSERT INTO clientes(nombre) VALUES(?)";

        PreparedStatement ps2 = con.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
        ps2.setString(1, cliente);
        ps2.executeUpdate();

        ResultSet gen = ps2.getGeneratedKeys();

        if (gen.next()) {
            return gen.getInt(1);
        }

        throw new SQLException("Error cliente");
    }

    // ELIMINAR
    public boolean eliminarLinea(String numero) {

        String sql = "DELETE FROM lineas WHERE numero=?";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numero);
            ps.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error eliminar: " + e);
            return false;
        }
    }

    public void registrarHistorialCompleto(
            String numero,
            String estadoAnt, String estadoNew,
            String municipioAnt, String municipioNew,
            String clienteAnt, String clienteNew,
            String servicioAnt, String servicioNew
    ) {

        String sql
                = "INSERT INTO historial_lineas (numero, estado_anterior, estado_nuevo, "
                + "municipio_anterior, municipio_nuevo, "
                + "cliente_anterior, cliente_nuevo, "
                + "servicio_anterior, servicio_nuevo) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?) "
                + "ON DUPLICATE KEY UPDATE "
                + "estado_anterior=VALUES(estado_anterior), "
                + "estado_nuevo=VALUES(estado_nuevo), "
                + "municipio_anterior=VALUES(municipio_anterior), "
                + "municipio_nuevo=VALUES(municipio_nuevo), "
                + "cliente_anterior=VALUES(cliente_anterior), "
                + "cliente_nuevo=VALUES(cliente_nuevo), "
                + "servicio_anterior=VALUES(servicio_anterior), "
                + "servicio_nuevo=VALUES(servicio_nuevo), "
                + "fecha=NOW()";

        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, numero);
            ps.setString(2, estadoAnt);
            ps.setString(3, estadoNew);
            ps.setString(4, municipioAnt);
            ps.setString(5, municipioNew);
            ps.setString(6, clienteAnt);
            ps.setString(7, clienteNew);
            ps.setString(8, servicioAnt);
            ps.setString(9, servicioNew);

            ps.executeUpdate();

        } catch (Exception e) {
            System.out.println("Error historial upsert: " + e);
        }
    }

    public int contarLineasTotales() {

        String sql = "SELECT COUNT(*) FROM lineas";

        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            if (rs.next()) {
                return rs.getInt(1);
            }

        } catch (Exception e) {
            System.out.println("Error contarLineasTotales: " + e);
        }

        return 0;
    }

    public int obtenerIdPublic(Connection con, String tabla, String nombre) throws SQLException {
        return obtenerId(con, tabla, nombre);
    }

    public int obtenerClientePublic(Connection con, String cliente) throws SQLException {
        return obtenerOInsertarCliente(con, cliente);
    }

}
