package dsm.service.schedule.infra.openTracing;

import ch.qos.logback.classic.spi.IThrowableProxy;
import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerSpanContext;
import io.jaegertracing.internal.JaegerTracer;
import io.jaegertracing.internal.MDCScopeManager;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import io.opentracing.log.Fields;
import io.opentracing.propagation.Format;
import io.opentracing.tag.Tags;
import javassist.tools.rmi.Sample;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;


@Component
public class JaegerHandler {
    private Configuration.SamplerConfiguration samplerConfiguration = Configuration.SamplerConfiguration.fromEnv()
            .withParam(1).withType("const");
    private Tracer tracer = Configuration.fromEnv("ScheduleService").withSampler(samplerConfiguration).getTracer();

    public Object serviceTracing(ProceedingJoinPoint pjp) throws Throwable {
        String spanContext = (String) pjp.getArgs()[2];
        String xRequestId = (String) pjp.getArgs()[1];

        Span span = tracer.buildSpan("service").asChildOf(generateSpanContext(spanContext)).start();

        Object service = new Object();
        try (Scope scope = tracer.scopeManager().activate(span)) {
            span.setTag("x-request-id", xRequestId);
            service = pjp.proceed();
        } finally {
            span.finish();
        }
        return service;
    }

    private SpanContext generateSpanContext(String spanContext) {
        String[] splitSpanContext = spanContext.split(":");
        System.out.println(Long.parseUnsignedLong(splitSpanContext[0], 16));
        Long traceIdLow = Long.parseUnsignedLong(splitSpanContext[0], 16);
        Long spanId = Long.parseUnsignedLong(splitSpanContext[1], 16);
        Long parentId = Long.parseUnsignedLong(splitSpanContext[2], 16);
        Byte flags = Byte.valueOf(splitSpanContext[3], 16);


        return new JaegerSpanContext(0, traceIdLow, spanId, parentId, flags);
    }
}
