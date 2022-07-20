package icscylinders;

import TransactionsBetween111.DisplayOutTransactionsBetween;
import InsertOrUpdateTransactions_Frames.*;
import CustomItem.GrayScrollBarUI;
import CustomItem.TableButton;

import Objects.*;
import TransactionsBetween111.DisplayCreditTransactionsBetween;
import TransactionsBetween111.DisplayDebtTransactionsBetween;
import java.awt.Color;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import mymethods.MyMethods;
import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

public class Main extends javax.swing.JFrame
{

    MyMethods mm = new MyMethods();

    DatabaseHandler handler = new DatabaseHandler();

    TableButton tableButton;

    Action doSomeThing;

    DefaultTableModel model;
    Object[] row;

    int id;

    int count;
    int price;
    int total;

    int columnIndex;

    String newAccessorValue;

    String from;
    String to;

    JTable tableName;

    // declare array lists
    // <editor-fold>
    ArrayList<Customers> cList;
    ArrayList<Purchase> pList;
    ArrayList<Refilled> rList;
    ArrayList<In> inList;
    ArrayList<Out> outList;
    ArrayList<CylindersAccounting> creditList;
    ArrayList<CylindersAccounting> debtList;
    ArrayList<BigCylinders> bigCylindersList;
    ArrayList<Accessories> accessoriesList;

    // </editor-fold>
    // declare objects
    // <editor-fold>
    Settings settings = new Settings(this, true);
    UpdateCustomer customers = new UpdateCustomer(this, true);
    UpdatePurchaseTransaction purchase = new UpdatePurchaseTransaction(this, true);
    UpdateRefilled refilled = new UpdateRefilled(this, true);
    UpdateIn in = new UpdateIn(this, true);
    UpdateOut out = new UpdateOut(this, true);
    UpdatCreditAndDebt updateCreditAndDebt = new UpdatCreditAndDebt(this, true);
    InsertDisabled insertDisabledFrame = new InsertDisabled(this, true);
    UpdateBigCylindersTransactions updateBigCylindersTransactions = new UpdateBigCylindersTransactions(this, true);
    UpdateAccessories updateAccessory = new UpdateAccessories(this, true);

    DisplayOutTransactionsBetween dtb = new DisplayOutTransactionsBetween(this, true);
    DisplayCreditTransactionsBetween displayCreditTransactionsBetween = new DisplayCreditTransactionsBetween(this, true);
    DisplayDebtTransactionsBetween displayDebtTransactionsBetween = new DisplayDebtTransactionsBetween(this, true);

    // </editor-fold>
    public
            Main()
    {
        initComponents();

        this.setExtendedState(Frame.MAXIMIZED_BOTH);

        from = settings.getFromDateValue();
        to = settings.getToDateValue();

        initDateEditor();

        setTablesHeader();
        setTablesScrollBarToGray();
        setTablesRowsTextToCenter();
        setTablesSelectionToSingleSelection();

        displayCustomers();

        setCylindersTotals();

        setTotalCreditForAllCustomer();
        setTotalRemainingCylindersForAllCustomer();

        setSalesCustomersTotals();

        AutoCompleteDecorator.decorate(accessoriesCombobox);
        handler.fillAccessoriesNamesComboBox(accessoriesCombobox);

        getButtonsArray();
    }

    // ---------------
    // <editor-fold>
    void initDateEditor()
    {
        mm.initDateEditor(credit_jDateChooser);
        mm.initDateEditor(debt_jDateChooser1);
        mm.initDateEditor(purchase_jDateChooser1);
        mm.initDateEditor(refilled_jDateChooser);
        mm.initDateEditor(in_jDateChooser1);
        mm.initDateEditor(out_jDateChooser2);
        mm.initDateEditor(bigCylinders_jDateChooser2);
        mm.initDateEditor(accessories_jDateChooser2);
    }

    ArrayList<JButton> buttonsList = new ArrayList<>();

    void getButtonsArray()
    {
        buttonsList.add(overviewBTN);
        buttonsList.add(purchaseBTN);
        buttonsList.add(refilledBTN);
        buttonsList.add(inBTN);
        buttonsList.add(outBTN);
        buttonsList.add(creditBTN);
        buttonsList.add(debtBTN);
        buttonsList.add(bigCylindersBTN);
        buttonsList.add(accessoriesBTN);
        buttonsList.add(reportsBTN1);
    }

    void setButtonBackground(JButton btn)
    {
        for (int i = 0; i < buttonsList.size(); i++)
        {
            if (buttonsList.get(i).getBackground() == Color.BLUE)
            {
                buttonsList.get(i).setBackground(new Color(255, 0, 0));
            }
        }

        btn.setBackground(Color.BLUE);
    }

    // </editor-fold>
    // manipulating Jtables
    // <editor-fold>
    ArrayList<JTable> a = new ArrayList<>();

    void setTablesHeader()
    {
        a.add(salesCustTable);
        a.add(purchaseCustTable);
        a.add(purchaseTable);
        a.add(refilledTable);
        a.add(inTable);
        a.add(outTable);
        a.add(creditTable);
        a.add(debtTable);
        a.add(salesCustTotalsTable);
        a.add(outTotalsTable);
        a.add(accessoryTable1);

        for (int i = 0; i < a.size(); i++)
        {
            mm.modificationJtablesHeader(a.get(i));
        }
    }

    void setTablesScrollBarToGray()
    {
        catygoriesScrollBar.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        salesCustScrollPane.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        purchasesCustScrollPane1.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        JScrollPane1.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        JScrollPane2.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        JScrollPane3.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        JScrollPane4.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        JScrollPane5.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        JScrollPane6.getVerticalScrollBar().setUI(new GrayScrollBarUI());
        JScrollPane7.getVerticalScrollBar().setUI(new GrayScrollBarUI());
    }

    void setTablesRowsTextToCenter()
    {
        for (int i = 0; i < a.size(); i++)
        {
            if (a.get(i) == outTable)
            {
                mm.setTableRowsTextToCenter(a.get(i), 6);
            }
            else if (a.get(i) == refilledTable)
            {
                mm.setTableRowsTextToCenter(a.get(i), 3);
            }
            else if (a.get(i) == purchaseCustTable)
            {
                mm.setTableRowsTextToCenter(purchaseCustTable, 3);
            }
            else
            {
                mm.setTableRowsTextToCenter(a.get(i), 4);
            }
        }
    }

    void setTablesSelectionToSingleSelection()
    {
        salesCustTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        purchaseCustTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        purchaseTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        refilledTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        inTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        outTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        creditTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        debtTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        bigCylindersTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
    }

    // </editor-fold>
    
    // load tables
    // <editor-fold>
    void displayCustomers(String type, JTable tableName, int columnIndex, int cloumnsCount)
    {
        cList = handler.getCustomersList(type, custFilterTXT.getText());

        model = mm.getDefaultTableModel(tableName);
        row = new Object[cloumnsCount];

        for (int i = 0; i < cList.size(); i++)
        {
            row[0] = cList.get(i).getId();
            row[1] = cList.get(i).getName();

            if (tableName == salesCustTable)
            {
                row[4] = cList.get(i).getCylinderUnitprice();
                row[5] = "Edit";
            }
            else if (tableName == purchaseCustTable)
            {
                row[3] = "Edit";
            }
            model.addRow(row);
        }

        doSomeThing = new AbstractAction()
        {
            @Override
            public
                    void actionPerformed(ActionEvent e)
            {
//                        rowIndex = Integer.valueOf(e.getActionCommand());
//                        salesCustTable.getSelectionModel().addSelectionInterval(tableRow, tableRow);
//                        rowIndex = salesCustTable.getSelectedRow();

                id = Integer.parseInt(mm.getValueAt(tableName, 0));

                if (tableName == purchaseCustTable)
                {
                    price = 0;
                }
                else
                {
                    price = Integer.parseInt(mm.getValueAt(tableName, 4));
                }

                customers.inflateUI(id,
                        mm.getValueAt(tableName, 1),
                        handler.getCustomerType(id),
                        price);

                customers.dispose();

                displayCustomers();

                salesCustTotalsTable.setValueAt(salesCustTable.getRowCount(), 0, 1);

                setTotalCreditForAllCustomer();
                setTotalRemainingCylindersForAllCustomer();
            }
        };

        tableButton = new TableButton(tableName, doSomeThing, columnIndex);
    }

    void displayCustomers()
    {
        mm.refrichTable(salesCustTable);
        displayCustomers("sales", salesCustTable, 5, 6);

        mm.refrichTable(purchaseCustTable);
        displayCustomers("purchases", purchaseCustTable, 3, 5);
    }

    // End Of Customers Block
    // start of load purchase block
    void displayPurchaseTransactions()
    {
        pList = handler.getPurchaseList("'" + from + "' and '" + to + "'");

        model = mm.getDefaultTableModel(purchaseTable);
        row = new Object[5];

        for (int i = 0; i < pList.size(); i++)
        {
            row[0] = pList.get(i).getId();
            row[1] = pList.get(i).getCount();
            row[2] = pList.get(i).getDate();
            row[3] = pList.get(i).getNotice();
            row[4] = "Edit";

            model.addRow(row);
        }

        doSomeThing = new AbstractAction()
        {
            @Override
            public
                    void actionPerformed(ActionEvent e)
            {
                id = Integer.parseInt(mm.getValueAt(purchaseTable, 0));
                count = Integer.parseInt(mm.getValueAt(purchaseTable, 1));

                purchase.inflateUI(
                        id,
                        count,
                        (Date) purchaseTable.getValueAt(purchaseTable.getSelectedRow(), 2),
                        mm.getValueAt(purchaseTable, 3));

                purchase.dispose();

                d_purchase();

                setCylindersTotals();
            }
        };

        tableButton = new TableButton(purchaseTable, doSomeThing, 4);
    }

    void d_purchase()
    {
        mm.refrichTable(purchaseTable);
        displayPurchaseTransactions();
    }

    // end of load purchase block
    // start of load refilled block
    void displayRefilledTransactions()
    {
        rList = handler.getRefilledList(Integer.parseInt(mm.getValueAt(purchaseCustTable, 0)), "'" + from + "' and '" + to + "'");

        model = mm.getDefaultTableModel(refilledTable);
        row = new Object[4];

        for (int i = 0; i < rList.size(); i++)
        {
            row[0] = rList.get(i).getId();
            row[1] = rList.get(i).getCount();
            row[2] = rList.get(i).getDate();
            row[3] = "Edit";

            model.addRow(row);
        }

        doSomeThing = new AbstractAction()
        {
            @Override
            public
                    void actionPerformed(ActionEvent e)
            {
                id = Integer.parseInt(mm.getValueAt(refilledTable, 0));

                refilled.inflateUI(
                        id,
                        Integer.parseInt(mm.getValueAt(refilledTable, 1)),
                        (Date) refilledTable.getValueAt(refilledTable.getSelectedRow(), 2));

                refilled.dispose();

                d_refilled();

                setCylindersTotals();

                setTotalCreditForSpecificCustomer(purchaseCustTable, 2);
            }
        };

        tableButton = new TableButton(refilledTable, doSomeThing, 3);
    }

    void d_refilled()
    {
        mm.refrichTable(refilledTable);
        displayRefilledTransactions();
    }

    // end of load refilled block
    // start of load in block
    void displayInTransactions()
    {
        inList = handler.getInList(Integer.parseInt(mm.getValueAt(salesCustTable, 0)), "'" + from + "' and '" + to + "'");

        model = mm.getDefaultTableModel(inTable);
        row = new Object[5];

        for (int i = 0; i < inList.size(); i++)
        {
            row[0] = inList.get(i).getId();
            row[1] = inList.get(i).getCount();
            row[2] = inList.get(i).getDate();
            row[3] = inList.get(i).getNotice();
            row[4] = "Edit";

            model.addRow(row);
        }

        doSomeThing = new AbstractAction()
        {
            @Override
            public
                    void actionPerformed(ActionEvent e)
            {
                id = Integer.parseInt(mm.getValueAt(inTable, 0));

                in.inflateUI(
                        id,
                        Integer.parseInt(mm.getValueAt(inTable, 1)),
                        (Date) inTable.getValueAt(inTable.getSelectedRow(), 2),
                        mm.getValueAt(inTable, 3));

                in.dispose();

                d_in();

                setTotalRemainingCylindersForSpecificCustomer();

                setCylindersTotals();

                setSalesCustomersTotals();
            }
        };

        tableButton = new TableButton(inTable, doSomeThing, 4);
    }

    void d_in()
    {
        mm.refrichTable(inTable);
        displayInTransactions();
    }

    // end of load in block
    // start of load out block
    void displayOutTransactions()
    {
        outList = handler.getOutList(Integer.parseInt(mm.getValueAt(salesCustTable, 0)), "'" + from + "' and '" + to + "'");

        model = mm.getDefaultTableModel(outTable);
        row = new Object[8];

        for (int i = 0; i < outList.size(); i++)
        {
            row[0] = outList.get(i).getId();
            row[1] = outList.get(i).getCount();
            row[2] = outList.get(i).getUnitPrice();
            row[3] = outList.get(i).getCount() * outList.get(i).getUnitPrice();
            row[4] = outList.get(i).getDate();
            row[5] = outList.get(i).getReciptNumber();
            row[6] = outList.get(i).getNotice();
            row[7] = "Edit";

            model.addRow(row);
        }

        doSomeThing = new AbstractAction()
        {
            @Override
            public
                    void actionPerformed(ActionEvent e)
            {
                id = Integer.parseInt(mm.getValueAt(outTable, 0));

                out.inflateUI(
                        id,
                        Integer.parseInt(mm.getValueAt(outTable, 1)),
                        Integer.parseInt(mm.getValueAt(outTable, 2)),
                        (Date) outTable.getValueAt(outTable.getSelectedRow(), 4),
                        mm.getValueAt(outTable, 5),
                        mm.getValueAt(outTable, 6));

                out.dispose();

                d_out();

                setTotalRemainingCylindersForSpecificCustomer();

                setCylindersTotals();

                setTotalCreditForSpecificCustomer(salesCustTable, 3);

                setSalesCustomersTotals();

                totalOutBetweenLabel.setText(Integer.toString(handler.getTotalCylindersOutBetween(from, to)));
            }
        };

        tableButton = new TableButton(outTable, doSomeThing, 7);
    }

    void d_out()
    {
        mm.refrichTable(outTable);
        displayOutTransactions();

        setOutTotals();
    }

// end of load out block
// start of load credit block
    void displayCreditTransactions()
    {
        creditList = handler.getCreditList(Integer.parseInt(mm.getValueAt(tableName, 0)), "'" + from + "' and '" + to + "'");

        model = mm.getDefaultTableModel(creditTable);
        row = new Object[5];

        for (int i = 0; i < creditList.size(); i++)
        {
            row[0] = creditList.get(i).getId();
            row[1] = creditList.get(i).getCreditOrDebt();
            row[2] = creditList.get(i).getDate();
            row[3] = creditList.get(i).getDesc();
            row[4] = "Edit";

            model.addRow(row);
        }

        doSomeThing = new AbstractAction()
        {
            @Override
            public
                    void actionPerformed(ActionEvent e)
            {
                id = Integer.parseInt(mm.getValueAt(creditTable, 0));

                updateCreditAndDebt.inflateUI(
                        id,
                        Integer.parseInt(mm.getValueAt(creditTable, 1)),
                        (Date) creditTable.getValueAt(creditTable.getSelectedRow(), 2),
                        mm.getValueAt(creditTable, 3),
                        "Credit");

                updateCreditAndDebt.dispose();

                d_credit();

                if (tableName == salesCustTable)
                {
                    columnIndex = 3;
                }
                else
                {
                    columnIndex = 2;
                }

                setTotalCreditForSpecificCustomer(tableName, columnIndex);

                if (tableName == salesCustTable)
                {
                    salesCustTotalsTable.setValueAt(
                            getJTableColumnTotal(salesCustTable, 3), 0, 3);
                }
            }
        };

        tableButton = new TableButton(creditTable, doSomeThing, 4);
    }

