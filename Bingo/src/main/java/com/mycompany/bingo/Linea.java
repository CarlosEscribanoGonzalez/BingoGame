/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bingo;
import estructuras.*;
import java.util.Iterator;

/**
 *
 * @author Usuario
 */
public class Linea {
    private final SimpleLinkedList<Integer> lineaInicial; //La línea que primero hay en el cartón
    private final SimpleLinkedList<Integer> lineaTachados; //La línea que se imprimirá si se canta línea
    
    
    public Linea(){
        lineaInicial = new SimpleLinkedList<>();
        lineaTachados = new SimpleLinkedList<>();
    }
    
    public void add(int numero){ //Añadimos los números a las líneas
        lineaInicial.add(numero);
    }
    
    public boolean tachar(int bola){
        for(Iterator<Integer> it = lineaInicial.iterator(); it.hasNext();) {
            Integer element = it.next();
            if(element == bola){
                lineaTachados.add(element);
                lineaInicial.removeElem(element);
                if(comprobarLinea()){
                    return true;
                }
            }
        }
        return false;
    }
    
    public boolean comprobarLinea(){
        return lineaInicial.isEmpty();
    }
    
    public void printLinea(){ //Imprimir la línea final cuando se cante línea
        for(Integer valor : lineaTachados){
            System.out.print(valor + "\t");
        }
    }
}
