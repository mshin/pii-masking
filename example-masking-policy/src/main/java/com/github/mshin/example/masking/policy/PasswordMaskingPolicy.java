package com.github.mshin.example.masking.policy;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;

/**
 * Replaces content with '************' every time.
 * 
 * @author MunChul Shin
 */
public class PasswordMaskingPolicy extends MaskingPolicyConfiguration {

    public PasswordMaskingPolicy() {
        this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.ALL;
        this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING;
        this.fixedLengthStringSize = 12;
        this.maskingCharacter = '*';
    }

    @Override
    public String toString() {
        return "PasswordMaskingPolicy [maskedCharactersPosition=" + maskedCharactersPosition + ", maskingPolicy="
                + maskingPolicy + ", fixedLengthStringSize=" + fixedLengthStringSize + ", maskingCharacter="
                + maskingCharacter + "]";
    }

}
