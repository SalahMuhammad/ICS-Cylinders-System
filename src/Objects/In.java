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
public class In {
    
    int id;
    int count;
    Date date;
    String notice;

    public In(int id, int count, Date date, String notice)
    {
        this.id = id;
        this.count = count;
        this.date = date;
        this.notice = notice;
    }

    public String getNotice()
    {
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
    
}
