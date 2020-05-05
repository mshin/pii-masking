package com.github.mshin.masking.policy;

/**
 * Must set both MaskedCharactersPosition and MaskingPolicy to configure Masking
 * Strategy.
 * 
 * @author MunChul Shin
 */
public abstract class MaskingPolicyConfiguration {

    public enum MaskedCharactersPosition {

        /**
         * 
         */
        ALL,

        /**
         * Set numberOfTrailingCharactersUnmasked XOR
         * trailingCharactersUnmaskedAnchorPattern
         */
        LEADING,

        /**
         * Set numberOfLeadingCharactersUnmasked XOR
         * leadingCharactersUnmaskedAnchorPattern
         */
        TRAILING,

        /**
         * Set (numberOfLeadingCharactersUnmasked XOR
         * leadingCharactersUnmaskedAnchorPattern) AND
         * (numberOfTrailingCharactersUnmasked XOR
         * trailingCharactersUnmaskedAnchorPattern)
         */
        CENTER
    }

    public enum MaskingPolicy {

        /**
         * Set maskingCharacter AND fixedLengthStringSize
         */
        REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING,

        /**
         * Set maskingCharacter <br>
         * OPTIONAL: Set regexForCharactersToMask
         */
        REPLACE_EACH_CHARACTER

    }

    protected MaskedCharactersPosition maskedCharactersPosition;

    protected Integer numberOfLeadingCharactersUnmasked;

    /**
     * The pattern is not included in the characters to be masked.
     */
    protected String leadingCharactersUnmaskedAnchorPattern;

    protected Integer numberOfTrailingCharactersUnmasked;

    /**
     * The pattern is not included in the characters to be masked.
     */
    protected String trailingCharactersUnmaskedAnchorPattern;

    protected MaskingPolicy maskingPolicy;

    protected Integer fixedLengthStringSize;

    protected Character maskingCharacter;

    protected String regexForCharactersToMask;

    public MaskingPolicyConfiguration() {

    }

    public MaskingPolicyConfiguration(MaskedCharactersPosition maskedCharactersPosition, MaskingPolicy maskingPolicy) {
        super();
        this.maskedCharactersPosition = maskedCharactersPosition;
        this.maskingPolicy = maskingPolicy;
    }

    public MaskingPolicyConfiguration(MaskedCharactersPosition maskedCharactersPosition,
            Integer numberOfLeadingCharactersUnmasked, String leadingCharactersUnmaskedAnchorPattern,
            Integer numberOfTrailingCharactersUnmasked, String trailingCharactersUnmaskedAnchorPattern,
            MaskingPolicy maskingPolicy, Integer fixedLengthStringSize, Character maskingCharacter,
            String regexForCharactersToMask) {
        super();
        this.maskedCharactersPosition = maskedCharactersPosition;
        this.numberOfLeadingCharactersUnmasked = numberOfLeadingCharactersUnmasked;
        this.leadingCharactersUnmaskedAnchorPattern = leadingCharactersUnmaskedAnchorPattern;
        this.numberOfTrailingCharactersUnmasked = numberOfTrailingCharactersUnmasked;
        this.trailingCharactersUnmaskedAnchorPattern = trailingCharactersUnmaskedAnchorPattern;
        this.maskingPolicy = maskingPolicy;
        this.fixedLengthStringSize = fixedLengthStringSize;
        this.maskingCharacter = maskingCharacter;
        this.regexForCharactersToMask = regexForCharactersToMask;
    }

    public MaskedCharactersPosition getMaskedCharactersPosition() {
        return maskedCharactersPosition;
    }

    public void setMaskedCharactersPosition(MaskedCharactersPosition maskedCharactersPosition) {
        this.maskedCharactersPosition = maskedCharactersPosition;
    }

    public Integer getNumberOfLeadingCharactersUnmasked() {
        return numberOfLeadingCharactersUnmasked;
    }

    public void setNumberOfLeadingCharactersUnmasked(Integer numberOfLeadingCharactersUnmasked) {
        this.numberOfLeadingCharactersUnmasked = numberOfLeadingCharactersUnmasked;
    }

    public String getLeadingCharactersUnmaskedAnchorPattern() {
        return leadingCharactersUnmaskedAnchorPattern;
    }

    public void setLeadingCharactersUnmaskedAnchorPattern(String leadingCharactersUnmaskedAnchorPattern) {
        this.leadingCharactersUnmaskedAnchorPattern = leadingCharactersUnmaskedAnchorPattern;
    }

    public Integer getNumberOfTrailingCharactersUnmasked() {
        return numberOfTrailingCharactersUnmasked;
    }

    public void setNumberOfTrailingCharactersUnmasked(Integer numberOfTrailingCharactersUnmasked) {
        this.numberOfTrailingCharactersUnmasked = numberOfTrailingCharactersUnmasked;
    }

    public String getTrailingCharactersUnmaskedAnchorPattern() {
        return trailingCharactersUnmaskedAnchorPattern;
    }

