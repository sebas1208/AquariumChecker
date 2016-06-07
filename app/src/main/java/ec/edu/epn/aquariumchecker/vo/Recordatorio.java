package ec.edu.epn.aquariumchecker.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by angel on 6/5/2016.
 */


public class Recordatorio implements Serializable {

    private String acuario;
    private String fecha;
    private String hora;
    private String tipoCambio;
    private String id;

    public Recordatorio(String fecha, String hora, String tipoCambio,String id ,String acuario) {
        this.fecha=fecha;
        this.hora=hora;
        this.tipoCambio=tipoCambio;
        this.id=id;
        this.acuario=acuario;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Recordatorio(){};

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

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}
