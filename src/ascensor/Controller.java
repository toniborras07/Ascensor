package ascensor;

import ascensor.Ascensor.Estado;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author XAMAP
 */
public class Controller extends Thread {

    private static Main prog;
    private boolean acabar = false;
    Ascensor asc;
    View vista;
    private boolean sleeping = false;
    
    public Controller(Main p) {
        this.prog = p;
        this.asc = p.getAscensor();
        this.vista = p.getVista();
    }

    public void run() {
        while (!acabar) {
            if (this.asc.getSalidas().size() > 0) {
                int pisoObjetivo = this.asc.getSalidas().get(0).ordinal();
                if (pisoObjetivo > this.asc.getPisoActual()) {
                    this.asc.setEstado(Estado.SUBIENDO);
                    subir();
                } else {
                    this.asc.setEstado(Estado.BAJANDO);
                    bajar();
                }

            } else if (this.asc.getLlamadas().size() > 0) {
                Llamada l = this.asc.getLlamadas().get(0);
                int pisoObjetivo = l.getPiso();
                boolean subir = l.getSubir();
                if (subir) {
                    if (pisoObjetivo > this.asc.getPisoActual()) {
                        this.asc.setEstado(Estado.SUBIENDO);
                        subir();
                    } else {
                        this.asc.setEstado(Estado.BAJANDO);
                        bajar();
                    }
                } else {
                    if (pisoObjetivo > this.asc.getPisoActual()) {
                        this.asc.setEstado(Estado.SUBIENDO);
                        subir();
                    } else {
                        this.asc.setEstado(Estado.BAJANDO);
                        bajar();
                    }
                }
            } else {
                this.asc.setEstado(Estado.PARADO);
            }
        }
    }

    private void espera(long m) {
        try {
            Thread.sleep(m);
        } catch (Exception e) {
        }
    }

    private void subir() {
        vista.notificar("SubirPiso");
        asc.subirPiso();
//        while(sleeping) {
//            try {
//                Thread.sleep(1);
//            } catch (InterruptedException ex) {
//                System.out.println(ex);
//            }
//        }
        
        vista.notificar("AbrirPuerta");
        asc.abrirPuerta();
        espera(5000);
        vista.notificar("CerrarPuerta");
        asc.cerrarPuerta();
        asc.quitarLlamadas(asc.getPisoActual(), true);
    }

    private void bajar() {
        vista.notificar("BajarPiso");
        asc.bajarPiso();
        while(sleeping) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException ex) {
                System.out.println(ex);
            }
        }
        vista.notificar("AbrirPuerta");
        asc.abrirPuerta();
        espera(5000);
        vista.notificar("CerrarPuerta");
        asc.cerrarPuerta();
        asc.quitarLlamadas(asc.getPisoActual(), false);
    }
    
//    public void notificar(String s) {
//        switch(s){
//            case "sleep":
//                this.sleeping = true;
//                break;
//            case "wake":
//                this.sleeping = false;
//                break;
//        }
//    }
}
