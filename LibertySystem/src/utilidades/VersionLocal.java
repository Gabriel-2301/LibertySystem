package utilidades;

import java.io.*;

public class VersionLocal {

    private static String ruta() {
        return System.getenv("APPDATA") + "\\LibertySystem\\version.dat";
    }

    public static String obtenerVersionLocal() {

        File file = new File(ruta());

        if (!file.exists()) {
            return "0.0";
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {

            String version = br.readLine();

            if (version == null) {
                return "0.0";
            }

            return version.trim();

        } catch (Exception e) {
            return "0.0";
        }
    }

    public static void guardarVersionLocal(String version) {

        try {
            File dir = new File(System.getenv("APPDATA") + "\\LibertySystem");

            if (!dir.exists()) {
                dir.mkdirs();
            }

            try (FileWriter fw = new FileWriter(ruta(), false)) {
                fw.write(version.trim());
                fw.flush();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
