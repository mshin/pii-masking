package com.github.mshin.masking.policy.exception;

/**
 * @author mshin
 */
public class MisconfiguredMaskingPolicyException extends RuntimeException {

    private static final long serialVersionUID = -8302349359332443440L;

    public MisconfiguredMaskingPolicyException() {
        super();
    }

    public MisconfiguredMaskingPolicyException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MisconfiguredMaskingPolicyException(String message, Throwable cause) {
        super(message, cause);
    }

    public MisconfiguredMaskingPolicyException(String message) {
        super(message);
    }

    public MisconfiguredMaskingPolicyException(Throwable cause) {
        super(cause);
    }

}
