package Poblacion;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import AccesoBBDD.AccesoBD;

/**
 * Created by JAVI on 21/10/2016.
 */
public class MunicipioProceso {
    private static AccesoBD db = null;
    private static ResultSet rs = null;

    public void conectar() {
        db = AccesoBD.getMiConexion();
        try {
            db.conectar("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/frutas2016", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Conexión realizada municipio");
    }

    public ArrayList<Municipio> listaMunicipiosArrayList() throws SQLException {
        ArrayList<Municipio> listaMunicipios = new ArrayList<Municipio>();
        this.conectar();
        String sql = "SELECT m.NombreMunicipio,cp.CodigoPostal,p.NombreProvincia " +
                "FROM codigopostal cp NATURAL JOIN municipio m NATURAL JOIN provincia p " +
                "ORDER BY 2;";
        rs = db.executeQuery(sql);
        while (rs.next()) {
            Municipio municipio = crearMunicipio(rs);
            listaMunicipios.add(municipio);
        }
        return listaMunicipios;
    }

    private Municipio crearMunicipio(ResultSet rs) throws SQLException{
        return new Municipio(rs.getString("NombreMunicipio"),rs.getString("CodigoPostal"),rs.getString("NombreProvincia"));
    }

}
