/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ec.epn.aqchecker.entity;

import java.io.Serializable;
import java.util.List;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author sebastian
 */
public class Acuario implements Serializable{
	private int id;
    private String nombre;
    private String tipo_agua;
    private String forma;
    private Double alto;
    private Double ancho;
    private Double profundidad_rectangular;
    private Double diametro;
    private Double profundidad_cilindrica;
    private Double volumen;
    

    public Acuario() {
    }

    public Acuario(String nombre, String tipo_agua, String forma, double alto, double ancho, double profundidad_rectangular, double diametro, double profundidad_cilindrica, double volumen, int id) {
        this.nombre = nombre;
        this.tipo_agua = tipo_agua;
        this.forma = forma;
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad_rectangular = profundidad_rectangular;
        this.diametro = diametro;
        this.profundidad_cilindrica = profundidad_cilindrica;
        this.volumen = volumen;
        this.id = id;
    }

    public Acuario(int id, String nombre, String tipo_agua, String forma, Double alto, Double ancho,
			Double profundidad_rectangular, Double diametro, Double profundidad_cilindrica, Double volumen) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.tipo_agua = tipo_agua;
		this.forma = forma;
		this.alto = alto;
		this.ancho = ancho;
		this.profundidad_rectangular = profundidad_rectangular;
		this.diametro = diametro;
		this.profundidad_cilindrica = profundidad_cilindrica;
		this.volumen = volumen;
	}

	public void setMedidasRectangulares(Double alto, Double ancho, Double profundidad_rectangular){
        this.alto = alto;
        this.ancho = ancho;
        this.profundidad_rectangular = profundidad_rectangular;
        this.diametro = 0.0;
        this.profundidad_cilindrica = 0.0;
    }

    public void setMedidasCilindricas(Double diametro, Double profundidad_cilindrica){
        this.diametro = diametro;
        this.profundidad_cilindrica = profundidad_cilindrica;
        this.alto = 0.0;
        this.ancho = 0.0;
        this.profundidad_rectangular = 0.0;
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

    public double getProfundidad_cilindrica() {
        return profundidad_cilindrica;
    }

    public void setProfundidad_cilindrica(double profundidad_cilindrica) {
        this.profundidad_cilindrica = profundidad_cilindrica;
    }

    public double getVolumen() {
        return volumen;
    }

    public void setVolumen(double volumen) {
        this.volumen = volumen;
    }
}