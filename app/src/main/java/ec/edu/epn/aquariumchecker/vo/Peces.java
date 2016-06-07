package ec.edu.epn.aquariumchecker.vo;

/**
 * Created by sebastian on 05/06/16.
 */
public class Peces {
    private String nombre;
    private String descripcion;
    private int cantidad;
    private String fotoURL;

    public Peces(String nombre, String descripcion, int cantidad, String fotoURL) {
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

    public String getFotoURL() {
        return fotoURL;
    }

    public void setFotoURL(String fotoURL) {
        this.fotoURL = fotoURL;
    }
}
