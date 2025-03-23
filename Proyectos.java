import java.util.*;
import java.io.*;

public class Proyectos implements Serializable {
    private int idprofesor;
    private int idestudiante;
    private String nombre;
    private String comantraio;
    //encapsulamiento

    Proyectos(int idest, int idprof,String nombre)
    {
        idestudiante=idest;
        idprofesor=idprof;
        this.nombre=nombre;
    }

    //sets
    public void setIdestudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }
    public void setIdprofesor(int idprofesor) {
        this.idprofesor = idprofesor;
    }
    public void setComantraio(String comantraio) {
        this.comantraio = comantraio;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    //gets
    public String getComantraio() {
        return comantraio;
    }
    public String getNombre() {
        return nombre;
    }
    public int getIdestudiante() {
        return idestudiante;
    }
    public int getIdprofesor() {
        return idprofesor;
    }
}
