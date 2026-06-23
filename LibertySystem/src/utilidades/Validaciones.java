package utilidades;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class Validaciones {

    public static boolean campoVacio(JTextField txt, String mensaje) { // VALIDAR CAMPO VACÍO

        if (txt.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(null, mensaje);
            txt.requestFocus();
            return true;
        }
        return false;
    }

    public static void soloNumeros8Digitos(JTextField txt, KeyEvent evt) { // VALIDAR SOLO NÚMEROS Y 8 DÍGITOS
        char c = evt.getKeyChar();
        if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) { // PERMITIR BACKSPACE Y DELETE
            return;
        }
        if (!Character.isDigit(c)) { // SOLO NÚMEROS
            evt.consume();
            return;
        }
        if (txt.getText().length() >= 11) { // MÁXIMO 8 DÍGITOS
            evt.consume();
        }
    }

    public static boolean validarNumeroLinea(String numero) {
        return numero.matches("\\d{8}|\\d{11}");
    }

    public static void soloMax50Caracteres(JTextField txt, KeyEvent evt) {
        if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE) { // permitir control keys
            return;
        }
        if (txt.getText().length() >= 50) { // máximo 50 caracteres
            evt.consume();
        }
    }

    public static void buscadorNumeroCliente(JTextField txt, KeyEvent evt) {
        if (txt.getText().length() >= 50) { // Máximo 50 caracteres
            if (evt.getKeyChar() == KeyEvent.VK_BACK_SPACE || evt.getKeyChar() == KeyEvent.VK_DELETE) {
                return;
            }
            evt.consume();
        }
    }

    public static boolean validarFechaFormato(String fecha) {
        if (!fecha.matches("\\d{2}/\\d{2}/\\d{4} \\d{2}:\\d{2}:\\d{2}")) { // SOLO permite: números, /, espacio 
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

    public static void soloFormatoFechaHora(KeyEvent evt) { // VALIDAR FORMATO FECHA Y HORA
        char c = evt.getKeyChar();
        if (!Character.isDigit(c) && c != '/' && c != ':' && c != ' ') { // SOLO números, /, : y espacio
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
