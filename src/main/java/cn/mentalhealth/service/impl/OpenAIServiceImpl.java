// src/main/java/cn/mentalhealth/service/impl/OpenAIServiceImpl.java

package cn.mentalhealth.service.impl;
import cn.mentalhealth.service.AIService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import com.fasterxml.jackson.databind.JsonNode;

import javax.annotation.PostConstruct;
import java.util.*;

@Service
public class OpenAIServiceImpl implements AIService {
    private static final Logger log = LoggerFactory.getLogger(OpenAIServiceImpl.class);
    private final RestTemplate restTemplate;

//    @Value("${openai.api-key}")
//    private String apiKey;
//
//    @Value("${openai.model}")
//    private String model;
//
//    @Value("${openai.api-url}")
//    private String apiUrl;

    private final String apiKey;
    private final String apiUrl;

    @Autowired
    public OpenAIServiceImpl(
            RestTemplate restTemplate,
            @Value("${openai.api-key}") String apiKey,
            @Value("${openai.api-url}") String apiUrl) {
        this.restTemplate = restTemplate;
        this.apiKey = apiKey;
        this.apiUrl = apiUrl;
        log.info("Initialized with API key: {}", apiKey);
    }

    @PostConstruct
    public void init() {
        log.info("System property openai.api-key: {}", System.getProperty("openai.api-key"));
        log.info("Environment variable OPENAI_API_KEY: {}", System.getenv("OPENAI_API_KEY"));
    }
    @Override
    public String getAIResponse(String userMessage) {
        try {
            log.info("准备发送 OpenAI 请求 - message: {}", userMessage);
            // 在这里也打印一下
            log.info("Using API key: {}", apiKey);
            String fullUrl = apiUrl + "/chat/completions";
            String actualKey = "sk-z7CzOyi3VfIQHv8xkEo3D6rb7jAIApdVtmUSmktU9SlIbBFa";

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(actualKey);

            Map<String, Object> messageUser = new HashMap<>();
            messageUser.put("role", "user");
            messageUser.put("content", userMessage);

            List<Map<String, Object>> messages = Collections.singletonList(messageUser);

            Map<String, Object> requestBody = new HashMap<>();
            requestBody.put("model", "claude-3-5-sonnet-20241022");
            requestBody.put("messages", messages);

            HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestBody, headers);

            ResponseEntity<Map> responseEntity = restTemplate.exchange(
                    fullUrl,
                    HttpMethod.POST,
                    entity,
                    Map.class
            );

            log.info("收到 API 响应 - Status: {}", responseEntity.getStatusCode());
            Map responseBody = responseEntity.getBody();

            if (responseBody == null) {
                throw new RuntimeException("API returned null response");
            }

            List<Map<String, Object>> choices = (List<Map<String, Object>>) responseBody.get("choices");
            if (choices != null && !choices.isEmpty()) {
                Map<String, Object> message = (Map<String, Object>) choices.get(0).get("message");
                if (message != null && message.containsKey("content")) {
                    return (String) message.get("content");
                }
            }

            throw new RuntimeException("Invalid response format: " + responseBody);

        } catch (Exception e) {
            log.error("OpenAI API call failed", e);
            return "抱歉，我暂时无法回答您的问题。请稍后再试。";
        }
    }
}