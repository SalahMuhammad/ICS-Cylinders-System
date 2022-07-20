package InsertOrUpdateTransactions_Frames;

import icscylinders.DatabaseHandler;
import mymethods.MyMethods;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class InsertDisabled extends java.awt.Dialog {

    public InsertDisabled(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        
        mm.initDateEditor(jDateChooser1);
    }
    
    private int value;
    
    DatabaseHandler handler = new DatabaseHandler();
    MyMethods mm = new MyMethods();

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        disabledTXT = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setResizable(false);
        setTitle("Insert Damaged Cylinders");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        disabledTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        disabledTXT.setForeground(new java.awt.Color(153, 153, 153));
        disabledTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        disabledTXT.setText("Damaged");
        disabledTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                disabledTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                disabledTXTFocusLost(evt);
            }
        });
        disabledTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                disabledTXTKeyPressed(evt);
            }
        });

        jButton1.setText("Submit");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jButton1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton1KeyPressed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jDateChooser1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton1))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(disabledTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 217, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(disabledTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
        
        emptyTXT();
    }//GEN-LAST:event_closeDialog

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        insertOrUpdateMethod();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void disabledTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_disabledTXTKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            jDateChooser1.requestFocus();
    }//GEN-LAST:event_disabledTXTKeyPressed

    private void jButton1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            insertOrUpdateMethod();
    }//GEN-LAST:event_jButton1KeyPressed

    void insertOrUpdateMethod()
    {
        if (disabledTXT.getText().equals("Damaged") || disabledTXT.getText().equals("0"))
        {
            JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
            return;
        }
        
        handler.insertOrUpdate("insert into damaged (count, date) values (" +
                disabledTXT.getText() + ", '" +
                ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText() + "')",
                "Done...");

        this.value = Integer.parseInt(disabledTXT.getText());
        
        this.closeDialog(null);
    }
    
    void emptyTXT()
    {
        disabledTXT.requestFocus();
        
        mm.initDateEditor(jDateChooser1);
        
        disabledTXT.setText("Damaged");
        disabledTXT.setForeground(new Color(153, 153, 153));
    }
    
    private void disabledTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_disabledTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGainedAndLost(disabledTXT, "Damaged");
    }//GEN-LAST:event_disabledTXTFocusGained

    private void disabledTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_disabledTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusGainedAndLost(disabledTXT, "Damaged");
    }//GEN-LAST:event_disabledTXTFocusLost

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            insertOrUpdateMethod();
    }//GEN-LAST:event_jDateChooser1KeyPressed

    public int getValue() {
        return value;
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                InsertDisabled dialog = new InsertDisabled(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField disabledTXT;
    private javax.swing.JButton jButton1;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
