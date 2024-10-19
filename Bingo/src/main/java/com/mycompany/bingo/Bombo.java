/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bingo;
import estructuras.*;
/**
 *
 * @author Usuario
 */
public class Bombo {
    private Conjunto<Integer> bolas;
    private BolasAparecidas aparecidas;
    
    public Bombo(int maximo){
        bolas = new Conjunto<>();
        aparecidas = new BolasAparecidas();
        
        generarBombo(maximo);
    }
    
    public void generarBombo (int maximo){
        for(int i = 1; i <= maximo; i++){
            bolas.addNum(i);
        }
    }
    
    public int sacarBola(){
        int bola = bolas.randomElement();
        aparecidas.add(bola);
        return bola;
    }
    
    public void printAparecidas(){
        aparecidas.print();
    }
}
