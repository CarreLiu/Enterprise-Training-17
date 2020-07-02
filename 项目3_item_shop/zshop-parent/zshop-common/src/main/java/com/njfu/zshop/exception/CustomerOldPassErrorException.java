package com.njfu.zshop.exception;

/**
 * Author:CarreLiu
 * Date:2020-07-02 16:08
 * Description:<描述>
 */
public class CustomerOldPassErrorException extends Exception {
    public CustomerOldPassErrorException() {
        super();
    }

    public CustomerOldPassErrorException(String message) {
        super(message);
    }

    public CustomerOldPassErrorException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomerOldPassErrorException(Throwable cause) {
        super(cause);
    }

    protected CustomerOldPassErrorException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
