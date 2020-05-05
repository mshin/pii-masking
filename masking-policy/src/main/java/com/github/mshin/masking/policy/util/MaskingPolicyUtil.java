package com.github.mshin.masking.policy.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.masking.policy.MaskEachCharacterMaskingPolicy;
import com.github.mshin.masking.policy.MaskingPolicyConfiguration;
import com.github.mshin.masking.policy.MaskingPolicyConfiguration.MaskedCharactersPosition;
import com.github.mshin.masking.policy.MaskingPolicyConfiguration.MaskingPolicy;
import com.github.mshin.masking.policy.exception.MisconfiguredMaskingPolicyException;

/**
 * @author MunChul Shin
 */
public class MaskingPolicyUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaskingPolicyUtil.class);

    private static MaskingPolicyConfiguration maskEachCharacterMaskingPolicy = new MaskEachCharacterMaskingPolicy();

    private static final Pattern matchAll = Pattern.compile(".*");

    private MaskingPolicyUtil() {

    }

    public static String mask(String string) {
        return maskWithPolicy(maskEachCharacterMaskingPolicy, string);
    }

    public static String maskWithPolicy(MaskingPolicyConfiguration config, String string)
            throws MisconfiguredMaskingPolicyException {

        if (null == string || string.trim().isEmpty()) {
            LOGGER.warn("Attempting to mask null or empty string.");
            return string;
        }
        if (null == config) {
            LOGGER.warn("Attempting to mask with null {}.", MaskingPolicyConfiguration.class.getName());
            config = new MaskEachCharacterMaskingPolicy();
        }
        string = string.trim();

        MaskedCharactersPosition position = config.getMaskedCharactersPosition();
        MaskingPolicy policy = config.getMaskingPolicy();

        if (null == position || null == policy) {
            LOGGER.error("Both {} and {} must be set; neither can be null.", MaskedCharactersPosition.class.getName(),
                    MaskingPolicy.class.getName());
            throw new MisconfiguredMaskingPolicyException(
                    "Neither MaskingPolicy nor MaskedCharactersPosition can be null.");
        }

        StringBuilder sb = new StringBuilder(string);
        String charactersToBeMasked = null;
        Integer beginIndexCharactersToBeMasked = null;
        Integer endIndexCharactersToBeMasked = null;

        switch (position) {
        case ALL:
            beginIndexCharactersToBeMasked = 0;
            endIndexCharactersToBeMasked = string.length();
            charactersToBeMasked = string;
            break;
        case LEADING:
            if (!(null == config.getNumberOfTrailingCharactersUnmasked()
                    ^ null == config.getTrailingCharactersUnmaskedAnchorPattern())) {
                String message = "Exactly one of numberOfTrailingCharactersUnmasked XOR "
                        + "trailingCharactersUnmaskedAnchorPattern must be set.";
                LOGGER.error(message);
                throw new MisconfiguredMaskingPolicyException(message);
            }
            if (null != config.getNumberOfTrailingCharactersUnmasked()) {
                endIndexCharactersToBeMasked = string.length() - config.getNumberOfTrailingCharactersUnmasked();
                if (endIndexCharactersToBeMasked < 0) {
                    endIndexCharactersToBeMasked = 0;
                }
            } else if (null != config.getTrailingCharactersUnmaskedAnchorPattern()) {
                Matcher m = getFoundMatch(config.getTrailingCharactersUnmaskedAnchorPattern(), string);
                endIndexCharactersToBeMasked = m.start();
            }
            beginIndexCharactersToBeMasked = 0;
            charactersToBeMasked = getCharactersToBeMasked(config, string, beginIndexCharactersToBeMasked,
                    endIndexCharactersToBeMasked);
            break;
        case TRAILING:
            if (!(null == config.getNumberOfLeadingCharactersUnmasked()
                    ^ null == config.getLeadingCharactersUnmaskedAnchorPattern())) {
                String message = "Exactly one of numberOfLeadingCharactersUnmasked XOR "
                        + "leadingCharactersUnmaskedAnchorPattern must be set.";
                LOGGER.error(message);
                throw new MisconfiguredMaskingPolicyException(message);
            }
            if (null != config.getNumberOfLeadingCharactersUnmasked()) {
                beginIndexCharactersToBeMasked = config.getNumberOfLeadingCharactersUnmasked();
            } else if (null != config.getLeadingCharactersUnmaskedAnchorPattern()) {
                Matcher m = getFoundMatch(config.getLeadingCharactersUnmaskedAnchorPattern(), string);
                beginIndexCharactersToBeMasked = m.end();
            }
            endIndexCharactersToBeMasked = string.length();
            charactersToBeMasked = getCharactersToBeMasked(config, string, beginIndexCharactersToBeMasked,
                    endIndexCharactersToBeMasked);
            break;
        case CENTER:
            if (!((null == config.getNumberOfLeadingCharactersUnmasked()
                    ^ null == config.getLeadingCharactersUnmaskedAnchorPattern())
                    && (null == config.getNumberOfTrailingCharactersUnmasked()
                            ^ null == config.getTrailingCharactersUnmaskedAnchorPattern()))) {
                String message = " (numberOfLeadingCharactersUnmasked XOR "
                        + "leadingCharactersUnmaskedAnchorPattern) AND " + "(numberOfTrailingCharactersUnmasked XOR "
                        + "trailingCharactersUnmaskedAnchorPattern) must be set.";
                LOGGER.error(message);
                throw new MisconfiguredMaskingPolicyException(message);
            }
            if (null != config.getNumberOfTrailingCharactersUnmasked()) {
                endIndexCharactersToBeMasked = string.length() - config.getNumberOfTrailingCharactersUnmasked();
            } else if (null != config.getTrailingCharactersUnmaskedAnchorPattern()) {
                Matcher m = getFoundMatch(config.getTrailingCharactersUnmaskedAnchorPattern(), string);
                endIndexCharactersToBeMasked = m.start();
            }
            if (null != config.getNumberOfLeadingCharactersUnmasked()) {
                beginIndexCharactersToBeMasked = config.getNumberOfLeadingCharactersUnmasked();
            } else if (null != config.getLeadingCharactersUnmaskedAnchorPattern()) {
                Matcher m = getFoundMatch(config.getLeadingCharactersUnmaskedAnchorPattern(), string);
                beginIndexCharactersToBeMasked = m.end();
            }
            charactersToBeMasked = getCharactersToBeMasked(config, string, beginIndexCharactersToBeMasked,
                    endIndexCharactersToBeMasked);
            break;
        default:
            LOGGER.error("Unrecognized {} type: {}.", MaskedCharactersPosition.class.getName(), position);
            throw new MisconfiguredMaskingPolicyException(
                    "Unknown enum value for " + MaskedCharactersPosition.class.getName() + ": " + position);
        }

        String replacementString = null;

        switch (policy) {
        case REPLACE_EACH_CHARACTER:
            if (null == config.getMaskingCharacter()) {
                String message = "Variable maskingCharacter must be set; the value can not be null.";
                LOGGER.error(message);
                throw new MisconfiguredMaskingPolicyException(message);
            }
            replacementString = createMaskedCharacterString(config.getMaskingCharacter(), charactersToBeMasked.length(),
                    charactersToBeMasked, config.getRegexForCharactersToMask());
            break;
        case REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING:
            if (null == config.getMaskingCharacter() || null == config.getFixedLengthStringSize()) {
                String message = "Both maskingCharacter and fixedLengthStringSize must be set;"
                        + " neither can be null.";
                LOGGER.error(message);
                throw new MisconfiguredMaskingPolicyException(message);
            }
            replacementString = createMaskedCharacterString(config.getMaskingCharacter(),
                    config.getFixedLengthStringSize(), null, null);
            break;
        default:
            LOGGER.error("Unrecognized {} type: {}.", MaskingPolicy.class.getName(), policy);
            throw new MisconfiguredMaskingPolicyException(
                    "Unknown enum value for " + MaskingPolicy.class.getName() + ": " + policy);

        }

        if (beginIndexCharactersToBeMasked <= endIndexCharactersToBeMasked) {
            sb.replace(beginIndexCharactersToBeMasked, endIndexCharactersToBeMasked, replacementString.toString());
        }

        return sb.toString();

    }

    private static Matcher getFoundMatch(String regex, String string) {
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(string);
        boolean found = m.find();
        if (!found) {
            m = matchAll.matcher(string);
            found = m.find();
            LOGGER.error("MaskingAnchorPatternNotFound: regex: {}. Masking everything. MatchAll found: {}", regex,
                    found);
            // TODO This is where MaskingAnchorPatternNotFoundException would go if you
            // needed to throw an exception in that scenario.
        }
        return m;
    }

    private static String getCharactersToBeMasked(MaskingPolicyConfiguration config, String string,
            int beginIndexCharactersToBeMasked, int endIndexCharactersToBeMasked) {
        LOGGER.debug("config={}, string={}, beginIndexCharactersToBeMasked={}, endIndexCharactersToBeMasked={}", config,
                string, beginIndexCharactersToBeMasked, endIndexCharactersToBeMasked);
        String charactersToBeMasked = null;
        if (beginIndexCharactersToBeMasked <= endIndexCharactersToBeMasked) {
            charactersToBeMasked = string.substring(beginIndexCharactersToBeMasked, endIndexCharactersToBeMasked);
        } else {
            charactersToBeMasked = "";
        }

        return charactersToBeMasked;
    }

    // Package level visibility to enable test
    static String createMaskedCharacterString(Character maskingCharacter, Integer length, String charactersToBeMasked,
            String regexForCharactersToMask) {

        if (null == length || null == maskingCharacter) {
            LOGGER.warn("length unexpectedly null. length={}", length);
            return "";
        }

        StringBuilder output = new StringBuilder();

        if (null == regexForCharactersToMask) {
            for (int i = 0; i < length; i++) {
                output.append(maskingCharacter.charValue());
            }
            return output.toString();
        } else {
            char[] dst = new char[charactersToBeMasked.length()];
            charactersToBeMasked.getChars(0, charactersToBeMasked.length(), dst, 0);
            for (int i = 0; i < dst.length; i++) {
                String character = String.valueOf(dst[i]);

                if (character.matches(regexForCharactersToMask)) {
                    output.append(maskingCharacter.charValue());
                } else {
                    output.append(character);
                }
            }
            return output.toString();
        }
    }
}
