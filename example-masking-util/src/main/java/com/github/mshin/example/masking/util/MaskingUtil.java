package com.github.mshin.example.masking.util;

import com.github.mshin.example.masking.policy.EmailAddressMaskingPolicy;
import com.github.mshin.example.masking.policy.PasswordMaskingPolicy;
import com.github.mshin.example.masking.policy.PhoneNumberMaskingPolicy;
import com.github.mshin.example.masking.policy.UserIdMaskingPolicy;
import com.github.mshin.example.masking.policy.ZipCodeMaskingPolicy;
import com.github.mshin.masking.policy.MaskEachCharacterMaskingPolicy;
import com.github.mshin.masking.policy.MaskingPolicyConfiguration;
import com.github.mshin.masking.policy.util.MaskingPolicyUtil;

/**
 * @author MunChul Shin
 */
public class MaskingUtil {

    private MaskingUtil() {
    }

    private static EmailAddressMaskingPolicy emailAddressMaskingPolicy = new EmailAddressMaskingPolicy();
    private static PhoneNumberMaskingPolicy phoneNumberMaskingPolicy = new PhoneNumberMaskingPolicy();
    private static UserIdMaskingPolicy userIdMaskingPolicy = new UserIdMaskingPolicy();
    private static ZipCodeMaskingPolicy zipCodeMaskingPolicy = new ZipCodeMaskingPolicy();
    private static PasswordMaskingPolicy passwordMaskingPolicy = new PasswordMaskingPolicy();
    private static MaskEachCharacterMaskingPolicy maskEachCharacterMaskingPolicy = new MaskEachCharacterMaskingPolicy();

    public static String maskWithPolicy(MaskingPolicyConfiguration config, String string) {
        return MaskingPolicyUtil.maskWithPolicy(config, string);
    }

    public static String maskEmailAddress(String emailAddress) {
        if (null == emailAddress) {
            return null;
        }
        return MaskingPolicyUtil.maskWithPolicy(emailAddressMaskingPolicy, emailAddress);
    }

    public static String maskPhoneNumber(String phoneNumber) {
        if (null == phoneNumber) {
            return null;
        }
        return MaskingPolicyUtil.maskWithPolicy(phoneNumberMaskingPolicy, phoneNumber);
    }

    public static String maskUserId(String userId) {
        if (null == userId) {
            return null;
        }
        return MaskingPolicyUtil.maskWithPolicy(userIdMaskingPolicy, userId);
    }

    public static String maskZipCode(String zipCode) {
        if (null == zipCode) {
            return null;
        }
        return MaskingPolicyUtil.maskWithPolicy(zipCodeMaskingPolicy, zipCode);
    }

    public static String maskPassword(String password) {
        if (null == password) {
            return null;
        }
        return MaskingPolicyUtil.maskWithPolicy(passwordMaskingPolicy, password);
    }

    public static String maskAllCharacters(String input) {
        if (null == input) {
            return null;
        }
        return MaskingPolicyUtil.maskWithPolicy(maskEachCharacterMaskingPolicy, input);
    }

}