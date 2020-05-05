package com.github.mshin.example.masking.util;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

/**
 * @author MunChul Shin
 */
public class MaskingUtilTest {

    @Test
    public void testOneCharacterEmail() throws Exception {
        assertEquals("m@redhat.com", MaskingUtil.maskEmailAddress("m@redhat.com"));
    }

    @Test
    public void testFiveCharacterEmail() throws Exception {
        assertEquals("m****@redhat.com", MaskingUtil.maskEmailAddress("mshin@redhat.com"));
    }

    @Test
    public void testEmailWithWhitespace() throws Exception {
        assertEquals("m****@redhat.com", MaskingUtil.maskEmailAddress("  mshin@redhat.com"));
    }

    @Test
    public void testNullEmail() throws Exception {
        assertNull(MaskingUtil.maskEmailAddress(null));
    }

    @Test
    public void testPhoneNumber() {
        String phoneNumber = "111-222-1234";
        assertEquals("***-***-1234", MaskingUtil.maskPhoneNumber(phoneNumber));
    }

    @Test
    public void testPhoneNumberWithAreaCodeParenthesis() {
        String phoneNumber = "(111) 222-1234";
        assertEquals("(***) ***-1234", MaskingUtil.maskPhoneNumber(phoneNumber));
    }

    @Test
    public void testPhoneNumberWithNullPhoneNumber() {
        String phoneNumber = null;
        assertEquals(null, MaskingUtil.maskPhoneNumber(phoneNumber));
    }

    @Test
    public void testZipCode() {
        String zip = "12345";
        assertEquals("*****", MaskingUtil.maskZipCode(zip));
    }

    @Test
    public void testZipCodeWithTwoParts() {
        String zip = "12345-6789";
        assertEquals("*****-****", MaskingUtil.maskZipCode(zip));
    }

    @Test
    public void testZipCodeWithTwoPartsNoDash() {
        String zip = "123456789";
        assertEquals("*********", MaskingUtil.maskZipCode(zip));
    }

    @Test
    public void testZipCodeFromCanada() {
        String zip = "K1A 0B1";
        assertEquals("*** ***", MaskingUtil.maskZipCode(zip));
    }

}