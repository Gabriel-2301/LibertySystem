package libertysystem;

import Formularios.FrmConsulta;
import utilidades.ConfigTema;
import utilidades.TemaManager;
import utilidades.VersionLocal;

public class LibertySystem {

    public static final String VERSION_LOCAL = "1.2";
    public static void main(String[] args) {

        //SI NO EXISTE version.dat, se crea con la versión instalada
        String localVersion = VersionLocal.obtenerVersionLocal();

        if (localVersion == null || localVersion.equals("0.0")) {
            VersionLocal.guardarVersionLocal(VERSION_LOCAL);
        }
        TemaManager.oscuro = ConfigTema.cargarTemaOscuro();
        FrmConsulta frm = new FrmConsulta();
        frm.setVisible(true);
    }
}
