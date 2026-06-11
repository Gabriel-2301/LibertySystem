package utilidades;

import java.io.*;
import java.util.Properties;

public class ConfigTema {

    private static final String FILE = "config.properties";

    // GUARDAR TEMA
    public static void guardarTema(boolean oscuro) {
        try {
            Properties p = new Properties();
            p.setProperty("tema", oscuro ? "oscuro" : "claro");

            FileOutputStream out = new FileOutputStream(FILE);
            p.store(out, "Configuración de tema");
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // CARGAR TEMA
    public static boolean cargarTemaOscuro() {
        try {
            Properties p = new Properties();

            FileInputStream in = new FileInputStream(FILE);
            p.load(in);
            in.close();

            return "oscuro".equals(p.getProperty("tema"));

        } catch (Exception e) {
            return false; // por defecto claro
        }
    }
}