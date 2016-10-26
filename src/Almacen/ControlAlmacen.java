package Almacen;

import AccesoBBDD.AccesoBD;
import com.aeat.valida.Validador;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

/**
 * Created by Yo on 24/10/2016.
 */
public class ControlAlmacen implements IControlAlmacen {

    AccesoBD accesoBD;

    private void conectar() throws SQLException, ClassNotFoundException {
        accesoBD = AccesoBD.getMiConexion();
        accesoBD.conectar("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/frutas2016", "root", "");

    }
    //TODO:Comprobar tlf, cp

    private boolean validarCIF(String CIF) throws SQLException, ClassNotFoundException {
        if (new Validador().checkNif(CIF) > 0) {
            String sql = "SELECT * FROM almacen WHERE CIF= \"" + CIF + "\";";
            return accesoBD.executeQuery(sql).next();
        }
        return true;
    }

    private boolean validaUsuario(String usuario) throws SQLException, ClassNotFoundException {
        if (usuario.length() <= 15 && usuario.length() > 5) {
            String sql = "SELECT * FROM almacen WHERE usuarioAlmacen= \"" + usuario + "\";";
            return accesoBD.executeQuery(sql).next();
        }
        return true;
    }

    private boolean validarDomicilio(String domicilio) {
        if (domicilio.length() < 30 && domicilio.length() > 5) {
            return true;
        }
        return false;
    }

    @Override
    public boolean insertar(Almacen almacen) throws SQLException, ClassNotFoundException {
        conectar();
        String sql;
        if(verificar(almacen)){
            sql = "INSERT INTO `almacen`(`CIF`, `nombreAlmacen`, `direccionAlmacen`, `cpAlmacen`," +
                    " `telefonoAlmacen`, `usuarioAlmacen`, `passwordAlmacen`) " +
                    "VALUES  (\"" + almacen.getCif() + "\", \"" + almacen.getNombre() + "\", \"" + almacen.getDireccion()
                    + "\", \"" + almacen.getCodigoPostal() + "\", \"" + almacen.getTelefono() + "\", \""
                    + almacen.getUsuario() + "\", \"" + DigestUtils.md5Hex(almacen.getContraseña()) + "\")";
            return accesoBD.executeUpdate(sql) > 0;
        }
        return false;
    }

    private boolean verificar(Almacen almacen) throws SQLException, ClassNotFoundException {
        if (!validarCIF(almacen.getCif())&&!validaUsuario(almacen.getUsuario())
                &&validarDomicilio(almacen.getDireccion())){
            return false;
        }
        return true;
    }
}