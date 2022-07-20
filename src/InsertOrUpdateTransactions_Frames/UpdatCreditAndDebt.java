package InsertOrUpdateTransactions_Frames;

import icscylinders.DatabaseHandler;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mymethods.MyMethods;

public class UpdatCreditAndDebt extends java.awt.Dialog
{

    public UpdatCreditAndDebt(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }

    /**
     * this variable what will refer to frame type either  credit or debt
     */
    String creditOrDebt;
    
    int id;
    
    MyMethods mm = new MyMethods();
    DatabaseHandler handler = new DatabaseHandler();
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        submitBTN = new javax.swing.JButton();
        deleteBTN = new javax.swing.JButton();
        valueTXT = new javax.swing.JTextField();
        descriptionTXT = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();

        setResizable(false);
        setTitle("Update Credit");
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        submitBTN.setText("Submit");
        submitBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                submitBTNActionPerformed(evt);
            }
        });

        deleteBTN.setText("Delete");
        deleteBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteBTNActionPerformed(evt);
            }
        });

        valueTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        valueTXT.setForeground(new java.awt.Color(153, 153, 153));
        valueTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valueTXT.setText("Count");
        valueTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                valueTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                valueTXTFocusLost(evt);
            }
        });
        valueTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                valueTXTKeyPressed(evt);
            }
        });

        descriptionTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        descriptionTXT.setForeground(new java.awt.Color(153, 153, 153));
        descriptionTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        descriptionTXT.setText("Notice");
        descriptionTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                descriptionTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                descriptionTXTFocusLost(evt);
            }
        });
        descriptionTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                descriptionTXTKeyPressed(evt);
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
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(submitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(deleteBTN))
                            .addComponent(valueTXT)
                            .addComponent(descriptionTXT))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(valueTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(3, 3, 3)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void valueTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_valueTXTFocusGained
    {//GEN-HEADEREND:event_valueTXTFocusGained
        mm.ifFocusGainedAndLost(valueTXT, creditOrDebt);
    }//GEN-LAST:event_valueTXTFocusGained

    private void valueTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_valueTXTFocusLost
    {//GEN-HEADEREND:event_valueTXTFocusLost
        mm.ifFocusGainedAndLost(valueTXT, creditOrDebt);
    }//GEN-LAST:event_valueTXTFocusLost

    private void valueTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_valueTXTKeyPressed
    {//GEN-HEADEREND:event_valueTXTKeyPressed
        mm.componentRequestFocus(evt, jDateChooser1);
    }//GEN-LAST:event_valueTXTKeyPressed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            submitBTNActionPerformed(null);
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void descriptionTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_descriptionTXTFocusGained
    {//GEN-HEADEREND:event_descriptionTXTFocusGained
        mm.ifFocusGained(descriptionTXT, "Description");
    }//GEN-LAST:event_descriptionTXTFocusGained

    private void descriptionTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_descriptionTXTFocusLost
    {//GEN-HEADEREND:event_descriptionTXTFocusLost
        mm.ifFocusLost(descriptionTXT, "Description");
    }//GEN-LAST:event_descriptionTXTFocusLost

    private void descriptionTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_descriptionTXTKeyPressed
    {//GEN-HEADEREND:event_descriptionTXTKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            submitBTNActionPerformed(null);
    }//GEN-LAST:event_descriptionTXTKeyPressed

    String quary;
    String setQuary()
    {
        quary = "update " + creditOrDebt + " set date = '" +
            ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText() + "', " + creditOrDebt + " = " +
            valueTXT.getText() + ", description = '" +
            descriptionTXT.getText() + "' where id = " + id;
        
        return quary;
    }
    
    private void submitBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_submitBTNActionPerformed
    {//GEN-HEADEREND:event_submitBTNActionPerformed
        if (creditOrDebt.equals("Credit"))
        {
            if (valueTXT.getText().equals("Credit"))
            {
                JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
                return;
            }

            handler.insertOrUpdate(setQuary(), "Transaction Updated Successfully...");

            this.closeDialog(null);
        }   
        else if (creditOrDebt.equals("Debt"))
        {
            if (valueTXT.getText().equals("Debt"))
            {
                JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
                return;
            }

            handler.insertOrUpdate(setQuary(), "Transaction Updated Successfully...");

            this.closeDialog(null);
        }
    }//GEN-LAST:event_submitBTNActionPerformed

    int response;
    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteBTNActionPerformed
    {//GEN-HEADEREND:event_deleteBTNActionPerformed
        response = JOptionPane.showConfirmDialog(this, "Are you sure that you wanna delete this transaction", "confirm", 2);
        
        if (response == JOptionPane.OK_OPTION)
        {
            handler.deletRecord(creditOrDebt, id);

            this.closeDialog(null);
        }
    }//GEN-LAST:event_deleteBTNActionPerformed

    public void inflateUI(int id, int value, Date date, String description, String creditOrDebt)
    {
        this.id = id;
        
        mm.focusGained(valueTXT, Integer.toString(value));
        jDateChooser1.setDate(date);
        mm.focusGained(descriptionTXT, description);
        
        this.creditOrDebt = creditOrDebt;
        
        this.setTitle("Update " + creditOrDebt + " Transaction");
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }
    
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                UpdatCreditAndDebt dialog = new UpdatCreditAndDebt(new java.awt.Frame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter()
                {
                    public void windowClosing(java.awt.event.WindowEvent e)
                    {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton deleteBTN;
    private javax.swing.JTextField descriptionTXT;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton submitBTN;
    private javax.swing.JTextField valueTXT;
    // End of variables declaration//GEN-END:variables
}
