package Formularios;

import conexion.Conexion;
import java.awt.Color;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.sql.PreparedStatement;
import javax.swing.ListSelectionModel;
import utilidades.TemaManager;

/**
 *
 * @author gabri
 */
public class FrmHistorial extends javax.swing.JFrame {

    public FrmHistorial() {
        initComponents();
        setTitle("Liberty Networks | Panel de Historial");
        setResizable(false);
        setLocationRelativeTo(null);
        SetImageLabel(jLabel2, "/IMG/Logoliberty.png");
        setIconImage(new ImageIcon(getClass().getResource("/IMG/Iconoliberty.png")).getImage());

        jTableDatosHistorial.setRowSelectionAllowed(true);
        jTableDatosHistorial.setColumnSelectionAllowed(false);
        jTableDatosHistorial.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        cargarHistorial();
        aplicarTema();
    }

    private void aplicarTema() {
        if (TemaManager.oscuro) {
            jMenu2.setForeground(Color.BLACK);
            jMenu2.setBackground(new Color(85, 85, 85));

        } else {
            jMenu2.setForeground(Color.BLACK);
            jMenu2.setBackground(Color.WHITE);
        }

        if (TemaManager.oscuro) {
            jPanel1.setBackground(new java.awt.Color(35, 35, 35));
            jPanel2.setBackground(new java.awt.Color(45, 45, 45));
            jLabel1.setForeground(Color.WHITE);
            jTableDatosHistorial.setBackground(new Color(45, 45, 45));
            jTableDatosHistorial.setForeground(Color.WHITE);
            jTableDatosHistorial.setGridColor(new Color(70, 70, 70));
            jTableDatosHistorial.setSelectionForeground(Color.BLACK);
            jTableDatosHistorial.getTableHeader().setBackground(new Color(60, 60, 60));
            jTableDatosHistorial.getTableHeader().setForeground(Color.WHITE);
        } else {
            jPanel1.setBackground(new java.awt.Color(255, 153, 0));
            jPanel2.setBackground(java.awt.Color.WHITE);
            jTableDatosHistorial.setBackground(java.awt.Color.WHITE);
            jTableDatosHistorial.setForeground(java.awt.Color.BLACK);
        }
        repaint();
    }

