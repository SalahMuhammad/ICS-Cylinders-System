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
public class Out {
    
    int id;
    int count;
    Date date;
    String reciptNumber;
    int unitPrice;
    String notice;

    public Out(int id, int count, Date date, String reciptNumber, int unitPrice, String notice) {
        this.id = id;
        this.count = count;
        this.date = date;
        this.reciptNumber = reciptNumber;
        this.unitPrice = unitPrice;
        this.notice = notice;
    }

    public Out(int count, Date date, int unitPrice)
    {
        this.count = count;
        this.date = date;
        this.unitPrice = unitPrice;
    }

    public int getUnitPrice() {
        return unitPrice;
    }

    public String getNotice() {
        return notice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getReciptNumber() {
        return reciptNumber;
    }

    public void setReciptNumber(String reciptNumber) {
        this.reciptNumber = reciptNumber;
    }
    
}
