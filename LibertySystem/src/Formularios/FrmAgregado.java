/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Formularios;

import dao.LineaDAO;
import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import utilidades.Validaciones;
import java.text.SimpleDateFormat;
import java.util.Date;
import utilidades.TemaManager;
import javax.swing.JButton;
import javax.swing.JComboBox;

/**
 *
 * @author gabri
 */
public class FrmAgregado extends javax.swing.JFrame {

    /**
     * Creates new form FrmAgregado
     */
    public void aplicarTemaOscuro() {
        aplicarColoresPersonalizados();
    }

    private FrmConsulta frmConsulta;

    public FrmAgregado() {
        initComponents();

        LineaDAO dao = new LineaDAO();
        dao.cargarMunicipios(CmbMunicipio);

        ajustarFlechaCombo(CmbServicio);
        ajustarFlechaCombo(CmbEstado);
        ajustarFlechaCombo(CmbMunicipio);

        aplicarTema();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        sdf.setTimeZone(java.util.TimeZone.getTimeZone("America/Tegucigalpa"));
        TxtFecha.setText(sdf.format(new Date()));

        iniciarRelojEnTiempoReal();

        TxtFecha.setEditable(false);

        setTitle("Liberty Networks | Panel de Agregado");
        setResizable(false);
        setLocationRelativeTo(null);
    }

//        private void initPost() {
//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        sdf.setTimeZone(java.util.TimeZone.getTimeZone("America/Tegucigalpa"));
//        TxtFecha.setText(sdf.format(new Date()));
//
//        iniciarRelojEnTiempoReal();
//
//        setTitle("Liberty Networks | Panel de Agregado");
//        setResizable(false);
//
//        LineaDAO dao = new LineaDAO();
//        dao.cargarMunicipios(CmbMunicipio);
//
//        setLocationRelativeTo(null);
//    }
    private void aplicarEstiloCombo(JComboBox combo) {

        combo.setOpaque(true);
        combo.setBackground(Color.WHITE);
        combo.setForeground(Color.BLACK);
        combo.setFocusable(false);
    }

    private void ajustarFlechaCombo(JComboBox combo) {

        Icon flecha = new ImageIcon(new ImageIcon(getClass().getResource("/IMG/Iconoflecha.png")).getImage().getScaledInstance(12, 12, Image.SCALE_SMOOTH));

        combo.setUI(new javax.swing.plaf.basic.BasicComboBoxUI() {

            @Override
            protected JButton createArrowButton() {

                JButton btn = new JButton(flecha);

                btn.setBorder(null);
                btn.setFocusPainted(false);
                btn.setContentAreaFilled(true);

                if (TemaManager.oscuro) {
                    btn.setBackground(Color.WHITE);
                } else {
                    btn.setBackground(Color.WHITE);
                }
                return btn;
            }
        });
    }
    
    private void aplicarColoresPersonalizados() {

        if (TemaManager.oscuro) {

            BtnGuardar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoguardar.png"));

            BtnLimpiar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconolimpiar.png"));

            jLabel3.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconocliente.png"));

            jLabel2.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconohashtag.png"));

            jLabel7.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoestado.png"));

            jLabel4.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconofecha.png"));

            jLabel5.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconomunicipio.png"));

            jLabel6.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoservicio.png"));

        } else {

            CmbEstado.setBackground(Color.WHITE);
            CmbEstado.setForeground(Color.BLACK);

            CmbMunicipio.setBackground(Color.WHITE);
            CmbMunicipio.setForeground(Color.BLACK);

            CmbServicio.setBackground(Color.WHITE);
            CmbServicio.setForeground(Color.BLACK);

            BtnGuardar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoguardar.png")));

            BtnLimpiar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconolimpiar.png")));

            jLabel3.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconocliente.png"));

            jLabel2.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconohashtag.png"));

            jLabel7.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoestado.png"));

            jLabel4.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconofecha.png"));

            jLabel5.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconomunicipio.png"));

            jLabel6.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoservicio.png"));
        }
        repaint();
    }

