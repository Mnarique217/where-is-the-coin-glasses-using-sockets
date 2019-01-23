/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.io.Serializable;

/**
 * @author xxxx
 */
public class Evento implements Serializable {
    
    Object info;
    String mensaje;
    int    nevento;

    public Evento(Object info, String mensaje, int nevento) {
        this.info = info;
        this.mensaje = mensaje;
        this.nevento = nevento;
    }

    public Object getInfo() {
        return info;
    }

    public String getMensaje() {
        return mensaje;
    }

    public int getNevento() {
        return nevento;
    }
    
    
}
