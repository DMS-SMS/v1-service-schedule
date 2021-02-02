package dsm.service.schedule.infra.config.db.mysql;

import dsm.service.schedule.infra.consul.ConsulService;
import lombok.AllArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@AllArgsConstructor
public class MysqlConfigure {
    private final ConsulService consulService;

    @Bean
    public DataSource dataSource() {
        JSONObject consulV = consulService.getValue("db/schedule/local");

        DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
        dataSourceBuilder.driverClassName("com.mysql.cj.jdbc.Driver");
        dataSourceBuilder.url((String) consulV.get("url"));
        dataSourceBuilder.username((String) consulV.get("username"));
        dataSourceBuilder.password(System.getenv("DB_PASSWORD"));
        return dataSourceBuilder.build();
    }
}