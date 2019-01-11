package com.prokarma.csv.configuration;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class ConfigurationTest {

	@InjectMocks
	Configuration configuration;
	
	@Before
    public void setup()
    {
        MockitoAnnotations.initMocks(this);
    }
	
	@Test
	public void testCheckNullCondition() {
		assertEquals("test", configuration.checkNullCondition("test"));
	}

}
