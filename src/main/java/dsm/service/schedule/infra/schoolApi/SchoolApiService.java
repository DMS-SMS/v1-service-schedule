package dsm.service.schedule.infra.schoolApi;

import java.util.List;
import java.util.Map;

public interface SchoolApiService {
    public Map<Integer, String> getTimeTable(Integer grade, Integer group, Integer year, Integer month, Integer day);
}
