/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ModelConverter.java, v 0.1 2024‐08‐11 6:43 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface ModelConverter<I, O> {
    O convert(I input);
}