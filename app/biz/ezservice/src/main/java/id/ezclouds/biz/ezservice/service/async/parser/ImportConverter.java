/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.parser;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ImportConverter.java, v 0.1 2024‐07‐11 4:49 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface ImportConverter<I, O> {

    O convert(I input);
}