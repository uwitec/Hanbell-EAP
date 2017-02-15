/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.eap.control;

import cn.hanbell.eap.ejb.BookingKindBean;
import cn.hanbell.eap.ejb.BookingSettingBean;
import cn.hanbell.eap.entity.BookingKind;
import cn.hanbell.eap.entity.BookingSetting;
import cn.hanbell.eap.lazy.BookingKindModel;
import cn.hanbell.eap.web.SuperMultiBean;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author C0160
 */
@ManagedBean(name = "bookingKindManagedBean")
@SessionScoped
public class BookingKindManagedBean extends SuperMultiBean<BookingKind, BookingSetting> {

    @EJB
    private BookingKindBean bookingKindBean;

    @EJB
    private BookingSettingBean bookingSettingBean;

    public BookingKindManagedBean() {
        super(BookingKind.class, BookingSetting.class);
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
    protected boolean doBeforePersist() throws Exception {
        if (!super.doBeforePersist()) {
            return false;
        }
        if (this.getCurrentSystemProgram() != null) {
            if (this.getCurrentSystemProgram().getNoauto() && !this.getCurrentSystemProgram().getNochange()) {
                String formid = superEJB.getFormId(newEntity.getCredate(), this.getCurrentSystemProgram().getNolead(), this.getCurrentSystemProgram().getNoformat(), this.getCurrentSystemProgram().getNoseqlen(), "kind");
                this.newEntity.setKind(formid);
            } else {
                Map<String, Object> filters = new HashMap<>();
                filters.put("kind", this.newEntity.getKind());
                if (superEJB.getRowCount(filters) > 0) {
                    showErrorMsg("Error", "编号已存在无法保存");
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void init() {
        superEJB = this.bookingKindBean;
        detailEJB = this.bookingSettingBean;
        setModel(new BookingKindModel(bookingKindBean));
        model.getSortFields().put("status", "ASC");
        model.getSortFields().put("kind", "DESC");
        super.init();
    }

}
