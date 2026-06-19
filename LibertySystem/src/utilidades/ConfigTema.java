package utilidades;

import java.io.*;
import java.util.Properties;

public class ConfigTema {

    private static final String APP_NAME = "LibertySystem";

    // UBICACIÓN FIJA (AppData o similar)
    private static File getFile() {

        String os = System.getProperty("os.name").toLowerCase();
        File dir;

        if (os.contains("win")) {
            dir = new File(System.getenv("APPDATA"), APP_NAME);
        } else {
            dir = new File(System.getProperty("user.home"), "." + APP_NAME.toLowerCase());
        }

        if (!dir.exists()) {
            dir.mkdirs();
        }
        return new File(dir, "config.properties");
    }

    // GUARDAR TEMA
    public static void guardarTema(boolean oscuro) {
        try {
            Properties p = new Properties();
            p.setProperty("tema", oscuro ? "oscuro" : "claro");
            FileOutputStream out = new FileOutputStream(getFile());
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

            File file = getFile();
            if (!file.exists()) {
                return false;
            }

            FileInputStream in = new FileInputStream(file);
            p.load(in);
            in.close();
            
            return "oscuro".equals(p.getProperty("tema"));
            
        } catch (Exception e) {
            return false;
        }
    }
}