    void d_credit()
    {
        mm.refrichTable(creditTable);
        displayCreditTransactions();
    }

// end of load credit block
// start of load debt block
    void displayDebtTransactions()
    {
        debtList = handler.getDebtList(Integer.parseInt(mm.getValueAt(tableName, 0)), "'" + from + "' and '" + to + "'");

        model = mm.getDefaultTableModel(debtTable);
        row = new Object[5];

        for (int i = 0; i < debtList.size(); i++)
        {
            row[0] = debtList.get(i).getId();
            row[1] = debtList.get(i).getCreditOrDebt();
            row[2] = debtList.get(i).getDate();
            row[3] = debtList.get(i).getDesc();
            row[4] = "Edit";

            model.addRow(row);
        }

        doSomeThing = new AbstractAction()
        {
            @Override
            public
                    void actionPerformed(ActionEvent e)
            {
                id = Integer.parseInt(mm.getValueAt(debtTable, 0));

                updateCreditAndDebt.inflateUI(
                        id,
                        Integer.parseInt(mm.getValueAt(debtTable, 1)),
                        (Date) debtTable.getValueAt(debtTable.getSelectedRow(), 2),
                        mm.getValueAt(debtTable, 3),
                        "Debt");

                updateCreditAndDebt.dispose();

                d_debt();

                if (tableName == salesCustTable)
                {
                    columnIndex = 3;
                }
                else
                {
                    columnIndex = 2;
                }

                setTotalCreditForSpecificCustomer(tableName, columnIndex);

                if (tableName == salesCustTable)
                {
                    salesCustTotalsTable.setValueAt(
                            getJTableColumnTotal(salesCustTable, 3), 0, 3);
                }
            }
        };

        tableButton = new TableButton(debtTable, doSomeThing, 4);
    }

