package cn.mentalhealth.service.impl;//@Service
//public class ChatHistoryServiceImpl implements ChatHistoryService {
//
//    @Autowired
//    private ChatHistoryDao chatHistoryDao;
//
//    @Autowired
//    private RestTemplate restTemplate;
//
//    @Value("${openai.api.key}")
//    private String apiKey;
//
//    @Override
//    public List<ChatHistory> getAllChatHistories() {
//        return chatHistoryDao.findAll();
//    }
//
//    @Override
//    public ChatHistory getChatHistoryById(Long id) {
//        return chatHistoryDao.findById(id);
//    }
//
//    @Override
//    public List<ChatHistory> getChatHistoriesByCid(Long cid) {
//        return chatHistoryDao.findByCid(cid);
//    }
//
//    @Override
//    public ChatHistory saveChatHistory(ChatHistory chatHistory) {
//        return chatHistoryDao.save(chatHistory);
//    }
//
//    @Override
//    public void deleteChatHistory(Long id) {
//        chatHistoryDao.deleteById(id);
//    }
//
//    @Override
//    public ChatHistory sendMessage(ChatHistory chatHistory) {
//        chatHistory.setHtime(LocalDateTime.now());
//
//        if ("user".equals(chatHistory.getRole())) {
//            // Send request to GPT and get response
//            String prompt = chatHistory.getContent();
//            HttpHeaders headers = new HttpHeaders();
//            headers.setContentType(MediaType.APPLICATION_JSON);
//            headers.set("Authorization", "Bearer " + apiKey);
//
//            Map<String, Object> requestBody = new HashMap<>();
//            requestBody.put("prompt", prompt);
//            requestBody.put("max_tokens", 150);
//
//            HttpEntity<Map<String, Object>> requestEntity = new HttpEntity<>(requestBody, headers);
//
//            ResponseEntity<Map> responseEntity = restTemplate.postForEntity(
//                    "https://api.openai.com/v1/engines/davinci-codex/completions",
//                    requestEntity,
//                    Map.class
//            );
//
//            String aiResponse = (String) responseEntity.getBody().get("choices.get(0).text");
//
//            ChatHistory aiChatHistory = new ChatHistory(
//                    null,
//                    chatHistory.getCid(),
//                    chatHistory.getUid(),
//                    LocalDateTime.now(),
//                    aiResponse,
//                    chatHistory.getHName(),
//                    "ai"
//            );
//            chatHistoryDao.save(aiChatHistory);
//        }
//
//        return chatHistoryDao.save(chatHistory);
//    }
//}