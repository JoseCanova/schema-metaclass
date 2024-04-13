package org.nanotek.meta.rdbms.exception;

public class SchemaMetaClassException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public SchemaMetaClassException() {
	}

	public SchemaMetaClassException(String message) {
		super(message);
	}

	public SchemaMetaClassException(Throwable cause) {
		super(cause);
	}

	public SchemaMetaClassException(String message, Throwable cause) {
		super(message, cause);
	}

	public SchemaMetaClassException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
