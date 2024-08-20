/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.util;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: TemplateModelConverter.java, v 0.1 2024‐08‐11 6:50 AM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class TemplateModelConverter<I, O> implements ModelConverter<I, O> {

    protected abstract O safeConvert(I input);

    @Override
    public O convert(I input) {
        if (input == null) {
            return null;
        }
        return safeConvert(input);
    }
}