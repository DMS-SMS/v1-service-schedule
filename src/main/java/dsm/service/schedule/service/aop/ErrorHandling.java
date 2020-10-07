package dsm.service.schedule.service.aop;

import dsm.service.schedule.domain.exception.BusinessException;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import dsm.service.schedule.proto.GetScheduleResponse;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ErrorHandling {
    @Pointcut("execution(* dsm.service.schedule.service.ScheduleServiceImpl.getScheduleService(..))")
    private void getSchedulePointCut() {}

    @Pointcut("execution(* dsm.service.schedule.service.ScheduleServiceImpl.updateScheduleService(..)) || " +
              "execution(* dsm.service.schedule.service.ScheduleServiceImpl.deleteScheduleService(..)) || " +
              "execution(* dsm.service.schedule.service.ScheduleServiceImpl.createScheduleService(..))")
    private void defaultSchedulePointCut() {}

    @Around("getSchedulePointCut()")
    public Object getScheduleErrorHandling(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (BusinessException businessException) {
            return GetScheduleResponse.newBuilder()
                    .setStatus(businessException.getStatusCode())
                    .setCode(businessException.getErrorCode())
                    .setMsg(businessException.getMessage())
                    .build();
        }
    }

    @Around("defaultSchedulePointCut()")
    public Object defaultErrorHandling(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return pjp.proceed();
        } catch (BusinessException businessException) {
            return DefaultScheduleResponse.newBuilder()
                    .setStatus(businessException.getStatusCode())
                    .setCode(businessException.getErrorCode())
                    .setMsg(businessException.getMessage())
                    .build();
        }
    }
}
