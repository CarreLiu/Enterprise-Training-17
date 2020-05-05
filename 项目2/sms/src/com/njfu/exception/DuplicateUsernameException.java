package com.njfu.exception;

/**
 * 重复的用户名异常
 * @author carreliu
 *
 */
public class DuplicateUsernameException extends Exception {

	public DuplicateUsernameException() {
	}

	public DuplicateUsernameException(String message) {
		super(message);
	}

	public DuplicateUsernameException(Throwable cause) {
		super(cause);
	}

	public DuplicateUsernameException(String message, Throwable cause) {
		super(message, cause);
	}

}
