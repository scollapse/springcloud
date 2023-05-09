package cn.itcast.publisher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableRabbit
public class SpringAmqpTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void testSendMessage2SimplerQueue() {
        String queueName = "simple.queue";
        String message = "hello,world";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello,world ,msg_";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName,message + i);
            Thread.sleep(20);
        }
    }

    @Test
    public void testSendFanoutExchange() throws InterruptedException {
        // 交换机名称
        String exchangeName = "itcast.fanout";
        //消息
        String message = "hello,everyone";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName,"",message);

    }
}
