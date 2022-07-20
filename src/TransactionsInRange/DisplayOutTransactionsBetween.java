package TransactionsInRange;

import Objects.DisplayTransactionsBetweenObject;
import CustomItem.GrayScrollBarUI;
import icscylinders.DatabaseHandler;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import mymethods.MyMethods;

public class DisplayOutTransactionsBetween extends java.awt.Dialog
{

    public DisplayOutTransactionsBetween(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();
        
        
        mm.setTableRowsTextToCenter(outTable, 6);
        
        try
        {
        JScrollPane2.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        }
        catch (NullPointerException e)
        {
            JOptionPane.showMessageDialog(this, e.getMessage(), "Error", 0);
        }
        
        outTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        mm.modificationJtablesHeader(outTable);
        
        mm.setTableRowsTextToCenter(totalsOutTable1, 6);
    }
    
    MyMethods mm = new MyMethods();
    DatabaseHandler handler = new DatabaseHandler();
    
    DefaultTableModel model;
    Object[] row;
    
    int total;

    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        JScrollPane2 = new javax.swing.JScrollPane();
        outTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        totalsOutTable1 = new javax.swing.JTable();

        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 0, 0));

        outTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        outTable.setForeground(new java.awt.Color(0, 0, 204));
        outTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Name", "Count", "Unit Price", "Total", "Date", "Receipt Number", "Noticel"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        outTable.setRowHeight(35);
        outTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane2.setViewportView(outTable);

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
        jLabel2.setText("Except Customers: Leaser Tech, Target, Unknown, Transmis With Machines, Damaged After Refill.");
        jLabel2.setToolTipText("");

        totalsOutTable1.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        totalsOutTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Total", null, null, null, null, null, null}
            },
            new String [] {
                "Name", "Count", "Unit Price", "Total", "Date", "Receipt Number", "Noticel"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        totalsOutTable1.setRowHeight(35);
        totalsOutTable1.getTableHeader().setReorderingAllowed(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(JScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 1343, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(totalsOutTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 1340, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 627, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalsOutTable1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
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

    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        setVisible(false);
        dispose();
    }//GEN-LAST:event_closeDialog

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jLabel1MouseClicked
    {//GEN-HEADEREND:event_jLabel1MouseClicked
        this.closeDialog(null);
    }//GEN-LAST:event_jLabel1MouseClicked

    int getJTableColumnTotal(JTable tableName1, int column)
    {
        total = 0;

        for (int i = 0; i < tableName1.getRowCount(); i++)
        {
            total = total + (Integer)tableName1.getValueAt(i, column);
        }

        return total;
    }
    
    ArrayList<DisplayTransactionsBetweenObject> displayTransactionsBetweenObjectList;
    public void inflateUI (String from, String to)
    {
        displayTransactionsBetweenObjectList = handler.displayOutTransactionsBetween(from, to);

        model = (DefaultTableModel)outTable.getModel();
        row = new Object[7];

        model.setRowCount(0);
        
        for(int i = 0; i < displayTransactionsBetweenObjectList.size(); i++)
        {
            row[0] = displayTransactionsBetweenObjectList.get(i).getName();
            row[1] = displayTransactionsBetweenObjectList.get(i).getCount();
            row[2] = displayTransactionsBetweenObjectList.get(i).getUnitprice();
            row[3] = displayTransactionsBetweenObjectList.get(i).getCount() * displayTransactionsBetweenObjectList.get(i).getUnitprice();
            row[4] = displayTransactionsBetweenObjectList.get(i).getDate();
            row[5] = displayTransactionsBetweenObjectList.get(i).getrNumber();
            row[6] = displayTransactionsBetweenObjectList.get(i).getNotice();

            model.addRow(row);
        }
        
        totalsOutTable1.setValueAt(
                getJTableColumnTotal(outTable, 1),
                0,
                1);
        
        totalsOutTable1.setValueAt(
                getJTableColumnTotal(outTable, 3),
                0,
                3);
    }
    
    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                DisplayOutTransactionsBetween dialog = new DisplayOutTransactionsBetween(new java.awt.Frame(), true);
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
    private javax.swing.JScrollPane JScrollPane2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTable outTable;
    private javax.swing.JTable totalsOutTable1;
    // End of variables declaration//GEN-END:variables
}
