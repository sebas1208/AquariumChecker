package ec.edu.epn.aquariumchecker.vo;

/**
 * Created by sebastian on 28/05/16.
 */
public class Forma {
    private String nombre;
    private Double litros;

    public Forma(String nombre, Double litros) {
        this.nombre = nombre;
        this.litros = litros;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getLitros() {
        return litros;
    }

    public void setLitros(Double litros) {
        this.litros = litros;
    }
}
