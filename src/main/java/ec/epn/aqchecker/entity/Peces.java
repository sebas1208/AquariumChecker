/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.entity;

import java.io.Serializable;

/**
 *
 * @author sebastian
 */
public class Peces implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idPez;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private String foto;
    private Acuario acuario;
    private Integer idAcuario;

    public Peces() {
    }

    public Peces(Integer idPez) {
        this.idPez = idPez;
    }

    public Peces(Integer idPez, String nombre, String descripcion, int cantidad, String foto) {
        this.idPez = idPez;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.foto = foto;
    }

    public Peces(String nombre, String descripcion, int cantidad, String foto, Integer idAcuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.foto = foto;
        this.idAcuario = idAcuario;
    }

    public Integer getIdAcuario() {
        return idAcuario;
    }

    public void setIdAcuario(Integer idAcuario) {
        this.idAcuario = idAcuario;
    }
    

    public Integer getIdPez() {
        return idPez;
    }

    public void setIdPez(Integer idPez) {
        this.idPez = idPez;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public Acuario getAcuario() {
        return acuario;
    }

    public void setAcuario(Acuario acuario) {
        this.acuario = acuario;
    }    
}
