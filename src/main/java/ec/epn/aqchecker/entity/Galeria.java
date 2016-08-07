/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebastian
 */
public class Galeria implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idGaleria;
    private Date fecha;
    private String observaciones;
    private String fotos;
    private List<Foto> fotoList;
    private Integer idAcuario;
    private Acuario acuario;

    public Galeria() {
    }

    public Galeria(Date fecha, String observaciones, String fotos, Integer idAcuario) {
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.fotos = fotos;
        this.idAcuario = idAcuario;
    }

    
    public Galeria(Integer idGaleria) {
        this.idGaleria = idGaleria;
    }

    public Integer getIdGaleria() {
        return idGaleria;
    }

    public void setIdGaleria(Integer idGaleria) {
        this.idGaleria = idGaleria;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
    }

    @XmlTransient
    public List<Foto> getFotoList() {
        return fotoList;
    }

    public void setFotoList(List<Foto> fotoList) {
        this.fotoList = fotoList;
    }

    public Acuario getAcuario() {
        return acuario;
    }

    public void setAcuario(Acuario acuario) {
        this.acuario = acuario;
    }

    public Integer getIdAcuario() {
        return idAcuario;
    }

    public void setIdAcuario(Integer idAcuario) {
        this.idAcuario = idAcuario;
    }
}
