package org.nanotek.meta.util.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.util.SnakeToCamelCaseTranslator;

public class SnakeToCamelCaseTranslatorTest {

	@Test
	public void testSnakeToCamelCaseTranslator() {
		
		var snakeCaseStr = "snake_case_string";
		
		var camelCaseResult = SnakeToCamelCaseTranslator.from(snakeCaseStr);
		System.err.println(camelCaseResult);
		assertTrue("snakeCaseString".equals(camelCaseResult));
		
	}
	
}
