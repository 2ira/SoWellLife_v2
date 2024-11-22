package cn.mentalhealth.service;

import cn.mentalhealth.dao.FavoriteDao;
import cn.mentalhealth.dao.impl.FavoriteDaoImpl;
import cn.mentalhealth.domain.Favorite;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

//这里实现不完全，应该对应控件注入对应的变量。
public class FavoriteService {

    private FavoriteDao favoriteDao = new FavoriteDaoImpl();
    private ObjectMapper objectMapper = new ObjectMapper();

    // 添加收藏功能，从报文中获取用户ID，返回添加结果的报文
    public String addFavorite(HttpServletRequest request) {
        try {
            // 从报文中获取用户ID和资源ID
            int uid = Integer.parseInt(request.getParameter("uid"));
            int rid = Integer.parseInt(request.getParameter("rid"));

            Favorite favorite = new Favorite();
            favorite.setUid(uid);
            favorite.setRid(rid);

            if (!isFavorite(uid, rid)) {
                favoriteDao.insertFavorite(favorite);
                return "{\"success\": true, \"message\": \"收藏添加成功\"}";
            } else {
                return "{\"success\": false, \"message\": \"该资源已被收藏过\"}";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"添加收藏时出错\"}";
        }
    }

    // 展示该用户所有收藏内容功能，从请求中获取用户ID，以JSON报文格式返回收藏内容
    public String getFavoritesByUidAsJson(HttpServletRequest request) {
        try {
            int uid = Integer.parseInt(request.getParameter("uid"));

            List<Favorite> favorites = favoriteDao.getFavoritesByUid(uid);
            return objectMapper.writeValueAsString(favorites);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            return "{\"error\": \"根据用户ID获取收藏内容时出错\"}";
        }
    }

    // 取消收藏功能，从报文中获取用户ID和资源ID，返回取消结果的报文
    public String cancelFavorite(HttpServletRequest request) {
        try {
            int uid = Integer.parseInt(request.getParameter("uid"));
            int rid = Integer.parseInt(request.getParameter("rid"));

            if (isFavorite(uid, rid)) {
                favoriteDao.deleteFavorite(uid, rid);
                return "{\"success\": true, \"message\": \"取消收藏成功\"}";
            } else {
                return "{\"success\": false, \"message\": \"该资源未被收藏，无需取消\"}";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"取消收藏时出错\"}";
        }
    }

    // 显示所有收藏次数，从报文中获取资源ID，以JSON报文格式返回收藏次数
    public String ShowFavoriteTimeAsJson(HttpServletRequest request) {
        try {
            int rid = Integer.parseInt(request.getParameter("rid"));

            List<Favorite> fa = favoriteDao.getFavoritesByRid(rid);
            int count = fa.size();
            return "{\"count\": " + count + ", \"message\": \"获取收藏次数成功\"}";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "{\"error\": \"获取收藏次数时出错\"}";
        }
    }

    // 检查用户是否已收藏特定资源的辅助方法
    public boolean isFavorite(int uid, int rid) {
        return favoriteDao.isFavoriteByUidAndRid(uid, rid);
    }
}
