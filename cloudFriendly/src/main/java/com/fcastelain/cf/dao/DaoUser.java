package com.fcastelain.cf.dao;

import com.fcastelain.cf.model.User;
import com.fcastelain.cf.persistence.MysqlConnector;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by fcastelain on 28/11/16.
 */
public enum DaoUser implements DaoCrud<User> {
    INSTANCE;

    private Connection conn;

    private static final String SQL_DELETE = "DELETE FROM user WHERE id = ?;";
    private static final String SQL_GET = "SELECT id, name, mail, password FROM user;";
    private static final String SQL_GET_BY_ID = "SELECT id, name, mail, password FROM user where id = ?;";
    private static final String SQL_GET_BY_NAME = "SELECT id, name, mail, password FROM user where name = ?;";
    private static final String SQL_GET_NB_EL = "SELECT count(id) number FROM user";
    private static final String SQL_UPDATE = "UPDATE user SET name = ?, mail = ?, password = ? WHERE id = ?;";
    private static final String SQL_INSERT= "insert into user (name, mail, password) values (?, ?, ?);";

    @Override
    public void delete(long id) {
        conn = MysqlConnector.INSTANCE.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setLong(1, id);
            if (!(stmt.executeUpdate() > 0)) {
                throw new DaoException("Fail to delete user");
            }
        } catch (SQLException e) {
            throw new DaoException("Fail to delete user");
        } finally {
            close(conn, stmt);
        }
    }

    @Override
    public List<User> get() {
        ArrayList<User> retVal = new ArrayList<>();
        conn = MysqlConnector.INSTANCE.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_GET);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                retVal.add(User.getBuilder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .mail(rs.getString("mail"))
                        .password(rs.getString("password"))
                        .build());
            }
        } catch (SQLException e) {
            throw new DaoException("Fail to return list of user");
        } finally {
            close(conn, stmt);
        }
        return retVal;
    }

    @Override
    public User getById(long idObj) {
        User retVal = null;
        conn = MysqlConnector.INSTANCE.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_GET_BY_ID);
            stmt.setLong(1, idObj);
            ResultSet rs = stmt.executeQuery();
            rs.first();
            retVal = User.getBuilder()
                    .id(rs.getLong("id"))
                    .name(rs.getString("name"))
                    .mail(rs.getString("mail"))
                    .password(rs.getString("password"))
                    .build();
        } catch (SQLException e) {
            throw new DaoException("Fail to return the user");
        } finally {
            close(conn, stmt);
        }
        return retVal;
    }

    @Override
    public long getNbEntity() {
        long retVal = -1;
        conn = MysqlConnector.INSTANCE.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_GET_NB_EL);
            ResultSet rs = stmt.executeQuery();
            rs.first();
            retVal = rs.getLong("number");
        } catch (SQLException e) {
            throw new DaoException("Fail to get a the number of users");
        } finally {
            close(conn, stmt);
        }
        return retVal;
    }

    @Override
    public User update(User obj) {
        conn = MysqlConnector.INSTANCE.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_UPDATE);
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getMail());
            stmt.setString(3, obj.getPassword());
            if (!(stmt.executeUpdate() > 0)) {
                throw new DaoException("Fail to update user");
            }
        } catch (SQLException e) {
            throw new DaoException("Fail to update user");
        } finally {
            close(conn, stmt);
        }
        return obj;
    }

    @Override
    public User add(User obj) {
        conn = MysqlConnector.INSTANCE.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setString(1, obj.getName());
            stmt.setString(2, obj.getMail());
            stmt.setString(3, obj.getPassword());
            if (!(stmt.executeUpdate() > 0)) {
                throw new DaoException("Fail to insert user");
            }
        } catch (SQLException e) {
            throw new DaoException("Fail to insert user");
        } finally {
            close(conn, stmt);
        }
        return obj;
    }

    @Override
    public long getIdByObj(User obj) {
        // TODO: need to do it
        return 0;
    }

    @Override
    public User isExist(String name) {
        User retVal = null;
        conn = MysqlConnector.INSTANCE.getConnection();
        PreparedStatement stmt = null;
        try {
            stmt = conn.prepareStatement(SQL_GET_BY_NAME);
            stmt.setString(1, name);
            ResultSet rs = stmt.executeQuery();
            if (rs.first()) {
                retVal = User.getBuilder()
                        .id(rs.getLong("id"))
                        .name(rs.getString("name"))
                        .mail(rs.getString("mail"))
                        .password(rs.getString("password"))
                        .build();
            }
            else {
                retVal = null;
            }
        } catch (SQLException e) {
            throw new DaoException("Fail to find user by id");
        } finally {
            close(conn, stmt);
        }
        return retVal;
    }

    @Override
    public void close(Connection conn, PreparedStatement stmt) {
        try {
            stmt.close();
            conn.close();
        } catch (SQLException e) {
            throw new DaoException("Fail to close connection");
        }
    }
}
