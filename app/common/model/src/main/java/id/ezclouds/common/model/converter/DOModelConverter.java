/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.converter;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: DOModelConverter.java, v 0.1 2024‐08‐16 4:58 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface DOModelConverter<D, M> {
    M convertQuery(D dataObject);
    D convertStore(M model);
}