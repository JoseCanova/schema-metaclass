package org.nanotek.meta.util.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.util.CamelCaseSnakeCaseTranslator;

public class CamelToSnakeTranslatorTest {

	@Test
	public void testCamelToSnakeTranslator() {
		String camelCaseString = "camelCaseString";
		
		String translated = CamelCaseSnakeCaseTranslator.from(camelCaseString);
		
		System.err.println(translated);
		
		assertTrue ("camel_case_string".equals(translated));
		
	}
	
	@Test
	public void testCamelToSnakeTranslator2() {
		String camelCaseString = "CamelCaseString";
		
		String translated = CamelCaseSnakeCaseTranslator.from(camelCaseString);
		
		System.err.println(translated);
		
		assertTrue ("camel_case_string".equals(translated));
		
	}
	
}
