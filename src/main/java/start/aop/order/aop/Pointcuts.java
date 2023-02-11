package start.aop.order.aop;

import org.aspectj.lang.annotation.Pointcut;

public class Pointcuts {    // 포인터컷 참조, 포인트컷을 공용으로 사용하기 위해 외부 클래스로 외부에 모아둔다
    @Pointcut("execution(* start.app.order..*(..))")
    public void allOrder() {}

    @Pointcut("execution(* *..*Service.*(..))")
    public void allService(){}

    @Pointcut("allOrder() && allService()")
    public void orderAndService() {}
}
