package InsertOrUpdateTransactions_Frames;

import icscylinders.DatabaseHandler;
import mymethods.MyMethods;
import java.awt.Color;
import java.awt.event.KeyEvent;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateCustomer extends java.awt.Dialog {

    public UpdateCustomer(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }

    int id;
    
    MyMethods mm = new MyMethods();
    DatabaseHandler handler = new DatabaseHandler();
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        nameTXT = new javax.swing.JTextField();
        submitBTN = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        deleteBTN = new javax.swing.JButton();
        cylinderUnitpriceTXT = new javax.swing.JTextField();

        setResizable(false);
        setTitle("Update Customer");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        nameTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        nameTXT.setForeground(new java.awt.Color(153, 153, 153));
        nameTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nameTXT.setText("Name");
        nameTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                nameTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                nameTXTFocusLost(evt);
            }
        });
        nameTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                nameTXTKeyPressed(evt);
            }
        });

        submitBTN.setText("Submit");
        submitBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBTNActionPerformed(evt);
            }
        });
        submitBTN.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                submitBTNKeyPressed(evt);
            }
        });

        jComboBox1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "sales", "purchases" }));
        jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jComboBox1KeyPressed(evt);
            }
        });

        deleteBTN.setText("Delete");
        deleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBTNActionPerformed(evt);
            }
        });

        cylinderUnitpriceTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        cylinderUnitpriceTXT.setForeground(new java.awt.Color(153, 153, 153));
        cylinderUnitpriceTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cylinderUnitpriceTXT.setText("Cylinder Unitprice");
        cylinderUnitpriceTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                cylinderUnitpriceTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                cylinderUnitpriceTXTFocusLost(evt);
            }
        });
        cylinderUnitpriceTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cylinderUnitpriceTXTKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameTXT)
                    .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(submitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBTN)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(cylinderUnitpriceTXT))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cylinderUnitpriceTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBTN)
                    .addComponent(deleteBTN))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
    }//GEN-LAST:event_closeDialog

    private void nameTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameTXTKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            jComboBox1.requestFocus();
    }//GEN-LAST:event_nameTXTKeyPressed

    private void submitBTNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_submitBTNKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            insertOrUpdateMethod();
    }//GEN-LAST:event_submitBTNKeyPressed

    private void nameTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGained(nameTXT, "Name");
    }//GEN-LAST:event_nameTXTFocusGained

    private void nameTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_nameTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusLost(nameTXT, "Name");
    }//GEN-LAST:event_nameTXTFocusLost

    private void submitBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBTNActionPerformed
        insertOrUpdateMethod();
    }//GEN-LAST:event_submitBTNActionPerformed

    private void jComboBox1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jComboBox1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            cylinderUnitpriceTXT.requestFocus();
    }//GEN-LAST:event_jComboBox1KeyPressed

    int response;
    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteBTNActionPerformed
    {//GEN-HEADEREND:event_deleteBTNActionPerformed
        response = JOptionPane.showConfirmDialog(this, "Are you sure that you wanna delete this customer", "confirm", 2);
        
        if (response == JOptionPane.OK_OPTION)
        {
            handler.deletRecord("customers", id);

            this.closeDialog(null);
        }
    }//GEN-LAST:event_deleteBTNActionPerformed

    private void cylinderUnitpriceTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cylinderUnitpriceTXTFocusGained
        mm.ifFocusGainedAndLost(cylinderUnitpriceTXT, "Cylinder Unitprice");
    }//GEN-LAST:event_cylinderUnitpriceTXTFocusGained

    private void cylinderUnitpriceTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_cylinderUnitpriceTXTFocusLost
        mm.ifFocusGainedAndLost(cylinderUnitpriceTXT, "Cylinder Unitprice");
    }//GEN-LAST:event_cylinderUnitpriceTXTFocusLost

    private void cylinderUnitpriceTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_cylinderUnitpriceTXTKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            insertOrUpdateMethod();
    }//GEN-LAST:event_cylinderUnitpriceTXTKeyPressed

    void insertOrUpdateMethod()
    {
        if(nameTXT.getText().equals("Name"))
        {
            JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
            return;
        }
        
        handler.insertOrUpdate("update customers set name = '" +
                nameTXT.getText() + "', type = '" +
                jComboBox1.getSelectedItem().toString() + "', " +
                "cylinder_unitprice = " + cylinderUnitpriceTXT.getText() + " where id = " +
                id,
                "Customer Updated Successfully...");

        this.closeDialog(null);
    }
    
    public void inflateUI(int id, String name, String type, int cylinderUnitprice)
    {
        this.id = id;
        
        mm.focusGained(nameTXT, name);
        
        jComboBox1.setSelectedItem(type);
        
        mm.focusGained(cylinderUnitpriceTXT, Integer.toString(cylinderUnitprice));
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateCustomer dialog = new UpdateCustomer(new java.awt.Frame(), true);
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
    private javax.swing.JTextField cylinderUnitpriceTXT;
    private javax.swing.JButton deleteBTN;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField nameTXT;
    private javax.swing.JButton submitBTN;
    // End of variables declaration//GEN-END:variables

}
