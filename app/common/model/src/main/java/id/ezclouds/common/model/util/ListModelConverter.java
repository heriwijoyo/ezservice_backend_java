/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ListModelConverter.java, v 0.1 2024‐08‐11 6:37 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface ListModelConverter<I, O> {
    List<O> convert(List<I> input);
}