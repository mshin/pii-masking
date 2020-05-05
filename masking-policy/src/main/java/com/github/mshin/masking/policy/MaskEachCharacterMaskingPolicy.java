package com.github.mshin.masking.policy;

/**
 * This is the default masking strategy.
 * 
 * @author MunChul Shin
 */
public class MaskEachCharacterMaskingPolicy extends MaskingPolicyConfiguration {

    public MaskEachCharacterMaskingPolicy() {
        this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.ALL;
        this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
        this.maskingCharacter = '*';
    }

    @Override
    public String toString() {
        return "MaskEachCharacterMaskingPolicy [maskedCharactersPosition=" + maskedCharactersPosition
                + ", maskingPolicy=" + maskingPolicy + ", maskingCharacter=" + maskingCharacter + "]";
    }

}
