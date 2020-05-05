package com.github.mshin.masking.service.api.concrete.no.reflection;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.example.masking.policy.EmailAddressMaskingPolicy;
import com.github.mshin.example.masking.policy.UserIdMaskingPolicy;
import com.github.mshin.example.masking.policy.ZipCodeMaskingPolicy;
import com.github.mshin.masking.service.api.MaskingHandler;
import com.github.mshin.masking.service.api.MaskingStrategy;
import com.github.mshin.masking.service.api.PiiAnnotatedModel;

/**
 * This Handler assumes you have total knowledge and control of the model. If
 * the model changes, the policies listed here will not be updated. This is
 * computationally faster but theoretically harder to maintain as it severs the
 * link between the policy and the field masked on the model.
 * 
 * @author MunChul Shin
 */
public class PiiAnnotatedModelMaskingHandlerNoReflection implements MaskingHandler<PiiAnnotatedModel> {

    static final Logger LOGGER = LoggerFactory.getLogger(PiiAnnotatedModelMaskingHandlerNoReflection.class);

    ZipCodeMaskingPolicy zipCodeMaskingPolicy = new ZipCodeMaskingPolicy();
    EmailAddressMaskingPolicy emailAddressMaskingPolicy = new EmailAddressMaskingPolicy();
    UserIdMaskingPolicy userIdMaskingPolicy = new UserIdMaskingPolicy();

    @Override
    public void maskObjectWithStrategy(MaskingStrategy maskingStrategy, PiiAnnotatedModel object) {
        if (null == object || null == maskingStrategy) {
            return;
        }

        String maskedUserId = maskingStrategy.mask(userIdMaskingPolicy, object.getUserId());
        object.setUserId(maskedUserId);

        String maskedzipCode = maskingStrategy.mask(zipCodeMaskingPolicy, object.getZipCode());
        object.setZipCode(maskedzipCode);

        String maskedEmail = maskingStrategy.mask(emailAddressMaskingPolicy, object.getEmailAddress());
        object.setEmailAddress(maskedEmail);

    }

}
