/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.oa.jrs;

import cn.hanbell.oa.entity.OrganizationUnit;
import java.util.List;

/**
 *
 * @author C0160
 */
public class OrganizationUnitResponseResult {

    private Integer size;
    private List<OrganizationUnit> result;

    public OrganizationUnitResponseResult() {

    }

    /**
     * @return the size
     */
    public Integer getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Integer size) {
        this.size = size;
    }

    /**
     * @return the result
     */
    public List<OrganizationUnit> getResult() {
        return result;
    }

    /**
     * @param result the result to set
     */
    public void setResult(List<OrganizationUnit> result) {
        this.result = result;
    }

}
