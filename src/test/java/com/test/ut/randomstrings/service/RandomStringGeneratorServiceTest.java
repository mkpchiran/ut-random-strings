package com.test.ut.randomstrings.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = {RandomStringGeneratorService.class,RandomStringGeneratorServiceImpl.class})
class RandomStringGeneratorServiceTest {

	@Autowired
	private RandomStringGeneratorService service;

	@Test
	void generate() {
		String result = service.generate(1);
		Assertions.assertEquals(1, result.length());
	}
}