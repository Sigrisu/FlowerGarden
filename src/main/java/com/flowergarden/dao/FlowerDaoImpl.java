package com.flowergarden.dao;


import com.flowergarden.flowers.Chamomile;
import com.flowergarden.flowers.Flower;
import com.flowergarden.flowers.Rose;
import com.flowergarden.flowers.Tulip;
import com.flowergarden.properties.FreshnessInteger;

import java.io.File;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FlowerDaoImpl implements FlowerDao {
    @Override
    public List<Flower> getAll() throws Exception {
        List<Flower> list = new ArrayList<Flower>();
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from flower");
            ResultSet rs = stmt.executeQuery())
        {
            while (rs.next()) {
                if (rs.getString("name").equals("chamomile")) {
                    Flower flower = new Chamomile(rs.getInt("petals"), rs.getInt("lenght"),
                            rs.getFloat("price"), new FreshnessInteger(rs.getInt("freshness")) );
                    list.add(flower);
                } else if (rs.getString("name").equals("rose")) {
                    Flower flower = new Rose(rs.getBoolean("spike"), rs.getInt("lenght"),
                            rs.getFloat("price"), new FreshnessInteger(rs.getInt("freshness")));
                    list.add(flower);
                } else if (rs.getString("name").equals("tulip")) {
                    Flower flower = new Tulip();
                    flower.setPrice(rs.getFloat("price"));
                    flower.setLenght(rs.getInt("lenght"));
                    flower.setFreshness(new FreshnessInteger(rs.getInt("freshness")));
                    list.add(flower);
                }
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Flower getById(int id) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM flower WHERE id=?");
             ResultSet rs = stmt.executeQuery())
        {
            stmt.setInt(1, id);
            Flower flower = null;
            if (rs.next()) {
                if (rs.getString("name").equals("chamomile")) {
                    flower = new Chamomile(rs.getInt("petals"), rs.getInt("lenght"),
                            rs.getFloat("price"), new FreshnessInteger(rs.getInt("freshness")) );
                } else if (rs.getString("name").equals("rose")) {
                    flower = new Rose(rs.getBoolean("spike"), rs.getInt("lenght"),
                            rs.getFloat("price"), new FreshnessInteger(rs.getInt("freshness")));
                } else if (rs.getString("name").equals("tulip")) {
                    flower = new Tulip();
                    flower.setPrice(rs.getFloat("price"));
                    flower.setLenght(rs.getInt("lenght"));
                    flower.setFreshness(new FreshnessInteger(rs.getInt("freshness")));
                }
                return flower;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public int add(Flower flower) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO flower(name, lenght, freshness, price, petals, spike) " +
                    "VALUES(?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS))
        {
            if (flower instanceof Chamomile) {
                stmt.setString(1, "chamomile");
                stmt.setInt(2, flower.getLenght());
                stmt.setInt(3, (int)flower.getFreshness().getFreshness());
                stmt.setFloat(4, flower.getPrice());
                stmt.setInt(5, ((Chamomile)flower).getPetals());
            }
            else if (flower instanceof Rose) {
                stmt.setString(1, "rose");
                stmt.setInt(2, flower.getLenght());
                stmt.setInt(3, (int)flower.getFreshness().getFreshness());
                stmt.setFloat(4, flower.getPrice());
                stmt.setBoolean(6, ((Rose)flower).getSpike());
            }
            else if (flower instanceof Tulip) {
                stmt.setString(1, "tulip");
                stmt.setInt(2, flower.getLenght());
                stmt.setInt(3, (int)flower.getFreshness().getFreshness());
                stmt.setFloat(4, flower.getPrice());
            }
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }

    }

    @Override
    public int update(int id, Flower flower) throws Exception {
        try (Connection conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(
                    "UPDATE flower SET name=?, lenght=?, freshness=?, price=?, petals=?, spike=? WHERE  id=?"))
        {
            if (flower instanceof Chamomile) {
                stmt.setString(1, "chamomile");
                stmt.setInt(2, flower.getLenght());
                stmt.setInt(3, (int)flower.getFreshness().getFreshness());
                stmt.setFloat(4, flower.getPrice());
                stmt.setInt(5, ((Chamomile)flower).getPetals());
            }
            else if (flower instanceof Rose) {
                stmt.setString(1, "rose");
                stmt.setInt(2, flower.getLenght());
                stmt.setInt(3, (int)flower.getFreshness().getFreshness());
                stmt.setFloat(4, flower.getPrice());
                stmt.setBoolean(6, ((Rose)flower).getSpike());
            }
            else if (flower instanceof Tulip) {
                stmt.setString(1, "tulip");
                stmt.setInt(2, flower.getLenght());
                stmt.setInt(3, (int)flower.getFreshness().getFreshness());
                stmt.setFloat(4, flower.getPrice());
            }
            stmt.setInt(7, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public int delete(int id) throws Exception {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM flower WHERE id=?"))
        {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }
    private Connection getConnection() throws Exception {
        File file = new File("flowergarden.db");
        String url = "jdbc:sqlite:"+file.getCanonicalFile().toURI();
        return DriverManager.getConnection(url);
    }
}
