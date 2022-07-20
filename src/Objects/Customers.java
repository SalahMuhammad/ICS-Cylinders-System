package Objects;

public class Customers
{
    private int id;
    private String name;
    private int cylinderUnitprice;

    public Customers(int id, String name, int cylinderUnitprice)
    {
        this.id = id;
        this.name = name;
        this.cylinderUnitprice = cylinderUnitprice;
    }

    public int getId()
    {
        return id;
    }

    public String getName()
    {
        return name;
    }
    
    public int getCylinderUnitprice()
    {
        return cylinderUnitprice;
    }
}
