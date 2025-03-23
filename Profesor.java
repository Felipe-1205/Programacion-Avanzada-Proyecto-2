import java.util.*;
import java.io.*;

public class Profesor extends Usuario implements Serializable {//hereda de usuario para tener los demas datos de esta
    public Profesor(String usu, String contraseña) {
        super(usu, contraseña);
        super.setTipou(2);
    }

    public void menu()
    {
        //funcion menu de con las opciones
        try 
        {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃              Profesor                ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃(1) Editar tu cuenta                  ┃");
            System.out.println("┃(2) Calificar proyecto                ┃");
            System.out.println("┃(3) Ver estudiantes en tu asignatura  ┃");
            System.out.println("┃(4) Enviar comunicado a un estudiante ┃");
            System.out.println("┃(5) Cerrar sesion                     ┃");
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
                calificar();
            }
            else if(opcion==3)
            {
                verestudiantes();
            }
            else if(opcion==4)
            {
                enviarcomunicado();
            }
            //en caso de escoger salir no hace nada y retorna al main
            else if(opcion==5)
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
                List<Profesor> nuevo =new ArrayList<>();
                try 
                {
                    File f = new File("profesor.obj");  
                    FileInputStream fis = new FileInputStream(f);
                    ois = new ObjectInputStream(fis);
                    while(true)
                    {
                        Profesor p =(Profesor) ois.readObject();
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
                    File f = new File("profesor.obj");
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
                List<Profesor> nuevo =new ArrayList<>();
                try 
                {
                File f = new File("profesor.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                    Profesor p =(Profesor) ois.readObject();
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
                    File f = new File("profesor.obj");
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
    public void calificar()//se abre el documento de proyectos y dependiendo del nombre del proyecto se abre y el priofesor pone un comentario despues se ctualiza el archivo
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
                int opcion=0;
                for (int i = 0; i < nuevo.size(); i++) {
                    if(getId()==nuevo.get(i).getIdprofesor())
                    {
                        if(nuevo.get(i).getComantraio()==null)
                        {
                            System.out.println("El estudiante de codigo: "+nuevo.get(i).getIdestudiante());
                            System.out.println("entrego el proyecto: "+nuevo.get(i).getNombre());
                            System.out.print("Ingrese su comentario: ");
                            Scanner in = new Scanner(System.in);
                            String comentario = in.nextLine();
                            nuevo.get(i).setComantraio(comentario);
                            System.out.println("si desdea dejar de calificar ponga un 1");
                            Scanner in2 = new Scanner(System.in);
                            opcion = in2.nextInt();
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
    public void verestudiantes()//abre y crea arraylist de los estudiantes y de las asignaturas busca la asignatura del profesor comparando con sus id y despues el array de estudiantes lo imprime con sus nombres del array de estudiantes
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
                for (int i = 0; i < nuevo2.size(); i++) 
                {
                    if(getId()==nuevo2.get(i).getIdprofesor())
                    {
                        for (int j = 0; j < nuevo2.get(i).idetudiantes.size(); j++) 
                        {
                            for (int j2 = 0; j2 < nuevo.size(); j2++) 
                            {
                                if(nuevo2.get(i).idetudiantes.get(j)==nuevo.get(j2).getId())
                                {
                                    System.out.println(nuevo.get(j2).getId()+" "+nuevo.get(j2).getUsu());
                                }
                            }
                        }
                    }
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
                for(int i=0 ; i<nuevo.size() ; i++)
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
    public void enviarcomunicado()//agregas un objeto comentario y actualizs el documentode comunicados
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
                System.out.print("Ingrese el id del estudiante al que se le enviara el comunicadoo: ");
                Scanner in2 = new Scanner(System.in);
                int id = in2.nextInt();
                System.out.print("Ingrese el comunicado: ");
                Scanner in = new Scanner(System.in);
                String txt = in.nextLine();
                nuevo.add(new Comunicados(id,getId(),txt));
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
