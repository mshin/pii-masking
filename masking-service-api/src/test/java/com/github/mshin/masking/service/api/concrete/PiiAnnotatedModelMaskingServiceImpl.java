package com.github.mshin.masking.service.api.concrete;

import com.github.mshin.masking.service.api.MaskingService;
import com.github.mshin.masking.service.api.MaskingStrategy;
import com.github.mshin.masking.service.api.PiiAnnotatedModel;

/**
 * @author mshin
 */
public class PiiAnnotatedModelMaskingServiceImpl implements MaskingService<PiiAnnotatedModel> {

    private PiiAnnotatedModelMaskingHandler handler = new PiiAnnotatedModelMaskingHandler() {
    };

    private MaskingStrategy maskingStrategy = new MaskingStrategy() {
    };

    public PiiAnnotatedModelMaskingServiceImpl() {
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
