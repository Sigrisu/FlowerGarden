package com.flowergarden.dao.impl.db;

import com.flowergarden.bouquet.Bouquet;
import com.flowergarden.bouquet.MarriedBouquet;
import com.flowergarden.dao.BouquetDao;
import com.flowergarden.dao.BouquetDaoIO;
import com.flowergarden.dao.impl.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository("bouquetDao")
public class BouquetDaoImpl implements BouquetDao, BouquetDaoIO {

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
    public int addAll(List<Bouquet> bouquets) throws Exception {
        try (Connection conn = dataSource.getConnection();
             PreparedStatement stmt = conn.prepareStatement("INSERT INTO bouquet(name, assemble_price) VALUES(?, ?)",
                     Statement.RETURN_GENERATED_KEYS))
        {
            for (Bouquet bouquet: bouquets) {

                stmt.setString(1, "married");
                stmt.setDouble(2, bouquet.getPrice());
                stmt.addBatch();
            }
            return stmt.executeBatch().length;

        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        }
    }

    @Override
    public Bouquet getById(int id) throws Exception {
        Bouquet bouquet = null;
        ResultSet rs = null;
        try (Connection conn = dataSource.getConnection();
            PreparedStatement stmt = conn.prepareStatement("select * from bouquet where id=?"))
        {
            stmt.setInt(1, id);
            rs= stmt.executeQuery();
            if (rs.next()) {
                bouquet = new MarriedBouquet();
                bouquet.setAssembledPrice(rs.getFloat("assemble_price"));
                return bouquet;
            } else {
                return null;
            }
        } catch (SQLException e) {
            throw new CustomException(e.getMessage(), e);
        } finally {
            try { if (rs != null) rs.close(); } catch (Exception e) {
                throw new CustomException(e.getMessage(), e);
            };
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
