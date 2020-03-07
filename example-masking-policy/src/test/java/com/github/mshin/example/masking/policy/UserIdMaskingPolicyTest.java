package com.github.mshin.example.masking.policy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;
import com.github.mshin.masking.policy.util.MaskingPolicyUtil;

/**
 * @author mshin
 */
public class UserIdMaskingPolicyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserIdMaskingPolicyTest.class);

    private MaskingPolicyConfiguration config = new UserIdMaskingPolicy();

    @Test
    public void maskWithUserIdPolicyFor1CharacterToMask() {

        String content = "m";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("m".equals(output));
    }

    @Test
    public void maskWithUserIdPolicyFor2CharactersToMask() {

        String content = "mu";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("mu".equals(output));
    }

    @Test
    public void maskWithUserIdPolicyFor3CharactersToMask() {

        String content = "mun";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("m*n".equals(output));
    }

    @Test
    public void maskWithUserIdPolicyFor4CharactersToMask() {

        String content = "munc";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("m**c".equals(output));
    }

    @Test
    public void maskWithUserIdPolicyFor5CharactersToMask() {

        String content = "munch";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("m***h".equals(output));
    }

    @Test
    public void maskWithUserIdPolicyFor6CharactersToMask() {

        String content = "munchu";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("m****u".equals(output));
    }

    @Test
    public void maskWithUserIdPolicyFor7CharactersToMask() {

        String content = "munchul";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("m*****l".equals(output));
    }

    @Test
    public void maskWithUserIdPolicyFor11CharactersToMask() {

        String content = "munchulshin";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("m*********n".equals(output));
    }
}