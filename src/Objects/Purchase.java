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
public class Purchase {
    
    int id;
    int count;
    Date date;
    String notice;

    public Purchase(int id, int count, Date date, String notice) {
        this.id = id;
        this.count = count;
        this.date = date;
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

    public String getNotice() {
        return notice;
    }
    
}
