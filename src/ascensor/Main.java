/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

/**
 * 
 * @author Xavier Matas y Antonio Borrás
 */
public class Main {
    private Ascensor asc;    // Punter al Model del patró
    private View vis;    // Punter a la Vista del patró
    private Controller con;  // punter al Control del patró
    
    private void inicio() {
        asc = new Ascensor(this);
        vis = new View(this);
        vis.mostrar();
        con = new Controller(this);
        con.start();       
        
    }
    
    public static void main(String [] args) {
        (new Main()).inicio();
    }
    
    //metodo para conseguir el modelo de datos
    public Ascensor getAscensor() {
        return this.asc;
    }

    //metodo para devolver la vista 
    public View getVista() {
        return this.vis;
    }

    //metodo que devuelve el controlador
    public Controller getController() {
        return con;
    }

}
