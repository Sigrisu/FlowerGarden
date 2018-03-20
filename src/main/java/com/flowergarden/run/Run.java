package com.flowergarden.run;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.BouquetDao;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Iterator;
import java.util.List;

public class Run {

    public static void main(String[] args) {
        ApplicationContext context =
                new ClassPathXmlApplicationContext("application-context.xml");
        BouquetDao bouquetDao = context.getBean("bouquetDAO", BouquetDao.class);
        try {
            List<Bouquet> bouquets = bouquetDao.getAll();
            Iterator<Bouquet> bouquetsIterator = bouquets.iterator();
            while (bouquetsIterator.hasNext()) {
                System.out.println("Bouquet price: " + bouquetsIterator.next().getPrice());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }



    }

}
