package com.flowergarden.dao;

import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.properties.FreshnessInteger;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class FlowerDaoImplTest {

    FlowerDao flowerDao = new ClassPathXmlApplicationContext("application-context.xml").getBean(FlowerDao.class);


    @Mock
    private Flower flower;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void getAllTest() throws Exception {
        Assert.assertFalse(flowerDao.getAll().isEmpty());
    }

    @Test
    public void getByIdTest() throws Exception {
        Assert.assertEquals(Float.valueOf(12), flowerDao.getById(3).getPrice(), 0);
    }

    @Test
    public void updateTest() throws Exception {
        Flower f = new Rose();
        f.setPrice(12);
        f.setLenght(2);
        f.setFreshness(new FreshnessInteger(23));
        Assert.assertEquals(Integer.valueOf(1), flowerDao.update(3, f), 0);

    }

    @Test
    public void deleteTest() throws Exception {
        when(flower.getPrice()).thenReturn((float) 12);
        Assert.assertEquals(Integer.valueOf(0), flowerDao.delete(1), 0);
    }
}