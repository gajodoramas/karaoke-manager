package logica;

import crud.CancionJpaController;
import crud.KaraokeJpaController;
import crud.UsuarioJpaController;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;


public class LogicaConexion {

    private static String ruta = "";
    private static String nombreBD = "";
    private static String usuario = "";
    private static String contrasena = "";
    private static EntityManagerFactory emf;
    private static EntityManager man;
    private static crud.CancionJpaController cancionController;
    private static crud.UsuarioJpaController usuarioController;
    private static crud.KaraokeJpaController karaokeController;

    public static boolean conectar() throws SQLException {
        
        try {
        //Se añaden los datos de conexion al archivo persistence utilizando un Map de clave : valor
        Map<String, String> persistenceMap = new HashMap<>();
        
        persistenceMap.put("javax.persistence.jdbc.url", "jdbc:mariadb://"
                + ruta.toLowerCase() + nombreBD + "?createDatabaseIfNotExist=true");
        persistenceMap.put("javax.persistence.jdbc.user", usuario.toLowerCase());
        persistenceMap.put("javax.persistence.jdbc.password", contrasena.toLowerCase());
        
        //Se sobreescribe el archivo de persistencia con los datos introducidos
        emf = Persistence.createEntityManagerFactory("persistencia", persistenceMap);
        man = emf.createEntityManager();
        
        //se soluciona un error con las foreign key
        cambiarFK();
        
        //Se envia el entity manager y se crean los controladores de cada clase
        cancionController = new crud.CancionJpaController(man);
        usuarioController = new crud.UsuarioJpaController(man);
        karaokeController = new crud.KaraokeJpaController(man);
        
        } catch (Exception ex) {
            return false;
        }
        
        return true;

    }
    
    private static void cambiarFK() {
        
        try {
            //conectamos mediante jdbc
            Connection connection = DriverManager.getConnection("jdbc:mariadb://" + ruta + nombreBD, usuario, contrasena);
            Statement statement = connection.createStatement();

            //cambiamos las fk porque con jpa no lo conseguimos
            statement.executeUpdate("ALTER TABLE karaoke DROP FOREIGN KEY fk_karaoke_cancion");
            statement.executeUpdate("ALTER TABLE karaoke ADD CONSTRAINT fk_karaoke_cancion FOREIGN KEY (ID_CANCION) REFERENCES canciones (IDENTIFICADOR) ON DELETE CASCADE ON UPDATE CASCADE");
            statement.executeUpdate("ALTER TABLE karaoke DROP FOREIGN KEY fk_karaoke_usuario");
            statement.executeUpdate("ALTER TABLE karaoke ADD CONSTRAINT fk_karaoke_usuario FOREIGN KEY (ID_USUARIO) REFERENCES usuarios (IDENTIFICADOR) ON DELETE CASCADE ON UPDATE CASCADE");
            connection.close();
            
        } catch (SQLException ex) {
            Logger.getLogger(LogicaConexion.class.getName()).log(Level.SEVERE, null, ex);
        }
 
    }

    public static CancionJpaController getCancionController() {
        return cancionController;
    }

    public static void setCancionController(CancionJpaController cancionController) {
        LogicaConexion.cancionController = cancionController;
    }

    public static UsuarioJpaController getUsuarioController() {
        return usuarioController;
    }

    public static void setUsuarioController(UsuarioJpaController usuarioController) {
        LogicaConexion.usuarioController = usuarioController;
    }

    public static KaraokeJpaController getKaraokeController() {
        return karaokeController;
    }

    public static void setKaraokeController(KaraokeJpaController karaokeController) {
        LogicaConexion.karaokeController = karaokeController;
    }
    
    public static String aString() {
        return "jdbc:mariadb://" + ruta + nombreBD + "\n" + usuario + "\n" + contrasena;
    }

    public static String getRuta() {
        return ruta;
    }

    public static void setRuta(String ruta) {
        LogicaConexion.ruta = ruta;
    }

    public static String getUsuario() {
        return usuario;
    }

    public static void setUsuario(String usuario) {
        LogicaConexion.usuario = usuario;
    }

    public static String getContrasena() {
        return contrasena;
    }

    public static void setContrasena(String contrasena) {
        LogicaConexion.contrasena = contrasena;
    }

    public static String getNombreBD() {
        return nombreBD;
    }

    public static void setNombreBD(String nombreBD) {
        LogicaConexion.nombreBD = nombreBD;
    }

    public static EntityManagerFactory getEmf() {
        return emf;
    }

    public static void setEmf(EntityManagerFactory emf) {
        LogicaConexion.emf = emf;
    }

    public static EntityManager getMan() {
        return man;
    }

    public static void setMan(EntityManager man) {
        LogicaConexion.man = man;
    }
    
}
