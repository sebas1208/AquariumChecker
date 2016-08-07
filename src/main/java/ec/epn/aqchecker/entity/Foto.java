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
public class Foto implements Serializable {

    private static final long serialVersionUID = 1L;
   
    private Integer idFoto;
    private String descripcion;
    private String path;
    private Galeria galeria;
    private Integer idGaleria;

    public Foto() {
    }

    public Foto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public Foto(String descripcion, String path, Integer idGaleria) {
        this.descripcion = descripcion;
        this.path = path;
        this.idGaleria = idGaleria;
    }

    public Integer getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(Integer idFoto) {
        this.idFoto = idFoto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Galeria getGaleria() {
        return galeria;
    }

    public void setGaleria(Galeria galeria) {
        this.galeria = galeria;
    }

    public Integer getIdGaleria() {
        return idGaleria;
    }

    public void setIdGaleria(Integer idGaleria) {
        this.idGaleria = idGaleria;
    }
    
    
    
}
