package com.njfu.exception;

/**
 * 数据访问失败异常
 * @author carreliu
 *
 */
public class DataAccessException extends RuntimeException {

	public DataAccessException() {
	}

	public DataAccessException(String message) {
		super(message);
	}

	public DataAccessException(Throwable cause) {
		super(cause);
	}

	public DataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
