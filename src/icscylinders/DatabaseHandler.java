package icscylinders;

import Objects.DisplayTransactionsBetweenObject;
import Objects.*;
import com.toedter.calendar.JDateChooser;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import mymethods.MessageDialog;
import mymethods.MyMethods;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class DatabaseHandler {
 
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    String sql;
    
    // connection block
    // <editor-fold>

    void createConnection() throws SQLException
    {
        con = DriverManager.getConnection("jdbc:mysql://localhost/cylinders_db?user=ICS&password=ICS&useSSL=false");
    }
    
    public void closeConnection(int value)
    {
        //1 mean rs.close() st.close() con.close(),||| 2 mean ps.close() con.close(),||| 3 mean st.close() con.close(),||| 4 mean con.close()
        try
        {
            switch (value)
            {        
                case 1:
                    ps.close();
                    con.close();
                    break;

                case 2:
                    con.close();
                    break;

                case 3:
                    rs.close();
                    ps.close();
                    con.close();
                    break;
                    
                case 4:
                    con.close();
                    break;

                default:
                    JOptionPane.showMessageDialog(null, "Something Went Wrong \nConnection Not Closed...DefaultMessage", "Error", 0);
            }
        }
        catch (SQLException ex)
        {
            System.err.println("closeConnection() Error: " + ex.getMessage());
        }
    }
    
    void getConPStAndRS(String sql) throws SQLException
    {
        createConnection();
        ps = con.prepareStatement(sql);
        rs = ps.executeQuery();
    }
    
    public void insertOrUpdate(String quary, String message)
    {   
        try
        {
            createConnection();
            ps = con.prepareStatement(quary);
            ps.executeUpdate();
            
            closeConnection(1);
            
            if(message != null)
                displayMessageDialog(message);
        }
        catch (SQLSyntaxErrorException sex)
        {
            closeConnection(1);
            System.err.println("insertOrUpdate() Error: " + sex.getMessage());
            JOptionPane.showMessageDialog(null, "Invalid values..", "message", 2);
        }
        catch (SQLException ex)
        {
            closeConnection(1);
            System.err.println("insertOrUpdate() Error: " + ex.getMessage());
        }
        
    }
    
    /**
     * this method deleted a row from database
     * 
     * @param tableName table name in database
     * @param id row id in database
     */
    public void deletRecord(String tableName, int id)
    {
        try
        {
            createConnection();
            
            ps = con.prepareStatement("delete from " + tableName + " where id = " + id);
            ps.execute();
            
            closeConnection(1);
            
            displayMessageDialog("Record Deleted Successfully...");
        }
        catch (SQLException ex)
        {
            closeConnection(1);
            System.out.println("deletRecord() Error: " + ex.getMessage());
        }
    }
    
    public void deletRecord(String quary)
    {
        try
        {
            createConnection();
            
            ps = con.prepareStatement(quary);
            ps.execute();
            
            closeConnection(1);
            
            displayMessageDialog("Record Deleted Successfully...");
        }
        catch (SQLException ex)
        {
            closeConnection(1);
            System.out.println("deletRecord() Error: " + ex.getMessage());
            JOptionPane.showMessageDialog(null, ex, "Error", 0);
        }
    }
    
    // </editor-fold>
    
    public String getCustomerType(int id)
    {
        try
        {
            getConPStAndRS("select type from customers where id = " + id);
            
            if(rs.next())
                return rs.getString("type");
            
            closeConnection(3);
        }
        catch (SQLException ex)
        {
            closeConnection(3);
            System.err.println("selectCustomerType() Error: " + ex.getMessage());
        }
        
        return null;
    }
    
    public void displayMessageDialog(String message)
    {
        MessageDialog a = new MessageDialog(null, true);
        a.getMessageLabel().setText(message);
        a.setLocationRelativeTo(null);
        a.setVisible(true);
        a.dispose();
    }
    
    // load lists block
    // <editor-fold>
        // Start Of Customers Block

            ArrayList<Customers> cList;
            Customers c;
            ArrayList<Customers> getCustomersList(String customersType, String name)
            {
                cList = new ArrayList<>();

                try
                {
                    getConPStAndRS("select * from customers where type = '" + customersType + "' and name like '%" + name + "%' order by name asc");

                    while (rs.next())
                    {
                        c = new Customers(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("cylinder_unitprice"));

                        cList.add(c);
                    }

                    closeConnection(3);
                }
                catch (SQLException ex)
                {
                    closeConnection(3);
                    System.err.println("getCustomersList() Error: " + ex.getMessage());
                }

                return cList;
            }
            
        // End Of Customers Block
        
        // start of get purchase list block
        
            ArrayList<Purchase> pList;
            Purchase p;
            ArrayList<Purchase> getPurchaseList(String whereDateBetween)
            {
                pList = new ArrayList<>();

                try
                {
                    getConPStAndRS("select * from purchase where date between " + whereDateBetween + " order by date desc");

                    while (rs.next())
                    {
                        p = new Purchase(
                                rs.getInt("id"),
                                rs.getInt("count"),
                                rs.getDate("date"),
                                rs.getString("notice"));

                        pList.add(p);
                    }

                    closeConnection(3);
                }
                catch (SQLException ex)
                {
                    closeConnection(3);
                    System.err.println("getPurchaseList() Error: " + ex.getMessage());
                }

                return pList;
            }
            
        // end of get purchase list block
        
        // start of get refilled list block
            
            ArrayList<Refilled> rList;
            Refilled r;
            ArrayList<Refilled> getRefilledList(int id, String whereDateBetween)
            {
                rList = new ArrayList<>();

                try
                {
                    getConPStAndRS("select * from refilled where customer_id = " + id + " and date between " + whereDateBetween + " order by date desc");

                    while (rs.next())
                    {
                        r = new Refilled(
                                rs.getInt("id"),
                                rs.getInt("count"),
                                rs.getDate("date"));

                        rList.add(r);
                    }

                    closeConnection(3);
                }
                catch (SQLException ex)
                {
                    closeConnection(3);
                    System.err.println("getRefilledList() Error: " + ex.getMessage());
                }

                return rList;
            }
            
        // end of get refilled list block
        
        // start of get in list block
            
            ArrayList<In> inList;
            In in;
            /**
             * 
             * @param id customer id 
             * @return 
             * <br> it return array list that contains all transaction with specific customer id 
             */
            ArrayList<In> getInList(int id, String whereDateBetween)
            {
                inList = new ArrayList<>();

                try
                {
                    getConPStAndRS("select * from in_table where customer_id = " + id + " and date between " + whereDateBetween + " order by date desc");

                    while (rs.next())
                    {
                        in = new In(
                                rs.getInt("id"),
                                rs.getInt("count"),
                                rs.getDate("date"),
                                rs.getString("notice"));

                        inList.add(in);
                    }

                    closeConnection(3);
                }
                catch (SQLException ex)
                {
                    closeConnection(3);
                    System.err.println("getInList() Error: " + ex.getMessage());
                }

                return inList;
            }
            
        // end of get in list block
        
        // start of get out list block
            
            ArrayList<Out> oList;
            Out out;
            ArrayList<Out> getOutList(int id, String whereDateBetween)
            {
                oList = new ArrayList<>();

                try
                {
                    getConPStAndRS("select * from out_table where customer_id = " + id + " and date between " + whereDateBetween + " order by date desc");

                    while (rs.next())
                    {
                        out = new Out(
                                rs.getInt("id"),
                                rs.getInt("count"),
                                rs.getDate("date"),
                                rs.getString("receipt_number"),
                                rs.getInt("unit_price"),
                                rs.getString("notice"));

                        oList.add(out);
                    }

                    closeConnection(3);
                }
                catch (SQLException ex)
                {
                    closeConnection(3);
                    System.err.println("getOutList() Error: " + ex.getMessage());
                }

                return oList;
            }
            
        // end of get out list block
        
        // start of get credit list block
            
            ArrayList<CylindersAccounting> creditList;
            CylindersAccounting credit;
            ArrayList<CylindersAccounting> getCreditList(int id, String whereDateBetween)
            {
                creditList = new ArrayList<>();

                try
                {
                    getConPStAndRS("select * from credit where customer_id = " + id + " and date between " + whereDateBetween + " order by date desc");

                    while (rs.next())
                    {
                        credit = new CylindersAccounting(
                                rs.getInt("id"),
                                rs.getString("description"),
                                rs.getDate("date"),
                                rs.getInt("credit"));

                        creditList.add(credit);
                    }

                    closeConnection(3);
                }
                catch (SQLException ex)
                {
                    closeConnection(3);
                    System.err.println("getCreditList() Error: " + ex.getMessage());
                }

                return creditList;
            }
            
        // end of get credit list block
        
        // start of get debt list block
            
            ArrayList<CylindersAccounting> debtList;
            CylindersAccounting debt;
            ArrayList<CylindersAccounting> getDebtList(int id, String whereDateBetween)
            {
                debtList = new ArrayList<>();

                try
                {
                    getConPStAndRS("select * from debt where customer_id = " + id + " and date between " + whereDateBetween + " order by date desc");

                    while (rs.next())
                    {
                        debt = new CylindersAccounting(
                                rs.getInt("id"),
                                rs.getString("description"),
                                rs.getDate("date"),
                                rs.getInt("debt"));

                        debtList.add(debt);
                    }

                    closeConnection(3);
                }
                catch (SQLException ex)
                {
                    closeConnection(3);
                    System.err.println("getDebtList() Error: " + ex.getMessage());
                }

                return debtList;
            }
            
        // end of get debt list block

        // start of get bigCylinders list block
            
            ArrayList<BigCylinders> bCylindersList;
            BigCylinders bCylinders;
            ArrayList<BigCylinders> getbigCylindersList(int id, String whereDateBetween)
            {
                bCylindersList = new ArrayList<>();

                try
                {
                    getConPStAndRS("select * from bigcylinders where customer_id = " + id + " and date between " + whereDateBetween + " order by date desc");

                    while (rs.next())
                    {
                        bCylinders = new BigCylinders(
                                rs.getInt("id"),
                                rs.getInt("count"),
                                rs.getInt("unitprice"),
                                rs.getDate("date"),
                                rs.getString("from_or_to"),
                                rs.getString("notice"));

                        bCylindersList.add(bCylinders);
                    }

                    closeConnection(3);
                }
                catch (SQLException ex)
                {
                    closeConnection(3);
                    System.err.println("getbigCylindersList() Error: " + ex.getMessage());
                }

                return bCylindersList;
            }
            
        // end of get bigCylinders list block
            
        ArrayList<Accessories> accessoriesList;
        Accessories accessories;
        ArrayList<Accessories> getAccessoriesList(int id, String whereDateBetween)
        {
            accessoriesList = new ArrayList<>();

            try
            {
                getConPStAndRS("select t.id, t.price, t.count, t.date, n.name from accessories_transactions t"
                        + " join accessories n on t.accessor_id = n.id where customer_id = " + id + " and date between " + whereDateBetween + " order by date desc");

                while (rs.next())
                {
                    accessories = new Accessories(
                            rs.getInt("id"),
                            rs.getString("name"),
                            rs.getInt("price"),
                            rs.getInt("count"),
                            rs.getDate("date"));

                    accessoriesList.add(accessories);
                }

                closeConnection(3);
            }
            catch (SQLException ex)
            {
                closeConnection(3);
                System.err.println("getAccessoriesList() Error: " + ex.getMessage());
            }

            return accessoriesList;
        }
            
        ArrayList<DisplayTransactionsBetweenObject> DCB_List;
        DisplayTransactionsBetweenObject dcb;
        public ArrayList<DisplayTransactionsBetweenObject> displayOutTransactionsBetween (String from, String to)
        {
            DCB_List = new ArrayList<>();

            try
            {
                getConPStAndRS("select c.name, o.count, o.unit_price, o.date, o.receipt_number, o.notice from out_table o"
                        + " join customers c on c.id = o.customer_id where o.date between '" + from + "' and '" + to + "' and o.customer_id not in(49, 35, 36, 21, 17, 18) order by o.date desc");

                while (rs.next())
                {
                    dcb = new DisplayTransactionsBetweenObject(
                              rs.getString("name")
                            , rs.getInt("count")
                            , rs.getInt("unit_price")
                            , rs.getDate("date")
                            , rs.getString("receipt_number")
                            , rs.getString("notice"));

                    DCB_List.add(dcb);
                }

                closeConnection(3);
            }
            catch (SQLException ex)
            {
                closeConnection(3);
                System.out.println("displayTransactionsBetween Error: " + ex.getMessage());
            }

            return DCB_List;
        }
        
        ArrayList<DebtTransactionsBetween> debtTransactionsBetweenList;
        DebtTransactionsBetween debtTransactionsBetween;
        public ArrayList<DebtTransactionsBetween> displayDebtTransactionsBetween (String from, String to, String tableName)
        {
            debtTransactionsBetweenList = new ArrayList<>();

            try
            {
                getConPStAndRS("select c.name, d.description, d.date, d." + tableName + " from " + tableName + " d"
                        + " join customers c on c.id = d.customer_id where d.date between '" + from + "' and '" + to + "' and d.customer_id not in(49, 35, 36, 27, 21, 17, 18, 26, 42, 43, 28) order by d.date desc");

                while (rs.next())
                {
                    debtTransactionsBetween = new DebtTransactionsBetween(
                              rs.getString("name")
                            , rs.getString("description")
                            , rs.getDate("date")
                            , rs.getInt(tableName));

                    debtTransactionsBetweenList.add(debtTransactionsBetween);
                }

                closeConnection(3);
            }
            catch (SQLException ex)
            {
                closeConnection(3);
                System.out.println("displayDebtTransactionsBetween Error: " + ex.getMessage());
            }

            return debtTransactionsBetweenList;
        }
        
    // </editor-fold>
    
    // about accessories
    // <editor-fold>
    ArrayList<String> list;
    String value;
    public void fillAccessoriesNamesComboBox (JComboBox comboBoxName)
    {
        list = new ArrayList<>();
        
        list.add("Other");
        
        try
        {
            getConPStAndRS("select name from accessories");
            
            while(rs.next())
            {
                value = rs.getString("name");

                list.add(value);
            }
            
            for(int i = 0; i < list.size(); i++)
                comboBoxName.addItem(list.get(i));
            
            closeConnection(3);
        }
        catch (SQLException ex)
        {
            closeConnection(3);
            System.out.println("faillAccessoriesNamesComboBox() Error: " + ex.getMessage());
        }
    } 
            
    /**
     * this method insert into accessories table new accessory
     * 
     * @param name new accessory name
     */
    void insertNewAccessories (String name)
    {
        insertOrUpdate("insert into accessories (name) values ('" + name + "')", "New Accessor Inserted Successfully..");
    }
    
    void insertNewAccessoriesTransaction (String accessor_name, int customer_id, int price, int count, String date)
    {
        insertOrUpdate("insert into accessories_transactions (accessor_id, customer_id, price, count, date) values (" +
                "(select id from accessories where name = '" + accessor_name + "'), " +
                customer_id + "," +
                price + ", " +
                count + ", '" +
                date + "')"
                , "Transaction Inserted Successfully..");
    }
    
    // </editor-fold>
    
    // calculate Totals
    // <editor-fold>
        int totalPurchase;
        int totalDamaged;
        int totalIn;
        int totalOut;
        int totalRefilled;
        int totalBigCylinders;

        void getCylindersTotals()
        {
            try
            {
                getConPStAndRS("select sum(count) as total_purchase from purchase");

                if (rs.next())
                    totalPurchase = rs.getInt("total_purchase");


                rs = ps.executeQuery("select sum(count) as total_damaged from damaged");

                if (rs.next())
                    totalDamaged = rs.getInt("total_damaged");


                rs = ps.executeQuery("select sum(count) as total_in from in_table");

                if (rs.next())
                    totalIn = rs.getInt("total_in");


                rs = ps.executeQuery("select sum(count) as total_out from out_table");

                if (rs.next())
                    totalOut = rs.getInt("total_out");


                rs = ps.executeQuery("select sum(count) as total_refilled from refilled");

                if (rs.next())
                    totalRefilled = rs.getInt("total_refilled");

                closeConnection(3);
            }
            catch (SQLException ex)
            {
                System.err.println("getCylindersTotals() Error: " + ex.getMessage());
            }
        }

        int getTotalPurchase()
        {
            getCylindersTotals();
            return totalPurchase;
        }

        int getTotalInTheStore()
        {
            getCylindersTotals();
            return totalPurchase - totalDamaged;
        }

        int getTotalAvailabe()
        {
            getCylindersTotals();
            return totalPurchase + totalIn - totalDamaged - totalRefilled;
        }

        int getTotalRefilled()
        {
            getCylindersTotals();
            return totalRefilled - totalOut;
        }

        int getTotalOut()
        {
            getCylindersTotals();
            return totalOut - totalIn;
        }

        
        int from;
        int to;
        int getBigCylindersTotal()
        {
            try
            {
                getConPStAndRS("select sum(count) as total_from from bigcylinders "
                        + "where from_or_to = 'from'");
                
                if (rs.next())
                    from = rs.getInt("total_from");

                rs = ps.executeQuery("select sum(count) as total_to from bigcylinders "
                        + "where from_or_to = 'to'");
                
                if (rs.next())
                    to = rs.getInt("total_to");
                
                closeConnection(3);
                
                return from - to;
            }
            catch (SQLException ex)
            {
                System.err.println("getBigCylindersTotal() Error: " + ex.getMessage());
            }
            return 0;
        }
        
        int total;
        /**
         * select Remaining Cylinders where customer_id = 
         * @param id
         * @return 
         */
        public int getTotalRemainingCylindersForSpecificCustomer (int customer_id)
        {
            total = 0;
            
            try
            {
                getConPStAndRS("select sum(count) as total_out from out_table where customer_id = " + customer_id);
                
                if (rs.next())
                    total = total + rs.getInt("total_out");
                
                rs = ps.executeQuery("select sum(count) total_in from in_table where customer_id = " + customer_id);
                
                if (rs.next())
                    total = total - rs.getInt("total_in");
                
                closeConnection(3);
            }
            catch (SQLException ex)
            {
                closeConnection(3);
                System.err.println("totalRemainingCylindersForSpecificCustomer() Error: " + ex.getMessage());
            }
            
            return total;
        }
        
        int times;
        int totalCredit;
        int totalDebt;
        public int getTotalDebtForSpecificCustomer (int customer_id)
        {
            totalCredit = 0;
            totalDebt = 0;
            
            try
            {
                getConPStAndRS("select count, unit_price from out_table where customer_id = " + customer_id);
                
                // get out cylinders credit
                    while (rs.next())
                        totalCredit = totalCredit + (rs.getInt("count") * rs.getInt("unit_price"));
                
                // get credit from credit table    
                    rs = ps.executeQuery("select credit from credit where customer_id = " + customer_id);

                    while (rs.next())
                        totalCredit = totalCredit + rs.getInt("credit");
                
                // get big cylinders credit    
                    rs = ps.executeQuery("select count, unitprice from bigcylinders where customer_id = " + customer_id);

                    while (rs.next())
                        totalDebt = totalDebt + (rs.getInt("count") * rs.getInt("unitprice"));
                    
                // get debt from debt table    
                    rs = ps.executeQuery("select debt from debt where customer_id = " + customer_id);

                    while (rs.next())
                        totalDebt = totalDebt + rs.getInt("debt");
                    
                    
                // get refilled canisters credit
                    rs = ps.executeQuery("select count from refilled where customer_id = " + customer_id);
                    
                    if (customer_id == 28)
                    {
                        times = 300;
                    }
                    else if (customer_id == 43)
                    {
                        times = 5;
                    }
                    else
                    {
                        times = 10;
                    }
                    
                    while (rs.next())
                        totalDebt = totalDebt + (rs.getInt("count") * times);
                    
                
                // total accessory debt 
                    rs = ps.executeQuery("select price, count from accessories_transactions where customer_id = " + customer_id);
                    
                    while (rs.next())
                        totalCredit = totalCredit + (rs.getInt("price") * rs.getInt("count"));
                    
                closeConnection(3);
                
                return totalCredit - totalDebt;
            }
            catch (SQLException ex)
            {
                closeConnection(3);
                System.out.println("getTotalDebtForSpecificCustomer() Error: " + ex.getMessage());
            }
            
            return 0;
        }
    
        int getTotalCylindersOutBetween(String dateFrom, String dateTo)
        {
            try
            {
                getConPStAndRS("select sum(count) as total_out from out_table where date between '" + dateFrom + "' and '" + dateTo + "' and customer_id not in(49, 35, 36, 21, 17, 18)");

                while (rs.next())
                {
                    return rs.getInt("total_out");
                }

                closeConnection(3);
            }
            catch (SQLException ex)
            {
                closeConnection(3);
                System.out.println("getTotalCylindersOutBetween() Error: " + ex.getMessage());
            }

            return 0;
        }
        
    // </editor-fold>
           
    // about settings 
    // <editor-fold>
        
    java.util.Date getDate (String select_from_or_to1) 
    {
        try
        {
            getConPStAndRS("select " + select_from_or_to1 + " from settings where id = 3");
            
            if (rs.next())
                return rs.getDate(select_from_or_to1);
            
            closeConnection(3);
        }
        catch (SQLException ex)
        {
            closeConnection(3);
            System.out.println("getDate() Error: " + ex.getMessage());
        }
        
        return null;
    }
        
    /**
     * this method update date vlues in settings table in database <bt>
     * from of type (Date) and to of type (Date) 
     * 
     * @param fromValue 
     * @param to
     */
    void updateDate_inSettingsTable (String fromValue, String toValue)
    {
        insertOrUpdate("update settings set fromm = '" +
                fromValue +
                "', too = '" +
                toValue + "'"
                , "Values Updated Successfully...");
    }
    
    //</editor-fold>
   
    // about treasury
    // <editor-fold>
        
        int totalInTreasury;
        int getCashOnHand(String condition)
        {
            totalInTreasury = 0;
            
            try
            {
                getConPStAndRS("select sum(debt) as totalDebt from debt" + condition);

                if (rs.next())
                {
                    totalInTreasury = totalInTreasury + rs.getInt("totalDebt");
                }

                rs = ps.executeQuery("select sum(credit) as totalCredit from credit" + condition);

                if (rs.next())
                {
                    totalInTreasury = totalInTreasury - rs.getInt("totalCredit");
                }

                closeConnection(3);   
            }
            catch (SQLException ex)
            {
                closeConnection(3);
                System.err.println("getCashOnHand() Error: " + ex.getMessage());
            }
            
           return totalInTreasury; 
        }
    
    // </editor-fold>

        
    JasperReport jr;
    JasperPrint jp;
    String reportPath;
    HashMap hashMap;
    /**
     * in case you need you report between specific date put from date value and to date value
     * 
     * @param reportPath
     * @param reportName
     * @param Name
     * @param customerId
     * @param from
     * @param to 
     */
    public void run_icsCylinders_Reports(String reportName, String Name, String customerId, Date from, Date to)
    {
        try
        {
            hashMap = new HashMap();
            
            hashMap.put("Name", Name);
            hashMap.put("CustomerID", customerId);
            hashMap.put("From", from);
            hashMap.put("To", to);
            
            createConnection();
            
            reportPath = "Reports\\" + reportName + ".jrxml";
            
            jr = JasperCompileManager.compileReport(reportPath);
            jp = JasperFillManager.fillReport(jr, hashMap, con);
            JasperViewer.viewReport(jp, false);
            
            con.close();
        }
        catch (SQLException ex)
        {
            closeConnection(4);
            JOptionPane.showMessageDialog(null, ex, "Error", 2);
        }
        catch (JRException ex)
        {
            closeConnection(4);
            JOptionPane.showMessageDialog(null, "Wrong Location OR File Not Found..\n\n" + ex, "Error", 0);
        }
    }
        
    ArrayList<SummaryReportObject> list1;
    SummaryReportObject summaryReportObject;
    int outL, debtL, accessoriesL, max;
    ArrayList<Integer> s;
    public ArrayList runInvoiceReport (int customer_id)
    {
        list1 = new ArrayList<>();

        oList = new ArrayList<>();
        debtList = new ArrayList<>();
        accessoriesList = new ArrayList<>();
        
        try
        {
            getConPStAndRS("select count, unit_price, date from out_table where customer_id = " + customer_id);
            
            while (rs.next())
            {
                out = new Out(rs.getInt("count"), rs.getDate("date"), rs.getInt("unit_price"));
                
                oList.add(out);
            }
            
            rs = ps.executeQuery("select t.price, t.count, t.date, n.name from accessories_transactions t"
                        + " join accessories n on t.accessor_id = n.id where customer_id = " + customer_id);
            
            while (rs.next())
            {
                accessories = new Accessories(rs.getString("name"), rs.getInt("price"), rs.getInt("count"), rs.getDate("date"));
                
                accessoriesList.add(accessories);
            }
            
            rs = ps.executeQuery("select debt, date from debt where customer_id = " + customer_id);
            
            while (rs.next())
            {
                debt = new CylindersAccounting(rs.getDate("date"), rs.getInt("debt"));
                
                debtList.add(debt);
            }
        }
        catch (SQLException ex)
        {
            System.out.println("runInvoiceReport Error: " + ex.getMessage());
        }
            
        outL = oList.size();
        debtL = debtList.size();
        accessoriesL = accessoriesList.size();

        s = new ArrayList<>();
        s.add(outL);
        s.add(debtL);
        s.add(accessoriesL);

        max =  Collections.max(s);

        while (debtList.size() < max)
        {
            debt = new CylindersAccounting(null, 0);
            debtList.add(debt);
        }

        while (accessoriesList.size() < max)
        {
            accessories = new Accessories(null, 0, 0, null);
            accessoriesList.add(accessories);
        }

        while (oList.size() < max)
        {
            out = new Out(0, null, 0);
            oList.add(out);
        }

//        for (int i = 0; i < max; i++)
//        {
//            summaryReportObject = new SummaryReportObject(
//                    oList.get(i).getCount(), oList.get(i).getDate(), oList.get(i).getUnitPrice()
//                    , accessoriesList.get(i).getName(), accessoriesList.get(i).getCount(), accessoriesList.get(i).getPrice(), accessoriesList.get(i).getDate()
//                    , debtList.get(i).getCreditOrDebt(), debtList.get(i).getDate());
//            list1.add(summaryReportObject);
//        }
        
//        for (int i = 0; i < oList.size(); i++)
//        {
//            summaryReportObject = new SummaryReportObject(
//                    oList.get(i).getCount(), oList.get(i).getDate(), oList.get(i).getUnitPrice(),
//                    null, 0, 0, null,
//                    0, null);
//            
//            list1.add(summaryReportObject);
//        }
Date a = null;
Date b = null;
        for (int i = 0; i < oList.size(); i++)
        {
            a = oList.get(i).getDate();
            for (int t = 0; t < debtList.size(); t++)
            {
                b = debtList.get(t).getDate();
                boolean bb = false;
                if (a == b)
                System.out.println("ds");
            }
//            System.out.println(oList.get(i).getCount() + "  " + oList.get(i).getDate() + "  " + oList.get(i).getUnitPrice());
//            System.out.println("");
//            System.out.println(debtList.get(i).getCreditOrDebt() + "  " + debtList.get(i).getDate());
        }
          
        return list1;
    }
    
    void updateReportsPath (String newPath)
    {
        insertOrUpdate("update settings set reportspath = '" + newPath + "'", "Path Updated Successfully...");
    }
    
    String getReportsPath ()
    {
        try
        {
            getConPStAndRS("select reportspath from settings");
            
            if (rs.next())
                return rs.getString("reportspath");
            
            closeConnection(3);
        }
        catch (SQLException ex)
        {
            closeConnection(3);
            System.out.println("getReportsPath() Error: " + ex.getMessage());
        }
        
        return null;
    }
    
//    int i = 0;
//    void aaaaaaa ()
//    {
//        try
//        {
//            getConPStAndRS("select c.credit, d.debt from customers s"
//                    + " join credit c on c.customer_id = s.id"
//                    + " join debt d on d.customer_id = s.id where c.customer_id = 0");
//            System.out.println("1111111111");
//            while (rs.next())
//            {i++;
////                System.out.println(rs.getInt("name"));
//                System.out.println(rs.getInt("credit"));// System.out.print("|"); System.out.print(rs.getDate("date"));
//                System.out.println(i);
//                System.out.println(rs.getInt("debt"));// System.out.print("|"); System.out.print(rs.getDate("date"));
//            }
//            System.out.println("2222222");
//            
//            
//            closeConnection(3);
//        }
//        catch (SQLException ex)
//        {
//            closeConnection(3);
//            System.out.println("aaaaaaa Error: " + ex.getMessage());
//        }
//    }
}

    
