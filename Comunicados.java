import java.util.*;
import java.io.*;

public class Comunicados implements Serializable {
    private int idprofesor;
    private int idestudiante;
    private String comunicado;//encapsulacion
    private boolean leido=false;

    Comunicados(int idest, int idprof,String comunicado)
    {
        idestudiante=idest;
        idprofesor=idprof;
        this.comunicado=comunicado;
    }
    //sets
    public void setComunicado(String comunicado) {
        this.comunicado = comunicado;
    }
    public void setLeido(boolean leido) {
        this.leido = leido;
    }
    public void setIdestudiante(int idestudiante) {
        this.idestudiante = idestudiante;
    }
    public void setIdprofesor(int idprofesor) {
        this.idprofesor = idprofesor;
    }
    //gets
    public String getComunicado() {
        return comunicado;
    }
    public boolean getLeido() {
        return leido;
    }
    public int getIdestudiante() {
        return idestudiante;
    }
    public int getIdprofesor() {
        return idprofesor;
    }
}
