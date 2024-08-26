/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.report;

import id.ezclouds.common.facade.dal.report.BizReportAreaCounterDAO;
import id.ezclouds.common.model.annotation.EzDAOLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CoreReportAreaCounterDAO.java, v 0.1 2024‐08‐27 12:07 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Component
public class CoreReportAreaCounterDAO implements BizReportAreaCounterDAO {

    @Autowired
    private EntityManager entityManager;

    @EzDAOLogger
    @Override
    public long countTotalVoter(String orgId, List<String> sources, String districtId, String villageId) {
        Query query = entityManager
                .createNativeQuery("SELECT COUNT(*) FROM biz_member_union WHERE org_id = ?1 AND source IN (?2) AND district_id = ?3 AND village_id = ?4");
        query.setParameter(1, orgId);
        query.setParameter(2, "");
        query.setParameter(3, districtId);
        query.setParameter(4, villageId);
        return (long) query.getSingleResult();
    }
}