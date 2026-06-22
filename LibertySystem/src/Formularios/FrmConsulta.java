package Formularios;

import java.awt.Toolkit;
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
import dao.VersionDAO;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.io.IOException;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.InputMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRootPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.KeyStroke;
import javax.swing.SwingWorker;
import javax.swing.table.DefaultTableModel;
import utilidades.Validaciones;
import javax.swing.Timer;
import javax.swing.table.TableColumnModel;
import utilidades.BackupBD;
import utilidades.ConfigTema;
import utilidades.TemaManager;
import java.awt.Desktop;
import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import javax.swing.JComboBox;
import javax.swing.SwingUtilities;
import utilidades.Downloader;
import utilidades.VersionLocal;

/**
 *
 * @author gabri
 */
public class FrmConsulta extends javax.swing.JFrame {

    private javax.swing.Timer filtroTimer;

    private FrmAgregado frmAgregado;
    private FrmEditar frmEditar;
    private FrmHistorial frmHistorial;

    public void cargarTabla() {

        LineaDAO dao = new LineaDAO();
        jTableDatos.setModel(dao.mostrarDatos());
        jTableDatos.setDefaultEditor(Object.class, null);
        configurarTabla();

    }

    public void aplicarTemaOscuro() {
        aplicarColoresPersonalizados();
    }

    public void aplicarTemaClaro() {
        aplicarColoresPersonalizados();
    }

    private void aplicarColoresPersonalizados() {

        Color naranja = new Color(255, 153, 0);

        if (TemaManager.oscuro) {

            BtnEliminar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoeliminar.png"));
            BtnLimpiar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconolimpiar.png"));
            BtnFiltrar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconofiltrar.png"));
            BtnImprimir.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoimprimir.png"));
            BtnAgregar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoagregar.png"));
            BtnEditar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoeditar.png"));
            BtnActualizacion.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoactualizar.png"));
            jLabel4.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconointerrogacion.png"));
            jLabel6.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconodatos.png"));
            BtnOrdenar.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconoordenar.png"));
            BtnBackup.setIcon(TemaManager.invertirIcono(getClass(), "/IMG/Iconobackup.png"));

        } else {

            BtnEliminar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoeliminar.png")));
            BtnLimpiar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconolimpiar.png")));
            BtnFiltrar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconofiltrar.png")));
            BtnImprimir.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoimprimir.png")));
            BtnAgregar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoagregar.png")));
            BtnEditar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoeditar.png")));
            BtnActualizacion.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoactualizar.png")));
            jLabel4.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconointerrogacion.png")));
            jLabel6.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconodatos.png")));
            BtnOrdenar.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconoordenar.png")));
            BtnBackup.setIcon(new ImageIcon(getClass().getResource("/IMG/Iconobackup.png")));
        }

        if (TemaManager.oscuro) {
            //FONDO GENERAL
            jPanel1.setBackground(new Color(35, 35, 35));

            //PANEL SUPERIOR BLANCO
            jPanel2.setBackground(new Color(45, 45, 45));
            jLabel1.setForeground(Color.WHITE);

            //LABELS
            jLabel3.setForeground(Color.WHITE);
            jLabel6.setForeground(Color.WHITE);
            jLabelResultadoFiltrado.setForeground(Color.WHITE);
            jLabelTotalDatos.setForeground(Color.WHITE);
            LblManualdeUsuario.setForeground(Color.WHITE);
            LblVerHistorial.setForeground(Color.WHITE);

            //TABLA             
            jTableDatos.setBackground(new Color(45, 45, 45));
            jTableDatos.setForeground(Color.WHITE);
            jTableDatos.setGridColor(new Color(70, 70, 70));
            jTableDatos.setSelectionForeground(Color.BLACK);
            jTableDatos.getTableHeader().setBackground(new Color(60, 60, 60));
            jTableDatos.getTableHeader().setForeground(Color.WHITE);

            //SCROLL           
            jScrollPane1.getViewport().setBackground(new Color(45, 45, 45));

            //TEXTFIELDS
            TxtFieldBuscador.setBackground(new Color(55, 55, 55));
            TxtFieldBuscador.setForeground(Color.WHITE);
            TxtFieldBuscador.setCaretColor(Color.WHITE);
            TxtCantidad.setBackground(new Color(55, 55, 55));
            TxtCantidad.setForeground(Color.WHITE);
            TxtCantidad.setCaretColor(Color.WHITE);

            //COMBOBOX            
            CmbEstado.setBackground(new Color(55, 55, 55));
            CmbEstado.setForeground(Color.WHITE);
            CmbMunicipio.setBackground(new Color(55, 55, 55));
            CmbMunicipio.setForeground(Color.WHITE);
            CmbServicio.setBackground(new Color(55, 55, 55));
            CmbServicio.setForeground(Color.WHITE);

            //BOTONES
            JButton[] botones = {
                BtnAgregar,
                BtnEditar,
                BtnEliminar,
                BtnFiltrar,
                BtnImprimir,
                BtnLimpiar,
                BtnActualizacion,
                BtnOrdenar,
                BtnBackup
            };

            for (JButton b : botones) {
                b.setBackground(new Color(55, 55, 55));
                b.setForeground(Color.WHITE);
            }

            //TOGGLE
            tglTema.setText("☀");
            tglTema.setBackground(new Color(40, 40, 40));
            tglTema.setForeground(Color.WHITE);
            tglTema.setFocusPainted(false);
            tglTema.setBorderPainted(true);
            tglTema.setOpaque(true);

        } else {

            //CLARO ORIGINAL
            jPanel1.setBackground(naranja);
            jPanel2.setBackground(Color.WHITE);
            jLabel1.setForeground(Color.BLACK);
            jLabel3.setForeground(Color.BLACK);
            jLabel6.setForeground(Color.BLACK);
            jLabelResultadoFiltrado.setForeground(Color.BLACK);
            jLabelTotalDatos.setForeground(Color.BLACK);
            LblManualdeUsuario.setForeground(Color.BLACK);
            LblVerHistorial.setForeground(Color.BLACK);

            //TABLA
            jTableDatos.setBackground(Color.WHITE);
            jTableDatos.setForeground(Color.BLACK);
            jTableDatos.setGridColor(Color.LIGHT_GRAY);
            jTableDatos.setSelectionForeground(Color.BLACK);
            jTableDatos.getTableHeader().setBackground(Color.WHITE);
            jTableDatos.getTableHeader().setForeground(Color.BLACK);
            jScrollPane1.getViewport().setBackground(Color.WHITE);

            //TEXTFIELDS
            TxtFieldBuscador.setBackground(Color.WHITE);
            TxtFieldBuscador.setForeground(Color.GRAY);
            TxtFieldBuscador.setCaretColor(Color.GRAY);
            TxtCantidad.setBackground(Color.WHITE);
            TxtCantidad.setForeground(Color.GRAY);
            TxtCantidad.setCaretColor(Color.GRAY);

            Color grisSistema = javax.swing.UIManager.getColor("ComboBox.disabledBackground");
            if (grisSistema == null) {
                grisSistema = new Color(240, 240, 240); // Gris suave estándar por si falla el look & feel
            }

            CmbEstado.setBackground(grisSistema);
            CmbEstado.setForeground(Color.BLACK);
            CmbMunicipio.setBackground(grisSistema);
            CmbMunicipio.setForeground(Color.BLACK);
            CmbServicio.setBackground(grisSistema);
            CmbServicio.setForeground(Color.BLACK);

            if (CmbEstado.getRenderer() instanceof javax.swing.JComponent) {
                ((javax.swing.JComponent) CmbEstado.getRenderer()).setBackground(grisSistema);
                ((javax.swing.JComponent) CmbMunicipio.getRenderer()).setBackground(grisSistema);
                ((javax.swing.JComponent) CmbServicio.getRenderer()).setBackground(grisSistema);
            }

            //BOTONES
            JButton[] botones = {
                BtnAgregar,
                BtnEditar,
                BtnEliminar,
                BtnFiltrar,
                BtnImprimir,
                BtnLimpiar,
                BtnActualizacion,
                BtnOrdenar,
                BtnBackup
            };

            for (JButton b : botones) {
                b.setBackground(new Color(240, 240, 240));
                b.setForeground(Color.BLACK);
            }

            tglTema.setText("🌙");
            tglTema.setBackground(new Color(240, 240, 240));
            tglTema.setForeground(Color.BLACK);
            tglTema.setFocusPainted(false);
            tglTema.setBorderPainted(true);
            tglTema.setOpaque(true);
        }
        repaint();
    }