    void d_debt()
    {
        mm.refrichTable(debtTable);
        displayDebtTransactions();
    }

// end of load debt block
// start of load bigCylinders blodk
    void displayBigCylindersTransactions()
    {
//        setTableName();

        bigCylindersList = handler.getbigCylindersList(Integer.parseInt(mm.getValueAt(purchaseCustTable, 0)), "'" + from + "' and '" + to + "'");

        model = mm.getDefaultTableModel(bigCylindersTable);
        row = new Object[7];

        for (int i = 0; i < bigCylindersList.size(); i++)
        {
            row[0] = bigCylindersList.get(i).getId();
            row[1] = bigCylindersList.get(i).getCount();
            row[2] = bigCylindersList.get(i).getUnitPrice();
            row[3] = bigCylindersList.get(i).getDate();
            row[4] = bigCylindersList.get(i).getFromOrTo();
            row[5] = bigCylindersList.get(i).getNotice();
            row[6] = "Edit";

            model.addRow(row);
        }

        doSomeThing = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                id = Integer.parseInt(mm.getValueAt(bigCylindersTable, 0));

                updateBigCylindersTransactions.inflateUI(
                        id,
                        Integer.parseInt(mm.getValueAt(bigCylindersTable, 1)),
                        Integer.parseInt(mm.getValueAt(bigCylindersTable, 2)),
                        (Date) bigCylindersTable.getValueAt(bigCylindersTable.getSelectedRow(), 3),
                        mm.getValueAt(bigCylindersTable, 4),
                        mm.getValueAt(bigCylindersTable, 5));

                updateBigCylindersTransactions.dispose();

                d_bigCylinders();

                bigCylindersLabel.setText( // set total big cylinders
                        Integer.toString(handler.getBigCylindersTotal()));

                setTotalCreditForSpecificCustomer( // set total debt for a customer
                        purchaseCustTable, 2);
            }
        };

        tableButton = new TableButton(bigCylindersTable, doSomeThing, 6);
    }

    void d_bigCylinders()
    {
        mm.refrichTable(bigCylindersTable);
        displayBigCylindersTransactions();
    }

    void displayAccessoriesTransactions()
    {
        accessoriesList = handler.getAccessoriesList(Integer.parseInt(mm.getValueAt(salesCustTable, 0)), "'" + from + "' and '" + to + "'");

        model = mm.getDefaultTableModel(accessoryTable1);
        row = new Object[7];

        for (int i = 0; i < accessoriesList.size(); i++)
        {
            row[0] = accessoriesList.get(i).getId();
            row[1] = accessoriesList.get(i).getName();
            row[2] = accessoriesList.get(i).getCount();
            row[3] = accessoriesList.get(i).getPrice();
            row[4] = accessoriesList.get(i).getCount() * accessoriesList.get(i).getPrice();
            row[5] = accessoriesList.get(i).getDate();
            row[6] = "Edit";

            model.addRow(row);
        }

        doSomeThing = new AbstractAction()
        {
            @Override
            public
                    void actionPerformed(ActionEvent e)
            {
                id = Integer.parseInt(mm.getValueAt(accessoryTable1, 0));

                updateAccessory.inflateUI(
                        id,
                        Integer.parseInt(mm.getValueAt(accessoryTable1, 2)),
                        Integer.parseInt(mm.getValueAt(accessoryTable1, 3)),
                        (Date) accessoryTable1.getValueAt(accessoryTable1.getSelectedRow(), 5),
                        mm.getValueAt(accessoryTable1, 1));

                updateAccessory.dispose();

                d_accessories();

                setTotalCreditForSpecificCustomer(salesCustTable, 3);

                setSalesCustomersTotals();
            }
        };

        tableButton = new TableButton(accessoryTable1, doSomeThing, 6);
    }

    void d_accessories()
    {
        mm.refrichTable(accessoryTable1);
        displayAccessoriesTransactions();
    }

    // </editor-fold>
    
    // calculate totals
    // <editor-fold>
    void setCylindersTotals()
    {
        purchaseLabel.setText(Integer.toString(handler.getTotalPurchase()));
        inTheStoreLabel.setText(Integer.toString(handler.getTotalInTheStore()));
        availableLabel.setText(Integer.toString(handler.getTotalAvailabe()));
        refilledLabel.setText(Integer.toString(handler.getTotalRefilled()));
        bigCylindersLabel.setText(Integer.toString(handler.getBigCylindersTotal()));
        totalOutBetweenLabel.setText(Integer.toString(handler.getTotalCylindersOutBetween(from, to)));
    }

    int getJTableColumnTotal(JTable tableName1, int column)
    {
        total = 0;

        for (int i = 0; i < tableName1.getRowCount(); i++)
        {
            total = total + (Integer) tableName1.getValueAt(i, column);
        }

        return total;
    }

    void setSalesCustomersTotals()
    {
        salesCustTotalsTable.setValueAt(salesCustTable.getRowCount(), 0, 1);
        salesCustTotalsTable.setValueAt(getJTableColumnTotal(salesCustTable, 2), 0, 2);
        salesCustTotalsTable.setValueAt(getJTableColumnTotal(salesCustTable, 3), 0, 3);
    }

    void setTotalCreditForAllCustomer()
    {
        for (int i = 0; i < salesCustTable.getRowCount(); i++)
        {
            salesCustTable.setValueAt(handler.getTotalDebtForSpecificCustomer((int) salesCustTable.getValueAt(i, 0)), i, 3);
        }

        for (int i = 0; i < purchaseCustTable.getRowCount(); i++)
        {
            purchaseCustTable.setValueAt(handler.getTotalDebtForSpecificCustomer((int) purchaseCustTable.getValueAt(i, 0)), i, 2);
        }
    }

    void setTotalRemainingCylindersForAllCustomer()
    {
        for (int i = 0; i < salesCustTable.getRowCount(); i++)
        {
            salesCustTable.setValueAt(
                    handler.getTotalRemainingCylindersForSpecificCustomer((int) salesCustTable.getValueAt(i, 0)),
                    i,
                    2);
        }
    }

    void setTotalRemainingCylindersForSpecificCustomer()
    {
        salesCustTable.setValueAt(
                handler.getTotalRemainingCylindersForSpecificCustomer((int) salesCustTable.getValueAt(salesCustTable.getSelectedRow(), 0)),
                salesCustTable.getSelectedRow(),
                2);
    }

    void setTotalCreditForSpecificCustomer(JTable tableName, int column)
    {
        tableName.setValueAt(
                handler.getTotalDebtForSpecificCustomer((int) tableName.getValueAt(tableName.getSelectedRow(), 0)), tableName.getSelectedRow(), column);
    }

    void setOutTotals()
    {
        count = 0;
        price = 0;

        for (int i = 0; i < outTable.getRowCount(); i++)
        {
            count = count + (int) outTable.getValueAt(i, 1);

            price = price + (int) outTable.getValueAt(i, 3);
        }

        outTotalsTable.setValueAt(count, 0, 1);
        outTotalsTable.setValueAt(price, 0, 3);
    }

    // </editor-fold>
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        mainPanel = new javax.swing.JPanel();
        customerPanel = new javax.swing.JPanel();
        salesCustScrollPane = new javax.swing.JScrollPane();
        salesCustTable = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        custFilterTXT = new javax.swing.JTextField();
        purchasesCustScrollPane1 = new javax.swing.JScrollPane();
        purchaseCustTable = new javax.swing.JTable();
        customerNameTXT = new javax.swing.JTextField();
        cutomerTypeCB = new javax.swing.JComboBox();
        addCustomerBTN = new javax.swing.JButton();
        salesCustTotalsTable = new javax.swing.JTable();
        totalsPanel = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        inTheStoreLabel = new javax.swing.JLabel();
        purchaseLabel = new javax.swing.JLabel();
        refilledLabel = new javax.swing.JLabel();
        availableLabel = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        bigCylindersLabel = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel14 = new javax.swing.JLabel();
        jSeparator3 = new javax.swing.JSeparator();
        treasuryButton = new javax.swing.JButton();
        jLabel15 = new javax.swing.JLabel();
        totalOutBetweenLabel = new javax.swing.JLabel();
        cardPanel = new javax.swing.JPanel();
        overviewPanel = new javax.swing.JPanel();
        bigCylindersPanel = new javax.swing.JPanel();
        JScrollPane7 = new javax.swing.JScrollPane();
        bigCylindersTable = new javax.swing.JTable();
        bigCylinders_countTXT = new javax.swing.JTextField();
        bigCylinders_unitpriceTXT2 = new javax.swing.JTextField();
        addBigCylindersTransactionBTN2 = new javax.swing.JButton();
        bigCylinders_jDateChooser2 = new com.toedter.calendar.JDateChooser();
        bigCylinders_jComboBox1 = new javax.swing.JComboBox();
        bigCylinders_noticeTXT1 = new javax.swing.JTextField();
        purchasePanel = new javax.swing.JPanel();
        JScrollPane1 = new javax.swing.JScrollPane();
        purchaseTable = new javax.swing.JTable();
        purchaseCountTXT = new javax.swing.JTextField();
        purchase_noticeTXT1 = new javax.swing.JTextField();
        addPurchaseBTN1 = new javax.swing.JButton();
        purchase_jDateChooser1 = new com.toedter.calendar.JDateChooser();
        refilledPanel = new javax.swing.JPanel();
        JScrollPane4 = new javax.swing.JScrollPane();
        refilledTable = new javax.swing.JTable();
        refilledCountTXT1 = new javax.swing.JTextField();
        addRefilledBTN2 = new javax.swing.JButton();
        refilled_jDateChooser = new com.toedter.calendar.JDateChooser();
        inPanel = new javax.swing.JPanel();
        JScrollPane3 = new javax.swing.JScrollPane();
        inTable = new javax.swing.JTable();
        inCountTXT = new javax.swing.JTextField();
        inNoticeTXT = new javax.swing.JTextField();
        addInBTN1 = new javax.swing.JButton();
        in_jDateChooser1 = new com.toedter.calendar.JDateChooser();
        creditPanel = new javax.swing.JPanel();
        JScrollPane5 = new javax.swing.JScrollPane();
        creditTable = new javax.swing.JTable();
        addCreditBTN = new javax.swing.JButton();
        creditTXT = new javax.swing.JTextField();
        credit_descriptionTXT = new javax.swing.JTextField();
        credit_jDateChooser = new com.toedter.calendar.JDateChooser();
        debtPanel = new javax.swing.JPanel();
        JScrollPane6 = new javax.swing.JScrollPane();
        debtTable = new javax.swing.JTable();
        debtTXT = new javax.swing.JTextField();
        debt_descriptionTXT1 = new javax.swing.JTextField();
        addDebtBTN1 = new javax.swing.JButton();
        debt_jDateChooser1 = new com.toedter.calendar.JDateChooser();
        outPanel = new javax.swing.JPanel();
        JScrollPane2 = new javax.swing.JScrollPane();
        outTable = new javax.swing.JTable();
        outCountTXT1 = new javax.swing.JTextField();
        outNoticeTXT1 = new javax.swing.JTextField();
        addOutBTN2 = new javax.swing.JButton();
        outUnitpriceTXT2 = new javax.swing.JTextField();
        outreceiptNumberTXT3 = new javax.swing.JTextField();
        out_jDateChooser2 = new com.toedter.calendar.JDateChooser();
        outTotalsTable = new javax.swing.JTable();
        accessoriesPanel = new javax.swing.JPanel();
        accessoriesPriceTXT1 = new javax.swing.JTextField();
        accessoriesCountTXT = new javax.swing.JTextField();
        accessories_jDateChooser2 = new com.toedter.calendar.JDateChooser();
        addAccessoriesBTN2 = new javax.swing.JButton();
        accessoriesCombobox = new javax.swing.JComboBox();
        JScrollPane8 = new javax.swing.JScrollPane();
        accessoryTable1 = new javax.swing.JTable();
        deleteAccessoriesBTN3 = new javax.swing.JButton();
        reportsPanel = new javax.swing.JPanel();
        customersReportBTN = new javax.swing.JButton();
        purhcaseReportBTN = new javax.swing.JButton();
        outReportBTN = new javax.swing.JButton();
        inReportBTN = new javax.swing.JButton();
        bigCylindersReportBTN = new javax.swing.JButton();
        debtReportBTN = new javax.swing.JButton();
        creidtReportBTN = new javax.swing.JButton();
        accessoriesReportBTN = new javax.swing.JButton();
        refilledReportBTN = new javax.swing.JButton();
        LimitedCB = new javax.swing.JCheckBox();
        allDebtTransactionBTN = new javax.swing.JButton();
        allCreditTransactionBTN1 = new javax.swing.JButton();
        allAccessoriesTransactionBTN2 = new javax.swing.JButton();
        invoiceBTN = new javax.swing.JButton();
        refilledBetween = new javax.swing.JButton();
        allOutBetween = new javax.swing.JButton();
        catygoriesScrollBar = new javax.swing.JScrollPane();
        categoriesPanel = new javax.swing.JPanel();
        overviewBTN = new javax.swing.JButton();
        purchaseBTN = new javax.swing.JButton();
        inBTN = new javax.swing.JButton();
        refilledBTN = new javax.swing.JButton();
        creditBTN = new javax.swing.JButton();
        debtBTN = new javax.swing.JButton();
        outBTN = new javax.swing.JButton();
        damagedBTN = new javax.swing.JButton();
        bigCylindersBTN = new javax.swing.JButton();
        accessoriesBTN = new javax.swing.JButton();
        settingsBTN = new javax.swing.JButton();
        reportsBTN1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("ICS-Cylinders");

        salesCustTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        salesCustTable.setForeground(new java.awt.Color(0, 0, 204));
        salesCustTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Remaining Cylinders", "Debt", "Cylinder Unitprice", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        salesCustTable.setRowHeight(35);
        salesCustTable.getTableHeader().setReorderingAllowed(false);
        salesCustTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                salesCustTableMouseClicked(evt);
            }
        });
        salesCustTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                salesCustTableKeyReleased(evt);
            }
        });
        salesCustScrollPane.setViewportView(salesCustTable);
        if (salesCustTable.getColumnModel().getColumnCount() > 0) {
            salesCustTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            salesCustTable.getColumnModel().getColumn(1).setPreferredWidth(250);
            salesCustTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            salesCustTable.getColumnModel().getColumn(3).setPreferredWidth(75);
        }

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Search");

        custFilterTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                custFilterTXTKeyReleased(evt);
            }
        });

        purchaseCustTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        purchaseCustTable.setForeground(new java.awt.Color(0, 0, 204));
        purchaseCustTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Name", "Debt", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        purchaseCustTable.setRowHeight(35);
        purchaseCustTable.getTableHeader().setReorderingAllowed(false);
        purchaseCustTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                purchaseCustTableMouseClicked(evt);
            }
        });
        purchaseCustTable.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                purchaseCustTableKeyReleased(evt);
            }
        });
        purchasesCustScrollPane1.setViewportView(purchaseCustTable);
        if (purchaseCustTable.getColumnModel().getColumnCount() > 0) {
            purchaseCustTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            purchaseCustTable.getColumnModel().getColumn(1).setPreferredWidth(250);
            purchaseCustTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            purchaseCustTable.getColumnModel().getColumn(3).setPreferredWidth(8);
        }

        customerNameTXT.setForeground(new java.awt.Color(153, 153, 153));
        customerNameTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        customerNameTXT.setText("Name");
        customerNameTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                customerNameTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                customerNameTXTFocusLost(evt);
            }
        });
        customerNameTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                customerNameTXTKeyPressed(evt);
            }
        });

        cutomerTypeCB.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "sales", "purchases" }));
        cutomerTypeCB.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                cutomerTypeCBKeyPressed(evt);
            }
        });

        addCustomerBTN.setText("add");
        addCustomerBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCustomerBTNActionPerformed(evt);
            }
        });

        salesCustTotalsTable.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        salesCustTotalsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Total", null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "null"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        salesCustTotalsTable.setRowHeight(25);

        javax.swing.GroupLayout customerPanelLayout = new javax.swing.GroupLayout(customerPanel);
        customerPanel.setLayout(customerPanelLayout);
        customerPanelLayout.setHorizontalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(salesCustScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                    .addComponent(purchasesCustScrollPane1)
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(custFilterTXT))
                    .addGroup(customerPanelLayout.createSequentialGroup()
                        .addComponent(customerNameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cutomerTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addCustomerBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(salesCustTotalsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        customerPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {customerNameTXT, cutomerTypeCB});

        customerPanelLayout.setVerticalGroup(
            customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(custFilterTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(customerPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(customerNameTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cutomerTypeCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addCustomerBTN))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salesCustScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(salesCustTotalsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(purchasesCustScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        customerPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addCustomerBTN, customerNameTXT, cutomerTypeCB});

        if (salesCustTotalsTable.getColumnModel().getColumnCount() > 0) {
            salesCustTotalsTable.getColumnModel().getColumn(0).setResizable(false);
            salesCustTotalsTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            salesCustTotalsTable.getColumnModel().getColumn(1).setResizable(false);
            salesCustTotalsTable.getColumnModel().getColumn(1).setPreferredWidth(250);
            salesCustTotalsTable.getColumnModel().getColumn(2).setResizable(false);
            salesCustTotalsTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            salesCustTotalsTable.getColumnModel().getColumn(3).setResizable(false);
            salesCustTotalsTable.getColumnModel().getColumn(3).setPreferredWidth(75);
            salesCustTotalsTable.getColumnModel().getColumn(4).setResizable(false);
            salesCustTotalsTable.getColumnModel().getColumn(5).setResizable(false);
        }

        totalsPanel.setBackground(new java.awt.Color(0, 0, 153));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(204, 204, 255));
        jLabel1.setText("Purchase: ");

        jLabel3.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 255));
        jLabel3.setText("In The Store: ");

        inTheStoreLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        inTheStoreLabel.setForeground(new java.awt.Color(204, 204, 255));
        inTheStoreLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        inTheStoreLabel.setText("jLabel1");

        purchaseLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        purchaseLabel.setForeground(new java.awt.Color(204, 204, 255));
        purchaseLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        purchaseLabel.setText("jLabel1");

        refilledLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        refilledLabel.setForeground(new java.awt.Color(204, 204, 255));
        refilledLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        refilledLabel.setText("jLabel1");

        availableLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        availableLabel.setForeground(new java.awt.Color(204, 204, 255));
        availableLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        availableLabel.setText("jLabel1");

        jLabel8.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(204, 204, 255));
        jLabel8.setText("Available: ");

        jLabel9.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(204, 204, 255));
        jLabel9.setText("Refilled: ");

        bigCylindersLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        bigCylindersLabel.setForeground(new java.awt.Color(204, 204, 255));
        bigCylindersLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bigCylindersLabel.setText("jLabel1");

        jLabel13.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(204, 204, 255));
        jLabel13.setText("Big Cylinders: ");

        jSeparator1.setOpaque(true);

        jSeparator2.setOpaque(true);

        jLabel14.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("Total");

        jSeparator3.setOpaque(true);

        treasuryButton.setFont(new java.awt.Font("Dialog", 1, 13)); // NOI18N
        treasuryButton.setText("Treasury");
        treasuryButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                treasuryButtonActionPerformed(evt);
            }
        });

        jLabel15.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(204, 204, 255));
        jLabel15.setText("Total Out Between: ");

        totalOutBetweenLabel.setFont(new java.awt.Font("Times New Roman", 1, 12)); // NOI18N
        totalOutBetweenLabel.setForeground(new java.awt.Color(204, 204, 255));
        totalOutBetweenLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalOutBetweenLabel.setText("jLabel1");
        totalOutBetweenLabel.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        javax.swing.GroupLayout totalsPanelLayout = new javax.swing.GroupLayout(totalsPanel);
        totalsPanel.setLayout(totalsPanelLayout);
        totalsPanelLayout.setHorizontalGroup(
            totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, totalsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 147, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(purchaseLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(inTheStoreLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(availableLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 112, Short.MAX_VALUE)
                    .addComponent(refilledLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(totalsPanelLayout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(bigCylindersLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 1, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                    .addGroup(totalsPanelLayout.createSequentialGroup()
                        .addGap(13, 13, 13)
                        .addComponent(jLabel15, javax.swing.GroupLayout.DEFAULT_SIZE, 153, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalOutBetweenLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 113, Short.MAX_VALUE)
                        .addGap(13, 13, 13)))
                .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(treasuryButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        totalsPanelLayout.setVerticalGroup(
            totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(totalsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(totalsPanelLayout.createSequentialGroup()
                        .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(bigCylindersLabel)
                            .addComponent(jLabel13))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(totalOutBetweenLabel)
                            .addComponent(jLabel15)))
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(totalsPanelLayout.createSequentialGroup()
                            .addComponent(purchaseLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(inTheStoreLabel))
                        .addGroup(totalsPanelLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel3)))
                    .addGroup(totalsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(totalsPanelLayout.createSequentialGroup()
                            .addComponent(availableLabel)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(refilledLabel))
                        .addGroup(totalsPanelLayout.createSequentialGroup()
                            .addComponent(jLabel8)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(jLabel9)))
                    .addGroup(totalsPanelLayout.createSequentialGroup()
                        .addComponent(jLabel14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(treasuryButton, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cardPanel.setLayout(new java.awt.CardLayout());

        javax.swing.GroupLayout overviewPanelLayout = new javax.swing.GroupLayout(overviewPanel);
        overviewPanel.setLayout(overviewPanelLayout);
        overviewPanelLayout.setHorizontalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 938, Short.MAX_VALUE)
        );
        overviewPanelLayout.setVerticalGroup(
            overviewPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );

        cardPanel.add(overviewPanel, "card2");

        bigCylindersTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        bigCylindersTable.setForeground(new java.awt.Color(0, 0, 204));
        bigCylindersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Count", "UnitPrice", "Date", "From_Or_To", "Notice", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        bigCylindersTable.setRowHeight(35);
        bigCylindersTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane7.setViewportView(bigCylindersTable);

        bigCylinders_countTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bigCylinders_countTXT.setForeground(new java.awt.Color(153, 153, 153));
        bigCylinders_countTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bigCylinders_countTXT.setText("Count");
        bigCylinders_countTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bigCylinders_countTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                bigCylinders_countTXTFocusLost(evt);
            }
        });
        bigCylinders_countTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bigCylinders_countTXTKeyPressed(evt);
            }
        });

        bigCylinders_unitpriceTXT2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bigCylinders_unitpriceTXT2.setForeground(new java.awt.Color(153, 153, 153));
        bigCylinders_unitpriceTXT2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bigCylinders_unitpriceTXT2.setText("Unitprice");
        bigCylinders_unitpriceTXT2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bigCylinders_unitpriceTXT2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                bigCylinders_unitpriceTXT2FocusLost(evt);
            }
        });
        bigCylinders_unitpriceTXT2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bigCylinders_unitpriceTXT2KeyPressed(evt);
            }
        });

        addBigCylindersTransactionBTN2.setText("add");
        addBigCylindersTransactionBTN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addBigCylindersTransactionBTN2ActionPerformed(evt);
            }
        });

        bigCylinders_jDateChooser2.setDateFormatString("yyyy-MM-dd");
        bigCylinders_jDateChooser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bigCylinders_jDateChooser2KeyPressed(evt);
            }
        });

        bigCylinders_jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "from", "to" }));
        bigCylinders_jComboBox1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bigCylinders_jComboBox1KeyPressed(evt);
            }
        });

        bigCylinders_noticeTXT1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        bigCylinders_noticeTXT1.setForeground(new java.awt.Color(153, 153, 153));
        bigCylinders_noticeTXT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        bigCylinders_noticeTXT1.setText("Notice");
        bigCylinders_noticeTXT1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                bigCylinders_noticeTXT1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                bigCylinders_noticeTXT1FocusLost(evt);
            }
        });
        bigCylinders_noticeTXT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                bigCylinders_noticeTXT1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout bigCylindersPanelLayout = new javax.swing.GroupLayout(bigCylindersPanel);
        bigCylindersPanel.setLayout(bigCylindersPanelLayout);
        bigCylindersPanelLayout.setHorizontalGroup(
            bigCylindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bigCylindersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bigCylindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JScrollPane7)
                    .addGroup(bigCylindersPanelLayout.createSequentialGroup()
                        .addGroup(bigCylindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(bigCylinders_noticeTXT1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bigCylindersPanelLayout.createSequentialGroup()
                                .addComponent(bigCylinders_countTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bigCylinders_unitpriceTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(bigCylindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(bigCylindersPanelLayout.createSequentialGroup()
                                .addComponent(bigCylinders_jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 417, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(bigCylinders_jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(addBigCylindersTransactionBTN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap())
        );
        bigCylindersPanelLayout.setVerticalGroup(
            bigCylindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, bigCylindersPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(bigCylindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bigCylindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(bigCylinders_countTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(bigCylinders_unitpriceTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(bigCylinders_jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bigCylinders_jComboBox1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bigCylindersPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(bigCylinders_noticeTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(addBigCylindersTransactionBTN2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 643, Short.MAX_VALUE))
        );

        bigCylindersPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {addBigCylindersTransactionBTN2, bigCylinders_countTXT, bigCylinders_jComboBox1, bigCylinders_jDateChooser2, bigCylinders_noticeTXT1, bigCylinders_unitpriceTXT2});

        cardPanel.add(bigCylindersPanel, "card6");

        purchaseTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        purchaseTable.setForeground(new java.awt.Color(0, 0, 204));
        purchaseTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Count", "Date", "Notice", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        purchaseTable.setRowHeight(35);
        purchaseTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane1.setViewportView(purchaseTable);

        purchaseCountTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        purchaseCountTXT.setForeground(new java.awt.Color(153, 153, 153));
        purchaseCountTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        purchaseCountTXT.setText("Count");
        purchaseCountTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                purchaseCountTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                purchaseCountTXTFocusLost(evt);
            }
        });
        purchaseCountTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                purchaseCountTXTKeyPressed(evt);
            }
        });

        purchase_noticeTXT1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        purchase_noticeTXT1.setForeground(new java.awt.Color(153, 153, 153));
        purchase_noticeTXT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        purchase_noticeTXT1.setText("Notice");
        purchase_noticeTXT1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                purchase_noticeTXT1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                purchase_noticeTXT1FocusLost(evt);
            }
        });
        purchase_noticeTXT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                purchase_noticeTXT1KeyPressed(evt);
            }
        });

        addPurchaseBTN1.setText("add");
        addPurchaseBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addPurchaseBTN1ActionPerformed(evt);
            }
        });

        purchase_jDateChooser1.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout purchasePanelLayout = new javax.swing.GroupLayout(purchasePanel);
        purchasePanel.setLayout(purchasePanelLayout);
        purchasePanelLayout.setHorizontalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, purchasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JScrollPane1)
                    .addGroup(purchasePanelLayout.createSequentialGroup()
                        .addComponent(purchaseCountTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchase_noticeTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(purchase_jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addPurchaseBTN1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)))
                .addContainerGap())
        );
        purchasePanelLayout.setVerticalGroup(
            purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, purchasePanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addPurchaseBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(purchasePanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(purchaseCountTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(purchase_noticeTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(purchase_jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))
        );

        cardPanel.add(purchasePanel, "card2");

        refilledTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        refilledTable.setForeground(new java.awt.Color(0, 0, 204));
        refilledTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Count", "Date", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        refilledTable.setRowHeight(35);
        refilledTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane4.setViewportView(refilledTable);

        refilledCountTXT1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        refilledCountTXT1.setForeground(new java.awt.Color(153, 153, 153));
        refilledCountTXT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        refilledCountTXT1.setText("Count");
        refilledCountTXT1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                refilledCountTXT1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                refilledCountTXT1FocusLost(evt);
            }
        });
        refilledCountTXT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                refilledCountTXT1KeyPressed(evt);
            }
        });

        addRefilledBTN2.setText("add");
        addRefilledBTN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addRefilledBTN2ActionPerformed(evt);
            }
        });

        refilled_jDateChooser.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout refilledPanelLayout = new javax.swing.GroupLayout(refilledPanel);
        refilledPanel.setLayout(refilledPanelLayout);
        refilledPanelLayout.setHorizontalGroup(
            refilledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, refilledPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(refilledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JScrollPane4)
                    .addGroup(refilledPanelLayout.createSequentialGroup()
                        .addComponent(refilledCountTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refilled_jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addRefilledBTN2, javax.swing.GroupLayout.DEFAULT_SIZE, 561, Short.MAX_VALUE)))
                .addContainerGap())
        );
        refilledPanelLayout.setVerticalGroup(
            refilledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, refilledPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(refilledPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addRefilledBTN2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refilledCountTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refilled_jDateChooser, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))
        );

        cardPanel.add(refilledPanel, "card2");

        inTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        inTable.setForeground(new java.awt.Color(0, 0, 204));
        inTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Count", "Date", "Notice", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        inTable.setRowHeight(35);
        inTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane3.setViewportView(inTable);
        if (inTable.getColumnModel().getColumnCount() > 0) {
            inTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            inTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            inTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            inTable.getColumnModel().getColumn(3).setPreferredWidth(400);
            inTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        inCountTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        inCountTXT.setForeground(new java.awt.Color(153, 153, 153));
        inCountTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inCountTXT.setText("Count");
        inCountTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inCountTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inCountTXTFocusLost(evt);
            }
        });
        inCountTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inCountTXTKeyPressed(evt);
            }
        });

        inNoticeTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        inNoticeTXT.setForeground(new java.awt.Color(153, 153, 153));
        inNoticeTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        inNoticeTXT.setText("Notice");
        inNoticeTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                inNoticeTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                inNoticeTXTFocusLost(evt);
            }
        });
        inNoticeTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                inNoticeTXTKeyPressed(evt);
            }
        });

        addInBTN1.setText("add");
        addInBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addInBTN1ActionPerformed(evt);
            }
        });

        in_jDateChooser1.setDateFormatString("yyyy-MM-dd");

        javax.swing.GroupLayout inPanelLayout = new javax.swing.GroupLayout(inPanel);
        inPanel.setLayout(inPanelLayout);
        inPanelLayout.setHorizontalGroup(
            inPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JScrollPane3)
                    .addGroup(inPanelLayout.createSequentialGroup()
                        .addComponent(inCountTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(inNoticeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(in_jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addInBTN1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)))
                .addContainerGap())
        );
        inPanelLayout.setVerticalGroup(
            inPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, inPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(inPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addInBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(inPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(inCountTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(inNoticeTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(in_jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(9, 9, 9)
                .addComponent(JScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 675, Short.MAX_VALUE))
        );

        cardPanel.add(inPanel, "card2");

        creditTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        creditTable.setForeground(new java.awt.Color(0, 0, 204));
        creditTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Credit", "Date", "Description", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        creditTable.setRowHeight(35);
        creditTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane5.setViewportView(creditTable);
        if (creditTable.getColumnModel().getColumnCount() > 0) {
            creditTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            creditTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            creditTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            creditTable.getColumnModel().getColumn(3).setPreferredWidth(400);
            creditTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        addCreditBTN.setText("add");
        addCreditBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCreditBTNActionPerformed(evt);
            }
        });

        creditTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        creditTXT.setForeground(new java.awt.Color(153, 153, 153));
        creditTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        creditTXT.setText("Credit");
        creditTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                creditTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                creditTXTFocusLost(evt);
            }
        });
        creditTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                creditTXTKeyPressed(evt);
            }
        });

        credit_descriptionTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        credit_descriptionTXT.setForeground(new java.awt.Color(153, 153, 153));
        credit_descriptionTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        credit_descriptionTXT.setText("Description");
        credit_descriptionTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                credit_descriptionTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                credit_descriptionTXTFocusLost(evt);
            }
        });
        credit_descriptionTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                credit_descriptionTXTKeyPressed(evt);
            }
        });

        credit_jDateChooser.setDateFormatString("yyyy-MM-dd");
        credit_jDateChooser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                credit_jDateChooserKeyPressed(evt);
            }
        });

        javax.swing.GroupLayout creditPanelLayout = new javax.swing.GroupLayout(creditPanel);
        creditPanel.setLayout(creditPanelLayout);
        creditPanelLayout.setHorizontalGroup(
            creditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(creditPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(creditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPane5)
                    .addGroup(creditPanelLayout.createSequentialGroup()
                        .addComponent(creditTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(credit_descriptionTXT, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(credit_jDateChooser, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addCreditBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 558, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        creditPanelLayout.setVerticalGroup(
            creditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, creditPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(creditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addCreditBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(creditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(credit_jDateChooser, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, creditPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(creditTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(credit_descriptionTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))
        );

        cardPanel.add(creditPanel, "card2");

        debtTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        debtTable.setForeground(new java.awt.Color(0, 0, 204));
        debtTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Debt", "Date", "Description", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        debtTable.setRowHeight(35);
        debtTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane6.setViewportView(debtTable);
        if (debtTable.getColumnModel().getColumnCount() > 0) {
            debtTable.getColumnModel().getColumn(0).setPreferredWidth(50);
            debtTable.getColumnModel().getColumn(1).setPreferredWidth(50);
            debtTable.getColumnModel().getColumn(2).setPreferredWidth(50);
            debtTable.getColumnModel().getColumn(3).setPreferredWidth(400);
            debtTable.getColumnModel().getColumn(4).setPreferredWidth(50);
        }

        debtTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        debtTXT.setForeground(new java.awt.Color(153, 153, 153));
        debtTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        debtTXT.setText("Debt");
        debtTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                debtTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                debtTXTFocusLost(evt);
            }
        });
        debtTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debtTXTKeyPressed(evt);
            }
        });

        debt_descriptionTXT1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        debt_descriptionTXT1.setForeground(new java.awt.Color(153, 153, 153));
        debt_descriptionTXT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        debt_descriptionTXT1.setText("Description");
        debt_descriptionTXT1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                debt_descriptionTXT1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                debt_descriptionTXT1FocusLost(evt);
            }
        });
        debt_descriptionTXT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debt_descriptionTXT1KeyPressed(evt);
            }
        });

        addDebtBTN1.setText("add");
        addDebtBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addDebtBTN1ActionPerformed(evt);
            }
        });

        debt_jDateChooser1.setDateFormatString("yyyy-MM-dd");
        debt_jDateChooser1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                debt_jDateChooser1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout debtPanelLayout = new javax.swing.GroupLayout(debtPanel);
        debtPanel.setLayout(debtPanelLayout);
        debtPanelLayout.setHorizontalGroup(
            debtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, debtPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(debtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(JScrollPane6)
                    .addGroup(debtPanelLayout.createSequentialGroup()
                        .addComponent(debtTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(debt_descriptionTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(debt_jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addDebtBTN1, javax.swing.GroupLayout.DEFAULT_SIZE, 558, Short.MAX_VALUE)))
                .addContainerGap())
        );
        debtPanelLayout.setVerticalGroup(
            debtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, debtPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(debtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(addDebtBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(debtPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(debtTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(debt_descriptionTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(debt_jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 678, Short.MAX_VALUE))
        );

        cardPanel.add(debtPanel, "card2");

        outTable.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        outTable.setForeground(new java.awt.Color(0, 0, 204));
        outTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Count", "Unit Price", "Total", "Date", "Receipt Number", "Noticel", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        outTable.setRowHeight(35);
        outTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane2.setViewportView(outTable);
        if (outTable.getColumnModel().getColumnCount() > 0) {
            outTable.getColumnModel().getColumn(5).setHeaderValue("Receipt Number");
            outTable.getColumnModel().getColumn(6).setHeaderValue("Noticel");
        }

        outCountTXT1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        outCountTXT1.setForeground(new java.awt.Color(153, 153, 153));
        outCountTXT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        outCountTXT1.setText("Count");
        outCountTXT1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                outCountTXT1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                outCountTXT1FocusLost(evt);
            }
        });
        outCountTXT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                outCountTXT1KeyPressed(evt);
            }
        });

        outNoticeTXT1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        outNoticeTXT1.setForeground(new java.awt.Color(153, 153, 153));
        outNoticeTXT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        outNoticeTXT1.setText("Notice");
        outNoticeTXT1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                outNoticeTXT1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                outNoticeTXT1FocusLost(evt);
            }
        });
        outNoticeTXT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                outNoticeTXT1KeyPressed(evt);
            }
        });

        addOutBTN2.setText("add");
        addOutBTN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addOutBTN2ActionPerformed(evt);
            }
        });

        outUnitpriceTXT2.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        outUnitpriceTXT2.setForeground(new java.awt.Color(153, 153, 153));
        outUnitpriceTXT2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        outUnitpriceTXT2.setText("Unitprice");
        outUnitpriceTXT2.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                outUnitpriceTXT2FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                outUnitpriceTXT2FocusLost(evt);
            }
        });
        outUnitpriceTXT2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                outUnitpriceTXT2KeyPressed(evt);
            }
        });

        outreceiptNumberTXT3.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        outreceiptNumberTXT3.setForeground(new java.awt.Color(153, 153, 153));
        outreceiptNumberTXT3.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        outreceiptNumberTXT3.setText("Receipt Number");
        outreceiptNumberTXT3.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                outreceiptNumberTXT3FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                outreceiptNumberTXT3FocusLost(evt);
            }
        });
        outreceiptNumberTXT3.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                outreceiptNumberTXT3KeyPressed(evt);
            }
        });

        out_jDateChooser2.setDateFormatString("yyyy-MM-dd");
        out_jDateChooser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                out_jDateChooser2KeyPressed(evt);
            }
        });

        outTotalsTable.setFont(new java.awt.Font("Serif", 1, 12)); // NOI18N
        outTotalsTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {"Total", null, null, null, null, null, null, null}
            },
            new String [] {
                "ID", "Count", "Unit Price", "Total", "Date", "Receipt Number", "Noticel", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        outTotalsTable.setRowHeight(25);
        outTotalsTable.getTableHeader().setReorderingAllowed(false);

        javax.swing.GroupLayout outPanelLayout = new javax.swing.GroupLayout(outPanel);
        outPanel.setLayout(outPanelLayout);
        outPanelLayout.setHorizontalGroup(
            outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(outPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPane2)
                    .addGroup(outPanelLayout.createSequentialGroup()
                        .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(outPanelLayout.createSequentialGroup()
                                .addComponent(outCountTXT1, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(outUnitpriceTXT2, javax.swing.GroupLayout.DEFAULT_SIZE, 148, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(out_jDateChooser2, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE))
                            .addComponent(outNoticeTXT1, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addOutBTN2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(outreceiptNumberTXT3, javax.swing.GroupLayout.DEFAULT_SIZE, 460, Short.MAX_VALUE)))
                    .addComponent(outTotalsTable, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        outPanelLayout.setVerticalGroup(
            outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, outPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(outCountTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(outUnitpriceTXT2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(outreceiptNumberTXT3)
                    .addComponent(out_jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGroup(outPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addOutBTN2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outNoticeTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outTotalsTable, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        cardPanel.add(outPanel, "card3");

        accessoriesPriceTXT1.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        accessoriesPriceTXT1.setForeground(new java.awt.Color(153, 153, 153));
        accessoriesPriceTXT1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        accessoriesPriceTXT1.setText("Price");
        accessoriesPriceTXT1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                accessoriesPriceTXT1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                accessoriesPriceTXT1FocusLost(evt);
            }
        });
        accessoriesPriceTXT1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                accessoriesPriceTXT1KeyPressed(evt);
            }
        });

        accessoriesCountTXT.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        accessoriesCountTXT.setForeground(new java.awt.Color(153, 153, 153));
        accessoriesCountTXT.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        accessoriesCountTXT.setText("Count");
        accessoriesCountTXT.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                accessoriesCountTXTFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                accessoriesCountTXTFocusLost(evt);
            }
        });
        accessoriesCountTXT.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                accessoriesCountTXTKeyPressed(evt);
            }
        });

        accessories_jDateChooser2.setDateFormatString("yyyy-MM-dd");
        accessories_jDateChooser2.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                accessories_jDateChooser2KeyPressed(evt);
            }
        });

        addAccessoriesBTN2.setText("add");
        addAccessoriesBTN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addAccessoriesBTN2ActionPerformed(evt);
            }
        });

        accessoriesCombobox.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                accessoriesComboboxKeyPressed(evt);
            }
        });

        accessoryTable1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        accessoryTable1.setForeground(new java.awt.Color(0, 0, 204));
        accessoryTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Accessory Type", "Count", "Price", "Total", "Date", ""
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        accessoryTable1.setRowHeight(35);
        accessoryTable1.getTableHeader().setReorderingAllowed(false);
        JScrollPane8.setViewportView(accessoryTable1);

        deleteAccessoriesBTN3.setText("Delete Accessory Type");
        deleteAccessoriesBTN3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteAccessoriesBTN3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout accessoriesPanelLayout = new javax.swing.GroupLayout(accessoriesPanel);
        accessoriesPanel.setLayout(accessoriesPanelLayout);
        accessoriesPanelLayout.setHorizontalGroup(
            accessoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accessoriesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accessoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(JScrollPane8)
                    .addGroup(accessoriesPanelLayout.createSequentialGroup()
                        .addComponent(accessoriesCombobox, 0, 223, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accessoriesCountTXT, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accessoriesPriceTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(accessories_jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addAccessoriesBTN2, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteAccessoriesBTN3, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        accessoriesPanelLayout.setVerticalGroup(
            accessoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(accessoriesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(accessoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(accessoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(addAccessoriesBTN2, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(deleteAccessoriesBTN3, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(accessoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(accessoriesPriceTXT1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(accessoriesCountTXT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(accessoriesCombobox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(accessories_jDateChooser2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(JScrollPane8, javax.swing.GroupLayout.DEFAULT_SIZE, 672, Short.MAX_VALUE)
                .addContainerGap())
        );

        accessoriesPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {accessoriesCombobox, accessoriesCountTXT, accessoriesPriceTXT1, accessories_jDateChooser2, addAccessoriesBTN2, deleteAccessoriesBTN3});

        cardPanel.add(accessoriesPanel, "card10");

        customersReportBTN.setText("Customers");
        customersReportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customersReportBTNActionPerformed(evt);
            }
        });

        purhcaseReportBTN.setText("Purchase");
        purhcaseReportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purhcaseReportBTNActionPerformed(evt);
            }
        });

        outReportBTN.setText("Cylinders Out");
        outReportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outReportBTNActionPerformed(evt);
            }
        });

        inReportBTN.setText("Cylinders In");
        inReportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inReportBTNActionPerformed(evt);
            }
        });

        bigCylindersReportBTN.setText("Big Cylinders");
        bigCylindersReportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bigCylindersReportBTNActionPerformed(evt);
            }
        });

        debtReportBTN.setText("Debt");
        debtReportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debtReportBTNActionPerformed(evt);
            }
        });

        creidtReportBTN.setText("Credit");
        creidtReportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creidtReportBTNActionPerformed(evt);
            }
        });

        accessoriesReportBTN.setText("Accessories");
        accessoriesReportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accessoriesReportBTNActionPerformed(evt);
            }
        });

        refilledReportBTN.setText("Refilled");
        refilledReportBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refilledReportBTNActionPerformed(evt);
            }
        });

        allDebtTransactionBTN.setText("All Debt Transactions");
        allDebtTransactionBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allDebtTransactionBTNActionPerformed(evt);
            }
        });

        allCreditTransactionBTN1.setText("All Credit Transactions");
        allCreditTransactionBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allCreditTransactionBTN1ActionPerformed(evt);
            }
        });

        allAccessoriesTransactionBTN2.setText("All Accessories Transactions");
        allAccessoriesTransactionBTN2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allAccessoriesTransactionBTN2ActionPerformed(evt);
            }
        });

        invoiceBTN.setText("Extract Invoice");
        invoiceBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceBTNActionPerformed(evt);
            }
        });

        refilledBetween.setText("All Refilled Between");
        refilledBetween.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refilledBetweenActionPerformed(evt);
            }
        });

        allOutBetween.setText("All Out Between");
        allOutBetween.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                allOutBetweenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout reportsPanelLayout = new javax.swing.GroupLayout(reportsPanel);
        reportsPanel.setLayout(reportsPanelLayout);
        reportsPanelLayout.setHorizontalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportsPanelLayout.createSequentialGroup()
                        .addComponent(customersReportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(LimitedCB)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(invoiceBTN))
                    .addComponent(purhcaseReportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(outReportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(inReportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bigCylindersReportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(debtReportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(creidtReportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(accessoriesReportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(refilledReportBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allDebtTransactionBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(allCreditTransactionBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(allOutBetween, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(refilledBetween, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(allAccessoriesTransactionBTN2, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 320, Short.MAX_VALUE)))
                .addContainerGap(462, Short.MAX_VALUE))
        );

        reportsPanelLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {accessoriesReportBTN, bigCylindersReportBTN, creidtReportBTN, customersReportBTN, debtReportBTN, inReportBTN, outReportBTN, purhcaseReportBTN, refilledReportBTN});

        reportsPanelLayout.setVerticalGroup(
            reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportsPanelLayout.createSequentialGroup()
                .addGroup(reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportsPanelLayout.createSequentialGroup()
                        .addGap(14, 14, 14)
                        .addGroup(reportsPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(customersReportBTN)
                            .addComponent(LimitedCB)))
                    .addGroup(reportsPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(invoiceBTN)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(purhcaseReportBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(inReportBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(outReportBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(accessoriesReportBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(creidtReportBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(debtReportBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(bigCylindersReportBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refilledReportBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allDebtTransactionBTN)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allCreditTransactionBTN1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allAccessoriesTransactionBTN2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(refilledBetween)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(allOutBetween)
                .addContainerGap(319, Short.MAX_VALUE))
        );

        reportsPanelLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {accessoriesReportBTN, bigCylindersReportBTN, creidtReportBTN, customersReportBTN, debtReportBTN, inReportBTN, outReportBTN, purhcaseReportBTN, refilledReportBTN});

        cardPanel.add(reportsPanel, "card11");

        categoriesPanel.setBackground(new java.awt.Color(45, 52, 71));

        overviewBTN.setBackground(java.awt.Color.blue);
        overviewBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        overviewBTN.setText("Overview");
        overviewBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                overviewBTNActionPerformed(evt);
            }
        });

        purchaseBTN.setBackground(new java.awt.Color(255, 0, 0));
        purchaseBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        purchaseBTN.setText("Purchase");
        purchaseBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                purchaseBTNActionPerformed(evt);
            }
        });

        inBTN.setBackground(new java.awt.Color(255, 0, 0));
        inBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        inBTN.setText("In");
        inBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inBTNActionPerformed(evt);
            }
        });

        refilledBTN.setBackground(new java.awt.Color(255, 0, 0));
        refilledBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        refilledBTN.setText("Refilled");
        refilledBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                refilledBTNActionPerformed(evt);
            }
        });

        creditBTN.setBackground(new java.awt.Color(255, 0, 0));
        creditBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        creditBTN.setText("Credit");
        creditBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                creditBTNActionPerformed(evt);
            }
        });

        debtBTN.setBackground(new java.awt.Color(255, 0, 0));
        debtBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        debtBTN.setText("Debt");
        debtBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                debtBTNActionPerformed(evt);
            }
        });

        outBTN.setBackground(new java.awt.Color(255, 0, 0));
        outBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        outBTN.setText("Out");
        outBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                outBTNActionPerformed(evt);
            }
        });

        damagedBTN.setBackground(new java.awt.Color(255, 0, 0));
        damagedBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        damagedBTN.setText("Damaged");
        damagedBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                damagedBTNActionPerformed(evt);
            }
        });

        bigCylindersBTN.setBackground(new java.awt.Color(255, 0, 0));
        bigCylindersBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        bigCylindersBTN.setText("Big Cylinders");
        bigCylindersBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bigCylindersBTNActionPerformed(evt);
            }
        });

        accessoriesBTN.setBackground(new java.awt.Color(255, 0, 0));
        accessoriesBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        accessoriesBTN.setText("Accessories");
        accessoriesBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                accessoriesBTNActionPerformed(evt);
            }
        });

        settingsBTN.setBackground(new java.awt.Color(255, 0, 0));
        settingsBTN.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        settingsBTN.setText("Settings");
        settingsBTN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                settingsBTNActionPerformed(evt);
            }
        });

        reportsBTN1.setBackground(new java.awt.Color(255, 0, 0));
        reportsBTN1.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        reportsBTN1.setText("Reports");
        reportsBTN1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsBTN1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout categoriesPanelLayout = new javax.swing.GroupLayout(categoriesPanel);
        categoriesPanel.setLayout(categoriesPanelLayout);
        categoriesPanelLayout.setHorizontalGroup(
            categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoriesPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(overviewBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(purchaseBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(inBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(refilledBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(creditBTN, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(debtBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(outBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(damagedBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(bigCylindersBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(accessoriesBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(settingsBTN, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE)
                    .addComponent(reportsBTN1, javax.swing.GroupLayout.DEFAULT_SIZE, 124, Short.MAX_VALUE))
                .addContainerGap())
        );
        categoriesPanelLayout.setVerticalGroup(
            categoriesPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(categoriesPanelLayout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(overviewBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(purchaseBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(refilledBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(inBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(outBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(creditBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(debtBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(bigCylindersBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(damagedBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(accessoriesBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(settingsBTN, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(reportsBTN1, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        catygoriesScrollBar.setViewportView(categoriesPanel);

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(catygoriesScrollBar, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(customerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(totalsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                    .addContainerGap(362, Short.MAX_VALUE)
                    .addComponent(cardPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(customerPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(mainPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(totalsPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(catygoriesScrollBar, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
            .addGroup(mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(mainPanelLayout.createSequentialGroup()
                    .addGap(65, 65, 65)
                    .addComponent(cardPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGap(14, 14, 14)))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(mainPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(0, 0, 0))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void custFilterTXTKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_custFilterTXTKeyReleased
    {//GEN-HEADEREND:event_custFilterTXTKeyReleased
        mm.refrichTable(salesCustTable);
        mm.refrichTable(purchaseCustTable);
        displayCustomers();

        setTotalCreditForAllCustomer();
        setTotalRemainingCylindersForAllCustomer();
    }//GEN-LAST:event_custFilterTXTKeyReleased

    // navigation buttons events
    // <editor-fold>

    private void purchaseBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_purchaseBTNActionPerformed
    {//GEN-HEADEREND:event_purchaseBTNActionPerformed
        setButtonBackground(purchaseBTN);

        // Removing the panel
        cardPanel.removeAll();

        //adding panels
        cardPanel.add(purchasePanel);
        cardPanel.repaint();
        cardPanel.revalidate();

        d_purchase();
    }//GEN-LAST:event_purchaseBTNActionPerformed

    private void refilledBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_refilledBTNActionPerformed
    {//GEN-HEADEREND:event_refilledBTNActionPerformed
        setButtonBackground(refilledBTN);

        // Removing the panel
        cardPanel.removeAll();

        //adding panels
        cardPanel.add(refilledPanel);
        cardPanel.repaint();
        cardPanel.revalidate();

        if (purchaseCustTable.getSelectedRow() >= 0)
        {
            d_refilled();
        }
    }//GEN-LAST:event_refilledBTNActionPerformed

    private void inBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inBTNActionPerformed
    {//GEN-HEADEREND:event_inBTNActionPerformed
        setButtonBackground(inBTN);

        // Removing the panel
        cardPanel.removeAll();

        //adding panels
        cardPanel.add(inPanel);
        cardPanel.repaint();
        cardPanel.revalidate();

        if (salesCustTable.getSelectedRow() >= 0)
        {
            d_in();
        }
    }//GEN-LAST:event_inBTNActionPerformed

    private void outBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_outBTNActionPerformed
    {//GEN-HEADEREND:event_outBTNActionPerformed
        if (outBTN.getBackground() == Color.BLUE)
        {
            dtb.inflateUI(from, to);

            dtb.pack();
            dtb.setLocationRelativeTo(null);
            dtb.setVisible(true);

            dtb.dispose();
        }
        else
        {
            setButtonBackground(outBTN);

            // Removing the panel
            cardPanel.removeAll();

            //adding panels
            cardPanel.add(outPanel);
            cardPanel.repaint();
            cardPanel.revalidate();

            if (salesCustTable.getSelectedRow() >= 0)
            {
                d_out();

            }
        }
    }//GEN-LAST:event_outBTNActionPerformed

    private void creditBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_creditBTNActionPerformed
    {//GEN-HEADEREND:event_creditBTNActionPerformed
        if (creditBTN.getBackground() == Color.BLUE)
        {
            displayCreditTransactionsBetween.inflateUI(from, to);

            displayCreditTransactionsBetween.pack();
            displayCreditTransactionsBetween.setLocationRelativeTo(null);
            displayCreditTransactionsBetween.setVisible(true);

            displayCreditTransactionsBetween.dispose();
        }
        else
        {
            setButtonBackground(creditBTN);

            // Removing the panel
            cardPanel.removeAll();

            //adding panels
            cardPanel.add(creditPanel);
            cardPanel.repaint();
            cardPanel.revalidate();

            if (salesCustTable.getSelectedRow() >= 0 || purchaseCustTable.getSelectedRow() >= 0)
            {
                d_credit();
            }
        }
    }//GEN-LAST:event_creditBTNActionPerformed

    private void debtBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_debtBTNActionPerformed
    {//GEN-HEADEREND:event_debtBTNActionPerformed
        if (debtBTN.getBackground() == Color.BLUE)
        {
            displayDebtTransactionsBetween.inflateUI(from, to);

            displayDebtTransactionsBetween.pack();
            displayDebtTransactionsBetween.setLocationRelativeTo(null);
            displayDebtTransactionsBetween.setVisible(true);

            displayDebtTransactionsBetween.dispose();
        }
        else
        {
            setButtonBackground(debtBTN);

            // Removing the panel
            cardPanel.removeAll();

            //adding panels
            cardPanel.add(debtPanel);
            cardPanel.repaint();
            cardPanel.revalidate();

            if (salesCustTable.getSelectedRow() >= 0 || purchaseCustTable.getSelectedRow() >= 0)
            {
                d_debt();
            }
        }
    }//GEN-LAST:event_debtBTNActionPerformed

    private void overviewBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_overviewBTNActionPerformed
    {//GEN-HEADEREND:event_overviewBTNActionPerformed
        setButtonBackground(overviewBTN);

        // Removing the panel
        cardPanel.removeAll();

        //adding panels
        cardPanel.add(overviewPanel);
        cardPanel.repaint();
        cardPanel.revalidate();
    }//GEN-LAST:event_overviewBTNActionPerformed

    // </editor-fold>
    void setTableName()
    {
        if (salesCustTable.getSelectedRow() < 0)
        {
            tableName = purchaseCustTable;
        }
        else
        {
            tableName = salesCustTable;
        }
    }

    private void salesCustTableMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_salesCustTableMouseClicked
    {//GEN-HEADEREND:event_salesCustTableMouseClicked
        purchaseCustTable.getSelectionModel().clearSelection();

        tableName = salesCustTable;

        if (refilledTable.getRowCount() > 0 || bigCylindersTable.getRowCount() > 0)
        {
            mm.refrichTable(refilledTable);
            mm.refrichTable(bigCylindersTable);
        }

        d_out();
        d_in();
        d_credit();
        d_debt();
        d_accessories();
    }//GEN-LAST:event_salesCustTableMouseClicked

    private void purchaseCustTableMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_purchaseCustTableMouseClicked
    {//GEN-HEADEREND:event_purchaseCustTableMouseClicked
        salesCustTable.getSelectionModel().clearSelection();

        tableName = purchaseCustTable;

        if (outTable.getRowCount() > 0 || inTable.getRowCount() > 0 || accessoryTable1.getRowCount() > 0)
        {
            mm.refrichTable(outTable);
            mm.refrichTable(inTable);
            mm.refrichTable(accessoryTable1);
        }

        d_refilled();
        d_credit();
        d_debt();
        d_bigCylinders();
    }//GEN-LAST:event_purchaseCustTableMouseClicked

    // (cusotomers) components events
    // <editor-fold>

    private void addCustomerBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addCustomerBTNActionPerformed
    {//GEN-HEADEREND:event_addCustomerBTNActionPerformed
        if (customerNameTXT.getText().equals("Name"))
        {
            JOptionPane.showMessageDialog(this, "message", "message", 2);
            return;
        }

        handler.insertOrUpdate("insert into customers (name, type) values ('"
                + customerNameTXT.getText() + "', '"
                + cutomerTypeCB.getSelectedItem().toString() + "')",
                "Customer Inserted Successfully...");

        customerNameTXT.setText("");
        mm.ifFocusGainedAndLost(customerNameTXT, "Name");

        cutomerTypeCB.setSelectedIndex(0);

        displayCustomers();

        salesCustTotalsTable.setValueAt(salesCustTable.getRowCount(), 0, 1);

        setTotalCreditForAllCustomer();
        setTotalRemainingCylindersForAllCustomer();
    }//GEN-LAST:event_addCustomerBTNActionPerformed

    private void customerNameTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_customerNameTXTFocusGained
    {//GEN-HEADEREND:event_customerNameTXTFocusGained
        mm.ifFocusGainedAndLost(customerNameTXT, "Name");
    }//GEN-LAST:event_customerNameTXTFocusGained

    private void customerNameTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_customerNameTXTFocusLost
    {//GEN-HEADEREND:event_customerNameTXTFocusLost
        mm.ifFocusGainedAndLost(customerNameTXT, "Name");
    }//GEN-LAST:event_customerNameTXTFocusLost

    private void customerNameTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_customerNameTXTKeyPressed
    {//GEN-HEADEREND:event_customerNameTXTKeyPressed
        mm.componentRequestFocus(evt, cutomerTypeCB);
    }//GEN-LAST:event_customerNameTXTKeyPressed

    private void cutomerTypeCBKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_cutomerTypeCBKeyPressed
    {//GEN-HEADEREND:event_cutomerTypeCBKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            addCustomerBTNActionPerformed(null);
        }
    }//GEN-LAST:event_cutomerTypeCBKeyPressed

    // </editor-fold>
    // tables key released
    //<editor-fold>

    private void salesCustTableKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_salesCustTableKeyReleased
    {//GEN-HEADEREND:event_salesCustTableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN)
        {
            purchaseCustTable.getSelectionModel().clearSelection();

            setTableName();

            if (refilledTable.getRowCount() > 0 || bigCylindersTable.getRowCount() > 0)
            {
                mm.refrichTable(refilledTable);
                mm.refrichTable(bigCylindersTable);
            }

            d_out();
            d_in();
            d_credit();
            d_debt();
            d_accessories();
        }
    }//GEN-LAST:event_salesCustTableKeyReleased

    private void purchaseCustTableKeyReleased(java.awt.event.KeyEvent evt)//GEN-FIRST:event_purchaseCustTableKeyReleased
    {//GEN-HEADEREND:event_purchaseCustTableKeyReleased
        if (evt.getKeyCode() == KeyEvent.VK_UP || evt.getKeyCode() == KeyEvent.VK_DOWN)
        {
            salesCustTable.getSelectionModel().clearSelection();

            setTableName();

            if (outTable.getRowCount() > 0 || inTable.getRowCount() > 0 || accessoryTable1.getRowCount() > 0)
            {
                mm.refrichTable(outTable);
                mm.refrichTable(inTable);
                mm.refrichTable(accessoryTable1);
            }

            d_refilled();
            d_credit();
            d_debt();
            d_bigCylinders();
        }
    }//GEN-LAST:event_purchaseCustTableKeyReleased

    // </editor-fold>
    // navigation buttons events
    // <editor-fold>

    private void damagedBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_damagedBTNActionPerformed
    {//GEN-HEADEREND:event_damagedBTNActionPerformed
        insertDisabledFrame.pack();
        insertDisabledFrame.setLocationRelativeTo(null);
        insertDisabledFrame.setVisible(true);

        insertDisabledFrame.dispose();

        setCylindersTotals();
    }//GEN-LAST:event_damagedBTNActionPerformed

    private void bigCylindersBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bigCylindersBTNActionPerformed
    {//GEN-HEADEREND:event_bigCylindersBTNActionPerformed
        setButtonBackground(bigCylindersBTN);

        // Removing the panel
        cardPanel.removeAll();

        //adding panels
        cardPanel.add(bigCylindersPanel);
        cardPanel.repaint();
        cardPanel.revalidate();

        if (purchaseCustTable.getSelectedRow() >= 0)
        {
            d_bigCylinders();
        }
    }//GEN-LAST:event_bigCylindersBTNActionPerformed

    // </editor-fold>
    // (big cylunders) components events
    // <editor-fold>

    private void bigCylinders_countTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_bigCylinders_countTXTFocusGained
    {//GEN-HEADEREND:event_bigCylinders_countTXTFocusGained
        mm.ifFocusGainedAndLost(bigCylinders_countTXT, "Count");
    }//GEN-LAST:event_bigCylinders_countTXTFocusGained

    private void bigCylinders_countTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_bigCylinders_countTXTFocusLost
    {//GEN-HEADEREND:event_bigCylinders_countTXTFocusLost
        mm.ifFocusGainedAndLost(bigCylinders_countTXT, "Count");
    }//GEN-LAST:event_bigCylinders_countTXTFocusLost

    private void bigCylinders_countTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_bigCylinders_countTXTKeyPressed
    {//GEN-HEADEREND:event_bigCylinders_countTXTKeyPressed
        mm.componentRequestFocus(evt, bigCylinders_unitpriceTXT2);
    }//GEN-LAST:event_bigCylinders_countTXTKeyPressed

    private void bigCylinders_unitpriceTXT2FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_bigCylinders_unitpriceTXT2FocusGained
    {//GEN-HEADEREND:event_bigCylinders_unitpriceTXT2FocusGained
        mm.ifFocusGainedAndLost(bigCylinders_unitpriceTXT2, "Unitprice");
    }//GEN-LAST:event_bigCylinders_unitpriceTXT2FocusGained

    private void bigCylinders_unitpriceTXT2FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_bigCylinders_unitpriceTXT2FocusLost
    {//GEN-HEADEREND:event_bigCylinders_unitpriceTXT2FocusLost
        mm.ifFocusGainedAndLost(bigCylinders_unitpriceTXT2, "Unitprice");
    }//GEN-LAST:event_bigCylinders_unitpriceTXT2FocusLost

    private void bigCylinders_unitpriceTXT2KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_bigCylinders_unitpriceTXT2KeyPressed
    {//GEN-HEADEREND:event_bigCylinders_unitpriceTXT2KeyPressed
        mm.componentRequestFocus(evt, bigCylinders_jDateChooser2);
    }//GEN-LAST:event_bigCylinders_unitpriceTXT2KeyPressed

    private void addBigCylindersTransactionBTN2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addBigCylindersTransactionBTN2ActionPerformed
    {//GEN-HEADEREND:event_addBigCylindersTransactionBTN2ActionPerformed
        // value credential
        // <editor-fold>

        try
        {
            id = Integer.parseInt(mm.getValueAt(purchaseCustTable, 0));
            count = Integer.parseInt(bigCylinders_countTXT.getText());
            price = Integer.parseInt(bigCylinders_unitpriceTXT2.getText());
        }
        catch (ArrayIndexOutOfBoundsException aex)
        {
            JOptionPane.showMessageDialog(this, "Select Customer", "message", 2);
            return;
        }
        catch (NumberFormatException nex)
        {
            JOptionPane.showMessageDialog(this, "Invalid values..." + nex.getMessage(), "message", 2);
            return;
        }

        if (count <= 0)
        {
            JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
            return;
        }

        // </editor-fold>
        handler.insertOrUpdate("insert into bigcylinders (customer_id, count, date, from_or_to, unitprice, notice) values ("
                + id + ", "
                + count + ", '"
                + ((JTextField) bigCylinders_jDateChooser2.getDateEditor().getUiComponent()).getText() + "', '"
                + bigCylinders_jComboBox1.getSelectedItem().toString() + "', "
                + price + ", '"
                + bigCylinders_noticeTXT1.getText() + "')",
                "Transaction Inserted Successfully...");

        if (bigCylinders_jComboBox1.getSelectedItem().toString().equals("to"))
        {
            bigCylindersLabel.setText( // set totl big cylinders
                    Integer.toString(
                            Integer.parseInt(
                                    bigCylindersLabel.getText()) - count));
        }
        else
        {
            bigCylindersLabel.setText( // set totl big cylinders
                    Integer.toString(
                            Integer.parseInt(
                                    bigCylindersLabel.getText()) + count));
        }

        purchaseCustTable.setValueAt( // set otal debt for this customer
                (int) purchaseCustTable.getValueAt(purchaseCustTable.getSelectedRow(), 2) - (count * price), purchaseCustTable.getSelectedRow(), 2);

        // empty fildes
        bigCylinders_countTXT.setText("Count");
        bigCylinders_countTXT.setForeground(new Color(153, 153, 153));

        bigCylinders_unitpriceTXT2.setText("Unitprice");
        bigCylinders_unitpriceTXT2.setForeground(new Color(153, 153, 153));

        bigCylinders_jComboBox1.setSelectedIndex(0);

        bigCylinders_noticeTXT1.setText("Notice");
        bigCylinders_noticeTXT1.setForeground(new Color(153, 153, 153));

        bigCylinders_jDateChooser2.setDate(new Date());

        d_bigCylinders();
    }//GEN-LAST:event_addBigCylindersTransactionBTN2ActionPerformed

    private void bigCylinders_jDateChooser2KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_bigCylinders_jDateChooser2KeyPressed
    {//GEN-HEADEREND:event_bigCylinders_jDateChooser2KeyPressed
        mm.componentRequestFocus(evt, bigCylinders_jComboBox1);
    }//GEN-LAST:event_bigCylinders_jDateChooser2KeyPressed

    private void bigCylinders_noticeTXT1FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_bigCylinders_noticeTXT1FocusGained
    {//GEN-HEADEREND:event_bigCylinders_noticeTXT1FocusGained
        mm.ifFocusGainedAndLost(bigCylinders_noticeTXT1, "Notice");
    }//GEN-LAST:event_bigCylinders_noticeTXT1FocusGained

    private void bigCylinders_noticeTXT1FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_bigCylinders_noticeTXT1FocusLost
    {//GEN-HEADEREND:event_bigCylinders_noticeTXT1FocusLost
        mm.ifFocusGainedAndLost(bigCylinders_noticeTXT1, "Notice");
    }//GEN-LAST:event_bigCylinders_noticeTXT1FocusLost

    private void bigCylinders_noticeTXT1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_bigCylinders_noticeTXT1KeyPressed
    {//GEN-HEADEREND:event_bigCylinders_noticeTXT1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            addBigCylindersTransactionBTN2ActionPerformed(null);
        }
    }//GEN-LAST:event_bigCylinders_noticeTXT1KeyPressed

    private void bigCylinders_jComboBox1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_bigCylinders_jComboBox1KeyPressed
    {//GEN-HEADEREND:event_bigCylinders_jComboBox1KeyPressed
        mm.componentRequestFocus(evt, bigCylinders_noticeTXT1);
    }//GEN-LAST:event_bigCylinders_jComboBox1KeyPressed

    // </editor-fold>
    // (purchase) components events
    // <editor-fold>

    private void purchaseCountTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_purchaseCountTXTFocusGained
    {//GEN-HEADEREND:event_purchaseCountTXTFocusGained
        mm.ifFocusGainedAndLost(purchaseCountTXT, "Count");
    }//GEN-LAST:event_purchaseCountTXTFocusGained

    private void purchaseCountTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_purchaseCountTXTFocusLost
    {//GEN-HEADEREND:event_purchaseCountTXTFocusLost
        mm.ifFocusGainedAndLost(purchaseCountTXT, "Count");
    }//GEN-LAST:event_purchaseCountTXTFocusLost

    private void purchaseCountTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_purchaseCountTXTKeyPressed
    {//GEN-HEADEREND:event_purchaseCountTXTKeyPressed
        mm.componentRequestFocus(evt, purchase_noticeTXT1);
    }//GEN-LAST:event_purchaseCountTXTKeyPressed

    private void purchase_noticeTXT1FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_purchase_noticeTXT1FocusGained
    {//GEN-HEADEREND:event_purchase_noticeTXT1FocusGained
        mm.ifFocusGainedAndLost(purchase_noticeTXT1, "Notice");
    }//GEN-LAST:event_purchase_noticeTXT1FocusGained

    private void purchase_noticeTXT1FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_purchase_noticeTXT1FocusLost
    {//GEN-HEADEREND:event_purchase_noticeTXT1FocusLost
        mm.ifFocusGainedAndLost(purchase_noticeTXT1, "Notice");
    }//GEN-LAST:event_purchase_noticeTXT1FocusLost

    private void purchase_noticeTXT1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_purchase_noticeTXT1KeyPressed
    {//GEN-HEADEREND:event_purchase_noticeTXT1KeyPressed
        mm.componentRequestFocus(evt, purchase_jDateChooser1);
    }//GEN-LAST:event_purchase_noticeTXT1KeyPressed

    private void addPurchaseBTN1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addPurchaseBTN1ActionPerformed
    {//GEN-HEADEREND:event_addPurchaseBTN1ActionPerformed
        if (purchaseCountTXT.getText().equals("Count") || Integer.parseInt(purchaseCountTXT.getText()) <= 0)
        {
            JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
            return;
        }

        count = Integer.parseInt(purchaseCountTXT.getText());

        handler.insertOrUpdate("insert into purchase (count, date, notice) values ("
                + count + ", '"
                + ((JTextField) purchase_jDateChooser1.getDateEditor().getUiComponent()).getText() + "', '"
                + purchase_noticeTXT1.getText() + "')",
                "Transaction Inserted Successfully");

        // set total purchase in the store and available
        total = Integer.parseInt(purchaseLabel.getText()) + count;
        purchaseLabel.setText(Integer.toString(total));

        total = Integer.parseInt(inTheStoreLabel.getText()) + count;
        inTheStoreLabel.setText(Integer.toString(total));

        total = Integer.parseInt(availableLabel.getText()) + count;
        availableLabel.setText(Integer.toString(total));

        purchaseCountTXT.setText("Count");
        purchaseCountTXT.setForeground(new Color(153, 153, 153));

        purchase_noticeTXT1.setText("Notice");
        purchase_noticeTXT1.setForeground(new Color(153, 153, 153));

        purchase_jDateChooser1.setDate(new Date());

        d_purchase();
    }//GEN-LAST:event_addPurchaseBTN1ActionPerformed

    // </editor-fold>
    // (refilld) components events
    // <editor-fold>

    private void refilledCountTXT1FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_refilledCountTXT1FocusGained
    {//GEN-HEADEREND:event_refilledCountTXT1FocusGained
        mm.ifFocusGainedAndLost(refilledCountTXT1, "Count");
    }//GEN-LAST:event_refilledCountTXT1FocusGained

    private void refilledCountTXT1FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_refilledCountTXT1FocusLost
    {//GEN-HEADEREND:event_refilledCountTXT1FocusLost
        mm.ifFocusGainedAndLost(refilledCountTXT1, "Count");
    }//GEN-LAST:event_refilledCountTXT1FocusLost

    private void refilledCountTXT1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_refilledCountTXT1KeyPressed
    {//GEN-HEADEREND:event_refilledCountTXT1KeyPressed
        mm.componentRequestFocus(evt, refilled_jDateChooser);
    }//GEN-LAST:event_refilledCountTXT1KeyPressed

    private void addRefilledBTN2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addRefilledBTN2ActionPerformed
    {//GEN-HEADEREND:event_addRefilledBTN2ActionPerformed
        // value credential
        // <editor-fold>
        try
        {
            id = Integer.parseInt(mm.getValueAt(purchaseCustTable, 0));
            count = Integer.parseInt(refilledCountTXT1.getText());
        }
        catch (ArrayIndexOutOfBoundsException aex)
        {
            JOptionPane.showMessageDialog(this, "Select Customer", "message", 2);
            return;
        }
        catch (NumberFormatException nex)
        {
            JOptionPane.showMessageDialog(this, "Invalid values..." + nex.getMessage(), "message", 2);
            return;
        }

        // </editor-fold>
        if (refilledCountTXT1.getText().equals("0"))
        {
            JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
            return;
        }

        handler.insertOrUpdate("insert into refilled (customer_id, count, date) values ("
                + id + ", "
                + count + ", '"
                + ((JTextField) refilled_jDateChooser.getDateEditor().getUiComponent()).getText() + "')",
                "Transaction Inserted Successfully");

        refilledLabel.setText( // set total refilled cylinders
                Integer.toString(Integer.parseInt(refilledLabel.getText()) + count));

        availableLabel.setText( // set total available cylinders
                Integer.toString(Integer.parseInt(availableLabel.getText()) - count));

        setTotalCreditForSpecificCustomer( // set total debt for this customer
                purchaseCustTable, 2);

        refilledCountTXT1.setText("Count");
        refilledCountTXT1.setForeground(new Color(153, 153, 153));

        refilled_jDateChooser.setDate(new Date());

        d_refilled();
    }//GEN-LAST:event_addRefilledBTN2ActionPerformed

    // </editor-fold>
    // (cylinders in) components events
    // <editor-fold>

    private void inCountTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_inCountTXTFocusGained
    {//GEN-HEADEREND:event_inCountTXTFocusGained
        mm.ifFocusGainedAndLost(inCountTXT, "Count");
    }//GEN-LAST:event_inCountTXTFocusGained

    private void inCountTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_inCountTXTFocusLost
    {//GEN-HEADEREND:event_inCountTXTFocusLost
        mm.ifFocusGainedAndLost(inCountTXT, "Count");
    }//GEN-LAST:event_inCountTXTFocusLost

    private void inCountTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_inCountTXTKeyPressed
    {//GEN-HEADEREND:event_inCountTXTKeyPressed
        mm.componentRequestFocus(evt, inNoticeTXT);
    }//GEN-LAST:event_inCountTXTKeyPressed

    private void inNoticeTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_inNoticeTXTFocusGained
    {//GEN-HEADEREND:event_inNoticeTXTFocusGained
        mm.ifFocusGainedAndLost(inNoticeTXT, "Notice");
    }//GEN-LAST:event_inNoticeTXTFocusGained

    private void inNoticeTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_inNoticeTXTFocusLost
    {//GEN-HEADEREND:event_inNoticeTXTFocusLost
        mm.ifFocusGainedAndLost(inNoticeTXT, "Notice");
    }//GEN-LAST:event_inNoticeTXTFocusLost

    private void inNoticeTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_inNoticeTXTKeyPressed
    {//GEN-HEADEREND:event_inNoticeTXTKeyPressed
        mm.componentRequestFocus(evt, in_jDateChooser1);
    }//GEN-LAST:event_inNoticeTXTKeyPressed

    private void addInBTN1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addInBTN1ActionPerformed
    {//GEN-HEADEREND:event_addInBTN1ActionPerformed
        // value credential
        // <editor-fold>

        try
        {
            id = Integer.parseInt(mm.getValueAt(salesCustTable, 0));
            count = Integer.parseInt(inCountTXT.getText());
        }
        catch (ArrayIndexOutOfBoundsException aex)
        {
            JOptionPane.showMessageDialog(this, "Select Customer", "message", 2);
            return;
        }
        catch (NumberFormatException nex)
        {
            JOptionPane.showMessageDialog(this, "Invalid values..." + nex.getMessage(), "message", 2);
            return;
        }

        // </editor-fold>
        handler.insertOrUpdate("insert into in_table (customer_id, count, date, notice) values ("
                + id + ", "
                + count + ", '"
                + ((JTextField) in_jDateChooser1.getDateEditor().getUiComponent()).getText() + "', '"
                + inNoticeTXT.getText() + "')",
                "Transaction Inserted Successfully...");

        availableLabel.setText( // set total available cylinder
                Integer.toString(Integer.parseInt(availableLabel.getText()) + count));

        salesCustTable.setValueAt( // set total remaining cylinder for this customer
                (int) salesCustTable.getValueAt(salesCustTable.getSelectedRow(), 2) - count, salesCustTable.getSelectedRow(), 2);

        salesCustTotalsTable.setValueAt( // set total remaining cylinders for all customers 
                (int) salesCustTotalsTable.getValueAt(0, 2) - count, 0, 2);

        inCountTXT.setText("Count");
        inCountTXT.setForeground(new Color(153, 153, 153));

        inNoticeTXT.setText("Notice");
        inNoticeTXT.setForeground(new Color(153, 153, 153));

        in_jDateChooser1.setDate(new Date());

        d_in();
    }//GEN-LAST:event_addInBTN1ActionPerformed

    // </editor-fold>
    // (credit) components events
    // <editor-fold>

    private void addCreditBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addCreditBTNActionPerformed
    {//GEN-HEADEREND:event_addCreditBTNActionPerformed
        // value credential
        // <editor-fold>

        try
        {
            id = Integer.parseInt(mm.getValueAt(tableName, 0));
            price = Integer.parseInt(creditTXT.getText());
        }
        catch (ArrayIndexOutOfBoundsException aex)
        {
            JOptionPane.showMessageDialog(this, "Select Customer", "message", 2);
            return;
        }
        catch (NumberFormatException nex)
        {
            JOptionPane.showMessageDialog(this, "Invalid values..." + nex.getMessage(), "message", 2);
            return;
        }

        if (price <= 0)
        {
            JOptionPane.showMessageDialog(this, "please set value..", "message", 2);
            return;
        }

        // </editor-fold>
        handler.insertOrUpdate("insert into credit (customer_id, description, date, credit) values ("
                + id + ", '"
                + credit_descriptionTXT.getText() + "', '"
                + ((JTextField) credit_jDateChooser.getDateEditor().getUiComponent()).getText() + "', "
                + price + ")",
                "Transaction Inserted Successfully");

        if (tableName == salesCustTable)
        {
            columnIndex = 3;
        }
        else
        {
            columnIndex = 2;
        }

        tableName.setValueAt(
                (int) tableName.getValueAt(tableName.getSelectedRow(), columnIndex) + price, tableName.getSelectedRow(), columnIndex);

        if (tableName == salesCustTable)
        {
            salesCustTotalsTable.setValueAt(
                    (int) salesCustTotalsTable.getValueAt(0, 3) + price, 0, 3);
        }

        creditTXT.setText("Credit");
        creditTXT.setForeground(new Color(153, 153, 153));

        credit_descriptionTXT.setText("Description");
        credit_descriptionTXT.setForeground(new Color(153, 153, 153));

        credit_jDateChooser.setDate(new Date());

        d_credit();
    }//GEN-LAST:event_addCreditBTNActionPerformed

    private void creditTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_creditTXTFocusGained
    {//GEN-HEADEREND:event_creditTXTFocusGained
        mm.ifFocusGainedAndLost(creditTXT, "Credit");
    }//GEN-LAST:event_creditTXTFocusGained

    private void creditTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_creditTXTFocusLost
    {//GEN-HEADEREND:event_creditTXTFocusLost
        mm.ifFocusGainedAndLost(creditTXT, "Credit");
    }//GEN-LAST:event_creditTXTFocusLost

    private void creditTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_creditTXTKeyPressed
    {//GEN-HEADEREND:event_creditTXTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            credit_descriptionTXT.requestFocus();
        }
    }//GEN-LAST:event_creditTXTKeyPressed

    private void credit_descriptionTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_credit_descriptionTXTFocusGained
    {//GEN-HEADEREND:event_credit_descriptionTXTFocusGained
        mm.ifFocusGainedAndLost(credit_descriptionTXT, "Description");
    }//GEN-LAST:event_credit_descriptionTXTFocusGained

    private void credit_descriptionTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_credit_descriptionTXTFocusLost
    {//GEN-HEADEREND:event_credit_descriptionTXTFocusLost
        mm.ifFocusGainedAndLost(credit_descriptionTXT, "Description");
    }//GEN-LAST:event_credit_descriptionTXTFocusLost

    private void credit_descriptionTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_credit_descriptionTXTKeyPressed
    {//GEN-HEADEREND:event_credit_descriptionTXTKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            credit_jDateChooser.requestFocus();
        }
    }//GEN-LAST:event_credit_descriptionTXTKeyPressed

    private void credit_jDateChooserKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_credit_jDateChooserKeyPressed
    {//GEN-HEADEREND:event_credit_jDateChooserKeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            addCreditBTNActionPerformed(null);
        }
    }//GEN-LAST:event_credit_jDateChooserKeyPressed

    // </editor-fold>
    // (debt) component events
    // <editor-fold>

    private void debtTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_debtTXTFocusGained
    {//GEN-HEADEREND:event_debtTXTFocusGained
        mm.ifFocusGainedAndLost(debtTXT, "Debt");
    }//GEN-LAST:event_debtTXTFocusGained

    private void debtTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_debtTXTFocusLost
    {//GEN-HEADEREND:event_debtTXTFocusLost
        mm.ifFocusGainedAndLost(debtTXT, "Debt");
    }//GEN-LAST:event_debtTXTFocusLost

    private void debtTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_debtTXTKeyPressed
    {//GEN-HEADEREND:event_debtTXTKeyPressed
        mm.componentRequestFocus(evt, debt_descriptionTXT1);
    }//GEN-LAST:event_debtTXTKeyPressed

    private void debt_descriptionTXT1FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_debt_descriptionTXT1FocusGained
    {//GEN-HEADEREND:event_debt_descriptionTXT1FocusGained
        mm.ifFocusGainedAndLost(debt_descriptionTXT1, "Description");
    }//GEN-LAST:event_debt_descriptionTXT1FocusGained

    private void debt_descriptionTXT1FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_debt_descriptionTXT1FocusLost
    {//GEN-HEADEREND:event_debt_descriptionTXT1FocusLost
        mm.ifFocusGainedAndLost(debt_descriptionTXT1, "Description");
    }//GEN-LAST:event_debt_descriptionTXT1FocusLost

    private void debt_descriptionTXT1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_debt_descriptionTXT1KeyPressed
    {//GEN-HEADEREND:event_debt_descriptionTXT1KeyPressed
        mm.componentRequestFocus(evt, debt_jDateChooser1);
    }//GEN-LAST:event_debt_descriptionTXT1KeyPressed

    private void addDebtBTN1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addDebtBTN1ActionPerformed
    {//GEN-HEADEREND:event_addDebtBTN1ActionPerformed
        setTableName();

        // value credential
        // <editor-fold>
        try
        {
            id = Integer.parseInt(mm.getValueAt(tableName, 0));
            price = Integer.parseInt(debtTXT.getText());
        }
        catch (ArrayIndexOutOfBoundsException aex)
        {
            JOptionPane.showMessageDialog(this, "Select Customer", "message", 2);
            return;
        }
        catch (NumberFormatException nex)
        {
            JOptionPane.showMessageDialog(this, "Invalid values..." + nex.getMessage(), "message", 2);
            return;
        }

        if (price <= 0)
        {
            JOptionPane.showMessageDialog(this, "please set value..", "message", 2);
            return;
        }

        // </editor-fold>
        handler.insertOrUpdate("insert into debt (customer_id, description, date, debt) values ("
                + id + ", '"
                + debt_descriptionTXT1.getText() + "', '"
                + ((JTextField) debt_jDateChooser1.getDateEditor().getUiComponent()).getText() + "', "
                + price + ")",
                "Transaction Inserted Successfully");

        if (tableName == salesCustTable)
        {
            columnIndex = 3;
        }
        else
        {
            columnIndex = 2;
        }

        tableName.setValueAt(
                (int) tableName.getValueAt(tableName.getSelectedRow(), columnIndex) - price, tableName.getSelectedRow(), columnIndex);

        if (tableName == salesCustTable)
        {
            salesCustTotalsTable.setValueAt(
                    (int) salesCustTotalsTable.getValueAt(0, 3) - price, 0, 3);
        }

        debtTXT.setText("Debt");
        debtTXT.setForeground(new Color(153, 153, 153));

        debt_descriptionTXT1.setText("Description");
        debt_descriptionTXT1.setForeground(new Color(153, 153, 153));

        debt_jDateChooser1.setDate(new Date());

        d_debt();
    }//GEN-LAST:event_addDebtBTN1ActionPerformed

    private void debt_jDateChooser1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_debt_jDateChooser1KeyPressed
    {//GEN-HEADEREND:event_debt_jDateChooser1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            addDebtBTN1ActionPerformed(null);
        }
    }//GEN-LAST:event_debt_jDateChooser1KeyPressed

    // </editor-fold>
    // (cylinders out) Components events
    // <editor-fold>

    private void outCountTXT1FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_outCountTXT1FocusGained
    {//GEN-HEADEREND:event_outCountTXT1FocusGained
        mm.ifFocusGainedAndLost(outCountTXT1, "Count");
    }//GEN-LAST:event_outCountTXT1FocusGained

    private void outCountTXT1FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_outCountTXT1FocusLost
    {//GEN-HEADEREND:event_outCountTXT1FocusLost
        mm.ifFocusGainedAndLost(outCountTXT1, "Count");
    }//GEN-LAST:event_outCountTXT1FocusLost

    private void outCountTXT1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_outCountTXT1KeyPressed
    {//GEN-HEADEREND:event_outCountTXT1KeyPressed
        mm.componentRequestFocus(evt, outUnitpriceTXT2);
    }//GEN-LAST:event_outCountTXT1KeyPressed

    private void outNoticeTXT1FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_outNoticeTXT1FocusGained
    {//GEN-HEADEREND:event_outNoticeTXT1FocusGained
        mm.ifFocusGainedAndLost(outNoticeTXT1, "Notice");
    }//GEN-LAST:event_outNoticeTXT1FocusGained

    private void outNoticeTXT1FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_outNoticeTXT1FocusLost
    {//GEN-HEADEREND:event_outNoticeTXT1FocusLost
        mm.ifFocusGainedAndLost(outNoticeTXT1, "Notice");
    }//GEN-LAST:event_outNoticeTXT1FocusLost

    private void outNoticeTXT1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_outNoticeTXT1KeyPressed
    {//GEN-HEADEREND:event_outNoticeTXT1KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            addOutBTN2ActionPerformed(null);
        }
    }//GEN-LAST:event_outNoticeTXT1KeyPressed

    private void addOutBTN2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addOutBTN2ActionPerformed
    {//GEN-HEADEREND:event_addOutBTN2ActionPerformed
        // value credential
        // <editor-fold>

        try
        {
            id = Integer.parseInt(mm.getValueAt(salesCustTable, 0));
            count = Integer.parseInt(outCountTXT1.getText());
            price = Integer.parseInt(outUnitpriceTXT2.getText());
        }
        catch (ArrayIndexOutOfBoundsException aex)
        {
            JOptionPane.showMessageDialog(this, "Select Customer", "message", 2);
            return;
        }
        catch (NumberFormatException nex)
        {
            JOptionPane.showMessageDialog(this, "Invalid values..." + nex.getMessage(), "message", 2);
            return;
        }
//
//            if (count <= 0)
//            {
//                JOptionPane.showMessageDialog(this, "please set values..", "message", 2);
//                return;
//            }
//        
        // </editor-fold>

        handler.insertOrUpdate("insert into out_table (customer_id, count, date, receipt_number, unit_price, notice) values ("
                + id + ", "
                + count + ", '"
                + ((JTextField) out_jDateChooser2.getDateEditor().getUiComponent()).getText() + "', '"
                + outreceiptNumberTXT3.getText() + "', "
                + price + ", '"
                + outNoticeTXT1.getText() + "')",
                "Transaction Inserted Successfully...");

        refilledLabel.setText( // set total refilled 
                Integer.toString(Integer.parseInt(refilledLabel.getText()) - count));

        salesCustTable.setValueAt( // set total remaining cylinders for specific customer
                (int) salesCustTable.getValueAt(salesCustTable.getSelectedRow(), 2) + count, salesCustTable.getSelectedRow(), 2);

        salesCustTable.setValueAt( // set total debt for specific customer
                (int) salesCustTable.getValueAt(salesCustTable.getSelectedRow(), 3) + (count * price), salesCustTable.getSelectedRow(), 3);

        salesCustTotalsTable.setValueAt( // set total remaining cylinders 
                (int) salesCustTotalsTable.getValueAt(0, 2) + count, 0, 2);

        salesCustTotalsTable.setValueAt( // set total debt
                (int) salesCustTotalsTable.getValueAt(0, 3) + (count * price), 0, 3);

        totalOutBetweenLabel.setText(
                Integer.toString(
                        Integer.parseInt(
                                totalOutBetweenLabel.getText()) + count));

        outCountTXT1.setText("Count");
        outCountTXT1.setForeground(new Color(153, 153, 153));

        outUnitpriceTXT2.setText("Unitprice");
        outUnitpriceTXT2.setForeground(new Color(153, 153, 153));

        outreceiptNumberTXT3.setText("Receipt Number");
        outreceiptNumberTXT3.setForeground(new Color(153, 153, 153));

        outNoticeTXT1.setText("Notice");
        outNoticeTXT1.setForeground(new Color(153, 153, 153));

        out_jDateChooser2.setDate(new Date());

        d_out();
    }//GEN-LAST:event_addOutBTN2ActionPerformed

    private void outUnitpriceTXT2FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_outUnitpriceTXT2FocusGained
    {//GEN-HEADEREND:event_outUnitpriceTXT2FocusGained
        mm.ifFocusGainedAndLost(outUnitpriceTXT2, "Unitprice");
    }//GEN-LAST:event_outUnitpriceTXT2FocusGained

    private void outUnitpriceTXT2FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_outUnitpriceTXT2FocusLost
    {//GEN-HEADEREND:event_outUnitpriceTXT2FocusLost
        mm.ifFocusGainedAndLost(outUnitpriceTXT2, "Unitprice");
    }//GEN-LAST:event_outUnitpriceTXT2FocusLost

    private void outUnitpriceTXT2KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_outUnitpriceTXT2KeyPressed
    {//GEN-HEADEREND:event_outUnitpriceTXT2KeyPressed
        mm.componentRequestFocus(evt, out_jDateChooser2);
    }//GEN-LAST:event_outUnitpriceTXT2KeyPressed

    private void outreceiptNumberTXT3FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_outreceiptNumberTXT3FocusGained
    {//GEN-HEADEREND:event_outreceiptNumberTXT3FocusGained
        mm.ifFocusGainedAndLost(outreceiptNumberTXT3, "Receipt Number");
    }//GEN-LAST:event_outreceiptNumberTXT3FocusGained

    private void outreceiptNumberTXT3FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_outreceiptNumberTXT3FocusLost
    {//GEN-HEADEREND:event_outreceiptNumberTXT3FocusLost
        mm.ifFocusGainedAndLost(outreceiptNumberTXT3, "Receipt Number");
    }//GEN-LAST:event_outreceiptNumberTXT3FocusLost

    private void outreceiptNumberTXT3KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_outreceiptNumberTXT3KeyPressed
    {//GEN-HEADEREND:event_outreceiptNumberTXT3KeyPressed
        mm.componentRequestFocus(evt, outNoticeTXT1);
    }//GEN-LAST:event_outreceiptNumberTXT3KeyPressed

    private void out_jDateChooser2KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_out_jDateChooser2KeyPressed
    {//GEN-HEADEREND:event_out_jDateChooser2KeyPressed
        mm.componentRequestFocus(evt, outreceiptNumberTXT3);
    }//GEN-LAST:event_out_jDateChooser2KeyPressed

    // </editor-fold>

    private void settingsBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_settingsBTNActionPerformed

        settings.pack();
        settings.setLocationRelativeTo(null);
        settings.setVisible(true);

        from = settings.getFromDateValue();
        to = settings.getToDateValue();

        totalOutBetweenLabel.setText(Integer.toString(handler.getTotalCylindersOutBetween(from, to)));
    }//GEN-LAST:event_settingsBTNActionPerformed

    // accessories components events
    // <editor-fold>

    private void accessoriesBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accessoriesBTNActionPerformed
    {//GEN-HEADEREND:event_accessoriesBTNActionPerformed
        setButtonBackground(accessoriesBTN);

        // Removing the panel
        cardPanel.removeAll();

        //adding panels
        cardPanel.add(accessoriesPanel);
        cardPanel.repaint();
        cardPanel.revalidate();

        if (salesCustTable.getSelectedRow() >= 0)
        {
            d_accessories();
        }
    }//GEN-LAST:event_accessoriesBTNActionPerformed

    private void accessoriesPriceTXT1FocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_accessoriesPriceTXT1FocusGained
    {//GEN-HEADEREND:event_accessoriesPriceTXT1FocusGained
        mm.ifFocusGainedAndLost(accessoriesPriceTXT1, "Price");
    }//GEN-LAST:event_accessoriesPriceTXT1FocusGained

    private void accessoriesPriceTXT1FocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_accessoriesPriceTXT1FocusLost
    {//GEN-HEADEREND:event_accessoriesPriceTXT1FocusLost
        mm.ifFocusGainedAndLost(accessoriesPriceTXT1, "Price");
    }//GEN-LAST:event_accessoriesPriceTXT1FocusLost

    private void accessoriesPriceTXT1KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_accessoriesPriceTXT1KeyPressed
    {//GEN-HEADEREND:event_accessoriesPriceTXT1KeyPressed
        mm.componentRequestFocus(evt, accessoriesCountTXT);
    }//GEN-LAST:event_accessoriesPriceTXT1KeyPressed

    private void accessoriesCountTXTFocusGained(java.awt.event.FocusEvent evt)//GEN-FIRST:event_accessoriesCountTXTFocusGained
    {//GEN-HEADEREND:event_accessoriesCountTXTFocusGained
        mm.ifFocusGainedAndLost(accessoriesCountTXT, "Count");
    }//GEN-LAST:event_accessoriesCountTXTFocusGained

    private void accessoriesCountTXTFocusLost(java.awt.event.FocusEvent evt)//GEN-FIRST:event_accessoriesCountTXTFocusLost
    {//GEN-HEADEREND:event_accessoriesCountTXTFocusLost
        mm.ifFocusGainedAndLost(accessoriesCountTXT, "Count");
    }//GEN-LAST:event_accessoriesCountTXTFocusLost

    private void accessoriesCountTXTKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_accessoriesCountTXTKeyPressed
    {//GEN-HEADEREND:event_accessoriesCountTXTKeyPressed
        mm.componentRequestFocus(evt, accessories_jDateChooser2);
    }//GEN-LAST:event_accessoriesCountTXTKeyPressed

    private void addAccessoriesBTN2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_addAccessoriesBTN2ActionPerformed
    {//GEN-HEADEREND:event_addAccessoriesBTN2ActionPerformed
        if (accessoriesCombobox.getSelectedIndex() == 0)
        {
            newAccessorValue = JOptionPane.showInputDialog("Enter New Accessor");

            if (newAccessorValue == null || newAccessorValue.equals(""))
            {
                return;
            }
            else
            {
                handler.insertNewAccessories(newAccessorValue);

                accessoriesCombobox.addItem(newAccessorValue);

                accessoriesCombobox.setSelectedItem(newAccessorValue);
            }
        }
        else
        {
            // value credential
            // <editor-fold>

            try
            {
                id = Integer.parseInt(mm.getValueAt(salesCustTable, 0));
                count = Integer.parseInt(accessoriesCountTXT.getText());
                price = Integer.parseInt(accessoriesPriceTXT1.getText());
            }
            catch (ArrayIndexOutOfBoundsException aex)
            {
                JOptionPane.showMessageDialog(this, "Select Customer", "message", 2);
                return;
            }
            catch (NumberFormatException nex)
            {
                JOptionPane.showMessageDialog(this, "Invalid values..." + nex.getMessage(), "message", 2);
                return;
            }

            if (count <= 0 || price <= 0)
            {
                JOptionPane.showMessageDialog(this, "invalid values..", "message", 2);
                return;
            }

            // </editor-fold>
            handler.insertNewAccessoriesTransaction(
                    accessoriesCombobox.getSelectedItem().toString(),
                    id,
                    price,
                    count,
                    ((JTextField) accessories_jDateChooser2.getDateEditor().getUiComponent()).getText());

            salesCustTable.setValueAt(
                    (int) salesCustTable.getValueAt(salesCustTable.getSelectedRow(), 3) + (price * count) // get selected row debt value then sum with new value
                    , salesCustTable.getSelectedRow(), 3);

            salesCustTotalsTable.setValueAt(
                    (int) salesCustTotalsTable.getValueAt(0, 3) + (price * count), 0, 3);

            // empty fildes
            mm.focusLost(accessoriesPriceTXT1, "Price");
            mm.focusLost(accessoriesCountTXT, "Count");
//            accessories_jDateChooser2.setDate(new Date());
            accessoriesCombobox.setSelectedIndex(0);

            d_accessories();
        }
    }//GEN-LAST:event_addAccessoriesBTN2ActionPerformed

    private void accessoriesComboboxKeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_accessoriesComboboxKeyPressed
    {//GEN-HEADEREND:event_accessoriesComboboxKeyPressed
        mm.componentRequestFocus(evt, accessoriesPriceTXT1);
    }//GEN-LAST:event_accessoriesComboboxKeyPressed

    private void accessories_jDateChooser2KeyPressed(java.awt.event.KeyEvent evt)//GEN-FIRST:event_accessories_jDateChooser2KeyPressed
    {//GEN-HEADEREND:event_accessories_jDateChooser2KeyPressed
        if (evt.getKeyCode() == KeyEvent.VK_ENTER)
        {
            addAccessoriesBTN2ActionPerformed(null);
        }
    }//GEN-LAST:event_accessories_jDateChooser2KeyPressed

    char c;
    private void deleteAccessoriesBTN3ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_deleteAccessoriesBTN3ActionPerformed
    {//GEN-HEADEREND:event_deleteAccessoriesBTN3ActionPerformed
        if (accessoriesCombobox.getSelectedIndex() == 0)
        {
            JOptionPane.showMessageDialog(this, "select accessory..", "message", 2);
            return;
        }

        handler.deletRecord("delete from accessories where name = '" + accessoriesCombobox.getSelectedItem().toString() + "'");

        accessoriesCombobox.removeAllItems();

        handler.fillAccessoriesNamesComboBox(accessoriesCombobox);
    }//GEN-LAST:event_deleteAccessoriesBTN3ActionPerformed

    // </editor-fold>
    Treasury t;
    private void treasuryButtonActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_treasuryButtonActionPerformed
    {//GEN-HEADEREND:event_treasuryButtonActionPerformed
        t = new Treasury(this, true);

        t.inflateUI(from, to);

        t.pack();
        t.setLocationRelativeTo(null);
        t.setVisible(true);

        t.dispose();
    }//GEN-LAST:event_treasuryButtonActionPerformed

    // About Reports
    // <editor-fold>

    private void reportsBTN1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_reportsBTN1ActionPerformed
    {//GEN-HEADEREND:event_reportsBTN1ActionPerformed
        setButtonBackground(reportsBTN1);

        // Removing the panel
        cardPanel.removeAll();

        //adding panels
        cardPanel.add(reportsPanel);
        cardPanel.repaint();
        cardPanel.revalidate();
    }//GEN-LAST:event_reportsBTN1ActionPerformed

    void runReprts1(JButton buttonName)
    {
        handler.run_icsCylinders_Reports(
                buttonName.getText(),
                null,
                null,
                null,
                null);
    }

    String limited;

    void runReports2(String reportName, JTable tableName)
    {
        if (LimitedCB.isSelected())
        {
            limited = " Limited";
        }
        else
        {
            limited = "";
        }

        try
        {
            handler.run_icsCylinders_Reports(
                    reportName + limited,
                    mm.getValueAt(tableName, 1),
                    mm.getValueAt(tableName, 0),
                    settings.getFromDateValueAsDate(),
                    settings.getToDateValueAsDate());
        }
        catch (ArrayIndexOutOfBoundsException ex)
        {
            JOptionPane.showMessageDialog(this, "select customer..", " message", 2);
        }
    }

    private void customersReportBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_customersReportBTNActionPerformed
    {//GEN-HEADEREND:event_customersReportBTNActionPerformed
        runReprts1(customersReportBTN);
    }//GEN-LAST:event_customersReportBTNActionPerformed

    private void purhcaseReportBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_purhcaseReportBTNActionPerformed
    {//GEN-HEADEREND:event_purhcaseReportBTNActionPerformed
        runReprts1(purhcaseReportBTN);
    }//GEN-LAST:event_purhcaseReportBTNActionPerformed

    private void inReportBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_inReportBTNActionPerformed
    {//GEN-HEADEREND:event_inReportBTNActionPerformed
        runReports2("Cylinders In", salesCustTable);
    }//GEN-LAST:event_inReportBTNActionPerformed

    private void outReportBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_outReportBTNActionPerformed
    {//GEN-HEADEREND:event_outReportBTNActionPerformed
        runReports2("Cylinders Out", salesCustTable);
    }//GEN-LAST:event_outReportBTNActionPerformed

    private void accessoriesReportBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_accessoriesReportBTNActionPerformed
    {//GEN-HEADEREND:event_accessoriesReportBTNActionPerformed
        runReports2("Accessories", salesCustTable);
    }//GEN-LAST:event_accessoriesReportBTNActionPerformed

    private void creidtReportBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_creidtReportBTNActionPerformed
    {//GEN-HEADEREND:event_creidtReportBTNActionPerformed
        setTableName();

        runReports2("Credit", tableName);
    }//GEN-LAST:event_creidtReportBTNActionPerformed

    private void debtReportBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_debtReportBTNActionPerformed
    {//GEN-HEADEREND:event_debtReportBTNActionPerformed
        setTableName();

        runReports2("Debt", tableName);
    }//GEN-LAST:event_debtReportBTNActionPerformed

    private void bigCylindersReportBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_bigCylindersReportBTNActionPerformed
    {//GEN-HEADEREND:event_bigCylindersReportBTNActionPerformed
        runReports2("Big Cylinders", purchaseCustTable);
    }//GEN-LAST:event_bigCylindersReportBTNActionPerformed

    private void refilledReportBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_refilledReportBTNActionPerformed
    {//GEN-HEADEREND:event_refilledReportBTNActionPerformed
        runReports2("Refilled", purchaseCustTable);
    }//GEN-LAST:event_refilledReportBTNActionPerformed

    private void allDebtTransactionBTNActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_allDebtTransactionBTNActionPerformed
    {//GEN-HEADEREND:event_allDebtTransactionBTNActionPerformed
        runReprts1(allDebtTransactionBTN);
    }//GEN-LAST:event_allDebtTransactionBTNActionPerformed

    private void allCreditTransactionBTN1ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_allCreditTransactionBTN1ActionPerformed
    {//GEN-HEADEREND:event_allCreditTransactionBTN1ActionPerformed
        runReprts1(allCreditTransactionBTN1);
    }//GEN-LAST:event_allCreditTransactionBTN1ActionPerformed

    private void allAccessoriesTransactionBTN2ActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_allAccessoriesTransactionBTN2ActionPerformed
    {//GEN-HEADEREND:event_allAccessoriesTransactionBTN2ActionPerformed
        runReprts1(allAccessoriesTransactionBTN2);
    }//GEN-LAST:event_allAccessoriesTransactionBTN2ActionPerformed

    // </editor-fold>

    private void invoiceBTNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceBTNActionPerformed

        if (salesCustTable.getSelectedRow() < 0)
        {
            JOptionPane.showMessageDialog(this, "Select Customer.");
            return;
        }
        
        /* Output file location to create report in pdf form */
//        String outputFile = "C:\\Users\\amitk\\Desktop\\JASPER\\" + "JasperReportExample.pdf";

        JRBeanCollectionDataSource itemsJRBean = new JRBeanCollectionDataSource(handler.runInvoiceReport((int)salesCustTable.getValueAt(salesCustTable.getSelectedRow(), 0)));

        /* Map to hold Jasper report Parameters */
        Map<String, Object> parameters = new HashMap<String, Object>();
        parameters.put("CollectionBeanParam", itemsJRBean);
        parameters.put("CustomerName", salesCustTable.getValueAt(salesCustTable.getSelectedRow(), 1));
        
        InputStream input = null;
        JasperDesign jasperDesign = null;        
        JasperReport jasperReport = null;
        JasperPrint jasperPrint = null;
        try
        {
            //read jrxml file and creating jasperdesign object
            input = new FileInputStream(new File("Reports\\Cherry_Landscape_3.jrxml"));
            
            jasperDesign = JRXmlLoader.load(input);
            
            /*compiling jrxml with help of JasperReport class*/
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            
            /* Using jasperReport object to generate PDF */
            jasperPrint = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
        }
        catch (FileNotFoundException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch (JRException ex)
        {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

        /*call jasper engine to display report in jasperviewer window*/
        JasperViewer.viewReport(jasperPrint, false);
        
        
        /* outputStream to create PDF */
        //OutputStream outputStream = new FileOutputStream(new File(outputFile));
        
        
        /* Write content to PDF file */
        //JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);

        System.out.println("File Generated");	
    }//GEN-LAST:event_invoiceBTNActionPerformed

    private void refilledBetweenActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_refilledBetweenActionPerformed
    {//GEN-HEADEREND:event_refilledBetweenActionPerformed
        handler.run_icsCylinders_Reports(
                "Refilled Cylinders Between",
                null,
                null,   
                settings.getFromDateValueAsDate(),
                settings.getToDateValueAsDate());
    }//GEN-LAST:event_refilledBetweenActionPerformed

    private void allOutBetweenActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_allOutBetweenActionPerformed
    {//GEN-HEADEREND:event_allOutBetweenActionPerformed
        handler.run_icsCylinders_Reports(
                "AllOutCylindersBetween",
                null,
                null,   
                settings.getFromDateValueAsDate(),
                settings.getToDateValueAsDate());
    }//GEN-LAST:event_allOutBetweenActionPerformed

    public static
            void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        }
        catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(Main.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public
                    void run()
            {
                new Main().setVisible(true);
            }
        });
    }

    // Variables declaration
    // <editor-fold> 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane JScrollPane1;
    private javax.swing.JScrollPane JScrollPane2;
    private javax.swing.JScrollPane JScrollPane3;
    private javax.swing.JScrollPane JScrollPane4;
    private javax.swing.JScrollPane JScrollPane5;
    private javax.swing.JScrollPane JScrollPane6;
    private javax.swing.JScrollPane JScrollPane7;
    private javax.swing.JScrollPane JScrollPane8;
    private javax.swing.JCheckBox LimitedCB;
    private javax.swing.JButton accessoriesBTN;
    private javax.swing.JComboBox accessoriesCombobox;
    private javax.swing.JTextField accessoriesCountTXT;
    private javax.swing.JPanel accessoriesPanel;
    private javax.swing.JTextField accessoriesPriceTXT1;
    private javax.swing.JButton accessoriesReportBTN;
    private com.toedter.calendar.JDateChooser accessories_jDateChooser2;
    private javax.swing.JTable accessoryTable1;
    private javax.swing.JButton addAccessoriesBTN2;
    private javax.swing.JButton addBigCylindersTransactionBTN2;
    private javax.swing.JButton addCreditBTN;
    private javax.swing.JButton addCustomerBTN;
    private javax.swing.JButton addDebtBTN1;
    private javax.swing.JButton addInBTN1;
    private javax.swing.JButton addOutBTN2;
    private javax.swing.JButton addPurchaseBTN1;
    private javax.swing.JButton addRefilledBTN2;
    private javax.swing.JButton allAccessoriesTransactionBTN2;
    private javax.swing.JButton allCreditTransactionBTN1;
    private javax.swing.JButton allDebtTransactionBTN;
    private javax.swing.JButton allOutBetween;
    private javax.swing.JLabel availableLabel;
    private javax.swing.JButton bigCylindersBTN;
    private javax.swing.JLabel bigCylindersLabel;
    private javax.swing.JPanel bigCylindersPanel;
    private javax.swing.JButton bigCylindersReportBTN;
    private javax.swing.JTable bigCylindersTable;
    private javax.swing.JTextField bigCylinders_countTXT;
    private javax.swing.JComboBox bigCylinders_jComboBox1;
    private com.toedter.calendar.JDateChooser bigCylinders_jDateChooser2;
    private javax.swing.JTextField bigCylinders_noticeTXT1;
    private javax.swing.JTextField bigCylinders_unitpriceTXT2;
    private javax.swing.JPanel cardPanel;
    private javax.swing.JPanel categoriesPanel;
    private javax.swing.JScrollPane catygoriesScrollBar;
    private javax.swing.JButton creditBTN;
    private javax.swing.JPanel creditPanel;
    private javax.swing.JTextField creditTXT;
    private javax.swing.JTable creditTable;
    private javax.swing.JTextField credit_descriptionTXT;
    private com.toedter.calendar.JDateChooser credit_jDateChooser;
    private javax.swing.JButton creidtReportBTN;
    private javax.swing.JTextField custFilterTXT;
    private javax.swing.JTextField customerNameTXT;
    private javax.swing.JPanel customerPanel;
    private javax.swing.JButton customersReportBTN;
    private javax.swing.JComboBox cutomerTypeCB;
    private javax.swing.JButton damagedBTN;
    private javax.swing.JButton debtBTN;
    private javax.swing.JPanel debtPanel;
    private javax.swing.JButton debtReportBTN;
    private javax.swing.JTextField debtTXT;
    private javax.swing.JTable debtTable;
    private javax.swing.JTextField debt_descriptionTXT1;
    private com.toedter.calendar.JDateChooser debt_jDateChooser1;
    private javax.swing.JButton deleteAccessoriesBTN3;
    private javax.swing.JButton inBTN;
    private javax.swing.JTextField inCountTXT;
    private javax.swing.JTextField inNoticeTXT;
    private javax.swing.JPanel inPanel;
    private javax.swing.JButton inReportBTN;
    private javax.swing.JTable inTable;
    private javax.swing.JLabel inTheStoreLabel;
    private com.toedter.calendar.JDateChooser in_jDateChooser1;
    private javax.swing.JButton invoiceBTN;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JButton outBTN;
    private javax.swing.JTextField outCountTXT1;
    private javax.swing.JTextField outNoticeTXT1;
    private javax.swing.JPanel outPanel;
    private javax.swing.JButton outReportBTN;
    private javax.swing.JTable outTable;
    private javax.swing.JTable outTotalsTable;
    private javax.swing.JTextField outUnitpriceTXT2;
    private com.toedter.calendar.JDateChooser out_jDateChooser2;
    private javax.swing.JTextField outreceiptNumberTXT3;
    private javax.swing.JButton overviewBTN;
    private javax.swing.JPanel overviewPanel;
    private javax.swing.JButton purchaseBTN;
    private javax.swing.JTextField purchaseCountTXT;
    private javax.swing.JTable purchaseCustTable;
    private javax.swing.JLabel purchaseLabel;
    private javax.swing.JPanel purchasePanel;
    private javax.swing.JTable purchaseTable;
    private com.toedter.calendar.JDateChooser purchase_jDateChooser1;
    private javax.swing.JTextField purchase_noticeTXT1;
    private javax.swing.JScrollPane purchasesCustScrollPane1;
    private javax.swing.JButton purhcaseReportBTN;
    private javax.swing.JButton refilledBTN;
    private javax.swing.JButton refilledBetween;
    private javax.swing.JTextField refilledCountTXT1;
    private javax.swing.JLabel refilledLabel;
    private javax.swing.JPanel refilledPanel;
    private javax.swing.JButton refilledReportBTN;
    private javax.swing.JTable refilledTable;
    private com.toedter.calendar.JDateChooser refilled_jDateChooser;
    private javax.swing.JButton reportsBTN1;
    private javax.swing.JPanel reportsPanel;
    private javax.swing.JScrollPane salesCustScrollPane;
    private javax.swing.JTable salesCustTable;
    private javax.swing.JTable salesCustTotalsTable;
    private javax.swing.JButton settingsBTN;
    private javax.swing.JLabel totalOutBetweenLabel;
    private javax.swing.JPanel totalsPanel;
    private javax.swing.JButton treasuryButton;
    // End of variables declaration//GEN-END:variables

    // </editor-fold>
}
