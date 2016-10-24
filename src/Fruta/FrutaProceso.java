package Fruta;

import AccesoBBDD.AccesoBD;

import java.sql.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.function.IntBinaryOperator;

/**
 * Created by JAVI on 14/10/2016.
 */
public class FrutaProceso {
    private static AccesoBD db = null;
    private static ResultSet rs = null;

    public void conectar() {
        db = AccesoBD.getMiConexion();
        try {
            db.conectar("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/frutas2016", "root", "");
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Conexión realizada fruta");
    }

    public ArrayList<Fruta> listaFrutaArrayList() throws SQLException {
        ArrayList<Fruta> frutas = new ArrayList<Fruta>();
        this.conectar();
        String sql = "SELECT * FROM fruta order by 2;";
        rs = db.executeQuery(sql);
        while (rs.next()) {
            Fruta fruta = crearFruta(rs);
            frutas.add(fruta);
        }
        return frutas;
    }

    private Fruta crearFruta(ResultSet rs) throws SQLException {
        return new Fruta(rs.getInt("idFruta"), rs.getString("nombreFruta"));
    }

    public String[] listaFrutaArray() throws SQLException, ClassNotFoundException {
        int i = 0;
        this.conectar();
        String sql = "SELECT * FROM fruta order by 2;";
        rs = db.executeQuery(sql);
        while ((rs.next())) {
            i++;
        }
        String frutas[] = new String[i];
        i = 0;
        rs.first();
        do {
            Fruta fruta = crearFruta(rs);
            frutas[i++] = fruta.getNombreFruta();
        } while (rs.next());

        return frutas;
    }

    public Vector<Fruta> listaFrutaVector() throws SQLException, ClassNotFoundException {
        Vector<Fruta> frutas = new Vector<Fruta>();
        this.conectar();
        String sql = "SELECT * FROM fruta order by 2;";
        rs = db.executeQuery(sql);
        while (rs.next()) {
            Fruta fruta = crearFruta(rs);
            frutas.addElement(fruta);
        }
        return frutas;
    }

    public Map<Integer, String> listaMapeo() throws SQLException {
        Map<Integer, String> listaFruta = new HashMap<Integer, String>();
        this.conectar();
        String sql = "SELECT * FROM fruta order by 2;";
        rs = db.executeQuery(sql);
        while (rs.next()) {
            Fruta fruta = crearFruta(rs);
            listaFruta.put(fruta.getCodigoFruta(), fruta.getNombreFruta());
        }
        return listaFruta;
    }
}
