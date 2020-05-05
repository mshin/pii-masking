package com.github.mshin.masking.service.impl;

import com.github.mshin.masking.service.api.MaskingService;
import com.github.mshin.masking.service.api.MaskingStrategy;
import com.github.mshin.masking.service.api.PiiAnnotatedObjectMaskingHandler;

/**
 * @author MunChul Shin
 */
public class DefaultPiiAnnotationMaskingServiceImpl implements MaskingService<Object> {

    private PiiAnnotatedObjectMaskingHandler<Object> genericHandler = new PiiAnnotatedObjectMaskingHandler<Object>() {
    };

    private MaskingStrategy maskingStrategy = new MaskingStrategy() {
    };

    public DefaultPiiAnnotationMaskingServiceImpl() {
    }

    @Override
    public void mask(Object t) {
        genericHandler.maskObjectWithStrategy(getMaskingStrategy(), t);
    }

    @Override
    public void setMaskingStrategy(MaskingStrategy maskingStrategy) {
        this.maskingStrategy = maskingStrategy;
    }

    @Override
    public MaskingStrategy getMaskingStrategy() {
        return maskingStrategy;
    }

}
