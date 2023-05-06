package cn.itcast.user.web;

import cn.itcast.user.config.PatternProperties;
import cn.itcast.user.pojo.User;
import cn.itcast.user.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
@RestController
@RequestMapping("/user")
//配置热更新的第一种方式
//@RefreshScope
public class UserController {

    @Autowired
    private UserService userService;

    //配置热更新的第一种方式
//    @Value("${pattern.dateformat}")
//    private String dateformat;

    @Autowired
    private PatternProperties properties;


    @GetMapping("prop")
    public PatternProperties prop(){
        return properties;
    }

    @GetMapping("now")
    public String now(){
//        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(dateformat));
        return LocalDateTime.now().format(DateTimeFormatter.ofPattern(properties.getDateformat()));
    }

    /**
     * 路径： /user/110
     *
     * @param id 用户id
     * @return 用户
     */
    @GetMapping("/{id}")
    public User queryById(@PathVariable("id") Long id,@RequestHeader(value = "Truth",required = false)String Truth) {
        System.out.println("Truth:"+Truth);
        return userService.queryById(id);
    }
}
