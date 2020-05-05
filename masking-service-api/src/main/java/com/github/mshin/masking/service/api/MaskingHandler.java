package com.github.mshin.masking.service.api;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.mshin.masking.policy.MaskingPolicyConfiguration;

/**
 * @author MunChul Shin
 * @param <T>
 */
public interface MaskingHandler<T> {

    static final Logger LOGGER = LoggerFactory.getLogger(MaskingHandler.class);

    static final Map<Class<? extends MaskingPolicyConfiguration>, MaskingPolicyConfiguration> CLASS_INSTANCE_MAP = new HashMap<>();
    static final Map<String, MaskingPolicyConfiguration> NAME_INSTANCE_MAP = new HashMap<>();

    void maskObjectWithStrategy(MaskingStrategy maskingStrategy, T object);

    static MaskingPolicyConfiguration getMaskingPolicyConfigurationInstance(
            Class<? extends MaskingPolicyConfiguration> piiPolicyClass) {
        if (null == piiPolicyClass) {
            return null;
        }
        MaskingPolicyConfiguration config = null;
        if (CLASS_INSTANCE_MAP.containsKey(piiPolicyClass)) {
            config = CLASS_INSTANCE_MAP.get(piiPolicyClass);
        } else {
            try {
                Constructor<? extends MaskingPolicyConfiguration> con = ConstructorUtils
                        .getMatchingAccessibleConstructor(piiPolicyClass, new Class<?>[0]);
                config = con.newInstance(new Object[0]);
            } catch (InstantiationException | IllegalAccessException | IllegalArgumentException
                    | InvocationTargetException e) {
                LOGGER.error("Problem creating new instance of class " + piiPolicyClass + ".", e);
                throw new RuntimeException(e);
            }
            CLASS_INSTANCE_MAP.put(piiPolicyClass, config);
            NAME_INSTANCE_MAP.put(piiPolicyClass.getName(), config);
        }
        return config;
    }

    static MaskingPolicyConfiguration getMaskingPolicyConfigurationInstance(String piiPolicyClassName,
            ClassLoader classLoader) {
        if (null == piiPolicyClassName) {
            return null;
        }
        if (NAME_INSTANCE_MAP.containsKey(piiPolicyClassName)) {
            return NAME_INSTANCE_MAP.get(piiPolicyClassName);
        }
        if (null == classLoader) {
            classLoader = MaskingHandler.class.getClassLoader();
        }
        Class<?> clazz = null;

        try {
            clazz = classLoader.loadClass(piiPolicyClassName);
        } catch (ClassNotFoundException e) {
            LOGGER.error("Cannot find class " + piiPolicyClassName + ".", e);
            throw new RuntimeException(e);
        }

        if (MaskingPolicyConfiguration.class.isAssignableFrom(clazz)) {
            @SuppressWarnings("rawtypes")
            Class tmpClazz = clazz;
            @SuppressWarnings("unchecked")
            Class<? extends MaskingPolicyConfiguration> mpcClazz = tmpClazz;
            MaskingPolicyConfiguration config = getMaskingPolicyConfigurationInstance(mpcClazz);
            NAME_INSTANCE_MAP.put(piiPolicyClassName, config);
            return config;
        } else {
            String message = "Class " + clazz + " is not of type " + MaskingPolicyConfiguration.class.getName() + ".";
            LOGGER.error(message);
            throw new RuntimeException(message);
        }
    }
}
