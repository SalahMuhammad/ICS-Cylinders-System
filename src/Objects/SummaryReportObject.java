package Objects;

import java.util.Date;

public class SummaryReportObject
{
    private int c_count;
    private Date c_date;
    private int c_unitprice;
    
    private String a_type;
    private int a_count;
    private int a_price;
    private Date a_date;
    
    private int d_value;
    private Date d_date;

    public SummaryReportObject(int c_count, Date c_date, int c_unitprice, String a_type, int a_count, int a_price, Date a_date, int d_value, Date d_date)
    {
        this.c_count = c_count;
        this.c_date = c_date;
        this.c_unitprice = c_unitprice;
        this.a_type = a_type;
        this.a_count = a_count;
        this.a_price = a_price;
        this.a_date = a_date;
        this.d_value = d_value;
        this.d_date = d_date;
    }

    public int getC_count()
    {
        return c_count;
    }

    public void setC_count(int c_count)
    {
        this.c_count = c_count;
    }

    public Date getC_date()
    {
        return c_date;
    }

    public void setC_date(Date c_date)
    {
        this.c_date = c_date;
    }

    public int getC_unitprice()
    {
        return c_unitprice;
    }

    public void setC_unitprice(int c_unitprice)
    {
        this.c_unitprice = c_unitprice;
    }

    public String getA_type()
    {
        return a_type;
    }

    public void setA_type(String a_type)
    {
        this.a_type = a_type;
    }

    public int getA_count()
    {
        return a_count;
    }

    public void setA_count(int a_count)
    {
        this.a_count = a_count;
    }

    public int getA_price()
    {
        return a_price;
    }

    public void setA_price(int a_price)
    {
        this.a_price = a_price;
    }

    public Date getA_date()
    {
        return a_date;
    }

    public void setA_date(Date a_date)
    {
        this.a_date = a_date;
    }

    public int getD_value()
    {
        return d_value;
    }

    public void setD_value(int d_vale)
    {
        this.d_value = d_vale;
    }

    public Date getD_date()
    {
        return d_date;
    }

    public void setD_date(Date d_date)
    {
        this.d_date = d_date;
    }
}