    private void actualizarColorTextFields() {

        JTextField[] campos = {
            TxtFieldBuscador,
            TxtCantidad
        };

        for (JTextField txt : campos) {
            String texto = txt.getText();
            boolean esPlaceholder = texto.startsWith(" Buscar") || texto.startsWith(" #Cantidad");
            if (!esPlaceholder) {

                if (TemaManager.oscuro) {
                    txt.setForeground(Color.WHITE);
                    txt.setCaretColor(Color.WHITE);
                } else {
                    txt.setForeground(Color.GRAY);
                    txt.setCaretColor(Color.GRAY);
                }
            }
        }
    }

    public FrmConsulta() {
        initComponents();

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ajustarFlechaCombo(CmbServicio);
                ajustarFlechaCombo(CmbEstado);
                ajustarFlechaCombo(CmbMunicipio);
            }
        });
        /*
        this.setLayout(new java.awt.BorderLayout());
        this.add(jPanel1, java.awt.BorderLayout.CENTER);
        jPanel1.setPreferredSize(null);
         */
        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {

                ajustarColumnasTabla();
            }
        });

        addComponentListener(new ComponentAdapter() {

            @Override
            public void componentResized(ComponentEvent e) {

                ajustarColumnasTabla();
                SetImageLabel(jLabel2, "/IMG/Logoliberty.png");
            }
        });

        jProgressBar1.setVisible(false);
        jProgressBar1.setString("");
        jProgressBar1.setValue(0);

        configurarAtajosTeclado();

        cargarTabla();

        jTableDatos.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        jTableDatos.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        jTableDatos.getTableHeader().setReorderingAllowed(false);
        jTableDatos.setFillsViewportHeight(true);

        cargarTotalInicial();

        setTitle("Liberty Networks | Sistema de Consulta de Líneas");

        BtnFiltrar.setEnabled(false);
        validarFiltros();

        this.setFocusable(true);
        this.requestFocusInWindow();

        setResizable(true);
        setSize(1400, 800);
        setMinimumSize(new java.awt.Dimension(1200, 700));
        setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH);

        setPlaceholder(TxtFieldBuscador, " Buscar por Numero o Cliente");
        setPlaceholder(TxtCantidad, " #Cantidad Filtrado");

        setLocationRelativeTo(null);
        SetImageLabel(jLabel2, "/IMG/Logoliberty.png");

        setIconImage(new ImageIcon(getClass().getResource("/IMG/Iconoliberty.png")).getImage());

        jLabelResultadoFiltrado.setText("");

        getContentPane().setLayout(new java.awt.BorderLayout());
        getContentPane().add(jPanel1, java.awt.BorderLayout.CENTER);

        aplicarColoresPersonalizados();
    }

    void actualizarLabelResultado() {

        int total = jTableDatos.getRowCount();

        // Si no hay nada o tabla vacía
        if (total <= 0) {
            jLabelResultadoFiltrado.setText("|| Sin resultados");
        } else {
            jLabelResultadoFiltrado.setText("||  Resultados Filtrados/Encontrados: " + total);
        }
    }

    void cargarTotalInicial() {

        LineaDAO dao = new LineaDAO();
        int total = dao.contarLineasTotales();
        jLabelTotalDatos.setText("" + total);
    }

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

    private void iniciarBackupAutomatico() {

        Timer timer = new Timer(60 * 60 * 1000, e -> {
            BackupBD.generarBackup();
        });

        timer.start();
    }

    private void ajustarColumnasTabla() {

        int ancho = jScrollPane1.getViewport().getWidth();

        if (ancho <= 0) {
            return;
        }

        TableColumnModel columnas = jTableDatos.getColumnModel();

        columnas.getColumn(0).setPreferredWidth((int) (ancho * 0.10));
        columnas.getColumn(1).setPreferredWidth((int) (ancho * 0.10));
        columnas.getColumn(2).setPreferredWidth((int) (ancho * 0.15));
        columnas.getColumn(3).setPreferredWidth((int) (ancho * 0.20));
        columnas.getColumn(4).setPreferredWidth((int) (ancho * 0.30));
        columnas.getColumn(5).setPreferredWidth((int) (ancho * 0.15));
    }

    private void configurarTabla() {
        jTableDatos.getColumnModel().getColumn(0).setPreferredWidth(130);
        jTableDatos.getColumnModel().getColumn(0).setResizable(false);
        jTableDatos.getColumnModel().getColumn(1).setPreferredWidth(130);
        jTableDatos.getColumnModel().getColumn(1).setResizable(false);
        jTableDatos.getColumnModel().getColumn(2).setPreferredWidth(180);
        jTableDatos.getColumnModel().getColumn(2).setResizable(false);
        jTableDatos.getColumnModel().getColumn(3).setPreferredWidth(300);
        jTableDatos.getColumnModel().getColumn(3).setResizable(false);
        jTableDatos.getColumnModel().getColumn(4).setPreferredWidth(400);
        jTableDatos.getColumnModel().getColumn(4).setResizable(false);
        jTableDatos.getColumnModel().getColumn(5).setPreferredWidth(140);
        jTableDatos.getColumnModel().getColumn(5).setResizable(false);
        jTableDatos.setRowHeight(25);
        jTableDatos.getTableHeader().setResizingAllowed(false);
        jTableDatos.getTableHeader().setReorderingAllowed(false);
    }

    private void actualizarProgreso(int valor, String mensaje) {

        jProgressBar1.setVisible(true);
        jProgressBar1.setValue(valor);
        jProgressBar1.setString(mensaje);

    }

    private void validarFiltros() {

        boolean estadoOk = CmbEstado.getSelectedIndex() > 0;
        boolean servicioOk = CmbServicio.getSelectedIndex() > 0;
        boolean municipioOk = CmbMunicipio.getSelectedIndex() > 0;

        String cantidad = TxtCantidad.getText().trim();

        boolean cantidadOk = !cantidad.isEmpty() && !cantidad.equals("#Cantidad Filtrado");

        BtnFiltrar.setEnabled(estadoOk || servicioOk || municipioOk || cantidadOk);
    }

    private String limpiarPDF(Object valor) {

        if (valor == null) {
            return "";
        }

        String v = valor.toString().trim();

        if (v.equalsIgnoreCase("null")) {
            return "";
        }
        if (v.equals("1900-01-01 00:00:00")) {
            return "";
        }
        if (v.equals("0")) {
            return "";
        }

        return v;
    }

    public void buscarActualizacion() {

        new Thread(() -> {

            try {

                String versionServer = VersionDAO.obtenerVersion();
                String url = VersionDAO.obtenerURL();
                String versionLocal = VersionLocal.obtenerVersionLocal();

                if (versionServer == null || versionServer.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No se pudo obtener la versión del servidor");
                    return;
                }

                // SI YA ESTÁ ACTUALIZADO
                if (versionServer.equals(versionLocal)) {
                    JOptionPane.showMessageDialog(null, "No hay actualizaciones disponibles");
                    return;
                }

                int opcion = JOptionPane.showConfirmDialog(null, "Nueva versión: " + versionServer + "\n¿Deseas actualizar?", "Actualización disponible", JOptionPane.YES_NO_OPTION);

                if (opcion == JOptionPane.YES_OPTION) {

                    // DESCARGAR INSTALADOR
                    String rutaInstalador = System.getProperty("user.home") + "\\Downloads\\LibertySystemSetup.exe";

                    Downloader.descargar(url, rutaInstalador);

                    JOptionPane.showMessageDialog(null, "Se descargó la actualización.\nSe abrirá el instalador.");

                    // EJECUTAR INSTALADOR
                    ProcessBuilder pb = new ProcessBuilder("cmd", "/c", "start", "", rutaInstalador
                    );
                    pb.start();

                    // IMPORTANTE: actualizar versión LOCAL ANTES DE CERRAR
                    VersionLocal.guardarVersionLocal(versionServer);

                    System.exit(0);
                }

            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Error: " + e.getMessage());
            }

        }).start();
    }

    private void configurarAtajosTeclado() {

        JRootPane root = this.getRootPane();

        InputMap im = root.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
        ActionMap am = root.getActionMap();

        // AGREGAR (Ctrl + N)
        im.put(KeyStroke.getKeyStroke("control N"), "agregar");
        am.put("agregar", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                BtnAgregar.doClick();
            }
        });

        // EDITAR (Ctrl + E)
        im.put(KeyStroke.getKeyStroke("control E"), "editar");
        am.put("editar", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                BtnEditar.doClick();
            }
        });

        // LIMPIAR (Ctrl + L)
        im.put(KeyStroke.getKeyStroke("control L"), "limpiar");
        am.put("limpiar", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                BtnLimpiar.doClick();
            }
        });

        // FILTRAR (Ctrl + F)
        im.put(KeyStroke.getKeyStroke("control F"), "filtrar");
        am.put("filtrar", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                BtnFiltrar.doClick();
            }
        });

        // ELIMINAR (SUPR)
        im.put(KeyStroke.getKeyStroke("DELETE"), "eliminar");
        am.put("eliminar", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                BtnEliminar.doClick();
            }
        });

        // IMPRIMIR (Ctrl + P)
        im.put(KeyStroke.getKeyStroke("control P"), "imprimir");
        am.put("imprimir", new AbstractAction() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                BtnImprimir.doClick();
            }
        });
    }

    private void setPlaceholder(javax.swing.JTextField txt, String placeholder) {

        txt.setText(placeholder);
        txt.setForeground(java.awt.Color.GRAY);
        if (TemaManager.oscuro) {
            txt.setCaretColor(java.awt.Color.WHITE);
        } else {
            txt.setCaretColor(java.awt.Color.BLACK);
        }
        txt.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent e) {

                if (txt.getText().equals(placeholder)) {
                    txt.setText("");
                    if (TemaManager.oscuro) {
                        txt.setForeground(java.awt.Color.WHITE);
                        txt.setCaretColor(java.awt.Color.WHITE);
                    } else {
                        txt.setForeground(java.awt.Color.BLACK);
                        txt.setCaretColor(java.awt.Color.BLACK);
                    }
                }
            }

            @Override
            public void focusLost(java.awt.event.FocusEvent e) {
                if (txt.getText().trim().isEmpty()) {
                    txt.setText(placeholder);
                    txt.setForeground(java.awt.Color.GRAY);
                    if (TemaManager.oscuro) {
                        txt.setCaretColor(java.awt.Color.WHITE);
                    } else {
                        txt.setCaretColor(java.awt.Color.BLACK);
                    }
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
        tglTema = new javax.swing.JToggleButton();
        BtnActualizacion = new javax.swing.JButton();
        TxtFieldBuscador = new javax.swing.JTextField();
        BtnLimpiar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableDatos = new javax.swing.JTable();
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
        jProgressBar1 = new javax.swing.JProgressBar();
        jLabel4 = new javax.swing.JLabel();
        LblManualdeUsuario = new javax.swing.JLabel();
        LblVerHistorial = new javax.swing.JLabel();
        BtnEditar = new javax.swing.JButton();
        BtnOrdenar = new javax.swing.JButton();
        BtnBackup = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        jPanel1.setBackground(new java.awt.Color(255, 153, 0));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Source Sans Pro Black", 0, 48)); // NOI18N
        jLabel1.setText("Liberty Networks | Panel de Consultas");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Logoliberty.png"))); // NOI18N
        jLabel2.setDisabledIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Logoliberty.png"))); // NOI18N

        tglTema.setText("🌙");
        tglTema.setToolTipText("Cambiar Tema");
        tglTema.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tglTema.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        tglTema.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tglTemaActionPerformed(evt);
            }
        });

        BtnActualizacion.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoactualizar.png"))); // NOI18N
        BtnActualizacion.setToolTipText("Buscar Actualizacion");
        BtnActualizacion.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        BtnActualizacion.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        BtnActualizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnActualizacionActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnActualizacion, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(102, 102, 102)
                        .addComponent(jLabel1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 137, Short.MAX_VALUE)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(26, 26, 26)
                .addComponent(tglTema, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(BtnActualizacion)
                .addGap(22, 22, 22)
                .addComponent(jLabel1)
                .addContainerGap(35, Short.MAX_VALUE))
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(tglTema)
                .addGap(0, 0, Short.MAX_VALUE))
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

        BtnLimpiar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnLimpiar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconolimpiar.png"))); // NOI18N
        BtnLimpiar.setText("Limpiar");
        BtnLimpiar.setToolTipText("Recarga de la tabla");
        BtnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnLimpiarActionPerformed(evt);
            }
        });

        jTableDatos.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        jTableDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Numero", "Estado", "Fecha_Ultimo_Estado", "Municipio", "Cliente", "Servicio"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableDatos.setRowHeight(25);
        jTableDatos.setShowGrid(true);
        jTableDatos.getTableHeader().setResizingAllowed(false);
        jTableDatos.getTableHeader().setReorderingAllowed(false);
        jScrollPane1.setViewportView(jTableDatos);

        BtnEliminar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnEliminar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoeliminar.png"))); // NOI18N
        BtnEliminar.setText("Eliminar");
        BtnEliminar.setToolTipText("Elimina las lineas seleccionadas");
        BtnEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEliminarActionPerformed(evt);
            }
        });

        BtnAgregar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnAgregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoagregar.png"))); // NOI18N
        BtnAgregar.setText("Agregar");
        BtnAgregar.setToolTipText("Abrir panel de agregado");
        BtnAgregar.setPreferredSize(null);
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
        CmbServicio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CmbServicio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        BtnFiltrar.setToolTipText("Busqueda por filtros");
        BtnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnFiltrarActionPerformed(evt);
            }
        });

        CmbEstado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        CmbEstado.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar Estado", "Libre", "Cancelado", "En Servicio", "Reservado" }));
        CmbEstado.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CmbEstado.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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
        BtnImprimir.setToolTipText("Imprimir reporte en PDF");
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
        CmbMunicipio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        CmbMunicipio.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
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

        jProgressBar1.setForeground(new java.awt.Color(0, 153, 0));
        jProgressBar1.setEnabled(false);
        jProgressBar1.setStringPainted(true);

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconointerrogacion.png"))); // NOI18N
        jLabel4.setToolTipText("Atajos:\n\n   Agregar (Ctrl N)\n\n   Eliminar (SUPR)\n\n   Imprimir (Ctrl P)\n\n   Filtrar (Ctrl F)\n\n   Limpiar (Ctrl L)");
        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        LblManualdeUsuario.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblManualdeUsuario.setText("Link Manual de Usuario ||");
        LblManualdeUsuario.setToolTipText("Lee el Manual de Usuario");
        LblManualdeUsuario.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LblManualdeUsuario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblManualdeUsuarioMouseClicked(evt);
            }
        });

        LblVerHistorial.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        LblVerHistorial.setText("Ver Historial");
        LblVerHistorial.setToolTipText("Ver Historial de Cambios en el Sistema");
        LblVerHistorial.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        LblVerHistorial.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                LblVerHistorialMouseClicked(evt);
            }
        });
        LblVerHistorial.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                LblVerHistorialKeyPressed(evt);
            }
        });

        BtnEditar.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        BtnEditar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoeditar.png"))); // NOI18N
        BtnEditar.setText("Editar");
        BtnEditar.setToolTipText("Abrir panel de edicion");
        BtnEditar.setPreferredSize(null);
        BtnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnEditarActionPerformed(evt);
            }
        });

        BtnOrdenar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconoordenar.png"))); // NOI18N
        BtnOrdenar.setToolTipText("Ordenar Numeros");
        BtnOrdenar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnOrdenarActionPerformed(evt);
            }
        });

        BtnBackup.setIcon(new javax.swing.ImageIcon(getClass().getResource("/IMG/Iconobackup.png"))); // NOI18N
        BtnBackup.setToolTipText("Generar Backup");
        BtnBackup.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BtnBackupActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(TxtFieldBuscador)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CmbMunicipio, 0, 1, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 222, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(CmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(TxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnOrdenar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnBackup)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 245, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelTotalDatos, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelResultadoFiltrado, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(BtnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(BtnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(1, 1, 1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(BtnEditar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblManualdeUsuario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(LblVerHistorial)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(BtnAgregar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(4, 4, 4)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnEliminar, javax.swing.GroupLayout.DEFAULT_SIZE, 39, Short.MAX_VALUE)
                    .addComponent(BtnLimpiar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(TxtFieldBuscador))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(BtnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(TxtCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CmbServicio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(CmbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(BtnImprimir, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(CmbMunicipio, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel3)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(BtnOrdenar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 20, Short.MAX_VALUE)
                        .addComponent(jLabel6)
                        .addComponent(jLabelResultadoFiltrado)
                        .addComponent(jLabelTotalDatos))
                    .addComponent(BtnBackup, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 647, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(BtnAgregar, javax.swing.GroupLayout.DEFAULT_SIZE, 51, Short.MAX_VALUE)
                    .addComponent(LblVerHistorial, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(LblManualdeUsuario, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(BtnEditar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void LblVerHistorialKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_LblVerHistorialKeyPressed

    }//GEN-LAST:event_LblVerHistorialKeyPressed

    private void LblVerHistorialMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblVerHistorialMouseClicked

        if (frmHistorial == null || !frmHistorial.isVisible()) {

            frmHistorial = new FrmHistorial();
            frmHistorial.setVisible(true);

        } else {

            java.awt.Toolkit.getDefaultToolkit().beep(); // sonido de advertencia
            frmHistorial.toFront();
            frmHistorial.requestFocus();
        }

    }//GEN-LAST:event_LblVerHistorialMouseClicked

    private void LblManualdeUsuarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_LblManualdeUsuarioMouseClicked

        try {

            InputStream input = getClass().getResourceAsStream("/manual/Manual de Usuario - Liberty Networks Sistema de Consulta de Lineas.pdf");

            if (input == null) {
                JOptionPane.showMessageDialog(this, "No se encontró el manual.");
                return;
            }

            File archivoTemporal = File.createTempFile("ManualUsuario", ".pdf");

            archivoTemporal.deleteOnExit();

            Files.copy(input, archivoTemporal.toPath(), StandardCopyOption.REPLACE_EXISTING);

            Desktop.getDesktop().open(archivoTemporal);

        } catch (Exception e) {

            JOptionPane.showMessageDialog(this, "Error al abrir el manual:\n" + e.getMessage());
        }

    }//GEN-LAST:event_LblManualdeUsuarioMouseClicked

    private void CmbMunicipioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbMunicipioActionPerformed

        validarFiltros();

    }//GEN-LAST:event_CmbMunicipioActionPerformed

    private void CmbMunicipioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbMunicipioItemStateChanged

    }//GEN-LAST:event_CmbMunicipioItemStateChanged

    private void BtnImprimirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnImprimirActionPerformed

        SwingWorker<Void, Integer> worker = new SwingWorker<>() {
            boolean cancelado = false;

            String ruta;
            String tituloDinamico;

            @Override
            protected Void doInBackground() throws Exception {

                publish(5);

                JFileChooser fileChooser = new JFileChooser();
                fileChooser.setDialogTitle("Guardar PDF");

                int option = fileChooser.showSaveDialog(FrmConsulta.this);

                if (option != JFileChooser.APPROVE_OPTION) {
                    cancelado = true;
                    publish(0);
                    return null;
                }

                ruta = fileChooser.getSelectedFile().getAbsolutePath();

                String nombreArchivo = fileChooser.getSelectedFile().getName();

                if (nombreArchivo.toLowerCase().endsWith(".pdf")) {
                    nombreArchivo = nombreArchivo.substring(0, nombreArchivo.length() - 4);
                }

                tituloDinamico = "LIBERTY NETWORKS - " + nombreArchivo.toUpperCase();

                if (!ruta.endsWith(".pdf")) {
                    ruta += ".pdf";
                }

                publish(15);

                Document documento = new Document(PageSize.A4.rotate(), 20, 20, 20, 20);
                PdfWriter.getInstance(documento, new FileOutputStream(ruta));

                documento.open();

                publish(25);

                //LOGO
                try {
                    com.itextpdf.text.Image logo = com.itextpdf.text.Image.getInstance("src/IMG/Logoliberty.png");

                    logo.scaleToFit(100, 100);
                    logo.setAlignment(Element.ALIGN_CENTER);

                    documento.add(logo);

                } catch (DocumentException | IOException e) {
                    System.out.println("Logo no encontrado");
                }

                publish(35);

                //TITULO
                Font tituloFont = new Font(Font.FontFamily.HELVETICA, 18, Font.BOLD, BaseColor.BLACK);

                Paragraph titulo = new Paragraph(tituloDinamico, tituloFont);
                titulo.setAlignment(Element.ALIGN_CENTER);

                documento.add(titulo);
                documento.add(new Paragraph(" "));

                publish(45);

                //FECHA
                String fecha = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(new java.util.Date());

                Paragraph fechaTexto = new Paragraph("Generado: " + fecha);
                fechaTexto.setAlignment(Element.ALIGN_RIGHT);

                documento.add(fechaTexto);
                documento.add(new Paragraph(" "));

                publish(55);

                //TABLA
                PdfPTable tabla = new PdfPTable(jTableDatos.getColumnCount());
                tabla.setWidthPercentage(100);

                float[] widths = new float[jTableDatos.getColumnCount()];
                for (int i = 0; i < widths.length; i++) {
                    widths[i] = 1f;
                }
                tabla.setWidths(widths);

                Font headerFont = new Font(Font.FontFamily.HELVETICA, 10, Font.BOLD, BaseColor.WHITE);
                Font dataFont = new Font(Font.FontFamily.HELVETICA, 9, Font.NORMAL, BaseColor.BLACK);

                //ENCABEZADOS
                for (int i = 0; i < jTableDatos.getColumnCount(); i++) {

                    PdfPCell header = new PdfPCell(new Paragraph(jTableDatos.getColumnName(i), headerFont));

                    header.setBackgroundColor(new BaseColor(255, 153, 0));
                    header.setHorizontalAlignment(Element.ALIGN_CENTER);
                    header.setPadding(6);
                    tabla.addCell(header);
                }

                publish(65);

                //DATOS
                for (int fila = 0; fila < jTableDatos.getRowCount(); fila++) {

                    for (int col = 0; col < jTableDatos.getColumnCount(); col++) {

                        Object valor = jTableDatos.getValueAt(fila, col);
                        String texto = limpiarPDF(valor);
                        PdfPCell cell = new PdfPCell(new Paragraph(texto, dataFont));

                        if (fila % 2 == 0) {
                            cell.setBackgroundColor(new BaseColor(245, 245, 245));
                        }
                        tabla.addCell(cell);
                    }

                    int progreso = 65 + (int) ((fila * 25.0) / jTableDatos.getRowCount());
                    publish(progreso);
                }

                documento.add(tabla);
                publish(95);
                Paragraph firma = new Paragraph("Generado automáticamente por Liberty Networks", new Font(Font.FontFamily.HELVETICA, 9, Font.ITALIC, BaseColor.GRAY));
                firma.setAlignment(Element.ALIGN_CENTER);
                documento.add(firma);
                documento.close();
                publish(100);

                return null;
            }

            @Override
            protected void process(java.util.List<Integer> chunks) {
                int valor = chunks.get(chunks.size() - 1);
                jProgressBar1.setVisible(true);
                jProgressBar1.setValue(valor);
                jProgressBar1.setString("Generando PDF... " + valor + "%");
            }

            @Override
            protected void done() {
                if (cancelado) {
                    jProgressBar1.setVisible(false);
                    jProgressBar1.setValue(0);
                    jProgressBar1.setString("");

                    return; //NO mostrar mensaje de éxito
                }

                JOptionPane.showMessageDialog(FrmConsulta.this, "PDF generado correctamente");

                new javax.swing.Timer(2000, e -> {
                    jProgressBar1.setVisible(false);
                    jProgressBar1.setValue(0);
                    jProgressBar1.setString("");
                }).start();
            }
        };
        worker.execute();

    }//GEN-LAST:event_BtnImprimirActionPerformed

    private void CmbEstadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbEstadoActionPerformed

        validarFiltros();

    }//GEN-LAST:event_CmbEstadoActionPerformed

    private void CmbEstadoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbEstadoItemStateChanged

    }//GEN-LAST:event_CmbEstadoItemStateChanged

    private void BtnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnFiltrarActionPerformed

        int estadoIndex = CmbEstado.getSelectedIndex();
        int servicioIndex = CmbServicio.getSelectedIndex();
        int municipioIndex = CmbMunicipio.getSelectedIndex();

        String estado = CmbEstado.getSelectedItem().toString();
        String servicio = CmbServicio.getSelectedItem().toString();
        String municipio = CmbMunicipio.getSelectedItem().toString();

        String cantidadTxt = TxtCantidad.getText().trim();

        Integer cantidad = null;

        boolean sinFiltros = estadoIndex == 0 && servicioIndex == 0 && municipioIndex == 0 && cantidadTxt.isEmpty();

        if (sinFiltros) {
            JOptionPane.showMessageDialog(this, "Por favor seleccione filtros");
            jTableDatos.setModel(new javax.swing.table.DefaultTableModel());
            jLabelResultadoFiltrado.setText("");
            return;
        }

        try {
            if (!cantidadTxt.isEmpty() && !cantidadTxt.equals("#Cantidad Filtrado")) {
                cantidad = Integer.valueOf(cantidadTxt);
                if (cantidad <= 0) {
                    cantidad = null;
                }
            }
        } catch (NumberFormatException e) {
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

        // ACTUALIZAR LABEL
        actualizarLabelResultado();

    }//GEN-LAST:event_BtnFiltrarActionPerformed

    private void TxtCantidadKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCantidadKeyTyped

        Validaciones.soloNumeros8Digitos(TxtCantidad, evt);

    }//GEN-LAST:event_TxtCantidadKeyTyped

    private void TxtCantidadKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtCantidadKeyReleased

        validarFiltros();

    }//GEN-LAST:event_TxtCantidadKeyReleased

    private void TxtCantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtCantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtCantidadActionPerformed

    private void CmbServicioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CmbServicioActionPerformed

        validarFiltros();

    }//GEN-LAST:event_CmbServicioActionPerformed

    private void CmbServicioItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CmbServicioItemStateChanged

    }//GEN-LAST:event_CmbServicioItemStateChanged

    private void BtnAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnAgregarActionPerformed

        if (frmAgregado == null || !frmAgregado.isVisible()) {
            frmAgregado = new FrmAgregado(this);
            frmAgregado.setVisible(true);
        } else {
            Toolkit.getDefaultToolkit().beep();
            frmAgregado.toFront();
            frmAgregado.requestFocus();
        }

    }//GEN-LAST:event_BtnAgregarActionPerformed

    private void BtnEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEliminarActionPerformed

        int[] filas = jTableDatos.getSelectedRows();

        if (filas.length == 0) {
            javax.swing.JOptionPane.showMessageDialog(this, "Selecciona al menos una fila");
            return;
        }

        int confirm = javax.swing.JOptionPane.showConfirmDialog(this, "¿Seguro que deseas eliminar " + filas.length + " registros?", "Confirmar eliminación", javax.swing.JOptionPane.YES_NO_OPTION);

        if (confirm != javax.swing.JOptionPane.YES_OPTION) {
            return;
        }

        LineaDAO dao = new LineaDAO();
        boolean error = false;

        // IMPORTANTE: elimina de abajo hacia arriba para evitar problemas visuales
        for (int i = filas.length - 1; i >= 0; i--) {

            int fila = filas[i];

            String numero = jTableDatos.getValueAt(fila, 0).toString();

            boolean ok = dao.eliminarLinea(numero);

            if (!ok) {
                error = true;
            }
        }

        if (!error) {
            javax.swing.JOptionPane.showMessageDialog(this, "Eliminados correctamente");
        } else {
            javax.swing.JOptionPane.showMessageDialog(this, "Algunos registros no se pudieron eliminar");
        }

        cargarTabla();

    }//GEN-LAST:event_BtnEliminarActionPerformed

    private void BtnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnEditarActionPerformed

        int[] filas = jTableDatos.getSelectedRows();

        if (filas.length == 0) {
            JOptionPane.showMessageDialog(this, "Selecciona al menos una fila");
            return;
        }

        java.util.List<Object[]> lista = new java.util.ArrayList<>();

        for (int i : filas) {
            Object[] fila = new Object[6];
            for (int c = 0; c < 6; c++) {
                fila[c] = jTableDatos.getValueAt(i, c);
            }
            lista.add(fila);
        }

        if (frmEditar == null || !frmEditar.isVisible()) {
            frmEditar = new FrmEditar();
            frmEditar.cargarDatos(this, lista);
            frmEditar.setVisible(true);
        } else {
            Toolkit.getDefaultToolkit().beep(); // sonido de advertencia
            frmEditar.toFront();
            frmEditar.requestFocus();
        }

    }//GEN-LAST:event_BtnEditarActionPerformed

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

        TxtFieldBuscador.setText(" Buscar por Numero o Cliente");
        TxtFieldBuscador.setForeground(java.awt.Color.BLACK);
        TxtFieldBuscador.requestFocus();

        // Recargar tabla completa
        cargarTabla();
        cargarTotalInicial();

        // Actualizar botón filtrar
        validarFiltros();

        jLabelResultadoFiltrado.setText("");

    }//GEN-LAST:event_BtnLimpiarActionPerformed

    private void TxtFieldBuscadorKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFieldBuscadorKeyTyped

        // máximo 50 caracteres
        if (TxtFieldBuscador.getText().length() >= 50) {
            evt.consume();
        }

    }//GEN-LAST:event_TxtFieldBuscadorKeyTyped

    private void TxtFieldBuscadorKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_TxtFieldBuscadorKeyReleased

        String texto = TxtFieldBuscador.getText().trim();

        if (texto.isEmpty() || texto.equals("Buscar por Numero o Cliente") || texto.equals(" Buscar por Numero o Cliente")) {
            cargarTabla();
            jLabelResultadoFiltrado.setText("");
            return;
        }

        LineaDAO dao = new LineaDAO();
        jTableDatos.setModel(dao.buscarNumeroOCliente(texto));
        jTableDatos.setDefaultEditor(Object.class, null);
        configurarTabla();
        actualizarLabelResultado();

    }//GEN-LAST:event_TxtFieldBuscadorKeyReleased

    private void TxtFieldBuscadorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_TxtFieldBuscadorActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_TxtFieldBuscadorActionPerformed

    private void tglTemaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tglTemaActionPerformed

        TemaManager.cambiarTema();
        aplicarColoresPersonalizados();

        //GUARDAR EN ARCHIVO
        ConfigTema.guardarTema(TemaManager.oscuro);

        //refrescar otros abiertos
        for (java.awt.Window w : java.awt.Window.getWindows()) {
            if (w instanceof javax.swing.JFrame) {
                w.repaint();
            }
        }

    }//GEN-LAST:event_tglTemaActionPerformed

    private void BtnActualizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnActualizacionActionPerformed

        buscarActualizacion();

    }//GEN-LAST:event_BtnActualizacionActionPerformed

    private void BtnOrdenarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnOrdenarActionPerformed

        LineaDAO dao = new LineaDAO();
        jTableDatos.setModel(dao.mostrarDatosOrdenadosPorNumero());

    }//GEN-LAST:event_BtnOrdenarActionPerformed

    private void BtnBackupActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BtnBackupActionPerformed

        int confirm = JOptionPane.showConfirmDialog(this, "¿Desea generar un backup de la base de datos?", "Confirmar Backup", JOptionPane.YES_NO_OPTION);

        if (confirm != JOptionPane.YES_OPTION) {
            return;
        }

        new Thread(() -> {

            BtnBackup.setEnabled(false);

            BackupBD.generarBackup();

            SwingUtilities.invokeLater(() -> {

                BtnBackup.setEnabled(true);

                JOptionPane.showMessageDialog(this, "Backup generado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            });

        }).start();

    }//GEN-LAST:event_BtnBackupActionPerformed

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
//        try {
//            UIManager.setLookAndFeel(new FlatDarkLaf());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {
            new FrmConsulta().setVisible(true);
        });
    }

    private void SetImageLabel(JLabel labelName, String path) {

        java.net.URL url = getClass().getResource(path);

        if (url == null) {
            System.out.println("No se encontró: " + path);
            return;
        }

        ImageIcon image = new ImageIcon(url);

        Icon icon = new ImageIcon(image.getImage().getScaledInstance(labelName.getWidth(), labelName.getHeight(), Image.SCALE_SMOOTH));
        labelName.setIcon(icon);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BtnActualizacion;
    private javax.swing.JButton BtnAgregar;
    private javax.swing.JButton BtnBackup;
    private javax.swing.JButton BtnEditar;
    private javax.swing.JButton BtnEliminar;
    private javax.swing.JButton BtnFiltrar;
    private javax.swing.JButton BtnImprimir;
    private javax.swing.JButton BtnLimpiar;
    private javax.swing.JButton BtnOrdenar;
    private javax.swing.JComboBox<String> CmbEstado;
    private javax.swing.JComboBox<String> CmbMunicipio;
    private javax.swing.JComboBox<String> CmbServicio;
    private javax.swing.JLabel LblManualdeUsuario;
    private javax.swing.JLabel LblVerHistorial;
    private javax.swing.JTextField TxtCantidad;
    private javax.swing.JTextField TxtFieldBuscador;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabelResultadoFiltrado;
    private javax.swing.JLabel jLabelTotalDatos;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableDatos;
    private javax.swing.JToggleButton tglTema;
    // End of variables declaration//GEN-END:variables
}
