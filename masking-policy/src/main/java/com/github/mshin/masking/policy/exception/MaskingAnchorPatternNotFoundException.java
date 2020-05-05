package com.github.mshin.masking.policy.exception;

/**
 * @author MunChul Shin
 */
public class MaskingAnchorPatternNotFoundException extends RuntimeException {

    private static final long serialVersionUID = -8302349359332443430L;

    public MaskingAnchorPatternNotFoundException() {
        super();
    }

    public MaskingAnchorPatternNotFoundException(String message, Throwable cause, boolean enableSuppression,
            boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public MaskingAnchorPatternNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public MaskingAnchorPatternNotFoundException(String message) {
        super(message);
    }

    public MaskingAnchorPatternNotFoundException(Throwable cause) {
        super(cause);
    }

}
