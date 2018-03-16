package com.flowergarden.bouquet;

import com.flowergarden.flowers.GeneralFlower;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BouquetDaoImpl implements BouquetDao{
    @Override
    public List<Bouquet> getAll() throws IOException {
        Connection conn = null;
        PreparedStatement stmt = null;
        List<Bouquet> list = new ArrayList<Bouquet>();

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("select * from bouquet");
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                if (rs.getString("name").equals("married")) {
                    MarriedBouquet bouquet = new MarriedBouquet();
                    bouquet.setAssembledPrice(rs.getFloat("assemble_price"));
                    list.add(bouquet);
                }
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            close(stmt);
            close(conn);
        }

        return list;
    }

    @Override
    public Bouquet getById(int id) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("SELECT * FROM bouquet WHERE id=?");
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Bouquet bouquet = new MarriedBouquet();
                bouquet.setAssembledPrice(rs.getFloat("assemble_price"));

                return bouquet;
            } else {
                return null;
            }
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public int add(Bouquet bouquet) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("INSERT INTO bouquet(name, assemble_price) VALUES(?, ?)", Statement.RETURN_GENERATED_KEYS);
            stmt.setString(1, "married");
            stmt.setDouble(2, bouquet.getPrice());

            int result = stmt.executeUpdate();
            ResultSet rs = stmt.getGeneratedKeys();

            return result;
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }

    }

    @Override
    public int update(int id, Bouquet bouquet) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("UPDATE bouquet SET assemble_price=? WHERE id=?");
            stmt.setDouble(1, bouquet.getPrice());
            stmt.setInt(2, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }

    @Override
    public int delete(int id) throws Exception {
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            conn = getConnection();
            stmt = conn.prepareStatement("DELETE FROM bouquet WHERE id=?");
            stmt.setInt(1, id);

            return stmt.executeUpdate();
        } catch (SQLException e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        } finally {
            close(stmt);
            close(conn);
        }
    }
    private Connection getConnection() throws IOException {
        File file = new File("flowergarden.db");
        String url = "jdbc:sqlite:"+file.getCanonicalFile().toURI();
        try {
            return DriverManager.getConnection(url);
        } catch (Exception e) {
            // e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private static void close(Connection con) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }


    private static void close(Statement stmt) {
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                // e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
    }

}
