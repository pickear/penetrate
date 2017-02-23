package com.weasel.penetrate.manager.infrastructure.exception;

/**
 * 用户同时在线异常
 * @author Dylan
 * @time 2013-8-12
 */
public class LockAccountException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7510980789927862457L;

	public LockAccountException(){
		super();
	}

	public LockAccountException(String message){
		super(message);
	}
}
