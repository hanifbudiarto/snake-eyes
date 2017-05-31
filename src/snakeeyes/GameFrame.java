/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package snakeeyes;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.SecureRandom;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author Hanif
 */
public class GameFrame extends javax.swing.JFrame {

    private final SecureRandom randomNumbers = new SecureRandom();
    private enum Status { CONTINUE, LOST, WON };
    private static final int SNAKE_EYES = 2;
    private static final int TREY = 3;
    private static final int SEVEN = 7;
    private static final int YO_LEVEN = 11;
    private static final int BOX_CARS = 12;
    
    private JButton rollBtn, restartBtn;
    private JTextField fieldDice1, fieldDice2, fieldSum, fieldPoint;
    private JLabel labelStatus;
    
    private int dice1, dice2, myPoint;
    private Status gameStatus;
        
    private boolean isFirstRoll = true;
    
    /**
     * Creates new form GameFrame
     */
    public GameFrame() {
        initComponents();        
        initLayout();
    }

    private void initLayout() {
        GridBagConstraints gbc = new GridBagConstraints();
        
        JPanel rootPanel = new JPanel();
        rootPanel.setLayout(new GridBagLayout());
        
        gbc.gridx = 0;
        gbc.gridy = 0;
        rootPanel.add(new JLabel("Dice 1"), gbc);
        
        gbc.gridx = 1;
        fieldDice1 = new JTextField(10);
        rootPanel.add(fieldDice1, gbc);
        
        ++gbc.gridy;
        gbc.gridx = 0;
        rootPanel.add(new JLabel("Dice 2"), gbc);
        
        gbc.gridx = 1;
        fieldDice2 = new JTextField(10);
        rootPanel.add(fieldDice2, gbc);
        
        ++gbc.gridy;
        gbc.gridx = 0;
        rootPanel.add(new JLabel("Sum"), gbc);
        
        gbc.gridx = 1;
        fieldSum = new JTextField(10);
        rootPanel.add(fieldSum, gbc);
        
        ++gbc.gridy;
        gbc.gridx = 0;
        rootPanel.add(new JLabel("Point"), gbc);
        
        gbc.gridx = 1;
        fieldPoint = new JTextField(10);
        rootPanel.add(fieldPoint, gbc);
        
        ++gbc.gridy;
        gbc.gridx = 0;
        
        rollBtn = new JButton("Roll");
        rollBtn.addActionListener(onRollBtnClick);
        rootPanel.add(rollBtn, gbc);
        
        gbc.gridx = 1;
        restartBtn = new JButton("Restart");
        restartBtn.addActionListener(onRestartBtnClick);        
        rootPanel.add(restartBtn, gbc);
        
        ++gbc.gridy;
        gbc.gridx=1;
        labelStatus = new JLabel();
        rootPanel.add(labelStatus, gbc);
        
        restartBtn.setEnabled(false);
        this.setContentPane(rootPanel);
               
    }
    
    private final ActionListener onRestartBtnClick = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e) {
            isFirstRoll = true;
            gameStatus = null;
            dice1 = 0;
            dice2 = 0;
            myPoint = 0;
            
            fieldDice1.setText("");
            fieldDice2.setText("");
            fieldPoint.setText("");
            fieldSum.setText("");
            labelStatus.setText("");
            
            rollBtn.setEnabled(true);
            restartBtn.setEnabled(false);
        }
    };
    
    private final ActionListener onRollBtnClick = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            play();
        }
    };
        
    private void play() {                
        int sumOfDice = rollDice();
        if (isFirstRoll) {
            isFirstRoll = false;
            calculateFirstRoll(sumOfDice);
        }
        else {
            calculateGameState(sumOfDice);
        }
        displayRoll(sumOfDice);    
    }
    
    
    private void calculateGameState(int sumOfDice){
        if (sumOfDice == myPoint) {
            gameStatus = Status.WON;
        }
        else {
            if (sumOfDice == SEVEN) {
                gameStatus = Status.LOST;
            }
        }
    }
    
    private void displayRoll(int sumOfDice) {
        fieldDice1.setText(dice1+"");
        fieldDice2.setText(dice2+"");
        fieldSum.setText(sumOfDice+"");
        
        if (myPoint != 0) {
            fieldPoint.setText(myPoint+"");
        }
        
        if (gameStatus == Status.WON || gameStatus == Status.LOST) {
            rollBtn.setEnabled(false);
            restartBtn.setEnabled(true);
            //JOptionPane.showMessageDialog(this, "You "+gameStatus.name());
            labelStatus.setText("You "+gameStatus.name());
        }
    }
    
    private void calculateFirstRoll(int sumOfDice) { 
        switch(sumOfDice) {
            case SEVEN:
            case YO_LEVEN:
                gameStatus = Status.WON;
                break;
            case SNAKE_EYES:
            case TREY:
            case BOX_CARS:
                gameStatus = Status.LOST;
                break;
            default:
                myPoint = sumOfDice;
                gameStatus = Status.CONTINUE;
                break;
        }
    }
    
    private int rollDice() {
        dice1 = 1 + randomNumbers.nextInt(6);
        dice2 = 1 + randomNumbers.nextInt(6);
        return dice1 + dice2;
    }
    
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

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
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GameFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GameFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
