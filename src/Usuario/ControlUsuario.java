package Usuario;

import AccesoBBDD.AccesoBD;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

/**
 * Created by Yo on 25/10/2016.
 */
public class ControlUsuario {

    private AccesoBD accesoBD;

    public void conectar() throws SQLException, ClassNotFoundException {
        accesoBD = AccesoBD.getMiConexion();
        accesoBD.conectar("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/frutas2016", "root", "");

    }

    public boolean comprobarUsuario(Usuario usuario) throws SQLException, ClassNotFoundException {
        String sql = "SELECT `usuarioAlmacen`,`passwordAlmacen` FROM `almacen` WHERE usuarioAlmacen =" + "\""+usuario.getNick() +"\"" + " AND passwordAlmacen = " + "\"" + DigestUtils.md5Hex(usuario.getContraseña())+ "\"" + ";";
        this.conectar();
        return accesoBD.executeQuery(sql).next();

    }
}
