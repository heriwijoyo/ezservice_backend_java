/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: ListModelConvertUtil.java, v 0.1 2024‐08‐11 9:07 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public final class ListModelConvertUtil {

    public static <I, O> List<O> convert(List<I> inputs, ModelConverter<I, O> converter) {
        List<O> outputs = new ArrayList<>();
        if (inputs != null) {
            for (I input : inputs) {
                outputs.add(converter.convert(input));
            }
        }
        return outputs;
    }
}