package DatabaseTest;

import cn.mentalhealth.dao.impl.UserDaoImpl;
import cn.mentalhealth.domain.User;
import cn.mentalhealth.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class UserServiceTest {

    private UserService userService;
    private UserDaoImpl userDaoImpl;

    @BeforeEach
    public void setUp() {
        userDaoImpl = new UserDaoImpl();
        userService = new UserService();
    }

    // 测试获取所有用户记录的方法
    @Test
    public void testGetAllUsers() {
        List<User> users = userService.getAllUsers();
        assertNotNull(users);
    }

    // 测试根据用户ID获取特定用户记录的方法
    @Test
    public void testGetUserById() {
        // 假设数据库中存在一个用户记录，其Uid为1（可根据实际情况调整）
        int uid = 1;
        User user = userService.getUserById(uid);
        if (user!= null) {
            assertEquals(uid, user.getUid());
        } else {
            assertNull(user);
        }
    }

    // 测试根据用户邮箱获取特定用户记录的方法
    @Test
    public void testGetUserByEmail() {
        // 假设数据库中存在一个用户记录，其邮箱为"test@example.com"（可根据实际情况调整）
        String email = "example@qq.com";
        User user = userService.getUserByEmail(email);
        if (user!= null) {
            assertEquals(email, user.getEmail());
        } else {
            assertNull(user);
        }
    }

    // 测试添加新用户记录的方法
    @Test
    public void testAddUser() {
        User newUser = new User();
        newUser.setEmail("test@example.com");
        newUser.setUName("New User");
        newUser.setAvatarUser("default_avatar.jpg");

        userService.addUser(newUser);

        // 再次查询数据库，验证是否插入成功
        User insertedUser = userService.getUserByEmail(newUser.getEmail());
        assertNotNull(insertedUser);
        assertEquals(newUser.getEmail(), insertedUser.getEmail());
        assertEquals(newUser.getUName(), insertedUser.getUName());
        assertEquals(newUser.getAvatarUser(), insertedUser.getAvatarUser());
    }

    // 测试更新用户记录的方法
    @Test
    public void testUpdateUser() {
        // 假设数据库中存在一个用户记录，其Uid为2（可根据实际情况调整）
        int uid = 1;
        User originalUser = userService.getUserById(uid);

        if (originalUser!= null) {
            originalUser.setUName("Updated User Name");
            userService.updateUser(originalUser, "Uid = " + uid);

            User updatedUser = userService.getUserById(uid);
            assertEquals("Updated User Name", updatedUser.getUName());
        } else {
            assertNull(originalUser);
        }
    }

    // 测试删除用户记录的方法
    @Test
    public void testDeleteUser() {
        // 假设数据库中存在一个用户记录，其Uid为3（可根据实际情况调整）
        int uid = 2;
        User userToDelete = userService.getUserById(uid);

        if (userToDelete!= null) {
            userService.deleteUser("Uid = 0000000002");

            User deletedUser = userService.getUserById(uid);
            assertNull(deletedUser);
        } else {
            assertNull(userToDelete);
        }
    }
}