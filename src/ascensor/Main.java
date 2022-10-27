/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ascensor;

/**
 *
 * @author XAMAP
 */
public class Main {
    private Ascensor asc;    // Punter al Model del patró
    private View vis;    // Punter a la Vista del patró
    private Controller con;  // punter al Control del patró
    
    private void inicio() {
        asc = new Ascensor(this);
        con = new Controller(this);
        vis = new View(this);
        vis.mostrar();
        con.start();
        
    }
    
    public static void main(String [] args) {
        (new Main()).inicio();
    }
    
    //metodo para conseguir el modelo de datos
    public Ascensor getAscensor() {
        return asc;
    }

    //metodo para devolver la vista 
    public View getVista() {
        return vis;
    }

    //metodo que devuelve el controlador
    public Controller getController() {
        return con;
    }

}
