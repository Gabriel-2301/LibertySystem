package libertysystem;

import javax.swing.*;
import java.awt.*;

public class Splash extends JWindow {

    private JProgressBar barra;
    private JLabel texto;
    private Timer animTimer;
    private Timer fadeTimer;
    private int objetivo = 0;

    public Splash() {
        JLabel imagen = new JLabel(new ImageIcon(getClass().getResource("/IMG/Splashliberty.png"))); // IMAGEN
        texto = new JLabel("Iniciando sistema...", SwingConstants.CENTER);// TEXTO
        texto.setForeground(Color.WHITE);
        texto.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        barra = new JProgressBar(0, 100); // BARRA NARANJA
        barra.setValue(0);
        barra.setStringPainted(false);
        barra.setForeground(new Color(255, 140, 0));
        barra.setBackground(new Color(40, 40, 40));
        barra.setPreferredSize(new Dimension(400, 18));
        barra.setBorderPainted(false);
        JPanel inferior = new JPanel(new BorderLayout());
        inferior.setBackground(new Color(25, 25, 25));
        inferior.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        inferior.add(texto, BorderLayout.NORTH);
        inferior.add(barra, BorderLayout.SOUTH);
        JPanel main = new JPanel(new BorderLayout());
        main.setBackground(Color.BLACK);
        main.add(imagen, BorderLayout.CENTER);
        main.add(inferior, BorderLayout.SOUTH);
        setContentPane(main);
        pack();
        setLocationRelativeTo(null);
        setOpacity(0f);// FADE IN
        fadeTimer = new Timer(20, e -> {
            float op = getOpacity();
            op += 0.05f;
            if (op >= 1f) {
                op = 1f;
                fadeTimer.stop();
            }
            setOpacity(op);
        });
        animTimer = new Timer(15, e -> { // BARRA SUAVE
            int actual = barra.getValue();
            if (actual < objetivo) {
                barra.setValue(actual + 1);
            } else {
                animTimer.stop();
            }
        });
    }

    @Override
    public void setVisible(boolean b) {
        super.setVisible(b);
        if (b) {
            setOpacity(0f);
            fadeTimer.start();
        }
    }

    public void setEstado(int valor, String mensaje) {
        objetivo = valor;
        texto.setText(mensaje);
        if (!animTimer.isRunning()) {
            animTimer.start();
        }
    }
}
