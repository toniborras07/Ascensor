/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

import java.util.ArrayList;

/**
 *
 * @author Usuario
 */



public class Ascensor {
    public enum Piso{
       BAJO, PRIMERA, SEGUNDA,TERCERA, CUARTA
    }
    
    public enum Estado {
        PARADO, SUBIENDO, BAJANDO
    }
    
    private Main prog;
    private int pisoActual;
    private ArrayList<Piso> pisosSalida;
    private ArrayList<Llamada> pisosObjetivo;
    private int estadoPisos[];
    private boolean puertaAbierta;
    private Estado estado;
    
    public Ascensor(Main p){
        prog = p;
        puertaAbierta=false;
        estadoPisos= new int[5];
        pisosObjetivo= new ArrayList();
        pisosSalida = new ArrayList();
        estado = Estado.PARADO;
        for (int i = 0; i < estadoPisos.length; i++) {          
            estadoPisos[i]=0;
        }
    }
    
    public void addLlamada(Llamada l) {
        pisosObjetivo.add(l);
    }
    public void addSalida(Piso p){
        pisosSalida.add(p);
    }
    
    public int getPisoActual() {
        return this.pisoActual;
    }
    
    public boolean puertaAbierta(){
        return puertaAbierta;
    }
    
    public ArrayList<Llamada> getLlamadas(){
        return this.pisosObjetivo;
    }
    
    public ArrayList<Piso> getSalidas() {
        return this.pisosSalida;
    }
    
     public void setEstado(Estado estado) {
        this.estado = estado;
    }
     
     public Estado getEstado() {
        return estado;
    }
    
    public void quitarLlamadas(int p, boolean subir) {
        this.pisosObjetivo.removeIf(l -> (l.getPiso() == p && l.getSubir() == subir));
        this.pisosSalida.removeIf(pi -> pi.ordinal() == p);
    }
    
    public void subirPiso(){
        if (this.pisoActual < Piso.CUARTA.ordinal()) {
            this.pisoActual++;
        }
    }
    
    public void bajarPiso(){
        if (this.pisoActual > Piso.BAJO.ordinal()) {
            this.pisoActual--;
        }
    }
    
    public void abrirPuerta(){
        puertaAbierta=true;
    }
    
    public void cerrarPuerta(){
        puertaAbierta=false;
    }
    
    public int sig(int i){
        if (i < Piso.CUARTA.ordinal()) {
            return i + 1;
        }
        return -1; //-1 = null = no piso inferior
    }
    
    public int prec(int i){
        if (i > Piso.BAJO.ordinal()) {
            return i - 1;
        }
        return -1; //-1 = null = no piso inferior
    }
}
