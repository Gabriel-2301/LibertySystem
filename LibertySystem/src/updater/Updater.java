package updater;

import java.io.File;

public class Updater {

    public static void main(String[] args) {
        try {
            String rutaFlag = System.getProperty("user.home")+ "\\Downloads\\restart.flag";
            File flag = new File(rutaFlag);
            Thread.sleep(3000);

            if (!flag.exists()) {
                return;
            }

            flag.delete();
            String rutaApp = System.getProperty("user.home")+ "\\AppData\\Local\\LibertySystem\\LibertySystem.exe";
            File exe = new File(rutaApp);

            if (!exe.exists()) {
                System.out.println("NO EXISTE: " + rutaApp);
                return;
            }

            ProcessBuilder pb = new ProcessBuilder(rutaApp);
            pb.start();
            System.out.println("APP REABRIDA");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
