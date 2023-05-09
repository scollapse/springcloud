package cn.itcast.consumer.listener;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class SpringRabbitListener {

//    @RabbitListener(queues = "simple.queue")
//    public void listenSimpleQueue(String msg){
//        System.out.println("消费者接受到simple.queue消息，msg:" + msg);
//    }

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
}
