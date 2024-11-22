package cn.mentalhealth.dao;

import cn.mentalhealth.domain.Testz;

import java.util.List;

public interface TestzDao {
    // 获取所有测试记录
    List<Testz> getAllTests();

    // 根据测试ID获取特定测试记录
    Testz getTestById(int tid);

    // 根据用户ID获取该用户的所有测试记录
    List<Testz> getTestsByUid(int uid);

    // 根据测试标签获取相关测试记录
    List<Testz> getTestsByTag(String tag);

    // 插入新的测试记录
    void insertTest(Testz test);

    // 更新测试记录
    void updateTest(Testz test, String condition);

    // 删除测试记录
    void deleteTest(String condition);
}