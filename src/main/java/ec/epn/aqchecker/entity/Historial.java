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
public class Historial implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idHistorial;
    private Date fecha;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    private Double ph;
    private Double kh;
    private Double gh;
    private Double co2;
    private String observaciones;
    private String iluminacion;
    private Acuario acuario;
    private Integer idAcuario;

    public Historial() {
    }

    public Historial(Date fecha, Double ph, Double kh, Double gh, Double co2, String observaciones, String iluminacion, Integer idAcuario) {
        this.fecha = fecha;
        this.ph = ph;
        this.kh = kh;
        this.gh = gh;
        this.co2 = co2;
        this.observaciones = observaciones;
        this.iluminacion = iluminacion;
        this.idAcuario = idAcuario;
    }
    

    public Integer getIdAcuario() {
        return idAcuario;
    }

    public void setIdAcuario(Integer idAcuario) {
        this.idAcuario = idAcuario;
    }

    public Historial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Integer getIdHistorial() {
        return idHistorial;
    }

    public void setIdHistorial(Integer idHistorial) {
        this.idHistorial = idHistorial;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Double getPh() {
        return ph;
    }

    public void setPh(Double ph) {
        this.ph = ph;
    }

    public Double getKh() {
        return kh;
    }

    public void setKh(Double kh) {
        this.kh = kh;
    }

    public Double getGh() {
        return gh;
    }

    public void setGh(Double gh) {
        this.gh = gh;
    }

    public Double getCo2() {
        return co2;
    }

    public void setCo2(Double co2) {
        this.co2 = co2;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getIluminacion() {
        return iluminacion;
    }

    public void setIluminacion(String iluminacion) {
        this.iluminacion = iluminacion;
    }

    public Acuario getAcuario() {
        return acuario;
    }

    public void setAcuario(Acuario acuario) {
        this.acuario = acuario;
    }    
}
