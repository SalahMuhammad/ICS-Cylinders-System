package InsertOrUpdateTransactions_Frames;

import icscylinders.DatabaseHandler;
import java.awt.event.KeyEvent;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import mymethods.MyMethods;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class UpdateAccessories extends java.awt.Dialog
{

    public UpdateAccessories(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        
        AutoCompleteDecorator.decorate(accessoryTypeCB);
        
        mm.initDateEditor(jDateChooser1);
    }

    int id;
    
    DatabaseHandler handler = new DatabaseHandler();
    MyMethods mm = new MyMethods();
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        countTXT = new javax.swing.JTextField();
        priceTXT = new javax.swing.JTextField();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        accessoryTypeCB = new javax.swing.JComboBox();
        deleteBTN = new javax.swing.JButton();
        submitBTN = new javax.swing.JButton();

        setResizable(false);
        setTitle("Update Accessory Transaction");
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

        priceTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        priceTXT.setForeground(new java.awt.Color(153, 153, 153));
        priceTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        priceTXT.setText("Price");
        priceTXT.addFocusListener(new java.awt.event.FocusAdapter()
        {
            public void focusGained(java.awt.event.FocusEvent evt)
            {
                priceTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt)
            {
                priceTXTFocusLost(evt);
            }
        });
        priceTXT.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                priceTXTKeyPressed(evt);
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

        accessoryTypeCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        accessoryTypeCB.addKeyListener(new java.awt.event.KeyAdapter()
        {
            public void keyPressed(java.awt.event.KeyEvent evt)
            {
                accessoryTypeCBKeyPressed(evt);
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

        submitBTN.setText("Submit");
        submitBTN.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                submitBTNActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(submitBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 176, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBTN))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(priceTXT, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(countTXT, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 242, Short.MAX_VALUE))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(accessoryTypeCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(accessoryTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(countTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(priceTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(deleteBTN)
                    .addComponent(submitBTN))
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

    private void countTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_countTXTFocusGained
    {//GEN-HEADEREND:event_countTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGainedAndLost(countTXT, "Count");
    }//GEN-LAST:event_countTXTFocusGained

    private void countTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_countTXTFocusLost
    {//GEN-HEADEREND:event_countTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusGainedAndLost(countTXT, "Count");
    }//GEN-LAST:event_countTXTFocusLost

    private void countTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_countTXTKeyPressed
    {//GEN-HEADEREND:event_countTXTKeyPressed
        mm.componentRequestFocus(evt, priceTXT);
    }//GEN-LAST:event_countTXTKeyPressed

    private void priceTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_priceTXTFocusGained
    {//GEN-HEADEREND:event_priceTXTFocusGained
        mm.ifFocusGainedAndLost(priceTXT, "Price");
    }//GEN-LAST:event_priceTXTFocusGained

    private void priceTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_priceTXTFocusLost
    {//GEN-HEADEREND:event_priceTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusGainedAndLost(priceTXT, "Price");
    }//GEN-LAST:event_priceTXTFocusLost

    private void priceTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_priceTXTKeyPressed
    {//GEN-HEADEREND:event_priceTXTKeyPressed
        mm.componentRequestFocus(evt, jDateChooser1);
    }//GEN-LAST:event_priceTXTKeyPressed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_jDateChooser1KeyPressed
    {//GEN-HEADEREND:event_jDateChooser1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            submitBTNActionPerformed(null);
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void accessoryTypeCBKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_accessoryTypeCBKeyPressed
    {//GEN-HEADEREND:event_accessoryTypeCBKeyPressed
        mm.componentRequestFocus(evt, countTXT);
    }//GEN-LAST:event_accessoryTypeCBKeyPressed

    private void submitBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_submitBTNActionPerformed
    {//GEN-HEADEREND:event_submitBTNActionPerformed
        if (countTXT.getText().equals("Count") || priceTXT.getText().equals("0"))
        {
            JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
            return;
        }

        handler.insertOrUpdate("update accessories_transactions set date = '" +
                ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText() + "', count = " +
                countTXT.getText() + ", price = " + 
                priceTXT.getText() + ", accessor_id = "
                + "(select id from accessories where name = '" + accessoryTypeCB.getSelectedItem().toString() + "') " +
                " where id = " + id,
                "Transaction Updated Successfully...");

        this.closeDialog(null);
    }//GEN-LAST:event_submitBTNActionPerformed

    int response;
    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteBTNActionPerformed
    {//GEN-HEADEREND:event_deleteBTNActionPerformed
        response = JOptionPane.showConfirmDialog(this, "Are you sure that you wanna delete this transaction", "confirm", 2);
        
        if (response == JOptionPane.OK_OPTION)
        {
            handler.deletRecord("accessories_transactions", id);

            this.closeDialog(null);
        }
    }//GEN-LAST:event_deleteBTNActionPerformed

    public void inflateUI(int id, int count, int price, Date date, String type)
    {
        accessoryTypeCB.removeAllItems();
        handler.fillAccessoriesNamesComboBox(accessoryTypeCB);
        
        this.id = id;
        mm.focusGained(countTXT, Integer.toString(count));
        mm.focusGained(priceTXT, Integer.toString(price));
        jDateChooser1.setDate(date);
        accessoryTypeCB.setSelectedItem(type);
        
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
                UpdateAccessories dialog = new UpdateAccessories(new java.awt.Frame(), true);
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
    private javax.swing.JComboBox accessoryTypeCB;
    private javax.swing.JTextField countTXT;
    private javax.swing.JButton deleteBTN;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField priceTXT;
    private javax.swing.JButton submitBTN;
    // End of variables declaration//GEN-END:variables
}