    private void aplicarTema() {
        if (TemaManager.oscuro) {

            BtnGuardar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoguardar.png"));

            BtnLimpiar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconolimpiar.png"));

            jLabel3.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconocliente.png"));

            jLabel2.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconohashtag.png"));

            jLabel7.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoestado.png"));

            jLabel4.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconofecha.png"));

            jLabel5.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconomunicipio.png"));

            jLabel6.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoservicio.png"));

            Color bgCombo = TemaManager.oscuro ? new Color(55, 55, 55) : Color.WHITE;
            Color fgCombo = TemaManager.oscuro ? Color.WHITE : Color.BLACK;

            CmbServicio.setBackground(bgCombo);
            CmbEstado.setBackground(bgCombo);
            CmbMunicipio.setBackground(bgCombo);

            CmbServicio.setForeground(fgCombo);
            CmbEstado.setForeground(fgCombo);
            CmbMunicipio.setForeground(fgCombo);

        } else {

            BtnGuardar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoguardar.png")));
            BtnLimpiar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconolimpiar.png")));

            jLabel3.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconocliente.png")));
            jLabel2.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconohashtag.png")));
            jLabel7.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoestado.png")));
            jLabel4.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconofecha.png")));
            jLabel5.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconomunicipio.png")));
            jLabel6.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoservicio.png")));
        }

        if (TemaManager.oscuro) {
            jPanel1.setBackground(new java.awt.Color(35, 35, 35));
            jPanel2.setBackground(new java.awt.Color(45, 45, 45));
            jLabel1.setForeground(Color.WHITE);
            jLabel2.setForeground(Color.WHITE);
            jLabel3.setForeground(Color.WHITE);
            jLabel4.setForeground(Color.WHITE);
            jLabel5.setForeground(Color.WHITE);
            jLabel6.setForeground(Color.WHITE);
            jLabel7.setForeground(Color.WHITE);
            TxtCliente.setBackground(new Color(55, 55, 55));
            TxtCliente.setForeground(Color.WHITE);
            TxtCliente.setCaretColor(Color.WHITE);
            TxtNumero.setBackground(new Color(55, 55, 55));
            TxtNumero.setForeground(Color.WHITE);
            TxtNumero.setCaretColor(Color.WHITE);
            TxtFecha.setBackground(new Color(55, 55, 55));
            TxtFecha.setForeground(Color.WHITE);
            TxtFecha.setCaretColor(Color.WHITE);
            CmbEstado.setBackground(new Color(55, 55, 55));
            CmbEstado.setForeground(Color.WHITE);
            CmbMunicipio.setBackground(new Color(55, 55, 55));
            CmbMunicipio.setForeground(Color.WHITE);
            CmbServicio.setBackground(new Color(55, 55, 55));
            CmbServicio.setForeground(Color.WHITE);
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
//        jLabel3.setForeground(Color.WHITE);
//        jLabel4.setForeground(Color.WHITE);
//        jLabel5.setForeground(Color.WHITE);
//        jLabel6.setForeground(Color.WHITE);
//        jLabel7.setForeground(Color.WHITE);
//        TxtCliente.setBackground(new Color(55,55,55));
//        TxtCliente.setForeground(Color.WHITE);
//        TxtCliente.setCaretColor(Color.WHITE);
//        TxtNumero.setBackground(new Color(55,55,55));
//        TxtNumero.setForeground(Color.WHITE);
//        TxtNumero.setCaretColor(Color.WHITE);
//        TxtFecha.setBackground(new Color(55,55,55));
//        TxtFecha.setForeground(Color.WHITE);
//        TxtFecha.setCaretColor(Color.WHITE);
//        CmbEstado.setBackground(new Color(55,55,55));
//        CmbEstado.setForeground(Color.WHITE);
//        CmbMunicipio.setBackground(new Color(55,55,55));
//        CmbMunicipio.setForeground(Color.WHITE);
//        CmbServicio.setBackground(new Color(55,55,55));
//        CmbServicio.setForeground(Color.WHITE);
//        JButton[] botones = {
//            BtnGuardar,
//            BtnLimpiar
//        };
//        for (JButton b : botones) {
//            b.setBackground(new Color(55,55,55));
//            b.setForeground(Color.WHITE);
//        }
        }
        repaint();
    }
    
