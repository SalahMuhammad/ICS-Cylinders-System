package Objects;

import java.util.Date;

public class DisplayTransactionsBetweenObject
{
    String name;
    int count;
    int unitprice;
    Date date;
    String rNumber;
    String notice;

    public DisplayTransactionsBetweenObject(String name, int count, int unitprice, Date date, String rNumber, String notice)
    {
        this.name = name;
        this.count = count;
        this.unitprice = unitprice;
        this.date = date;
        this.rNumber = rNumber;
        this.notice = notice;
    }

    public String getName()
    {
        return name;
    }

    public int getCount()
    {
        return count;
    }

    public int getUnitprice()
    {
        return unitprice;
    }

    public Date getDate()
    {
        return date;
    }

    public String getrNumber()
    {
        return rNumber;
    }

    public String getNotice()
    {
        return notice;
    }
    
}
