/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.jdbc;

import ec.epn.aqchecker.entity.Galeria;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastian
 */
public class GaleriaService {

    public GaleriaService() {

    }

    public String create(Galeria entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("insert into galeria "
                    + "(id_acuario,fecha,observaciones, fotos)"
                    + "values(?,?,?,?);");

            int i = 1;
            ps.setInt(i++, entity.getIdAcuario());
            ps.setDate(i++, entity.getFecha());
            ps.setString(i++, entity.getObservaciones());
            ps.setString(i++, entity.getFotos());

            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return clasz.getName() + " creado exitosamente";
        } catch (SQLException e) {
            return "Error al crear " + clasz.getName();
        }
    }

    public String edit(Galeria entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("update geleria "
                    + "set id_acuario =  ?, fecha = ?, observaciones = ?, fotos = ?)");

            int i = 1;
            ps.setInt(i++, entity.getIdGaleria());
            ps.setDate(i++, entity.getFecha());
            ps.setString(i++, entity.getObservaciones());
            ps.setString(i++, entity.getFotos());

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
                    + "from galeria where id_galeria = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return "Galeria eliminado exitosamente";
        } catch (SQLException e) {
            return "Error al eliminar foto";
        }
    }

    public Galeria find(Integer id) {
        Galeria galeria = null;
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * "
                    + "from galeria where id_galeria = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            galeria = new Galeria(rs.getDate("fecha"), rs.getString("observaciones"),
                    rs.getString("fotos"), rs.getInt("id_galeria"));

            rs.close();
            ps.close();
            Conn.getConnection().close();

            return galeria;
        } catch (SQLException e) {
            return new Galeria();
        }
    }

    public List<Galeria> findAll() {
        List<Galeria> list = new ArrayList<>();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * from galeria");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Galeria galeria = new Galeria(rs.getDate("fecha"), rs.getString("observaciones"),
                        rs.getString("fotos"), rs.getInt("id_galeria"));
                list.add(galeria);
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
