/**
 * Ezclouds.id
 * Copyright (c) 2020‐2024 All Rights Reserved.
 */
package id.ezclouds.common.model.converter;

/**
 * @author Heri Wijoyo (heri.wijoyo@gmail.com)
 * @version $Id: CommonDOModelConverter.java, v 0.1 2024‐08‐16 5:07 PM Heri Wijoyo (heri.wijoyo@gmail.com) Exp $$
 */
public abstract class CommonDOModelConverter<D, M> implements DOModelConverter<D, M> {

    protected abstract M safeConvertQuery(D dataObject);
    protected abstract D safeConvertStore(M model);

    @Override
    public M convertQuery(D dataObject) {
        if (dataObject == null) {
            return null;
        }
        return safeConvertQuery(dataObject);
    }

    @Override
    public D convertStore(M model) {
        if (model == null) {
            return null;
        }
        return safeConvertStore(model);
    }
}