package cn.mentalhealth.controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import cn.mentalhealth.domain.ChatHistory;
import cn.mentalhealth.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
@RestController
@RequestMapping("/api/chathistories")
public class ChatHistoryController {
    private static final Logger logger = LoggerFactory.getLogger(ChatHistoryController.class);
    @Autowired
    private ChatHistoryService chatHistoryService;

    // 取消注释所有方法
    // 确保路径匹配前端的调用
    @GetMapping("/sessions/{uid}")
    public List<ChatHistory> getUserSessions(@PathVariable Integer uid) {
        return chatHistoryService.getUserChatSessions(uid);
    }

    @PostMapping("/sessions/new")
    public Integer createNewChatSession(@RequestBody Map<String, Object> request) {
        Integer uid = ((Number) request.get("uid")).intValue();
        String initialMessage = (String) request.get("initialMessage");
        return chatHistoryService.createNewChatSession(uid, initialMessage);
    }
    @GetMapping("/messages/{cid}")
    public List<ChatHistory> getChatMessages(@PathVariable Integer cid) {
        return chatHistoryService.getChatHistoryByCid(cid);
    }

    @PostMapping("/send")
    public ResponseEntity<?> sendMessage(@RequestBody Map<String, Object> request) {
        try {
            logger.info("收到发送消息请求");
            Integer cid = ((Number) request.get("cid")).intValue();
            Integer uid = ((Number) request.get("uid")).intValue();
            String content = (String) request.get("content");

            logger.debug("Message params - cid: {}, uid: {}, content: {}", cid, uid, content);

            ChatHistory response = chatHistoryService.sendMessage(cid, uid, content);
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            logger.error("处理消息时发生错误", e);
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("error", e.getMessage());
            errorResponse.put("details", e.getCause() != null ? e.getCause().getMessage() : "未知错误");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(errorResponse);
        }
    }

}