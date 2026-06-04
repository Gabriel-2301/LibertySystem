/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileOutputStream;
import javax.swing.JFileChooser;
import dao.LineaDAO;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Item;
import utilidades.Validaciones;


/**
 *
 * @author gabri
 */
public class FrmConsulta extends javax.swing.JFrame {

    /**
     * Creates new form FrmConsulta
     */
    private javax.swing.Timer filtroTimer;
    
    public void cargarTabla() {

    LineaDAO dao = new LineaDAO();

    jTableDatos.setModel(dao.mostrarDatos());

    jTableDatos.setDefaultEditor(Object.class, null);

    configurarTabla();
}
    
    public FrmConsulta() {
        initComponents();
        cargarTabla();
        
        cargarTotalInicial();
        
        setTitle("Liberty Networks | Sistema de Consulta de Líneas");
        
        BtnFiltrar.setEnabled(false);
        
        validarFiltros();
        
       this.setFocusable(true);
       this.requestFocusInWindow();
        
        setResizable(false);
        
        setPlaceholder(TxtFieldBuscador, " Buscar por Numero o Cliente");
        setPlaceholder(TxtCantidad, " #Cantidad Filtrado");
        validarFiltros();
        this.setLocationRelativeTo(this);
        SetImageLabel(jLabel2, "src/IMG/Logoliberty.png");
        
        setIconImage(new ImageIcon(getClass().getResource("/IMG/Iconoliberty.png")).getImage());
        
        jLabelResultadoFiltrado.setText("");
        
    }
    
    
    
    private void actualizarLabelResultado() {

    int total = jTableDatos.getRowCount();

    // Si no hay nada o tabla vacía
    if (total <= 0) {
        jLabelResultadoFiltrado.setText("Sin resultados");
    } else {
        jLabelResultadoFiltrado.setText("Resultados Filtrados/Encontrados: " + total);
    }
}
    
   private void cargarTotalInicial() {

    LineaDAO dao = new LineaDAO();

    int total = dao.contarLineasTotales();

    jLabelTotalDatos.setText("" + total);
}
    
    private void configurarTabla() {
    
        //jTableDatos.setAutoResizeMode(jTableDatos.AUTO_RESIZE_OFF);

    jTableDatos.getColumnModel().getColumn(0).setPreferredWidth(130);
    jTableDatos.getColumnModel().getColumn(0).setResizable(false);
    
    jTableDatos.getColumnModel().getColumn(1).setPreferredWidth(130);
    jTableDatos.getColumnModel().getColumn(1).setResizable(false);

    jTableDatos.getColumnModel().getColumn(2).setPreferredWidth(130);
    jTableDatos.getColumnModel().getColumn(2).setResizable(false);

    jTableDatos.getColumnModel().getColumn(3).setPreferredWidth(180);
    jTableDatos.getColumnModel().getColumn(3).setResizable(false);

    jTableDatos.getColumnModel().getColumn(4).setPreferredWidth(300);
    jTableDatos.getColumnModel().getColumn(4).setResizable(false);

    jTableDatos.getColumnModel().getColumn(5).setPreferredWidth(400);
    jTableDatos.getColumnModel().getColumn(5).setResizable(false);

    jTableDatos.getColumnModel().getColumn(6).setPreferredWidth(140);
    jTableDatos.getColumnModel().getColumn(6).setResizable(false);

    jTableDatos.setRowHeight(25);

    jTableDatos.getTableHeader().setResizingAllowed(false);
    jTableDatos.getTableHeader().setReorderingAllowed(false);
}
    
   private void validarFiltros() {

    boolean estadoOk = CmbEstado.getSelectedIndex() > 0;
    boolean servicioOk = CmbServicio.getSelectedIndex() > 0;
    boolean municipioOk = CmbMunicipio.getSelectedIndex() > 0;

    String cantidad = TxtCantidad.getText().trim();

    boolean cantidadOk =
            !cantidad.isEmpty()
            && !cantidad.equals("#Cantidad Filtrado");

    BtnFiltrar.setEnabled(
            estadoOk ||
            servicioOk ||
            municipioOk ||
            cantidadOk
    );
}

