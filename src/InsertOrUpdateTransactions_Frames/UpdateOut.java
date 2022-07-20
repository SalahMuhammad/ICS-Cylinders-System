package InsertOrUpdateTransactions_Frames;

import com.toedter.calendar.JDateChooser;
import icscylinders.DatabaseHandler;
import mymethods.MyMethods;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class UpdateOut extends java.awt.Dialog {

    public UpdateOut(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        
        mm.initDateEditor(jDateChooser1);
    }
    
    int id;
    
    MyMethods mm = new MyMethods();
    DatabaseHandler handler = new DatabaseHandler();

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        countTXT = new javax.swing.JTextField();
        rNumberTXT = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        submitBTN = new javax.swing.JButton();
        unitPriceTXT = new javax.swing.JTextField();
        noticeTXT = new javax.swing.JTextField();
        deleteBTN = new javax.swing.JButton();

        setResizable(false);
        setTitle("Update Cylinders Out Transaction");
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });

        countTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        countTXT.setForeground(new java.awt.Color(153, 153, 153));
        countTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        countTXT.setText("Count");
        countTXT.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                countTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                countTXTFocusLost(evt);
            }
        });
        countTXT.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                countTXTKeyPressed(evt);
            }
        });

        rNumberTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        rNumberTXT.setForeground(new java.awt.Color(153, 153, 153));
        rNumberTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        rNumberTXT.setText("Receipt Number");
        rNumberTXT.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                rNumberTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                rNumberTXTFocusLost(evt);
            }
        });
        rNumberTXT.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                rNumberTXTKeyPressed(evt);
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
        submitBTN.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                submitBTNKeyPressed(evt);
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

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(rNumberTXT)
                    .addComponent(noticeTXT)
                    .addComponent(unitPriceTXT)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(submitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBTN)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(countTXT))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(countTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(unitPriceTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(9, 9, 9)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(rNumberTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noticeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBTN)
                    .addComponent(deleteBTN))
                .addContainerGap(9, Short.MAX_VALUE))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {deleteBTN, submitBTN});

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

    private void countTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_countTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGained(countTXT, "Count");
    }//GEN-LAST:event_countTXTFocusGained

    private void countTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_countTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusLost(countTXT, "Count");
    }//GEN-LAST:event_countTXTFocusLost

    private void rNumberTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rNumberTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGained(rNumberTXT, "Receipt Number");
    }//GEN-LAST:event_rNumberTXTFocusGained

    private void rNumberTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_rNumberTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusLost(rNumberTXT, "Receipt Number");
    }//GEN-LAST:event_rNumberTXTFocusLost

    private void submitBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBTNActionPerformed
        // TODO add your handling code here:
        insertOrUpdateMethod();
    }//GEN-LAST:event_submitBTNActionPerformed

    private void countTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_countTXTKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            unitPriceTXT.requestFocus();
    }//GEN-LAST:event_countTXTKeyPressed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            rNumberTXT.requestFocus();
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void rNumberTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_rNumberTXTKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            noticeTXT.requestFocus();
    }//GEN-LAST:event_rNumberTXTKeyPressed

    private void submitBTNKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_submitBTNKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            insertOrUpdateMethod();
    }//GEN-LAST:event_submitBTNKeyPressed

    private void unitPriceTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_unitPriceTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGained(unitPriceTXT, "Unit Price");
    }//GEN-LAST:event_unitPriceTXTFocusGained

    private void unitPriceTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_unitPriceTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusLost(unitPriceTXT, "Unit Price");
    }//GEN-LAST:event_unitPriceTXTFocusLost

    private void unitPriceTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_unitPriceTXTKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            jDateChooser1.requestFocus();
    }//GEN-LAST:event_unitPriceTXTKeyPressed

    private void noticeTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_noticeTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGained(noticeTXT, "Notice");
    }//GEN-LAST:event_noticeTXTFocusGained

    private void noticeTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_noticeTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusLost(noticeTXT, "Notice");
    }//GEN-LAST:event_noticeTXTFocusLost

    private void noticeTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_noticeTXTKeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            submitBTN.requestFocus();
    }//GEN-LAST:event_noticeTXTKeyPressed

    int response;
    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteBTNActionPerformed
    {//GEN-HEADEREND:event_deleteBTNActionPerformed
        response = JOptionPane.showConfirmDialog(this, "Are you sure that you wanna delete this transaction", "confirm", 2);
        
        if (response == JOptionPane.OK_OPTION)
        {
            handler.deletRecord("out_table", id);

            this.closeDialog(null);
        }
    }//GEN-LAST:event_deleteBTNActionPerformed
    
    void insertOrUpdateMethod()
    {   
        if (countTXT.getText().equals("Count"))
        {
            JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
            return;
        }

        handler.insertOrUpdate("update out_table set date = '" +
                ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText() + "', count = " +
                countTXT.getText() + ", receipt_number = '" + 
                rNumberTXT.getText() + "', unit_price = " +
                unitPriceTXT.getText() + ", notice = '" +
                noticeTXT.getText() + "' where id = " + id,
                "Transaction Updated Successfully...");

        this.closeDialog(null);
    }
    
    public void inflateUI(int id, int count, int unitprice, Date date, String receiptNumber, String notice)
    {
        this.id = id;
        mm.focusGained(countTXT, Integer.toString(count));
        mm.focusGained(unitPriceTXT, Integer.toString(unitprice));
        jDateChooser1.setDate(date);
        mm.focusGained(rNumberTXT, receiptNumber);
        mm.focusGained(noticeTXT, notice);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateOut dialog = new UpdateOut(new java.awt.Frame(), true);
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
    private javax.swing.JTextField countTXT;
    private javax.swing.JButton deleteBTN;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField noticeTXT;
    private javax.swing.JTextField rNumberTXT;
    private javax.swing.JButton submitBTN;
    private javax.swing.JTextField unitPriceTXT;
    // End of variables declaration//GEN-END:variables
}
