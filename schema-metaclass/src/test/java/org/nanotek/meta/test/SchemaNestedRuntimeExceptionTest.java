package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.nanotek.meta.constants.SystemStaticMessageSource.NONOK;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.constants.LocaleContext;
import org.nanotek.meta.constants.SystemStaticMessageSource;
import org.nanotek.meta.rdbms.exception.SchemaMetaClassException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
public class SchemaNestedRuntimeExceptionTest {

	@Autowired
	SystemStaticMessageSource messageSource;
	
	@Test
	void testRuntimeException() {
		Exception e = new Exception("TestException");
		Exception exception =  assertThrows(SchemaMetaClassException.class, () -> {
								throw new SchemaMetaClassException(messageSource.getMessage(NONOK , new Object[]{}, LocaleContext.getCurrentLocale()) , e.getCause());
					});
	
	
	    String actualMessage = messageSource.getMessage(NONOK , new Object[]{}, LocaleContext.getCurrentLocale());
	    String exceptionMessage = exception.getMessage();
	    assertTrue(actualMessage.contains(exceptionMessage));

	}
}
