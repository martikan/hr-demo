package com.martikan.carrental;

import javax.transaction.Transactional;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Init integration tests.
 */
@Transactional
@SpringBootTest
@ActiveProfiles("test")
public abstract class CarrentalApplicationTests {

	@Test
	void contextLoads() {
	}

}
