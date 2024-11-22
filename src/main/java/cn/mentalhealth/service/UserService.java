package cn.mentalhealth.service;

import cn.mentalhealth.dao.UserDao;
import cn.mentalhealth.dao.impl.UserDaoImpl;
import cn.mentalhealth.domain.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class UserService {

    private UserDao userDao = new UserDaoImpl();
    private ObjectMapper objectMapper = new ObjectMapper();

    // 初始化ObjectMapper，设置序列化时的一些特性，比如日期格式等（可根据实际需求调整）
    public UserService() {
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    // 获取所有用户记录，并以JSON报文格式返回
    public String getAllUsersAsJson() {
        List<User> users = getAllUsers();
        try {
            return objectMapper.writeValueAsString(users);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"获取所有用户记录时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 根据用户ID获取特定用户记录，并以JSON报文格式返回
    public String getUserByIdAsJson(int uid) {
        User user = getUserById(uid);
        try {
            if (user!= null) {
                return objectMapper.writeValueAsString(user);
            } else {
                return "{\"error\": \"未找到ID为 " + uid + " 的用户\", \"details\": \"请检查输入的用户ID是否正确\"}";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"根据ID获取用户记录时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 根据用户邮箱获取特定用户记录，并以JSON报文格式返回
    public String getUserByEmailAsJson(String email) {
        User user = getUserByEmail(email);
        try {
            if (user!= null) {
                return objectMapper.writeValueAsString(user);
            } else {
                return "{\"error\": \"未找到邮箱为 " + email + " 的用户\", \"details\": \"请检查输入的邮箱是否正确\"}";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"根据邮箱获取用户记录时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 获取所有用户记录
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }

    // 根据用户ID获取特定用户记录
    public User getUserById(int uid) {
        return userDao.getUserById(uid);
    }

    // 根据用户邮箱获取特定用户记录
    public User getUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    // 添加新的用户记录
    public void addUser(User user) {
        userDao.insertUser(user);
    }

    // 更新用户记录
    public void updateUser(User user, String condition) {
        userDao.updateUser(user, condition);
    }

    // 删除用户记录
    public void deleteUser(String condition) {
        userDao.deleteUser(condition);
    }

    // 从HttpServletRequest中获取用户ID并更新用户名称，返回操作结果的JSON报文
    public String updateUserName(HttpServletRequest request) {
        try {
            int uid = Integer.parseInt(request.getParameter("uid"));
            String newUserName = request.getParameter("newUserName");

            User user = getUserById(uid);
            if (user!= null) {
                user.setUName(newUserName);
                // 这里可以添加一些额外的逻辑，比如记录操作日志，假设已经有一个log方法用于记录日志
                log("用户 " + uid + " 更新了名称为 " + newUserName, request);
                updateUser(user, "Uid = " + uid);
                return "{\"success\": true, \"message\": \"用户名称更新成功\"}";
            } else {
                return "{\"success\": false, \"error\": \"未找到ID为 " + uid + " 的用户，无法更新名称\", \"details\": \"请检查输入的用户ID是否正确\"}";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "{\"success\": false, \"error\": \"更新用户名称时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 从HttpServletRequest中获取用户ID并更新用户邮箱，返回操作结果的JSON报文
    public String updateUserEmail(HttpServletRequest request) {
        try {
            int uid = Integer.parseInt(request.getParameter("uid"));
            String newEmail = request.getParameter("newEmail");

            User user = getUserById(uid);
            if (user!= null) {
                user.setEmail(newEmail);
                // 记录操作日志
                log("用户 " + uid + " 更新了邮箱为 " + newEmail, request);
                updateUser(user, "Uid = " + uid);
                return "{\"success\": true, \"message\": \"用户邮箱更新成功\"}";
            } else {
                return "{\"success\": false, \"error\": \"未找到ID为 " + uid + " 的用户，无法更新邮箱\", \"details\": \"请检查输入的用户ID是否正确\"}";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "{\"success\": false, \"error\": \"更新用户邮箱时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 从HttpServletRequest中获取用户ID并更新用户头像地址，返回操作结果的JSON报文
    public String updateUserAvatar(HttpServletRequest request) {
        try {
            int uid = Integer.parseInt(request.getParameter("uid"));
            String newAvatarUrl = request.getParameter("newAvatarUrl");

            User user = getUserById(uid);
            if (user!= null) {
                user.setAvatarUser(newAvatarUrl);
                // 记录操作日志
                log("用户 " + uid + " 更新了头像地址为 " + newAvatarUrl, request);
                updateUser(user, "Uid = " + uid);
                return "{\"success\": true, \"message\": \"用户头像地址更新成功\"}";
            } else {
                return "{\"success\": false, \"error\": \"未找到ID为 " + uid + " 的用户，无法更新头像地址\", \"details\": \"请检查输入的用户ID是否正确\"}";
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "{\"success\": false, \"error\": \"更新用户头像地址时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 模拟记录操作日志的方法，这里只是简单打印，实际应用中可能需要将日志记录到文件或数据库等
    private void log(String message, HttpServletRequest request) {
        System.out.println("操作日志: " + message + "，来源IP: " + request.getRemoteAddr());
    }
}