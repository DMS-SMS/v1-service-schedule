package dsm.service.schedule.infra.schoolApi;

import dsm.service.schedule.domain.exception.ServerError;
import dsm.service.schedule.service.aop.annotation.Tracing;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class SchoolApiServiceImpl implements SchoolApiService {
    @Value("${schedule.schoolApi.key}")
    private String key;

    @Override
    @Tracing(serviceName = "schoolAPI (getTimetable)")
    public Map<Integer, String> getTimeTable(Integer grade, Integer group, Integer year, Integer month, Integer day) {
        try {
            HashMap<Integer, String> tableMap = new HashMap<Integer, String>();
            String json = Jsoup.connect(
                    "https://open.neis.go.kr/hub/hisTimetable?" +
                            "KEY="+key+
                            "&Type=json" +
                            "&ATPT_OFCDC_SC_CODE=G10" +
                            "&SD_SCHUL_CODE=7430310" +
                            "&GRADE="+grade.toString()+
                            "&CLASS_NM="+group.toString()+
                            "&ALL_TI_YMD="+year.toString()+month.toString()+day.toString())
                    .ignoreContentType(true).execute().body();
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(json);
            JSONArray hisTimetable = (JSONArray) jsonObject.get("hisTimetable");
            JSONObject timetable = (JSONObject) hisTimetable.get(1);
            JSONArray row = (JSONArray) timetable.get("row");

            for (Object table: row) {
                JSONObject jsonTable = (JSONObject) table;
                tableMap.put(Integer.valueOf(jsonTable.get("PERIO").toString()),
                        jsonTable.get("ITRT_CNTNT").toString());
            }

            return tableMap;
        } catch (Exception e) {
            throw new ServerError(e.getMessage());
        }
    }
}
