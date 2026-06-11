/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package libertysystem;

import Formularios.FrmConsulta;
import utilidades.ConfigTema;
import utilidades.TemaManager;

/**
 *
 * @author gabri
 */
public class LibertySystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TemaManager.oscuro = ConfigTema.cargarTemaOscuro();
        FrmConsulta frm = new FrmConsulta();
        frm.setVisible(true);
    }
}
