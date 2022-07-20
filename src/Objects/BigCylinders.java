/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.Date;

/**
 *
 * @author AL MASRIA 4 COMP
 */
public class BigCylinders {
 
    int id;
    int count;
    Date date;
    String fromOrTo;
    int unitPrice;
    String notice;

    public BigCylinders(int id, int count, int unitPrice, Date date, String fromOrTo, String notice) {
        this.id = id;
        this.count = count;
        this.date = date;
        this.fromOrTo = fromOrTo;
        this.unitPrice = unitPrice;
        this.notice = notice;
    }

    public int getId() {
        return id;
    }

    public int getCount() {
        return count;
    }

    public Date getDate() {
        return date;
    }

    public String getFromOrTo() {
        return fromOrTo;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public String getNotice() {
        return notice;
    }
    
}
