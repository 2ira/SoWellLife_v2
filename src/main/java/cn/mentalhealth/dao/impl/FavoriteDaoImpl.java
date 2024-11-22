package cn.mentalhealth.dao.impl;

import cn.mentalhealth.dao.FavoriteDao;
import cn.mentalhealth.domain.Favorite;
import cn.mentalhealth.utils.JDBCUtils;

import java.util.List;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class FavoriteDaoImpl implements FavoriteDao {
    private JDBCUtils jdbcUtils = new JDBCUtils();

    @Override
    public List<Favorite> getAllFavorites() {
        List<Favorite> favoriteList = new ArrayList<>();
        String sql = "SELECT * FROM favorite";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {

            while (resultSet.next()) {
                Favorite favorite = new Favorite();
                favorite.setUid(resultSet.getInt("Uid"));
                favorite.setRid(resultSet.getInt("Rid"));

                favoriteList.add(favorite);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteList;
    }

    @Override
    public List<Favorite> getFavoritesByUid(int uid) {
        List<Favorite> favoriteList = new ArrayList<>();
        String sql = "SELECT * FROM favorite WHERE Uid =?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, uid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Favorite favorite = new Favorite();
                    favorite.setUid(resultSet.getInt("Uid"));
                    favorite.setRid(resultSet.getInt("Rid"));
                    favoriteList.add(favorite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteList;
    }

    @Override
    public List<Favorite> getFavoritesByRid(int rid) {
        List<Favorite> favoriteList = new ArrayList<>();
        String sql = "SELECT * FROM favorite WHERE Rid =?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, rid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Favorite favorite = new Favorite();
                    favorite.setUid(resultSet.getInt("Uid"));
                    favorite.setRid(resultSet.getInt("Rid"));
                    favoriteList.add(favorite);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return favoriteList;
    }

    @Override
    public boolean isFavoriteByUidAndRid(int uid, int rid) {
        boolean isFavorite = false;
        String sql = "SELECT COUNT(*) AS count FROM favorite WHERE Uid =? AND Rid =?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, uid);
            preparedStatement.setInt(2, rid);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt("count");
                    isFavorite = count > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isFavorite;
    }

    @Override
    public void insertFavorite(Favorite favorite) {
        String sql = "INSERT INTO favorite (Uid, Rid) VALUES (?,?)";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, favorite.getUid());
            preparedStatement.setInt(2, favorite.getRid());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFavorite(int uid, int rid) {
        String sql = "DELETE FROM favorite WHERE Uid =? AND Rid =?";
        try (Connection connection = jdbcUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, uid);
            preparedStatement.setInt(2, rid);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
