/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.entity;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebastian
 */
public class Acuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer idAcuario;

    private String nombre;

    private String tipoagua;

    private String forma;
    
    private Double alto;
    
    private Double ancho;
    
    private Double profMedidas;
    
    private Double diametro;
    
    private Double profRedondo;
    
    private Double volumen;
    
    private List<Plantas> plantasList;
    
    private List<Peces> pecesList;
    
    private List<Recordatorio> recordatorioList;
    
    private List<Historial> historialList;
    
    private List<Galeria> galeriaList;

    public Acuario() {
    }

    public Acuario(String nombre, String tipoagua, String forma, Double alto, Double ancho, Double profMedidas, Double diametro, Double profRedondo, Double volumen) {
        this.nombre = nombre;
        this.tipoagua = tipoagua;
        this.forma = forma;
        this.alto = alto;
        this.ancho = ancho;
        this.profMedidas = profMedidas;
        this.diametro = diametro;
        this.profRedondo = profRedondo;
        this.volumen = volumen;
    }

    public Acuario(Integer idAcuario,String nombre, String tipoagua, String forma, Double alto, Double ancho, Double profMedidas, Double diametro, Double profRedondo, Double volumen) {
        this.idAcuario = idAcuario;
        this.nombre = nombre;
        this.tipoagua = tipoagua;
        this.forma = forma;
        this.alto = alto;
        this.ancho = ancho;
        this.profMedidas = profMedidas;
        this.diametro = diametro;
        this.profRedondo = profRedondo;
        this.volumen = volumen;
    }
    
    
    public Acuario(Integer idAcuario) {
        this.idAcuario = idAcuario;
    }

    public Acuario(Integer idAcuario, String nombre) {
        this.idAcuario = idAcuario;
        this.nombre = nombre;
    }

    public Integer getId() {
        return idAcuario;
    }

    public void setId(Integer idAcuario) {
        this.idAcuario = idAcuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoagua() {
        return tipoagua;
    }

    public void setTipoagua(String tipoagua) {
        this.tipoagua = tipoagua;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public Double getAlto() {
        return alto;
    }

    public void setAlto(Double alto) {
        this.alto = alto;
    }

    public Double getAncho() {
        return ancho;
    }

    public void setAncho(Double ancho) {
        this.ancho = ancho;
    }

    public Double getProfMedidas() {
        return profMedidas;
    }

    public void setProfMedidas(Double profMedidas) {
        this.profMedidas = profMedidas;
    }

    public Double getDiametro() {
        return diametro;
    }

    public void setDiametro(Double diametro) {
        this.diametro = diametro;
    }

    public Double getProfRedondo() {
        return profRedondo;
    }

    public void setProfRedondo(Double profRedondo) {
        this.profRedondo = profRedondo;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }

    @XmlTransient
    public List<Plantas> getPlantasList() {
        return plantasList;
    }

    public void setPlantasList(List<Plantas> plantasList) {
        this.plantasList = plantasList;
    }

    @XmlTransient
    public List<Peces> getPecesList() {
        return pecesList;
    }

    public void setPecesList(List<Peces> pecesList) {
        this.pecesList = pecesList;
    }

    @XmlTransient
    public List<Recordatorio> getRecordatorioList() {
        return recordatorioList;
    }

    public void setRecordatorioList(List<Recordatorio> recordatorioList) {
        this.recordatorioList = recordatorioList;
    }

    @XmlTransient
    public List<Historial> getHistorialList() {
        return historialList;
    }

    public void setHistorialList(List<Historial> historialList) {
        this.historialList = historialList;
    }

    @XmlTransient
    public List<Galeria> getGaleriaList() {
        return galeriaList;
    }

    public void setGaleriaList(List<Galeria> galeriaList) {
        this.galeriaList = galeriaList;
    }

    public Integer getIdAcuario() {
        return idAcuario;
    }

    public void setIdAcuario(Integer idAcuario) {
        this.idAcuario = idAcuario;
    }
    
}
