/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.cliente;

import cliente.vista.Vista;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import modelo.Evento;

import protocolo.Protocolo;

/**
 *
 * @author xxxx
 */
public class Cliente implements Runnable{

    Socket skt;
    ObjectOutputStream salida;
    ObjectInputStream  entrada;
    Vista juego;
    
    
    public Cliente(Vista aThis) {
        juego=aThis;
    }

    
    @Override
    public void run() {
    try {
            iniciarComponentes();
            while(true)
            leerMensajes();
        } catch (IOException | ClassNotFoundException ex) {}
    }

    private void iniciarComponentes() {
    try {
            skt=new Socket("localhost",Protocolo.PUERTO);
            salida=new ObjectOutputStream(skt.getOutputStream());
            entrada=new ObjectInputStream(skt.getInputStream());
        } catch (IOException ex) {}
    }

    
    private void leerMensajes() throws IOException, ClassNotFoundException {
           Evento e=(Evento) entrada.readObject();
           switch(e.getMensaje()){
               case  "INICIAR":mostrarVista((String )e.getInfo(),e.getNevento());break;// no escribe al servidor
               case  "TURNO"  :habilitar((String )e.getInfo());break;   //este envia respuesta
               case  "ACTUALIZACION" :iniciarAnimacion();break;//eno responde al servidor
               case  "GANADOR" :verGanador((String )e.getInfo());break;
           }
    }

    private void mostrarVista(String n, int x) {
        juego.actualizacion(n);
        juego.setNumCliente(x);
        juego.mostrar();
        
    }

    private void habilitar(String n) {
        juego.actualizacion(n);
        juego.iniciar();
        juego.habilitar();
    }

    private void iniciarAnimacion() {
        juego.iniciar();
    }
    
    public void mensaje(Evento e){
        try {salida.writeObject(e); } catch (IOException ex) {}
    }

    private void verGanador(String n) {
        juego.verGanador( n);
    }
}
