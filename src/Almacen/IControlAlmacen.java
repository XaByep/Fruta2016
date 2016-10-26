package Almacen;

import java.sql.SQLException;

/**
 * Created by JAVI on 24/10/2016.
 */
public interface IControlAlmacen {
    boolean insertar(Almacen almacen) throws SQLException, ClassNotFoundException;

}
