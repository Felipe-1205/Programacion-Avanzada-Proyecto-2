import java.util.*;
import java.io.*;

public class Usuario implements Serializable {
    private int id;
    private String usu;
    private String contraseña;
    private int tipou;
    //datos para heredar a los tres tipos de usuario
    //encapsulamiento

    public Usuario(String usu, String contraseña) {
        this.usu = usu;
        this.contraseña = contraseña;
    }
    //get   
    public int getId() {
        return id;
    }
    public String getContraseña() {
        return contraseña;
    }
    public int getTipou() {
        return tipou;
    }
    public String getUsu() {
        return usu;
    }
    //set
    public void setId(int id) {
        this.id = id;
    }
    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    public void setTipou(int tipou) {
        this.tipou = tipou;
    }
    public void setUsu(String usu) {
        this.usu = usu;
    }
    
}
