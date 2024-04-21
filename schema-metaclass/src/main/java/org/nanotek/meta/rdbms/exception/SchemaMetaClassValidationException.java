package org.nanotek.meta.rdbms.exception;

import org.springframework.core.NestedRuntimeException;

public class SchemaMetaClassValidationException extends NestedRuntimeException {
    
	private static final long serialVersionUID = 3352027202324570248L;

	public SchemaMetaClassValidationException(String message) {
		super(message);
	}

	public SchemaMetaClassValidationException(String message, Throwable cause) {
		super(message, cause);
	}

}
