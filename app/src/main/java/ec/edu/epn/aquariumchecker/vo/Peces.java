package ec.edu.epn.aquariumchecker.vo;

import java.io.Serializable;

/**
 * Created by sebastian on 05/06/16.
 */
public class Peces implements Serializable{
    private String nombre;
    private String descripcion;
    private int cantidad;
    private String foto;
    private int id;
    private int idAcuario;

    public Peces() {
    }

    public Peces(String nombre, String descripcion, int cantidad, String foto, int id, int idAcuario) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.cantidad = cantidad;
        this.foto = foto;
        this.id = id;
        this.idAcuario = idAcuario;
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdAcuario() {
        return idAcuario;
    }

    public void setIdAcuario(int idAcuario) {
        this.idAcuario = idAcuario;
    }
}