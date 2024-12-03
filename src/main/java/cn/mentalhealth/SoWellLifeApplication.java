package cn.mentalhealth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
@SpringBootApplication
//@ComponentScan("cn.mentalhealth"),都在同一个包下面，就没有必要添加
public class SoWellLifeApplication {
    public static void main(String[] args) {
        SpringApplication.run(SoWellLifeApplication.class, args);
    }

}
