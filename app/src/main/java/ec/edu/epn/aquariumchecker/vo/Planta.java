package ec.edu.epn.aquariumchecker.vo;

import java.io.Serializable;

/**
 * Created by sebastian on 28/05/16.
 */
public class Planta implements Serializable {

    private String nombre;
    private String descripcion;
    private int cantidad;
    private String fotoURL;
    private int id;
    private int AcuarioId;

    public Planta() {
    }

    public Planta(String nombre, String descripcion, int cantidad, String fotoURL, int id, int acuarioId) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fotoURL = fotoURL;
        this.id = id;
        AcuarioId = acuarioId;
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

    public int getAcuarioId() {
        return AcuarioId;
    }

    public void setAcuarioId(int acuarioId) {
        AcuarioId = acuarioId;
    }
}