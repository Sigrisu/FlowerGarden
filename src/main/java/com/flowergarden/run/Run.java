package com.flowergarden.run;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.BouquetDaoIO;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

public class Run {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-context.xml");
        //BouquetDao bouquetDao = context.getBean("bouquetDao", BouquetDao.class);
        BouquetDaoIO bouquetDaoJson = context.getBean("bouquetDaoJson", BouquetDaoIO.class);

        try {
            List<Bouquet> i = bouquetDaoJson.getAll();
            bouquetDaoJson.addAll(i);
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

}
