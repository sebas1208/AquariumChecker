package ec.epn.aqchecker.jdbc;

import java.util.ArrayList;
import java.util.List;

import ec.epn.aqchecker.entity.Acuario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AcuarioService {

    public AcuarioService() {

    }

    public String create(Acuario entity) throws SQLException {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("insert into acuario "
                    + "(nombre,tipoagua,forma,alto,ancho,prof_medidas, diametro,prof_redondo,volumen)"
                    + "values(?,?,?,?,?,?,?,?,?);");

            int i = 1;
            ps.setString(i++, entity.getNombre());
            ps.setString(i++, entity.getTipo_agua());
            ps.setString(i++, entity.getForma());
            ps.setDouble(i++, entity.getAlto());
            ps.setDouble(i++, entity.getAncho());
            ps.setDouble(i++, entity.getProfundidad_rectangular());
            ps.setDouble(i++, entity.getDiametro());
            ps.setDouble(i++, entity.getProfundidad_cilindrica());
            ps.setDouble(i++, entity.getVolumen());
            
            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return clasz.getName() + " creado exitosamente";
        } catch (SQLException e) {
            return "Error al crear " + clasz.getName();
        }
    }

    public String edit(Acuario entity) {
        Class<?> clasz = entity.getClass();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("update acuario "
                    + "set nombre =  ?, tipoagua = ?, forma = ?, alto = ?, ancho = ?,"
                    + "prof_medidas = ?, diametro = ?, prof_redondo = ?, volumen = ?)");

            int i = 1;
            ps.setString(i++, entity.getNombre());
            ps.setString(i++, entity.getTipo_agua());
            ps.setString(i++, entity.getForma());
            ps.setDouble(i++, entity.getAlto());
            ps.setDouble(i++, entity.getAncho());
            ps.setDouble(i++, entity.getProfundidad_rectangular());
            ps.setDouble(i++, entity.getDiametro());
            ps.setDouble(i++, entity.getProfundidad_cilindrica());
            ps.setDouble(i++, entity.getVolumen());
            
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
            PreparedStatement ps = Conn.getConnection().prepareStatement("delete from acuario where id_acuario = ?");
            ps.setInt(1, id);
            ps.executeUpdate();
            ps.close();

            Conn.getConnection().close();

            return "Acuario eliminado exitosamente";
        } catch (SQLException e) {
            return "Error al eliminar acuario";
        }
    }

    public Acuario find(Integer id) {        
        Acuario acuario = null;
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * from acuario where id_acuario = ?");
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            rs.next();
            acuario = new Acuario(rs.getInt("id"), rs.getString("nombre"), rs.getString("tipo_agua"),
                    rs.getString("forma"), rs.getDouble("alto"),rs.getDouble("ancho"), 
                    rs.getDouble("profundidad_rectangular") ,rs.getDouble("diametro"),
                    rs.getDouble("profundidad_cilindrica") ,rs.getDouble("volumen"));

            rs.close();
            ps.close();
            Conn.getConnection().close();

            return acuario;
        } catch (SQLException e) {
            return new Acuario();
        }
    }

    public List<Acuario> findAll() {
        List<Acuario> list = new ArrayList<>();
        try {
            PreparedStatement ps = Conn.getConnection().prepareStatement("select * from acuario");
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()){
                Acuario acuario = new Acuario(rs.getInt("id_acuario"),rs.getString("nombre"), rs.getString("tipoagua"),
                        rs.getString("forma"), rs.getDouble("alto"),rs.getDouble("ancho"), 
                        rs.getDouble("prof_medidas") ,rs.getDouble("diametro"),
                        rs.getDouble("prof_redondo") ,rs.getDouble("volumen"));
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
