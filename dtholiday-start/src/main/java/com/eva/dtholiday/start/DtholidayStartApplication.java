package com.eva.dtholiday.start;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication(scanBasePackages = "com.eva.**")
@MapperScan(basePackages = {"com.eva.dtholiday.**.mapper"})
@EnableAsync
@EnableScheduling
public class DtholidayStartApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(DtholidayStartApplication.class, args);

        String[] names = run.getBeanDefinitionNames();

        for (String name : names) {
            System.out.println(name);
        }
    }

}
