package utilidades;

import java.io.File;
import java.io.InputStream;
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

        String fecha = new java.text.SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                .format(new java.util.Date());

        String archivo = carpetaBackup + "liberty_backup_" + fecha + ".sql";

        ProcessBuilder pb = new ProcessBuilder(
                rutaMySQL,
                "-u" + dbUser,
                dbPass.isEmpty() ? "" : "-p" + dbPass,
                dbName
        );

        pb.redirectOutput(new File(archivo));

        Process process = pb.start();

        InputStream errorStream = process.getErrorStream();
        String error = new String(errorStream.readAllBytes());

        int exitCode = process.waitFor();

        if (exitCode == 0) {
            System.out.println("Backup creado: " + archivo);
        } else {
            System.out.println("ERROR BACKUP: " + error);
        }

    } catch (Exception e) {
        System.out.println("Error backup general: " + e);
    }
}
}