/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.core.shared.converter;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: PageResultConverter.java, v 0.1 2024‐05‐17 10:36 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface PageResultConverter<I, O> {
    List<O> convert(List<I> input);
}