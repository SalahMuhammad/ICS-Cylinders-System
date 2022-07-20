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

public class UpdateIn extends java.awt.Dialog {

    public UpdateIn(java.awt.Frame parent, boolean modal) {
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
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        submitBTN = new javax.swing.JButton();
        noticeTXT = new javax.swing.JTextField();
        deleteBTN = new javax.swing.JButton();

        setResizable(false);
        setTitle("Update In Transaction");
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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(submitBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteBTN))
                    .addComponent(noticeTXT)
                    .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(countTXT))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(countTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(noticeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(submitBTN)
                    .addComponent(deleteBTN))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

    private void countTXTKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_countTXTKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            jDateChooser1.requestFocus();
    }//GEN-LAST:event_countTXTKeyPressed

    private void jDateChooser1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jDateChooser1KeyPressed
        // TODO add your handling code here:
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            noticeTXT.requestFocus();
    }//GEN-LAST:event_jDateChooser1KeyPressed

    private void countTXTFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_countTXTFocusGained
        // TODO add your handling code here:
        mm.ifFocusGained(countTXT, "Count");
    }//GEN-LAST:event_countTXTFocusGained

    private void countTXTFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_countTXTFocusLost
        // TODO add your handling code here:
        mm.ifFocusLost(countTXT, "Count");
    }//GEN-LAST:event_countTXTFocusLost

    private void submitBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_submitBTNActionPerformed
       
        if (countTXT.getText().equals("Count"))
        {
            JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
            return;
        }
        
        handler.insertOrUpdate("update in_table set date = '" +
                ((JTextField)jDateChooser1.getDateEditor().getUiComponent()).getText() + "', count = " +
                countTXT.getText() + ", notice = '" +
                noticeTXT.getText() + "' where id = " + id,
                "Transaction Updated Successfully...");

        this.closeDialog(null);
    }//GEN-LAST:event_submitBTNActionPerformed

    private void noticeTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_noticeTXTFocusGained
    {//GEN-HEADEREND:event_noticeTXTFocusGained
        mm.ifFocusGained(noticeTXT, "Notice");
    }//GEN-LAST:event_noticeTXTFocusGained

    private void noticeTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_noticeTXTFocusLost
    {//GEN-HEADEREND:event_noticeTXTFocusLost
        mm.ifFocusLost(noticeTXT, "Notice");
    }//GEN-LAST:event_noticeTXTFocusLost

    private void noticeTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_noticeTXTKeyPressed
    {//GEN-HEADEREND:event_noticeTXTKeyPressed
        if(evt.getKeyCode() == KeyEvent.VK_ENTER)
            submitBTNActionPerformed(null);
    }//GEN-LAST:event_noticeTXTKeyPressed

    int response;
    private void deleteBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteBTNActionPerformed
    {//GEN-HEADEREND:event_deleteBTNActionPerformed
        response = JOptionPane.showConfirmDialog(this, "Are you sure that you wanna delete this transaction", "confirm", 2);
        
        if (response == JOptionPane.OK_OPTION)
        {
            handler.deletRecord("in_table", id);

            this.closeDialog(null);
        }
    }//GEN-LAST:event_deleteBTNActionPerformed
    
    public void inflateUI(int id, int count, Date date, String notice)
    {
        this.id = id;
        
        mm.focusGained(countTXT, Integer.toString(count));
        jDateChooser1.setDate(date);
        mm.focusGained(noticeTXT, notice);
        
        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                UpdateIn dialog = new UpdateIn(new java.awt.Frame(), true);
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
    private javax.swing.JButton submitBTN;
    // End of variables declaration//GEN-END:variables
}
