/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.jdbc;

import ec.epn.aqchecker.entity.Historial;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastian
 */
public class HistorialService {

    public HistorialService() {

    }

    public String create(Historial entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("insert into historial "
                    + "(id_acuario,fecha,ph, kh, gh, co2, observaciones, iluminacion)"
                    + "values(?,?,?,?,?,?,?,?);");

            int i = 1;
            ps.setInt(i++, entity.getIdAcuario());
            ps.setDate(i++, entity.getFecha());
            ps.setDouble(i++, entity.getPh());
            ps.setDouble(i++, entity.getKh());
            ps.setDouble(i++, entity.getGh());
            ps.setDouble(i++, entity.getCo2());
            ps.setString(i++, entity.getObservaciones());
            ps.setString(i++, entity.getIluminacion());

            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return clasz.getName() + " creado exitosamente";
        } catch (SQLException e) {
            return "Error al crear " + clasz.getName();
        }
    }

    public String edit(Historial entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("update geleria "
                    + "id_acuario = ?,fecha = ?,ph = ?, kh = ?, gh = ?, co2 = ?, "
                    + "observaciones = ?, iluminacion = ?)");

            int i = 1;
            ps.setInt(i++, entity.getIdAcuario());
            ps.setDate(i++, entity.getFecha());
            ps.setDouble(i++, entity.getPh());
            ps.setDouble(i++, entity.getKh());
            ps.setDouble(i++, entity.getGh());
            ps.setDouble(i++, entity.getCo2());
            ps.setString(i++, entity.getObservaciones());
            ps.setString(i++, entity.getIluminacion());
            
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
                    + "from historial where id_historial = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return "Historial eliminado exitosamente";
        } catch (SQLException e) {
            return "Error al eliminar historial";
        }
    }

    public Historial find(Integer id) {
        Historial historial = null;
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * "
                    + "from historial where id_historial = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            historial = new Historial(rs.getDate("fecha"), rs.getDouble("ph"),
                    rs.getDouble("kh"), rs.getDouble("gh"), rs.getDouble("co2"), 
                    rs.getString("observaciones"), rs.getString("iluminacion"), rs.getInt("id_acuario"));

            rs.close();
            ps.close();
            Conn.getConnection().close();

            return historial;
        } catch (SQLException e) {
            return new Historial();
        }
    }

    public List<Historial> findAll() {
        List<Historial> list = new ArrayList<>();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * from historial");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Historial historial = new Historial(rs.getDate("fecha"), rs.getDouble("ph"),
                    rs.getDouble("kh"), rs.getDouble("gh"), rs.getDouble("co2"), 
                    rs.getString("observaciones"), rs.getString("iluminacion"), rs.getInt("id_acuario"));
                list.add(historial);
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
