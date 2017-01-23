package Almacen;

import AccesoBBDD.AccesoBD;
import com.aeat.valida.Validador;
import org.apache.commons.codec.digest.DigestUtils;

import java.sql.SQLException;

/**
 * Created by Yo on 24/10/2016.
 */
public class ControlAlmacen implements IControlAlmacen {
    private StringBuilder error = new StringBuilder("");
    AccesoBD accesoBD;

    private void conectar() throws SQLException, ClassNotFoundException {
        accesoBD = AccesoBD.getMiConexion();
        accesoBD.conectar("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/frutas2016", "root", "");

    }

    private final boolean validarCP(String cp) {
        boolean bandera = true;
        try {
            Integer.parseInt(cp);
        } catch (NumberFormatException e) {
            bandera = false;
            error.append("Codigo postal con letras.\n");
        }
        error.append(cp.length() < 5 ? "Codigo postal con menos de 5 numeros.\n" : "");
        error.append(cp.length() > 5 ? "Codigo postal con mas de 5 numeros.\n" : "");
        bandera = !(bandera && cp.length() > 5);
        bandera = !(bandera && cp.length() < 5);
        return bandera;
    }

    private final boolean validarTlf(String tlf) {
        boolean bandera = true;

        try {
            int numero = Integer.parseInt(tlf);
        } catch (NumberFormatException e) {
            bandera = false;
            error.append("Solo se pueden meter números.\n");
        }
        error.append(tlf.length() < 9 ? "El con menos de 9 numeros.\n" : "");
        bandera = bandera && tlf.length() < 9;

        return bandera;
    }

    private final boolean validarCIF(String CIF) throws SQLException, ClassNotFoundException {
        if (new Validador().checkNif(CIF) > 0) {
            String sql = "SELECT * FROM almacen WHERE CIF= \"" + CIF + "\";";
            return accesoBD.executeQuery(sql).next();
        }
        error.append("CIF no válido.\n");
        return true;
    }

    private final boolean validaUsuario(String usuario) throws SQLException, ClassNotFoundException {
        boolean bandera = false;
        String sql = "SELECT * FROM almacen WHERE usuarioAlmacen= \"" + usuario + "\";";
        bandera = usuario.length() <= 15 && usuario.length() > 5;
        error.append(error.length() == 0 && bandera ? "Usuario demasiado corto (5 caracteres minimo).\n" : "");
        bandera = usuario.contains(" ");
        error.append(error.length() == 0 && bandera ? "No se adminten espacios en el usuario.\n" : "");
        if (!bandera)
            return accesoBD.executeQuery(sql).next();
        return true;
    }

    private final boolean validarDomicilio(String domicilio) {
        if (domicilio.length() <= 30) {
            return true;
        }
        error.append("Domicilio demasiado largo.\n");
        return false;
    }

    @Override
    public boolean insertar(Almacen almacen) throws SQLException, ClassNotFoundException {
        conectar();
        String sql;
        if (!verificar(almacen)) {
            System.out.println("entro");
            sql = "INSERT INTO `almacen`(`CIF`, `nombreAlmacen`, `direccionAlmacen`, `cpAlmacen`," +
                    " `telefonoAlmacen`, `usuarioAlmacen`, `passwordAlmacen`) " +
                    "VALUES  (\"" + almacen.getCif() + "\", \"" + almacen.getNombre() + "\", \"" + almacen.getDireccion()
                    + "\", \"" + almacen.getCodigoPostal() + "\", \"" + almacen.getTelefono() + "\", \""
                    + almacen.getUsuario() + "\", \"" + DigestUtils.md5Hex(almacen.getContraseña()) + "\")";
            return accesoBD.executeUpdate(sql) > 0;
        }
        System.out.println("no entro");
        almacen.setErrores(error.toString());
        return false;
    }

    private final boolean verificar(Almacen almacen) throws SQLException, ClassNotFoundException {
        ejecutarTodo(almacen);

        if(!validaUsuario(almacen.getUsuario()) &&
                validarDomicilio(almacen.getDireccion()) &&
                validarTlf(almacen.getTelefono()) &&
                validarCP(almacen.getCodigoPostal()))
            return false;
        return true;
    }

    private final void ejecutarTodo(Almacen almacen) throws SQLException, ClassNotFoundException {
        validarCIF(almacen.getCif());
        validaUsuario(almacen.getUsuario());
        validarDomicilio(almacen.getDireccion());
        validarTlf(almacen.getTelefono());
        validarCP(almacen.getCodigoPostal());
    }
}