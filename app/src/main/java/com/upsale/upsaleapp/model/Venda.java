/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.upsale.upsaleapp.model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Mauricio R. Vidal
 */
public class Venda implements Serializable {
    private long id;
    private Date data;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }
    
    
}
