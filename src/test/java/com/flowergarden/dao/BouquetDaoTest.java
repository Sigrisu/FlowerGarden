package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.flowers.GeneralFlower;
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
public class BouquetDaoTest {

    BouquetDao bouquetDao = new ClassPathXmlApplicationContext("application-context.xml").getBean(BouquetDao.class);

    @Mock
    private Bouquet<GeneralFlower> bouquet;

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Test
    public void getAllTest() throws Exception {
        Assert.assertFalse(bouquetDao.getAll().isEmpty());
    }

    @Test
    public void getByIdTest() throws Exception {
        Assert.assertEquals(Float.valueOf(12), bouquetDao.getById(4).getPrice(),0);
    }

    @Test
    public void updateTest() throws Exception {
        when(bouquet.getPrice()).thenReturn((float) 12);
        Assert.assertEquals(Integer.valueOf(1), bouquetDao.update(4,bouquet),0);

    }

    @Test
    public void addTest() throws Exception {
        when(bouquet.getPrice()).thenReturn((float) 12);
        Assert.assertEquals(Integer.valueOf(1), bouquetDao.add(bouquet),0);
    }

    @Test
    public void deleteTest() throws Exception {
        when(bouquet.getPrice()).thenReturn((float) 12);
        Assert.assertEquals(Integer.valueOf(0), bouquetDao.delete(1),0);
    }
}