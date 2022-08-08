package com.jumbo.store.util;

import org.apache.commons.lang3.RandomStringUtils;

public class JumboUtil {

	public static String createRandomUuid() {
		return RandomStringUtils.randomAlphanumeric(24);
	}
	

}
