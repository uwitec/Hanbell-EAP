/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cn.hanbell.jrs;

import cn.hanbell.erp.entity.ArmhadSum;
import java.math.BigDecimal;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

/**
 *
 * @author C0160
 */
@Stateless
@Path("shberp.armhadsum")
public class ArmhadSumFacadeREST extends AbstractFacade<ArmhadSum> {
    @PersistenceContext(unitName = "RESTPU_shberp")
    private EntityManager em;

    public ArmhadSumFacadeREST() {
        super(ArmhadSum.class);
    }

    @GET
    @Path("{id}")
    @Produces({"application/json"})
    public ArmhadSum find(@PathParam("id") String id) {
           Query query = em.createNativeQuery("SELECT a.cusno,a.coin," 
                + "SUM(CASE WHEN left(convert(char(8),a.warngdate,112),6) < left(convert(char(8),getdate(),112),6)  THEN a.booamts - a.recamts ELSE 0 END) AS warningamts,"
                + "SUM(CASE WHEN left(convert(char(8),a.warngdate,112),6) = left(convert(char(8),getdate(),112),6)  THEN a.booamts - a.recamts ELSE 0 END) AS thisrec, "
                + "SUM(a.booamts - a.recamts) AS totalrec "
                + " FROM armhad a WHERE a.cusno = '" + id + "' GROUP BY a.cusno,a.coin ");
        List result = query.getResultList();
        ArmhadSum armsum = new ArmhadSum();
        if (result != null) {
            Object[] row = (Object[]) result.get(0);
            armsum.setCusno(row[0].toString());
            armsum.setCoin(row[1].toString());
            armsum.setWarningamts(BigDecimal.class.cast(row[2]));
            armsum.setThisrec(BigDecimal.class.cast(row[3]));
            armsum.setTotalrec(BigDecimal.class.cast(row[4]));
        } else {
            armsum.setCusno(id);
            armsum.setWarningamts(BigDecimal.ZERO);
            armsum.setThisrec(BigDecimal.ZERO);
            armsum.setTotalrec(BigDecimal.ZERO);
        }
        return armsum;
    }

    @GET
    @Override
    @Produces({"application/xml", "application/json"})
    public List<ArmhadSum> findAll() {
        return super.findAll();
    }

    @GET
    @Path("{from}/{to}")
    @Produces({"application/xml", "application/json"})
    public List<ArmhadSum> findRange(@PathParam("from") Integer from, @PathParam("to") Integer to) {
        return super.findRange(new int[]{from, to});
    }

    @GET
    @Path("count")
    @Produces("text/plain")
    public String countREST() {
        return String.valueOf(super.count());
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}
