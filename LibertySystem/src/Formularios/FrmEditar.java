/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import dao.LineaDAO;
import conexion.Conexion;
import java.awt.Color;
import java.sql.*;
import java.util.ArrayList;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import utilidades.TemaManager;

/**
 *
 * @author gabri
 */
public class FrmEditar extends javax.swing.JFrame {

    /**
     * Creates new form FrmEditar
     *
     */
    public void aplicarTemaOscuro() {
        aplicarColoresPersonalizados();
    }

    ArrayList<String> estadoOriginal = new ArrayList<>();
    ArrayList<String> municipioOriginal = new ArrayList<>();
    ArrayList<String> clienteOriginal = new ArrayList<>();
    ArrayList<String> servicioOriginal = new ArrayList<>();

    private FrmConsulta frmConsulta;
    private DefaultTableModel modelo;
    private java.util.List<String> numerosOriginales = new java.util.ArrayList<>();

    public FrmEditar() {
        initComponents();

        setTitle("Liberty Networks | Panel de Edicion");
        setResizable(false);
        setLocationRelativeTo(null);
        SetImageLabel(jLabel2, "/IMG/Logoliberty.png");
        setIconImage(new ImageIcon(getClass().getResource("/IMG/Iconoliberty.png")).getImage());

        aplicarTema();

    }

    private void aplicarColoresPersonalizados() {

        if (TemaManager.oscuro) {

            BtnGuardar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoguardar.png"));

            BtnLimpiar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconolimpiar.png"));

            LblCantidad.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoeditar.png"));

        } else {

            BtnGuardar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoguardar.png")));

            BtnLimpiar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconolimpiar.png")));

            LblCantidad.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoeditar.png")));
        }
        repaint();
    }

    private void aplicarTema() {
        if (TemaManager.oscuro) {

            BtnGuardar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoguardar.png"));

            BtnLimpiar.setIcon(
                    TemaManager.invertirIcono(getClass(), "/IMG/Iconolimpiar.png"));

            LblCantidad.setIcon(
                    TemaManager.invertirIcono(getClass(), "/IMG/Iconoeditar.png"));

        } else {

            BtnGuardar.setIcon(
                    new ImageIcon(getClass().getResource("/IMG/Iconoguardar.png")));

            BtnLimpiar.setIcon(
                    new ImageIcon(getClass().getResource("/IMG/Iconolimpiar.png")));

            LblCantidad.setIcon(
                    new ImageIcon(getClass().getResource("/IMG/Iconoeditar.png")));
        }

        if (TemaManager.oscuro) {
            jPanel1.setBackground(new java.awt.Color(35, 35, 35));
            jPanel2.setBackground(new java.awt.Color(45, 45, 45));

            jLabel1.setForeground(Color.WHITE);
            LblCantidad.setForeground(Color.WHITE);

            jTableDatosEditados.setBackground(new Color(45, 45, 45));
            jTableDatosEditados.setForeground(Color.WHITE);
            jTableDatosEditados.setGridColor(new Color(70, 70, 70));
            jTableDatosEditados.setSelectionForeground(Color.BLACK);
            jTableDatosEditados.getTableHeader().setBackground(new Color(60, 60, 60));
            jTableDatosEditados.getTableHeader().setForeground(Color.WHITE);

            JButton[] botones = {
                BtnGuardar,
                BtnLimpiar
            };
            for (JButton b : botones) {
                b.setBackground(new Color(55, 55, 55));
                b.setForeground(Color.WHITE);
            }
//    } else {
//        jPanel1.setBackground(new java.awt.Color(35,35,35));
//        jPanel2.setBackground(new java.awt.Color(45,45,45));
//        jLabel1.setForeground(Color.WHITE);
//        jLabel2.setForeground(Color.WHITE);
//        jTableDatosEditados.setBackground(new Color(45,45,45));
//        jTableDatosEditados.setForeground(Color.WHITE);
//        jTableDatosEditados.setGridColor(new Color(70,70,70));
//        jTableDatosEditados.setSelectionForeground(Color.BLACK);
//        jTableDatosEditados.getTableHeader().setBackground(new Color(60,60,60));
//        jTableDatosEditados.getTableHeader().setForeground(Color.WHITE);
//        JButton[] botones = {
//            BtnGuardar,
//            BtnLimpiar
//        };
        }
        repaint();
    }

