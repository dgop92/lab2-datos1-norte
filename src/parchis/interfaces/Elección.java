/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package parchis.interfaces;

import inevaup.dialogs.InfoDialog;
import inevaup.dialogs.InfoDialog.TypeInfoDialog;
import java.awt.Graphics;
import java.awt.Image;
import java.util.ArrayList;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import parchis.ParchisSketch;
import parchis.game.players.PlayerColor;

/**
 *
 * @author DiegoLineroPinto
 */
public class Elección extends javax.swing.JFrame {

    FondoPanel fondo = new FondoPanel();

    public ArrayList<String> Turnos;

    public Elección() {
        this.setContentPane(fondo);
        Turnos = new ArrayList<>();

        initComponents();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        Imagenturno1 = new javax.swing.JLabel();
        Imagenturno3 = new javax.swing.JLabel();
        Imagenturno2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });
        getContentPane().setLayout(null);

        Imagenturno1.setPreferredSize(null);
        getContentPane().add(Imagenturno1);
        Imagenturno1.setBounds(70, 180, 173, 167);

        Imagenturno3.setPreferredSize(null);
        getContentPane().add(Imagenturno3);
        Imagenturno3.setBounds(550, 180, 173, 167);

        Imagenturno2.setPreferredSize(null);
        getContentPane().add(Imagenturno2);
        Imagenturno2.setBounds(320, 180, 173, 167);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
        int x = evt.getX();
        int y = evt.getY();
        boolean insideX = x > 86 && x < 335;
        boolean insideY = y > 510 && y < 549;

        if (insideX && insideY) {
            shuffleTurns();
        }

        insideX = x > 492 && x < 736;
        insideY = y > 510 && y < 551;
        if (insideX && insideY) {
            if (Turnos.isEmpty()) {
                InfoDialog errDialog = new InfoDialog(
                    this,
                    "Error",
                    "Por favor, debe elegir los turnos antes de empezar",
                    TypeInfoDialog.ERROR_DIALOG
                );
                errDialog.setVisible(true);
            } else {
                ArrayList<PlayerColor> orders = new ArrayList<>();
                for (String Turno : Turnos) {
                    switch (Turno) {
                        case "Rojo":
                            orders.add(PlayerColor.Rojo);
                            break;

                        case "Azul":
                            orders.add(PlayerColor.Azul);
                            break;

                        case "Verde":
                            orders.add(PlayerColor.Verde);
                            break;

                    }
                }
                InfoDialog dialog = new InfoDialog(
                    this,
                    "Como Jugar",
                    "Todas las acciones son controladas automaticamente "
                        + "solo debe hacer click sobre el dado para jugar",
                    TypeInfoDialog.INFO_DIALOG
                );
                dialog.setVisible(true);
                ParchisSketch parchisSketch = new ParchisSketch();
                parchisSketch.initGameControl(orders);
                parchisSketch.run();
                this.dispose();
            }
        }
    }//GEN-LAST:event_formMouseClicked

    private void shuffleTurns() {
        Turnos.clear();
        int cantidad = 3, rango = 3;
        int arreglo[] = new int[cantidad];

        for (int i = 0; i < cantidad; i++) {
            arreglo[i] = (int) (Math.random() * rango);
            for (int j = 0; j < i; j++) {
                if (arreglo[i] == arreglo[j]) {
                    i--;
                }
            }
        }

        for (int i = 0; i < cantidad; i++) {

            switch (arreglo[i]) {

                case 0:
                    Turnos.add("Rojo");
                    break;

                case 1:
                    Turnos.add("Azul");
                    break;

                case 2:
                    Turnos.add("Verde");
                    break;

            }

        }

        int cont = 0;
        for (String x : Turnos) {

            if (cont == 0) {

                if (x.equals("Rojo")) {

                    Imagenturno1.setIcon(new ImageIcon(getClass().getResource("/Imagen/Ficha-Roja.png")));
                } else if (x.equals("Azul")) {

                    Imagenturno1.setIcon(new ImageIcon(getClass().getResource("/Imagen/Ficha-Azul.png")));
                } else if (x.equals("Verde")) {

                    Imagenturno1.setIcon(new ImageIcon(getClass().getResource("/Imagen/Ficha-Verde.png")));
                }

            } else if (cont == 1) {

                if (x.equals("Rojo")) {

                    Imagenturno2.setIcon(new ImageIcon(getClass().getResource("/Imagen/Ficha-Roja.png")));
                } else if (x.equals("Azul")) {

                    Imagenturno2.setIcon(new ImageIcon(getClass().getResource("/Imagen/Ficha-Azul.png")));
                } else if (x.equals("Verde")) {

                    Imagenturno2.setIcon(new ImageIcon(getClass().getResource("/Imagen/Ficha-Verde.png")));
                }

            } else if (cont == 2) {

                if (x.equals("Rojo")) {

                    Imagenturno3.setIcon(new ImageIcon(getClass().getResource("/Imagen/Ficha-Roja.png")));
                } else if (x.equals("Azul")) {

                    Imagenturno3.setIcon(new ImageIcon(getClass().getResource("/Imagen/Ficha-Azul.png")));
                } else if (x.equals("Verde")) {

                    Imagenturno3.setIcon(new ImageIcon(getClass().getResource("/Imagen/Ficha-Verde.png")));
                }

            }
            cont = cont + 1;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Elección.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Elección.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Elección.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Elección.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Elección().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Imagenturno1;
    private javax.swing.JLabel Imagenturno2;
    private javax.swing.JLabel Imagenturno3;
    // End of variables declaration//GEN-END:variables

    public class FondoPanel extends JPanel {

        private Image imagen;

        @Override
        public void paint(Graphics g) {
            imagen = new ImageIcon(getClass().getResource("/Imagen/menu-principal-3.png")).getImage();

            g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);

            setOpaque(false);

            super.paint(g);
        }
    }

}
