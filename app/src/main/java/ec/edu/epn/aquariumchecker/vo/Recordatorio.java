package ec.edu.epn.aquariumchecker.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by angel on 6/5/2016.
 */


public class Recordatorio implements Serializable {

    private String idAcuario;
    private Date fecha;
    private Date hora;
    private String tipoRecordatorio;
    private String id;

    public Recordatorio(String id, Date fecha, Date hora, String tipoRecordatorio, String idAcuario) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoRecordatorio = tipoRecordatorio;
        this.id = id;
        this.idAcuario = idAcuario;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Recordatorio() {
    }

    public String getIdAcuario() {
        return idAcuario;
    }


    public void setIdAcuario(String idAcuario) {
        this.idAcuario = idAcuario;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public String getTipoRecordatorio() {
        return tipoRecordatorio;
    }

    public void setTipoRecordatorio(String tipoRecordatorio) {
        this.tipoRecordatorio = tipoRecordatorio;
    }
}
