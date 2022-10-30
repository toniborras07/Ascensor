/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package ascensor;

import ascensor.Ascensor.Piso;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Usuario
 */
public class View extends javax.swing.JFrame implements MouseListener {

    ImageIcon imageAscensorOpen = new ImageIcon("ascOpen.png");
    ImageIcon imageAscensorClosed = new ImageIcon("ascClosed.png");
    ImageIcon verde = new ImageIcon("src/img/verde.png");
    ImageIcon rojo = new ImageIcon("src/img/rojo.png");

    boolean subir;

    /**
     * Creates new form Interaze
     */
    public View(Main p) {
        this.prog = p;
        initComponents();

    }

    public void mostrar() {
        this.pack();

        setLocationRelativeTo(null);
        setTitle("Ascensor");
        //setSize(769,610);

        ImageIcon wallpaper = new ImageIcon("src/img/cerrado.png");
        Icon icono = new ImageIcon(wallpaper.getImage().getScaledInstance(this.Ascensor.getWidth(), this.Ascensor.getHeight(), Image.SCALE_DEFAULT));
        Icon icono1 = new ImageIcon(rojo.getImage().getScaledInstance(this.bajPrimero.getWidth(), this.bajPrimero.getHeight(), Image.SCALE_DEFAULT));
        Icon icono2 = new ImageIcon(rojo.getImage().getScaledInstance(this.bajSegundo.getWidth(), this.bajSegundo.getHeight(), Image.SCALE_DEFAULT));
        Icon icono3 = new ImageIcon(rojo.getImage().getScaledInstance(this.bajtercero.getWidth(), this.bajtercero.getHeight(), Image.SCALE_DEFAULT));
        Icon icono4 = new ImageIcon(rojo.getImage().getScaledInstance(this.bajCuarto.getWidth(), this.bajCuarto.getHeight(), Image.SCALE_DEFAULT));
        Icon icono5 = new ImageIcon(rojo.getImage().getScaledInstance(this.subPrimero.getWidth(), this.subPrimero.getHeight(), Image.SCALE_DEFAULT));
        Icon icono6 = new ImageIcon(rojo.getImage().getScaledInstance(this.subSegundo.getWidth(), this.subSegundo.getHeight(), Image.SCALE_DEFAULT));
        Icon icono7 = new ImageIcon(rojo.getImage().getScaledInstance(this.subTercero.getWidth(), this.subTercero.getHeight(), Image.SCALE_DEFAULT));
        Icon icono8 = new ImageIcon(rojo.getImage().getScaledInstance(this.subBajo.getWidth(), this.subBajo.getHeight(), Image.SCALE_DEFAULT));

        Icon icono11 = new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel2.getWidth(), this.jLabel2.getHeight(), Image.SCALE_DEFAULT));
        Icon icono22 = new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel3.getWidth(), this.jLabel3.getHeight(), Image.SCALE_DEFAULT));
        Icon icono33 = new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel4.getWidth(), this.jLabel4.getHeight(), Image.SCALE_DEFAULT));
        Icon icono44 = new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel5.getWidth(), this.jLabel5.getHeight(), Image.SCALE_DEFAULT));
        Icon icono55 = new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel6.getWidth(), this.jLabel6.getHeight(), Image.SCALE_DEFAULT));

        this.Ascensor.setIcon(icono);
        this.subBajo.setIcon(icono8);
        this.bajPrimero.setIcon(icono1);
        this.bajSegundo.setIcon(icono2);
        this.bajtercero.setIcon(icono3);
        this.bajCuarto.setIcon(icono4);
        this.subPrimero.setIcon(icono5);
        this.subSegundo.setIcon(icono6);
        this.subTercero.setIcon(icono7);

        this.jLabel2.setIcon(icono11);
        this.jLabel3.setIcon(icono22);
        this.jLabel4.setIcon(icono33);
        this.jLabel5.setIcon(icono44);
        this.jLabel6.setIcon(icono55);
        try {
            Thread.sleep(500);
        } catch (Exception e) {
        }

        this.setResizable(false);
        this.revalidate();
        //this.repaint();
        this.setVisible(true);

