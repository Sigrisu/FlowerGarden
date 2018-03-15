package com.flowergarden.run;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.BouquetDao;
import com.flowergarden.bouquet.BouquetDaoImpl;
import com.flowergarden.flowers.*;
import com.flowergarden.properties.FreshnessInteger;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.Iterator;
import java.util.List;

public class Run {

    public static void main(String[] args) throws IOException {
        BouquetDao bouquetDao = new BouquetDaoImpl();
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
