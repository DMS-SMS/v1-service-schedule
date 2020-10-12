package dsm.service.schedule.service.aop;

import dsm.service.schedule.domain.exception.BusinessException;
import dsm.service.schedule.infra.openTracing.JaegerHandler;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import dsm.service.schedule.proto.GetScheduleResponse;
import dsm.service.schedule.proto.GetTimeTableResponse;
import dsm.service.schedule.service.aop.annotation.Tracing;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@Aspect
@AllArgsConstructor
public class AspectService {
    @Pointcut("execution(* dsm.service.schedule.service.ScheduleServiceImpl.getScheduleService(..))")
    private void getSchedulePointCut() {}

    @Pointcut("execution(* dsm.service.schedule.service.ScheduleServiceImpl.getTimeTableService(..))")
    private void getTimeTablePointCut() {}

    @Pointcut("execution(* dsm.service.schedule.service.ScheduleServiceImpl.updateScheduleService(..)) || " +
              "execution(* dsm.service.schedule.service.ScheduleServiceImpl.deleteScheduleService(..)) || " +
              "execution(* dsm.service.schedule.service.ScheduleServiceImpl.createScheduleService(..))")
    private void defaultSchedulePointCut() {}

    private final JaegerHandler jaegerHandler;

    @Around("getSchedulePointCut()")
    public Object getScheduleErrorHandling(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return jaegerHandler.serviceTracing(pjp);
        } catch (BusinessException businessException) {
            return GetScheduleResponse.newBuilder()
                    .setStatus(businessException.getStatusCode())
                    .setCode(businessException.getErrorCode())
                    .setMsg(businessException.getMessage())
                    .build();
        }
    }

    @Around("getTimeTablePointCut()")
    public Object getTimeTableErrorHandling(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return jaegerHandler.serviceTracing(pjp);
        } catch (BusinessException businessException) {
            return GetTimeTableResponse.newBuilder()
                    .setStatus(businessException.getStatusCode())
                    .setCode(businessException.getErrorCode())
                    .setMsg(businessException.getMessage())
                    .build();
        }
    }

    @Around("defaultSchedulePointCut()")
    public Object defaultErrorHandling(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return jaegerHandler.serviceTracing(pjp);
        } catch (BusinessException businessException) {
            return DefaultScheduleResponse.newBuilder()
                    .setStatus(businessException.getStatusCode())
                    .setCode(businessException.getErrorCode())
                    .setMsg(businessException.getMessage())
                    .build();
        }
    }

    @Around("@annotation(dsm.service.schedule.service.aop.annotation.Tracing) && @annotation(tracing)")
    public Object extensionTracing(ProceedingJoinPoint pjp, Tracing tracing) throws Throwable {
        return jaegerHandler.extensionTracing(pjp, tracing.serviceName());
    }
}
