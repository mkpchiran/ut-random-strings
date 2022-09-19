package com.test.ut.randomstrings.service;

import lombok.extern.log4j.Log4j2;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class RandomStringGeneratorServiceImpl implements RandomStringGeneratorService{

	@Override
	public String generate(int length){
		log.debug("Generate random string of size = " + length);
		return RandomStringUtils.random(length, true, true);
	}
}
