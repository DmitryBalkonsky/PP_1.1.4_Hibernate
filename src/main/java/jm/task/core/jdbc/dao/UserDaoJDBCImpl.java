package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static jm.task.core.jdbc.util.Util.getConnection;

public class UserDaoJDBCImpl implements UserDao {
    Connection connection;

    {
        try {
            connection = getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public UserDaoJDBCImpl() {

    }

    @Override
    public void createUsersTable() throws SQLException {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS Mydbtest " +
                    "(ID BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                    "name varchar(40) NOT NULL, " +
                    "lastname varchar(40) NOT NULL, age TINYINT)");

        }

    }

    public void dropUsersTable() {
        try (Connection connection = getConnection(); Statement statement = connection.createStatement()) {
            statement.execute("DROP TABLE IF EXISTS MYDBTEST");

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO MYDBTEST(name, lastname,age) VALUES (?,?,?)")) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeUserById(long id) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MYDBTEST ID=?")) {
            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM MYDBTEST")) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<User> resultUsers = new ArrayList<>();
            while (resultSet.next()) {
                resultUsers.add(new User(resultSet.getString("name"),
                        resultSet.getString("lastname"),
                        resultSet.getByte("age")));

            }
            return resultUsers;

        } catch (SQLException th) {
            th.printStackTrace();
        }
        return null;
    }

    public void cleanUsersTable() {

        try (Connection connection = getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM MYDBTEST")) {
            preparedStatement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }


    }
}
