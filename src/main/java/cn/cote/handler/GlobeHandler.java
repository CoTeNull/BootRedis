package cn.cote.handler;

import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

//异常的全局捕获
@ControllerAdvice
public class GlobeHandler {
    @ExceptionHandler(value = UnauthorizedException.class)
    @ResponseBody
    public String login(Exception e){
        return e.getMessage();
    }
}
