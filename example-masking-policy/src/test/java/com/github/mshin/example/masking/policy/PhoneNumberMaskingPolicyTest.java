package com.github.mshin.example.masking.policy;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;
import com.github.mshin.masking.policy.util.MaskingPolicyUtil;

/**
 * @author MunChul Shin
 */
public class PhoneNumberMaskingPolicyTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(PhoneNumberMaskingPolicyTest.class);

    private MaskingPolicyConfiguration config = new PhoneNumberMaskingPolicy();

    @Test
    public void mask1() {

        String content = "123-456-7890";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("***-***-7890".equals(output));
    }

    @Test
    public void mask2() {

        String content = "(123) 456-7890";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("(***) ***-7890".equals(output));
    }

    @Test
    public void mask3() {

        String content = "1234567890";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("******7890".equals(output));
    }

    @Test
    public void mask4() {

        String content = "+1 23456-789-00";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("+* *****-**9-00".equals(output));
    }

    @Test
    public void mask5() {

        String content = "1234";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("1234".equals(output));
    }

    @Test
    public void mask6() {

        String content = "123";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("123".equals(output));
    }

    @Test
    public void mask7() {

        String content = "12";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("12".equals(output));
    }

    @Test
    public void mask8() {

        String content = "1";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("1".equals(output));
    }

    @Test
    public void mask9() {

        String content = "";
        String output = MaskingPolicyUtil.maskWithPolicy(config, content);
        LOGGER.info("Content {} masked with policy {}: {}", content, config.getClass().getSimpleName(), output);
        assertTrue("".equals(output));
    }
}