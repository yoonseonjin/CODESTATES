package start.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Aspect2 {  // 포인트컷 분리, 포인트컷 : 조인 포인트 중에서 어드바이스가 적용될 위치를 선별하는 기능
    @Pointcut("execution(* start.aop.order..*(..))")
    private void allOrder() {}

    @Around("allOrder()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log -> {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }
}
