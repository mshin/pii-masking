package com.github.mshin.masking.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.github.mshin.masking.policy.MaskEachCharacterMaskingPolicy;
import com.github.mshin.masking.policy.MaskingPolicyConfiguration;

/**
 * @author mshin
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Pii {

    Class<? extends MaskingPolicyConfiguration> value() default MaskEachCharacterMaskingPolicy.class;
}
