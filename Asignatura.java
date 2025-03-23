import java.util.*;
import java.io.*;

public class Asignatura implements Serializable {
    private int idprofesor;
    public List<Integer> idetudiantes = new ArrayList<>();
    private String Nombre;

    Asignatura(int idprofesor,String Nombre)
    {
        this.idprofesor=idprofesor;
        this.Nombre=Nombre;
    }


    public int getIdprofesor() {
        return idprofesor;
    }
    public String getNombre() {
        return Nombre;
    }

    public void setIdprofesor(int idprofesor) {
        this.idprofesor = idprofesor;
    }
    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}
