/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.dal.broker.repo;

import id.ezclouds.core.dal.broker.dataobject.EzBrokerMessageDO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzBrokerMessageRepository.java, v 0.1 2024‐08‐31 12:05 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
@Repository
public interface EzBrokerMessageRepository extends JpaRepository<EzBrokerMessageDO, String> {
}