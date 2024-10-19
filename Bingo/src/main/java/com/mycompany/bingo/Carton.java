/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.bingo;

import estructuras.*;
import java.util.Iterator;
import java.util.Random;
/**
 *
 * @author Usuario
 */
public class Carton {
    private final Object[][]carton; //Los cartones son arrays a los cuales se les añade números de una sorted linked list
    private final Conjunto totalBolas; //Todos las bolas susceptibles de salir
    private final Random generator; //Generador de números aleatorios, para la generación de los * en el cartón
    private final int MAXIMOBOLAS; //El mayor número que puede aparecer en una bola
    private final SortedLinkedListEDD<Integer> numerosCarton; // Es el conjunto de números, ordenados de menor a mayor, que componen el cartón
    private final Linea[]lines; //Array de líneas del cartón
    private final SimpleLinkedList<Integer> salidos; //Conjunto de bolas del cartón que se han tachado
   
    public Carton(int maximo){
        carton = new Object[3][9];
        
        totalBolas = new Conjunto<>();
        
        generator = new Random();
        
        MAXIMOBOLAS = maximo;
        
        numerosCarton = new SortedLinkedListEDD<>();
        
        lines = new Linea[3];
        
        salidos = new SimpleLinkedList<>();
    }
    
    public void generarCarton(){
        for(int i = 1; i <= MAXIMOBOLAS; i++){
            totalBolas.addNum(i);
        }
        
        for(int i = 0; i < 15; i++){
            int número = (Integer) totalBolas.randomElement();
            numerosCarton.add(número);
        }
        rellenarCarton();
    }
    
    public void rellenarCarton(){
        
        for(int i = 0; i < carton.length; i++){
            
            int asteriscos = 0; //Número de * que hay en la fila
            int numeros = 0; //Número de números que hay en la fila
            
            for(int j = 0; j < carton[i].length; j++){
                if(asteriscos == 4){
                    numeros++;
                } else if (numeros == 5){
                    asteriscos++;
                    carton[i][j] = "*";
                } else{
                    boolean par = generarAleatorio(); //Se crea un booleano que será true o false al 50%. Si es true se rellena con número; si es false con *
                    if(par){
                        numeros++;
                    } else{
                        asteriscos++;
                        carton[i][j] = "*";
                    }
                }
            }
        }
        
        for(int j = 0; j < 9; j++){ //Se tiene que reordenar la última fila para que no queden columnas de sólo asteriscos o de sólo números
            if(carton[0][j] == null && carton[1][j] == null && carton[2][j] == null){
                replace(null, j);
            } else if(carton[0][j] == "*" && carton[1][j] == "*" && carton[2][j] == "*"){
                replace("*", j);
            }
        }
        añadirNumeros();
    }
    
    public boolean generarAleatorio(){
        int aleatorio = generator.nextInt();
        if(aleatorio % 2 == 0){
            return true;
        }else{
            return false;
        }
    }
    
    public void replace(Object objeto, int pos){
        boolean cambiado = false;
        
        for(int j = 0; j < 9 && !cambiado; j++){
            if(objeto == null){
                if(carton[2][j] == "*" && (carton[1][j] != null || carton[0][j] != null)){
                    Object aux = carton[2][j];
                    carton[2][j] = carton[2][pos];
                    carton [2][pos] = aux;
                    cambiado = true;
                } 
            } else{
                if(carton[2][j] == null && (carton[1][j] != "*" || carton[0][j] != "*")){
                    Object aux = carton[2][j];
                    carton[2][j] = carton[2][pos];
                    carton [2][pos] = aux;
                    cambiado = true;
                } 
            }
        }
    }
    
    public void añadirNumeros(){
        SimpleNode<Integer> current = numerosCarton.first();
        
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 3; j++){
                if(carton[j][i] != "*"){
                    carton[j][i] = current.getValue();
                    current = current.getNext();
                }
            }
        }
        crearLineas();
    }
    
    public void crearLineas(){
        for(int i = 0; i < carton.length; i++){
            lines[i] = new Linea();
            for(int j = 0; j < carton[i].length; j++){
                if(carton[i][j] != "*"){
                    lines[i].add((int) carton[i][j]);
                }
            }
        }
    }
    
    public void printCarton(){
        for(int i = 0; i < carton.length; i++){
            for(int j = 0; j < carton[i].length; j++){
                if(carton[i][j] != "*" && (int)carton[i][j] < 10 || carton[i][j] == "*"){
                    System.out.print(carton[i][j] + "    ");
                } else{
                    System.out.print(carton[i][j] + "   ");
                }
            }
            System.out.println("");
        }
    }
    
    public boolean comprobar(int bola, Partida partida, Jugador jugador){
        for(Iterator<Integer> it = numerosCarton.iterator(); it.hasNext();) {
            Integer element = it.next();
            if(element.equals(bola)){
                for(int i = 0; i < lines.length; ++i){
                    if(lines[i].tachar(bola)){ //Tacha el número de las líneas que lo tengan y comprueba si se ha cantado línea
                        partida.cantarLinea(jugador, lines[i]);
                    } 
                }
                salidos.add(numerosCarton.removeElem(element));
                if(comprobarBingo()){
                    partida.cantarBingo(jugador, salidos);
                }
                return true;
            }
        }
        return false;
    }
    
    public boolean comprobarBingo(){
        return numerosCarton.isEmpty();
    }
    
    public void imprimirSalidos(){
        for(Iterator<Integer> it = salidos.iterator(); it.hasNext();) {
            Integer element = it.next();
            System.out.print(element + " ");
        }
    }
}
