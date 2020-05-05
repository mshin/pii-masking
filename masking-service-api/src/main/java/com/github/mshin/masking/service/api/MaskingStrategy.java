package com.github.mshin.masking.service.api;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;
import com.github.mshin.masking.policy.util.MaskingPolicyUtil;

/**
 * @author MunChul Shin
 */
public interface MaskingStrategy {

    default String mask(MaskingPolicyConfiguration maskingPolicyConfiguration, String string) {
        return MaskingPolicyUtil.maskWithPolicy(maskingPolicyConfiguration, string);
    }
}