    private void cargarHistorial() {

        DefaultTableModel modelo = new DefaultTableModel() {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };

        modelo.addColumn("Numero");
        modelo.addColumn("Estado Ant");
        modelo.addColumn("Estado Nuevo");
        modelo.addColumn("Municipio Ant");
        modelo.addColumn("Municipio Nuevo");
        modelo.addColumn("Cliente Ant");
        modelo.addColumn("Cliente Nuevo");
        modelo.addColumn("Servicio Ant");
        modelo.addColumn("Servicio Nuevo");
        modelo.addColumn("Fecha");

        String sql = "SELECT * FROM historial_lineas ORDER BY id DESC";

        try (Connection con = Conexion.conectar(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {

            while (rs.next()) {

                modelo.addRow(new Object[]{
                    rs.getString("numero"),
                    rs.getString("estado_anterior"),
                    rs.getString("estado_nuevo"),
                    rs.getString("municipio_anterior"),
                    rs.getString("municipio_nuevo"),
                    rs.getString("cliente_anterior"),
                    rs.getString("cliente_nuevo"),
                    rs.getString("servicio_anterior"),
                    rs.getString("servicio_nuevo"),
                    rs.getString("fecha")
                });
            }

            jTableDatosHistorial.setModel(modelo);
            configurarTabla();
            jTableDatosHistorial.getTableHeader().setReorderingAllowed(false);

        } catch (Exception e) {
            System.out.println("Error historial: " + e);
        }
    }

    private void configurarTabla() {

        jTableDatosHistorial.setAutoResizeMode(javax.swing.JTable.AUTO_RESIZE_OFF);
        jTableDatosHistorial.getColumnModel().getColumn(0).setPreferredWidth(120); // Número
        jTableDatosHistorial.getColumnModel().getColumn(1).setPreferredWidth(120); // Estado Ant
        jTableDatosHistorial.getColumnModel().getColumn(2).setPreferredWidth(120); // Estado Nuevo
        jTableDatosHistorial.getColumnModel().getColumn(3).setPreferredWidth(170); // Municipio Ant
        jTableDatosHistorial.getColumnModel().getColumn(4).setPreferredWidth(210); // Municipio Nuevo
        jTableDatosHistorial.getColumnModel().getColumn(5).setPreferredWidth(210); // Cliente Ant
        jTableDatosHistorial.getColumnModel().getColumn(6).setPreferredWidth(210); // Cliente Nuevo
        jTableDatosHistorial.getColumnModel().getColumn(7).setPreferredWidth(150); // Servicio Ant
        jTableDatosHistorial.getColumnModel().getColumn(8).setPreferredWidth(150); // Servicio Nuevo
        jTableDatosHistorial.getColumnModel().getColumn(9).setPreferredWidth(160); // Fecha
        jTableDatosHistorial.setRowHeight(25);
        jTableDatosHistorial.getTableHeader().setResizingAllowed(false);
        jTableDatosHistorial.getTableHeader().setReorderingAllowed(false);
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
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatosHistorial = new javax.swing.JTable();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu2 = new javax.swing.JMenu();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 48)); // NOI18N
        jLabel1.setText("Liberty Networks | Historial de Lineas");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Logoliberty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(194, 194, 194)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 336, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(122, 122, 122))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel1)
                .addContainerGap(29, Short.MAX_VALUE))
        );

        jTableDatosHistorial.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTableDatosHistorial.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Numero", "Estado Anterior", "Estado Nuevo", "Municipio Anterior", "Municipio Nuevo", "Cliente Anterior", "Cliente Nuevo", "Servicio Anterior", "Servicio Nuevo", "Fecha "
            }
        ));
        jTableDatosHistorial.setCellSelectionEnabled(true);
        jTableDatosHistorial.setRowHeight(25);
        jScrollPane1.setViewportView(jTableDatosHistorial);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 461, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jMenu2.setBackground(new java.awt.Color(0, 0, 0));
        jMenu2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoeliminar.png"))); // NOI18N
        jMenu2.setText("Eliminar");
        jMenu2.setToolTipText("Eliminar Lineas del Historial");
        jMenu2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jMenu2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jMenu2.setPreferredSize(new java.awt.Dimension(92, 25));
        jMenu2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenu2MouseClicked(evt);
            }
        });
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });
        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed

    }//GEN-LAST:event_jMenu2ActionPerformed

    private void jMenu2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenu2MouseClicked

        try {

            int[] filas = jTableDatosHistorial.getSelectedRows();

            if (filas.length == 0) {
                JOptionPane.showMessageDialog(this, "Seleccione al menos un registro");
                return;
            }

            int confirm = JOptionPane.showConfirmDialog(this, "¿Seguro que desea eliminar " + filas.length + " registro(s)?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }

            Connection con = Conexion.conectar();

            String sql = "DELETE FROM historial_lineas WHERE numero = ?";
            PreparedStatement ps = con.prepareStatement(sql);

            for (int i = 0; i < filas.length; i++) {

                int fila = filas[i];

                String numero = jTableDatosHistorial.getValueAt(fila, 0).toString();

                System.out.println("Eliminando número: " + numero);

                ps.setString(1, numero);
                ps.addBatch();
            }

            ps.executeBatch();
            con.close();

            cargarHistorial();

            JOptionPane.showMessageDialog(this, "Eliminado correctamente");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }

    }//GEN-LAST:event_jMenu2MouseClicked

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
            java.util.logging.Logger.getLogger(FrmHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmHistorial.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmHistorial().setVisible(true);
            }
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
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDatosHistorial;
    // End of variables declaration//GEN-END:variables
}
