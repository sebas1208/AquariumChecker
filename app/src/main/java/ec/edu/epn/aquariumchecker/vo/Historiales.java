package ec.edu.epn.aquariumchecker.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by angel on 6/5/2016.
 */
public class Historiales implements Serializable {
    private int id;
    private int idAcuario;
    private Date fecha;
    private int gh;
    private int ph;
    private int kh;
    private String co2;
    private String iluminacion;
    private String observaciones;

    public Historiales(int idAcuario, Date fecha, int gh, int ph, int kh, String co2, String iluminacion, String observaciones) {
        this.idAcuario = idAcuario;
        this.fecha = fecha;
        this.gh = gh;
        this.ph = ph;
        this.kh = kh;
        this.co2 = co2;
        this.iluminacion = iluminacion;
        this.observaciones = observaciones;
    }

    public Historiales() {
    }

    public int getIdAcuario() {
        return idAcuario;
    }

    public void setIdAcuario(int idAcuario) {
        this.idAcuario = idAcuario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public int getGh() {
        return gh;
    }

    public void setGh(int gh) {
        this.gh = gh;
    }

    public int getPh() {
        return ph;
    }

    public void setPh(int ph) {
        this.ph = ph;
    }

    public int getKh() {
        return kh;
    }

    public void setKh(int kh) {
        this.kh = kh;
    }

    public String getCo2() {
        return co2;
    }

    public void setCo2(String co2) {
        this.co2 = co2;
    }

    public String getIluminacion() {
        return iluminacion;
    }

    public void setIluminacion(String iluminacion) {
        this.iluminacion = iluminacion;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
}
