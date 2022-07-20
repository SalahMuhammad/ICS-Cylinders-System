package TransactionsInRange;

import CustomItem.GrayScrollBarUI;
import Objects.DebtTransactionsBetween;
import icscylinders.DatabaseHandler;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import mymethods.MyMethods;

public class DisplayCreditTransactionsBetween extends java.awt.Dialog
{

    public DisplayCreditTransactionsBetween(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        
        mm.setTableRowsTextToCenter(creditTable, 3);
        
        try
        {
        JScrollPane222222222.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        }
        catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", 0);
        }
        creditTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mm.modificationJtablesHeader(creditTable);
//        
        mm.setTableRowsTextToCenter(totalJTable, 3);
    }
    
    MyMethods mm = new MyMethods();
    DatabaseHandler handler = new DatabaseHandler();
    
    DefaultTableModel model;
    Object[] row;

    int total;

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JScrollPane222222222 = new javax.swing.JScrollPane();
        creditTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totalJTable = new javax.swing.JTable();

        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        creditTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        creditTable.setForeground(new java.awt.Color(0, 0, 204));
        creditTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Description", "Date", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        creditTable.setRowHeight(35);
        creditTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane222222222.setViewportView(creditTable);

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("X");
        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.setOpaque(true);
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Except Customers: Leaser Tech, Target, Unknown, Transmis With Machines, Damaged After Refill, Abou Kareem, Muhammad El Sayed, Emirates, Salah, Ali Muhammad Saaid.");
        jLabel2.setToolTipText("");

        totalJTable.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        totalJTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Total", null, null, null}
            },
            new String [] {
                "Name", "Description", "Date", "Value"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalJTable.setRowHeight(35);
        totalJTable.getTableHeader().setReorderingAllowed(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(JScrollPane222222222, javax.swing.GroupLayout.PREFERRED_SIZE, 1343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(totalJTable, javax.swing.GroupLayout.PREFERRED_SIZE, 1340, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(JScrollPane222222222, javax.swing.GroupLayout.PREFERRED_SIZE, 661, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalJTable, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    ArrayList<DebtTransactionsBetween> displayDebtTransactionsBetweenList;
    public void inflateUI (String from, String to)
    {
        displayDebtTransactionsBetweenList = handler.displayDebtTransactionsBetween(from, to, "credit");
        
        model = (DefaultTableModel)creditTable.getModel();
        row = new Object[4];

        model.setRowCount(0);
        
        for(int i = 0; i < displayDebtTransactionsBetweenList.size(); i++)
        {
            row[0] = displayDebtTransactionsBetweenList.get(i).getName();
            row[1] = displayDebtTransactionsBetweenList.get(i).getDescription();
            row[2] = displayDebtTransactionsBetweenList.get(i).getDate();
            row[3] = displayDebtTransactionsBetweenList.get(i).getValue();

            model.addRow(row);
        }
        
        total = 0;

        for (int i = 0; i < creditTable.getRowCount(); i++)
        {
            total = total + (Integer)creditTable.getValueAt(i, 3);
        }
        
        totalJTable.setValueAt(
                total,
                0,
                3);
    }
    
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLabel1MouseClicked
    {//GEN-HEADEREND:event_jLabel1MouseClicked
        this.closeDialog(null);
    }//GEN-LAST:event_jLabel1MouseClicked

    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                DisplayCreditTransactionsBetween dialog = new DisplayCreditTransactionsBetween(new java.awt.Frame(), true);
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
    private javax.swing.JScrollPane JScrollPane222222222;
    private javax.swing.JTable creditTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable totalJTable;
    // End of variables declaration//GEN-END:variables
}
