package dao;

import conexion.Conexion;
import java.sql.*;
import javax.swing.table.DefaultTableModel;

public class LineaDAO {

    // =========================
    // MOSTRAR DATOS
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

    // =========================
    // FILTRO FLEXIBLE (ACTUALIZADO)
    // =========================
    public DefaultTableModel filtrarLineasFlexible(
        int estadoIndex,
        int servicioIndex,
        int municipioIndex,
        String estado,
        String servicio,
        String municipio,
        Integer cantidad) {

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

    // =========================
    // SOLO APLICAR FILTROS REALES
    // =========================

    if (estadoIndex > 0) {
        sql.append("AND e.nombre = '").append(estado).append("' ");
    }

    if (servicioIndex > 0) {
        sql.append("AND s.nombre = '").append(servicio).append("' ");
    }

    if (municipioIndex > 0) {
        sql.append("AND m.nombre = '").append(municipio).append("' ");
    }

    // si no hay ningún filtro REAL → bloquear resultado total
    if (estadoIndex == 0 && servicioIndex == 0 && municipioIndex == 0) {
        return modelo; // vacío
    }

    sql.append("ORDER BY l.id ASC ");

    if (cantidad != null && cantidad > 0) {
        sql.append("LIMIT ").append(cantidad);
    }

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
        System.out.println("Error: " + e);
    }

    return modelo;
}

    // =========================
    // INSERTAR LINEA (igual que el tuyo)
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

            int estadoId = obtenerId(con, "estados", estado);
            int municipioId = obtenerId(con, "municipios", municipio);
            int clienteId = obtenerOInsertarCliente(con, cliente);
            int servicioId = obtenerId(con, "servicios", servicio);

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
    // AUXILIAR ID
    // =========================
    private int obtenerId(Connection con, String tabla, String nombre) throws SQLException {

        String sql = "SELECT id FROM " + tabla + " WHERE nombre = ?";

        PreparedStatement ps = con.prepareStatement(sql);
        ps.setString(1, nombre);

        ResultSet rs = ps.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }

        return -1;
    }

    // =========================
    // CLIENTE
    // =========================
    private int obtenerOInsertarCliente(Connection con, String cliente) throws SQLException {

        String sqlBuscar = "SELECT id FROM clientes WHERE TRIM(LOWER(nombre)) = TRIM(LOWER(?))";

        PreparedStatement psBuscar = con.prepareStatement(sqlBuscar);
        psBuscar.setString(1, cliente);

        ResultSet rs = psBuscar.executeQuery();

        if (rs.next()) {
            return rs.getInt("id");
        }

        String sqlInsertar = "INSERT INTO clientes(nombre) VALUES(?)";

        PreparedStatement psInsertar = con.prepareStatement(
                sqlInsertar,
                Statement.RETURN_GENERATED_KEYS
        );

        psInsertar.setString(1, cliente);
        psInsertar.executeUpdate();

        ResultSet generado = psInsertar.getGeneratedKeys();

        if (generado.next()) {
            return generado.getInt(1);
        }

        throw new SQLException("No se pudo insertar cliente");
    }

    // =========================
    // ELIMINAR
    // =========================
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

    // =========================
    // CARGAR MUNICIPIOS
    // =========================
    public void cargarMunicipios(javax.swing.JComboBox<String> combo) {

        String sql = "SELECT nombre FROM municipios ORDER BY nombre ASC";

        try (Connection con = Conexion.conectar();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {

            combo.removeAllItems();
            combo.addItem("Seleccionar Municipio");

            while (rs.next()) {
                combo.addItem(rs.getString("nombre"));
            }

        } catch (SQLException e) {
            System.out.println("Error cargando municipios: " + e);
        }
    }
}

