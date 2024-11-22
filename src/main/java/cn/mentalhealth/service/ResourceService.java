package cn.mentalhealth.service;

import cn.mentalhealth.dao.ResourceDao;
import cn.mentalhealth.dao.impl.ResourceDaoImpl;
import cn.mentalhealth.domain.Resource;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class ResourceService {

    private ResourceDao resourceDao = new ResourceDaoImpl();
    private ObjectMapper objectMapper = new ObjectMapper();

    // 初始化ObjectMapper，设置序列化时的一些特性，比如日期格式等（可根据实际需求调整）
    public ResourceService() {
        objectMapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        objectMapper.setDateFormat(new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
    }

    // 获取所有资源，并以JSON报文格式返回
    public String getAllResourcesAsJson() {
        List<Resource> resources = resourceDao.getAllResources();
        try {
            return objectMapper.writeValueAsString(resources);
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"获取所有资源时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 根据资源ID获取特定资源，并以JSON报文格式返回
    public String getResourceByIdAsJson(int rid) {
        Resource resource = resourceDao.getResourceById(rid);
        try {
            if (resource!= null) {
                return objectMapper.writeValueAsString(resource);
            } else {
                return "{\"error\": \"未找到ID为 " + rid + " 的资源\", \"details\": \"请检查输入的资源ID是否正确\"}";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "{\"error\": \"根据ID获取资源时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 根据资源名称获取相关资源，并以JSON报文格式返回
    public String getResourcesByNameAsJson(HttpServletRequest request) {
        try {
            String rname = request.getParameter("rname");

            List<Resource> resources = resourceDao.getResourcesByName(rname);
            return objectMapper.writeValueAsString(resources);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            return "{\"error\": \"根据资源名称获取资源时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 根据标签获取相关资源，并以JSON报文格式返回
    public String getResourcesByTagAsJson(HttpServletRequest request) {
        try {
            String rtag = request.getParameter("rtag");

            List<Resource> resources = resourceDao.getResourcesByTag(rtag);
            return objectMapper.writeValueAsString(resources);
        } catch (NumberFormatException | IOException e) {
            e.printStackTrace();
            return "{\"error\": \"根据标签获取资源时出错\", \"details\": \"" + e.getMessage() + "\"}";
        }
    }

    // 添加新资源，从报文中获取资源信息并添加，返回添加结果的报文
    public String addResource(HttpServletRequest request) {
        try {
            Resource resource = new Resource();
            resource.setRid(Integer.parseInt(request.getParameter("rid")));
            resource.setRname(request.getParameter("rname"));
            resource.setRtag(request.getParameter("rtag"));
            resource.setRurl(request.getParameter("content"));
            resource.setRTime(/* 根据前端传入的时间格式进行解析并设置，这里假设前端传入的是没有设置格式的字符串，你可根据实际情况调整 */
                    String.valueOf(java.time.LocalDateTime.parse(request.getParameter("createTime"))));

            resourceDao.insertResource(resource);
            return "{\"success\": true, \"message\": \"资源添加成功\"}";
        } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"添加资源时出错\"}";
        }
    }

    // 更新资源，从报文中获取更新信息并更新，返回更新结果的报文
    public String updateResource(HttpServletRequest request) {
        try {
            int rid = Integer.parseInt(request.getParameter("rid"));
            Resource originalResource = resourceDao.getResourceById(rid);

            if (originalResource!= null) {
                originalResource.setRname(request.getParameter("rname"));
                originalResource.setRtag(request.getParameter("rtag"));
                originalResource.setRurl(request.getParameter("content"));
                originalResource.setRTime(/* 同样根据前端传入的时间格式进行解析并设置 */
                        String.valueOf(java.time.LocalDateTime.parse(request.getParameter("createTime"))));

                resourceDao.updateResource(originalResource, "Rid = " + rid);
                return "{\"success\": true, \"message\": \"资源更新成功\"}";
            } else {
                return "{\"success\": false, \"message\": \"未找到ID为 " + rid + " 的资源，无法更新\"}";
            }
        } catch (NumberFormatException | java.time.format.DateTimeParseException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"更新资源时出错\"}";
        }
    }

    // 删除资源，从报文中获取资源ID并删除，返回删除结果的报文
    public String deleteResource(HttpServletRequest request) {
        try {
            int rid = Integer.parseInt(request.getParameter("rid"));

            resourceDao.deleteResource("Rid = " + rid);
            return "{\"success\": true, \"message\": \"资源删除成功\"}";
        } catch (NumberFormatException e) {
            e.printStackTrace();
            return "{\"success\": false, \"message\": \"删除资源时出错\"}";
        }
    }
}