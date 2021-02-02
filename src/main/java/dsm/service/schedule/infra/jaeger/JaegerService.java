package dsm.service.schedule.infra.jaeger;

import dsm.service.schedule.controller.grpc.MetadataInterceptor;
import io.jaegertracing.Configuration;
import io.jaegertracing.internal.JaegerSpanContext;
import io.opentracing.Scope;
import io.opentracing.Span;
import io.opentracing.SpanContext;
import io.opentracing.Tracer;
import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;


@Component
public class JaegerService {
    private final Configuration.SamplerConfiguration samplerConfiguration = Configuration.SamplerConfiguration.fromEnv()
            .withParam(1).withType("const");
    private final Tracer tracer = Configuration.fromEnv("DMS.SMS.v1.service.schedule").withSampler(samplerConfiguration).getTracer();


    public Object serviceTracing(ProceedingJoinPoint pjp) throws Throwable {
        String spanContext = (String) MetadataInterceptor.spanContext.get();
        String xRequestId = (String) MetadataInterceptor.xRequestId.get();

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

    public Object extensionTracing(ProceedingJoinPoint pjp, String serviceName) throws Throwable {
        Span span = tracer.buildSpan(serviceName).asChildOf(
                tracer.activeSpan()
        ).start();

        Object service = new Object();

        try (Scope scope = tracer.scopeManager().activate(span)) {
            service = pjp.proceed();
        } finally {
            span.finish();
        }
        return service;

    }

    public void tracingStart(String serviceName) {
        Span span1 = tracer.activeSpan();
        Span span = tracer.buildSpan(serviceName).asChildOf(
                tracer.activeSpan()
        ).start();

        tracer.scopeManager().activate(span);
    }

    public void tracingEnd() {
        Span span = tracer.activeSpan();
        span.finish();
    }

    public String getActiveSpanContext() {
        return tracer.activeSpan().context().toString();
    }

    private SpanContext generateSpanContext(String spanContext) {
        String[] splitSpanContext = spanContext.split(":");
        long traceIdLow = Long.parseUnsignedLong(splitSpanContext[0], 16);
        long spanId = Long.parseUnsignedLong(splitSpanContext[1], 16);
        long parentId = Long.parseUnsignedLong(splitSpanContext[2], 16);
        Byte flags = Byte.valueOf(splitSpanContext[3], 16);


        return new JaegerSpanContext(0, traceIdLow, spanId, parentId, flags);
    }
}
