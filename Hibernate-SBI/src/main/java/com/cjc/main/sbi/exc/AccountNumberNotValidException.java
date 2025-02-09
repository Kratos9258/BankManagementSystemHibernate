package com.cjc.main.sbi.exc;

public class AccountNumberNotValidException extends Exception{
	
	public AccountNumberNotValidException (String warning)
	{
		super(warning);
	}

}
