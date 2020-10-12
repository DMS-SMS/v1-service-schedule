package dsm.service.schedule.service.aop.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Tracing {
    String serviceName() default "";
}