   private String limpiarPDF(Object valor) {

    if (valor == null) return "";

    String v = valor.toString().trim();

    if (v.equalsIgnoreCase("null")) return "";
    if (v.equals("1900-01-01 00:00:00")) return "";
    if (v.equals("0")) return "";

    return v;
}
   
    private void setPlaceholder(javax.swing.JTextField txt, String placeholder) {

    txt.setText(placeholder);
    txt.setForeground(java.awt.Color.GRAY);

    txt.addFocusListener(new java.awt.event.FocusAdapter() {

        @Override
        public void focusGained(java.awt.event.FocusEvent e) {

            if (txt.getText().equals(placeholder)) {
                txt.setText("");
                txt.setForeground(java.awt.Color.BLACK);
            }
        }

        @Override
        public void focusLost(java.awt.event.FocusEvent e) {

            if (txt.getText().isEmpty()) {
                txt.setText(placeholder);
                txt.setForeground(java.awt.Color.GRAY);
            }
        }
    });
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
        TxtFieldBuscador = new javax.swing.JTextField();
        BtnBuscar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatos = new javax.swing.JTable();
        BtnEditar = new javax.swing.JButton();
        BtnEliminar = new javax.swing.JButton();
        BtnAgregar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        CmbServicio = new javax.swing.JComboBox<>();
        TxtCantidad = new javax.swing.JTextField();
        BtnFiltrar = new javax.swing.JButton();
        CmbEstado = new javax.swing.JComboBox<>();
        BtnImprimir = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        CmbMunicipio = new javax.swing.JComboBox<>();
        jLabelResultadoFiltrado = new javax.swing.JLabel();
        jLabelTotalDatos = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel1.setText("Liberty Networks | Panel de Consultas");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Logoliberty.png"))); // NOI18N
        jLabel2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Logoliberty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(112, 112, 112)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(25, 25, 25))
        );

        TxtFieldBuscador.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TxtFieldBuscador.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TxtFieldBuscador.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFieldBuscadorActionPerformed(evt);
            }
        });
        TxtFieldBuscador.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtFieldBuscadorKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtFieldBuscadorKeyTyped(evt);
            }
        });

        BtnBuscar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnBuscar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconobuscar.png"))); // NOI18N
        BtnBuscar.setText("Buscar");
        BtnBuscar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBuscarActionPerformed(evt);
            }
        });

        BtnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconolimpiar.png"))); // NOI18N
        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        jTableDatos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Numero", "PBX", "Estado", "Fecha_Ultimo_Estado", "Municipio", "Cliente", "Servicio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, true, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDatos.setRowHeight(25);
        jTableDatos.getTableHeader().setResizingAllowed(false);
        jTableDatos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableDatos);

        BtnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoeditar.png"))); // NOI18N
        BtnEditar.setText("Editar");

        BtnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoeliminar.png"))); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoagregar.png"))); // NOI18N
        BtnAgregar.setText("Agregar");
        BtnAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnAgregarActionPerformed(evt);
            }
        });

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Source Sans Pro Black", 1, 20)); // NOI18N
        jLabel3.setText("Busqueda por Filtro");

        CmbServicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CmbServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Servicio", "Bussines Trunk", "MyUC" }));
        CmbServicio.setToolTipText("");
        CmbServicio.setBorder(null);
        CmbServicio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbServicioItemStateChanged(evt);
            }
        });
        CmbServicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbServicioActionPerformed(evt);
            }
        });

        TxtCantidad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TxtCantidad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TxtCantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtCantidadActionPerformed(evt);
            }
        });
        TxtCantidad.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                TxtCantidadKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtCantidadKeyTyped(evt);
            }
        });

        BtnFiltrar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconofiltrar.png"))); // NOI18N
        BtnFiltrar.setText("Filtrar");
        BtnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFiltrarActionPerformed(evt);
            }
        });

        CmbEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Estado", "Libre", "Cancelado", "En Servicio", "Reservado" }));
        CmbEstado.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbEstadoItemStateChanged(evt);
            }
        });
        CmbEstado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbEstadoActionPerformed(evt);
            }
        });

        BtnImprimir.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnImprimir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoimprimir.png"))); // NOI18N
        BtnImprimir.setText("Imprimir");
        BtnImprimir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnImprimirActionPerformed(evt);
            }
        });

        jLabel6.setBackground(new java.awt.Color(0, 0, 0));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconodatos.png"))); // NOI18N
        jLabel6.setText("Tabla de Lineas Registradas HND:");

        CmbMunicipio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CmbMunicipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Municipio", "Alianza, Valle", "Amapala, Valle", "Azacualpa, Santa Barbara", "Campamento, Olancho", "Catacamas, Olancho", "Choloma, Cortes", "Choluteca, Choluteca", "Comayagua, Comayagua", "Copan Ruinas, Copan", "Cucuyagua, Copan", "Danli, El Paraiso", "Dulce Nombre de Culmi, Olancho", "El Negrito, Yoro", "El Paraiso, El Paraiso", "El Porvenir, Francisco Morazan", "El Progreso, Yoro", "El Triunfo, Choluteca", "Florida, Copan", "Gracias, Lempira", "Guiamaca, Francisco Morazan", "Jesus de Otoro, Intibuca", "Juticalpa, Olancho", "La Ceiba, Atlantida", "La Esperanza, Intibuca", "La Flecha, Santa Barbara", "La Labor, Ocotepeque", "La Libertad, Comayagua", "La Lima, Cortes", "La Paz, La Paz", "La Trinidad, Santa Barbara", "La Union, Copan", "Langue, Valle", "Las Vegas, Santa Barbara", "Lepaera, Lempira", "Macuelizo, Santa Barbara", "Marcala, La Paz", "Monjaras, Marcovia", "Morazan, Yoro", "Nacaome, Valle", "Nueva Arcadia, Copan", "Ocotepeque, Ocotepeque", "Olanchito, Yoro", "Omoa, Cortes", "Pespire, Choluteca", "Pimienta, Cortes", "Potrerillos, Cortes", "Puerto Cortes, Cortes", "Puerto Lempira, Gracias a Dios", "Quimistan, Santa Barbara", "Roatan, Islas de la Bahia", "Saba, Colon", "San Manuel, Cortes", "San Antonio de Oriente, Francisco Morazan", "San Juan, Intibuca", "San Juan Pueblo, Atlantida", "San Lorenzo, Valle", "San Marcos, Ocotepeque", "San Marcos de Colon, Choluteca", "San Pedro Sula, Cortes", "Santa Barbara, Santa Barbara", "Santa Cruz de Yojoa, Cortes", "Santa Rita, Copan", "Santa Rosa de Copan, Copan", "Siguatepeque, Comayagua", "Talanga, Francisco Morazan", "Taulabe, Comayagua", "Tegucigalpa, Francisco Morazan", "Tela, Atlantida", "Tocoa, Colon", "Trojes, El Paraiso", "Trujillo, Colon", "Villanueva, Cortes", "Yoro, Yoro", "Yuscaran, El Paraiso" }));
        CmbMunicipio.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CmbMunicipioItemStateChanged(evt);
            }
        });
        CmbMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbMunicipioActionPerformed(evt);
            }
        });

        jLabelResultadoFiltrado.setBackground(new java.awt.Color(0, 0, 0));
        jLabelResultadoFiltrado.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelResultadoFiltrado.setText(".");

        jLabelTotalDatos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabelTotalDatos.setText(".");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(517, 517, 517)
                        .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel3)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CmbMunicipio, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(CmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(TxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(TxtFieldBuscador))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTotalDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(833, 833, 833)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabelResultadoFiltrado, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 270, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtFieldBuscador, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnBuscar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(TxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(CmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(CmbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jLabelResultadoFiltrado)
                    .addComponent(jLabelTotalDatos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 342, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(10, 10, 10))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void BtnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirActionPerformed
        
          try {

        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Guardar PDF");

        int option = fileChooser.showSaveDialog(this);

        if (option != JFileChooser.APPROVE_OPTION) {
            return;
        }

        String ruta = fileChooser.getSelectedFile().getAbsolutePath();

        String nombreArchivo = fileChooser.getSelectedFile().getName();

        if (nombreArchivo.toLowerCase().endsWith(".pdf")) {
            nombreArchivo = nombreArchivo.substring(0, nombreArchivo.length() - 4);
        }

        String tituloDinamico = "LIBERTY NETWORKS - " + nombreArchivo.toUpperCase();

        if (!ruta.endsWith(".pdf")) {
            ruta += ".pdf";
        }

        // =========================
        // DOCUMENTO
        // =========================
        Document documento = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);

        PdfWriter.getInstance(documento, new FileOutputStream(ruta));

        documento.open();

        // =========================
        // COLORES
        // =========================
        BaseColor naranjaLiberty = new BaseColor(255, 153, 0);
        BaseColor grisClaro = new BaseColor(245, 245, 245);

        // =========================
        // LOGO
        // =========================
        try {
            com.itextpdf.text.Image logo =
                    com.itextpdf.text.Image.getInstance("src/IMG/Logoliberty.png");

            logo.scaleToFit(100, 100);
            logo.setAlignment(Element.ALIGN_CENTER);

            documento.add(logo);

        } catch (Exception e) {
            System.out.println("Logo no encontrado");
        }

        // =========================
        // TITULO
        // =========================
        Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);

        Paragraph titulo = new Paragraph(tituloDinamico, tituloFont);
        titulo.setAlignment(Element.ALIGN_CENTER);

        documento.add(titulo);
        documento.add(new Paragraph(" "));

        // =========================
        // FECHA
        // =========================
        String fecha = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
                .format(new java.util.Date());

        Paragraph fechaTexto = new Paragraph("Generado: " + fecha);
        fechaTexto.setAlignment(Element.ALIGN_RIGHT);

        documento.add(fechaTexto);
        documento.add(new Paragraph(" "));

        // =========================
        // TABLA
        // =========================
        PdfPTable tabla = new PdfPTable(jTableDatos.getColumnCount());
        tabla.setWidthPercentage(100);

        tabla.setSplitLate(false);
        tabla.setSplitRows(true);

        // IMPORTANTE: evitar error si cambias columnas
        float[] widths = new float[jTableDatos.getColumnCount()];
        for (int i = 0; i < widths.length; i++) {
            widths[i] = 1f;
        }
        tabla.setWidths(widths);

        Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
        Font dataFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);

        // =========================
        // ENCABEZADOS
        // =========================
        for (int i = 0; i < jTableDatos.getColumnCount(); i++) {

            PdfPCell header = new PdfPCell(
                    new Paragraph(jTableDatos.getColumnName(i), headerFont)
            );

            header.setBackgroundColor(naranjaLiberty);
            header.setHorizontalAlignment(Element.ALIGN_CENTER);
            header.setVerticalAlignment(Element.ALIGN_MIDDLE);
            header.setPadding(6);

            tabla.addCell(header);
        }

        // =========================
        // DATOS 
        // =========================
        for (int fila = 0; fila < jTableDatos.getRowCount(); fila++) {

            for (int col = 0; col < jTableDatos.getColumnCount(); col++) {

                Object valor = jTableDatos.getValueAt(fila, col);

                String texto = limpiarPDF(valor);

                PdfPCell cell = new PdfPCell(new Paragraph(texto, dataFont));

                cell.setPadding(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);

                if (fila % 2 == 0) {
                    cell.setBackgroundColor(grisClaro);
                }

                tabla.addCell(cell);
            }
        }

        documento.add(tabla);

        documento.add(new Paragraph(" "));

        Font firmaFont = new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.GRAY);

        Paragraph firma = new Paragraph(
                "Generado automáticamente por Liberty Networks",
                firmaFont
        );

        firma.setAlignment(Element.ALIGN_CENTER);

        documento.add(firma);

        documento.close();

        JOptionPane.showMessageDialog(this, "PDF generado correctamente");

    } catch (Exception e) {

        JOptionPane.showMessageDialog(this, "Error al generar PDF: " + e);
    }
        
    }//GEN-LAST:event_BtnImprimirActionPerformed

    private void CmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbEstadoActionPerformed
        validarFiltros();
    }//GEN-LAST:event_CmbEstadoActionPerformed

    private void BtnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFiltrarActionPerformed

    int estadoIndex = CmbEstado.getSelectedIndex();
    int servicioIndex = CmbServicio.getSelectedIndex();
    int municipioIndex = CmbMunicipio.getSelectedIndex();

    String estado = CmbEstado.getSelectedItem().toString();
    String servicio = CmbServicio.getSelectedItem().toString();
    String municipio = CmbMunicipio.getSelectedItem().toString();

    String cantidadTxt = TxtCantidad.getText().trim();

    Integer cantidad = null;

    boolean sinFiltros =
            estadoIndex == 0 &&
            servicioIndex == 0 &&
            municipioIndex == 0 &&
            cantidadTxt.isEmpty();

    if (sinFiltros) {
        JOptionPane.showMessageDialog(this,
            "Por favor seleccione filtros");

        jTableDatos.setModel(new javax.swing.table.DefaultTableModel());
        jLabelResultadoFiltrado.setText("");
        return;
    }

    try {
        if (!cantidadTxt.isEmpty()
                && !cantidadTxt.equals("#Cantidad Filtrado")) {
            cantidad = Integer.parseInt(cantidadTxt);
            if (cantidad <= 0) cantidad = null;
        }
    } catch (Exception e) {
        cantidad = null;
    }

    LineaDAO dao = new LineaDAO();

    DefaultTableModel modelo = dao.filtrarLineasFlexible(
            estadoIndex,
            servicioIndex,
            municipioIndex,
            estado,
            servicio,
            municipio,
            cantidad
    );

    jTableDatos.setModel(modelo);
    jTableDatos.setDefaultEditor(Object.class, null);

    configurarTabla();

    // =========================
    // ACTUALIZAR LABEL
    // =========================
    actualizarLabelResultado();
    
       
    }//GEN-LAST:event_BtnFiltrarActionPerformed

    private void TxtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCantidadActionPerformed

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed
        
        FrmAgregado frm = new FrmAgregado(this);
        frm.setVisible(true);
        
    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed
        
        int fila = jTableDatos.getSelectedRow();

        if (fila == -1) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona una fila primero");
            return;
        }

        String numero = jTableDatos.getValueAt(fila, 0).toString();

        int confirm = javax.swing.JOptionPane.showConfirmDialog(
            this,
            "¿Seguro que deseas eliminar el registro " + numero + "?",
            "Confirmar eliminación",
            javax.swing.JOptionPane.YES_NO_OPTION
        );

        if (confirm == javax.swing.JOptionPane.YES_OPTION) {

            LineaDAO dao = new LineaDAO();
            boolean ok = dao.eliminarLinea(numero);

            if (ok) {
                javax.swing.JOptionPane.showMessageDialog(this, "Eliminado correctamente");

                // refrescar tabla
                cargarTabla();

            } else {
                javax.swing.JOptionPane.showMessageDialog(this, "Error al eliminar");
            }
        }
        
    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed
        
    // Limpiar buscador
    TxtFieldBuscador.setText("");

    // Restaurar filtros
    CmbMunicipio.setSelectedIndex(0);
    CmbEstado.setSelectedIndex(0);
    CmbServicio.setSelectedIndex(0);

    // Limpiar cantidad
    TxtCantidad.setText("");

    // Restaurar placeholders
    TxtCantidad.setText(" #Cantidad Filtrado");
    TxtCantidad.setForeground(java.awt.Color.GRAY);

    TxtFieldBuscador.setText(" ");
    TxtFieldBuscador.setForeground(java.awt.Color.BLACK);

    TxtFieldBuscador.requestFocus();

    // Recargar tabla completa
    cargarTabla();

    // Actualizar botón filtrar
    validarFiltros();

   jLabelResultadoFiltrado.setText("");
        
    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnBuscarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBuscarActionPerformed
        
          String texto = TxtFieldBuscador.getText().trim();

    if (texto.isEmpty()
            || texto.equals("Buscar por Numero o Cliente")) {

        JOptionPane.showMessageDialog(this,
                "Ingrese texto");

        return;
    }

    LineaDAO dao = new LineaDAO();

    DefaultTableModel modelo = dao.buscarNumeroOCliente(texto);

    jTableDatos.setModel(modelo);
    jTableDatos.setDefaultEditor(Object.class, null);

    configurarTabla();

    //actualizarLabelResultado();
        
    }//GEN-LAST:event_BtnBuscarActionPerformed

    private void TxtFieldBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFieldBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFieldBuscadorActionPerformed

    private void TxtFieldBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFieldBuscadorKeyReleased
    
     String texto = TxtFieldBuscador.getText().trim();

    if (texto.isEmpty()
            || texto.equals("Buscar por Numero o Cliente")
            || texto.equals(" Buscar por Numero o Cliente")) {

        cargarTabla();
        //actualizarLabelResultado();
        return;
    }

    LineaDAO dao = new LineaDAO();

    jTableDatos.setModel(dao.buscarNumeroOCliente(texto));

    jTableDatos.setDefaultEditor(Object.class, null);

    configurarTabla();

    actualizarLabelResultado();
   
    }//GEN-LAST:event_TxtFieldBuscadorKeyReleased

    private void TxtFieldBuscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFieldBuscadorKeyTyped
       
    // máximo 50 caracteres
    if (TxtFieldBuscador.getText().length() >= 50) {
        evt.consume();
    }
        
    }//GEN-LAST:event_TxtFieldBuscadorKeyTyped

    private void TxtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCantidadKeyTyped
        
        Validaciones.soloNumeros8Digitos(
        TxtCantidad,
        evt
        );
        
    }//GEN-LAST:event_TxtCantidadKeyTyped

    private void CmbMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbMunicipioActionPerformed
        validarFiltros();
    }//GEN-LAST:event_CmbMunicipioActionPerformed

    private void CmbServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbServicioActionPerformed
       validarFiltros();
    }//GEN-LAST:event_CmbServicioActionPerformed

    private void TxtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCantidadKeyReleased
        validarFiltros();
    }//GEN-LAST:event_TxtCantidadKeyReleased

    private void CmbMunicipioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbMunicipioItemStateChanged
      
    }//GEN-LAST:event_CmbMunicipioItemStateChanged

    private void CmbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbEstadoItemStateChanged
     
    }//GEN-LAST:event_CmbEstadoItemStateChanged

    private void CmbServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbServicioItemStateChanged
        
    
    }//GEN-LAST:event_CmbServicioItemStateChanged

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
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmConsulta.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmConsulta().setVisible(true);
            }
        });
    }

    private void SetImageLabel(JLabel labelName, String root){
        ImageIcon image = new ImageIcon(root);
        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_DEFAULT));
        labelName.setIcon(icon);
        this.repaint();
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnBuscar;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnFiltrar;
    private javax.swing.JButton BtnImprimir;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JComboBox<String> CmbEstado;
    private javax.swing.JComboBox<String> CmbMunicipio;
    private javax.swing.JComboBox<String> CmbServicio;
    private javax.swing.JTextField TxtCantidad;
    private javax.swing.JTextField TxtFieldBuscador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelResultadoFiltrado;
    private javax.swing.JLabel jLabelTotalDatos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDatos;
    // End of variables declaration//GEN-END:variables
}
