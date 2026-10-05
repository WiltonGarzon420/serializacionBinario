/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package serializacionusuario;

import java.io.Serializable;

/**
 *
 * @author wilton
 */
public class usuarios implements Serializable {
    
    /*Basicamente es para que sean compatibles estos y proximos programas parecidos
    Si no tengo esto y cambio los campos de la clase, Java no va acceder a los archivos 
    guardados anteriormente pq piensa que son diferentes, me evito el problema 
    dandole yo mismo un id a la clase (1L) L de tipo long
    */
    private static final long serialVersionUID = 1L;
    
    String nombre, profesion;
    double salario;
    
    public usuarios (String nombre, String profesion, double salario){
        this.nombre= nombre;
        this.profesion=profesion;
        this.salario=salario;
    }

    public String getNombre() {
        return nombre;
    }

    public String getProfesion() {
        return profesion;
    }

    public double getSalario() {
        return salario;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setProfesion(String profesion) {
        this.profesion = profesion;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    
    
}
