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
public class BolasAparecidas implements Iterable<Integer> {
    private ListEDD<Integer> aparecidas;
    
    public BolasAparecidas(){
        aparecidas = new SimpleLinkedList<>();
    }
    
    public void add(int elem){
        aparecidas.add(elem);
    }
    
    public void print(){
        for(Integer element : this){ 
            System.out.print(element + "\t");
        }
    }

    @Override
    public Iterator<Integer> iterator() {
        return aparecidas.iterator();
    }
}
