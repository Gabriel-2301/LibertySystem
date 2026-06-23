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

            BufferedImage original = ImageIO.read(url);
            BufferedImage img = new BufferedImage(original.getWidth(), original.getHeight(), BufferedImage.TYPE_INT_ARGB);
            img.getGraphics().drawImage(original, 0, 0, null);

            int width = img.getWidth();
            int height = img.getHeight();
            for (int y = 0; y < height; y++) {
                for (int x = 0; x < width; x++) {
                    int rgba = img.getRGB(x, y);
                    Color c = new Color(rgba, true);
                    Color invertida = new Color(255 - c.getRed(), 255 - c.getGreen(), 255 - c.getBlue(), c.getAlpha());
                    img.setRGB(x, y, invertida.getRGB());
                }
            }
            return new ImageIcon(img);
        } catch (Exception e) {
            System.err.println("Error al invertir icono: " + e.getMessage());
            return new ImageIcon();
        }
    }
}