    public void cargarDatos(FrmConsulta frmConsulta, java.util.List<Object[]> datos) {

        this.frmConsulta = frmConsulta;

        modelo = new DefaultTableModel(new Object[]{"Numero", "Estado", "Fecha", "Municipio", "Cliente", "Servicio"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int col) {
                return col != 2;
            }
        };

        jTableDatosEditados.setModel(modelo);
        numerosOriginales.clear();
        estadoOriginal.clear();
        municipioOriginal.clear();
        clienteOriginal.clear();
        servicioOriginal.clear();
        numerosOriginales.clear();

        for (Object[] fila : datos) {

            Object[] safe = new Object[6];

            for (int i = 0; i < 6; i++) {
                safe[i] = (fila[i] == null) ? "" : fila[i];
            }

            numerosOriginales.add(safe[0].toString());
            estadoOriginal.add(safe[1].toString());
            municipioOriginal.add(safe[3].toString());
            clienteOriginal.add(safe[4].toString());
            servicioOriginal.add(safe[5].toString());
            modelo.addRow(safe);
        }
        LblCantidad.setText("Total de Lineas Seleccionadas para Editar: " + datos.size());

        configurarTabla();

        //VALIDACIONES EN TIEMPO REAL
        modelo.addTableModelListener(e -> {

            int fila = e.getFirstRow();
            int col = e.getColumn();

            if (fila < 0 || col < 0) {
                return;
            }

            Object val = modelo.getValueAt(fila, col);
            if (val == null) {
                return;
            }

            String texto = val.toString();

            //NUMERO
            if (col == 0) {
                if (!texto.matches("\\d{0,8}")) {
                    JOptionPane.showMessageDialog(this, "Máximo 8 dígitos.");
                    modelo.setValueAt(texto.replaceAll("\\D", "").substring(0, Math.min(8, texto.replaceAll("\\D", "").length())), fila, col);
                }
            }

            //CLIENTE
            if (col == 4) {
                if (texto.length() > 50) {
                    JOptionPane.showMessageDialog(this, "Máximo 50 caracteres.");
                    modelo.setValueAt(texto.substring(0, 50), fila, col);
                }
            }
        });
    }

