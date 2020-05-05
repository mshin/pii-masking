package com.github.mshin.example.masking.policy;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;

/**
 * 1 -> 1 <br>
 * 12 -> 12 <br>
 * 123 -> 1*3 <br>
 * 1234 -> 1**4 <br>
 * 12345 -> 1***5 <br>
 * 123456 -> 1****6 <br>
 *
 * @author MunChul Shin
 */
public final class UserIdMaskingPolicy extends MaskingPolicyConfiguration {

    public UserIdMaskingPolicy() {
        this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.CENTER;
        this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
        this.numberOfLeadingCharactersUnmasked = 1;
        this.numberOfTrailingCharactersUnmasked = 1;
        this.maskingCharacter = '*';
    }

    @Override
    public String toString() {
        return "UserIdMaskingPolicy [maskedCharactersPosition=" + maskedCharactersPosition
                + ", numberOfLeadingCharactersUnmasked=" + numberOfLeadingCharactersUnmasked
                + ", numberOfTrailingCharactersUnmasked=" + numberOfTrailingCharactersUnmasked + ", maskingPolicy="
                + maskingPolicy + ", maskingCharacter=" + maskingCharacter + "]";
    }

}
