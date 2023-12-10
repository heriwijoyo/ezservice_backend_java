/**
 * Ezclouds.id
 * Copyright (c) 2020‐2023 All Rights Reserved.
 */
package id.ezclouds.common.dal;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.function.Predicate;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: Specification.java, v 0.1 2023‐12‐10 9:24 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface Specification<T> {

    Predicate toPredicate(Root<T> root, CriteriaQuery<T> query, CriteriaBuilder cb);
}