package com.sds.icto.mysite.exception;

public class GuestBookException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	public GuestBookException() {
		super("Create GuestBookExcpetion");
	}
	public GuestBookException(String msg){
		
	}
}