//        animacion(0);
    }

    public void animacion(int n) {
        int y;
        int nextFloor;
        boolean llegado;
        ImageIcon wallpaper;
        Icon icono;
        y = this.Ascensor.getY();
        if (n == 0) {
            nextFloor = y - (this.Ascensor.getHeight() + 5);
        } else {
            nextFloor = y + (this.Ascensor.getHeight() + 5);
        }

        llegado = false;

        while (!llegado) {
            if (this.Ascensor.getY() != nextFloor) {
                if (n == 0) {
                    this.Ascensor.setLocation(this.Ascensor.getX(), this.Ascensor.getY() - 1);
                    this.Ascensor.repaint();

                } else {
                    this.Ascensor.setLocation(this.Ascensor.getX(), this.Ascensor.getY() + 1);
                    this.Ascensor.repaint();
                }

                try {
                    //PARALIZACIÓN DE LA EJECUCIÓN DURANTE RETRASO/1000 SEGUNDOS
                    //PARA SIMULAR LA VELOCIDAD DEL MOVIMIENTO DE LA
                    //FIGURA ELIPSE
                    Thread.sleep(20);
                } catch (InterruptedException err) {
                    System.out.println(err);
                }

            } else {
                llegado = true;
            }
        }
    }

    public void notificar(String s) {
        int y;
        int nextFloor;
        boolean llegado;
        ImageIcon wallpaper;
        Icon icono;

        switch (s) {
            case "BajarPiso":
                this.animacion(1);
                break;
            case "SubirPiso":
                this.animacion(0);
                break;
            case "AbrirPuerta":
                wallpaper = new ImageIcon("src/img/abierto.png");
                icono = new ImageIcon(wallpaper.getImage().getScaledInstance(this.Ascensor.getWidth(), this.Ascensor.getHeight(), Image.SCALE_DEFAULT));
                this.Ascensor.setIcon(icono);
                this.repaint();
                break;
            case "CerrarPuerta":
                wallpaper = new ImageIcon("src/img/cerrado.png");
                icono = new ImageIcon(wallpaper.getImage().getScaledInstance(this.Ascensor.getWidth(), this.Ascensor.getHeight(), Image.SCALE_DEFAULT));
                this.Ascensor.setIcon(icono);
                this.cambiarLuzBoton(this.prog.getAscensor().getPisoActual());
                this.repaint();
                break;
        }

    }

    public void cambiarLuzBoton(int piso) {
        switch (piso) {
            case 0:
                this.subBajo.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.subBajo.getWidth(),
                        this.subBajo.getHeight(), Image.SCALE_DEFAULT)));
                this.jLabel6.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel6.getWidth(),
                        this.jLabel6.getHeight(), Image.SCALE_DEFAULT)));
                this.repaint();
                break;
            case 1:
                this.subPrimero.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.subPrimero.getWidth(),
                        this.subPrimero.getHeight(), Image.SCALE_DEFAULT)));
                this.bajPrimero.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.bajPrimero.getWidth(),
                        this.bajPrimero.getHeight(), Image.SCALE_DEFAULT)));
                this.jLabel5.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel5.getWidth(),
                        this.jLabel5.getHeight(), Image.SCALE_DEFAULT)));
                this.repaint();
                break;
            case 2:
                this.subSegundo.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.subSegundo.getWidth(),
                        this.subSegundo.getHeight(), Image.SCALE_DEFAULT)));
                this.bajSegundo.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.bajSegundo.getWidth(),
                        this.bajSegundo.getHeight(), Image.SCALE_DEFAULT)));
                this.jLabel4.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel4.getWidth(),
                        this.jLabel4.getHeight(), Image.SCALE_DEFAULT)));
                this.repaint();
                break;
            case 3:
                this.subTercero.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.subTercero.getWidth(),
                        this.subTercero.getHeight(), Image.SCALE_DEFAULT)));
                this.bajtercero.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.bajtercero.getWidth(),
                        this.bajtercero.getHeight(), Image.SCALE_DEFAULT)));
                this.jLabel3.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel3.getWidth(),
                        this.jLabel3.getHeight(), Image.SCALE_DEFAULT)));
                this.repaint();
                break;
            case 4:
                this.bajCuarto.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.bajCuarto.getWidth(),
                        this.bajCuarto.getHeight(), Image.SCALE_DEFAULT)));
                this.jLabel2.setIcon(new ImageIcon(rojo.getImage().getScaledInstance(this.jLabel2.getWidth(),
                        this.jLabel2.getHeight(), Image.SCALE_DEFAULT)));
                this.repaint();
                break;

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        exterior = new javax.swing.JPanel();
        fondo = new javax.swing.JPanel();
        ediIzq = new javax.swing.JPanel();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        sub3 = new javax.swing.JButton();
        tercero = new javax.swing.JLabel();
        segundo = new javax.swing.JLabel();
        primero = new javax.swing.JLabel();
        bajo = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel10 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        sub2 = new javax.swing.JButton();
        sub1 = new javax.swing.JButton();
        subB = new javax.swing.JButton();
        subTercero = new javax.swing.JLabel();
        subSegundo = new javax.swing.JLabel();
        subPrimero = new javax.swing.JLabel();
        subBajo = new javax.swing.JLabel();
        ediDer = new javax.swing.JPanel();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        baj1 = new javax.swing.JButton();
        baj2 = new javax.swing.JButton();
        baj3 = new javax.swing.JButton();
        baj4 = new javax.swing.JButton();
        bajCuarto = new javax.swing.JLabel();
        bajtercero = new javax.swing.JLabel();
        bajSegundo = new javax.swing.JLabel();
        bajPrimero = new javax.swing.JLabel();
        Ascensor = new javax.swing.JLabel();
        panelAsc = new javax.swing.JPanel();
        asc4 = new javax.swing.JButton();
        asc3 = new javax.swing.JButton();
        asc2 = new javax.swing.JButton();
        ascBajo = new javax.swing.JButton();
        asc1 = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();

        jLabel1.setText("jLabel1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(135, 206, 235));
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        exterior.setBackground(new java.awt.Color(0, 32, 255));

        fondo.setBackground(new java.awt.Color(0, 0, 0));

        ediIzq.setBackground(new java.awt.Color(255, 255, 204));

        jSeparator14.setBackground(new java.awt.Color(0, 0, 0));

        jSeparator15.setBackground(new java.awt.Color(0, 0, 0));

        sub3.setText("S");
        sub3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub3ActionPerformed(evt);
            }
        });

        tercero.setText("  3º");

        segundo.setText(" 2º");

        primero.setText(" 1º");

        bajo.setText(" Bajo");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));

        jLabel10.setText("4º");

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));

        sub2.setText("S");
        sub2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub2ActionPerformed(evt);
            }
        });

        sub1.setText("S");
        sub1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sub1ActionPerformed(evt);
            }
        });

        subB.setText("S");
        subB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subBActionPerformed(evt);
            }
        });

        subTercero.setText("jLabel6");

        subSegundo.setText("jLabel7");

        subPrimero.setText("jLabel8");

        subBajo.setText("jLabel9");

        javax.swing.GroupLayout ediIzqLayout = new javax.swing.GroupLayout(ediIzq);
        ediIzq.setLayout(ediIzqLayout);
        ediIzqLayout.setHorizontalGroup(
            ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator14)
            .addComponent(jSeparator15)
            .addGroup(ediIzqLayout.createSequentialGroup()
                .addGroup(ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(segundo)
                    .addComponent(primero))
                .addGap(0, 0, Short.MAX_VALUE))
            .addComponent(jSeparator1)
            .addComponent(jSeparator3)
            .addGroup(ediIzqLayout.createSequentialGroup()
                .addGroup(ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ediIzqLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ediIzqLayout.createSequentialGroup()
                                .addComponent(jLabel10)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ediIzqLayout.createSequentialGroup()
                                .addGap(0, 60, Short.MAX_VALUE)
                                .addComponent(subTercero, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sub3))))
                    .addGroup(ediIzqLayout.createSequentialGroup()
                        .addGroup(ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tercero)
                            .addComponent(bajo))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ediIzqLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ediIzqLayout.createSequentialGroup()
                                .addComponent(subSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sub2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ediIzqLayout.createSequentialGroup()
                                .addComponent(subBajo, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(subB))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ediIzqLayout.createSequentialGroup()
                                .addComponent(subPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(sub1)))))
                .addContainerGap())
        );
        ediIzqLayout.setVerticalGroup(
            ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ediIzqLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addGap(86, 86, 86)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(tercero)
                .addGap(42, 42, 42)
                .addGroup(ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sub3)
                    .addComponent(subTercero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator15, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(segundo)
                .addGap(45, 45, 45)
                .addGroup(ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sub2)
                    .addComponent(subSegundo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator14, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(primero)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 18, Short.MAX_VALUE)
                .addGroup(ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(sub1)
                    .addComponent(subPrimero))
                .addGap(23, 23, 23)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(21, 21, 21)
                .addComponent(bajo)
                .addGap(37, 37, 37)
                .addGroup(ediIzqLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(subB)
                    .addComponent(subBajo))
                .addContainerGap())
        );

        ediDer.setBackground(new java.awt.Color(255, 255, 204));

        jSeparator16.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator16.setMinimumSize(new java.awt.Dimension(50, 11));

        jSeparator17.setBackground(new java.awt.Color(0, 0, 0));

        jSeparator18.setBackground(new java.awt.Color(0, 0, 0));

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));

        baj1.setText("B");
        baj1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baj1ActionPerformed(evt);
            }
        });

        baj2.setText("B");
        baj2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baj2ActionPerformed(evt);
            }
        });

        baj3.setText("B");
        baj3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baj3ActionPerformed(evt);
            }
        });

        baj4.setText("B");
        baj4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baj4ActionPerformed(evt);
            }
        });

        bajCuarto.setText("jLabel11");

        bajtercero.setText("jLabel12");

        bajSegundo.setText("jLabel13");

        bajPrimero.setText("jLabel14");

        javax.swing.GroupLayout ediDerLayout = new javax.swing.GroupLayout(ediDer);
        ediDer.setLayout(ediDerLayout);
        ediDerLayout.setHorizontalGroup(
            ediDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator18, javax.swing.GroupLayout.Alignment.TRAILING)
            .addComponent(jSeparator2)
            .addComponent(jSeparator17)
            .addGroup(ediDerLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(ediDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ediDerLayout.createSequentialGroup()
                        .addComponent(baj1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bajPrimero, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ediDerLayout.createSequentialGroup()
                        .addComponent(baj2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bajSegundo, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ediDerLayout.createSequentialGroup()
                        .addComponent(baj3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bajtercero, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(ediDerLayout.createSequentialGroup()
                        .addComponent(baj4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bajCuarto, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(65, Short.MAX_VALUE))
            .addComponent(jSeparator16, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        ediDerLayout.setVerticalGroup(
            ediDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ediDerLayout.createSequentialGroup()
                .addGap(73, 73, 73)
                .addGroup(ediDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baj4)
                    .addComponent(bajCuarto))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(ediDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baj3)
                    .addComponent(bajtercero))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator18, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(68, 68, 68)
                .addGroup(ediDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baj2)
                    .addComponent(bajSegundo))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator17, javax.swing.GroupLayout.PREFERRED_SIZE, 11, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(ediDerLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(baj1)
                    .addComponent(bajPrimero))
                .addGap(18, 18, 18)
                .addComponent(jSeparator16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114))
        );

        Ascensor.setBackground(new java.awt.Color(8, 138, 238));
        Ascensor.setForeground(new java.awt.Color(140, 200, 130));
        Ascensor.setText("jLabel3");

        javax.swing.GroupLayout fondoLayout = new javax.swing.GroupLayout(fondo);
        fondo.setLayout(fondoLayout);
        fondoLayout.setHorizontalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(fondoLayout.createSequentialGroup()
                .addComponent(ediIzq, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Ascensor, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(ediDer, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        fondoLayout.setVerticalGroup(
            fondoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(ediIzq, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(ediDer, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, fondoLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Ascensor, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        asc4.setText("4º");
        asc4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asc4ActionPerformed(evt);
            }
        });

        asc3.setText("3º");
        asc3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asc3ActionPerformed(evt);
            }
        });

        asc2.setText("2º");
        asc2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asc2ActionPerformed(evt);
            }
        });

        ascBajo.setText("Bajo");
        ascBajo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ascBajoActionPerformed(evt);
            }
        });

        asc1.setText("1º");
        asc1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asc1ActionPerformed(evt);
            }
        });

        jLabel2.setText("jLabel2");

        jLabel3.setText("jLabel3");

        jLabel4.setText("jLabel4");

        jLabel5.setText("jLabel5");

        jLabel6.setText("jLabel6");

        javax.swing.GroupLayout panelAscLayout = new javax.swing.GroupLayout(panelAsc);
        panelAsc.setLayout(panelAscLayout);
        panelAscLayout.setHorizontalGroup(
            panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAscLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(panelAscLayout.createSequentialGroup()
                        .addGroup(panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(asc3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(asc4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 13, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(panelAscLayout.createSequentialGroup()
                        .addComponent(ascBajo)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(panelAscLayout.createSequentialGroup()
                        .addComponent(asc2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 1, Short.MAX_VALUE))
                    .addGroup(panelAscLayout.createSequentialGroup()
                        .addComponent(asc1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)))
                .addContainerGap(15, Short.MAX_VALUE))
        );
        panelAscLayout.setVerticalGroup(
            panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAscLayout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asc4)
                    .addComponent(jLabel2))
                .addGap(18, 18, 18)
                .addGroup(panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asc3)
                    .addComponent(jLabel3))
                .addGap(18, 18, 18)
                .addGroup(panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(asc2)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(asc1)
                    .addGroup(panelAscLayout.createSequentialGroup()
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(8, 8, 8)))
                .addGap(18, 18, 18)
                .addGroup(panelAscLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ascBajo)
                    .addComponent(jLabel6))
                .addContainerGap(31, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout exteriorLayout = new javax.swing.GroupLayout(exterior);
        exterior.setLayout(exteriorLayout);
        exteriorLayout.setHorizontalGroup(
            exteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exteriorLayout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, 458, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(panelAsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(182, Short.MAX_VALUE))
        );
        exteriorLayout.setVerticalGroup(
            exteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(exteriorLayout.createSequentialGroup()
                .addGroup(exteriorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(exteriorLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(fondo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(exteriorLayout.createSequentialGroup()
                        .addGap(146, 146, 146)
                        .addComponent(panelAsc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        getContentPane().add(exterior, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void sub3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub3ActionPerformed
        // TODO add your handling code here:
        Llamada l = new Llamada(3, true);
        this.subTercero.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.subTercero.getWidth(),
                this.subTercero.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addLlamada(l);
    }//GEN-LAST:event_sub3ActionPerformed

    private void sub2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub2ActionPerformed
        // TODO add your handling code here:
        Llamada l = new Llamada(2, true);
        this.subSegundo.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.subSegundo.getWidth(),
                this.subSegundo.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addLlamada(l);
    }//GEN-LAST:event_sub2ActionPerformed

    private void sub1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sub1ActionPerformed
        // TODO add your handling code here:
        Llamada l = new Llamada(1, true);
        this.subPrimero.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.subPrimero.getWidth(),
                this.subPrimero.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addLlamada(l);
    }//GEN-LAST:event_sub1ActionPerformed

    private void subBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subBActionPerformed
        // TODO add your handling code here:
        Llamada l = new Llamada(0, true);
        this.subBajo.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.subBajo.getWidth(),
                this.subBajo.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addLlamada(l);
    }//GEN-LAST:event_subBActionPerformed

    private void baj1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baj1ActionPerformed
        // TODO add your handling code here:
        Llamada l = new Llamada(1, false);
        this.bajPrimero.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.bajPrimero.getWidth(),
                this.bajPrimero.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addLlamada(l);
    }//GEN-LAST:event_baj1ActionPerformed

    private void baj2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baj2ActionPerformed
        // TODO add your handling code here:
        Llamada l = new Llamada(2, false);
        this.bajSegundo.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.bajSegundo.getWidth(),
                this.bajSegundo.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addLlamada(l);
    }//GEN-LAST:event_baj2ActionPerformed

    private void baj3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baj3ActionPerformed
        // TODO add your handling code here:
        Llamada l = new Llamada(3, false);
        this.bajtercero.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.bajtercero.getWidth(),
                this.bajtercero.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addLlamada(l);
    }//GEN-LAST:event_baj3ActionPerformed

    private void baj4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_baj4ActionPerformed
        // TODO add your handling code here:
        Llamada l = new Llamada(4, false);
        this.bajCuarto.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.bajCuarto.getWidth(),
                this.bajCuarto.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addLlamada(l);
    }//GEN-LAST:event_baj4ActionPerformed

    private void asc4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asc4ActionPerformed
        // TODO add your handling code here:
       
        this.jLabel2.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.jLabel2.getWidth(),
                        this.jLabel2.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
         this.prog.getAscensor().addSalida(Piso.CUARTA);
    }//GEN-LAST:event_asc4ActionPerformed

    private void asc3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asc3ActionPerformed
        // TODO add your handling code here:
        
        this.jLabel3.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.jLabel3.getWidth(),
                        this.jLabel3.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addSalida(Piso.TERCERA);
    }//GEN-LAST:event_asc3ActionPerformed

    private void asc2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asc2ActionPerformed
        // TODO add your handling code here:
       
        this.jLabel4.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.jLabel4.getWidth(),
                        this.jLabel4.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
         this.prog.getAscensor().addSalida(Piso.SEGUNDA);
    }//GEN-LAST:event_asc2ActionPerformed

    private void asc1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asc1ActionPerformed
        // TODO add your handling code here:
        
        this.jLabel5.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.jLabel5.getWidth(),
                        this.jLabel5.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addSalida(Piso.PRIMERA);
    }//GEN-LAST:event_asc1ActionPerformed

    private void ascBajoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ascBajoActionPerformed
        // TODO add your handling code here:
        this.jLabel6.setIcon(new ImageIcon(verde.getImage().getScaledInstance(this.jLabel6.getWidth(),
                        this.jLabel6.getHeight(), Image.SCALE_DEFAULT)));
        this.repaint();
        this.prog.getAscensor().addSalida(Piso.BAJO);
    }//GEN-LAST:event_ascBajoActionPerformed

    private Main prog;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Ascensor;
    private javax.swing.JButton asc1;
    private javax.swing.JButton asc2;
    private javax.swing.JButton asc3;
    private javax.swing.JButton asc4;
    private javax.swing.JButton ascBajo;
    private javax.swing.JButton baj1;
    private javax.swing.JButton baj2;
    private javax.swing.JButton baj3;
    private javax.swing.JButton baj4;
    private javax.swing.JLabel bajCuarto;
    private javax.swing.JLabel bajPrimero;
    private javax.swing.JLabel bajSegundo;
    private javax.swing.JLabel bajo;
    private javax.swing.JLabel bajtercero;
    private javax.swing.JPanel ediDer;
    private javax.swing.JPanel ediIzq;
    private javax.swing.JPanel exterior;
    private javax.swing.JPanel fondo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel panelAsc;
    private javax.swing.JLabel primero;
    private javax.swing.JLabel segundo;
    private javax.swing.JButton sub1;
    private javax.swing.JButton sub2;
    private javax.swing.JButton sub3;
    private javax.swing.JButton subB;
    private javax.swing.JLabel subBajo;
    private javax.swing.JLabel subPrimero;
    private javax.swing.JLabel subSegundo;
    private javax.swing.JLabel subTercero;
    private javax.swing.JLabel tercero;
    // End of variables declaration//GEN-END:variables

    public JLabel getAscensor() {
        return Ascensor;
    }

    public void setAscensor(JLabel Ascensor) {
        this.Ascensor = Ascensor;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mouseExited(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
