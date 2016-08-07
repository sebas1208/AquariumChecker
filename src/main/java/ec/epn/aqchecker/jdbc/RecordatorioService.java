/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.jdbc;

import ec.epn.aqchecker.entity.Recordatorio;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastian
 */
public class RecordatorioService {

    public RecordatorioService() {

    }

    public String create(Recordatorio entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("insert into recordatorio "
                    + "(id_acuario, fecha, hora, tipo_recordatorio)"
                    + "values(?,?,?,?);");

            int i = 1;
            ps.setInt(i++, entity.getIdAcuario());
            ps.setDate(i++, entity.getFecha());
            ps.setDate(i++, entity.getHora());
            ps.setString(i++, entity.getTipoRecordatorio());

            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return clasz.getName() + " creado exitosamente";
        } catch (SQLException e) {
            return "Error al crear " + clasz.getName();
        }
    }

    public String edit(Recordatorio entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("update geleria "
                    + "(id_acuario = ?, fecha = ?, hora = ?, tipo_recordatorio = ?);");

            int i = 1;
            ps.setInt(i++, entity.getIdAcuario());
            ps.setDate(i++, entity.getFecha());
            ps.setDate(i++, entity.getHora());
            ps.setString(i++, entity.getTipoRecordatorio());

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
                    + "from recordatorio where id_recordatorio = ?");

            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return "Recordatorio eliminado exitosamente";
        } catch (SQLException e) {
            return "Error al eliminar recordatorio";
        }
    }

    public Recordatorio find(Integer id) {
        Recordatorio recordatorio = null;
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * "
                    + "from recordatorio where id_recordatorio = ?");

            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            rs.next();
            recordatorio = new Recordatorio(rs.getDate("fecha"), rs.getDate("hora"),
                    rs.getString("tipo_recordatorio"), rs.getInt("id_acuario"));

            rs.close();
            ps.close();
            Conn.getConnection().close();

            return recordatorio;
        } catch (SQLException e) {
            return new Recordatorio();
        }
    }

    public List<Recordatorio> findAll() {
        List<Recordatorio> list = new ArrayList<>();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * from recordatorio");
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Recordatorio recordatorio = new Recordatorio(rs.getDate("fecha"), rs.getDate("hora"),
                    rs.getString("tipo_recordatorio"), rs.getInt("id_acuario"));
                list.add(recordatorio);
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
