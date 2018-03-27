package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;

import java.util.List;

public interface BouquetDaoIO {
    List<Bouquet> getAll() throws Exception;

    int addAll(List<Bouquet> bouquets) throws Exception;
}
