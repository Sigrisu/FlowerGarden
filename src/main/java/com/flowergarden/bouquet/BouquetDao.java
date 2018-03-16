package com.flowergarden.bouquet;

import java.util.List;

public interface BouquetDao {

    List<Bouquet> getAll() throws Exception;

    Bouquet getById(int id) throws Exception;

    int add(Bouquet bouquet) throws Exception;

    int update(int id, Bouquet bouquet) throws Exception;

    int delete(int id) throws Exception;
}
