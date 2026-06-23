package libertysystem;

import conexion.Conexion;
import Formularios.FrmConsulta;
import utilidades.ConfigTema;
import utilidades.TemaManager;
import utilidades.VersionLocal;
import javax.swing.JOptionPane;

public class LibertySystem {

    public static final String VERSION_LOCAL = "1.0";

    public static void main(String[] args) {

        Splash splash = new Splash();
        splash.setVisible(true);

        try {
            splash.setEstado(10, "Verificando sistema...");
            Thread.sleep(500);
            String localVersion = VersionLocal.obtenerVersionLocal();

            if (localVersion == null || localVersion.equals("0.0")) {
                VersionLocal.guardarVersionLocal(VERSION_LOCAL);
            }

            splash.setEstado(30, "Cargando configuración...");
            Thread.sleep(800);
            TemaManager.oscuro = ConfigTema.cargarTemaOscuro();
            splash.setEstado(60, "Conectando a base de datos...");
            Thread.sleep(1500);
            splash.setEstado(60, "Cargandos datos y modulos...");
            Thread.sleep(500);

            boolean ok = Conexion.probarConexion();

            if (!ok) {
                splash.dispose();
                JOptionPane.showMessageDialog(null, "Base de datos caída.\nVerifique el servidor o la conexión.", "Error de conexión", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }

            splash.setEstado(90, "Abriendo sistema Liberty...");
            Thread.sleep(1500);
            splash.setEstado(100, "Sistema Iniciado");
            Thread.sleep(800);

            splash.dispose();
            java.awt.EventQueue.invokeLater(() -> {
                new FrmConsulta().setVisible(true);
            });

        } catch (Exception e) {
            splash.dispose();
            JOptionPane.showMessageDialog(null, "Error al iniciar:\n" + e.getMessage());
            System.exit(0);
        }
    }
}
