package Almacen;

import AccesoBBDD.AccesoBD;

import java.sql.SQLException;

/**
 * Created by JAVI on 24/10/2016.
 */
public class ControlAlmacen implements IControlAlmacen {

    AccesoBD accesoBD;


    public void conectar() throws SQLException, ClassNotFoundException {
        accesoBD = AccesoBD.getMiConexion();
        accesoBD.conectar("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/frutas2016", "root", "");

    }

    @Override
    public boolean validarCIF(String CIF) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM almacen WHERE CIF= \"" + CIF + "\";";
        return accesoBD.executeQuery(sql).next();
    }

    @Override
    public boolean validaUsuario(String usuario) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM almacen WHERE usuarioAlmacen= \"" + usuario + "\";";
        return accesoBD.executeQuery(sql).next();
    }

    @Override
    public boolean insertar(Almacen almacen) throws SQLException, ClassNotFoundException {
        conectar();
        String sql = "INSERT INTO `almacen`(`CIF`, `nombreAlmacen`, `direccionAlmacen`, `cpAlmacen`, `telefonoAlmacen`, `usuarioAlmacen`, `passwordAlmacen`) " +
                "VALUES  (\"" + almacen.getCif() + "\", \"" + almacen.getNombre() + "\", \"" + almacen.getDireccion() + "\", \"" + almacen.getCodigoPostal() + "\", \"" + almacen.getTelefono() + "\", \"" + almacen.getUsuario() + "\", \"" + almacen.getContraseña() + "\")";
        return accesoBD.executeUpdate(sql) > 0;

    }

    public boolean verificar(String usuario, String CIF) throws SQLException, ClassNotFoundException {
        conectar();
        boolean bandera;
        bandera = validarCIF(CIF);
        if (!bandera) {
            bandera = validaUsuario(usuario);
        }
        return bandera;


    }


    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ControlAlmacen ins = new ControlAlmacen();
        System.out.println(ins.validarCIF("5564255-B"));
        System.out.println(ins.validaUsuario("u1"));
    }


}