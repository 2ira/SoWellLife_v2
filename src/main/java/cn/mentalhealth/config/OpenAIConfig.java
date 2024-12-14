// src/main/java/cn/mentalhealth/config/OpenAIConfig.java

package cn.mentalhealth.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class OpenAIConfig {

    @Value("${openai.timeout}")
    private Integer timeout;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();

        // 配置消息转换器
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Arrays.asList(
                MediaType.APPLICATION_JSON,
                MediaType.APPLICATION_JSON_UTF8
        ));

        restTemplate.setMessageConverters(Collections.singletonList(converter));

        // 配置请求工厂
        HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
        factory.setConnectTimeout(timeout * 1000);
        factory.setReadTimeout(timeout * 1000);
        restTemplate.setRequestFactory(factory);

        return restTemplate;
    }
}