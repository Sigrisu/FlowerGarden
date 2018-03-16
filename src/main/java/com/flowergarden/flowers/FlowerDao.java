package com.flowergarden.flowers;

import java.io.IOException;
import java.util.List;

public interface FlowerDao {

    List<Flower> getAll() throws IOException;

    Flower getById(int id) throws Exception;

    int add(Flower flower) throws Exception;

    int update(int id, Flower flower) throws Exception;

    int delete(int id) throws Exception;
}
