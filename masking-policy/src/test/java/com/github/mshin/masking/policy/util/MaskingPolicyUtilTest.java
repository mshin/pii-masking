package com.github.mshin.masking.policy.util;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;

/**
 * @author mshin
 */
public class MaskingPolicyUtilTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaskingPolicyUtilTest.class);

    private static final String CONTENT = "123456@789";

    @Test
    public void createMaskedCharacterString() {
        Character maskingCharacter = '*';
        Integer length = 12;

        String maskedCharacterString = MaskingPolicyUtil.createMaskedCharacterString(maskingCharacter, length, null,
                null);

        LOGGER.info("maskedCharacterString: {}", maskedCharacterString);
        assertTrue("************".equals(maskedCharacterString));
    }

    @Test
    public void createMaskedCharacterStringWithRegex() {
        Character maskingCharacter = '*';
        Integer length = 12;
        String charactersToBeMasked = "(213) 100-5555 ext.12";
        String regexForCharactersToMask = "[a-zA-Z0-9]";

        String maskedCharacterString = MaskingPolicyUtil.createMaskedCharacterString(maskingCharacter, length,
                charactersToBeMasked, regexForCharactersToMask);

        LOGGER.info("maskedCharacterString: {}", maskedCharacterString);
        assertTrue("(***) ***-**** ***.**".equals(maskedCharacterString));
    }

    @Test
    public void maskWithPolicyAllFixedLength() {
        MaskingPolicyConfiguration config = new AllFixedLength();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("********".equals(output));
    }

    @Test
    public void maskWithPolicyAllEach() {
        MaskingPolicyConfiguration config = new AllEach();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("**********".equals(output));
    }

    @Test
    public void maskWithPolicyAllNull() {
        MaskingPolicyConfiguration config = new AllNull();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("".equals(output));
    }

    @Test
    public void maskWithPolicyLeadingFixedLength() {
        MaskingPolicyConfiguration config = new LeadingFixedLength();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("********789".equals(output));
    }

    @Test
    public void maskWithPolicyLeadingEach() {
        MaskingPolicyConfiguration config = new LeadingEach();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("******@789".equals(output));
    }

    @Test
    public void maskWithPolicyLeadingEachRegexForCharactersToMask() {
        MaskingPolicyConfiguration config = new LeadingEachRegexForCharactersToMask();

        String content = "(213) 123-456@78";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("(***) ***-**6@78".equals(output));
    }

    @Test
    public void maskWithPolicyLeadingEachRegexForCharactersToMaskB() {
        MaskingPolicyConfiguration config = new LeadingEachRegexForCharactersToMaskB();

        String content = "(213) 123-456@78";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("(***) ***-***@78".equals(output));
    }

    @Test
    public void maskWithPolicyLeadingNull() {
        MaskingPolicyConfiguration config = new LeadingNull();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("89".equals(output));
    }

    @Test
    public void maskWithPolicyTrailingFixedLength() {
        MaskingPolicyConfiguration config = new TrailingFixedLength();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("123456@********".equals(output));
    }

    @Test
    public void maskWithPolicyTrailingEach() {
        MaskingPolicyConfiguration config = new TrailingEach();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("1234******".equals(output));
    }

    @Test
    public void maskWithPolicyTrailingNull() {
        MaskingPolicyConfiguration config = new TrailingNull();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("123456@78".equals(output));
    }

    @Test
    public void maskWithPolicyCenterFixedLength() {
        MaskingPolicyConfiguration config = new CenterFixedLength();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("12********@789".equals(output));
    }

    @Test
    public void maskWithPolicyCenterEach() {
        MaskingPolicyConfiguration config = new CenterEach();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("123*****89".equals(output));
    }

    @Test
    public void maskWithPolicyCenterEachB() {
        MaskingPolicyConfiguration config = new CenterEachB();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("12      89".equals(output));
    }

    @Test
    public void maskWithPolicyCenterNull() {
        MaskingPolicyConfiguration config = new CenterNull();

        String output = MaskingPolicyUtil.maskWithPolicy(config, CONTENT);
        LOGGER.info("Content {} masked with policy {}: {}", CONTENT, config.getClass().getSimpleName(), output);
        assertTrue("123@789".equals(output));
    }

    public class AllFixedLength extends MaskingPolicyConfiguration {
        public AllFixedLength() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.ALL;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING;
            this.fixedLengthStringSize = 8;
            this.maskingCharacter = '*';
        }

    }

    public class AllEach extends MaskingPolicyConfiguration {
        public AllEach() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.ALL;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
            this.maskingCharacter = '*';
        }
    }

    public class AllNull extends MaskingPolicyConfiguration {
        public AllNull() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.ALL;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING;
            this.fixedLengthStringSize = 0;
            this.maskingCharacter = '*';
        }
    }

    public class LeadingFixedLength extends MaskingPolicyConfiguration {
        public LeadingFixedLength() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.LEADING;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING;
            this.numberOfTrailingCharactersUnmasked = 3;
            this.fixedLengthStringSize = 8;
            this.maskingCharacter = '*';
        }
    }

    public class LeadingEach extends MaskingPolicyConfiguration {
        public LeadingEach() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.LEADING;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
            this.trailingCharactersUnmaskedAnchorPattern = "@";
            this.maskingCharacter = '*';
        }
    }

    public class LeadingEachRegexForCharactersToMask extends MaskingPolicyConfiguration {
        public LeadingEachRegexForCharactersToMask() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.LEADING;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
            this.numberOfTrailingCharactersUnmasked = 4;
            this.maskingCharacter = '*';
            this.regexForCharactersToMask = "[a-zA-Z0-9]";
        }
    }

    public class LeadingEachRegexForCharactersToMaskB extends MaskingPolicyConfiguration {
        public LeadingEachRegexForCharactersToMaskB() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.LEADING;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
            this.trailingCharactersUnmaskedAnchorPattern = "@";
            this.maskingCharacter = '*';
            this.regexForCharactersToMask = "[a-zA-Z0-9]";
        }
    }

    public class LeadingNull extends MaskingPolicyConfiguration {
        public LeadingNull() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.LEADING;
            this.trailingCharactersUnmaskedAnchorPattern = "8";
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING;
            this.fixedLengthStringSize = 0;
            this.maskingCharacter = '*';
        }
    }

    public class TrailingFixedLength extends MaskingPolicyConfiguration {
        public TrailingFixedLength() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.TRAILING;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING;
            this.leadingCharactersUnmaskedAnchorPattern = "@";
            this.fixedLengthStringSize = 8;
            this.maskingCharacter = '*';
        }
    }

    public class TrailingEach extends MaskingPolicyConfiguration {
        public TrailingEach() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.TRAILING;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
            this.numberOfLeadingCharactersUnmasked = 4;
            this.maskingCharacter = '*';
        }
    }

    public class TrailingNull extends MaskingPolicyConfiguration {
        public TrailingNull() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.TRAILING;
            this.leadingCharactersUnmaskedAnchorPattern = "8";
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING;
            this.fixedLengthStringSize = 0;
            this.maskingCharacter = '*';
        }
    }

    public class CenterFixedLength extends MaskingPolicyConfiguration {
        public CenterFixedLength() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.CENTER;
            this.numberOfLeadingCharactersUnmasked = 2;
            this.trailingCharactersUnmaskedAnchorPattern = "@";
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING;
            this.fixedLengthStringSize = 8;
            this.maskingCharacter = '*';
        }
    }

    public class CenterEach extends MaskingPolicyConfiguration {
        public CenterEach() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.CENTER;
            this.leadingCharactersUnmaskedAnchorPattern = "3";
            this.numberOfTrailingCharactersUnmasked = 2;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
            this.maskingCharacter = '*';
        }
    }

    public class CenterNull extends MaskingPolicyConfiguration {
        public CenterNull() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.CENTER;
            this.leadingCharactersUnmaskedAnchorPattern = "3";
            this.trailingCharactersUnmaskedAnchorPattern = "@";
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING;
            this.fixedLengthStringSize = 0;
            this.maskingCharacter = '*';
        }
    }

    public class CenterEachB extends MaskingPolicyConfiguration {
        public CenterEachB() {
            this.maskedCharactersPosition = MaskingPolicyConfiguration.MaskedCharactersPosition.CENTER;
            this.numberOfLeadingCharactersUnmasked = 2;
            this.numberOfTrailingCharactersUnmasked = 2;
            this.maskingPolicy = MaskingPolicyConfiguration.MaskingPolicy.REPLACE_EACH_CHARACTER;
            this.maskingCharacter = ' ';
        }
    }

}
