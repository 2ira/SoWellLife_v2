package cn.mentalhealth.service.impl;

import cn.mentalhealth.dao.ChatHistoryDao;
import cn.mentalhealth.dao.impl.ChatHistoryDaoImpl;
import cn.mentalhealth.domain.ChatHistory;
import cn.mentalhealth.service.AIService;
import cn.mentalhealth.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    private ChatHistoryDao chatHistoryDao = new ChatHistoryDaoImpl();

    @Autowired
    private AIService aiService;

    @Override
    public List<ChatHistory> getUserChatSessions(Integer uid) {
        List<ChatHistory> allUserChats = chatHistoryDao.getChatHistoryByUid(uid);
        return allUserChats.stream()
                .collect(Collectors.groupingBy(ChatHistory::getCid))
                .values()
                .stream()
                .map(chatList -> chatList.get(0))
                .collect(Collectors.toList());
    }

    @Override
    public List<ChatHistory> getChatHistoryByCid(Integer cid) {
        return chatHistoryDao.getChatHistoryByCid(cid);
    }

    @Override
    public Integer createNewChatSession(Integer uid, String initialMessage) {
        // 生成新的会话ID
        Integer newCid = (int) (System.currentTimeMillis() / 1000);

        // 创建初始消息记录
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setCid(newCid);
        chatHistory.setUid(uid);
        chatHistory.setHtime(LocalDateTime.now());
        chatHistory.setContent(initialMessage);
        chatHistory.setHName(initialMessage.length() > 6 ?
                initialMessage.substring(0, 6) : initialMessage);
        chatHistory.setRole("user");

        // 保存到数据库
        chatHistoryDao.insertChatHistory(chatHistory);

        // 创建AI的自动回复
        ChatHistory aiResponse = new ChatHistory();
        aiResponse.setCid(newCid);
        aiResponse.setUid(uid);
        aiResponse.setHtime(LocalDateTime.now());
        aiResponse.setContent("您好！我是心理AI助手沙包，很高兴为您服务。");
        aiResponse.setHName(chatHistory.getHName());
        aiResponse.setRole("ai");

        chatHistoryDao.insertChatHistory(aiResponse);

        return newCid;
    }

    @Override
    public ChatHistory sendMessage(Integer cid, Integer uid, String message) {
        try {
            List<ChatHistory> chatHistory = getChatHistoryByCid(cid);

            // 创建用户消息
            ChatHistory userMessage = new ChatHistory();
            userMessage.setCid(cid);
            userMessage.setUid(uid);
            userMessage.setHtime(LocalDateTime.now());
            userMessage.setContent(message);

            String chatName;
            if (chatHistory.isEmpty()) {
                chatName = message.length() > 6 ? message.substring(0, 6) : message;
            } else {
                chatName = chatHistory.get(0).getHName();
            }
            userMessage.setHName(chatName);
            userMessage.setRole("user");

            chatHistoryDao.insertChatHistory(userMessage);

            // 获取 AI 响应
            String aiReply = aiService.getAIResponse(message);

            // 创建 AI 响应
            ChatHistory aiResponse = new ChatHistory();
            aiResponse.setCid(cid);
            aiResponse.setUid(uid);
            aiResponse.setHtime(LocalDateTime.now());
            aiResponse.setContent(aiReply);
            aiResponse.setHName(chatName);
            aiResponse.setRole("ai");

            chatHistoryDao.insertChatHistory(aiResponse);

            return aiResponse;
        } catch (Exception e) {
            throw new RuntimeException("发送消息失败: " + e.getMessage());
        }
    }

    @Override
    public void deleteChatSession(Integer cid) {
        chatHistoryDao.deleteChatHistory("Cid = " + cid);
    }

    @Override
    public void updateChatSessionName(Integer cid, String newName) {
        List<ChatHistory> chatHistories = chatHistoryDao.getChatHistoryByCid(cid);
        for (ChatHistory chat : chatHistories) {
            chat.setHName(newName);
            chatHistoryDao.updateChatHistory(chat, "Hid = " + chat.getHid());
        }
    }
}