    public void setTrailingCharactersUnmaskedAnchorPattern(String trailingCharactersUnmaskedAnchorPattern) {
        this.trailingCharactersUnmaskedAnchorPattern = trailingCharactersUnmaskedAnchorPattern;
    }

    public MaskingPolicy getMaskingPolicy() {
        return maskingPolicy;
    }

    public void setMaskingPolicy(MaskingPolicy maskingPolicy) {
        this.maskingPolicy = maskingPolicy;
    }

    public Integer getFixedLengthStringSize() {
        return fixedLengthStringSize;
    }

    public void setFixedLengthStringSize(Integer fixedLengthStringSize) {
        this.fixedLengthStringSize = fixedLengthStringSize;
    }

    public Character getMaskingCharacter() {
        return maskingCharacter;
    }

    public void setMaskingCharacter(Character maskingCharacter) {
        this.maskingCharacter = maskingCharacter;
    }

    public String getRegexForCharactersToMask() {
        return regexForCharactersToMask;
    }

    public void setRegexForCharactersToMask(String regexForCharactersToMask) {
        this.regexForCharactersToMask = regexForCharactersToMask;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((fixedLengthStringSize == null) ? 0 : fixedLengthStringSize.hashCode());
        result = prime * result + ((leadingCharactersUnmaskedAnchorPattern == null) ? 0
                : leadingCharactersUnmaskedAnchorPattern.hashCode());
        result = prime * result + ((maskedCharactersPosition == null) ? 0 : maskedCharactersPosition.hashCode());
        result = prime * result + ((maskingCharacter == null) ? 0 : maskingCharacter.hashCode());
        result = prime * result + ((maskingPolicy == null) ? 0 : maskingPolicy.hashCode());
        result = prime * result
                + ((numberOfLeadingCharactersUnmasked == null) ? 0 : numberOfLeadingCharactersUnmasked.hashCode());
        result = prime * result
                + ((numberOfTrailingCharactersUnmasked == null) ? 0 : numberOfTrailingCharactersUnmasked.hashCode());
        result = prime * result + ((regexForCharactersToMask == null) ? 0 : regexForCharactersToMask.hashCode());
        result = prime * result + ((trailingCharactersUnmaskedAnchorPattern == null) ? 0
                : trailingCharactersUnmaskedAnchorPattern.hashCode());
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
        MaskingPolicyConfiguration other = (MaskingPolicyConfiguration) obj;
        if (fixedLengthStringSize == null) {
            if (other.fixedLengthStringSize != null)
                return false;
        } else if (!fixedLengthStringSize.equals(other.fixedLengthStringSize))
            return false;
        if (leadingCharactersUnmaskedAnchorPattern == null) {
            if (other.leadingCharactersUnmaskedAnchorPattern != null)
                return false;
        } else if (!leadingCharactersUnmaskedAnchorPattern.equals(other.leadingCharactersUnmaskedAnchorPattern))
            return false;
        if (maskedCharactersPosition != other.maskedCharactersPosition)
            return false;
        if (maskingCharacter == null) {
            if (other.maskingCharacter != null)
                return false;
        } else if (!maskingCharacter.equals(other.maskingCharacter))
            return false;
        if (maskingPolicy != other.maskingPolicy)
            return false;
        if (numberOfLeadingCharactersUnmasked == null) {
            if (other.numberOfLeadingCharactersUnmasked != null)
                return false;
        } else if (!numberOfLeadingCharactersUnmasked.equals(other.numberOfLeadingCharactersUnmasked))
            return false;
        if (numberOfTrailingCharactersUnmasked == null) {
            if (other.numberOfTrailingCharactersUnmasked != null)
                return false;
        } else if (!numberOfTrailingCharactersUnmasked.equals(other.numberOfTrailingCharactersUnmasked))
            return false;
        if (regexForCharactersToMask == null) {
            if (other.regexForCharactersToMask != null)
                return false;
        } else if (!regexForCharactersToMask.equals(other.regexForCharactersToMask))
            return false;
        if (trailingCharactersUnmaskedAnchorPattern == null) {
            if (other.trailingCharactersUnmaskedAnchorPattern != null)
                return false;
        } else if (!trailingCharactersUnmaskedAnchorPattern.equals(other.trailingCharactersUnmaskedAnchorPattern))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "MaskingPolicyConfiguration [maskedCharactersPosition=" + maskedCharactersPosition
                + ", numberOfLeadingCharactersUnmasked=" + numberOfLeadingCharactersUnmasked
                + ", leadingCharactersUnmaskedAnchorPattern=" + leadingCharactersUnmaskedAnchorPattern
                + ", numberOfTrailingCharactersUnmasked=" + numberOfTrailingCharactersUnmasked
                + ", trailingCharactersUnmaskedAnchorPattern=" + trailingCharactersUnmaskedAnchorPattern
                + ", maskingPolicy=" + maskingPolicy + ", fixedLengthStringSize=" + fixedLengthStringSize
                + ", maskingCharacter=" + maskingCharacter + ", regexForCharactersToMask=" + regexForCharactersToMask
                + "]";
    }

}
