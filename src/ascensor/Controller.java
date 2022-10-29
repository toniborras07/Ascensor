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
                if (!asc.puertaAbierta()) {
                    if (pisoObjetivo > this.asc.getPisoActual()) {
                        if (asc.getEstado() == Estado.SUBIENDO || asc.getEstado() == Estado.PARADO) {
                            this.asc.setEstado(Estado.SUBIENDO);
                            subir(pisoObjetivo, null);
                        } else if (asc.getEstado() == Estado.BAJANDO) {
                            if (procede(false, 0) && procede(false, 2)) {
                                this.asc.setEstado(Estado.SUBIENDO);
                                subir(pisoObjetivo, null);
                            }
                        }

                    } else {
                        if (asc.getEstado() != Estado.SUBIENDO) {
                            this.asc.setEstado(Estado.BAJANDO);
                            bajar(pisoObjetivo, null);
                        } else if (asc.getEstado() == Estado.SUBIENDO) {
                            if (procede(true, 1) && procede(true, 3)) {
                                this.asc.setEstado(Estado.BAJANDO);
                                bajar(pisoObjetivo, null);
                            }
                        }

                    }
                }

            } else if (this.asc.getLlamadas().size() > 0) {
                Llamada l = this.asc.getLlamadas().get(0);
                int pisoObjetivo = l.getPiso();
                boolean subir = l.getSubir();

                if (!asc.puertaAbierta()) {
                    if (subir) { //SUBIR
                        if (pisoObjetivo > this.asc.getPisoActual()) {
                            if (asc.getEstado() == Estado.SUBIENDO || asc.getEstado() == Estado.PARADO) {
                                this.asc.setEstado(Estado.SUBIENDO);
                                subir(pisoObjetivo, l);
                            } else if (asc.getEstado() == Estado.BAJANDO) {
                                if (procede(false, 0) && procede(false, 2)) {
                                    this.asc.setEstado(Estado.SUBIENDO);
                                    subir(pisoObjetivo, l);
                                }
                            }

                        } else {
                            if (asc.getEstado() == Estado.SUBIENDO || asc.getEstado() == Estado.PARADO) {
                                if (procede(true, 1)) {
                                    this.asc.setEstado(Estado.BAJANDO);
                                    bajar(pisoObjetivo, l);
                                }

                            } else if (asc.getEstado() == Estado.BAJANDO) {
                                this.asc.setEstado(Estado.BAJANDO);
                                bajar(pisoObjetivo, l);

                            }

                        }
                    } else { //BAJAR
                        if (pisoObjetivo > this.asc.getPisoActual()) {
                            if (asc.getEstado() == Estado.SUBIENDO || asc.getEstado() == Estado.PARADO) {
                                this.asc.setEstado(Estado.SUBIENDO);
                                subir(pisoObjetivo, l);
                            } else if (asc.getEstado() == Estado.BAJANDO) {
                                if (procede(false, 0) && procede(false, 2)) {
                                    this.asc.setEstado(Estado.SUBIENDO);
                                    subir(pisoObjetivo, l);
                                }
                            }

                        } else {
                            if (asc.getEstado() == Estado.SUBIENDO || asc.getEstado() == Estado.PARADO) {
                                if (procede(true, 1)) {
                                    this.asc.setEstado(Estado.BAJANDO);
                                    bajar(pisoObjetivo, l);
                                }

                            } else if (asc.getEstado() == Estado.BAJANDO) {
                                this.asc.setEstado(Estado.BAJANDO);
                                bajar(pisoObjetivo, l);

                            }

                        }

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

    private void subir(int objetivo, Llamada l) {
        boolean end = false;
        while (!end) {
            vista.notificar("SubirPiso");
            asc.subirPiso();

            if (asc.getPisoActual() == objetivo) {
                abrirPuerta();
                asc.quitarLlamadas(asc.getPisoActual(), l.getSubir());
                end = true;
//                asc.setEstado(Estado.PARADO);

            } else {
                boolean fin = false;
                int i = 0;
                while (!fin) {
                    if ((i < asc.getLlamadas().size())
                            && (asc.getLlamadas().get(i).getPiso() == asc.getPisoActual())
                            && (asc.getLlamadas().get(i).getSubir())) {
                        abrirPuerta();
                        asc.quitarLlamadas(asc.getPisoActual(), l.getSubir());
                        fin = true;
                    } else {
                        if (i >= asc.getLlamadas().size()) {
                            fin = true;
                        } else {
                            i++;
                        }

                    }

                }

            }
        }

    }

    private void bajar(int objetivo, Llamada l) {
        boolean end = false;
        while (!end) {
            vista.notificar("BajarPiso");
            asc.bajarPiso();

            if (asc.getPisoActual() == objetivo) {
                abrirPuerta();
                asc.quitarLlamadas(asc.getPisoActual(), l.getSubir());
                end = true;

            } else {
                boolean fin = false;
                int i = 0;
                while (!fin) {
                    if ((i < asc.getLlamadas().size())
                            && (asc.getLlamadas().get(i).getPiso() == asc.getPisoActual())
                            && !(asc.getLlamadas().get(i).getSubir())) {
                        abrirPuerta();
                        asc.quitarLlamadas(asc.getPisoActual(), l.getSubir());
                        fin = true;
                    } else {
                        if (i >= asc.getLlamadas().size()) {
                            fin = true;
                        } else {
                            i++;
                        }

                    }

                }

            }
        }
    }

    public boolean procede(boolean subir, int opcion) {
        boolean fin = false;
        int i = 0;
        while (!fin) {
            switch (opcion) {
                case 0:
                    if ((i < asc.getLlamadas().size())
                            && (asc.getLlamadas().get(i).getPiso() < asc.getPisoActual())
                            && (asc.getLlamadas().get(i).getSubir() == subir)) {
                        return false;
                    } else if (i >= asc.getLlamadas().size()) {
                        fin = true;
                    }
                    break;
                case 1:
                    if ((i < asc.getLlamadas().size())
                            && (asc.getLlamadas().get(i).getPiso() > asc.getPisoActual())
                            && (asc.getLlamadas().get(i).getSubir() == subir)) {
                        return false;
                    } else if (i >= asc.getLlamadas().size()) {
                        fin = true;
                    }
                    break;

                case 2:
                    if ((i < asc.getSalidas().size())
                            && (asc.getSalidas().get(i).ordinal() < asc.getPisoActual())) {
                        return false;
                    } else if (i >= asc.getLlamadas().size()) {
                        fin = true;
                    }
                    break;
                case 3:
                    if ((i < asc.getSalidas().size())
                            && (asc.getSalidas().get(i).ordinal() > asc.getPisoActual())) {
                        return false;
                    } else if (i >= asc.getLlamadas().size()) {
                        fin = true;
                    }
                    break;
            }
            i++;

        }
        return true;
    }

    public void abrirPuerta() {
        vista.notificar("AbrirPuerta");
        asc.abrirPuerta();
        espera(5000);
        vista.notificar("CerrarPuerta");
        asc.cerrarPuerta();

    }

}
