package cn.mentalhealth.dao;
import cn.mentalhealth.domain.Favorite;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public interface FavoriteDao {
    public List<Favorite> getAllFavorites();

    public List<Favorite> getFavoritesByUid(int uid);

    public List<Favorite> getFavoritesByRid(int rid);

    public void insertFavorite(Favorite favorite);

    //用户点击不收藏，即删除一条记录，不存在更新
    public void deleteFavorite(int uid, int rid);

    public boolean isFavoriteByUidAndRid(int uid, int rid);

}
