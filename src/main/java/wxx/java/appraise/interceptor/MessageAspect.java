package wxx.java.appraise.interceptor;

import com.alibaba.fastjson.JSONObject;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;


@Configuration
@Aspect
public class MessageAspect {

    private static final Logger logger = LoggerFactory.getLogger(MessageAspect.class);

    @Pointcut("execution(public * wxx.rent.controller.*.*(..))")
    public void pointCutMethod() {
    }

    @Around("pointCutMethod()")
    public Object doAround(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        String controller = proceedingJoinPoint.getTarget().getClass().getSimpleName();
        String method = proceedingJoinPoint.getSignature().getName();
        String args = Arrays.toString(proceedingJoinPoint.getArgs());
        logger.debug("<{},{}>:请求参数:{}", controller, method, args);

        Object response = proceedingJoinPoint.proceed();

        logger.debug("响应参数:{}", JSONObject.toJSONString(response));

        return response;
    }

    //@AfterThrowing: 异常通知
    @AfterThrowing(value = "execution (* *.*(..))", throwing = "e")
    public void afterReturningMethod(JoinPoint proceedingJoinPoint, Exception e) {
        String methodName = proceedingJoinPoint.getSignature().getName();
        logger.debug("The method name:" + methodName + " ends and result=" + e);
    }
}
