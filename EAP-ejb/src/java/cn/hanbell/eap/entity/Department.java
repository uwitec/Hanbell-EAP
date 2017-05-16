/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.entity;

import com.lightshell.comm.SuperEntity;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author C0160
 */
@Entity
@Table(name = "department")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Department.getRowCount", query = "SELECT COUNT(d) FROM Department d"),
    @NamedQuery(name = "Department.findAll", query = "SELECT d FROM Department d ORDER BY d.deptno"),
    @NamedQuery(name = "Department.findById", query = "SELECT d FROM Department d WHERE d.id = :id"),
    @NamedQuery(name = "Department.findByDeptno", query = "SELECT d FROM Department d WHERE d.deptno = :deptno"),
    @NamedQuery(name = "Department.findByDept", query = "SELECT d FROM Department d WHERE d.dept = :dept"),
    @NamedQuery(name = "Department.findByLeader", query = "SELECT d FROM Department d WHERE d.leader = :leader"),
    @NamedQuery(name = "Department.findByPId", query = "SELECT d FROM Department d WHERE d.parentDept.id = :pid"),
    @NamedQuery(name = "Department.findByStatus", query = "SELECT d FROM Department d WHERE d.status = :status"),
    @NamedQuery(name = "Department.findRoot", query = "SELECT d FROM Department d WHERE d.parentDept is NULL")})
public class Department extends SuperEntity {

    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "deptno")
    private String deptno;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "dept")
    private String dept;
    @Size(max = 10)
    @Column(name = "leader")
    private String leader;
    @Size(max = 300)
    @Column(name = "remark")
    private String remark;

    @JoinColumn(name = "pid", referencedColumnName = "id")
    @ManyToOne
    private Department parentDept;

    public Department() {
    }

    public Department(String deptno, String dept) {
        this.deptno = deptno;
        this.dept = dept;
    }

    public String getDeptno() {
        return deptno;
    }

    public void setDeptno(String deptno) {
        this.deptno = deptno;
    }

    public String getDept() {
        return dept;
    }

    public void setDept(String dept) {
        this.dept = dept;
    }

    public String getLeader() {
        return leader;
    }

    public void setLeader(String leader) {
        this.leader = leader;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Department getParentDept() {
        return parentDept;
    }

    public void setParentDept(Department parentDept) {
        this.parentDept = parentDept;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Department)) {
            return false;
        }
        Department other = (Department) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "cn.hanbell.eap.entity.Department[ id=" + id + " ]";
    }

}
