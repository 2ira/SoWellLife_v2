package cn.mentalhealth.controller;//@RestController
//@RequestMapping("/api/chathistories")
//public class ChatHistoryController {
//
//    @Autowired
//    private ChatHistoryService chatHistoryService;
//
//    @GetMapping
//    public List<ChatHistory> getAllChatHistories() {
//        return chatHistoryService.getAllChatHistories();
//    }
//
//    @GetMapping("/{id}")
//    public ChatHistory getChatHistoryById(@PathVariable Long id) {
//        return chatHistoryService.getChatHistoryById(id);
//    }
//
//    @GetMapping("/cid/{cid}")
//    public List<ChatHistory> getChatHistoriesByCid(@PathVariable Long cid) {
//        return chatHistoryService.getChatHistoriesByCid(cid);
//    }
//
//    @PostMapping
//    public ChatHistory saveChatHistory(@RequestBody ChatHistory chatHistory) {
//        return chatHistoryService.saveChatHistory(chatHistory);
//    }
//
//    @DeleteMapping("/{id}")
//    public void deleteChatHistory(@PathVariable Long id) {
//        chatHistoryService.deleteChatHistory(id);
//    }
//
//    @PostMapping("/send")
//    public ChatHistory sendMessage(@RequestBody ChatHistory chatHistory) {
//        return chatHistoryService.sendMessage(chatHistory);
//    }
//}