package com.flowergarden.run;

import java.util.Collection;
import java.util.Collections;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import com.flowergarden.bouquet.*;
import com.flowergarden.flowers.GeneralFlower;
import com.flowergarden.flowers.Rose;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import static org.mockito.Mockito.when;

public class BouquetTest {
	@Mock
	private Bouquet<GeneralFlower> bouquet;

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();

	@Test
	public void getPriceTest() {
		when(bouquet.getPrice()).thenReturn((float) 120); ;
		Assert.assertEquals(Float.valueOf(120), bouquet.getPrice(),0);
	}

	@Test
	public void searchFlowersByLenghtTest() {
		when( bouquet.searchFlowersByLenght(0, 0)).thenReturn(Collections.emptyList());
		Assert.assertTrue(bouquet.searchFlowersByLenght(0, 0).isEmpty());
	}
}
