package cn.mentalhealth.controller;

import cn.mentalhealth.domain.Resource;
import cn.mentalhealth.service.ResourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/resources")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    private static final Logger logger = LoggerFactory.getLogger(ResourceController.class);

    // 获取所有资源数据
    @GetMapping("/all")
    public String getAllResources() {
        logger.info("开始获取所有资源数据");
        String result = resourceService.getAllResourcesAsJson();
        logger.info("获取到的所有资源数据: {}", result);
        return result;
    }

    // 根据资源ID获取资源数据
    @GetMapping("/{id}")
    public String getResourceById(@PathVariable("id") int id) {
        logger.info("开始获取ID为 {} 的资源数据", id);
        String result = resourceService.getResourceByIdAsJson(id);
        logger.info("获取到的资源数据: {}", result);
        return result;
    }

    // 根据资源名称获取相关资源
    @GetMapping("/by-name")
    public String getResourcesByName(HttpServletRequest request) {
        logger.info("开始根据资源名称获取资源数据");
        String result = resourceService.getResourcesByNameAsJson(request);
        logger.info("根据名称获取到的资源数据: {}", result);
        return result;
    }

    // 根据资源标签获取相关资源
    @GetMapping("/by-tag")
    public String getResourcesByTag(HttpServletRequest request) {
        logger.info("开始根据标签获取资源数据");
        String result = resourceService.getResourcesByTagAsJson(request);
        logger.info("根据标签获取到的资源数据: {}", result);
        return result;
    }

    // 添加新资源
    @PostMapping("/add")
    public String addResource(HttpServletRequest request) {
        logger.info("开始添加新的资源");
        String result = resourceService.addResource(request);
        logger.info("添加资源的结果: {}", result);
        return result;
    }

    // 更新资源
    @PutMapping("/update")
    public String updateResource(HttpServletRequest request) {
        logger.info("开始更新资源");
        String result = resourceService.updateResource(request);
        logger.info("更新资源的结果: {}", result);
        return result;
    }

    // 删除资源
    @DeleteMapping("/delete")
    public String deleteResource(HttpServletRequest request) {
        logger.info("开始删除资源");
        String result = resourceService.deleteResource(request);
        logger.info("删除资源的结果: {}", result);
        return result;
    }
}
