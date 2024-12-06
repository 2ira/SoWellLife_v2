package cn.mentalhealth.service;

import cn.mentalhealth.domain.ChatHistory;
import java.util.List;

public interface ChatHistoryService {
    // 获取某个用户的所有会话列表
    List<ChatHistory> getUserChatSessions(Integer uid);

    // 获取特定会话的聊天记录
    List<ChatHistory> getChatHistoryByCid(Integer cid);

    // 创建新的会话
    Integer createNewChatSession(Integer uid, String initialMessage);

    // 发送消息并获取AI响应
    ChatHistory sendMessage(Integer cid, Integer uid, String message);

    // 删除会话
    void deleteChatSession(Integer cid);

    // 修改会话名称
    void updateChatSessionName(Integer cid, String newName);
}