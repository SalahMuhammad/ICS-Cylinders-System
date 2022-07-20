package InsertOrUpdateTransactions_Frames;

import icscylinders.DatabaseHandler;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mymethods.MyMethods;

public class UpdateBigCylindersTransactions extends java.awt.Dialog
{

    public UpdateBigCylindersTransactions(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
    }

    int id;
    
    MyMethods mm = new MyMethods();
    DatabaseHandler handler = new DatabaseHandler();

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel3 = new javax.swing.JPanel();
        countTXT2 = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        submitBTN = new javax.swing.JButton();
        unitPriceTXT = new javax.swing.JTextField();
        noticeTXT = new javax.swing.JTextField();
        deleteBTN = new javax.swing.JButton();
        bigCylinders_jComboBox1 = new javax.swing.JComboBox();

        setTitle("Update Big Cylinders Transaction");
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });

        countTXT2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        countTXT2.setForeground(new java.awt.Color(153, 153, 153));
        countTXT2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        countTXT2.setText("Count");
        countTXT2.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                countTXT2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                countTXT2FocusLost(evt);
            }
        });
        countTXT2.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                countTXT2KeyPressed(evt);
            }
        });

        jDateChooser1.setDateFormatString("yyyy-MM-dd");
        jDateChooser1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                jDateChooser1KeyPressed(evt);
            }
        });

        submitBTN.setText("Submit");
        submitBTN.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                submitBTNActionPerformed(evt);
            }
        });

        unitPriceTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        unitPriceTXT.setForeground(new java.awt.Color(153, 153, 153));
        unitPriceTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        unitPriceTXT.setText("Unit Price");
        unitPriceTXT.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                unitPriceTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                unitPriceTXTFocusLost(evt);
            }
        });
        unitPriceTXT.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                unitPriceTXTKeyPressed(evt);
            }
        });

        noticeTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        noticeTXT.setForeground(new java.awt.Color(153, 153, 153));
        noticeTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        noticeTXT.setText("Notice");
        noticeTXT.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                noticeTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                noticeTXTFocusLost(evt);
            }
        });
        noticeTXT.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                noticeTXTKeyPressed(evt);
            }
        });

        deleteBTN.setText("Delete");
        deleteBTN.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                deleteBTNActionPerformed(evt);
            }
        });

        bigCylinders_jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "from", "to" }));
        bigCylinders_jComboBox1.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                bigCylinders_jComboBox1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countTXT2)
                    .addComponent(noticeTXT)
                    .addComponent(unitPriceTXT)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(submitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBTN)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(bigCylinders_jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(countTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unitPriceTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bigCylinders_jComboBox1)
                .addGap(2, 2, 2)
                .addComponent(noticeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBTN)
                    .addComponent(deleteBTN))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void countTXT2FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_countTXT2FocusGained
    {//GEN-HEADEREND:event_countTXT2FocusGained
        mm.ifFocusGainedAndLost(countTXT2, "Count");
    }//GEN-LAST:event_countTXT2FocusGained

    private void countTXT2FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_countTXT2FocusLost
    {//GEN-HEADEREND:event_countTXT2FocusLost
        mm.ifFocusGainedAndLost(countTXT2, "Count");
    }//GEN-LAST:event_countTXT2FocusLost

    private void countTXT2KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_countTXT2KeyPressed
    {//GEN-HEADEREND:event_countTXT2KeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        unitPriceTXT.requestFocus();
    }//GEN-LAST:event_countTXT2KeyPressed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jDateChooser1KeyPressed
    {//GEN-HEADEREND:event_jDateChooser1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        bigCylinders_jComboBox1.requestFocus();
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void submitBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_submitBTNActionPerformed
    {//GEN-HEADEREND:event_submitBTNActionPerformed
        if (countTXT2.getText().equals("Count"))
        {
            JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
            return;
        }
        
        if (unitPriceTXT.getText().equals("Unit Price"))
            unitPriceTXT.setText("0");

        handler.insertOrUpdate("update bigcylinders set date = '" +
            ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText() + "', count = " +
            countTXT2.getText() + ", from_or_to = '" +
            bigCylinders_jComboBox1.getSelectedItem().toString() + "', unitprice = " +
            unitPriceTXT.getText() + ", notice = '" +
            noticeTXT.getText() + "' where id = " + id,
            "Transaction Updated Successfully...");

        this.closeDialog(null);
    }//GEN-LAST:event_submitBTNActionPerformed

    private void unitPriceTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_unitPriceTXTFocusGained
    {//GEN-HEADEREND:event_unitPriceTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGained(unitPriceTXT, "Unit Price");
    }//GEN-LAST:event_unitPriceTXTFocusGained

    private void unitPriceTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_unitPriceTXTFocusLost
    {//GEN-HEADEREND:event_unitPriceTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusLost(unitPriceTXT, "Unit Price");
    }//GEN-LAST:event_unitPriceTXTFocusLost

    private void unitPriceTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_unitPriceTXTKeyPressed
    {//GEN-HEADEREND:event_unitPriceTXTKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        jDateChooser1.requestFocus();
    }//GEN-LAST:event_unitPriceTXTKeyPressed

    private void noticeTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_noticeTXTFocusGained
    {//GEN-HEADEREND:event_noticeTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGained(noticeTXT, "Notice");
    }//GEN-LAST:event_noticeTXTFocusGained

    private void noticeTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_noticeTXTFocusLost
    {//GEN-HEADEREND:event_noticeTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusLost(noticeTXT, "Notice");
    }//GEN-LAST:event_noticeTXTFocusLost

    private void noticeTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_noticeTXTKeyPressed
    {//GEN-HEADEREND:event_noticeTXTKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
        submitBTNActionPerformed(null);
    }//GEN-LAST:event_noticeTXTKeyPressed

    int response;
    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteBTNActionPerformed
    {//GEN-HEADEREND:event_deleteBTNActionPerformed
        response = JOptionPane.showConfirmDialog(this, "Are you sure that you wanna delete this transaction", "confirm", 2);

        if (response == JOptionPane.OK_OPTION)
        {
            handler.deletRecord("bigcylinders", id);

            this.closeDialog(null);
        }
    }//GEN-LAST:event_deleteBTNActionPerformed

    private void bigCylinders_jComboBox1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_bigCylinders_jComboBox1KeyPressed
    {//GEN-HEADEREND:event_bigCylinders_jComboBox1KeyPressed
        mm.componentRequestFocus(evt, noticeTXT);
    }//GEN-LAST:event_bigCylinders_jComboBox1KeyPressed

    public void inflateUI(int id, int count, int unitprice, Date date, String fromOrTo, String notice)
    {
        this.id = id;
        mm.focusGained(countTXT2, Integer.toString(count));
        mm.focusGained(unitPriceTXT, Integer.toString(unitprice));
        jDateChooser1.setDate(date);
        bigCylinders_jComboBox1.setSelectedItem(fromOrTo);
        mm.focusGained(noticeTXT, notice);
        
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
                UpdateBigCylindersTransactions dialog = new UpdateBigCylindersTransactions(new java.awt.Frame(), true);
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
    private javax.swing.JComboBox bigCylinders_jComboBox1;
    private javax.swing.JTextField countTXT2;
    private javax.swing.JButton deleteBTN;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JTextField noticeTXT;
    private javax.swing.JButton submitBTN;
    private javax.swing.JTextField unitPriceTXT;
    // End of variables declaration//GEN-END:variables
}
