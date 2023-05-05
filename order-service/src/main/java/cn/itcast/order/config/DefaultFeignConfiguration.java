package cn.itcast.order.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;

/**
 * feign 的配置类 实现
 */
public class DefaultFeignConfiguration {
    @Bean
    public Logger.Level LogLevel(){
        return Logger.Level.BASIC;
    }
}
