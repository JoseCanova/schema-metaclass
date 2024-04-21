package org.nanotek.meta.test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.validation.Validator;

@SpringBootTest
public class ValidatorInjectionTest {

	@Autowired
	Validator validator;
	
	@Test
	void testValidatorInjection() {
		assertNotNull(validator);
	}
	
}
