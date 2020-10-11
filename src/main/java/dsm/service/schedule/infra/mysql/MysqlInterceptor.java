package dsm.service.schedule.infra.mysql;

import dsm.service.schedule.infra.openTracing.JaegerHandler;
import dsm.service.schedule.infra.springBoot.BeanUtils;
import dsm.service.schedule.service.aop.annotation.Tracing;
import lombok.AllArgsConstructor;
import org.apache.catalina.core.ApplicationContext;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.Serializable;


public class MysqlInterceptor extends EmptyInterceptor {
    private JaegerHandler jaegerHandler;

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        jaegerHandler = (JaegerHandler) BeanUtils.getBean("jaegerHandler");
        jaegerHandler.tracingEnd();
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public void afterTransactionBegin(Transaction tx) {
        jaegerHandler = (JaegerHandler) BeanUtils.getBean("jaegerHandler");
        jaegerHandler.tracingStart("SQL");
    }
}
