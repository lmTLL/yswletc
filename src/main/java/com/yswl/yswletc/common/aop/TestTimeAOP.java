package com.yswl.yswletc.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

/**
 * User: jang
 * Date: 2019/8/4
 * Time: 16:41
 * Description: No Description
 */
@Aspect
@Component
public class TestTimeAOP {
    /**
     * 环绕增强
     * @return
     */
    @Around("execution(* *.*.controller.*.*(..)) || @annotation(TestTime)")
    public Object isLogin(ProceedingJoinPoint joinPoint){
        long startTime = System.currentTimeMillis();

        long endTime = System.currentTimeMillis();
        float seconds = (endTime - startTime) / 1000F;
        System.out.println(Float.toString(seconds) + " seconds.");
        return null;
    }
}
