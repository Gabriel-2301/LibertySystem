package dao;

import conexion.Conexion;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class LineaDAO {

   
    // =========================
    // MOSTRAR DATOS (YA LO TENÍA)
    // =========================
    public DefaultTableModel mostrarDatos() {

        String[] columnas = {
            "Numero", "Estado", "Fecha_Ultimo_Estado",
            "Municipio", "Cliente", "Servicio"
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
                + "LEFT JOIN servicios s ON l.servicio_id = s.id";

        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                String[] fila = new String[6];

                fila[0] = rs.getString("numero");
                fila[1] = rs.getString("estado");
                fila[2] = rs.getString("fechas_ultimo_estado");
                fila[3] = rs.getString("municipio");
                fila[4] = rs.getString("cliente");
                fila[5] = rs.getString("servicio");

                modelo.addRow(fila);
            }

        } catch (SQLException e) {
            System.out.println("Error al cargar datos: " + e);
        }

        return modelo;
    }
    
    public DefaultTableModel filtrarLineasFlexible(String estado,
                                               String servicio,
                                               int cantidad) {

    String[] columnas = {
        "Numero",
        "Estado",
        "Fecha_Ultimo_Estado",
        "Municipio",
        "Cliente",
        "Servicio"
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
       .append("LEFT JOIN estados e ON l.estado_id = e.id ")
       .append("LEFT JOIN municipios m ON l.municipio_id = m.id ")
       .append("LEFT JOIN clientes c ON l.cliente_id = c.id ")
       .append("LEFT JOIN servicios s ON l.servicio_id = s.id ")
       .append("WHERE 1=1 ");

    // filtro estado (solo si no es "Seleccionar")
    if (estado != null && !estado.equals("Seleccionar Estado")) {
        sql.append("AND e.nombre = '").append(estado).append("' ");
    }

    // filtro servicio (solo si no es "Seleccionar")
    if (servicio != null && !servicio.equals("Seleccionar Servicio")) {
        sql.append("AND s.nombre = '").append(servicio).append("' ");
    }

    sql.append("ORDER BY l.numero ASC ")
       .append("LIMIT ").append(cantidad);

    try (Connection con = Conexion.conectar();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql.toString())) {

        while (rs.next()) {

            String[] fila = new String[6];

            fila[0] = rs.getString("numero");
            fila[1] = rs.getString("estado");
            fila[2] = rs.getString("fechas_ultimo_estado");
            fila[3] = rs.getString("municipio");
            fila[4] = rs.getString("cliente");
            fila[5] = rs.getString("servicio");

            modelo.addRow(fila);
        }

    } catch (SQLException e) {
        System.out.println("Error al filtrar: " + e);
    }

    return modelo;
}
    

    // =========================
    // INSERTAR DATOS (NUEVO)
    // =========================
    public boolean insertarLinea(String numero,
                                 String estado,
                                 String fecha,
                                 String municipio,
                                 String cliente,
                                 String servicio) {

        Connection con = null;

        try {
            con = Conexion.conectar();

            // convertir nombres a IDs
            int estadoId = obtenerId(con, "estados", estado);
            int municipioId = obtenerId(con, "municipios", municipio);
            int clienteId = obtenerId(con, "clientes", cliente);
            int servicioId = obtenerId(con, "servicios", servicio);

            if (estadoId == -1) {
    throw new SQLException("Estado no encontrado: " + estado);
}

if (municipioId == -1) {
    throw new SQLException("Municipio no encontrado: " + municipio);
}

if (clienteId == -1) {
    throw new SQLException("Cliente no encontrado: " + cliente);
}

if (servicioId == -1) {
    throw new SQLException("Servicio no encontrado: " + servicio);
}
            
            String sql = "INSERT INTO lineas "
                    + "(numero, estado_id, fechas_ultimo_estado, municipio_id, cliente_id, servicio_id) "
                    + "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement ps = con.prepareStatement(sql);

            ps.setString(1, numero);
            ps.setInt(2, estadoId);
            ps.setString(3, fecha);
            ps.setInt(4, municipioId);
            ps.setInt(5, clienteId);
            ps.setInt(6, servicioId);

            ps.executeUpdate();

            return true;

        } catch (Exception e) {
            System.out.println("Error al insertar: " + e);
            return false;
        }
    }

    // =========================
    // MÉTODO AUXILIAR: OBTENER ID
    // =========================
    private int obtenerId(Connection con, String tabla, String nombre) throws SQLException {

        String sql = "SELECT id FROM " + tabla + " WHERE nombre = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombre);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }

        return -1; // si no encuentra
    }
    
    public boolean eliminarLinea(String numero) {

    String sql = "DELETE FROM lineas WHERE numero = ?";

    try (Connection con = Conexion.conectar();
         PreparedStatement ps = con.prepareStatement(sql)) {

        ps.setString(1, numero);

        ps.executeUpdate();
        return true;

    } catch (Exception e) {
        System.out.println("Error al eliminar: " + e);
        return false;
    }
  }
    
    public void cargarMunicipios(javax.swing.JComboBox<String> combo) {

    String sql = "SELECT nombre FROM municipios ORDER BY nombre ASC";

    try (Connection con = Conexion.conectar();
         Statement st = con.createStatement();
         ResultSet rs = st.executeQuery(sql)) {

        combo.removeAllItems();

        combo.addItem("Seleccionar Municipio");

        while (rs.next()) {

            String municipio = rs.getString("nombre");

            combo.addItem(municipio);
        }

    } catch (SQLException e) {

        System.out.println(
                "Error cargando municipios: " + e
        );
    }
  }
}

