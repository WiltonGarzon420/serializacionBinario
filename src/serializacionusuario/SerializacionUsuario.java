/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package serializacionusuario;

import java.io.*;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author wilton
 */
public class SerializacionUsuario {
    static File file = new File ("usu.bin");
    static ArrayList<usuarios> usuario = new ArrayList<>();

    public static void main(String[] args)  {
        int opcion;
        do {
            String menu = "Menú\n"+
            "1/ Ingresar usuario\n"+
            "2/ Serializar usuarios\n"+
            "3/ Deserializar usuarios\n"+
            "4/ Vaciar archivo\n"+
            "5/ salir\n";
            String entrada = JOptionPane.showInputDialog(menu);
            
            if (entrada==null){
                opcion=4;
            }else{
                try{
                    opcion=Integer.parseInt(entrada);
                }catch (NumberFormatException e){
                    opcion=0;
                }
            }
            switch(opcion){
                case 1:
                    ingresar();
                    break;
                case 2:
                    serializar();
                    break;
                case 3:
                    deserializar();
                    break;
                case 4:
                    vaciar();
                    break;
                case 5:
                    JOptionPane.showMessageDialog(null, "Saliendo... ");
                    break;
                default:
                    JOptionPane.showMessageDialog(null, "Opcion inválida");

            }
        }while (opcion!=5);
    }
    
    public static void vaciar(){
        FileOutputStream fos;
        try {
            /*es como abrir el filewriter, como sobreescribe y no le dije q escriba algo
            pone en blanco el archivo
            */
            fos = new FileOutputStream(file);
            fos.close();
            JOptionPane.showMessageDialog(null, "Archivo en blanco");

        } catch (IOException ex) {
            System.getLogger(SerializacionUsuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static void ingresar(){
        String nombre = JOptionPane.showInputDialog("Ingrese su nombre: ");
        String profesion = JOptionPane.showInputDialog("Ingrese su profesion: ");
        String salario = JOptionPane.showInputDialog("Ingrese su sueldo: ");
        
        if (nombre!= null && profesion!= null && salario!= null){
        try {
        double Salario = Double.parseDouble(salario);
        //añado a la lista usuario un nuevo objeto de la clase usuarios con sus atributos
        usuario.add(new usuarios(nombre, profesion, Salario));
        
        } catch (NumberFormatException ex) {
            System.getLogger(SerializacionUsuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
            }
        }
    }
    
    public static void serializar() {
        if (usuario == null || usuario.isEmpty()){
            JOptionPane.showMessageDialog(null, "No hay nada para ingresar");
            return;
        }
        try {
            FileOutputStream fos = new FileOutputStream(file);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(usuario); //aqui paso lo del ram al archivo
            oos.close();//siempre cerrar para evitar errores, evitable con el try-with-resources
            
        } catch (FileNotFoundException ex) {
            System.getLogger(SerializacionUsuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(SerializacionUsuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
    }
    
    public static void deserializar() {
        //por si esta vacio o no hay archivo
        if (!file.exists() || file.length()==0){
            JOptionPane.showMessageDialog(null, "Archivo vacío","Y que vas a leer ome??",JOptionPane.WARNING_MESSAGE);
            return;
        }
        //lista diferente a la de ingresar para que no la sobreescriba y queden guardados en ram
        ArrayList<usuarios> mostrar = new ArrayList<>();
        try {
        /*Los try solo para posibles fallos
        como archivo inexistente, o datos de tipo incorrecto*/
        FileInputStream fis = new FileInputStream(file);
        ObjectInputStream ois = new ObjectInputStream (fis);
        
        //Aqui saco los datos del disco a la lista que esta en la RAM (donde no quedan guardados)
        mostrar = (ArrayList<usuarios>) ois.readObject();
        ois.close();
        System.out.println("Archivo recuperado");

        
        StringBuilder sb = new StringBuilder();
        for (usuarios a: mostrar){
        sb.append("Nombre: ").append(a.getNombre()).append("\n")
        .append("Profesion: ").append(a.getProfesion()).append("\n")
        .append("Salario: ").append(a.getSalario()).append("\n")
        .append("\n");
        }
        /*joption usa 2 o 4 parametros para funcionar
        1 es donde se muestra la pantalla que invoco. Null en este caso pq no tengo ventana padre
        2 lo que va a mostrar
        (Solo pueden haber 2 o 4 parametros, el 3 depende del 4 y viceversa)
        3 el titulo de la ventana que saco
        4 el tipo de ventana que saca (warning, information y demas)
        */
        JOptionPane.showMessageDialog(null, sb.toString(),"Usuarios son: ", JOptionPane.INFORMATION_MESSAGE);
        
        } catch (FileNotFoundException ex) {
            System.getLogger(SerializacionUsuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (IOException ex) {
            System.getLogger(SerializacionUsuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        } catch (ClassNotFoundException ex) {
            System.getLogger(SerializacionUsuario.class.getName()).log(System.Logger.Level.ERROR, (String) null, ex);
        }
        
    }

    
}
