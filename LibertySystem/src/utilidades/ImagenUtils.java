package utilidades;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ImagenUtils {

    public static Icon invertirIcono(Class<?> clazz, String path) {

        try {
            java.net.URL url = clazz.getResource(path);

            if (url == null) {
                System.out.println("NO SE ENCONTRÓ: " + path);
                return new ImageIcon();
            }

            BufferedImage img = ImageIO.read(url);

            for (int y = 0; y < img.getHeight(); y++) {
                for (int x = 0; x < img.getWidth(); x++) {

                    int rgba = img.getRGB(x, y);
                    Color c = new Color(rgba, true);

                    int r = 255 - c.getRed();
                    int g = 255 - c.getGreen();
                    int b = 255 - c.getBlue();

                    Color invertida = new Color(r, g, b, c.getAlpha());

                    img.setRGB(x, y, invertida.getRGB());
                }
            }
            return new ImageIcon(img);

        } catch (Exception e) {
            e.printStackTrace();
            return new ImageIcon();
        }
    }
}
