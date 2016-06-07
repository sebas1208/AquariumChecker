package ec.edu.epn.aquariumchecker.vo;

import java.io.Serializable;

/**
 * Created by angel on 6/5/2016.
 */
public class Historiales implements Serializable {
    private String acuario,fecha,ph,gh,kh,co2,iluminacion,observaciones;
    public Historiales(String acuario, String fecha, String ph, String gh, String kh, String Co2, String Iluminacion, String observaciones) {
        this.acuario=acuario;
        this.fecha=fecha;
        this.ph=ph;
        this.gh=gh;
        this.kh=kh;
        this.co2=Co2;
        this.iluminacion=Iluminacion;
        this.observaciones=observaciones;

    }

    public Historiales(){}
    public String getAcuario() {
        return acuario;
    }

    public void setAcuario(String acuario) {
        this.acuario = acuario;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getGh() {
        return gh;
    }

    public void setGh(String gh) {
        this.gh = gh;
    }

    public String getKh() {
        return kh;
    }

    public void setKh(String kh) {
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
