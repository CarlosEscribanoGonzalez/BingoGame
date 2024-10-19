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
public class Partida {
    Bombo bombo;
    Jugador []jugadores;
    final int PUNTOSLINEA;
    final int PUNTOSBINGO;
    boolean linea;
    boolean bingo;
    boolean lineaCantada;
    
    public Partida(int bolasMax, Jugador [] player, int puntosLinea, int puntosBingo){
        bombo = new Bombo(bolasMax);
        jugadores = player;
        PUNTOSLINEA = puntosLinea;
        PUNTOSBINGO = puntosBingo; 
        linea = false;
        bingo = false;
        lineaCantada = false;
        
        for(int i = 0; i < jugadores.length; i++){ //Creamos los cartones de cada jugador
            jugadores[i].carton = new Carton(bolasMax);
            jugadores[i].carton.generarCarton();
            System.out.println(jugadores[i].nombre +" jugará con el siguiente cartón: ");
            jugadores[i].carton.printCarton();
            System.out.println("");
        }
    }
    
    public void jugarPartida(){
        int jugada = 1;
        int bola;
        
        while(!bingo){
            System.out.println("Jugada " +jugada + ":");
            jugada++;
            bola = bombo.sacarBola();
            System.out.println("Se saca del bombo la bola: " +bola);
            System.out.print("Bolas sacadas: ");
            bombo.printAparecidas();
            System.out.println("");
            
            ListEDD<String> posee = new SimpleLinkedList<>();
            for(int i = 0; i < jugadores.length; i++){
                if(jugadores[i].carton.comprobar(bola, this, jugadores[i])){ //Tacha el número de aquellos cartones y líneas que lo tengan y hace una llamada a cantarLínea o cantarBingo si es necesario
                    posee.add(jugadores[i].nombre);
                }
            }
            
            if(!bingo && !lineaCantada){ //Cuando se canta línea o bingo no se menciona quién más poseía el número. El protagonismo no se roba. 
                if(posee.isEmpty()){
                    System.out.println("Nadie posee la bola " +bola);
                } else{
                    System.out.print("La tiene: ");
                    for(Iterator<String> it = posee.iterator(); it.hasNext();) {
                        String element = it.next();
                        System.out.print(element +" ");
                    }
                    System.out.println("");
                } 
            }
            System.out.println("");
            lineaCantada = false;
        }
    }
    
    public void enseñarGanadorPartida(){
        int max = 0;
        String nombre = null;
        for(int i = 0; i < jugadores.length; i++){
            if(jugadores[i].getPointsPartida() > max){
                max = jugadores[i].getPointsPartida();
                nombre = jugadores[i].nombre;
            }
            jugadores[i].resetPoints();
        }
        System.out.println("El ganador de esta partida es: " +nombre +" , con " +max + " puntos");
    }
    
    public void mostrarGanador(int numPartidas){
        int max = 0;
        String nombre = null;
        for(int i = 0; i < jugadores.length; i++){
            if(jugadores[i].getPointsTotales() > max){
                max = jugadores[i].getPointsTotales();
                nombre = jugadores[i].nombre;
            }
        }
        System.out.println("El ganador de la tanda de " +numPartidas +" partidas es: " +nombre +" , con " +max + " puntos");
    }
    
    public void cantarLinea(Jugador jugador, Linea line){
        if(!linea){
            jugador.sumarPuntos(PUNTOSLINEA);
            System.out.println("¡Línea de " + jugador.nombre + "!");
            System.out.print("Números de la línea (en orden de aparición): ");
            line.printLinea();
            System.out.println("");
            linea = true;
            lineaCantada = true;
        }
    }
    
    public void cantarBingo(Jugador jugador, SimpleLinkedList salidos){
        bingo = true;
        System.out.println("¡Bingo de " +jugador.nombre +"!");
        jugador.sumarPuntos(PUNTOSBINGO);
        System.out.print("Números del bingo (en orden de aparición): ");
        jugador.carton.imprimirSalidos();
        System.out.println("");
    }
}
