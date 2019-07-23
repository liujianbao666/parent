package com.blueview.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;


/**
 * @ClassName RabbitConfig
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/7/19
 * @Version V1.0
 **/
/*@Configuration*/
public class RabbitConfig {
    @Bean
    public Queue helloQueue(){
        return new Queue("hello");
    }
}
