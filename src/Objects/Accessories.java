package Objects;

import java.util.Date;

public class Accessories
{
    int id;
    String name;
    int price;
    int count;
    Date date;

    public Accessories(int id, String name, int price, int count, Date date)
    {
        this.id = id;
        this.name = name;
        this.price = price;
        this.count = count;
        this.date = date;
    }

    public Accessories(String name, int price, int count, Date date)
    {
        this.name = name;
        this.price = price;
        this.count = count;
        this.date = date;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }

    public int getPrice()
    {
        return price;
    }

    public int getCount()
    {
        return count;
    }

    public Date getDate()
    {
        return date;
    }
    
}
