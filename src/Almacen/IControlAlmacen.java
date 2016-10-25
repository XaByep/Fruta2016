package Almacen;

import java.sql.SQLException;

/**
 * Created by JAVI on 24/10/2016.
 */
public interface IControlAlmacen {
    boolean validarCIF(String CIF) throws SQLException, ClassNotFoundException;
    boolean validaUsuario(String CIF) throws SQLException, ClassNotFoundException;
    boolean insertar(String[] elementos) throws SQLException, ClassNotFoundException;

}
