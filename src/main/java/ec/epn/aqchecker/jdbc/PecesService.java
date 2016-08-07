/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.jdbc;

import ec.epn.aqchecker.entity.Peces;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastian
 */
public class PecesService {

    public PecesService() {

    }

    public String create(Peces entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("insert into peces "
                    + "(id_acuario,nombre,descripcion, cantidad, foto)"
                    + "values(?,?,?,?,?);");

            int i = 1;
            ps.setInt(i++, entity.getIdAcuario());
            ps.setString(i++, entity.getNombre());
            ps.setString(i++, entity.getDescripcion());
            ps.setInt(i++, entity.getCantidad());
            ps.setString(i++, entity.getFoto());
            
            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return clasz.getName() + " creado exitosamente";
        } catch (SQLException e) {
            return "Error al crear " + clasz.getName();
        }
    }

    public String edit(Peces entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("update geleria "
                    + "(id_acuario = ?, nombre = ?,descripcion = ?, cantidad = ?, foto = ?);");

            int i = 1;
            ps.setInt(i++, entity.getIdAcuario());
            ps.setString(i++, entity.getNombre());
            ps.setString(i++, entity.getDescripcion());
            ps.setInt(i++, entity.getCantidad());
            ps.setString(i++, entity.getFoto());

            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return clasz.getName() + " editada exitosamente";
        } catch (SQLException e) {
            return "Error al editar " + clasz.getName();
        }
    }

    public String remove(Integer id) {
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("delete "
                    + "from peces where id_peces = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return "Peces eliminado exitosamente";
        } catch (SQLException e) {
            return "Error al eliminar peces";
        }
    }

    public Peces find(Integer id) {
        Peces peces = null;
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * "
                    + "from peces where id_peces = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            peces = new Peces(rs.getString("nombre"), rs.getString("descripcion"),
                    rs.getInt("cantidad"), rs.getString("foto"), rs.getInt("id_acuario"));

            rs.close();
            ps.close();
            Conn.getConnection().close();

            return peces;
        } catch (SQLException e) {
            return new Peces();
        }
    }

    public List<Peces> findAll() {
        List<Peces> list = new ArrayList<>();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * from peces");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Peces peces = new Peces(rs.getString("nombre"), rs.getString("descripcion"),
                        rs.getInt("cantidad"), rs.getString("foto"), rs.getInt("id_acuario"));
                list.add(peces);
            }

            rs.close();
            ps.close();
            Conn.getConnection().close();

            return list;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }
}
