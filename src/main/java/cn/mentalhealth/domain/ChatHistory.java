package cn.mentalhealth.domain;//// 表示一条聊天记录,包含了会话id、用户id、时间、内容等字段,以及一些基本的getter和setter方法。
//import java.time.LocalDateTime;
//
//public class ChatHistory {
//    private Long Hid;               // 对话ID，唯一
//    private Long Cid;               // 会话ID，不唯一
//    private String Uid;             // 用户ID
//    private LocalDateTime Htime;    // 对话时间
//    private String Content;         // 对话内容
//    private String HName;           // 会话名称
//    private String Role;            // 角色，ai还是用户
//
//    // 构造函数
//    public ChatHistory(Long Hid, Long Cid, String Uid, LocalDateTime Htime, String Content, String HName, String Role) {
//        this.Hid = Hid;
//        this.Cid = Cid;
//        this.Uid = Uid;
//        this.Htime = Htime;
//        this.Content = Content;
//        this.HName = HName;
//        this.Role = Role;
//    }
//
//
//    // Getters and Setters
//    public Long getHid() {
//        return Hid;
//    }
//
//    public void setHid(Long Hid) {
//        this.Hid = Hid;
//    }
//
//    public Long getCid() {
//        return Cid;
//    }
//
//    public void setCid(Long Cid) {
//        this.Cid = Cid;
//    }
//
//    public String getUid() {
//        return Uid;
//    }
//
//    public void setUid(String Uid) {
//        this.Uid = Uid;
//    }
//
//    public LocalDateTime getHtime() {
//        return Htime;
//    }
//
//    public void setHtime(LocalDateTime Htime) {
//        this.Htime = Htime;
//    }
//
//    public String getContent() {
//        return Content;
//    }
//
//    public void setContent(String Content) {
//        this.Content = Content;
//    }
//
//    public String getHName() {
//        return HName;
//    }
//
//    public void setHName(String HName) {
//        this.HName = HName;
//    }
//
//    public String getRole() {
//        return Role;
//    }
//
//    public void setRole(String Role) {
//        this.Role = Role;
//    }
//
//    @Override
//    public String toString() {
//        return "ChatHistory{" +
//                "Hid=" + Hid +
//                ", Cid=" + Cid +
//                ", Uid='" + Uid + '\'' +
//                ", Htime=" + Htime +
//                ", Content='" + Content + '\'' +
//                ", HName='" + HName + '\'' +
//                ", Role='" + Role + '\'' +
//                '}';
//    }
//
//    public static void main(String[] args) {
//        // 创建一个 ChatHistory 对象
//        LocalDateTime now = LocalDateTime.now();
//        ChatHistory chat = new ChatHistory(1L, 100L, "user123", now, "Hello, AI!", "General Chat", "user");
//
//        // 打印 ChatHistory 对象
//        System.out.println(chat);
//
//        // 修改对话内容
//        chat.setContent("How are you, AI?");
//
//        // 打印修改后的 ChatHistory 对象
//        System.out.println(chat);
//    }
//}