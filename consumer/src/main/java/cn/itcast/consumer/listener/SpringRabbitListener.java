package cn.itcast.consumer.listener;

import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringRabbitListener {

    /**
     * 基本消息模型消费
      * @param msg
     */
    @RabbitListener(queues = "simple.queue")
    public void listenSimpleQueue(String msg){
        System.out.println("消费者接受到simple.queue消息，msg:" + msg);
    }

    /**
     * 工作消息模型消费
     * @param msg
     * @throws InterruptedException
     */
    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到simple.queue消息，msg:【" + msg + "】");
        Thread.sleep(20);
    }

    @RabbitListener(queues = "simple.queue")
    public void listenWorkQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2接收到simple.queue消息，msg:【" + msg + "】");
        Thread.sleep(2000);
    }

    /**
     * 发布订阅消息模型
     */

    /**
     * 1.fanout消息模型 消费
     * @param msg
     * @throws InterruptedException
     */
    @RabbitListener(queues = "fanout.queue1")
    public void listenFanoutQueue1(String msg) throws InterruptedException {
        System.out.println("消费者1接收到fanout.queue1消息，msg:【" + msg + "】");
        Thread.sleep(2000);
    }

    @RabbitListener(queues = "fanout.queue2")
    public void listenFanoutQueue2(String msg) throws InterruptedException {
        System.out.println("消费者2接收到fanout.queue2消息，msg:【" + msg + "】");
        Thread.sleep(2000);
    }

    /**
     * direct消息模型 消费
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue1"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red","blue"}
    ))
    public void listenDirectQueue1(String msg){
        System.out.println("消费者1接收到direct.queue1消息，msg:【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "direct.queue2"),
            exchange = @Exchange(name = "itcast.direct", type = ExchangeTypes.DIRECT),
            key = {"red","yellow"}
    ))
    public void listenDirectQueue2(String msg){
        System.out.println("消费者1接收到direct.queue2消息，msg:【" + msg + "】");
    }

    /**
     * topic消息模型消费
     * @param msg
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue1"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "china.#"
    ))
    public void listenTopicQueue1(String msg){
        System.out.println("消费者1接收到topic.queue1消息，msg:【" + msg + "】");
    }

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = "topic.queue2"),
            exchange = @Exchange(name = "itcast.topic", type = ExchangeTypes.TOPIC),
            key = "#.news"
    ))
    public void listenTopicQueue2(String msg){
        System.out.println("消费者2接收到topic.queue2消息，msg:【" + msg + "】");
    }

    /**
     * 消息转换器消费
     * @param map
     */
    @RabbitListener(queues = "object.queue")
    public void listenObjectQueue(Map<String,Object> map) {
        System.out.println("接收到object.queue的消息 msg:【" + map + "】");
    }
}
