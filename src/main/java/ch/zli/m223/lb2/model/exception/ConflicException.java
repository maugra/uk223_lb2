package ch.zli.m223.lb2.model.exception;

import javax.ws.rs.core.Response;

public class ConflicException extends Exception {
	public ConflicException() {
		super();
	}
	public ConflicException(String msg){
		super(msg);
	}
	public ConflicException(String msg, Exception e){
		super(msg, e);
	}
}
