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
        jaegerHandler.tracingStart("SQL (Transaction)");
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        jaegerHandler = (JaegerHandler) BeanUtils.getBean("jaegerHandler");
        jaegerHandler.tracingEnd();
        super.afterTransactionCompletion(tx);
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        jaegerHandler = (JaegerHandler) BeanUtils.getBean("jaegerHandler");
        jaegerHandler.tracingStart("SQL (Select)");
        jaegerHandler.tracingEnd();
        return super.onLoad(entity, id, state, propertyNames, types);
    }
}
