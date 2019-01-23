/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.vista;


import cliente.entidades.Vaso;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 *
 * @author xxxx
 */
public class PanelImagen extends JPanel{

    ImageIcon img;
    
    public PanelImagen() {
        
        vasos=new ArrayList<>();
        iniciarGrupoVasos();
      
    }
    
    private void iniciarGrupoVasos() {

        ancho=150;
        alto=200;
        for(int i=1; i<=CANT_VASOS; i++){
            Vaso v=new Vaso();
            v.iniciarComponentes(ancho*i,alto);
            vasos.add(v);
        }
    }
    
    public void reiniciar(){
        ancho=150;
        alto=200;
        int aux=0;
        for(int i=1; i<=CANT_VASOS; i++){
            vasos.get(aux).iniciarComponentes(ancho*i,alto);
            aux++; 
        }
    }

    
    @Override
    public void paintComponent(Graphics g){
        img=new ImageIcon(getClass().getResource("../icons/m.png"));
        
        g.drawImage(img.getImage(),0,0,this.getWidth(), this.getHeight(),this);
        for(int i=0; i <CANT_VASOS; i++){
            Vaso temp=vasos.get(i);
            g.drawImage(temp.getImg(),temp.getX(),temp.getY(),48, 48,this);
        }
    } 

 
    public void moverArriba(int numeroBaso){
           Vaso temp =vasos.get(numeroBaso);
           temp.moverArriba(); 
    }
    
    
    public void moverDerecha(int numeroBaso){
           Vaso temp =vasos.get(numeroBaso);
           temp.moverDerecha();
    }
    
   public void moverIzquierda(int numeroBaso){
           Vaso temp =vasos.get(numeroBaso);
           temp.moverIzquierda();
    }
   
   
    public void moverAbajo(int numeroBaso){
           Vaso temp =vasos.get(numeroBaso);
           temp.moverAbajo();
    }
    ArrayList<Vaso> vasos;
    public static int CANT_VASOS=3;
    int alto;
    int ancho;
}

