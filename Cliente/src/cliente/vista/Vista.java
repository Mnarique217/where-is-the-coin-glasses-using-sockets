/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vista;

import cliente.cliente.Cliente;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import static java.lang.Thread.sleep;


import javax.swing.*;
import modelo.Evento;


/**
 * @author xxxx
 */
public class Vista extends JFrame implements Runnable{

    public Vista (){
        
        configurarVentana();
        agregarComponentes();
        conectarse();
    }
    
   public void mostrar(){
        this.setVisible(true);
    }
    
   private void configurarVentana(){

       this.setSize(640, 480);
       this.setResizable(false);
       this.setLocationRelativeTo(null);
       this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
   
   private void agregarComponentes(){
       pnl=new JPanel(new BorderLayout());   
       fondo=new PanelImagen();
       actualizacion=new JLabel(".....");
       numeroCliente=new JLabel("Jugador # ");
       
       btn0=new JButton("vaso 1");
       btn0.addActionListener((ActionEvent e) -> {actualizacion.setText("espera resultado.. elejiste el vaso 1");this.deshabilitar(); cliente.mensaje(new Evento(null, "0", HEIGHT));});
       
       btn1=new JButton("vaso 2");
       btn1.addActionListener((ActionEvent e) -> {actualizacion.setText("espera resultado.. elejiste el vaso 2");this.deshabilitar(); cliente.mensaje(new Evento(null, "1", HEIGHT));});
       
       btn2=new JButton("vaso 3");
       btn2.addActionListener((ActionEvent e) -> {actualizacion.setText("espera resultado.. elejiste el vaso 3");this.deshabilitar(); cliente.mensaje(new Evento(null, "2", HEIGHT));});
       
        JPanel aux=new JPanel();
        aux.add(numeroCliente);
        aux.add(btn0);
        aux.add(btn1);
        aux.add(btn2);
        aux.add(actualizacion);
        pnl.add(fondo);
        pnl.add(aux,BorderLayout.PAGE_END);
       this.getContentPane().add(pnl);  
       deshabilitar();
   }
   
   public void iniciar(){
   
       hilo=new Thread(this);
       if(hilo!=null)
           hilo.start();   
   }
   
    @Override
    public void run() {
        int cont=0;
        int cont2=0;
        int cont3=0;
        while(hilo==Thread.currentThread()){
            try {
                    cont++;
                    if(cont<30)
                        moverIzqArriba();
                    else{
                     cont2++;
                     if(cont2<30)
                       fondo.moverIzquierda(1);
                     else{
                         cont3++;
                         if(cont3<30)
                         fondo.moverAbajo(1);
                         else
                             hilo=null; 
                        }            
                    }
                    sleep(50);
                    fondo.repaint();
                    this.requestFocus();
            } catch (InterruptedException ex) {}
        
        }fondo.reiniciar();
        
    }
    public void moverIzqArriba(){
        fondo.moverArriba(1);
        fondo.moverDerecha(0);
    }
   
   private void conectarse() {
       cliente =new Cliente(this);
       hilo=new Thread(cliente);
       
       if( hilo!= null )
           hilo.start();
       
   }

    public void habilitar() {
       
       btn0.setEnabled(true);
       btn1.setEnabled(true);
       btn2.setEnabled(true);
       JOptionPane.showMessageDialog(this,"elije un vaso" ,"elije un vaso",1);
    }
  
   private void deshabilitar() {
       btn0.setEnabled(false);
       btn1.setEnabled(false);
       btn2.setEnabled(false);
    }
     
   public void actualizacion(String n){
         actualizacion.setText(n);
     }
     
   public void setNumCliente(int x){
       numeroCliente.setText(numeroCliente.getText()+x);
     }
     
   public void verGanador(String n) {
       
       JOptionPane.showMessageDialog(this,"...."+n,"ganador",1);
       fondo.reiniciar();
    }

   JPanel pnl;
   PanelImagen fondo;
   JButton btn0;
   JButton btn1;
   JButton btn2;
   Thread hilo;
   Cliente cliente;
   JLabel actualizacion;
   JLabel numeroCliente;

}
