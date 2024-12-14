package cn.mentalhealth.service;

import cn.mentalhealth.domain.ChatHistory;
import java.util.List;

public interface ChatHistoryService {
    List<ChatHistory> getUserChatSessions(Integer uid);
    List<ChatHistory> getChatHistoryByCid(Integer cid);
    Integer createNewChatSession(Integer uid, String initialMessage);
    ChatHistory sendMessage(Integer cid, Integer uid, String message);
    void deleteChatSession(Integer cid);
    void updateChatSessionName(Integer cid, String newName);
}