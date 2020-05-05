package com.github.mshin.masking.service.api.concrete.no.reflection;

import com.github.mshin.masking.service.api.MaskingService;
import com.github.mshin.masking.service.api.MaskingStrategy;
import com.github.mshin.masking.service.api.PiiAnnotatedModel;

/**
 * @author MunChul Shin
 */
public class PiiAnnotatedModelMaskingServiceNoReflectionImpl implements MaskingService<PiiAnnotatedModel> {

    private PiiAnnotatedModelMaskingHandlerNoReflection handler = new PiiAnnotatedModelMaskingHandlerNoReflection() {
    };

    private MaskingStrategy maskingStrategy = new MaskingStrategy() {
    };

    public PiiAnnotatedModelMaskingServiceNoReflectionImpl() {
    }

    @Override
    public void mask(PiiAnnotatedModel t) {
        handler.maskObjectWithStrategy(getMaskingStrategy(), t);
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
