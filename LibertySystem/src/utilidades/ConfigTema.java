package utilidades;

import java.io.*;
import java.util.Properties;

public class ConfigTema {

    private static final String APP_NAME = "LibertySystem";

    private static File getFile() { // UBICACIÓN FIJA (AppData o similar)

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

    public static void guardarTema(boolean oscuro) {  // GUARDAR TEMA
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

    public static boolean cargarTemaOscuro() { // CARGAR TEMA
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
