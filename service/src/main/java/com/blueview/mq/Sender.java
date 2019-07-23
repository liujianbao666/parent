package com.blueview.mq;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @ClassName Sender
 * @Description: TODO
 * @Author Administrator
 * @Date 2019/7/19
 * @Version V1.0
 **/
@Component
public class Sender {
    @Autowired
    private AmqpTemplate rabbitTemplate;
    public void send(){
        String context = "hello" + new Date();
        System.out.println("Sender:"+context);
        rabbitTemplate.convertAndSend("hello",context);
    }
}
