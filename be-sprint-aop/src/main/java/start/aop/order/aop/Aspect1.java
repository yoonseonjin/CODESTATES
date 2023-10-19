package start.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class Aspect1 {  // 스프링 AOP 구현, Aspect : 여러 객체에 공통으로 적용되는 기능
    @Around("execution(* start.aop.order..*(..))")  // start.aop.order 패키지와 하위 패키지를 지정한 AspectJ 포인트컷 표현식
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log -> {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
