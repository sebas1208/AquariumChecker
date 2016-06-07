package ec.edu.epn.aquariumchecker.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by sebas on 7/6/2016.
 */
public class Galeria implements Serializable {
    private Date fecha;
    private String observaciones;
    private String fotos;
    private int id;
    private int idAcuario;

    public Galeria(Date fecha, String observaciones, String fotos, int id, int idAcuario) {
        this.fecha = fecha;
        this.observaciones = observaciones;
        this.fotos = fotos;
        this.id = id;
        this.idAcuario = idAcuario;
    }

    public Galeria() {
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getFotos() {
        return fotos;
    }

    public void setFotos(String fotos) {
        this.fotos = fotos;
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
