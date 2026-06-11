package Formularios;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import utilidades.TemaManager;

public class ComboBoxTemaUI extends BasicComboBoxUI {

    private Icon icono;

    public ComboBoxTemaUI(Icon icono) {
        this.icono = icono;
    }

    @Override
    protected JButton createArrowButton() {

        JButton button = new JButton();
        button.setBorder(null);
        button.setFocusPainted(false);
        button.setIcon(icono);

        return button;
    }
}
