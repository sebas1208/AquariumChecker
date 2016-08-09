package ec.edu.epn.aquariumchecker.vo;

import android.graphics.Bitmap;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * Created by sebas on 7/6/2016.
 */
public class Foto implements Serializable {
    private String descripcion;
    private String path;
    private int id;
    private int idGaleria;

    @JsonIgnore
    private Bitmap foto;

    public Foto(String descripcion, String path, int id, int idGaleria) {
        this.descripcion = descripcion;
        this.path = path;
        this.id = id;
        this.idGaleria = idGaleria;
    }

    public Foto() {
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdGaleria() {
        return idGaleria;
    }

    public void setIdGaleria(int idGaleria) {
        this.idGaleria = idGaleria;
    }

    public Bitmap getFoto() {
        return foto;
    }

    public void setFoto(Bitmap foto) {
        this.foto = foto;
    }
}
