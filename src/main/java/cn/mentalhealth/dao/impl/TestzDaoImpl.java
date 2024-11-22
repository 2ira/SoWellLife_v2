package cn.mentalhealth.dao.impl;

import cn.mentalhealth.dao.TestzDao;
import cn.mentalhealth.domain.Testz;
import cn.mentalhealth.utils.JDBCUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TestzDaoImpl implements TestzDao {

    private JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Testz> getAllTests() {
        List<Testz> testList = new ArrayList<>();
        String sql = "SELECT * FROM test";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Testz test = new Testz();
                test.setTid(resultSet.getInt("Tid"));
                test.setUid(resultSet.getInt("Uid"));
                test.setTime(resultSet.getTimestamp("Time").toLocalDateTime());
                test.setTag(resultSet.getString("Tag"));
                test.setResult(resultSet.getString("Result"));
                testList.add(test);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    @Override
    public Testz getTestById(int tid) {
        Testz test = new Testz();
        String sql = "SELECT * FROM test WHERE Tid =?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, tid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    test.setTid(resultSet.getInt("Tid"));
                    test.setUid(resultSet.getInt("Uid"));
                    test.setTime(resultSet.getTimestamp("Time").toLocalDateTime());
                    test.setTag(resultSet.getString("Tag"));
                    test.setResult(resultSet.getString("Result"));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return test;
    }

    @Override
    public List<Testz> getTestsByUid(int uid) {
        List<Testz> testList = new ArrayList<>();
        String sql = "SELECT * FROM test WHERE Uid =?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, uid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Testz test = new Testz();
                    test.setTid(resultSet.getInt("Tid"));
                    test.setUid(resultSet.getInt("Uid"));
                    test.setTime(resultSet.getTimestamp("Time").toLocalDateTime());
                    test.setTag(resultSet.getString("Tag"));
                    test.setResult(resultSet.getString("Result"));
                    testList.add(test);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    @Override
    public List<Testz> getTestsByTag(String tag) {
        List<Testz> testList = new ArrayList<>();
        String sql = "SELECT * FROM test WHERE Tag LIKE '%' +? + '%'";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, tag);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Testz test = new Testz();
                    test.setTid(resultSet.getInt("Tid"));
                    test.setUid(resultSet.getInt("Uid"));
                    test.setTime(resultSet.getTimestamp("Time").toLocalDateTime());
                    test.setTag(resultSet.getString("Tag"));
                    test.setResult(resultSet.getString("Result"));
                    testList.add(test);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return testList;
    }

    @Override
    public void insertTest(Testz test) {
        String sql = "INSERT INTO test (Uid, Time, Tag, Result) VALUES (?,?,?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, test.getUid());
            preparedStatement.setObject(2, test.getTime());
            preparedStatement.setString(3, test.getTag());
            preparedStatement.setString(4, test.getResult());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateTest(Testz test, String condition) {
        String sql = "UPDATE test SET Uid =?, Time =?, Tag =?, Result =? WHERE " + condition;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, test.getUid());
            preparedStatement.setObject(2, test.getTime());
            preparedStatement.setString(3, test.getTag());
            preparedStatement.setString(4, test.getResult());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteTest(String condition) {
        String sql = "DELETE FROM test WHERE " + condition;
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}