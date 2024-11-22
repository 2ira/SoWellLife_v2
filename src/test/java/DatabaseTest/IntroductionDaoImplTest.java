package DatabaseTest;

import cn.mentalhealth.dao.impl.IntroductionDaoImpl;
import cn.mentalhealth.domain.Introduction;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class IntroductionDaoImplTest {

    private IntroductionDaoImpl introductionDaoImpl;

    @BeforeEach
    public void setUp() {
        introductionDaoImpl = new IntroductionDaoImpl();
    }

    @Test
    public void testGetAllIntroductions() {
        List<Introduction> introductions = introductionDaoImpl.getAllIntroductions();
        assertNotNull(introductions);
    }

    @Test
    public void testGetIntroductionById() {
        // 假设数据库中存在一个Introduction记录，其iid为1（可根据实际情况调整）
        int iid = 1;
        Introduction introduction = introductionDaoImpl.getIntroductionById(iid);
        if (introduction!= null) {
            assertEquals(iid, introduction.getIid());
        } else {
            assertNull(introduction);
        }
    }

    @Test
    public void testGetIntroductionsByType() {
        // 假设数据库中存在类型为"SomeType"的相关Introduction记录（可根据实际情况调整）
        String type = "SomeType";
        List<Introduction> introductions = introductionDaoImpl.getIntroductionsByType(type);
        assertNotNull(introductions);
    }

    @Test
    public void testInsertIntroduction() {
        Introduction introduction = new Introduction();
        introduction.setType("NewType");
        introduction.setSymptom("NewSymptom");
        introduction.setCauses("NewCauses");
        introduction.setTreatment("NewTreatment");
        introduction.setPicture("NewPicture");

        introductionDaoImpl.insertIntroduction(introduction);

        // 再次查询数据库，验证是否插入成功
        List<Introduction> introductions = introductionDaoImpl.getIntroductionsByType(introduction.getType());
        assertTrue(introductions.stream().anyMatch(i -> i.getSymptom().equals(introduction.getSymptom())));
    }

    @Test
    public void testUpdateIntroduction() {
        // 假设数据库中存在一个Introduction记录，其iid为2（可根据实际情况调整）
        int iid = 2;
        Introduction originalIntroduction = introductionDaoImpl.getIntroductionById(iid);

        if (originalIntroduction!= null) {
            originalIntroduction.setSymptom("UpdatedSymptom");
            introductionDaoImpl.updateIntroduction(originalIntroduction, "iid = " + iid);

            Introduction updatedIntroduction = introductionDaoImpl.getIntroductionById(iid);
            assertEquals("UpdatedSymptom", updatedIntroduction.getSymptom());
        } else {
            assertNull(originalIntroduction);
        }
    }

    @Test
    public void testDeleteIntroduction() {
        // 假设数据库中存在一个Introduction记录，其iid为3（可根据实际情况调整）
        int iid = 3;
        Introduction introductionToDelete = introductionDaoImpl.getIntroductionById(iid);

        if (introductionToDelete!= null) {
            introductionDaoImpl.deleteIntroduction("iid = " + iid);

            Introduction deletedIntroduction = introductionDaoImpl.getIntroductionById(iid);
            assertNull(deletedIntroduction);
        } else {
            assertNull(introductionToDelete);
        }
    }
}