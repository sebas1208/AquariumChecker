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
public class Plantas implements Serializable {

    private static final long serialVersionUID = 1L;
    private Integer idPlanta;
    private String nombre;
    private String descripcion;
    private int cantidad;
    private String foto;
    private Acuario acuario;
    private Integer idAcuario;

    public Plantas() {
    }

    public Plantas(Integer idPlanta) {
        this.idPlanta = idPlanta;
    }

    public Plantas(Integer idPlanta, String nombre, String descripcion, int cantidad, String foto) {
        this.idPlanta = idPlanta;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.foto = foto;
    }

    public Plantas(String nombre, String descripcion, int cantidad, String foto, Integer idAcuario) {
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

    public Integer getIdPlanta() {
        return idPlanta;
    }

    public void setIdPlanta(Integer idPlanta) {
        this.idPlanta = idPlanta;
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
