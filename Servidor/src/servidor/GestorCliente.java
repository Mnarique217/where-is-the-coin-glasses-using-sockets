/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Observable;
import java.util.Observer;

import modelo.Evento;
import modelo.Modelo;
import static modelo.Modelo.M_INICIO;

/**
 *
 * @author xxxx
 */
public class GestorCliente implements Runnable,Observer{
    Socket skt;
    ObjectOutputStream salida;
    ObjectInputStream  entrada;
    Servidor control;
    int numCliente;
    
    public GestorCliente(Socket skt, Servidor control,int numCliente) {
        try {
            this.numCliente=numCliente;
            this.skt = skt;
            this.salida = null;
            this.entrada = null;
            this.control = control;
            
            this.salida   = new ObjectOutputStream(skt.getOutputStream());
            this.entrada  = new ObjectInputStream (skt.getInputStream() );
            
        } catch (IOException ex) {} 
    } 
    
    public void mensaje(Evento e){
        try {salida.writeObject(e); } catch (IOException ex) {}
    }
   
    @Override
    public void run() {
        mensaje(new Evento("esperando jugadores",M_INICIO, numCliente));
    }

    @Override
    public void update(Observable o, Object arg) {
        
        mensaje(new Evento(arg, Modelo.M_GANADOR, numCliente));
    }

    public Socket getSkt() {
        return skt;
    }

    public ObjectOutputStream getSalida() {
        return salida;
    }

    public ObjectInputStream getEntrada() {
        return entrada;
    }

    public Servidor getControl() {
        return control;
    }

    public int getNumCliente() {
        return numCliente;
    }

    public String leerRespuesta() {
        Evento e=null;
    try {
             e=(Evento) entrada.readObject();
            
            
        } catch (IOException | ClassNotFoundException ex) {}
    return e.getMensaje();
    }
    
    
}
