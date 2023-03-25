package com.exception;
public class TypeConversionException extends Exception
{
	private static final long serialVersionUID = 1L;
	
	public TypeConversionException(String message, Throwable cause) {
		super(message, cause);
	}

	public TypeConversionException(String message) {
		super(message);
	}
}