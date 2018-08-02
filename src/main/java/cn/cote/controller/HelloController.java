package cn.cote.controller;

import cn.cote.myutils.RedisOperator;
import cn.cote.myutils.WebData;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.TimeUnit;


@RestController
@CrossOrigin
public class HelloController {


    @GetMapping("/hello")
    public String sayHello(){
        return "begin Hello!";
    }

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public WebData login(@RequestParam("userName") String userName,@RequestParam("passWord") String passWord){
        System.out.println(userName+passWord);
        WebData webData = new WebData();

        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userName,passWord);
        try {
            subject.login(token);
            String sessionId = (String) subject.getSession().getId();

//            subject.getSession().setTimeout(3600000);
            webData.setMessage("success");
            webData.setData(sessionId);
            webData.setCode(1);
        } catch (AuthenticationException e) {
            webData.setMessage(e.getMessage());
            webData.setCode(0);
        }
        return webData;
    }

    @RequiresRoles("admin")
    @RequestMapping(value = "/test" , method = RequestMethod.POST)
   public String login2(){
//        Subject subject = SecurityUtils.getSubject();
//        if(subject.hasRole("admin")){
//            return "yes";
//        }else{
//            return "no";
//        }
        return "111";
    }

    @Autowired
    private RedisOperator redisOperator;

//    redis
    @GetMapping("/redis")
    public String redis(){
        redisOperator.set("userName","admin");
        return redisOperator.get("userName");
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/redis2")
    public String redis2(){
        redisTemplate.opsForValue().set("key6","value6");
        return redisTemplate.opsForValue().get("key6");
    }
}
