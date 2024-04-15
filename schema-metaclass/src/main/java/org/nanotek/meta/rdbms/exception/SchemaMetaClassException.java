package org.nanotek.meta.rdbms.exception;

import org.springframework.core.NestedRuntimeException;

public class SchemaMetaClassException extends NestedRuntimeException {

	private static final long serialVersionUID = 1L;


	public SchemaMetaClassException(String message) {
		super(message);
	}

	public SchemaMetaClassException(String message, Throwable cause) {
		super(message, cause);
	}

	public SchemaMetaClassException(String message, Throwable cause, boolean enableSuppression,
			boolean writableStackTrace) {
		super(message, cause);
	}

}
