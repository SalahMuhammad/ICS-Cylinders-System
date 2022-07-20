package Objects;

import java.util.Date;

public class CylindersAccounting {
    
    int id;
    String desc;
    Date date;
    int creditOrDebt;

    public CylindersAccounting(int id, String desc, Date date, int creditOrDebt) {
        this.id = id;
        this.desc = desc;
        this.date = date;
        this.creditOrDebt = creditOrDebt;
    }

    public CylindersAccounting(Date date, int creditOrDebt)
    {
        this.date = date;
        this.creditOrDebt = creditOrDebt;
    }

    public int getCreditOrDebt() {
        return creditOrDebt;
    }

    public int getId() {
        return id;
    }

    public String getDesc() {
        return desc;
    }

    public Date getDate() {
        return date;
    }
   
}