    private void configurarTabla() {

        jTableDatosEditados.getColumnModel().getColumn(0).setPreferredWidth(130);
        jTableDatosEditados.getColumnModel().getColumn(1).setPreferredWidth(130);
        jTableDatosEditados.getColumnModel().getColumn(2).setPreferredWidth(180);
        jTableDatosEditados.getColumnModel().getColumn(3).setPreferredWidth(300);
        jTableDatosEditados.getColumnModel().getColumn(4).setPreferredWidth(400);
        jTableDatosEditados.getColumnModel().getColumn(5).setPreferredWidth(140);
        jTableDatosEditados.setRowHeight(25);
        jTableDatosEditados.getTableHeader().setResizingAllowed(false);
        jTableDatosEditados.getTableHeader().setReorderingAllowed(false);

        //Combo Estado
        JComboBox<String> comboEstado = new JComboBox<>();
        comboEstado.addItem("Libre");
        comboEstado.addItem("Reservado");
        comboEstado.addItem("Cancelado");
        comboEstado.addItem("En Servicio");

        jTableDatosEditados.getColumnModel()
                .getColumn(1)
                .setCellEditor(new DefaultCellEditor(comboEstado));

        JComboBox<String> comboMunicipio = new JComboBox<>();
        comboMunicipio.addItem("Alianza, Valle");
        comboMunicipio.addItem("Amapala, Valle");
        comboMunicipio.addItem("Azacualpa, Santa Barbara");
        comboMunicipio.addItem("Campamento, Olancho");
        comboMunicipio.addItem("Catacamas, Olancho");
        comboMunicipio.addItem("Choloma, Cortes");
        comboMunicipio.addItem("Choluteca, Choluteca");
        comboMunicipio.addItem("Comayagua, Comayagua");
        comboMunicipio.addItem("Copan Ruinas, Copan");
        comboMunicipio.addItem("Cucuyagua, Copan");
        comboMunicipio.addItem("Danli, El Paraiso");
        comboMunicipio.addItem("Dulce Nombre de Culmi, Olancho");
        comboMunicipio.addItem("El Negrito, Yoro");
        comboMunicipio.addItem("El Paraiso, El Paraiso");
        comboMunicipio.addItem("El Porvenir, Francisco Morazan");
        comboMunicipio.addItem("El Progreso, Yoro");
        comboMunicipio.addItem("El Triunfo, Choluteca");
        comboMunicipio.addItem("Florida, Copan");
        comboMunicipio.addItem("Gracias, Lempira");
        comboMunicipio.addItem("Guiamaca, Francisco Morazan");
        comboMunicipio.addItem("Jesus de Otoro, Intibuca");
        comboMunicipio.addItem("Juticalpa, Olancho");
        comboMunicipio.addItem("La Ceiba, Atlantida");
        comboMunicipio.addItem("La Esperanza, Intibuca");
        comboMunicipio.addItem("La Flecha, Santa Barbara");
        comboMunicipio.addItem("La Labor, Ocotepeque");
        comboMunicipio.addItem("La Libertad, Comayagua");
        comboMunicipio.addItem("La Lima, Cortes");
        comboMunicipio.addItem("La Paz, La Paz");
        comboMunicipio.addItem("La Trinidad, Santa Barbara");
        comboMunicipio.addItem("La Union, Copan");
        comboMunicipio.addItem("Langue, Valle");
        comboMunicipio.addItem("Las Vegas, Santa Barbara");
        comboMunicipio.addItem("Lepaera, Lempira");
        comboMunicipio.addItem("Macuelizo, Santa Barbara");
        comboMunicipio.addItem("Marcala, La Paz");
        comboMunicipio.addItem("Monjaras, Marcovia");
        comboMunicipio.addItem("Morazan, Yoro");
        comboMunicipio.addItem("Nacaome, Valle");
        comboMunicipio.addItem("Nueva Arcadia, Copan");
        comboMunicipio.addItem("Ocotepeque, Ocotepeque");
        comboMunicipio.addItem("Olanchito, Yoro");
        comboMunicipio.addItem("Omoa, Cortes");
        comboMunicipio.addItem("Pespire, Choluteca");
        comboMunicipio.addItem("Pimienta, Cortes");
        comboMunicipio.addItem("Potrerillos, Cortes");
        comboMunicipio.addItem("Puerto Cortes, Cortes");
        comboMunicipio.addItem("Puerto Lempira, Gracias a Dios");
        comboMunicipio.addItem("Quimistan, Santa Barbara");
        comboMunicipio.addItem("Roatan, Islas de la Bahia");
        comboMunicipio.addItem("Saba, Colon");
        comboMunicipio.addItem("San Antonio de Oriente, Francisco Morazan");
        comboMunicipio.addItem("San Juan Pueblo, Atlantida");
        comboMunicipio.addItem("San Juan, Intibuca");
        comboMunicipio.addItem("San Lorenzo, Valle");
        comboMunicipio.addItem("San Manuel, Cortes");
        comboMunicipio.addItem("San Marcos de Colon, Choluteca");
        comboMunicipio.addItem("San Marcos, Ocotepeque");
        comboMunicipio.addItem("San Pedro Sula, Cortes");
        comboMunicipio.addItem("Santa Barbara, Santa Barbara");
        comboMunicipio.addItem("Santa Cruz de Yojoa, Cortes");
        comboMunicipio.addItem("Santa Rita, Copan");
        comboMunicipio.addItem("Santa Rosa de Copan, Copan");
        comboMunicipio.addItem("Siguatepeque, Comayagua");
        comboMunicipio.addItem("Talanga, Francisco Morazan");
        comboMunicipio.addItem("Taulabe, Comayagua");
        comboMunicipio.addItem("Tegucigalpa, Francisco Morazan");
        comboMunicipio.addItem("Tela, Atlantida");
        comboMunicipio.addItem("Tocoa, Colon");
        comboMunicipio.addItem("Trojes, El Paraiso");
        comboMunicipio.addItem("Trujillo, Colon");
        comboMunicipio.addItem("Villanueva, Cortes");
        comboMunicipio.addItem("Yoro, Yoro");
        comboMunicipio.addItem("Yuscaran, El Paraiso");

        jTableDatosEditados.getColumnModel()
                .getColumn(3)
                .setCellEditor(new DefaultCellEditor(comboMunicipio));

        //Combo Servicio
        JComboBox<String> comboServicio = new JComboBox<>();
        comboServicio.addItem("MyUC");
        comboServicio.addItem("Bussines Trunk");

        jTableDatosEditados.getColumnModel()
                .getColumn(5)
                .setCellEditor(new DefaultCellEditor(comboServicio));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatosEditados = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        BtnGuardar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        LblCantidad = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jTableDatosEditados.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTableDatosEditados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Numero", "Estado", "Fecha_Ultimo_Estado", "Municipio", "Cliente", "Servicio"
            }
        ));
        jTableDatosEditados.setRowHeight(25);
        jScrollPane1.setViewportView(jTableDatosEditados);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setText("Liberty Networks | Panel de Edicion");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Logoliberty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(59, 59, 59))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jLabel1)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        BtnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoguardar.png"))); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.setToolTipText("Guardar cambios");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconolimpiar.png"))); // NOI18N
        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.setToolTipText("Limpiar tabla");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        LblCantidad.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblCantidad.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoeditar.png"))); // NOI18N
        LblCantidad.setText("Seleccionados: 0");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 892, Short.MAX_VALUE)
                        .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 115, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(LblCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 370, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(LblCantidad)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed
        try {

            if (jTableDatosEditados.isEditing()) {
                jTableDatosEditados.getCellEditor().stopCellEditing();
            }

            Connection con = Conexion.conectar();
            LineaDAO dao = new LineaDAO();

            String sql = "UPDATE lineas SET " + "numero=?," + "estado_id=?," + "fechas_ultimo_estado=NOW()," + "municipio_id=?," + "cliente_id=?," + "servicio_id=? " + "WHERE numero=?";

            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 0; i < modelo.getRowCount(); i++) {

                String numeroNuevo = modelo.getValueAt(i, 0).toString().trim();
                String estadoNew = modelo.getValueAt(i, 1).toString().trim();
                String municipioNew = modelo.getValueAt(i, 3).toString().trim();
                String clienteNew = modelo.getValueAt(i, 4).toString().trim();
                String servicioNew = modelo.getValueAt(i, 5).toString().trim();

                String numeroOriginal = numerosOriginales.get(i);

                String estadoAnt = estadoOriginal.get(i);
                String municipioAnt = municipioOriginal.get(i);
                String clienteAnt = clienteOriginal.get(i);
                String servicioAnt = servicioOriginal.get(i);

                // VALIDACIÓN FK (EVITA ERROR)
                int estadoId = dao.obtenerIdPublic(con, "estados", estadoNew);
                int municipioId = dao.obtenerIdPublic(con, "municipios", municipioNew);
                int clienteId = dao.obtenerClientePublic(con, clienteNew);
                int servicioId = dao.obtenerIdPublic(con, "servicios", servicioNew);

                if (servicioId == 0) {
                    throw new Exception("Servicio inválido: " + servicioNew);
                }

                // HISTORIAL (ANTES DEL UPDATE)
                dao.registrarHistorialCompleto(
                        numeroNuevo,
                        estadoAnt, estadoNew,
                        municipioAnt, municipioNew,
                        clienteAnt, clienteNew,
                        servicioAnt, servicioNew
                );

                // UPDATE
                ps.setString(1, numeroNuevo);
                ps.setInt(2, estadoId);
                ps.setInt(3, municipioId);
                ps.setInt(4, clienteId);
                ps.setInt(5, servicioId);
                ps.setString(6, numeroOriginal);

                ps.addBatch();
            }

            ps.executeBatch();
            con.close();

            frmConsulta.cargarTabla();

            JOptionPane.showMessageDialog(this, "Actualizado correctamente");
            dispose();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed

        ((DefaultTableModel) jTableDatosEditados.getModel()).setRowCount(0);

    }//GEN-LAST:event_BtnLimpiarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmEditar.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrmEditar().setVisible(true);
        });
    }

    private void SetImageLabel(javax.swing.JLabel labelName, String resourcePath) {

        java.net.URL imageURL = getClass().getResource(resourcePath);

        if (imageURL != null) {

            ImageIcon image = new ImageIcon(imageURL);

            Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_SMOOTH));

            labelName.setIcon(icon);

        } else {

            System.out.println("No se encontró la imagen: " + resourcePath);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JLabel LblCantidad;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDatosEditados;
    // End of variables declaration//GEN-END:variables
}
