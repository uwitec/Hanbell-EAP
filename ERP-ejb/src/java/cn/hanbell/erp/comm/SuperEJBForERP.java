/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.erp.comm;

import cn.hanbell.util.SuperEJB;
import java.io.StringReader;
import java.lang.reflect.Field;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author C0160
 * @param <T>
 */
public abstract class SuperEJBForERP<T> extends SuperEJB<T> {

    @PersistenceContext(unitName = "ERP-ejbPU")
    private EntityManager em;

    public SuperEJBForERP(Class<T> entityClass) {
        super(entityClass);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }

    protected T buildObjectByXmlString(String xmlString) {
        try {
            JAXBContext context = JAXBContext.newInstance(entityClass.getClass());
            Unmarshaller unmarshaller = context.createUnmarshaller();
            //Unmarshal the XML in the stringWriter back into an object  
            T entity = (T) unmarshaller.unmarshal(new StringReader(xmlString));
            return entity;
        } catch (JAXBException ex) {
            Logger.getLogger(entityClass.getClass().getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

}
