package utilidades;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

public class TemaManager {

    public static boolean oscuro = false;

    public static void cambiarTema() {
        oscuro = !oscuro;
    }

    public static void aplicar(JFrame frame, Runnable aplicador) {
        aplicador.run();
        frame.repaint();
    }

    public static Icon invertirIcono(Class<?> clase, String ruta) {

        ImageIcon icon = new ImageIcon(clase.getResource(ruta));

        BufferedImage img = new BufferedImage(icon.getIconWidth(), icon.getIconHeight(), BufferedImage.TYPE_INT_ARGB);

        Graphics2D g = img.createGraphics();
        icon.paintIcon(null, g, 0, 0);
        g.dispose();

        for (int x = 0; x < img.getWidth(); x++) {
            for (int y = 0; y < img.getHeight(); y++) {

                int rgba = img.getRGB(x, y);
                Color col = new Color(rgba, true);

                Color invertido = new Color(255 - col.getRed(), 255 - col.getGreen(), 255 - col.getBlue(), col.getAlpha());

                img.setRGB(x, y, invertido.getRGB());
            }
        }

        return new ImageIcon(img);
    }

}
