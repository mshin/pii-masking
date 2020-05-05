package com.github.mshin.masking.service.api;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.masking.annotation.Pii;
import com.github.mshin.masking.policy.MaskingPolicyConfiguration;

/**
 * @author MunChul Shin
 * @param <T>
 */
public interface PiiAnnotatedObjectMaskingHandler<T> extends MaskingHandler<T> {

    static final Logger LOGGER = LoggerFactory.getLogger(PiiAnnotatedObjectMaskingHandler.class);

    static final Map<Class<?>, Map<Field, Pii>> PII_ANNOTATED_FIELDS_MAP = new HashMap<>();

    @Override
    default void maskObjectWithStrategy(MaskingStrategy maskingStrategy, T object) {
        if (null == object || null == maskingStrategy) {
            return;
        }
        Map<Field, Pii> fieldToPiiMap = getPiiAnnotatedFieldMap(object.getClass());

        for (Map.Entry<Field, Pii> entry : fieldToPiiMap.entrySet()) {
            Pii pii = entry.getValue();

            MaskingPolicyConfiguration config = MaskingHandler.getMaskingPolicyConfigurationInstance(pii.value());

            Field field = entry.getKey();

            Object fieldValue = null;
            String fieldString = null;
            String maskedValue = null;
            try {
                fieldValue = FieldUtils.readField(field, object, true);
                fieldString = (null == fieldValue) ? null : fieldValue.toString();
                maskedValue = maskingStrategy.mask(config, fieldString);
                FieldUtils.writeField(field, object, maskedValue, true);
            } catch (IllegalArgumentException | IllegalAccessException e) {
                LOGGER.error("Problem reading or writing value of field " + field + ". Read value: " + fieldString
                        + ". MaskedValue: " + maskedValue + ".", e);
                throw new RuntimeException(e);
            }

        }

    }

    static Map<Field, Pii> getPiiAnnotatedFieldMap(Class<?> clazz) {
        if (null == clazz) {
            return null;
        }
        if (PII_ANNOTATED_FIELDS_MAP.containsKey(clazz)) {
            return PII_ANNOTATED_FIELDS_MAP.get(clazz);
        } else {
            PII_ANNOTATED_FIELDS_MAP.put(clazz, generateMapOfFieldsWithPii(clazz));
            return PII_ANNOTATED_FIELDS_MAP.get(clazz);
        }

    }

    static Map<Field, Pii> generateMapOfFieldsWithPii(Class<?> classOfObjectProfiled) {
        if (null == classOfObjectProfiled) {
            throw new IllegalArgumentException("classOfObjectProfiled cannot be null.");
        }
        Map<Field, Pii> map = new HashMap<>();

        Field[] fieldArray = classOfObjectProfiled.getDeclaredFields();
        for (Field field : fieldArray) {
            if (field.isAnnotationPresent(Pii.class)) {
                map.put(field, field.getAnnotation(Pii.class));
            }
            // TODO check type, go into that field to get more Pii
            // maybe do recursion, maybe compare against map.
            // Recursion could be expensive.
        }
        return map;
    }

}
