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
        this.asc = p.getAscensor();
        this.vista = p.getVista();
    }

    public void run() {
        int aviso = 0;
        while (!acabar) {
            if (this.asc.getSalidas().size() > 0) {
                int pisoObjetivo = this.asc.getSalidas().get(aviso).ordinal();
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
                Llamada l = this.asc.getLlamadas().get(aviso);
                int pisoObjetivo = l.getPiso();
                boolean subir = l.getSubir();

                if (!asc.puertaAbierta()) {
                    if (subir) { //SUBIR
                        if (pisoObjetivo > this.asc.getPisoActual()) {
                            if (asc.getEstado() == Estado.SUBIENDO || asc.getEstado() == Estado.PARADO) {
                                this.asc.setEstado(Estado.SUBIENDO);
                                subir(pisoObjetivo, l);
                                aviso = 0;
                            } else if (asc.getEstado() == Estado.BAJANDO) {
                                if (procede(false, 0) && procede(false, 2)) {
                                    this.asc.setEstado(Estado.SUBIENDO);
                                    subir(pisoObjetivo, l);
                                    aviso = 0;
                                } else {
                                    aviso = aviso + 1;
                                }
                            }

                        } else if (pisoObjetivo < this.asc.getPisoActual()) {
                            if (asc.getEstado() == Estado.SUBIENDO || asc.getEstado() == Estado.PARADO) {
                                if (procede(true, 1)) {
                                    this.asc.setEstado(Estado.BAJANDO);
                                    bajar(pisoObjetivo, l);
                                    aviso = 0;
                                } else {
                                    aviso = aviso + 1;
                                }

                            } else if (asc.getEstado() == Estado.BAJANDO) {
                                this.asc.setEstado(Estado.BAJANDO);
                                bajar(pisoObjetivo, l);
                                aviso = 0;

                            }

                        } else if (pisoObjetivo == this.asc.getPisoActual()) {
                            abrirPuerta();
                            asc.quitarLlamadas(asc.getPisoActual(), l.getSubir());
                        }
                    } else { //BAJAR
                        if (pisoObjetivo > this.asc.getPisoActual()) {
                            if (asc.getEstado() == Estado.SUBIENDO || asc.getEstado() == Estado.PARADO) {
                                this.asc.setEstado(Estado.SUBIENDO);
                                subir(pisoObjetivo, l);
                                aviso = 0;
                            } else if (asc.getEstado() == Estado.BAJANDO) {
                                if (procede(false, 0) && procede(false, 2)) {
                                    this.asc.setEstado(Estado.SUBIENDO);
                                    subir(pisoObjetivo, l);
                                    aviso = 0;
                                } else {
                                    aviso = aviso + 1;
                                }
                            }

                        } else if (pisoObjetivo < this.asc.getPisoActual()) {
                            if (asc.getEstado() == Estado.SUBIENDO || asc.getEstado() == Estado.PARADO) {
                                if (procede(true, 1)) {
                                    this.asc.setEstado(Estado.BAJANDO);
                                    bajar(pisoObjetivo, l);
                                    aviso = 0;
                                } else {
                                    aviso = aviso + 1;
                                }

                            } else if (asc.getEstado() == Estado.BAJANDO) {
                                this.asc.setEstado(Estado.BAJANDO);
                                bajar(pisoObjetivo, l);
                                aviso = 0;

                            }

                        } else if (pisoObjetivo == this.asc.getPisoActual()) {
                            abrirPuerta();
                            asc.quitarLlamadas(asc.getPisoActual(), l.getSubir());
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

            if (l == null) {
                l = new Llamada(asc.getPisoActual(), true);
            }

            if (asc.getPisoActual() == objetivo) {
                abrirPuerta();
                asc.quitarLlamadas(asc.getPisoActual(), l.getSubir());
                end = true;
//                asc.setEstado(Estado.PARADO);

            } else {
                if (asc.quitarLlamadas(asc.getPisoActual(), l.getSubir())) {
                    abrirPuerta();
                }

            }
        }

    }

    private void bajar(int objetivo, Llamada l) {
        boolean end = false;
        while (!end) {
            vista.notificar("BajarPiso");
            asc.bajarPiso();

            if (l == null) {
                l = new Llamada(asc.getPisoActual(), false);
            }
            if (asc.getPisoActual() == objetivo) {
                abrirPuerta();
                asc.quitarLlamadas(asc.getPisoActual(), l.getSubir());
                end = true;

            } else {
                if (asc.quitarLlamadas(asc.getPisoActual(), l.getSubir())) {
                    abrirPuerta();
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
                            && (asc.getLlamadas().get(i).getPiso() < asc.getPisoActual())) {
                        return false;
                    } else if (i >= asc.getLlamadas().size()) {
                        fin = true;
                    }
                    break;
                case 1:
                    if ((i < asc.getLlamadas().size())
                            && (asc.getLlamadas().get(i).getPiso() > asc.getPisoActual())) {
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
