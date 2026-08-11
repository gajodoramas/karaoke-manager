package logica;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;
import javax.swing.JDialog;
import objetos.Cancion;
import objetos.Karaoke;
import objetos.Usuario;


public class LogicaDatosDePrueba {

    //vector de nombres
    private static final Usuario[] usuarios = {
        new Usuario("Wilfredo Amaro"),
        new Usuario("Doramas García Jorge"),
        new Usuario("Xavier Nateras"),
        new Usuario("Ana Quesada"),
        new Usuario("Alejandro Nieves"),
        new Usuario("Franco Solís")
    };

    //vector de canciones
    private static final Cancion[] canciones = {
        new Cancion("Imagine", "John Lennon"),
        new Cancion("Johnny B. Goode", "Chuck Berry"),
        new Cancion("Tiny Dancer", "Elton John"),
        new Cancion("I Bet You Look Good on the Dancefloor", "Arctic Monkeys"),
        new Cancion("Jailhouse Rock", "Elvis Presley"),
        new Cancion("Baba O'Riley", "The Who"),
        new Cancion("The Chain", "Fletwood mac"),
        new Cancion("Gardening at Night", "REM"),
        new Cancion("Blitzkrieg Bop", "Ramones"),
        new Cancion("Back In Black", "AC/DC"),};

    public static boolean annadirDatos(JDialog padre) {

        try {

            //CREACION DE USUARIOS
            for (Usuario usuario : usuarios) {
                logica.LogicaConexion.getUsuarioController().create(usuario);
            }

            //CREACIÓN DE CANCIONES
            for (Cancion cancion : canciones) {
                logica.LogicaConexion.getCancionController().create(cancion);
            }

            //CREACION DE KARAOKES
            Calendar calendar = Calendar.getInstance();

            //Generar 500 karaokes 
            for (int i = 0; i < 500; i++) {

                //Generamos una fecha aleatoria en los últimos 7 días
                calendar.setTime(new Date());
                calendar.set(calendar.get(Calendar.YEAR), calendar
                        .get(Calendar.MONTH), calendar
                                .get(Calendar.DATE) - new Random().nextInt(1, 7));

                calendar.set(Calendar.HOUR_OF_DAY, new Random().nextInt(24));
                calendar.set(Calendar.MINUTE, new Random().nextInt(60));
                calendar.set(Calendar.SECOND, new Random().nextInt(60));

                Date fechaAleatoria = calendar.getTime();

                //tras calcular una fecha aleatoria, crea el karaoke
                logica.LogicaConexion.getKaraokeController().create(new Karaoke(
                        logica.LogicaConexion.getCancionController().findCancion(
                                new Random().nextInt(7, 17)
                        ),
                        logica.LogicaConexion.getUsuarioController().findUsuario(
                                new Random().nextInt(1, 7)
                        ),
                        fechaAleatoria));

            }

        } catch (Exception ex) {
            return false;
        }

        return true;
    }

}
