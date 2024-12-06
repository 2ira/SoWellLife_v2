package cn.mentalhealth.service.impl;

import cn.mentalhealth.dao.ChatHistoryDao;
import cn.mentalhealth.dao.impl.ChatHistoryDaoImpl;
import cn.mentalhealth.domain.ChatHistory;
import cn.mentalhealth.service.ChatHistoryService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChatHistoryServiceImpl implements ChatHistoryService {

    private ChatHistoryDao chatHistoryDao = new ChatHistoryDaoImpl();

    @Override
    public List<ChatHistory> getUserChatSessions(Integer uid) {
        List<ChatHistory> allUserChats = chatHistoryDao.getChatHistoryByUid(uid);
        // 按照Cid分组，只返回每个会话的最后一条消息
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
        // 生成新的会话ID（这里示例使用时间戳，实际应用中可能需要更复杂的逻辑）
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
        // 保存用户消息
        ChatHistory userMessage = new ChatHistory();
        userMessage.setCid(cid);
        userMessage.setUid(uid);
        userMessage.setHtime(LocalDateTime.now());
        userMessage.setContent(message);
        userMessage.setHName(getChatHistoryByCid(cid).get(0).getHName()); // 使用已有的会话名称
        userMessage.setRole("user");

        chatHistoryDao.insertChatHistory(userMessage);

        // 这里应该调用AI服务获取回复
        // 示例中使用简单的回复，实际应用中需要集成具体的AI服务
        ChatHistory aiResponse = new ChatHistory();
        aiResponse.setCid(cid);
        aiResponse.setUid(uid);
        aiResponse.setHtime(LocalDateTime.now());
        aiResponse.setContent("收到您的消息：" + message);  // 实际中应该是AI的响应
        aiResponse.setHName(userMessage.getHName());
        aiResponse.setRole("ai");

        chatHistoryDao.insertChatHistory(aiResponse);

        return aiResponse;
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