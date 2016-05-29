package ec.edu.epn.aquariumchecker.vo;

/**
 * Created by sebastian on 28/05/16.
 */
public class Acuario {
    private String nombre;
    private String tipoAgua;
    private Forma forma;
    private Double volumen;

    public Acuario(String nombre, String tipoAgua, Forma forma, Double volumen) {
        this.nombre = nombre;
        this.tipoAgua = tipoAgua;
        this.forma = forma;
        this.volumen = volumen;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoAgua() {
        return tipoAgua;
    }

    public void setTipoAgua(String tipoAgua) {
        this.tipoAgua = tipoAgua;
    }

    public Forma getForma() {
        return forma;
    }

    public void setForma(Forma forma) {
        this.forma = forma;
    }

    public Double getVolumen() {
        return volumen;
    }

    public void setVolumen(Double volumen) {
        this.volumen = volumen;
    }
}
