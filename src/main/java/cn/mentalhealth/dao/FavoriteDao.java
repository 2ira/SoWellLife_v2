package cn.mentalhealth.dao;

import cn.mentalhealth.domain.Favorite;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FavoriteDao {
    void insertFavorite(Favorite favorite);
    void deleteFavorite(int Uid, int Rid, int flag);
    List<Favorite> getFavoritesByUidAndFlag(int Uid, int flag);


}