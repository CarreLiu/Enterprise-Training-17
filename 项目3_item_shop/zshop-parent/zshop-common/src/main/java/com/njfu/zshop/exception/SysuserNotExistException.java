package com.njfu.zshop.exception;

/**
 * Author:CarreLiu
 * Date:2020-06-11 15:57
 * Description:<描述>
 */
public class SysuserNotExistException extends Exception {
    public SysuserNotExistException() {
        super();
    }

    public SysuserNotExistException(String message) {
        super(message);
    }

    public SysuserNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    public SysuserNotExistException(Throwable cause) {
        super(cause);
    }

    protected SysuserNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
