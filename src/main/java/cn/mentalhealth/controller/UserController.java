package cn.mentalhealth.controller;

import cn.mentalhealth.domain.User;
import cn.mentalhealth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.regex.Pattern;

@RestController
@RequestMapping("/api/login")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    // 正则表达式检查是否为有效邮箱
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$");

    /**
     * 处理获取用户信息的 GET 请求
     *
     * @param identifier 用户唯一标识符（邮箱或ID）
     * @return 用户信息
     */
    @GetMapping("/profile")
    public User getUserByGet(@RequestParam("identifier") String identifier) {
        logger.info("GET 请求: 获取用户信息，传入标识符: {}", identifier);
        return fetchUser(identifier);
    }

    /**
     * 处理获取用户信息的 POST 请求
     *
     * @param identifier 用户唯一标识符（邮箱或ID）
     * @return 用户信息
     */
    @PostMapping("/profile")
    public User getUserByPost(@RequestParam("identifier") String identifier) {
        logger.info("POST 请求: 获取用户信息，传入标识符: {}", identifier);
        return fetchUser(identifier);
    }

    /**
     * 统一的获取用户信息逻辑
     *
     * @param identifier 用户唯一标识符（邮箱或ID）
     * @return 用户信息
     */
    private User fetchUser(String identifier) {
        User user = null;

        try {
            if (isValidEmail(identifier)) {
                // 使用邮箱验证
                logger.info("标识符识别为邮箱，开始根据邮箱获取用户信息，邮箱: {}", identifier);
                user = userService.getUserByEmail(identifier);
            } else {
                // 使用用户ID验证
                int userId = Integer.parseInt(identifier); // 确保 identifier 可转为整数
                logger.info("标识符识别为用户ID，开始根据用户ID获取用户信息，用户ID: {}", userId);
                user = userService.getUserById(userId);
            }

            if (user == null) {
                logger.error("未找到用户，标识符: {}", identifier);
                throw new RuntimeException("用户不存在");
            }

            // 打印用户信息，确保所有字段（如 UName）都包含在内
            logger.info("用户信息：Uid = {}, UName = {}, Email = {}, Avatar = {}", user.getUid(), user.getUName(), user.getEmail(), user.getAvatarUser());

        } catch (NumberFormatException e) {
            logger.error("无效的用户ID格式: {}", identifier, e);
            throw new RuntimeException("无效的用户ID格式");
        } catch (Exception e) {
            logger.error("获取用户信息失败，标识符: {}", identifier, e);
            throw new RuntimeException("获取用户信息失败");
        }

        return user;
    }

    /**
     * 用于验证邮箱格式是否有效
     *
     * @param email 邮箱
     * @return 是否有效
     */
    private boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    /**
     * 用户密码验证接口（这个部分与获取用户信息的部分无关，但也涉及到用户名和密码验证）
     */
    @PostMapping("/verify-password")
    public String verifyPassword(@RequestParam("identifier") String identifier,
                                 @RequestParam("password") String password) {

        logger.info("开始验证密码，传入标识符: {}", identifier);
        logger.info("开始验证密码，传入密码: {}", password);

        try {
            User user ;

            // 判断是否为有效邮箱
            if (isValidEmail(identifier)) {
                // 使用邮箱验证密码
                logger.info("识别为邮箱，开始根据邮箱验证密码，邮箱: {}", identifier);
                user = userService.getUserByEmail(identifier);
                logger.info("用户名: {}", user.getUName());
            } else {
                // 使用用户ID验证密码
                try {
                    int userId = Integer.parseInt(identifier);
                    logger.info("识别为用户ID，开始根据用户ID验证密码，用户ID: {}", userId);
                    user = userService.getUserById(userId);
                } catch (NumberFormatException e) {
                    logger.error("无效的标识符: {}", identifier);
                    return "{\"success\": false, \"message\": \"无效的标识符\"}";
                }
            }

            if (user == null) {
                logger.error("未找到用户，标识符: {}", identifier);
                return "{\"success\": false, \"message\": \"用户不存在\"}";
            }

            String storedPassword = user.getUserPSW();
            logger.debug("从数据库中获取的存储密码: {}", storedPassword);

            // 比较输入的密码和存储的密码
            if (storedPassword != null && storedPassword.equals(password)) {
                logger.info("密码验证成功，标识符: {}", identifier);
                return "{\"success\": true, \"message\": \"密码验证成功\"}";
            } else {
                logger.warn("密码验证失败，标识符: {}, 提供的密码: {}", identifier, password);
                return "{\"success\": false, \"message\": \"密码错误\"}";
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("密码验证过程中出错，标识符: {}", identifier, e);
            return "{\"success\": false, \"message\": \"验证过程中出错\"}";
        }
    }
}
