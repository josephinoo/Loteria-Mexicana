/*
 Clase jugador, esta todos los datos que tiene el jugador
 */


package componetes;



import javafx.beans.property.SimpleStringProperty;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 * 
 * @author GRUPO10
 * Esta Clase representa al jugador y sus atributos, de aqui inclusive se toman para el reporte
 *
 */
public class Jugador implements Comparable<Jugador>{
    /*
    Fecha en Actual en la que se va jugar
     */ 
    private String  fecha;
    /*
    El tiempo de duracion que tiene el jugador, en la partida
     */
    private String duracion;
    /*
    Nombre del jugador que estara en la partida
     */
    private String name;
    /*
    El juego permitira  tener varios oponentes
     */
    private String oponentes;
    /*
    El tipo de alineacion que va tener , en el juego esta implementado 5 alineaciones para jugar
     */
    private String alinicacion;

    /*
     El constructor de la clase JUGADOR
     */
    public Jugador(String fecha, String duracion, String name, String oponentes, String alinicacion) {
        this.fecha = fecha;
        this.duracion = duracion;
        this.name = name;
        this.oponentes = oponentes;
        this.alinicacion = alinicacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getDuracion() {
        return duracion;
    }

    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOponentes() {
        return oponentes;
    }

    public void setOponentes(String oponentes) {
        this.oponentes = oponentes;
    }

    public String getAlinicacion() {
        return alinicacion;
    }

    public void setAlinicacion(String alinicacion) {
        this.alinicacion = alinicacion;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Jugador)) return false;
        Jugador jugador = (Jugador) o;
        return Objects.equals(getFecha(), jugador.getFecha()) &&
                Objects.equals(getDuracion(), jugador.getDuracion()) &&
                getName().equals(jugador.getName()) &&
                Objects.equals(getOponentes(), jugador.getOponentes()) &&
                Objects.equals(getAlinicacion(), jugador.getAlinicacion());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFecha(), getDuracion(), getName(), getOponentes(), getAlinicacion());
    }



    @Override
    public int compareTo(Jugador o) {
        return this.fecha.compareTo(o.getFecha());
    }


}