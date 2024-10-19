/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bingo;
import java.util.Scanner;
/**
 *
 * @author Usuario
 */
public class TandaPartidas {
    Scanner entrada;
    Jugador[]jugadores;
    int numJugadores;
    int bolasMax;
    int PUNTOSLINEA;
    int PUNTOSBINGO;
    int NUMPARTIDAS;
    
    public TandaPartidas(){
        entrada = new Scanner(System.in);
    }
    
    public void pedirJugadores(){
        do{
            System.out.println("¿Cuántos jugadores van a jugar? Recuerde que el mínimo son 2 y el máximo son 4.");
            numJugadores = entrada.nextInt();
        } while(numJugadores < 2 || numJugadores > 4);
        
        jugadores = new Jugador[numJugadores];
    }
    
    public void pedirNombres(){
        boolean firstPlayer = false;
        for(int i = 0; i < numJugadores; i++){
            System.out.println("¿Cómo se llama el jugador " + (i+1) + "?");
            jugadores[i] = new Jugador();
            jugadores[i].nombre = entrada.nextLine();
            if(!firstPlayer){
                firstPlayer = true;
                jugadores[i].nombre = entrada.nextLine(); //El programa se saltaba el nextLine del jugador 1
            }
            
            if(jugadores[i].nombre.equalsIgnoreCase("Marc")){
                System.out.println("¡Hola, Marc! Pónganos un 10.");
            }
        }
    }
    
    public void pedirNumBolas(){
        System.out.println("¿Cuál es el número máximo de bolas con el que se desea jugar? Debe ser un múltiplo de 18.");
        do{
            bolasMax = entrada.nextInt(); // el número de bolas que quiere en la partida
            if(bolasMax < 18){
                System.out.println("El número de bolas debe ser mayor a 18 para que se pueda empezar una partida. Introduzca otro número");
            }
        }while (bolasMax < 18);         
        
        if(bolasMax % 18 != 0){
            bolasMax = (int) bolasMax / 18;
            bolasMax = (int) bolasMax * 18;
            System.out.println("Como el número ha de ser múltiplo de 18, se jugará con " + bolasMax + " bolas");
        }
    }
    
    public void pedirValoresPuntos(){
        System.out.println("¿Cuántos puntos se otorgarán por cantar una línea?");
        PUNTOSLINEA = entrada.nextInt();
        System.out.println("¿Y por cantar bingo?");
        PUNTOSBINGO = entrada.nextInt();
    }
    
    public void pedirNumPartidas(){
        System.out.println("¿Cuántas partidas desesas jugar?");
        NUMPARTIDAS = entrada.nextInt();
    }
    
    public void jugarPartidas(){
        for(int i = 0; i < NUMPARTIDAS; i++){
            Partida partida = new Partida(bolasMax, jugadores, PUNTOSLINEA, PUNTOSBINGO);
            partida.jugarPartida();
            partida.enseñarGanadorPartida();
            System.out.println("-----------------------");
            
            if(i == NUMPARTIDAS - 1){
                partida.mostrarGanador(NUMPARTIDAS);
            }
        }
    }
}
