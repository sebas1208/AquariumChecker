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

    public Planta() {
    }

    public Planta(String nombre, String descripcion, int cantidad, String fotoURL) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.fotoURL = fotoURL;
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
}
