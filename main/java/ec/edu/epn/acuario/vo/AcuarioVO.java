package ec.edu.epn.acuario.vo;

/**
 * Created by natyd on 28/5/2016.
 */
public class AcuarioVO {

    private String nombreAcuario;
    private String tipoAgua;
    private double volumen;

    public AcuarioVO() {
    }

    public AcuarioVO(String nombreAcuario, String tipoAgua, double volumen) {
        this.nombreAcuario = nombreAcuario;
        this.tipoAgua = tipoAgua;
        this.volumen = volumen;
    }

    public String getNombreAcuario() {
        return nombreAcuario;
    }

    public void setNombreAcuario(String nombreAcuario) {
        this.nombreAcuario = nombreAcuario;
    }

    public String getTipoAgua() {
        return tipoAgua;
    }

    public void setTipoAgua(String tipoAgua) {
        this.tipoAgua = tipoAgua;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }
}