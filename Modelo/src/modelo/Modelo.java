/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Observable;
import java.util.Observer;
import java.util.Random;


/**
 *
 * @author xxxx
 */
public class Modelo extends Observable{

    int cantidad;
    public static String M_INICIO="INICIAR";
    public static String M_TURNO ="TURNO";
    public static String M_ACTUALIZACION="ACTUALIZACION";
    public static String M_GANADOR="GANADOR";


    public Modelo() {
        cantidad=3;
        elecciones=new int[4];
        
    }

    public int getCantidad() {
        return cantidad;
    }

    
    @Override
    public void addObserver(Observer c) {
       super.addObserver(c); 
    }

    public void guardarEleccion(String leerRespuesta, int numCliente) {
        
        elecciones[numCliente]=Integer.parseInt(leerRespuesta);
        System.out.println("guardado eleccion "+leerRespuesta);
    }
    

    public void revisar(){
        
        int x = (int) (Math.random()*3+1);
        System.out.println("randomm dio "+x);
        
        
        actualizar("gano el Jugador.. ["+(elecciones[x]+1)+"]");
    }
    
    public void actualizar(Object t){
        this.setChanged();
        this.notifyObservers(t);
    }

     int  []elecciones;
}
