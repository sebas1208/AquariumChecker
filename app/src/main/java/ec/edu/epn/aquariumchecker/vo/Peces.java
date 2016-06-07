package ec.edu.epn.aquariumchecker.vo;

import java.io.Serializable;

/**
 * Created by sebastian on 05/06/16.
 */
public class Peces implements Serializable{
    private String nombre;
    private String descripcion;
    private int cantidad;
    private String fotoURL;
    private int id;
    private int acuario_id;

    public Peces() {
    }

    public Peces(String nombre, String descripcion, int cantidad, String fotoURL, int id, int acuario_id) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fotoURL = fotoURL;
        this.id = id;
        this.acuario_id = acuario_id;
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

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAcuario_id() {
        return acuario_id;
    }

    public void setAcuario_id(int acuario_id) {
        this.acuario_id = acuario_id;
    }
}