package ec.edu.epn.aquariumchecker.vo;

import java.util.Date;

/**
 * Created by angel on 6/5/2016.
 */


public class Recordatorio {

    private String acuario;
    private String fecha;
    private int hora;
    private String tipoCambio;

    public Recordatorio(String acuario, String fecha, int hora, String tipoCambio) {
        this.acuario=acuario;
        this.fecha=fecha;
        this.hora=hora;
        this.tipoCambio=tipoCambio;
    }

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

    public int getHora() {
        return hora;
    }

    public void setHora(int hora) {
        this.hora = hora;
    }

    public String getTipoCambio() {
        return tipoCambio;
    }

    public void setTipoCambio(String tipoCambio) {
        this.tipoCambio = tipoCambio;
    }
}
