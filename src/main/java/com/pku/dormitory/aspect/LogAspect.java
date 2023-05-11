package com.pku.dormitory.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 日志切面
 *
 * @author Yonmin
 * @date 2023/5/11 17:02
 */
@Aspect
@Component
public class LogAspect {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 定义切面
     */
    @Pointcut("execution(* com.pku.dormitory.controller.*.*(..))")
    public void log() {
    }

    /**
     * 在切面之前执行
     *
     * @param joinPoint 切面对象
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest request = null;
        String url = null;
        String ip = null;
        if (attributes != null) {
            request = attributes.getRequest();
            url = request.getRequestURL().toString();
            ip = request.getRemoteAddr();
        }
        String classMethod = joinPoint.getSignature().getDeclaringTypeName() + '.' + joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        RequestLog requestLog = new RequestLog(url, ip, classMethod, args);
        logger.info("Request: {}", requestLog);
    }

    /**
     * 在切面之后执行
     */
    @After("log()")
    public void doAfter() {
//        logger.info("---------After----------");
    }

    /**
     * 在切面之后执行，获取返回值
     *
     * @param result
     */
    @AfterReturning(returning = "result", pointcut = "log()")
    public void doAfterReturn(Object result) {
        logger.info("Result: {}", result.toString());
    }
}

class RequestLog {
    private final String url;
    private final String ip;
    private final String classMethod;
    private final Object[] args;

    public RequestLog(String url, String ip, String classMethod, Object[] args) {
        this.url = url;
        this.ip = ip;
        this.classMethod = classMethod;
        this.args = args;
    }

    @Override
    public String toString() {
        return "RequestLog{" +
                "url='" + url + '\'' +
                ", ip='" + ip + '\'' +
                ", classMethod='" + classMethod + '\'' +
                ", args=" + Arrays.toString(args) +
                '}';
    }
}
