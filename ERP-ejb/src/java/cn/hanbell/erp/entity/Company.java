/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.entity;

import java.io.Serializable;
import javax.persistence.Id;

/**
 *
 * @author C0160
 */

public class Company implements Serializable {

    @Id
    private String facno;
    private String name;

    public Company() {

    }

    public Company(String facno, String name) {
        this.facno = facno;
        this.name = name;
    }

    /**
     * @return the facno
     */
    public String getFacno() {
        return facno;
    }

    /**
     * @param facno the facno to set
     */
    public void setFacno(String facno) {
        this.facno = facno;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
