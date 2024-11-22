package cn.mentalhealth.service;

import cn.mentalhealth.dao.TestzDao;
import cn.mentalhealth.dao.impl.TestzDaoImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import cn.mentalhealth.domain.Testz;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
////////////////////这里的测试时间没有设置，需要根据具体情况进行书写
public class TestzService {

    private TestzDao testzDao = new TestzDaoImpl();
    private ObjectMapper objectMapper = new ObjectMapper();

    // 获取所有测试记录，并以JSON报文格式返回
    public String getAllTestsAsJson() {
        List<Testz> tests = testzDao.getAllTests();
        try {
            return objectMapper.writeValueAsString(tests);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"获取所有测试记录时出错\"}";
        }
    }

    // 根据测试ID获取特定测试记录，并以JSON报文格式返回
    public String getTestByIdAsJson(int tid) {
        Testz test = testzDao.getTestById(tid);
        try {
            if (test!= null) {
                return objectMapper.writeValueAsString(test);
            } else {
                return "{\"error\": \"未找到ID为 " + tid + " 的测试记录\"}";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"根据ID获取测试记录时出错\"}";
        }
    }

    // 根据用户ID获取该用户的所有测试记录，并以JSON报文格式返回
    public String getTestsByUidAsJson(HttpServletRequest request) {
        try {
            int uid = Integer.parseInt(request.getParameter("uid"));

            List<Testz> tests = testzDao.getTestsByUid(uid);
            return objectMapper.writeValueAsString(tests);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            return "{\"error\": \"根据用户ID获取测试记录时出错\"}";
        }
    }

    // 根据测试标签获取相关测试记录，并以JSON报文格式返回
    public String getTestsByTagAsJson(HttpServletRequest request) {
        try {
            String tag = request.getParameter("tag");

            List<Testz> tests = testzDao.getTestsByTag(tag);
            return objectMapper.writeValueAsString(tests);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            return "{\"error\": \"根据标签获取测试记录时出错\"}";
        }
    }

    // 添加新的测试记录，从报文中获取测试信息并添加，返回添加结果的报文
    public String addTest(HttpServletRequest request) {
        try {
            Testz test = new Testz();
            test.setTid(Integer.parseInt(request.getParameter("tid")));
            test.setUid(Integer.parseInt(request.getParameter("uid")));
            test.setTime(/* 根据前端传入的时间格式进行解析并设置，这里假设前端传入的是符合ISO 8601格式的字符串，你可根据实际情况调整 */
                    java.time.LocalDateTime.parse(request.getParameter("time")));
            test.setTag(request.getParameter("tag"));
            test.setResult(request.getParameter("result"));

            testzDao.insertTest(test);
            return "{\"success\": true, \"message\": \"测试记录添加成功\"}";
        } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"添加测试记录时出错\"}";
        }
    }


    // 更新测试记录，从报文中获取更新信息并更新，返回更新结果的报文
    public String updateTest(HttpServletRequest request) {
        try {
            int tid = Integer.parseInt(request.getParameter("tid"));
            Testz originalTest = testzDao.getTestById(tid);

            if (originalTest!= null) {
                originalTest.setUid(Integer.parseInt(request.getParameter("uid")));
                originalTest.setTime(/* 同样根据前端传入的时间格式进行解析并设置 */
                        java.time.LocalDateTime.parse(request.getParameter("time")));
                originalTest.setTag(request.getParameter("tag"));
                originalTest.setResult(request.getParameter("result"));

                testzDao.updateTest(originalTest, "Tid = " + tid);
                return "{\"success\": true, \"message\": \"测试记录更新成功\"}";
            } else {
                return "{\"success\": false, \"message\": \"未找到ID为 " + tid + " 的测试记录，无法更新\"}";
            }
        } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"更新测试记录时出错\"}";
        }
    }

    // 删除测试记录，从报文中获取测试ID并删除，返回删除结果的报文
    public String deleteTest(HttpServletRequest request) {
        try {
            int tid = Integer.parseInt(request.getParameter("tid"));

            testzDao.deleteTest("Tid = " + tid);
            return "{\"success\": true, \"message\": \"测试记录删除成功\"}";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"删除测试记录时出错\"}";
        }
    }
}