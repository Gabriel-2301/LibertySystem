package utilidades;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BackupBD {

    public static void generarBackup() {
        try {
            String dbName = "liberty";
            String dbUser = "root";
            String dbPass = "1234";
            String rutaMySQL = "C:\\Program Files\\MySQL\\MySQL Server 8.0\\bin\\mysqldump.exe";
            String carpetaBackup = "C:\\BackupsLiberty\\";
            File dir = new File(carpetaBackup);
            if (!dir.exists()) {
                dir.mkdirs();
            }
            String fecha = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss").format(new Date()); // Backup único por fecha + hora (permite múltiples backups manuales)
            String archivo = carpetaBackup + "liberty_backup_" + fecha + ".sql";
            ProcessBuilder pb;

            if (dbPass == null || dbPass.isEmpty()) {
                pb = new ProcessBuilder(rutaMySQL, "-u" + dbUser, dbName);
            } else {
                pb = new ProcessBuilder(rutaMySQL, "-u" + dbUser, "-p" + dbPass, dbName);
            }

            pb.redirectOutput(new File(archivo));
            Process process = pb.start();
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("Backup creado: " + archivo);
            } else {
                System.out.println("Error al crear backup");
            }
        } catch (Exception e) {
            System.out.println("Error backup general: " + e.getMessage());
        }
    }
}
