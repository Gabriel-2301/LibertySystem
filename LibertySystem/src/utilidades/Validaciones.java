package utilidades;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Validaciones {

    // VALIDAR CAMPO VACÍO
    public static boolean campoVacio(JTextField txt, String mensaje) {

        if (txt.getText().trim().isEmpty()) {

            JOptionPane.showMessageDialog(null, mensaje);
            txt.requestFocus();
            return true;
        }
        return false;
    }

    // VALIDAR SOLO NÚMEROS Y 8 DÍGITOS
    public static void soloNumeros8Digitos(JTextField txt, KeyEvent evt) {

        char c = evt.getKeyChar();

        // PERMITIR BACKSPACE Y DELETE
        if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
            return;
        }

        // SOLO NÚMEROS
        if (!Character.isDigit(c)) {
            evt.consume();
            return;
        }

        // MÁXIMO 8 DÍGITOS
        if (txt.getText().length() >= 11) {
            evt.consume();
        }
    }
    
    public static boolean validarNumeroLinea(String numero) {

    return numero.matches("\\d{8}|\\d{11}");
}

    public static void soloMax50Caracteres(JTextField txt, KeyEvent evt) {

        // permitir control keys
        if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE) {
            return;
        }

        // máximo 50 caracteres
        if (txt.getText().length() >= 50) {
            evt.consume();
        }
    }

    public static void buscadorNumeroCliente(JTextField txt, KeyEvent evt) {

        // Máximo 50 caracteres
        if (txt.getText().length() >= 50) {

            if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE) {
                return;
            }
            evt.consume();
        }
    }

    public static boolean validarFechaFormato(String fecha) {
        // SOLO permite: números, /, espacio 
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}")) {
            return false;
        }

        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        sdf.setLenient(false);

        try {
            sdf.parse(fecha);
            return true;

        } catch (java.text.ParseException e) {
            return false;
        }
    }

    // VALIDAR FORMATO FECHA Y HORA
    public static void soloFormatoFechaHora(KeyEvent evt) {

        char c = evt.getKeyChar();

        // SOLO números, /, : y espacio
        if (!Character.isDigit(c) && c != '/' && c != ':' && c != ' ') {
            evt.consume();
        }
    }

    public static String convertirFechaMySQL(String fechaUsuario) {

        try {

            java.text.SimpleDateFormat formatoUsuario = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
            java.text.SimpleDateFormat formatoMySQL = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            java.util.Date fecha = formatoUsuario.parse(fechaUsuario);

            return formatoMySQL.format(fecha);

        } catch (Exception e) {
            return null;
        }
    }
}
