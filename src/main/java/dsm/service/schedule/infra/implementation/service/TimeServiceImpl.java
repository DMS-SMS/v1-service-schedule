package dsm.service.schedule.infra.implementation.service;

import dsm.service.schedule.domain.service.TimeService;
import org.springframework.stereotype.Component;

import java.util.Calendar;

@Component
public class TimeServiceImpl implements TimeService {
    @Override
    public Integer getWeekNumber() {
        Calendar calendar = Calendar.getInstance();

        return calendar.get(Calendar.DAY_OF_WEEK);
    }
}
