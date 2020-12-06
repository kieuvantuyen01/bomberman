/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package uet.oop.bomberman.gui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import uet.oop.bomberman.BombermanGame;

/**
 *
 * @author Admin
 */
public class MenuGameJframe extends javax.swing.JFrame {
    public static boolean isApplicationRunFirstTime = false;
    /**
     * Creates new form MenuGameJframe
     */
    public MenuGameJframe() {
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

        jPanel1 = new javax.swing.JPanel();
        playButton = new javax.swing.JButton();
        optionButton = new javax.swing.JButton();
        highScoreButton = new javax.swing.JButton();
        exitButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(911, 580));
        getContentPane().setLayout(new java.awt.CardLayout());

        jPanel1.setLayout(null);

        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/Play.png"))); // NOI18N
        playButton.setPreferredSize(new java.awt.Dimension(240, 60));
        playButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                playButtonMouseMoved(evt);
            }
        });
        playButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                playButtonMouseExited(evt);
            }
        });
        playButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                playButtonActionPerformed(evt);
            }
        });
        jPanel1.add(playButton);
        playButton.setBounds(370, 250, 140, 50);

        optionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/Option.png"))); // NOI18N
        optionButton.setPreferredSize(new java.awt.Dimension(240, 60));
        optionButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                optionButtonMouseMoved(evt);
            }
        });
        optionButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                optionButtonMouseExited(evt);
            }
        });
        optionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                optionButtonActionPerformed(evt);
            }
        });
        jPanel1.add(optionButton);
        optionButton.setBounds(370, 320, 140, 50);

        highScoreButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/HightScore.png"))); // NOI18N
        highScoreButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                highScoreButtonMouseMoved(evt);
            }
        });
        highScoreButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                highScoreButtonMouseExited(evt);
            }
        });
        highScoreButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                highScoreButtonActionPerformed(evt);
            }
        });
        jPanel1.add(highScoreButton);
        highScoreButton.setBounds(370, 390, 140, 50);

        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/Exit.png"))); // NOI18N
        exitButton.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseMoved(java.awt.event.MouseEvent evt) {
                exitButtonMouseMoved(evt);
            }
        });
        exitButton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseExited(java.awt.event.MouseEvent evt) {
                exitButtonMouseExited(evt);
            }
        });
        exitButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exitButtonActionPerformed(evt);
            }
        });
        jPanel1.add(exitButton);
        exitButton.setBounds(370, 460, 140, 50);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/backGroundMenu.png"))); // NOI18N
        jPanel1.add(jLabel1);
        jLabel1.setBounds(0, -10, 910, 590);

        getContentPane().add(jPanel1, "card2");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void highScoreButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_highScoreButtonActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_highScoreButtonActionPerformed

    private void optionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_optionButtonActionPerformed
        new optionMenuJframe().setVisible(true);
        this.dispose();
    }//GEN-LAST:event_optionButtonActionPerformed

    private void playButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_playButtonActionPerformed
        if(!isApplicationRunFirstTime) {
            this.dispose();
            Application.launch(BombermanGame.class);
            isApplicationRunFirstTime = true;
        }
        else if(isApplicationRunFirstTime){
            System.out.println(isApplicationRunFirstTime);
            Platform.runLater(() -> {
                Stage stage = new Stage();
                stage.setScene(new BombermanGame().createScene());
                stage.show();
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            });
            
        }
    }//GEN-LAST:event_playButtonActionPerformed

    private void playButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseExited
        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/Play.png")));
    }//GEN-LAST:event_playButtonMouseExited

    private void playButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_playButtonMouseMoved
        playButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/Play2.png")));
    }//GEN-LAST:event_playButtonMouseMoved

    private void optionButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionButtonMouseExited
         optionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/Option.png")));
    }//GEN-LAST:event_optionButtonMouseExited

    private void optionButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_optionButtonMouseMoved
         optionButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/Option2.png")));
    }//GEN-LAST:event_optionButtonMouseMoved

    private void highScoreButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_highScoreButtonMouseMoved
        highScoreButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/HightScore2.png")));
    }//GEN-LAST:event_highScoreButtonMouseMoved

    private void highScoreButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_highScoreButtonMouseExited
        highScoreButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/HightScore.png")));
    }//GEN-LAST:event_highScoreButtonMouseExited

    private void exitButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exitButtonActionPerformed
        System.exit(0);
    }//GEN-LAST:event_exitButtonActionPerformed

    private void exitButtonMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseExited
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/Exit.png")));
    }//GEN-LAST:event_exitButtonMouseExited

    private void exitButtonMouseMoved(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitButtonMouseMoved
        exitButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/textures/Exit2.png")));
    }//GEN-LAST:event_exitButtonMouseMoved

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
            java.util.logging.Logger.getLogger(MenuGameJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MenuGameJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MenuGameJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MenuGameJframe.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MenuGameJframe().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton exitButton;
    private javax.swing.JButton highScoreButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton optionButton;
    private javax.swing.JButton playButton;
    // End of variables declaration//GEN-END:variables
}
