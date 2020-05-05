package com.github.mshin.example.masking.policy;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;

/**
 * Will only replace alpha-numeric characters. <br>
 * Will always leave the last 4 characters unmasked. <br>
 * 123-456-7890 -> ***-***-7890 <br>
 * (555) 888-3489 -> (***) ***-3489 <br>
 * 1800HANDYMN -> *******DYMN <br>
 * 
 * @author MunChul Shin
 */
public class PhoneNumberMaskingPolicy extends MaskingPolicyConfiguration {

    public PhoneNumberMaskingPolicy() {
        this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.LEADING;
        this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
        this.numberOfTrailingCharactersUnmasked = 4;
        this.maskingCharacter = '*';
        this.regexForCharactersToMask = "[a-zA-Z0-9]";
    }

    @Override
    public String toString() {
        return "PhoneNumberMaskingPolicy [maskedCharactersPosition=" + maskedCharactersPosition + ", maskingPolicy="
                + maskingPolicy + ", numberOfTrailingCharactersUnmasked=" + numberOfTrailingCharactersUnmasked
                + ", maskingCharacter=" + maskingCharacter + ", regexForCharactersToMask=" + regexForCharactersToMask
                + "]";
    }

}
