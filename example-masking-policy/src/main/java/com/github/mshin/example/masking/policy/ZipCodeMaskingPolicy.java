package com.github.mshin.example.masking.policy;

import com.github.mshin.masking.policy.MaskEachCharacterMaskingPolicy;

/**
 * This one extends MaskEachCharacterMaskingPolicy instead of
 * MaskingPolicyConfiguration.
 * 
 * @author MunChul Shin
 */
public final class ZipCodeMaskingPolicy extends MaskEachCharacterMaskingPolicy {

    public ZipCodeMaskingPolicy() {

        this.regexForCharactersToMask = "[a-zA-Z0-9]";
    }

    @Override
    public String toString() {
        return "ZipCodeMaskingPolicy [maskedCharactersPosition=" + maskedCharactersPosition + ", maskingPolicy="
                + maskingPolicy + ", maskingCharacter=" + maskingCharacter + ", regexForCharactersToMask="
                + regexForCharactersToMask + "]";
    }
}
