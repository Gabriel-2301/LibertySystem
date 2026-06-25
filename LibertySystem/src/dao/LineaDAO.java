package dao;

import conexion.Conexion;
import java.sql.*;
import java.util.List;
import javax.swing.table.DefaultTableModel;

public class LineaDAO {

    private String ultimoTexto = "";
    private DefaultTableModel cacheModelo = null;

    public DefaultTableModel mostrarDatos() { // MOSTRAR DATOS
        String[] columnas = {
            "Numero", "Estado", "Fecha_Ultimo_Estado", "Municipio", "Cliente", "Servicio"
        };
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        String sql
                = "SELECT "
                + "l.numero, "
                + "COALESCE(e.nombre, 'Sin Estado') AS estado, "
                + "l.fechas_ultimo_estado, "
                + "COALESCE(m.nombre, 'SIN MUNICIPIO') AS municipio, "
                + "COALESCE(c.nombre, 'SIN CLIENTE') AS cliente, "
                + "COALESCE(s.nombre, 'Sin Servicio') AS servicio "
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

    public DefaultTableModel buscarNumeroOCliente(String texto) {
        if (texto != null && texto.equals(ultimoTexto) && cacheModelo != null) {
            return cacheModelo;
        }
        String[] columnas = {
            "Numero", "Estado", "Fecha_Ultimo_Estado",
            "Municipio", "Cliente", "Servicio"
        };
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        String sql
                = "SELECT l.numero, "
                + "COALESCE(e.nombre,'Sin Estado') AS estado, "
                + "l.fechas_ultimo_estado, "
                + "COALESCE(m.nombre,'SIN MUNICIPIO') AS municipio, "
                + "COALESCE(c.nombre,'SIN CLIENTE') AS cliente, "
                + "COALESCE(s.nombre,'Sin Servicio') AS servicio "
                + "FROM lineas l "
                + "LEFT JOIN estados e ON l.estado_id = e.id "
                + "LEFT JOIN municipios m ON l.municipio_id = m.id "
                + "LEFT JOIN clientes c ON l.cliente_id = c.id "
                + "LEFT JOIN servicios s ON l.servicio_id = s.id "
                + "WHERE l.numero LIKE ? "
                + "OR c.nombre LIKE ? "
                + "ORDER BY l.id ASC";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            String filtro = texto + "%";
            ps.setString(1, texto + "%");
            ps.setString(2, texto + "%");
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
        ultimoTexto = texto;
        cacheModelo = modelo;
        return modelo;
    }

    public DefaultTableModel filtrarLineasFlexible(int estadoIndex, int servicioIndex, int municipioIndex, String estado, String servicio, String municipio, Integer cantidad) { //FILTRO
        String[] columnas = {
            "Numero", "Estado", "Fecha_Ultimo_Estado", "Municipio", "Cliente", "Servicio"
        };
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);
        StringBuilder sql = new StringBuilder();
        sql.append("SELECT ")
                .append("l.numero, ")
                .append("COALESCE(e.nombre, 'Sin Estado') AS estado, ")
                .append("l.fechas_ultimo_estado, ")
                .append("COALESCE(m.nombre, 'SIN MUNICIPIO') AS municipio, ")
                .append("COALESCE(c.nombre, 'SIN CLIENTE') AS cliente, ")
                .append("COALESCE(s.nombre, 'Sin Servicio') AS servicio ")
                .append("FROM lineas l ")
                .append("LEFT JOIN estados e ON l.estado_id = e.id ")
                .append("LEFT JOIN municipios m ON l.municipio_id = m.id ")
                .append("LEFT JOIN clientes c ON l.cliente_id = c.id ")
                .append("LEFT JOIN servicios s ON l.servicio_id = s.id ")
                .append("WHERE 1=1 ");
        if (estadoIndex > 0 && estado != null) {
            sql.append(" AND e.nombre = '").append(estado).append("' ");
        }
        if (servicioIndex > 0 && servicio != null) {
            sql.append(" AND s.nombre = '").append(servicio).append("' ");
        }
        if (municipioIndex > 0 && municipio != null) {
            sql.append(" AND m.nombre = '").append(municipio).append("' ");
        }
        sql.append(" ORDER BY l.id ASC ");

        if (cantidad != null && cantidad > 0) {
            sql.append(" LIMIT ").append(cantidad);
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

    public void cargarMunicipios(javax.swing.JComboBox<String> combo) { // MUNICIPIOS
        try {
            if (combo == null) {
                return;
            }
            try (Connection con = Conexion.conectar()) {
                if (con == null) {
                    return;
                }
                String sql = "SELECT nombre FROM municipios ORDER BY nombre ASC";
                try (Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
                    combo.removeAllItems();
                    combo.addItem("Seleccionar Municipio");
                    while (rs.next()) {
                        combo.addItem(rs.getString("nombre"));
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error municipios: " + e);
        }
    }

    public int insertarLinea(String numero, String estado, String fecha, String municipio, String cliente, String servicio) { // INSERTAR (SIN CAMBIOS NECESARIOS)
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

    public int obtenerId(Connection con, String tabla, String nombre) throws SQLException { // HELPERS
        if (nombre == null) {
            return 0;
        }
        nombre = nombre.trim();
        if (nombre.isEmpty()) {
            return 0;
        }
        String sql = "SELECT id FROM " + tabla + " WHERE LOWER(TRIM(nombre)) = LOWER(TRIM(?))";
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombre);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            return rs.getInt("id");
        }
        return 0;
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

    public boolean eliminarLinea(String numero) { // ELIMINAR
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

    public void registrarHistorialCompleto(String numero, String estadoAnt, String estadoNew, String municipioAnt, String municipioNew, String clienteAnt, String clienteNew, String servicioAnt, String servicioNew) {
        String sql = "UPDATE historial_lineas SET " + "estado_anterior=?, estado_nuevo=?, " + "municipio_anterior=?, municipio_nuevo=?, " + "cliente_anterior=?, cliente_nuevo=?, " + "servicio_anterior=?, servicio_nuevo=?, fecha=NOW() " + "WHERE numero=?";
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setString(1, estadoAnt);
            ps.setString(2, estadoNew);
            ps.setString(3, municipioAnt);
            ps.setString(4, municipioNew);
            ps.setString(5, clienteAnt);
            ps.setString(6, clienteNew);
            ps.setString(7, servicioAnt);
            ps.setString(8, servicioNew);
            ps.setString(9, numero);
            int rows = ps.executeUpdate();
            if (rows == 0) { // si no existe aún, lo creamos
                String insert = "INSERT INTO historial_lineas (" + "numero, estado_anterior, estado_nuevo, " + "municipio_anterior, municipio_nuevo, " + "cliente_anterior, cliente_nuevo, " + "servicio_anterior, servicio_nuevo, fecha" + ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, NOW())";
                try (PreparedStatement ps2 = con.prepareStatement(insert)) {
                    ps2.setString(1, numero);
                    ps2.setString(2, estadoAnt);
                    ps2.setString(3, estadoNew);
                    ps2.setString(4, municipioAnt);
                    ps2.setString(5, municipioNew);
                    ps2.setString(6, clienteAnt);
                    ps2.setString(7, clienteNew);
                    ps2.setString(8, servicioAnt);
                    ps2.setString(9, servicioNew);
                    ps2.executeUpdate();
                }
            }
        } catch (Exception e) {
            System.out.println("Error historial: " + e);
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

    public DefaultTableModel mostrarDatosOrdenadosPorNumero() {
        String[] columnas = {
            "Numero", "Estado", "Fecha_Ultimo_Estado", "Municipio", "Cliente", "Servicio"
        };
        DefaultTableModel modelo = new DefaultTableModel(null, columnas);

        String sql
                = "SELECT "
                + "l.numero, "
                + "COALESCE(e.nombre, 'Sin Estado') AS estado, "
                + "l.fechas_ultimo_estado, "
                + "COALESCE(m.nombre, 'SIN MUNICIPIO') AS municipio, "
                + "COALESCE(c.nombre, 'SIN CLIENTE') AS cliente, "
                + "COALESCE(s.nombre, 'Sin Servicio') AS servicio "
                + "FROM lineas l "
                + "LEFT JOIN estados e ON l.estado_id = e.id "
                + "LEFT JOIN municipios m ON l.municipio_id = m.id "
                + "LEFT JOIN clientes c ON l.cliente_id = c.id "
                + "LEFT JOIN servicios s ON l.servicio_id = s.id "
                + "ORDER BY CAST(l.numero AS UNSIGNED) ASC";
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
            System.out.println("Error ordenar: " + e);
        }
        return modelo;
    }

    public boolean eliminarLineasMasivo(List<String> numeros) {
        if (numeros == null || numeros.isEmpty()) {
            return false;
        }
        StringBuilder sql = new StringBuilder("DELETE FROM lineas WHERE numero IN (");
        for (int i = 0; i < numeros.size(); i++) {
            sql.append("?");
            if (i < numeros.size() - 1) {
                sql.append(",");
            }
        }
        sql.append(")");
        try (Connection con = Conexion.conectar(); PreparedStatement ps = con.prepareStatement(sql.toString())) {
            for (int i = 0; i < numeros.size(); i++) {
                ps.setString(i + 1, numeros.get(i));
            }
            ps.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error eliminar masivo: " + e);
            return false;
        }
    }

    public int obtenerIdPublic(Connection con, String tabla, String nombre) throws SQLException {
        return obtenerId(con, tabla, nombre);
    }

    public int obtenerClientePublic(Connection con, String cliente) throws SQLException {
        return obtenerOInsertarCliente(con, cliente);
    }

}
