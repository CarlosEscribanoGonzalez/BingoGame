/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bingo;

/**
 *
 * @author Usuario
 */
public class Jugador {
    String nombre;
    Carton carton;
    private int puntosPartida;
    private int puntosTotales;
    
    public Jugador(){
        puntosPartida = 0;
        puntosTotales = 0;
    }
    
    public void sumarPuntos(int valor){
        puntosPartida += valor;
        puntosTotales += valor;
    }
    
    public int getPointsPartida(){
        return puntosPartida;
    }
    
    public int getPointsTotales(){
        return puntosTotales;
    }
    
    public void resetPoints(){
        puntosPartida = 0;
    }
}


