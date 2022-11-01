/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

/**
 *
 * @author Xavier Matas y Antonio Borrás
 */
public class Llamada {
    private int piso;
    private boolean subir;
    
    public Llamada(int p, boolean s) {
        this.piso = p;
        this.subir = s;
    }
    
    public int getPiso(){
        return this.piso;
    }
    
    public boolean getSubir() {
        return this.subir;
    }
}
