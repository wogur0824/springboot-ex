package world.worldspring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect // AOP는 이 어노테이션을 적어줘야 AOP로 사용할 수 있다.
@Component //이 코드를 스프링 빈으로 등록할려면 이 어노테이션을 해도 된다.
public class TimeTraceAop {

    @Around("execution(* world.worldspring..*())") // 적용해줄 타켓을 정하는 것
    public Object excut(ProceedingJoinPoint joinPoint) throws  Throwable {
        long start = System.currentTimeMillis();
        System.out.println("Start: " + joinPoint.toString());
        try{
            return joinPoint.proceed();
        } finally {
            long finish = System.currentTimeMillis();
            long timeMs = finish - start;
            System.out.println("END: " + joinPoint.toString() + " " + timeMs + "ms");
        }
    }
}
