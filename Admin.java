import java.util.*;
import java.io.*;

public class Admin extends Usuario implements Serializable {//hereda de usuario para tener los demas datos de esta

    public Admin(String usu, String contraseña) {
        super(usu, contraseña);
        super.setTipou(0);
    }

    public void menu()
    {
        //funcion menu de con las opciones
        try 
        {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃        Administrador       ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃(1) Crear una cuenta        ┃");
            System.out.println("┃(2) Eliminar una cuenta     ┃");
            System.out.println("┃(3) Editar una cuenta       ┃");
            System.out.println("┃(4) Crear una asignatura    ┃");
            System.out.println("┃(5) Eliminar una asignatura ┃");
            System.out.println("┃(6) Editar una asignatura   ┃");
            System.out.println("┃(7) Cerrar sesion           ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Ingrese una opcion: ");
            Scanner in = new Scanner(System.in);
            int opcion = in.nextInt();
            //dependiendo de la opcion escogida se llama un metodo o otro
            if(opcion==1)
            {
                crearcuenta();
            }
            else if(opcion==2)
            {
                eliminarcuenta();
            }
            else if(opcion==3)
            {
                editarcuenta();
            }
            else if(opcion==4)
            {
                crearasignatura();
            }
            else if(opcion==5)
            {
                eliminarasignatura();
            }
            else if(opcion==6)
            {
                editarasignatura();
            }
            //en caso de escoger salir no hace nada y retorna al main
            else if(opcion==7)
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

    public void crearcuenta() //abre el archivo y con un arraylist agrega el nuevo usuario y despues guarda ese arraylist de nuevo en el archivo
    {
        try 
        {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃        Crear cuenta         ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃(1) Tipo Administrador       ┃");
            System.out.println("┃(2) Tipo profesor            ┃");
            System.out.println("┃(3) Tipo estudiante          ┃");
            System.out.println("┃(4) Volver al menu principal ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Ingrese una opcion: ");
            Scanner in = new Scanner(System.in);
            int opcion = in.nextInt();
            if(opcion <=3 && opcion >=1)
            {
                try
                {
                    Scanner inu = new Scanner(System.in);
                    System.out.print("Ingrese el nombre del usuario: ");
                    String user = inu.nextLine();
                    Scanner inc = new Scanner(System.in);
                    System.out.print("Ingrese la contraseña: ");
                    String contra = inc.nextLine();
                    if(opcion==1)
                    {
                        ObjectInputStream ois;
                        List<Admin> nuevo =new ArrayList<>();
                        try 
                        {
                          File f = new File("admin.obj");  
                          FileInputStream fis = new FileInputStream(f);
                          ois = new ObjectInputStream(fis);
                          while(true)
                          {
                            Admin p =(Admin) ois.readObject();
                            nuevo.add(p);
                          }
                        }
                        catch(EOFException es){}
                        catch(IOException e){}
                        catch (Exception e) 
                        { 
                            Main.añadirallog(e);
                        }
                        try 
                        {
                            nuevo.add(new Admin(user, contra));
                            if(nuevo.get(1)==null)
                            {
                                nuevo.get(0).setId(0);//si es un usuario nuevo le pone default el 0
                            }
                            else
                            {
                                nuevo.get(nuevo.size()-1).setId(nuevo.get(nuevo.size()-2).getId()+1);//para evitar que los id se repitan mira el anterior del arrylist y le suma uno
                            }
                        } 
                        catch (IndexOutOfBoundsException e){}
                        catch (Exception e) 
                        { 
                            Main.añadirallog(e);
                        }
                        try 
                        {
                            File f = new File("admin.obj");
                            FileOutputStream fos = new FileOutputStream(f);
                            ObjectOutputStream oos = new ObjectOutputStream(fos);
                            for(int i=0 ; i<nuevo.size() ; i++)
                            {
                                oos.writeObject(nuevo.get(i));
                            }
                            oos.close();
                        } 
                        catch (Exception e) 
                        { 
                            Main.añadirallog(e);
                        }
                    }
                    else if(opcion==2)
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
                        catch (Exception e) 
                        { 
                            Main.añadirallog(e);
                        }
                        try 
                        {
                            nuevo.add(new Profesor(user, contra));
                            if(nuevo.get(1)==null)
                            {
                                nuevo.get(0).setId(0);
                            }
                            else
                            {
                                nuevo.get(nuevo.size()-1).setId(nuevo.get(nuevo.size()-2).getId()+1);
                            }
                        } 
                        catch (IndexOutOfBoundsException e){}
                        catch (Exception e) 
                        { 
                            Main.añadirallog(e);
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
                        catch (Exception e) 
                        { 
                            Main.añadirallog(e);
                        }
                    }
                    else if(opcion==3)
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
                        catch (Exception e) 
                        { 
                            Main.añadirallog(e);
                        }
                        try 
                        {
                            nuevo.add(new Estudiante(user, contra));
                            if(nuevo.get(1)==null)
                            {
                                nuevo.get(0).setId(0);
                            }
                            else
                            {
                                nuevo.get(nuevo.size()-1).setId(nuevo.get(nuevo.size()-2).getId()+1);
                            }
                        } 
                        catch (IndexOutOfBoundsException e){}
                        catch (Exception e) 
                        { 
                            Main.añadirallog(e);
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
                        catch (Exception e) 
                        { 
                            Main.añadirallog(e);
                        }
                    }
                }
                catch (Exception e) 
                {
                    Main.añadirallog(e);
                }
            }
            else if(opcion==4)
            {
                System.out.println("Volviendo al menu");
                menu();
            }
            else
            {
                System.out.println("se ingreso un valor incorrecto");
                crearcuenta();
            }
            menu();
        } 
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
    public void eliminarcuenta() //lo mismo de antes pero en vez de agregar lo elimina
    {
        try 
        {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃      Eliminar cuenta        ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃(1) Tipo Administrador       ┃");
            System.out.println("┃(2) Tipo profesor            ┃");
            System.out.println("┃(3) Tipo estudiante          ┃");
            System.out.println("┃(4) Volver al menu principal ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            Scanner in = new Scanner(System.in);
            int opcion = in.nextInt();
            if(opcion <=3 && opcion >=1)
            {
                try
                {
                    System.out.print("ingrese el id de la cuenta que desea eliminar: ");
                    Scanner in2 = new Scanner(System.in);
                    int id = in2.nextInt();
                    if(opcion==1)
                    {
                        ObjectInputStream ois;
                        List<Admin> nuevo =new ArrayList<>();
                        try 
                        {
                          File f = new File("admin.obj");  
                          FileInputStream fis = new FileInputStream(f);
                          ois = new ObjectInputStream(fis);
                          while(true)
                          {
                            Admin p =(Admin) ois.readObject();
                            nuevo.add(p);
                          }
                        }
                        catch(EOFException es){}
                        catch(IOException e){}
                        catch (Exception ex) 
                        { 
                            Main.añadirallog(ex);

                        }
                        for(int i=0 ; i<nuevo.size() ; i++)
                        {
                            if(id==nuevo.get(i).getId())
                            {
                                nuevo.remove(i);
                                break;
                            }
                        }
                        try 
                        {
                            File f = new File("admin.obj");
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
                    else if(opcion==2)
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
                        for(int i=0 ; i<nuevo.size() ; i++)
                        {
                            if(id==nuevo.get(i).getId())
                            {
                                nuevo.remove(i);
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
                    else if(opcion==3)
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
                        for(int i=0 ; i<nuevo.size() ; i++)
                        {
                            if(id==nuevo.get(i).getId())
                            {
                                nuevo.remove(i);
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
                }
                catch (Exception e) 
                {
                    Main.añadirallog(e);
                }
            }
            else if(opcion==4)
            {
                System.out.println("Volviendo al menu");
                menu();
            }
            else
            {
                System.out.println("se ingreso un valor incorrecto");
                eliminarcuenta();
            }
            menu();
        } 
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
    public void editarcuenta() //lo mismo pero dependiendo del dato que se busque se modifica 
    {
        try 
        {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃        Editar cuenta        ┃");
            System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┫");
            System.out.println("┃(1) Tipo Administrador       ┃");
            System.out.println("┃(2) Tipo profesor            ┃");
            System.out.println("┃(3) Tipo estudiante          ┃");
            System.out.println("┃(4) Volver al menu principal ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Ingrese una opcion: ");
            Scanner in = new Scanner(System.in);
            int opcion = in.nextInt();
            if(opcion <=3 && opcion >=1)
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
                    if(opcion2 <=2 && opcion2 >=1)
                    {
                        System.out.print("Ingrese el id del usuario que desea editar: ");
                        Scanner in3 = new Scanner(System.in);
                        int id = in3.nextInt();
                        if(opcion==1)
                        {
                            if(opcion2==1)
                            {
                                ObjectInputStream ois;
                                List<Admin> nuevo =new ArrayList<>();
                                try 
                                {
                                File f = new File("admin.obj");  
                                FileInputStream fis = new FileInputStream(f);
                                ois = new ObjectInputStream(fis);
                                while(true)
                                {
                                    Admin p =(Admin) ois.readObject();
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
                                    if(id==nuevo.get(i).getId())
                                    {
                                        nuevo.get(i).setUsu(opcion4);
                                        break;
                                    }
                                }
                                try 
                                {
                                    File f = new File("admin.obj");
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
                                List<Admin> nuevo =new ArrayList<>();
                                try 
                                {
                                File f = new File("admin.obj");  
                                FileInputStream fis = new FileInputStream(f);
                                ois = new ObjectInputStream(fis);
                                while(true)
                                {
                                    Admin p =(Admin) ois.readObject();
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
                                    if(id==nuevo.get(i).getId())
                                    {
                                        nuevo.get(i).setContraseña(opcion4);
                                        break;
                                    }
                                }
                                try 
                                {
                                    File f = new File("admin.obj");
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
                        }
                        else if(opcion==2)
                        {
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
                                    if(id==nuevo.get(i).getId())
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
                                    if(id==nuevo.get(i).getId())
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
                        }
                        else if(opcion==3)
                        {
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
                                    if(id==nuevo.get(i).getId())
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
                                    if(id==nuevo.get(i).getId())
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
                        }
                    }
                    else
                    {
                        System.out.println("se ingreso un valor incorrecto");
                        crearcuenta();
                    }
                }
                catch (Exception e) 
                {
                    Main.añadirallog(e);
                }
            }
            else if(opcion==4)
            {
                System.out.println("Volviendo al menu");
                menu();
            }
            else
            {
                System.out.println("se ingreso un valor incorrecto");
                editarcuenta();
            }
            menu();
        } 
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
    public void crearasignatura()//lo mimo que crear cuenta pero con las asignaturas
    {
        try 
        {
            Scanner inu = new Scanner(System.in);
            System.out.print("Ingrese el nombre de la asignatura: ");
            String nom = inu.nextLine();
            Scanner inc = new Scanner(System.in);
            System.out.print("Ingrese el id del profesor que dicta la materia: ");
            int prof = inc.nextInt();

            ObjectInputStream ois;
            List<Asignatura> nuevo =new ArrayList<>();
            try 
            {
                File f = new File("asignatura.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                Asignatura p =(Asignatura) ois.readObject();
                nuevo.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex);
            }
            nuevo.add(new Asignatura(prof,nom));
            try 
            {
                File f = new File("asignatura.obj");
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
    public void eliminarasignatura()
    {
        try 
        {
            Scanner inu = new Scanner(System.in);
            System.out.print("Ingrese el nombre de la asignatura a borrar: ");
            String nom = inu.nextLine();

            ObjectInputStream ois;
            List<Asignatura> nuevo =new ArrayList<>();
            try 
            {
                File f = new File("asignatura.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois = new ObjectInputStream(fis);
                while(true)
                {
                Asignatura p =(Asignatura) ois.readObject();
                nuevo.add(p);
                }
            }
            catch(EOFException es){}
            catch(IOException e){}
            catch (Exception ex) 
            { 
                Main.añadirallog(ex);
            }
            for(int i=0 ; i<nuevo.size() ; i++)
            {
                if(nom.equals(nuevo.get(i).getNombre()))
                {
                    nuevo.remove(i);
                    break;
                }
            }
            try 
            {
                File f = new File("asignatura.obj");
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
    public void editarasignatura()
    {
        try 
        {
            System.out.println("┏━━━━━━━━━━━━━━━━━━━━━┓");
            System.out.println("┃(1) Editar nombre    ┃");
            System.out.println("┃(2) Editar profesor  ┃");
            System.out.println("┗━━━━━━━━━━━━━━━━━━━━━┛");
            System.out.print("Ingrese una opcion a editar: ");
            Scanner in = new Scanner(System.in);
            int opcion = in.nextInt();
            if(opcion <=2 && opcion >=1)
            {
                System.out.print("Ingrese el nombre de la asignatura que desea editar: ");
                Scanner in3 = new Scanner(System.in);
                String nom = in3.nextLine();
                if(opcion==1)
                {
                    ObjectInputStream ois;
                    List<Asignatura> nuevo =new ArrayList<>();
                    try 
                    {
                        File f = new File("asignatura.obj");  
                        FileInputStream fis = new FileInputStream(f);
                        ois = new ObjectInputStream(fis);
                        while(true)
                        {
                            Asignatura p =(Asignatura) ois.readObject();
                            nuevo.add(p);
                        }
                    }
                    catch(EOFException es){}
                    catch(IOException e){}
                    catch (Exception ex) 
                    { 
                        Main.añadirallog(ex);
                    }
                    System.out.print("Ingrese el nuevo nombre: ");
                    Scanner in4 = new Scanner(System.in);
                    String opcion4 = in4.nextLine();
                    for(int i=0 ; i<nuevo.size() ; i++)
                    {
                        if(nom.equals(nuevo.get(i).getNombre()))
                        {
                            nuevo.get(i).setNombre(opcion4);
                            break;
                        }
                    }
                    try 
                    {
                        File f = new File("asignatura.obj");
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
                else if(opcion==2)
                {
                    ObjectInputStream ois;
                    List<Asignatura> nuevo =new ArrayList<>();
                    try 
                    {
                        File f = new File("asignatura.obj");  
                        FileInputStream fis = new FileInputStream(f);
                        ois = new ObjectInputStream(fis);
                        while(true)
                        {
                            Asignatura p =(Asignatura) ois.readObject();
                            nuevo.add(p);
                        }
                    }
                    catch(EOFException es){}
                    catch(IOException e){}
                    catch (Exception ex) 
                    { 
                        Main.añadirallog(ex);
                    }
                    System.out.print("Ingrese el nuevo id del profesor: ");
                    Scanner in4 = new Scanner(System.in);
                    int opcion4 = in4.nextInt();
                    for(int i=0 ; i<nuevo.size() ; i++)
                    {
                        if(nom.equals(nuevo.get(i).getNombre()))
                        {
                            nuevo.get(i).setIdprofesor(opcion4);
                            break;
                        }
                    }
                    try 
                    {
                        File f = new File("asignatura.obj");
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
            }
            else
            {
                System.out.println("se ingreso un valor incorrecto");
                editarasignatura();
            }
            menu();
        } 
        catch (Exception e) 
        {
            Main.añadirallog(e);
        }
    }
    
}