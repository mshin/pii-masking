package com.github.mshin.masking.service.api;

import java.util.Collection;

/**
 * @author mshin
 */
public interface MaskingService<T> {

    void mask(T t);

    default void mask(Collection<T> collection) {
        if (null == collection) {
            return;
        }

        for (T t : collection) {
            mask(t);
        }
    }

    void setMaskingStrategy(MaskingStrategy maskingStrategy);

    MaskingStrategy getMaskingStrategy();
}
