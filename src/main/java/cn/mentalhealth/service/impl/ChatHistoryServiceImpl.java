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

        // 1. 首先插入系统指令
        ChatHistory systemPrompt = new ChatHistory();
        systemPrompt.setCid(newCid);
        systemPrompt.setUid(uid);
        systemPrompt.setHtime(LocalDateTime.now());
        systemPrompt.setContent("你是一个AI心理健康助手，名字叫\"沙包\"，你需要尽量以温柔的语气与用户对话，因为他们大多数是来寻求心理帮助的，你要表现得尽量温和善良");
        systemPrompt.setHName("新对话");
        systemPrompt.setRole("system");  // 使用特殊的role标记系统指令

        chatHistoryDao.insertChatHistory(systemPrompt);

        // 2. 插入AI的欢迎语
        ChatHistory welcomeMessage = new ChatHistory();
        welcomeMessage.setCid(newCid);
        welcomeMessage.setUid(uid);
        welcomeMessage.setHtime(LocalDateTime.now());
        welcomeMessage.setContent("你好！我是AI心理助手\"沙包\"，很高兴和你一起聊天😊");
        welcomeMessage.setHName("新对话");
        welcomeMessage.setRole("ai");

        chatHistoryDao.insertChatHistory(welcomeMessage);

        // 3. 如果有初始消息，则创建用户的消息记录
        if (initialMessage != null && !initialMessage.trim().isEmpty()) {
            ChatHistory userMessage = new ChatHistory();
            userMessage.setCid(newCid);
            userMessage.setUid(uid);
            userMessage.setHtime(LocalDateTime.now());
            userMessage.setContent(initialMessage);
            userMessage.setHName(initialMessage.length() > 6 ?
                    initialMessage.substring(0, 6) : initialMessage);
            userMessage.setRole("user");

            chatHistoryDao.insertChatHistory(userMessage);
        }

        return newCid;
    }

    @Override
    public ChatHistory sendMessage(Integer cid, Integer uid, String message, Boolean shouldSave) {
        try {
            // 获取AI响应
            String aiReply = aiService.getAIResponse(cid, message);

            // 如果是错误消息，直接返回但不保存
            if (aiReply.equals("沙包出了点小问题，后面再试试吧~😀")) {
                ChatHistory errorResponse = new ChatHistory();
                errorResponse.setContent(aiReply);
                errorResponse.setRole("ai");
                return errorResponse;
            }

            // 只有在需要保存且不是错误消息时才保存到数据库
            if (shouldSave) {
                // 保存用户消息
                ChatHistory userMessage = new ChatHistory();
                userMessage.setCid(cid);
                userMessage.setUid(uid);
                userMessage.setHtime(LocalDateTime.now());
                userMessage.setContent(message);
                userMessage.setRole("user");

                ChatHistory existingChat = chatHistoryDao.getLastMessageByCid(cid);
                String chatName = existingChat != null ? existingChat.getHName() :
                        (message.length() > 6 ? message.substring(0, 6) : message);
                userMessage.setHName(chatName);

                chatHistoryDao.insertChatHistory(userMessage);

                // 保存AI响应
                ChatHistory aiResponse = new ChatHistory();
                aiResponse.setCid(cid);
                aiResponse.setUid(uid);
                aiResponse.setHtime(LocalDateTime.now());
                aiResponse.setContent(aiReply);
                aiResponse.setHName(chatName);
                aiResponse.setRole("ai");

                chatHistoryDao.insertChatHistory(aiResponse);
                return aiResponse;
            } else {
                // 不保存到数据库，只返回响应
                ChatHistory aiResponse = new ChatHistory();
                aiResponse.setContent(aiReply);
                aiResponse.setRole("ai");
                return aiResponse;
            }
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