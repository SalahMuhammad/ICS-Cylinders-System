package icscylinders;

public class Treasury extends java.awt.Dialog
{

    public Treasury(java.awt.Frame parent, boolean modal)
    {
        super(parent, modal);
        initComponents();  
    }
    
    DatabaseHandler handler = new DatabaseHandler();
    
    void inflateUI (String from, String to)
    {
        fromLabel.setText(from);
        toLabel.setText(to);
        
        cashOnHandValueLabl.setText(Integer.toString(
                handler.getCashOnHand("")));
        
        totalProfitBetweenLabel.setText(": " + Integer.toString(
                handler.getCashOnHand(" where date between '" + from + "' and '" + to + "'")));
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPanel1 = new javax.swing.JPanel();
        cashOnHandLabel = new javax.swing.JLabel();
        totalProfitFromToLabel = new javax.swing.JLabel();
        fromLabel = new javax.swing.JLabel();
        totalProfitFromToLabel2 = new javax.swing.JLabel();
        toLabel = new javax.swing.JLabel();
        cashOnHandValueLabl = new javax.swing.JLabel();
        totalProfitBetweenLabel = new javax.swing.JLabel();

        setResizable(false);
        setTitle("Treasury");
        addWindowListener(new java.awt.event.WindowAdapter()
        {
            public void windowClosing(java.awt.event.WindowEvent evt)
            {
                closeDialog(evt);
            }
        });

        cashOnHandLabel.setFont(new java.awt.Font("Times New Roman", 1, 18)); // NOI18N
        cashOnHandLabel.setText("Cash On Hand: ");

        totalProfitFromToLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        totalProfitFromToLabel.setText("Total Profit From: ");

        fromLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        fromLabel.setForeground(new java.awt.Color(102, 102, 102));
        fromLabel.setText("0000-00-00");

        totalProfitFromToLabel2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        totalProfitFromToLabel2.setText(" To: ");
        totalProfitFromToLabel2.setToolTipText("");

        toLabel.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        toLabel.setForeground(new java.awt.Color(102, 102, 102));
        toLabel.setText("0000-00-00");
        toLabel.setToolTipText("");

        cashOnHandValueLabl.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        cashOnHandValueLabl.setForeground(new java.awt.Color(0, 0, 0));

        totalProfitBetweenLabel.setFont(new java.awt.Font("Times New Roman", 3, 14)); // NOI18N
        totalProfitBetweenLabel.setForeground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(totalProfitFromToLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(fromLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalProfitFromToLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(toLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalProfitBetweenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(cashOnHandLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cashOnHandValueLabl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(cashOnHandLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cashOnHandValueLabl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(totalProfitFromToLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(fromLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(totalProfitFromToLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(toLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(totalProfitBetweenLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
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
    }//GEN-LAST:event_closeDialog

    public static void main(String args[])
    {
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                Treasury dialog = new Treasury(new java.awt.Frame(), true);
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
    private javax.swing.JLabel cashOnHandLabel;
    private javax.swing.JLabel cashOnHandValueLabl;
    private javax.swing.JLabel fromLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel toLabel;
    private javax.swing.JLabel totalProfitBetweenLabel;
    private javax.swing.JLabel totalProfitFromToLabel;
    private javax.swing.JLabel totalProfitFromToLabel2;
    // End of variables declaration//GEN-END:variables
}
