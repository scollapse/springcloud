package cn.itcast.publisher;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
@EnableRabbit
public class SpringAmqpTests {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 基本消息发送测试
     */
    @Test
    public void testSendMessage2SimplerQueue() {
        String queueName = "simple.queue";
        String message = "hello,world";
        rabbitTemplate.convertAndSend(queueName,message);
    }

    /**
     * 工作队列消息模型测试
     * @throws InterruptedException
     */
    @Test
    public void testSendMessage2WorkQueue() throws InterruptedException {
        String queueName = "simple.queue";
        String message = "hello,world ,msg_";
        for (int i = 0; i < 50; i++) {
            rabbitTemplate.convertAndSend(queueName,message + i);
            Thread.sleep(20);
        }
    }

    /**
     * fanout消息模型测试
     * @throws InterruptedException
     */
    @Test
    public void testSendFanoutExchange() throws InterruptedException {
        // 交换机名称
        String exchangeName = "itcast.fanout";
        //消息
        String message = "hello,everyone";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName,"",message);

    }

    /**
     * direct消息模型测试
     * @throws InterruptedException
     */
    @Test
    public void testSendDirectExchange() throws InterruptedException {
        // 交换机名称
        String exchangeName = "itcast.direct";
        //消息
        String message = "hello,red";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName,"red",message);

    }

    /**
     * topic消息模型测试
     * @throws InterruptedException
     */
    @Test
    public void testSendTopicExchange() throws InterruptedException {
        // 交换机名称
        String exchangeName = "itcast.topic";
        //消息
        String message = "美国的新闻";
        // 发送消息
        rabbitTemplate.convertAndSend(exchangeName,"america.news",message);

    }

    /**
     * 消息转换器测试
     */
    @Test
    public void testSendObjectQueue(){
        // 交换机名称
        String exchangeName = "object.queue";
        //消息
        Map<String,Object> map = new HashMap<>();
        map.put("name","杰斯");
        map.put("age",21);
        rabbitTemplate.convertAndSend(exchangeName, map);
    }
}
