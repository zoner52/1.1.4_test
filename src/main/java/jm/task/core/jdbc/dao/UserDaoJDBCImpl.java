package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class UserDaoJDBCImpl  implements UserDao {
    public UserDaoJDBCImpl() {

    }
    Util utilConnect = new Util();

    @Override
    public void createUsersTable() {
        try (Statement st = utilConnect.getConnection().createStatement()) {
            st.execute("create table if not exists Users (" +
                    "ID bigint primary key auto_increment," +
                    "Name varchar(15) not null," +
                    "LastName varchar(15)," +
                    "Age tinyint)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement st = utilConnect.getConnection().createStatement()) {
            st.execute("drop table if exists Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement st = utilConnect.getConnection().prepareStatement("insert into Users (Name, LastName, Age) values (?, ?, ?)")) {
            st.setString(1, name);
            st.setString(2, lastName);
            st.setByte(3, age);
            st.executeUpdate();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        String sql = "delete from Users where ID = ?";
        try (PreparedStatement st = utilConnect.getConnection().prepareStatement(sql)) {
            st.setLong(1, id);
            st.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        try (Statement st = utilConnect.getConnection().createStatement()) {
            ResultSet resultSet = st.executeQuery("select * from Users");
            while (resultSet.next()) {
                User tUser = new User();
                tUser.setId(resultSet.getLong("ID"));
                tUser.setName(resultSet.getString("Name"));
                tUser.setLastName(resultSet.getString("LastName"));
                tUser.setAge(resultSet.getByte("Age"));
                list.add(tUser);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public void cleanUsersTable() {
        try (Statement st = utilConnect.getConnection().createStatement()) {
            st.execute("truncate table Users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
