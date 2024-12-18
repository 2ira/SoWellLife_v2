package cn.mentalhealth;

import cn.mentalhealth.service.impl.OpenAIServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.PropertySource;

import java.util.Arrays;

@SpringBootApplication
//@ComponentScan("cn.mentalhealth"),都在同一个包下面，就没有必要添加
public class SoWellLifeApplication {
    public static void main(String[] args) {
//        SpringApplication.run(SoWellLifeApplication.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(SoWellLifeApplication.class, args);

        // 打印所有配置源
        ConfigurableEnvironment env = context.getEnvironment();
        Logger log = LoggerFactory.getLogger(OpenAIServiceImpl.class);
        log.info("Active profiles: {}", Arrays.toString(env.getActiveProfiles()));
        log.info("Property sources: ");
        for (PropertySource<?> propertySource : ((AbstractEnvironment) env).getPropertySources()) {
            log.info("  {}", propertySource.getName());
        }
    }
}
