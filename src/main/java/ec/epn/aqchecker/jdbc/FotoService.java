/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.jdbc;

import ec.epn.aqchecker.entity.Foto;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sebastian
 */
public class FotoService {
    
     public FotoService() {

    }

    public String create(Foto entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("insert into foto "
                    + "(id_galeria,descripcion,path)"
                    + "values(?,?,?);");

            int i = 1;
            ps.setInt(i++, entity.getIdGaleria());
            ps.setString(i++, entity.getDescripcion());
            ps.setString(i++, entity.getPath());
            
            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return clasz.getName() + " creado exitosamente";
        } catch (SQLException e) {
            return "Error al crear " + clasz.getName();
        }
    }

    public String edit(Foto entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("update acuario "
                    + "set id_galeria =  ?, descripcion = ?, path = ?)");

            int i = 1;
            ps.setInt(i++, entity.getIdGaleria());
            ps.setString(i++, entity.getDescripcion());
            ps.setString(i++, entity.getPath());
            
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
            PreparedStatement ps = Conn.getConnection().prepareStatement("delete from foto where id_foto = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return "Foto eliminado exitosamente";
        } catch (SQLException e) {
            return "Error al eliminar foto";
        }
    }

    public Foto find(Integer id) {        
        Foto foto = null;
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * "
                    + "from foto where id_acuario = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            foto = new Foto(rs.getString("descripcion"), rs.getString("path"), id);

            rs.close();
            ps.close();
            Conn.getConnection().close();

            return foto;
        } catch (SQLException e) {
            return new Foto();
        }
    }

    public List<Foto> findAll() {
        List<Foto> list = new ArrayList<>();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * from foto");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Foto acuario = new Foto(rs.getString("descripcion"), 
                        rs.getString("path"), rs.getInt("id_foto"));
                list.add(acuario);
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
