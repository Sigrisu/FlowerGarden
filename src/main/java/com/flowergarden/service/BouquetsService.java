package com.flowergarden.service;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.dao.BouquetDao;

@Service
public class BouquetsService {
	public List<Bouquet> getAllBouquets() throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
		BouquetDao bouquetDao = context.getBean("bouquetDao", BouquetDao.class);

		return bouquetDao.getAll();

	}
}
