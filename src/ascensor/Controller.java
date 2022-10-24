package ascensor;

import ascensor.Ascensor.Estado;

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

    public Controller(Main p) {
        this.prog = p;
        this.asc = this.prog.getAscensor();
        this.vista = this.prog.getVista();
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
        vista.notificar("AbrirPuerta");
        asc.abrirPuerta();
        espera(5000);
        vista.notificar("CerrarPuerta");
        asc.cerrarPuerta();
        asc.quitarLlamadas(asc.getPisoActual(), false);
    }
}
