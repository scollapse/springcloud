package cn.itcast.order.service;

import cn.itcast.feign.clients.UserClient;
import cn.itcast.feign.pojo.User;
import cn.itcast.order.mapper.OrderMapper;
import cn.itcast.order.pojo.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private UserClient client;

    /**
     * feign方式发起调用
     * @param orderId
     * @return
     */

    public Order queryOrderById(Long orderId) {
        // 1.查询订单
        Order order = orderMapper.findById(orderId);
        //feign方式发起调用
        User user = client.findById(order.getUserId());
        order.setUser(user);
        // 4.返回
        return order;
    }

    /**
     * 利用restTemplate发起http请求
     */

//    public Order queryOrderById(Long orderId) {
//        // 1.查询订单
//        Order order = orderMapper.findById(orderId);
//        //利用restTemplate发起http请求
//        String url = "http://userservice/user/" + order.getUserId();
//        User forObject = restTemplate.getForObject(url, User.class);
//        order.setUser(forObject);
//        // 4.返回
//        return order;
//    }
}
