/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.facade.dal;

import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: EzSampleDAO.java, v 0.1 2024‐07‐26 3:50 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public interface EzSampleDAO {

    String getSample();
    long getSampleCount();
    List<String> getSampleList();
    String getException();
}