package dsm.service.schedule.infra.aop;

import dsm.service.schedule.core.domain.exception.BusinessException;
import dsm.service.schedule.infra.aop.annotation.Tracing;
import dsm.service.schedule.infra.jaeger.JaegerService;
import dsm.service.schedule.proto.DefaultScheduleResponse;
import dsm.service.schedule.proto.GetScheduleResponse;
import dsm.service.schedule.proto.GetTimeTableResponse;
import dsm.service.schedule.proto.GetTimeTablesResponse;
import lombok.AllArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Component
@Aspect
@AllArgsConstructor
public class AspectJService {
    @Pointcut("execution(* dsm.service.schedule.controller.grpc.ScheduleService.getSchedule(..))")
    private void getSchedulePointCut() {}

    @Pointcut("execution(* dsm.service.schedule.controller.grpc.ScheduleService.getTimeTable(..))")
    private void getTimeTablePointCut() {}

    @Pointcut("execution(* dsm.service.schedule.controller.grpc.ScheduleService.getTimeTables(..))")
    private void getTimeTablesPointCut() {}

    @Pointcut("execution(* dsm.service.schedule.controller.grpc.ScheduleService.updateSchedule(..)) || " +
            "execution(* dsm.service.schedule.controller.grpc.ScheduleService.deleteSchedule(..)) || " +
            "execution(* dsm.service.schedule.controller.grpc.ScheduleService.createSchedule(..))")
    private void defaultSchedulePointCut() {}

    private final JaegerService jaegerService;

    @Around("getSchedulePointCut()")
    public Object getScheduleErrorHandling(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return jaegerService.serviceTracing(pjp);
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
            return jaegerService.serviceTracing(pjp);
        } catch (BusinessException businessException) {
            return GetTimeTableResponse.newBuilder()
                    .setStatus(businessException.getStatusCode())
                    .setCode(businessException.getErrorCode())
                    .setMsg(businessException.getMessage())
                    .build();
        }
    }

    @Around("getTimeTablesPointCut()")
    public Object getTimeTablesErrorHandling(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return jaegerService.serviceTracing(pjp);
        } catch (BusinessException businessException) {
            return GetTimeTablesResponse.newBuilder()
                    .setStatus(businessException.getStatusCode())
                    .setCode(businessException.getErrorCode())
                    .setMsg(businessException.getMessage())
                    .build();
        }
    }

    @Around("defaultSchedulePointCut()")
    public Object defaultErrorHandling(ProceedingJoinPoint pjp) throws Throwable {
        try {
            return jaegerService.serviceTracing(pjp);
        } catch (BusinessException businessException) {
            return DefaultScheduleResponse.newBuilder()
                    .setStatus(businessException.getStatusCode())
                    .setCode(businessException.getErrorCode())
                    .setMsg(businessException.getMessage())
                    .build();
        }
    }

    @Around("@annotation(dsm.service.schedule.infra.aop.annotation.Tracing) && @annotation(tracing)")
    public Object extensionTracing(ProceedingJoinPoint pjp, Tracing tracing) throws Throwable {
        return jaegerService.extensionTracing(pjp, tracing.serviceName());
    }
}