    public FrmAgregado(FrmConsulta frmConsulta) {
        this();
        this.frmConsulta = frmConsulta;

        TxtFecha.addKeyListener(new java.awt.event.KeyAdapter() {

            @Override
            public void keyReleased(java.awt.event.KeyEvent e) {
            }
        });

        TxtCliente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                Validaciones.soloMax50Caracteres(TxtCliente, evt);
            }
        });

        setPlaceholder(TxtNumero, " Agregar Numero de Linea");
        setPlaceholder(TxtCliente, " Agregar Nombre de Cliente");

        this.setLocationRelativeTo(null);
        SetImageLabel(jLabel9, "/IMG/Logoliberty.png");
        setIconImage(new ImageIcon(getClass().getResource("/IMG/Iconoliberty.png")).getImage());

    }

    private void iniciarRelojEnTiempoReal() {

        javax.swing.Timer timer = new javax.swing.Timer(1000, new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                String fechaHora = sdf.format(new Date());
                TxtFecha.setText(fechaHora);
            }
        });
        timer.start();
    }

    private void restaurarPlaceholders() {

        setPlaceholder(TxtNumero, " Agregar Numero de Linea");
        setPlaceholder(TxtCliente, " Agregar Nombre de Cliente");
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

        jLabel10 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        TxtNumero = new javax.swing.JTextField();
        TxtCliente = new javax.swing.JTextField();
        TxtFecha = new javax.swing.JTextField();
        CmbServicio = new javax.swing.JComboBox<>();
        CmbEstado = new javax.swing.JComboBox<>();
        CmbMunicipio = new javax.swing.JComboBox<>();
        BtnGuardar = new javax.swing.JButton();
        BtnLimpiar = new javax.swing.JButton();

        jLabel10.setText("jLabel10");

        jMenu1.setText("File");
        jMenuBar1.add(jMenu1);

        jMenu2.setText("Edit");
        jMenuBar1.add(jMenu2);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 36)); // NOI18N
        jLabel1.setText("Liberty Networks | Panel de Agregado");

        jLabel9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Logoliberty.png"))); // NOI18N
        jLabel9.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Logoliberty.png"))); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jLabel1)
                .addGap(48, 48, 48)
                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(31, 31, 31)
                .addComponent(jLabel1)
                .addContainerGap(31, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconohashtag.png"))); // NOI18N
        jLabel2.setText("Numero");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconocliente.png"))); // NOI18N
        jLabel3.setText("Cliente");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconofecha.png"))); // NOI18N
        jLabel4.setText("Fecha");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconomunicipio.png"))); // NOI18N
        jLabel5.setText("Municipio");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoservicio.png"))); // NOI18N
        jLabel6.setText("Servicio");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoestado.png"))); // NOI18N
        jLabel7.setText("Estado");

        TxtNumero.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TxtNumero.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TxtNumero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtNumeroActionPerformed(evt);
            }
        });
        TxtNumero.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtNumeroKeyTyped(evt);
            }
        });

        TxtCliente.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        TxtCliente.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        TxtFecha.setEditable(false);
        TxtFecha.setFont(new java.awt.Font("Segoe UI", 1, 21)); // NOI18N
        TxtFecha.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        TxtFecha.setToolTipText("");
        TxtFecha.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        TxtFecha.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TxtFecha.setFocusable(false);
        TxtFecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                TxtFechaActionPerformed(evt);
            }
        });
        TxtFecha.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                TxtFechaKeyTyped(evt);
            }
        });

        CmbServicio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CmbServicio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un Servicio", "Bussines Trunk", "MyUC" }));
        CmbServicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CmbServicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        CmbEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un Estado", "Libre", "Cancelado", "En Servicio", "Reservado" }));
        CmbEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CmbEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        CmbMunicipio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CmbMunicipio.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Selecciona un Municipio", "Alianza, Valle", "Amapala, Valle", "Azacualpa, Santa Barbara", "Campamento, Olancho", "Catacamas, Olancho", "Choloma, Cortes", "Choluteca, Choluteca", "Comayagua, Comayagua", "Copan Ruinas, Copan", "Cucuyagua, Copan", "Danli, El Paraiso", "Dulce Nombre de Culmi, Olancho", "El Negrito, Yoro", "El Paraiso, El Paraiso", "El Porvenir, Francisco Morazan", "El Progreso, Yoro", "El Triunfo, Choluteca", "Florida, Copan", "Gracias, Lempira", "Guiamaca, Francisco Morazan", "Jesus de Otoro, Intibuca", "Juticalpa, Olancho", "La Ceiba, Atlantida", "La Esperanza, Intibuca", "La Flecha, Santa Barbara", "La Labor, Ocotepeque", "La Libertad, Comayagua", "La Lima, Cortes", "La Paz, La Paz", "La Trinidad, Santa Barbara", "La Union, Copan", "Langue, Valle", "Las Vegas, Santa Barbara", "Lepaera, Lempira", "Macuelizo, Santa Barbara", "Marcala, La Paz", "Monjaras, Marcovia", "Morazan, Yoro", "Nacaome, Valle", "Nueva Arcadia, Copan", "Ocotepeque, Ocotepeque", "Olanchito, Yoro", "Omoa, Cortes", "Pespire, Choluteca", "Pimienta, Cortes", "Potrerillos, Cortes", "Puerto Cortes, Cortes", "Puerto Lempira, Gracias a Dios", "Quimistan, Santa Barbara", "Roatan, Islas de la Bahia", "Saba, Colon", "San Manuel, Cortes", "San Antonio de Oriente, Francisco Morazan", "San Juan, Intibuca", "San Juan Pueblo, Atlantida", "San Lorenzo, Valle", "San Marcos, Ocotepeque", "San Marcos de Colon, Choluteca", "San Pedro Sula, Cortes", "Santa Barbara, Santa Barbara", "Santa Cruz de Yojoa, Cortes", "Santa Rita, Copan", "Santa Rosa de Copan, Copan", "Siguatepeque, Comayagua", "Talanga, Francisco Morazan", "Taulabe, Comayagua", "Tegucigalpa, Francisco Morazan", "Tela, Atlantida", "Tocoa, Colon", "Trojes, El Paraiso", "Trujillo, Colon", "Villanueva, Cortes", "Yoro, Yoro", "Yuscaran, El Paraiso" }));
        CmbMunicipio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CmbMunicipio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        CmbMunicipio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CmbMunicipioActionPerformed(evt);
            }
        });

        BtnGuardar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnGuardar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoguardar.png"))); // NOI18N
        BtnGuardar.setText("Guardar");
        BtnGuardar.setToolTipText("Guardar nueva linea");
        BtnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnGuardarActionPerformed(evt);
            }
        });

        BtnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconolimpiar.png"))); // NOI18N
        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.setToolTipText("Limpiar campos");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(TxtCliente)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CmbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(TxtNumero))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtFecha)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(BtnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(TxtCliente, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(CmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(TxtNumero, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(TxtFecha))))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabel5))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(5, 5, 5)
                                .addComponent(jLabel6)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(CmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(CmbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(BtnGuardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(BtnLimpiar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(11, 11, 11))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void TxtNumeroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtNumeroActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtNumeroActionPerformed

    private void CmbMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbMunicipioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CmbMunicipioActionPerformed

    private void BtnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnLimpiarActionPerformed

        restaurarPlaceholders();
        CmbMunicipio.setSelectedIndex(0);
        CmbEstado.setSelectedIndex(0);
        CmbServicio.setSelectedIndex(0);

    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void BtnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnGuardarActionPerformed

        // VALIDAR MUNICIPIO
        if (CmbMunicipio.getSelectedIndex() == 0) {
            JOptionPane.showMessageDialog(this, "Complete los campos");
            return;
        }

        LineaDAO dao = new LineaDAO();

        String numero = TxtNumero.getText().trim();
        String municipio = CmbMunicipio.getSelectedItem().toString();
        String cliente = TxtCliente.getText().trim();
        String estado = CmbEstado.getSelectedItem().toString();
        String servicio = CmbServicio.getSelectedItem().toString();

        // VALIDAR CAMPOS
        if (numero.isEmpty() || cliente.isEmpty()) {

            JOptionPane.showMessageDialog(this, "Complete todos los campos", "Advertencia", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // FECHA AUTOMÁTICA (IMPORTANTE)
        String fecha = TxtFecha.getText().trim();

        // INSERTAR
        int resultado = dao.insertarLinea(numero, estado, fecha, municipio, cliente, servicio
        );

        // RESPUESTAS
        if (resultado == 1) {

            dao.registrarHistorialCompleto(numero, "NUEVO", estado, "NUEVO", municipio, "NUEVO", cliente, "NUEVO", servicio
            );

            JOptionPane.showMessageDialog(this, "Registro agregado correctamente");

            //REFRESCAR TABLA
            if (frmConsulta != null) {
                frmConsulta.cargarTabla();
                frmConsulta.cargarTotalInicial();
                frmConsulta.actualizarLabelResultado();
            }

            //LIMPIAR CAMPOS (OPCIONAL)
            TxtNumero.setText("");
            TxtCliente.setText("");
            CmbMunicipio.setSelectedIndex(0);
            CmbEstado.setSelectedIndex(0);
            CmbServicio.setSelectedIndex(0);
            TxtNumero.requestFocus();

        } else if (resultado == 0) {

            JOptionPane.showMessageDialog(this, "Ya existe una línea con ese número", "Duplicado", JOptionPane.WARNING_MESSAGE
            );

        } else {

            JOptionPane.showMessageDialog(this, "Error al insertar el registro", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_BtnGuardarActionPerformed

    private void TxtNumeroKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtNumeroKeyTyped

        Validaciones.soloNumeros8Digitos(TxtNumero, evt);

    }//GEN-LAST:event_TxtNumeroKeyTyped

    private void TxtFechaKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFechaKeyTyped

        Validaciones.soloFormatoFechaHora(evt);

    }//GEN-LAST:event_TxtFechaKeyTyped

    private void TxtFechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFechaActionPerformed

    }//GEN-LAST:event_TxtFechaActionPerformed

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
            java.util.logging.Logger.getLogger(FrmAgregado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmAgregado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmAgregado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmAgregado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

//        try {
//            UIManager.setLookAndFeel( new FlatDarkLaf());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmAgregado().setVisible(true);
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

    private Icon icon(String path) {
        return new ImageIcon(getClass().getResource(path));
    }

    private Icon iconInvertido(String path) {
        return TemaManager.invertirIcono(getClass(), path);
    }
    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnGuardar;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JComboBox<String> CmbEstado;
    private javax.swing.JComboBox<String> CmbMunicipio;
    private javax.swing.JComboBox<String> CmbServicio;
    private javax.swing.JTextField TxtCliente;
    private javax.swing.JTextField TxtFecha;
    private javax.swing.JTextField TxtNumero;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
