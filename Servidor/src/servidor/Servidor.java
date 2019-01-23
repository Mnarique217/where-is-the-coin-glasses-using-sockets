/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import modelo.Evento;
import modelo.Modelo;

import protocolo.Protocolo;

/**
 * @author xxxx
 */
public class Servidor implements Runnable {

    
    ServerSocket server;
    Modelo modelo;
    Thread hilo;
    int nCliente;
    ArrayList<GestorCliente>grupoClientes;
    
    
    public Servidor() {
        modelo=new Modelo();
        grupoClientes=new ArrayList<>();
        nCliente=0;
        iniciar();
    }
    
    
    private void iniciar(){
        hilo=new Thread(this);
        if(hilo!=null)
            hilo.start();
    }
    
    @Override
    public void run() {
        iniciarComponentes();
    }

    private void iniciarComponentes() {
    try {
            server=new ServerSocket(Protocolo.PUERTO);
            controlFlujo(); 
        } catch (IOException e) {}
    }

    private void controlFlujo() throws IOException {

        while(nCliente < modelo.getCantidad() && hilo== Thread.currentThread()){
                     
                     System.out.println("esperando clientes ");
                     Socket skt=server.accept();
                     nCliente++;
                     System.out.println("recivido cliente "+nCliente);
                     GestorCliente c=new GestorCliente(skt, this, nCliente);
                     grupoClientes.add(c);
                     modelo.addObserver(c);
                     Thread hl=new Thread(c);
                     hl.start();//le escribii  para que se vea la vista
        }
        
        iniciarAnimacion();
        while(true){
        regularTurnos();}
    }

    private void regularTurnos() {
        
        for(GestorCliente cliente : grupoClientes) {
            cliente.mensaje(new Evento("te toca jugar", "TURNO",cliente.getNumCliente()));
            modelo.guardarEleccion(cliente.leerRespuesta(),cliente.getNumCliente());  
        }  
        modelo.revisar();
    }

    private void iniciarAnimacion() {
         for(GestorCliente cliente : grupoClientes) {
            cliente.mensaje(new Evento(null, "ACTUALIZACION",cliente.getNumCliente()));
        }
    }
}
