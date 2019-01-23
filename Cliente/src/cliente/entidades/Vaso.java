/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cliente.entidades;

import java.awt.Image;
import java.net.URL;
import javax.swing.ImageIcon;

/**
 *
 * @author xxxx
 */
public class Vaso {
    
    public void moverArriba(){
        Y-=5;
    }
    
    public void moverDerecha(){
        X+=5;
    }
    public void moverIzquierda(){
        X-=5;
    }
    public void moverAbajo(){
        Y+=5;
    }
    
    public void iniciarComponentes(int x,int y){
        this.X=x;
        this.Y=y;
        
        url=this.getClass().getResource("../icons/v.png");
        icono=new ImageIcon(url);
        img=icono.getImage();
    }

    public Image getImg() {
        return img;
    }

    public URL getUrl() {
        return url;
    }

    public ImageIcon getIcono() {
        return icono;
    }

    public int getX() {
        return X;
    }

    public int getY() {
        return Y;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public void setIcono(ImageIcon icono) {
        this.icono = icono;
    }

    public void setX(int X) {
        this.X = X;
    }

    public void setY(int Y) {
        this.Y = Y;
    }
    
    
    //atributos
    Image img;
    URL url;
    ImageIcon icono;
    int X;
    int Y;
    
    
}
