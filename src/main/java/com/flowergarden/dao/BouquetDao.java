package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;

import java.util.List;

public interface BouquetDao {

    List<Bouquet> getAll() throws Exception;

    Bouquet getById(int id) throws Exception;

    int add(Bouquet bouquet) throws Exception;

    int addAll(List<Bouquet> bouquets) throws Exception;

    int update(int id, Bouquet bouquet) throws Exception;

    int delete(int id) throws Exception;
}
