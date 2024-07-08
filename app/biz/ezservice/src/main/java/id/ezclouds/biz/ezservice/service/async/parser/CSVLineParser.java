/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.biz.ezservice.service.async.parser;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CSVLineParser.java, v 0.1 2024‐07‐07 8:04 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface CSVLineParser<T> {

    T parseLine(String[] line);
}