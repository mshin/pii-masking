package com.github.mshin.masking.service.api;

import java.io.Serializable;

import com.github.mshin.example.masking.policy.EmailAddressMaskingPolicy;
import com.github.mshin.example.masking.policy.UserIdMaskingPolicy;
import com.github.mshin.example.masking.policy.ZipCodeMaskingPolicy;
import com.github.mshin.masking.annotation.Pii;

/**
 * @author mshin
 */
public class PiiAnnotatedModel implements Serializable {

    private static final long serialVersionUID = 2515089275992191232L;

    @Pii(ZipCodeMaskingPolicy.class)
    private String zipCode = "19355";

    @Pii(EmailAddressMaskingPolicy.class)
    private String emailAddress = "mshin@redhat.com";

    @Pii(UserIdMaskingPolicy.class)
    private String userId = "ky1234";

    private String nonPiiField = "hi";

    public PiiAnnotatedModel() {

    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getNonPiiField() {
        return nonPiiField;
    }

    public void setNonPiiField(String nonPiiField) {
        this.nonPiiField = nonPiiField;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((emailAddress == null) ? 0 : emailAddress.hashCode());
        result = prime * result + ((nonPiiField == null) ? 0 : nonPiiField.hashCode());
        result = prime * result + ((userId == null) ? 0 : userId.hashCode());
        result = prime * result + ((zipCode == null) ? 0 : zipCode.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        PiiAnnotatedModel other = (PiiAnnotatedModel) obj;
        if (emailAddress == null) {
            if (other.emailAddress != null)
                return false;
        } else if (!emailAddress.equals(other.emailAddress))
            return false;
        if (nonPiiField == null) {
            if (other.nonPiiField != null)
                return false;
        } else if (!nonPiiField.equals(other.nonPiiField))
            return false;
        if (userId == null) {
            if (other.userId != null)
                return false;
        } else if (!userId.equals(other.userId))
            return false;
        if (zipCode == null) {
            if (other.zipCode != null)
                return false;
        } else if (!zipCode.equals(other.zipCode))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MetadataStableModel [zipCode=" + zipCode + ", emailAddress=" + emailAddress + ", userId=" + userId
                + ", nonPiiField=" + nonPiiField + "]";
    }

}
