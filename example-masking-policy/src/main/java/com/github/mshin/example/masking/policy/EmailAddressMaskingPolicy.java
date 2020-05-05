package com.github.mshin.example.masking.policy;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;

/**
 * m@redhat.com -> m@redhat.com <br>
 * ms@redhat.com -> m*@redhat.com <br>
 * msh@redhat.com -> m**@redhat.com <br>
 * mshi@redhat.com -> m***@redhat.com <br>
 * mshin@redhat.com -> m****@redhat.com <br>
 *
 * @author MunChul Shin
 */
public final class EmailAddressMaskingPolicy extends MaskingPolicyConfiguration {

    public EmailAddressMaskingPolicy() {
        this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.CENTER;
        this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
        this.numberOfLeadingCharactersUnmasked = 1;
        this.trailingCharactersUnmaskedAnchorPattern = "@";
        this.maskingCharacter = '*';
    }

    @Override
    public String toString() {
        return "EmailAddressMaskingPolicy [maskedCharactersPosition=" + maskedCharactersPosition + ", maskingPolicy="
                + maskingPolicy + ", numberOfLeadingCharactersUnmasked=" + numberOfLeadingCharactersUnmasked
                + ", trailingCharactersUnmaskedAnchorPattern=" + trailingCharactersUnmaskedAnchorPattern
                + ", maskingCharacter=" + maskingCharacter + "]";
    }

}
