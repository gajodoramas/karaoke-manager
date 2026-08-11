package logica;

import gui.Aplicacion;
import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;
import objetos.*;


public class logicaDatos {
    
    private static DefaultTableModel dtmUsuarios;
    private static DefaultTableModel dtmCanciones;
    private static DefaultTableModel dtmKaraokes;
    private static DefaultTableModel dtmTopCanciones;
    private static DefaultTableModel dtmTopCantantes;
    private static DefaultTableModel dtmBuscarPorFecha;
    private static JComboBox comboUsu;
    private static JComboBox comboCan;
    
    public static void actualizarDatos() {
        //limpia las tablas primero
        dtmUsuarios.setRowCount(0);
        dtmCanciones.setRowCount(0);
        dtmKaraokes.setRowCount(0);
        dtmTopCanciones.setRowCount(0);
        dtmTopCantantes.setRowCount(0);
        dtmBuscarPorFecha.setRowCount(0);
        //
        //lee la base de datos y recupera los objetos necesarios para rellenar tablas
        List<Cancion> listaCanciones = logica.LogicaConexion
                .getCancionController().findCancionEntities();
        List<Usuario> listaUsuarios = logica.LogicaConexion
                .getUsuarioController().findUsuarioEntities();
        List<Object[]> topCanciones = (List<Object[]>) logica
                .LogicaConexion.getMan().createQuery("SELECT c.id, c.titulo, c.autor, count(k.cancion.id) "
                        + "FROM Cancion c JOIN c.listaKaraokes k "
                        + "GROUP BY c.titulo "
                        + "ORDER BY count(k.cancion.id) desc").getResultList();
        List<Object[]> topCantantes = (List<Object[]>) logica
                .LogicaConexion.getMan().createQuery("SELECT u.id, u.nombre, count(k.usuario.id) "
                        + "FROM Usuario u JOIN u.listaKaraokes k "
                        + "GROUP BY u.nombre "
                        + "ORDER BY count(k.usuario.id) desc").getResultList();
        
        //agrega los objetos a las tablas
        for (Object[] usuario : topCantantes) {
            dtmTopCantantes.addRow(usuario);
        }
        
        for (Object[] cancion : topCanciones) {
            dtmTopCanciones.addRow(cancion);
        }
        
        DefaultComboBoxModel modelCancion = new DefaultComboBoxModel(listaCanciones.toArray());
        comboCan.setModel(modelCancion);
        for (Cancion cancion : listaCanciones) {
            dtmCanciones.addRow(cancion.toStringsArray());
        }
        
        DefaultComboBoxModel modelUsuario = new DefaultComboBoxModel(listaUsuarios.toArray());
        comboUsu.setModel(modelUsuario);
        for (Usuario usuario : logica.LogicaConexion.getUsuarioController().findUsuarioEntities()) {
            dtmUsuarios.addRow(usuario.toStringsArray());
        }
        
        for (Karaoke karaoke : logica.LogicaConexion.getKaraokeController().findKaraokeEntities()) {
            dtmKaraokes.addRow(karaoke.toStringsArray());
        }
        
    }
    
    public static void iniciarTablas(Aplicacion app) {
        
        //fija los datos a combobox y prepara los modelos de tablas
        comboUsu = app.getComboBoxUsuarios();
        comboCan = app.getComboBoxCanciones();
        dtmUsuarios = (DefaultTableModel) app.getTablaGestionUsuarios().getModel();
        dtmCanciones = (DefaultTableModel) app.getTablaGestionCanciones().getModel();
        dtmKaraokes = (DefaultTableModel) app.getTablaGestionKaraokes().getModel();
        dtmTopCanciones = (DefaultTableModel) app.getTablaInfoTopCanciones().getModel();
        dtmTopCantantes = (DefaultTableModel) app.getTablaInfoTopCantantes().getModel();
        dtmBuscarPorFecha = (DefaultTableModel) app.getTablaInfoBuscarFecha().getModel();
        
    }

    public static DefaultTableModel getDtmUsuarios() {
        return dtmUsuarios;
    }

    public static DefaultTableModel getDtmCanciones() {
        return dtmCanciones;
    }

    public static DefaultTableModel getDtmKaraokes() {
        return dtmKaraokes;
    }
    
    
    
}
