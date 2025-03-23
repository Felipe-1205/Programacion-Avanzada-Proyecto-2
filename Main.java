import java.util.*;
import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Main 
{ 
  public static void main(String[] args) throws ClassNotFoundException
  { 
    try 
    {
      ObjectInputStream ois=null;
      Admin busca=null;
      try 
      {
        //busca si exsiste algun usuario admin de lo contrario creo uno default
        File f = new File("admin.obj");  
        FileInputStream fis = new FileInputStream(f);
        ois = new ObjectInputStream(fis);
        while(true)
        {
          busca = (Admin) ois.readObject();
        }
      }
      catch(EOFException e)
      {
        if(busca==null)
        {
          System.out.println("No se encontraron un usuario administrador asi que se creo uno de base de usuario 'root' y contraseña '1234'");
          Admin adm = new Admin("root","1234");
          adm.setId(0);
          File f = new File("admin.obj");  
          FileOutputStream fos = new FileOutputStream (f,
          true);
          ObjectOutputStream oos = new ObjectOutputStream(fos);
          oos.writeObject(adm);
          oos.close();
        }
      }
      catch(IOException e){}
      catch (Exception e) 
      {
        añadirallog(e);
      }
      try 
      {
        int opcion;
        do
        {
          System.out.println("┏━━━━━━━━━━━━━━━━━━━━━━┓");
          System.out.println("┃    Iniciar Sesion    ┃");
          System.out.println("┣━━━━━━━━━━━━━━━━━━━━━━┫");
          System.out.println("┃(1) Administrador     ┃");
          System.out.println("┃(2) Estudiante        ┃");
          System.out.println("┃(3) Profesor          ┃");
          System.out.println("┃(4) Salir del sistema ┃");
          System.out.println("┗━━━━━━━━━━━━━━━━━━━━━━┛");
          System.out.print("Ingrese el tipo de usuario con el que desa iniciar sesion: ");
          Scanner in = new Scanner(System.in);
          opcion = in.nextInt();
          //dependeiendo del tipo de usuario de sesion seleccionado comprueba si existe y crea el onjeto y llama su metodo menu
          if(opcion==1)
          {
            try 
            {
              Scanner inu = new Scanner(System.in);
              System.out.print("Ingrese su nombre de usuario: ");
              String user = inu.nextLine();
              Scanner inc = new Scanner(System.in);
              System.out.print("Ingrese su contraseña: ");
              String contra = inc.nextLine();
              ObjectInputStream ois2=null;
              try 
              {
                File f = new File("admin.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois2 = new ObjectInputStream(fis);
                while(true)
                {
                  Admin iniciar = (Admin) ois2.readObject();
                  if(user.equals(iniciar.getUsu()))
                  {
                    if(contra.equals(iniciar.getContraseña()))
                    {
                      System.out.println("Sesion Iniciada");
                      iniciar.menu();
                      break;
                    }
                  }
                }
              }
              catch(IOException e)
              {
                System.out.println("No se encontro el usuario revise que escribio bien los datos");
              }
              catch (Exception e) 
              {
                añadirallog(e);
              }
            } 
            catch (Exception e) 
            {
              añadirallog(e);
            }
          }
          else if(opcion==2)
          {
            try 
            {
              Scanner inu = new Scanner(System.in);
              System.out.print("Ingrese su nombre de usuario: ");
              String user = inu.nextLine();
              Scanner inc = new Scanner(System.in);
              System.out.print("Ingrese su contraseña: ");
              String contra = inc.nextLine();
              ObjectInputStream ois2=null;
              try 
              {
                File f = new File("estudiante.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois2 = new ObjectInputStream(fis);
                while(true)
                {
                  Estudiante iniciar = (Estudiante) ois2.readObject();
                  if(user.equals(iniciar.getUsu()))
                  {
                    if(contra.equals(iniciar.getContraseña()))
                    {
                      System.out.println("Sesion Iniciada");
                      iniciar.menu();
                      break;
                    }
                  }
                }
              }
              catch(IOException e)
              {
                System.out.println("No se encontro el usuario revise que escribio bien los datos");
              }
              catch (Exception e) 
              {
                añadirallog(e);
              }
            } 
            catch (Exception e) 
            {
              añadirallog(e);
            }
          }
          else if(opcion==3)
          {
            try 
            {
              Scanner inu = new Scanner(System.in);
              System.out.print("Ingrese su nombre de usuario: ");
              String user = inu.nextLine();
              Scanner inc = new Scanner(System.in);
              System.out.print("Ingrese su contraseña: ");
              String contra = inc.nextLine();
              ObjectInputStream ois2=null;
              try 
              {
                File f = new File("profesor.obj");  
                FileInputStream fis = new FileInputStream(f);
                ois2 = new ObjectInputStream(fis);
                while(true)
                {
                  Profesor iniciar = (Profesor) ois2.readObject();
                  if(user.equals(iniciar.getUsu()))
                  {
                    if(contra.equals(iniciar.getContraseña()))
                    {
                      System.out.println("Sesion Iniciada");
                      iniciar.menu();
                      break;
                    }
                  }
                }
              }
              catch(IOException e)
              {
                System.out.println("No se encontro el usuario revise que escribio bien los datos");
              }
              catch (Exception e) 
              {
                //en caso de error llama la funcion estatica del main
                añadirallog(e);
              }
            } 
            catch (Exception e) 
            {
              añadirallog(e);
            }
          }
          else if(opcion==4)
          {
            System.out.println("Gracias por usar el sistema");
          }
          else
          {
            System.out.println("se ingreso un valor incorrecto");
          }
        }
        while(opcion!=4);
      } 
      catch (Exception e) 
      {
        añadirallog(e);
      }
    } 
    catch (Exception e) 
    {
      añadirallog(e);
    }
  }
  public static void añadirallog(Exception e)
  {
    try
    {
      //recoge las excepsiones y las guarda con la fecha y hora de cuando ocurri  en el archivo log
    DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
    PrintWriter writer = new PrintWriter("logs.txt", "UTF-8");
    writer.println(dtf.format(LocalDateTime.now())+" "+e);
    writer.close();
    }
    catch (Exception ex) 
    {
      añadirallog(ex);
    }
  }
}