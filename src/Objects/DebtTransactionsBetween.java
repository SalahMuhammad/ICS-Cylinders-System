package Objects;

import java.util.Date;

public class DebtTransactionsBetween
{
    String name;
    String description;
    Date date;
    int value;

    public DebtTransactionsBetween(String name, String description, Date date, int value)
    {
        this.name = name;
        this.description = description;
        this.date = date;
        this.value = value;
    }

    public String getName()
    {
        return name;
    }

    public String getDescription()
    {
        return description;
    }

    public Date getDate()
    {
        return date;
    }

    public int getValue()
    {
        return value;
    }
    
}
