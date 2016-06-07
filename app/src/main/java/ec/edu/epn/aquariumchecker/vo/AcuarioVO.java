package ec.edu.epn.aquariumchecker.vo;

import java.io.Serializable;

/**
 * Created by natyd on 5/6/2016.
 */
public class AcuarioVO implements Serializable{

    private String nombre;
    private String tipo_agua;
    private String forma;
    private double alto;
    private double ancho;
    private double profundidad_rectangular;
    private double diametro;
    private double profundidad_redondo;
    private double volumen;
    private int id;

    public AcuarioVO() {
    }

    public AcuarioVO(String nombre, String tipo_agua, String forma, double alto, double ancho, double profundidad_rectangular, double diametro, double profundidad_redondo, double volumen, int id) {
        this.nombre = nombre;
        this.tipo_agua = tipo_agua;
        this.forma = forma;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad_rectangular = profundidad_rectangular;
        this.diametro = diametro;
        this.profundidad_redondo = profundidad_redondo;
        this.volumen = volumen;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo_agua() {
        return tipo_agua;
    }

    public void setTipo_agua(String tipo_agua) {
        this.tipo_agua = tipo_agua;
    }

    public String getForma() {
        return forma;
    }

    public void setForma(String forma) {
        this.forma = forma;
    }

    public double getAlto() {
        return alto;
    }

    public void setAlto(double alto) {
        this.alto = alto;
    }

    public double getAncho() {
        return ancho;
    }

    public void setAncho(double ancho) {
        this.ancho = ancho;
    }

    public double getProfundidad_rectangular() {
        return profundidad_rectangular;
    }

    public void setProfundidad_rectangular(double profundidad_rectangular) {
        this.profundidad_rectangular = profundidad_rectangular;
    }

    public double getDiametro() {
        return diametro;
    }

    public void setDiametro(double diametro) {
        this.diametro = diametro;
    }

    public double getProfundidad_redondo() {
        return profundidad_redondo;
    }

    public void setProfundidad_redondo(double profundidad_redondo) {
        this.profundidad_redondo = profundidad_redondo;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }
}