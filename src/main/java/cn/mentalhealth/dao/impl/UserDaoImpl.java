package cn.mentalhealth.dao.impl;
import cn.mentalhealth.dao.UserDao;
import cn.mentalhealth.domain.User;
import cn.mentalhealth.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM user";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                User user = new User();
                user.setUid(resultSet.getInt("Uid"));
                user.setEmail(resultSet.getString("Email"));
                user.setUName(resultSet.getString("UName"));
                user.setAvatarUser(resultSet.getString("AvatarUser"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    @Override
    public User getUserById(int uid) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE Uid =?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, uid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user.setUid(resultSet.getInt("Uid"));
                    user.setEmail(resultSet.getString("Email"));
                    user.setUName(resultSet.getString("UName"));
                    user.setAvatarUser(resultSet.getString("AvatarUser"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public User getUserByEmail(String email) {
        User user = new User();
        String sql = "SELECT * FROM user WHERE Email =?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, email);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user.setUid(resultSet.getInt("Uid"));
                    user.setEmail(resultSet.getString("Email"));
                    user.setUName(resultSet.getString("UName"));
                    user.setAvatarUser(resultSet.getString("AvatarUser"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public void insertUser(User user) {
        String sql = "INSERT INTO user (Email, UName, AvatarUser) VALUES (?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUName());
            preparedStatement.setString(3, user.getAvatarUser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateUser(User user, String condition) {
        String sql = "UPDATE user SET Email =?, UName =?, AvatarUser =? WHERE " + condition;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getEmail());
            preparedStatement.setString(2, user.getUName());
            preparedStatement.setString(3, user.getAvatarUser());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
//    @Override
//    public void deleteUser(String condition) {
//        String sql = "DELETE FROM user WHERE " + condition;
//        try (Connection connection = jdbcUtils.getConnection();
//             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
//            preparedStatement.executeUpdate();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
@Override
public void deleteUser(String condition) {
    Connection connection = null;
    try {
        connection = jdbcUtils.getConnection();
        connection.setAutoCommit(false);

        String sql = "DELETE FROM user WHERE " + condition;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        }

        connection.commit();
    } catch (SQLException e) {
        if (connection!= null) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        e.printStackTrace();
    } finally {
        if (connection!= null) {
            try {
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
}