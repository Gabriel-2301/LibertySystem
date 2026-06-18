package utilidades;

import java.io.*;
import java.net.*;

public class Downloader {

    public static void descargar(String urlStr, String destino) throws Exception {

        URL url = URI.create(urlStr).toURL();

        try (InputStream in = url.openStream(); FileOutputStream out = new FileOutputStream(destino)) {

            byte[] buffer = new byte[4096];
            int len;

            while ((len = in.read(buffer)) != -1) {
                out.write(buffer, 0, len);
            }
        }
    }
}
