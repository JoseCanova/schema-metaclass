package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.nanotek.meta.util.ColumnNameTranslationStrategy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ColumnNameTranslationStrategyTest {

	@Autowired
	ColumnNameTranslationStrategy columnNameTranslationStrategy;
	
	@Test
	void testColumnNameTranslationStrategy() {
		assertNotNull(columnNameTranslationStrategy);
		String temp = "_&";
		String result = columnNameTranslationStrategy.processNameTranslationStrategy(temp);
		assertEquals(result, "_");
	}
}
