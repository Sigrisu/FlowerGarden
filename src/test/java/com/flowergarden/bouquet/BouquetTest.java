package com.flowergarden.bouquet;

import com.flowergarden.flowers.GeneralFlower;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Collections;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

public class BouquetTest {
    @Mock
    private Bouquet<GeneralFlower> bouquet;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void getPriceTest() {
        when(bouquet.getPrice()).thenReturn((float) 120);
        Assert.assertEquals(Float.valueOf(120), bouquet.getPrice(),0);
    }

    @Test
    public void searchFlowersByLenghtTest() {
        when( bouquet.searchFlowersByLenght(0, 0)).thenReturn(Collections.emptyList());
        Assert.assertTrue(bouquet.searchFlowersByLenght(0, 0).isEmpty());
    }


    @Test
    public void addFlowerTest() {
    }



    @Test
    public void sortByFreshnessTest() {
    }

    @Test
    public void getFlowersTest() {
    }

    @Test
    public void setAssembledPriceTest() {
    }

}