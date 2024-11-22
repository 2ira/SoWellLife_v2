package DatabaseTest;

import cn.mentalhealth.dao.impl.TestzDaoImpl;
import cn.mentalhealth.domain.Testz;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import java.util.List;

public class TestDaoImplTest {

    private TestzDaoImpl testDaoImpl;

    @BeforeEach
    public void setUp() {
        testDaoImpl = new TestzDaoImpl();
    }

    @Test
    public void testGetAllTests() {
        List<Testz> tests = testDaoImpl.getAllTests();
        assertNotNull(tests);
    }

    @Test
    public void testGetTestById() {
        // 假设数据库中存在一个测试记录，其Tid为1（可根据实际情况调整）
        int tid = 1;
        Testz test = testDaoImpl.getTestById(tid);
        if (test!= null) {
            assertEquals(tid, test.getTid());
        } else {
            assertNull(test);
        }
    }

    @Test
    public void testGetTestsByUid() {
        // 假设数据库中存在用户ID为2的相关测试记录（可根据实际情况调整）
        int uid = 2;
        List<Testz> tests = testDaoImpl.getTestsByUid(uid);
        assertNotNull(tests);
    }

    @Test
    public void testGetTestsByTag() {
        // 假设数据库中存在标签为"SomeTag"的相关测试记录（可根据实际情况调整）
        String tag = "SomeTag";
        List<Testz> tests = testDaoImpl.getTestsByTag(tag);
        assertNotNull(tests);
    }

    @Test
    public void testInsertTest() {
        Testz test = new Testz();
        test.setUid(3);
        test.setTime(LocalDateTime.now());
        test.setTag("NewTestTag");
        test.setResult("Pending");

        testDaoImpl.insertTest(test);

        // 再次查询数据库，验证是否插入成功
        List<Testz> tests = testDaoImpl.getTestsByUid(test.getUid());
        assertTrue(tests.stream().anyMatch(t -> t.getTag().equals(test.getTag())));
    }

    @Test
    public void testUpdateTest() {
        // 假设数据库中存在一个测试记录，其Tid为4（可根据实际情况调整）
        int tid = 4;
        Testz originalTest = testDaoImpl.getTestById(tid);

        if (originalTest!= null) {
            originalTest.setTag("UpdatedTag");
            testDaoImpl.updateTest(originalTest, "Tid = " + tid);

            Testz updatedTest = testDaoImpl.getTestById(tid);
            assertEquals("UpdatedTag", updatedTest.getTag());
        } else {
            assertNull(originalTest);
        }
    }

    @Test
    public void testDeleteTest() {
        // 假设数据库中存在一个测试记录，其Tid为5（可根据实际情况调整）
        int tid = 5;
        Testz testToDelete = testDaoImpl.getTestById(tid);

        if (testToDelete!= null) {
            testDaoImpl.deleteTest("Tid = " + tid);

            Testz deletedTest = testDaoImpl.getTestById(tid);
            assertNull(deletedTest);
        } else {
            assertNull(testToDelete);
        }
    }
}