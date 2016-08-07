/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.entity;

import java.io.Serializable;
import java.sql.Date;

/**
 *
 * @author sebastian
 */
public class Recordatorio implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idRecordatorio;
    private Date fecha;
    private Date hora;
    private String tipoRecordatorio;
    private Acuario acuario;
    private Integer idAcuario;

    public Recordatorio() {
    }
    
    public Recordatorio(Integer idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
    }

    public Recordatorio(Date fecha, Date hora, String tipoRecordatorio, Integer idAcuario) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipoRecordatorio = tipoRecordatorio;
        this.idAcuario = idAcuario;
    }

    public Integer getIdAcuario() {
        return idAcuario;
    }

    public void setIdAcuario(Integer idAcuario) {
        this.idAcuario = idAcuario;
    }

    public Integer getIdRecordatorio() {
        return idRecordatorio;
    }

    public void setIdRecordatorio(Integer idRecordatorio) {
        this.idRecordatorio = idRecordatorio;
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

    public Acuario getAcuario() {
        return acuario;
    }

    public void setAcuario(Acuario acuario) {
        this.acuario = acuario;
    }
}
