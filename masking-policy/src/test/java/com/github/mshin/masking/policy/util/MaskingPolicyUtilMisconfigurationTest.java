package com.github.mshin.masking.policy.util;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;
import com.github.mshin.masking.policy.MaskingPolicyConfiguration.MaskedCharactersPosition;
import com.github.mshin.masking.policy.MaskingPolicyConfiguration.MaskingPolicy;
import com.github.mshin.masking.policy.exception.MisconfiguredMaskingPolicyException;

/**
 * @author MunChul Shin
 */
public class MaskingPolicyUtilMisconfigurationTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(MaskingPolicyUtilMisconfigurationTest.class);

    @Test
    public void testConfigEnums() {
        MaskingPolicyConfiguration config = new MaskingPolicyConfiguration() {
        };
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setMaskedCharactersPosition(MaskedCharactersPosition.ALL);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setMaskingPolicy(MaskingPolicy.REPLACE_EACH_CHARACTER);
        config.setMaskingCharacter('a');
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));

        config.setMaskedCharactersPosition(null);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));
    }

    @Test
    public void testLeadingPosition() {
        MaskingPolicyConfiguration config = new MaskingPolicyConfiguration() {
        };
        config.setMaskingPolicy(MaskingPolicy.REPLACE_EACH_CHARACTER);
        config.setMaskingCharacter('a');
        config.setMaskedCharactersPosition(MaskedCharactersPosition.LEADING);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfTrailingCharactersUnmasked(1);
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));

        config.setTrailingCharactersUnmaskedAnchorPattern("1");
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfTrailingCharactersUnmasked(null);
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));

    }

    @Test
    public void testTrailingPosition() {
        MaskingPolicyConfiguration config = new MaskingPolicyConfiguration() {
        };
        config.setMaskingPolicy(MaskingPolicy.REPLACE_EACH_CHARACTER);
        config.setMaskingCharacter('a');
        config.setMaskedCharactersPosition(MaskedCharactersPosition.TRAILING);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfLeadingCharactersUnmasked(1);
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));

        config.setLeadingCharactersUnmaskedAnchorPattern("1");
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfLeadingCharactersUnmasked(null);
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));
    }

    /**
     * Center A B C D <br>
     * !A !B !C !D fail <br>
     * A !B !C !D fail <br>
     * A B !C !D fail <br>
     * A B C !D fail <br>
     * !A B C !D pass <br>
     * !A B !C !D fail <br>
     * A !B C !D pass <br>
     * !A !B C !D fail <br>
     * <br>
     * !A !B !C D fail <br>
     * A !B !C D pass <br>
     * A B !C D fail <br>
     * !A B !C D pass <br>
     * !A B C D fail <br>
     * !A !B C D fail <br>
     * A !B C D fail <br>
     * A B C D fail <br>
     * A = numberOfLeadingCharactersUnmasked <br>
     * B = leadingCharactersUnmaskedAnchorPattern <br>
     * C = numberOfTrailingCharactersUnmasked <br>
     * D = trailingCharactersUnmaskedAnchorPattern <br>
     */
    @Test
    public void testCenterPosition() {
        MaskingPolicyConfiguration config = new MaskingPolicyConfiguration() {
        };
        config.setMaskingPolicy(MaskingPolicy.REPLACE_EACH_CHARACTER);
        config.setMaskingCharacter('a');
        config.setMaskedCharactersPosition(MaskedCharactersPosition.CENTER);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfLeadingCharactersUnmasked(1);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setLeadingCharactersUnmaskedAnchorPattern("1");
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfTrailingCharactersUnmasked(1);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfLeadingCharactersUnmasked(null);
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfTrailingCharactersUnmasked(null);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfLeadingCharactersUnmasked(1);
        config.setLeadingCharactersUnmaskedAnchorPattern(null);
        config.setNumberOfTrailingCharactersUnmasked(1);
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfLeadingCharactersUnmasked(null);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfTrailingCharactersUnmasked(null);
        config.setTrailingCharactersUnmaskedAnchorPattern("1");
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfLeadingCharactersUnmasked(1);
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));

        config.setLeadingCharactersUnmaskedAnchorPattern("1");
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfLeadingCharactersUnmasked(null);
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfTrailingCharactersUnmasked(1);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setLeadingCharactersUnmaskedAnchorPattern(null);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setNumberOfLeadingCharactersUnmasked(1);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setLeadingCharactersUnmaskedAnchorPattern("1");
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

    }

    @Test
    public void testMaskingPolicy() {
        MaskingPolicyConfiguration config = new MaskingPolicyConfiguration() {
        };
        config.setMaskedCharactersPosition(MaskedCharactersPosition.ALL);
        config.setMaskingPolicy(MaskingPolicy.REPLACE_CHARACTERS_WITH_FIXED_LENGTH_STRING);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setMaskingCharacter('a');
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setFixedLengthStringSize(1);
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));

        config.setMaskingCharacter(null);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setMaskingPolicy(MaskingPolicy.REPLACE_EACH_CHARACTER);
        config.setFixedLengthStringSize(null);
        assertTrue(throwsMisconfiguredMaskingPolicyException(config));

        config.setMaskingCharacter('a');
        assertFalse(throwsMisconfiguredMaskingPolicyException(config));
    }

    private boolean throwsMisconfiguredMaskingPolicyException(MaskingPolicyConfiguration config) {
        boolean isThrown = false;
        String maskedString = null;
        try {
            maskedString = MaskingPolicyUtil.maskWithPolicy(config, "1234");
        } catch (MisconfiguredMaskingPolicyException e) {
            isThrown = true;
        }
        LOGGER.debug("maskedString: " + maskedString);
        return isThrown;
    }

}
