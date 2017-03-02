/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.control;

import cn.hanbell.eap.ejb.BookingBean;
import cn.hanbell.eap.ejb.BookingDetailBean;
import cn.hanbell.eap.entity.Booking;
import cn.hanbell.eap.entity.BookingDetail;
import cn.hanbell.eap.lazy.BookingModel;
import cn.hanbell.eap.web.FormMultiBean;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "bookingManagedBean")
@SessionScoped
public class BookingManagedBean extends FormMultiBean<Booking, BookingDetail> {

    @EJB
    private BookingBean bookingBean;

    @EJB
    private BookingDetailBean bookingDetailBean;

    private String queryKind;

    public BookingManagedBean() {
        super(Booking.class, BookingDetail.class);
    }

    @Override
    public void createDetail() {
        super.createDetail();
        currentDetail = newDetail;
    }

    @Override
    protected boolean doAfterPersist() throws Exception {
        this.detailList.clear();
        return super.doAfterPersist();
    }

    @Override
    public void init() {
        superEJB = this.bookingBean;
        detailEJB = this.bookingDetailBean;
        setModel(new BookingModel(bookingBean));
        model.getSortFields().put("status", "ASC");
        model.getSortFields().put("formid", "DESC");
        super.init();
    }

    @Override
    public void query() {
        this.model.getFilterFields().clear();
        if (this.queryFormId != null && !"".equals(this.queryFormId)) {
            this.model.getFilterFields().put("formid", this.queryFormId);
        }
        if (this.queryKind != null && !"".equals(this.queryKind)) {
            this.model.getFilterFields().put("bookingKind.name", this.queryKind);
        }
        if (this.queryName != null && !"".equals(this.queryName)) {
            this.model.getFilterFields().put("name", this.queryName);
        }
        if (this.queryState != null && !"ALL".equals(this.queryState)) {
            this.model.getFilterFields().put("status", this.queryState);
        }
        if (this.queryDateBegin != null) {
            this.model.getFilterFields().put("startDateBegin", this.queryDateBegin);
        }
        if (this.queryDateEnd != null) {
            this.model.getFilterFields().put("endDateEnd", this.queryDateEnd);
        }
    }

    /**
     * @return the queryKind
     */
    public String getQueryKind() {
        return queryKind;
    }

    /**
     * @param queryKind the queryKind to set
     */
    public void setQueryKind(String queryKind) {
        this.queryKind = queryKind;
    }

}
