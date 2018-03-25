package com.flowergarden.dao;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("bouquetDao")
public class BouquetDaoImpl implements BouquetDao{

    @Autowired
    private DataSource dataSource;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Bouquet> getAll() throws Exception {
        List<Bouquet> list = new ArrayList<Bouquet>();
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from bouquet");
            ResultSet rs = stmt.executeQuery())
        {
            while (rs.next()) {
                if (rs.getString("name").equals("married")) {
                    MarriedBouquet bouquet = new MarriedBouquet();
                    bouquet.setAssembledPrice(rs.getFloat("assemble_price"));
                    list.add(bouquet);
                }
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Bouquet getById(int id) throws Exception {
        Bouquet bouquet = null;
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM bouquet WHERE id=?");
             ResultSet rs = stmt.executeQuery())
        {
            stmt.setInt(1, id);
            if (rs.next()) {
                bouquet = new MarriedBouquet();
                bouquet.setAssembledPrice(rs.getFloat("assemble_price"));
                return bouquet;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public int add(Bouquet bouquet) throws Exception {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("INSERT INTO bouquet(name, assemble_price) VALUES(?, ?)",
                    Statement.RETURN_GENERATED_KEYS))
        {
            stmt.setString(1, "married");
            stmt.setDouble(2, bouquet.getPrice());
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public int update(int id, Bouquet bouquet) throws Exception {
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("UPDATE bouquet SET assemble_price=? WHERE id=?"))
        {
            stmt.setDouble(1, bouquet.getPrice());
            stmt.setInt(2, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public int delete(int id) throws Exception {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("DELETE FROM bouquet WHERE id=?"))
        {
            stmt.setInt(1, id);
            return stmt.executeUpdate();
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

}
