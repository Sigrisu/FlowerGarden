package com.flowergarden.dao;

import com.flowergarden.flowers.Flower;

import java.util.List;

public interface FlowerDao {

    List<Flower> getAll() throws Exception;

    Flower getById(int id) throws Exception;

    int add(Flower flower) throws Exception;

    int update(int id, Flower flower) throws Exception;

    int delete(int id) throws Exception;
}
