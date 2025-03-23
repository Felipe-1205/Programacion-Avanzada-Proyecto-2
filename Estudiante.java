import java.util.*;
import java.io.*;

public class Estudiante extends Usuario implements Serializable {//hereda de usuario para tener los demas datos de esta

    public List<Asignatura> materias =new ArrayList<>();

    public Estudiante(String usu, String contraseña) {
        super(usu, contraseña);
        super.setTipou(1);
    }

    public void menu()
    {
         //funcion menu de con las opciones
        try 
        {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃              Estudiante              ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃(1) Editar tu cuenta                  ┃");
            System.out.println("┃(2) Ver tus cursos                    ┃");
            System.out.println("┃(3) Entregar proyecto                 ┃");
            System.out.println("┃(4) Leer comentarios de un proyecto   ┃");
            System.out.println("┃(5) Inscribir materias                ┃");
            System.out.println("┃(6) Retirra materias                  ┃");
            System.out.println("┃(7) Leer comunicados de profesores    ┃");
            System.out.println("┃(8) Cerrar sesion                     ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Ingrese una opcion: ");
            Scanner in = new Scanner(System.in);
            int opcion = in.nextInt();
            //dependiendo de la opcion escogida se llama un metodo o otro
            if(opcion==1)
            {
                cambiarcuenta();
            }
            else if(opcion==2)
            {
                vercursos();
            }
            else if(opcion==3)
            {
                enviarproyecto();
            }
            else if(opcion==4)
            {
                leercomentarios();
            }
            else if(opcion==5)
            {
                inscribirmateria();
            }
            else if(opcion==6)
            {
                cancelarmateria();
            }
            else if(opcion==7)
            {
                leercomunicados();
            }
            //en caso de escoger salir no hace nada y retorna al main
            else if(opcion==8)
            {
                System.out.println("Cerrando sesion");
            }
            //si no se ingresa el valor correcto se vuelve a llamar si misma la fuincion
            else
            {
                System.out.println("se ingreso un valor incorrecto");
                menu();
            }
        } 
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }

