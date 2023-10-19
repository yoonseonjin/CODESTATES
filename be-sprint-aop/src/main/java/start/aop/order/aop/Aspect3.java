package start.aop.order.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Slf4j
@Aspect
public class Aspect3 {  // 어드바이스 추가, 로그 출력 기능에 추가로 트랜잭션이 동작하는 것처럼 로그를 남기는 기능 추가
    @Pointcut("execution(* start.app.order..*(..))")
    private void allOrder() {}

    @Pointcut("execution(* *..*Service.*(..))")
    private void allService() {}

    @Around("allOrder()")
    public Object logging(ProceedingJoinPoint joinPoint) throws Throwable {
        log.info("log -> {}", joinPoint.getSignature());
        return joinPoint.proceed();
    }

    @Around("allOrder() && allService()")
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
            log.info("트랜잭션 롤백 -> {}", joinPoint.getSignature());
            throw e;
        }
        finally {
            // (Optional)예외 발생 여부와 상관없이 항상 실행
            log.info("리소스 릴리즈 -> {}", joinPoint.getSignature());
        }
    }
}
