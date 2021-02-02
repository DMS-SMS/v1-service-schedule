package dsm.service.schedule.infra.config.db.mysql;

import dsm.service.schedule.infra.config.spring.BeanUtils;
import dsm.service.schedule.infra.jaeger.JaegerService;
import org.hibernate.EmptyInterceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import java.io.Serializable;

public class MysqlInterceptor extends EmptyInterceptor {
    private JaegerService jaegerService;

    @Override
    public void beforeTransactionCompletion(Transaction tx) {
        jaegerService = (JaegerService) BeanUtils.getBean("jaegerService");
        jaegerService.tracingStart("SQL (Transaction)");
        super.beforeTransactionCompletion(tx);
    }

    @Override
    public void afterTransactionCompletion(Transaction tx) {
        jaegerService = (JaegerService) BeanUtils.getBean("jaegerService");
        jaegerService.tracingEnd();
        super.afterTransactionCompletion(tx);
    }

    @Override
    public boolean onLoad(Object entity, Serializable id, Object[] state, String[] propertyNames, Type[] types) {
        jaegerService = (JaegerService) BeanUtils.getBean("jaegerService");
        jaegerService.tracingStart("SQL (Select)");
        jaegerService.tracingEnd();
        return super.onLoad(entity, id, state, propertyNames, types);
    }
}