    public void cambiarcuenta()//se escoge un dato y se abre el archivo para una vez cambiado se actualice el valor en el archivo
    {
        try 
        {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃(1) Editar nombre       ┃");
            System.out.println("┃(2) Editar contraseña   ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Ingrese una opcion a editar: ");
            Scanner in2 = new Scanner(System.in);
            int opcion2 = in2.nextInt();
            if(opcion2==1)
            {
                ObjectInputStream ois;
                List<Estudiante> nuevo =new ArrayList<>();
                try 
                {
                    File f = new File("estudiante.obj");  
                    FileInputStream fis = new FileInputStream(f);
                    ois = new ObjectInputStream(fis);
                    while(true)
                    {
                        Estudiante p =(Estudiante) ois.readObject();
                        nuevo.add(p);
                    }
                }
                catch(EOFException es){}
                catch(IOException e){}
                catch (Exception ex) 
                { 
                    Main.añadirallog(ex); 
                }
                System.out.print("Ingrese el nombre nuevo: ");
                Scanner in4 = new Scanner(System.in);
                String opcion4 = in4.nextLine();
                for(int i=0 ; i<nuevo.size() ; i++)
                {
                    if(getUsu().equals(nuevo.get(i).getUsu()))
                    {
                        nuevo.get(i).setUsu(opcion4);
                        break;
                    }
                }
                try 
                {
                    File f = new File("estudiante.obj");
                    FileOutputStream fos = new FileOutputStream(f);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    for(int i=0 ; i<nuevo.size() ; i++)
                    {
                        oos.writeObject(nuevo.get(i));
                    }
                    oos.close();
                } 
                catch (Exception ex) 
                { 
                    Main.añadirallog(ex); 
                }
            }
            else if(opcion2==2)
            {
                ObjectInputStream ois;
                List<Estudiante> nuevo =new ArrayList<>();
                try 
                {
                File f = new File("estudiante.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                    Estudiante p =(Estudiante) ois.readObject();
                    nuevo.add(p);
                }
                }
                catch(EOFException es){}
                catch(IOException e){}
                catch (Exception ex) 
                { 
                    Main.añadirallog(ex); 
                }
                System.out.print("Ingrese la contraseña nueva: ");
                Scanner in4 = new Scanner(System.in);
                String opcion4 = in4.nextLine();
                for(int i=0 ; i<nuevo.size() ; i++)
                {
                    if(getContraseña().equals(nuevo.get(i).getContraseña()))
                    {
                        nuevo.get(i).setContraseña(opcion4);
                        break;
                    }
                }
                try 
                {
                    File f = new File("estudiante.obj");
                    FileOutputStream fos = new FileOutputStream(f);
                    ObjectOutputStream oos = new ObjectOutputStream(fos);
                    for(int i=0 ; i<nuevo.size() ; i++)
                    {
                        oos.writeObject(nuevo.get(i));
                    }
                    oos.close();
                } 
                catch (Exception ex) 
                { 
                    Main.añadirallog(ex); 
                }
            }
            else
            {
                System.out.println("Ingreso un valor incorrecto");
                cambiarcuenta();
            }
            menu();
        } 
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
    public void vercursos()//busca en el array list de asignaturas que tiene cadsa estudiante y lo imprime 
    {
        try
        {
            ObjectInputStream ois;
            List<Estudiante> nuevo =new ArrayList<>();
            try 
            {
                File f = new File("estudiante.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                    Estudiante p =(Estudiante) ois.readObject();
                    nuevo.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            for (int i = 0; i < nuevo.size(); i++) 
            {
                if(getUsu().equals(nuevo.get(i).getUsu()))
                {
                    if(nuevo.get(i).materias.isEmpty())
                    {
                        System.out.println("Notienes materias inscritas");
                    }
                    for (int j = 0; j < nuevo.get(i).materias.size(); j++) {
                        System.out.println(nuevo.get(i).materias.get(j).getNombre());
                    }
                    break;
                }    
            }
            try 
            {
                File f = new File("estudiante.obj");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for(int i=0 ; i<nuevo.size() ; i++)
                {
                    oos.writeObject(nuevo.get(i));
                }
                oos.close();
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            menu();
        }
        catch (Exception e) 
        {
            Main.añadirallog(e);    
        }
    }
    public void enviarproyecto()//se establece un nombre al proyecto y se envia al profesor que se elija
    {
        try
        {
            ObjectInputStream ois;
            List<Proyectos> nuevo =new ArrayList<>();
            try 
            {
                File f = new File("proyectos.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                Proyectos p =(Proyectos) ois.readObject();
                nuevo.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                System.out.print("Ingrese el nombre del proyecto: ");
                Scanner in = new Scanner(System.in);
                String nom = in.nextLine();
                System.out.print("Ingrese el id del profesor al que se le entrega el proyecto: ");
                Scanner in2 = new Scanner(System.in);
                int id = in2.nextInt();
                nuevo.add(new Proyectos(getId(),id,nom));
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                File f = new File("proyectos.obj");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for(int i=0 ; i<nuevo.size() ; i++)
                {
                    oos.writeObject(nuevo.get(i));
                }
                oos.close();
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            menu();
        }
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
    public void leercomentarios()//de los proyectos que hayas enviados verifica si tiene algun comentario si lo tiene te lo imprime
    {
        try
        {
            ObjectInputStream ois;
            List<Proyectos> nuevo =new ArrayList<>();
            try 
            {
                File f = new File("proyectos.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                Proyectos p =(Proyectos) ois.readObject();
                nuevo.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                System.out.print("Ingrese el nombre del proyecto que desea ver los comentarios: ");
                Scanner in = new Scanner(System.in);
                String nom = in.nextLine();
                for (int i = 0; i < nuevo.size(); i++) {
                    if(nom.equals(nuevo.get(i).getNombre()))
                    {
                        if(nuevo.get(i).getComantraio()==null)
                        {
                            System.out.println("El profesor no a calificado su trabajo");
                        }
                        else
                        {
                            System.out.println(nuevo.get(i).getComantraio());
                        }
                        break;
                    }
                }
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                File f = new File("proyectos.obj");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for(int i=0 ; i<nuevo.size() ; i++)
                {
                    oos.writeObject(nuevo.get(i));
                }
                oos.close();
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            menu();
        }
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
    public void inscribirmateria()//busca la materia y de encontrarla la mete en el arraylist de asignaturas del estudiante
    {
        try
        {
            ObjectInputStream ois;
            List<Estudiante> nuevo =new ArrayList<>();
            try 
            {
                File f = new File("estudiante.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                    Estudiante p =(Estudiante) ois.readObject();
                    nuevo.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            ObjectInputStream ois2;
            List<Asignatura> nuevo2 =new ArrayList<>();
            try 
            {
                File f = new File("asignatura.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois2 = new ObjectInputStream(fis);
                while(true)
                {
                    Asignatura p =(Asignatura) ois2.readObject();
                    nuevo2.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                boolean ext=false;
                System.out.print("Ingrese el nombre de la asignatura a inscribir: ");
                Scanner in = new Scanner(System.in);
                String nom = in.nextLine();
                for (int i = 0; i < nuevo2.size(); i++) {
                    if(nom.equals(nuevo2.get(i).getNombre()))
                    {
                        for (int j = 0; j < nuevo.size(); j++) {
                            if(getUsu().equals(nuevo.get(j).getUsu()))
                            {
                                ext=true;
                                nuevo.get(j).materias.add(nuevo2.get(i));
                                nuevo2.get(i).idetudiantes.add(nuevo.get(j).getId());
                                break;
                            }
                        }
                        break;
                    }
                }
                if(ext==false)
                {
                    System.out.println("No se encontro la asignatura");
                }
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                File f = new File("asignatura.obj");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for(int i=0 ; i<nuevo2.size() ; i++)
                {
                    oos.writeObject(nuevo2.get(i));
                }
                oos.close();
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                File f = new File("estudiante.obj");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for(int i=0 ; i<nuevo.size() ; i++)
                {
                    oos.writeObject(nuevo.get(i));
                }
                oos.close();
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            menu();
        }
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
    public void cancelarmateria()//busca la materia y la borra del arraylist del estudiante
    {
        try
        {
            ObjectInputStream ois;
            List<Estudiante> nuevo =new ArrayList<>();
            try 
            {
                File f = new File("estudiante.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                    Estudiante p =(Estudiante) ois.readObject();
                    nuevo.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            ObjectInputStream ois2;
            List<Asignatura> nuevo2 =new ArrayList<>();
            try 
            {
                File f = new File("asignatura.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois2 = new ObjectInputStream(fis);
                while(true)
                {
                    Asignatura p =(Asignatura) ois2.readObject();
                    nuevo2.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                boolean ext=false;
                System.out.print("Ingrese el nombre de la asignatura a retirar: ");
                Scanner in = new Scanner(System.in);
                String nom = in.nextLine();
                for (int i = 0; i < nuevo2.size(); i++) {
                    if(nom.equals(nuevo2.get(i).getNombre()))
                    {
                        for (int j = 0; j < nuevo.size(); j++) {
                            if(getUsu().equals(nuevo.get(j).getUsu()))
                            {
                                ext=true;
                                nuevo.get(j).materias.remove(i);
                                nuevo2.get(i).idetudiantes.remove(nuevo.get(j).getId());
                                break;
                            }
                        }
                        break;
                    }
                }
                if(ext==false)
                {
                    System.out.println("No se encontro la asignatura");
                }
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                File f = new File("asignatura.obj");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for(int i=0 ; i<nuevo2.size() ; i++)
                {
                    oos.writeObject(nuevo2.get(i));
                }
                oos.close();
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                File f = new File("estudiante.obj");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for(int i=0 ; i<nuevo.size() ; i++)
                {
                    oos.writeObject(nuevo.get(i));
                }
                oos.close();
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            menu();
        }
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
    public void leercomunicados()//abre el archivo de comunicados y entre todos los comunicados mira cual id coinside con el del estudiante
    {
        try
        {
            ObjectInputStream ois;
            List<Comunicados> nuevo =new ArrayList<>();
            try 
            {
                File f = new File("comunicados.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                    Comunicados p =(Comunicados) ois.readObject();
                    nuevo.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                int opcion=0;
                for (int i = 0; i < nuevo.size(); i++) 
                {
                    if(getId()==nuevo.get(i).getIdestudiante())
                    {
                        if(nuevo.get(i).getLeido()==false)
                        {
                            nuevo.get(i).setLeido(true);
                            System.out.println("El profesor de codigo: "+nuevo.get(i).getIdprofesor());
                            System.out.println("te dejo el siguiente comunicado: ");
                            System.out.println(nuevo.get(i).getComunicado());
                            System.out.println("si quiere dejar de leer comunicados ponga un 1");
                            Scanner in = new Scanner(System.in);
                            opcion = in.nextInt();
                        }
                    }
                    if(opcion==1)
                    {
                        break;
                    }
                }
                
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            try 
            {
                File f = new File("comunicados.obj");
                FileOutputStream fos = new FileOutputStream(f);
                ObjectOutputStream oos = new ObjectOutputStream(fos);
                for(int i=0 ; i<nuevo.size() ; i++)
                {
                    oos.writeObject(nuevo.get(i));
                }
                oos.close();
            } 
            catch (Exception ex) 
            { 
                Main.añadirallog(ex); 
            }
            menu();
        }
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
}
