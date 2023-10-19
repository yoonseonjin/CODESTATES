package start.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Slf4j
@Aspect
public class Aspect4 {  // 포인트컷 참조, 포인트컷을 공용으로 사용하기 위해 외부 클래스로 외부에 모아둔다 -> Pointcuts
    @Around("start.aop.order.aop.Pointcuts.allOrder()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log -> {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("start.aop.order.aop.Pointcuts.orderAndService()")
    public Object doTransaction(ProceedingJoinPoint joinPoint) throws Throwable {

        try {
            // 예외가 발생할 가능성이 있는 코드를 삽입
            log.info("트랜잭션 시작 -> {}", joinPoint.getSignature());
            Object result = joinPoint.proceed();
            log.info("트랜잭션 커밋 -> {}", joinPoint.getSignature());
            return result;
        }
        catch (Exception e) {
            // 예외 발생 시 실행할 코드
            log.info("트랜잭션 커밋 -> {}", joinPoint.getSignature());
            throw e;
        }
        finally {
            // (Optional)예외 발생 여부와 상관없이 항상 실행
            log.info("리소스 릴리즈 -> {}", joinPoint.getSignature());
        }
    }
}
