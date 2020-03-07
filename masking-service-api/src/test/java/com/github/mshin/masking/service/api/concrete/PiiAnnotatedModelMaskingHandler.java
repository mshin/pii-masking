package com.github.mshin.masking.service.api.concrete;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.masking.annotation.Pii;
import com.github.mshin.masking.policy.MaskingPolicyConfiguration;
import com.github.mshin.masking.service.api.MaskingHandler;
import com.github.mshin.masking.service.api.MaskingStrategy;
import com.github.mshin.masking.service.api.PiiAnnotatedModel;

/**
 * This handler assumes you know the model but want to maintain the link between
 * the annotations and the field. It still uses the
 * MaskingHandler.getMaskingPolicyConfigurationInstance code to get instances of
 * the policy class. This method caches the instances to reduce the reflection
 * footprint.
 * 
 * @author mshin
 */
public class PiiAnnotatedModelMaskingHandler implements MaskingHandler<PiiAnnotatedModel> {

    static final Logger LOGGER = LoggerFactory.getLogger(PiiAnnotatedModelMaskingHandler.class);

    @Override
    public void maskObjectWithStrategy(MaskingStrategy maskingStrategy, PiiAnnotatedModel object) {
        if (null == object || null == maskingStrategy) {
            return;
        }

        // userId field
        Pii userIdPiiAnnotation = null;
        try {
            userIdPiiAnnotation = PiiAnnotatedModel.class.getDeclaredField("userId").getAnnotation(Pii.class);
        } catch (NoSuchFieldException | SecurityException e) {
            LOGGER.error("Problem Getting field userId in model: " + PiiAnnotatedModel.class.getCanonicalName());
        }
        MaskingPolicyConfiguration userIdConfig = MaskingHandler
                .getMaskingPolicyConfigurationInstance(userIdPiiAnnotation.value());
        String maskedUserId = maskingStrategy.mask(userIdConfig, object.getUserId());
        object.setUserId(maskedUserId);

        // zipCode field
        Pii zipCodePiiAnnotation = null;
        try {
            zipCodePiiAnnotation = PiiAnnotatedModel.class.getDeclaredField("zipCode").getAnnotation(Pii.class);
        } catch (NoSuchFieldException | SecurityException e) {
            LOGGER.error("Problem Getting field zipCode in model: " + PiiAnnotatedModel.class.getCanonicalName());
        }
        MaskingPolicyConfiguration zipCodeConfig = MaskingHandler
                .getMaskingPolicyConfigurationInstance(zipCodePiiAnnotation.value());
        String maskedzipCode = maskingStrategy.mask(zipCodeConfig, object.getZipCode());
        object.setZipCode(maskedzipCode);

        // emailAddress field
        Pii emailPiiAnnotation = null;
        try {
            emailPiiAnnotation = PiiAnnotatedModel.class.getDeclaredField("emailAddress").getAnnotation(Pii.class);
        } catch (NoSuchFieldException | SecurityException e) {
            LOGGER.error("Problem Getting field emailAddress in model: " + PiiAnnotatedModel.class.getCanonicalName());
        }
        MaskingPolicyConfiguration emailConfig = MaskingHandler
                .getMaskingPolicyConfigurationInstance(emailPiiAnnotation.value());
        String maskedEmail = maskingStrategy.mask(emailConfig, object.getEmailAddress());
        object.setEmailAddress(maskedEmail);

    }

}
